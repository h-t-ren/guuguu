package cn.ecust.bs.guuguu.client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;

public class Client {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String url = "http://localhost:8080/guuguu-rest/rest/example/xml/meeting";
  
    MeetingForm meeting = (MeetingForm)restTemplate.getForObject(url, MeetingForm.class);
  
    System.out.println("meeting - title:" + meeting.getTitle());

}
}
