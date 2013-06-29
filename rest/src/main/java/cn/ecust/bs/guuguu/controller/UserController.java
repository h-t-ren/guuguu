package cn.ecust.bs.guuguu.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.form.MeetingForm;
import cn.ecust.bs.guuguu.form.TimeSlot;
import cn.ecust.bs.guuguu.repo.UserRepository;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserRepository userRepository;

	@RequestMapping(value="{login}/info", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody User userInfo(@PathVariable("login") String login) {
		return userRepository.findByLogin(login);
	}
	
	@RequestMapping(value="/meetingform",method=RequestMethod.GET)
	public String populate(@ModelAttribute MeetingForm meetingForm)
	{
		System.out.println("populate meeting form: ");
		return "meetingform";	
	}
	
	@RequestMapping(value="/meetingform",method=RequestMethod.POST)
	public String create(MeetingForm meetingForm)
	{
		System.out.println("title: " + meetingForm.getTitle());
		return "redirect:/";	
	}
	@RequestMapping(value="/meetingJsonForm",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody MeetingForm populateJson()
	{
		MeetingForm meetingForm = new MeetingForm();
		meetingForm.setTitle("去哪里玩呢");
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
		return meetingForm;
	}
	
	@RequestMapping(value="/meetingJsonForm",method=RequestMethod.POST)
	public @ResponseBody MeetingForm createMeeting(@RequestBody MeetingForm meetingForm)
	{
		System.out.println("title: " + meetingForm.getTitle());
		TimeSlot[] timeslot = meetingForm.getTimeSlot();
		for(TimeSlot t :timeslot)
		{
			System.out.println(" date: " + t.getDate() + ", slot: " + t.getTimeSlot());
		}
		return meetingForm;	
	}
}
