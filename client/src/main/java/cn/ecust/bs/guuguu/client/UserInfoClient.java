package cn.ecust.bs.guuguu.client;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;

public class UserInfoClient {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String url = "http://localhost:8080/guuguu-rest/rest/user/53564232@qq.com/info";
    GuuGuuUser user = (GuuGuuUser)restTemplate.getForObject(url, GuuGuuUser.class); 
  
    System.out.println("user name: " + user.getName()+", email: " + user.getEmail()+", mobile: " + user.getMobile());
   
    JsonPrinter.print(user);
}
}
