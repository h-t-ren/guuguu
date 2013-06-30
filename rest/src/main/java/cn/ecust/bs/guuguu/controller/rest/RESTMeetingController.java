package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ecust.bs.guuguu.form.MeetingForm;
import cn.ecust.bs.guuguu.form.TimeSlot;
import cn.ecust.bs.guuguu.repo.UserRepository;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/rest/meeting")
public class RESTMeetingController {
	
	@Autowired private UserRepository userRepository;

	@RequestMapping(value="example",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody MeetingForm populateExample()
	{
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
		String[] receivers = new String[]{"tieju@ecust.edu.cn","ddd@iiasa.ac.at","asasas@163.com"};
		meetingForm.setReceivers(receivers);
		return meetingForm;
	}
	@RequestMapping(value="example",method=RequestMethod.POST)
	public @ResponseBody MeetingForm createExample(@RequestBody MeetingForm meetingForm)
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
