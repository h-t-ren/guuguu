package cn.ecust.bs.guuguu.client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.transform.StringResult;

import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
public class Example {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String xmlurl = ServerURL.restURL+"example/xml/meeting";
    MeetingForm meeting = (MeetingForm)restTemplate.getForObject(xmlurl, MeetingForm.class); 
  
    //object to XML
    Jaxb2Marshaller jaxbMarshaller = applicationContext.getBean("jaxbMarshaller",Jaxb2Marshaller.class);
    StringResult  result = new StringResult();
    jaxbMarshaller.marshal(meeting, result);
    System.out.println(result.toString());
    
    //object to JSON
    String jsonurl = ServerURL.restURL+"example/json/meeting";
    MeetingForm meeting1 = (MeetingForm)restTemplate.getForObject(jsonurl, MeetingForm.class);
    
    JsonPrinter.print(meeting1);
    
}
}
