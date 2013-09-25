package cn.ecust.bs.guuguu.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;
import cn.ecust.bs.guuguu.ws.domain.Result;


public class UserRegisterClient {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/appContext.xml");
    RestTemplate restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
    
    String url = "http://localhost:8080/guuguu-rest/rest/user/register";
    GuuGuuUser user = new GuuGuuUser();
    user.setEmail("53564232@qq.com");
    user.setUserName("任宏涛");
    user.setMobile("18916488290");
    user.setPassword("18916488290");
    Result result = (Result)restTemplate.postForObject(url, user, Result.class);
    System.out.println(result.getResult());
    JsonPrinter.print(user);
}
  
}
