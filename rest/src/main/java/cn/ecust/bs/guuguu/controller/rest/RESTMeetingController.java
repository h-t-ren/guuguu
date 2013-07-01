package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.MeetingRepository;
import cn.ecust.bs.guuguu.repo.MeetingTimeRepository;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.EmailSenderService;
import cn.ecust.bs.guuguu.service.UserService;
import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
import cn.ecust.bs.guuguu.ws.domain.PollForm;
import cn.ecust.bs.guuguu.ws.domain.Role;
import cn.ecust.bs.guuguu.ws.domain.TimeSlot;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/rest/meeting")
public class RESTMeetingController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private MeetingTimeRepository meetingTimeRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	MeetingForm createMeeting(@RequestBody MeetingForm meetingForm) {
		User user = userRepository.findByEmail(meetingForm.getCreatorEmail());
		if (user == null) {
			user = new User();
			user.setCreated(new Date());
			user.setEmail(meetingForm.getCreatorEmail());
			user.setLogin(meetingForm.getCreatorEmail());
			user.setRoles(new Role[] { Role.ROLE_USER });
			userRepository.save(user);
		}
		Meeting meeting = new Meeting();
		meeting.setCreated(new Date());
		meeting.setTitle(meetingForm.getTitle());
		meeting.setDescription(meetingForm.getDescription());
		meeting.setLocation(meetingForm.getLocation());
		meeting.setReceivers(meetingForm.getReceivers());
		meetingRepository.save(meeting);
		TimeSlot[] timeslot = meetingForm.getTimeSlot();
		int seq = 1;
		for (TimeSlot ts : timeslot) {
			MeetingTime t = new MeetingTime();
			t.setMeeting(meeting);
			t.setSeqence(seq);
			t.setDate(ts.getDate());
			t.setTimeSlot(ts.getTimeSlot());
			meetingTimeRepository.save(t);
			seq++;
		}
		userService.createOrParticipateMeeting(user, meeting,
				meetingForm.getIp(), ClientType.Web, meetingForm.getComment(),
				true);
		String subject = "邀请您参加一个活动";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("url", "www.guuguu.com/rssadsxssdsds/");
		emailSenderService.sendEmail(meetingForm.getReceivers(), subject, model, null);
		return meetingForm;
	}
	
	
	@RequestMapping(value = "poll", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	PollForm pollMeeting(@RequestBody PollForm pollForm) {
		 User user =  userRepository.findByEmail(pollForm.getEmail());
		 if (user == null) {
				user = new User();
				user.setCreated(new Date());
				user.setEmail(pollForm.getEmail());
				user.setLogin(pollForm.getEmail());
				user.setRoles(new Role[] { Role.ROLE_USER });
				userRepository.save(user);
			}
		 Meeting meeting = meetingRepository.findOne(pollForm.getIdMeeting());
	  	 userService.createOrParticipateMeeting(user, meeting, pollForm.getIp(), ClientType.AndriodApp, pollForm.getComment(),false);
	  	 for(long idSlot:pollForm.getIdSlots())
	  	 {
	  	  	 userService.poll(user, meetingTimeRepository.findOne(idSlot));
	  	 }
		return pollForm;
	}
}
