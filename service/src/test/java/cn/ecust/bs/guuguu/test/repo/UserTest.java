package cn.ecust.bs.guuguu.test.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.repo.UserRepository;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service-test-context.xml"})
@Transactional
public class UserTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired Neo4jOperations template;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateUser() throws Exception {
    	User me = new User();
    	me.setLogin("hongtao.ren");
        User user = userRepository.save(me);
        assertEquals("user login", "hongtao.ren",user.getLogin());
  
        User user2=userRepository.findOne(user.getId());
        assertEquals("loaded user id", user.getId(),user2.getId());
        assertEquals("loaded user login", "hongtao.ren",user2.getLogin());
    }
}