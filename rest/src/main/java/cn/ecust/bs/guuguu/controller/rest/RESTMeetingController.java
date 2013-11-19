package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ecust.bs.guuguu.domain.Meeting;
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
}
