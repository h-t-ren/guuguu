package cn.ecust.bs.guuguu.client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.transform.StringResult;

import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
public class Client {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String url = "http://localhost:8080/guuguu-rest/rest/example/xml/meeting";
    MeetingForm meeting = (MeetingForm)restTemplate.getForObject(url, MeetingForm.class); 
  
    //object to XML
    Jaxb2Marshaller jaxbMarshaller = applicationContext.getBean("jaxbMarshaller",Jaxb2Marshaller.class);
    StringResult  result = new StringResult();
    jaxbMarshaller.marshal(meeting, result);
    System.out.println(result.toString());
   
    
    
    //object to JSON
}
}
