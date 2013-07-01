package cn.ecust.bs.guuguu.controller.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ecust.bs.guuguu.ws.domain.MeetingForm;



@Controller
@Transactional(readOnly = true)
@RequestMapping("/meeting")
public class MeetingController {
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String populate(@ModelAttribute MeetingForm meetingForm)
	{
		return "meetingform";	
	}
	
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String create(MeetingForm meetingForm)
	{
		System.out.println("title: " + meetingForm.getTitle());
		return "redirect:/";	
	}

}
