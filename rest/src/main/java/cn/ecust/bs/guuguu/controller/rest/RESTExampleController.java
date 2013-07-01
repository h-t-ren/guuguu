package cn.ecust.bs.guuguu.controller.rest;

import java.util.Date;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
import cn.ecust.bs.guuguu.ws.domain.PollForm;
import cn.ecust.bs.guuguu.ws.domain.TimeSlot;


@Controller
@RequestMapping("/rest/example")
public class RESTExampleController {

	@RequestMapping(value = "json/meeting", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	MeetingForm populateJsonMeeting() {
		System.out.println("pop json");
		return populateMeetingExample();
	} 

	@RequestMapping(value = "xml/meeting", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	MeetingForm populateXMLMeeting() {
		System.out.println("pop xml");
		return populateMeetingExample();
	}
	
	
	@RequestMapping(value = "json/poll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	PollForm populateJsonPoll() {
	  return populatePollExmaple();
	}
	
	@RequestMapping(value = "xml/poll", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody
	PollForm populateXMLPoll() {
	  return populatePollExmaple();
	}
	
	private MeetingForm populateMeetingExample()
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
		String[] receivers = new String[] { "tjma@ecust.edu.cn",
				"renh@iiasa.ac.at", "53564232@163.com" };
		meetingForm.setReceivers(receivers);
		meetingForm.setIp("192.168.1.1");
		meetingForm.setClientType(ClientType.Web);
		meetingForm.setComment("每位参加者至少要选择一个时间段");
		return meetingForm;
	}
	
	
	private PollForm populatePollExmaple() {
	  PollForm pollForm = new PollForm();
	  pollForm.setClientType(ClientType.AndriodApp);
	  pollForm.setComment("只有这一天有时间");
	  pollForm.setIdMeeting(3l);
	  pollForm.setEmail("53564232@qq.com");
	  pollForm.setIdSlots(new Long[]{4l,5l});
	  return pollForm;
	}
	
}