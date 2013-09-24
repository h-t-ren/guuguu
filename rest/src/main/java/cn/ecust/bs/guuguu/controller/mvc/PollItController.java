package cn.ecust.bs.guuguu.controller.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.MeetingTimeRepository;
import cn.ecust.bs.guuguu.repo.MeetingRepository;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.MeetingService;
import cn.ecust.bs.guuguu.service.UserService;
import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.Role;


@Controller
@Transactional(readOnly = true)

public class PollItController {
	@Autowired private UserRepository userRepository;
	@Autowired private MeetingRepository meetingRepository;
	@Autowired private MeetingTimeRepository meetingTimeRepository;
	@Autowired private MeetingService meetingService;
	@Autowired private UserService userService;
	@Value("#{application_prop['urlContext']}") private String urlContext;

	@RequestMapping(value="/pollIt/{timeStamp}/email/{email}",method=RequestMethod.GET)
	public String populate(@PathVariable long timeStamp,@PathVariable String email, Model model)
	{
		Meeting meeting =meetingService.findByUrl(urlContext+timeStamp);
		model.addAttribute("meeting", meeting);
		
		List<MeetingTime> meetingTimes= meetingRepository.getMeetingTimes(meeting);
		model.addAttribute("meetingTimes", meetingTimes);
		
		List<User> users=meetingRepository.getPaticipates(meeting);
		model.addAttribute("users", users);
		Map<String,List<String>> userHash = new LinkedHashMap<String,List<String>>(); 
		for(User u:users)
		{
			List<String> options = new ArrayList<String>();
			for(MeetingTime mt:meetingTimes)
			{
				if(meetingTimeRepository.checkIfUserPolled(u, mt.getId())!=null)
				{
					options.add("有空");

				}
				else
				{
					options.add("繁忙");
				}
				
			}
			userHash.put(u.getLogin(), options);
			if(u.getLogin().equals(email))
			{
				model.addAttribute("message", "您已经完成投票了!");
			}
			model.addAttribute("userHash",userHash);
		}
		return "/pollIt";
	}
	
	@RequestMapping(value="/pollIt/{timeStamp}/email/{email}",method=RequestMethod.POST)
	public String save(@PathVariable long timeStamp,@PathVariable String email,HttpServletRequest request, Model model)
	{
		Meeting meeting =meetingService.findByUrl(urlContext+timeStamp);
		 User user =  userRepository.findByEmail(email);
		 if (user == null) {
				user = new User();
				user.setCreated(new Date());
				user.setEmail(email);
				user.setLogin(email);
				user.setRoles(new Role[] { Role.ROLE_USER });
				userRepository.save(user);
		 }
	  	 userService.createOrParticipateMeeting(user, meeting, request.getRemoteAddr(), ClientType.Web,null,false);

		List<MeetingTime> meetingTimes= meetingRepository.getMeetingTimes(meeting);
	    for(MeetingTime meetingTime:meetingTimes)
	    {
	    	String id =request.getParameter("sel_"+meetingTime.getId());
	    	if(id!=null)
	    	{
	    		userService.poll(user, meetingTime);
	    	}
	    }
		
		return "redirect:/pollIt/{timeStamp}/email/{email}";
	}

}
