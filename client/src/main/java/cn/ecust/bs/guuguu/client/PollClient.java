package cn.ecust.bs.guuguu.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.PollForm;

public class PollClient {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    
    String pollUrl =ServerURL.restURL+ "meeting/poll";
    PollForm pollForm =populatePoll();
    restTemplate.postForObject(pollUrl, pollForm, PollForm.class);
    
}
  
	private static PollForm populatePoll() {
		  PollForm pollForm = new PollForm();
		  pollForm.setClientType(ClientType.AndriodApp);
		  pollForm.setComment("只有这一天有时间");
		  pollForm.setIdMeeting(3l);
		  pollForm.setEmail("53564232@qq.com");
		  pollForm.setIdSlots(new Long[]{4l,5l});
		  return pollForm;
		}
}
