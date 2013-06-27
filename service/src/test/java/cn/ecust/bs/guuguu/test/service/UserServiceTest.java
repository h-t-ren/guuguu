/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;


import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingRole;
import cn.ecust.bs.guuguu.domain.Role;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserInMeeting;
import cn.ecust.bs.guuguu.domain.VoteSatus;
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
	   DynamicProperties slots = new DynamicPropertiesContainer();
	   String[] slot1 = {"上午","下午"};
	   slots.setProperty(new Date().toString(),slot1);

	 
	   Calendar c=Calendar.getInstance();
	   c.add(Calendar.DAY_OF_MONTH, 1);
	   String[] slot2 = {"9:00-10:00","13:00-15:00","17:00-19:00"};
	   slots.setProperty(c.getTime().toString(), slot2);
	   
	   meeting.setSlots(slots);
	   meetingRepository.save(meeting);
  	   User user =  userRepository.findByLogin("hongtao.ren");
  	  
  	   
  	   userService.inMeeting(user, meeting, "192.168.0.2", MeetingRole.Leader, ClientType.Web, "每个人至少选一个时间段", VoteSatus.invitated);


  	   User tieju =  userRepository.findByLogin("tieju.ma");
  	   UserInMeeting userInMeeting =userService.inMeeting(tieju, meeting, "192.168.0.10", MeetingRole.Attendee, ClientType.AndriodApp, null, VoteSatus.voted);
  	
  	   Map<String,Object> meetingSlots= meeting.getSlots().asMap();
  	   String[] poll=new String[5];
  	   int i=0;
  	   for(String date:meetingSlots.keySet())
  	   {
  		      String[] slts =(String[])meetingSlots.get(date);
  			  for(String s: slts)
  			  {
  				  poll[i]=date+"&&"+s;
  				  i++;
  			  }

  	   }
  	   userInMeeting.setPoll(poll);
  	 template.save(userInMeeting);
  }
}
