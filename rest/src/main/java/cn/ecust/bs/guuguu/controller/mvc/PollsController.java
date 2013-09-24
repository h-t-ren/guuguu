package cn.ecust.bs.guuguu.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.MeetingService;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/polls")
public class PollsController {
	@Autowired private UserRepository userRepository;
	@Autowired private MeetingService meetingService;
	@RequestMapping(value="/newPoll",method=RequestMethod.GET)
	public String populateDescription(Model model)
	{
		MeetingForm meeting = new MeetingForm();
		model.addAttribute("meeting", meeting);
		return "/polls/description";	
	}
	
	@RequestMapping(value="/newPoll",method=RequestMethod.POST, params="step1")
	public String saveDescription(Model model,@ModelAttribute("meeting") MeetingForm meeting)
	{
		model.addAttribute("meeting", meeting);
		return "/polls/dates";	
	}
	@RequestMapping(value="/newPoll",method=RequestMethod.POST, params="step2")
	public String saveDates(Model model,@ModelAttribute("meeting") MeetingForm meeting,@RequestParam(required = false) String cancel)
	{
		model.addAttribute("meeting", meeting);
		if(cancel!=null)
			return "/polls/description";	
		return "/polls/invitation";	
	}
	
	@RequestMapping(value="/newPoll",method=RequestMethod.POST, params="finish")
	public String saveMeeting(Model model,@ModelAttribute("meeting") MeetingForm meeting,@RequestParam(required = false) String cancel)
	{
		model.addAttribute("meeting", meeting);
		if(cancel!=null)
			return "/polls/dates";
		try {
			meetingService.createMeeting(meeting);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/polls/error";	
		}
		return "redirect:/polls/success";	
	}
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String success(Model model)
	{
		return "/polls/success";	
	}
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error(Model model)
	{
		return "/polls/error";	
	}
	
}
