package cn.ecust.bs.guuguu.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
import cn.ecust.bs.guuguu.service.MeetingService;
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
	@Autowired private MeetingService meetingService;

	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	MeetingForm createMeeting(@RequestBody MeetingForm meetingForm) {
		try {
			meetingService.createMeeting(meetingForm);
		} catch (Exception e) {
			e.printStackTrace();
			return meetingForm;	
		}
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
	
	@RequestMapping(value = "user/{login}/meetings", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	List<MeetingForm> findMeetings(@PathVariable("login") String login) {
		 User user =  userRepository.findByLogin(login);
		 List<Meeting> meetings = meetingService.findYourCreatedMeetings(user);
		 List<MeetingForm> meetingForms = new ArrayList<MeetingForm>(0);
		 for(Meeting m : meetings)
		 {
			 meetingForms.add(toWSDomain(m));
		 }
		 return meetingForms;
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	MeetingForm findMeetingById(@PathVariable("id") Long id) {
		return toWSDomain(meetingRepository.findOne(id));
	}
	
	@Transactional(readOnly = true)
	private MeetingForm toWSDomain(Meeting meeting)
	{
		MeetingForm meetingForm = new MeetingForm();
		meetingForm.setId(meeting.getId());
		meetingForm.setTitle(meeting.getTitle());
		meetingForm.setCreator(meeting.getUserName());
		meetingForm.setCreatorEmail(meeting.getUserEmail());
		meetingForm.setCreated(meeting.getCreated());
		meetingForm.setLocation(meeting.getLocation());
		meetingForm.setDescription(meeting.getDescription());
		meetingForm.setReceivers(meeting.getReceivers());
		List<MeetingTime> mts=meetingRepository.getMeetingTimes(meeting);
		TimeSlot[] ts = new TimeSlot[mts.size()];
		for(MeetingTime mt:mts)
		{
			ts[mt.getSeqence()].setSeqence(mt.getSeqence());
			ts[mt.getSeqence()].setDate(mt.getDate());
			ts[mt.getSeqence()].setCount(mt.getCount());
			ts[mt.getSeqence()].setTimeSlot(mt.getTimeSlot());
		}

		return meetingForm;
	}
}
