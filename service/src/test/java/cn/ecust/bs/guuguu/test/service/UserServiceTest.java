/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;


import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.Role;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.MeetingRepository;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.service.UserService;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service-test-context.xml"})

public class UserServiceTest {
  @Autowired private UserService userService;
  @Autowired protected UserRepository userRepository;
  @Autowired protected MeetingRepository meetingRepository;
  @Autowired private Neo4jOperations template;
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @Before
  public void setUp() throws Exception {
  }
  @Test @Transactional
  @Rollback(false)
  public void testRegisterUser() throws Exception {
  	   User me = new User();
  	   me.setLogin("hongtao.ren");
  	   me.setPassword("1977921");
  	   Role[] roles={Role.ROLE_BROWSER,Role.ROLE_USER};
       me.setRoles(roles);
  	   userService.register(me);
  	   
  	   User user =  userRepository.findByLogin("hongtao.ren");
  	   log.debug("password is: "+ user.getPassword()); 
  	   
  	   
  	   User tieju = new User();
  	   tieju.setLogin("tieju.ma");
       tieju.setPassword("123456");
  	   Role[] roles1={Role.ROLE_BROWSER};
       tieju.setRoles(roles1);
  	   userService.register(tieju);
  }
  
  @Test @Transactional
  @Rollback(false)
  public void testCreateMeeting() throws Exception {
	   Meeting meeting = new Meeting();
	   meeting.setCreated(new Date());
	   meeting.setTitle("暑假郊游");
	   meeting.setDescription("利用暑假期间组织实验室内所有成员去旅游");
	   meeting.setLocation("济州岛");
	   meetingRepository.save(meeting);
	   
	   MeetingTime t1= new MeetingTime();
	   t1.setMeeting(meeting);
	   t1.setSeqence(1);
	   t1.setDate(new Date());
	   t1.setTimeSlot("上午");
	   template.save(t1);

	   MeetingTime t2= new MeetingTime();
	   t2.setMeeting(meeting);
	   t2.setSeqence(2);
	   t2.setDate(new Date());
	   t2.setTimeSlot("下午");
	   template.save(t2);
	   
	   
	   
	 
	   Calendar c=Calendar.getInstance();
	   c.add(Calendar.DAY_OF_MONTH, 1);
	  
	   MeetingTime t3= new MeetingTime();
	   t3.setMeeting(meeting);
	   t3.setSeqence(3);
	   t3.setDate(c.getTime());
	   t3.setTimeSlot("9:00-10:00");
	   template.save(t3);
	   
	   
	   
  	   User user =  userRepository.findByLogin("hongtao.ren");
  	   userService.createOrParticipateMeeting(user, meeting, "192.168.0.2", ClientType.Web, "每个人至少选一个时间段",true);

  	   User tieju =  userRepository.findByLogin("tieju.ma");
  	   userService.createOrParticipateMeeting(tieju, meeting, "192.168.0.10", ClientType.AndriodApp, null,false);
  	   userService.poll(tieju, t1);
  	   userService.poll(tieju, t3);
  }
}
