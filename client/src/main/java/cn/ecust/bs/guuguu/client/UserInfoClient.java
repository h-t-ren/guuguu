package cn.ecust.bs.guuguu.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;

public class UserInfoClient {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    String url = ServerURL.restURL+"rest/user/53564232@qq.com/info";
    GuuGuuUser user = (GuuGuuUser)restTemplate.getForObject(url, GuuGuuUser.class); 
  
    System.out.println("user name: " + user.getUserName()+", email: " + user.getEmail()+", mobile: " + user.getMobile());
   
    JsonPrinter.print(user);
}
}
