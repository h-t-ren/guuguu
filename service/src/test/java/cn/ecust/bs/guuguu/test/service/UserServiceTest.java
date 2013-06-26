/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import cn.ecust.bs.guuguu.domain.User;
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
  	   userService.register(me);
  	   
  	   User user =  userRepository.findByLogin("hongtao.ren");
  	   log.debug("password is: "+ user.getPassword()); 

      
  }
}
