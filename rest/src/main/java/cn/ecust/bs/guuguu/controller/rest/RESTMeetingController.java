package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.Role;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.form.MeetingForm;
import cn.ecust.bs.guuguu.form.PollForm;
import cn.ecust.bs.guuguu.form.TimeSlot;
import cn.ecust.bs.guuguu.repo.MeetingRepository;
import cn.ecust.bs.guuguu.repo.MeetingTimeRepository;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.EmailSenderService;
import cn.ecust.bs.guuguu.service.UserService;

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

	@RequestMapping(value = "example", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	MeetingForm populateExample() {
		MeetingForm meetingForm = new MeetingForm();
		meetingForm.setTitle("去哪里玩呢");
		meetingForm.setCreator("hongtao.ren");
		meetingForm.setCreatorEmail("hongtao.ren@gmail.com");
		TimeSlot t1 = new TimeSlot();
		t1.setDate(new Date());
		t1.setTimeSlot("9:10-10:00");

		TimeSlot t2 = new TimeSlot();
		t2.setDate(new Date());
		t2.setTimeSlot("19:10-22:00");

		TimeSlot[] timeSlots = new TimeSlot[2];
		timeSlots[0] = t1;
		timeSlots[1] = t2;
		meetingForm.setTimeSlot(timeSlots);
		meetingForm.setLocation("上海华东理工大学励志公寓104");
		meetingForm.setDescription("声称Json对象的一个测试");
		String[] receivers = new String[] { "tjma@ecust.edu.cn",
				"renh@iiasa.ac.at", "53564232@163.com" };
		meetingForm.setReceivers(receivers);
		meetingForm.setIp("192.168.1.1");
		meetingForm.setClientType(ClientType.Web);
		meetingForm.setComment("每位参加者至少要选择一个时间段");
		return meetingForm;
	}

	
	
	@RequestMapping(value = "pollexample", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	PollForm populatePollExample() {
	  PollForm pollForm = new PollForm();
	  pollForm.setClientType(ClientType.AndriodApp);
	  pollForm.setComment("只有这一天有时间");
	  pollForm.setIdMeeting(3l);
	  pollForm.setEmail("53564232@qq.com");
	  pollForm.setIdSlots(new Long[]{4l,5l});
	  return pollForm;
	}
	
	
	
	
	
	@RequestMapping(value = "example", method = RequestMethod.POST)
	public @ResponseBody
	MeetingForm createExample(@RequestBody MeetingForm meetingForm) {
		// System.out.println("title: " + meetingForm.getTitle());
		TimeSlot[] timeslot = meetingForm.getTimeSlot();
		for (TimeSlot t : timeslot) {
			// System.out.println(" date: " + t.getDate() + ", slot: " +
			// t.getTimeSlot());
		}
		return meetingForm;
	}

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
