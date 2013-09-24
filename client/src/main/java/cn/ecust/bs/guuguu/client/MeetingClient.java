package cn.ecust.bs.guuguu.client;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
import cn.ecust.bs.guuguu.ws.domain.PollForm;
import cn.ecust.bs.guuguu.ws.domain.TimeSlot;


public class MeetingClient {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String url = "http://localhost:8080/guuguu-rest/rest/meeting/create";
    MeetingForm meeting = populateMeeting();
    restTemplate.postForObject(url, meeting, MeetingForm.class);
    
}
  
  
  private static MeetingForm populateMeeting()
	{
		MeetingForm meetingForm = new MeetingForm();
		meetingForm.setTitle("去哪里玩呢");
		meetingForm.setCreator("hongtao.ren@gmail.com");
		meetingForm.setCreatorEmail("hongtao.ren@gmail.com");
		meetingForm.setDescription("客户端生成活动");
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
		meetingForm.setInvitations("renh@iiasa.ac.at;53564232@qq.com");
		meetingForm.setIp("192.168.1.1");
		meetingForm.setClientType(ClientType.AndriodApp);
		meetingForm.setComment("每位参加者至少要选择一个时间段");
		return meetingForm;
	}
	
 
}
