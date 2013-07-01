package cn.ecust.bs.guuguu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.RelationType;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserCreateMeeting;
import cn.ecust.bs.guuguu.domain.UserParticipateMeeting;
import cn.ecust.bs.guuguu.domain.UserPoll;
import cn.ecust.bs.guuguu.repo.UserRepository;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */

@Service("userService") 
public class UserServiceImpl implements UserService {

	@Autowired private Neo4jOperations template;
    @Autowired private UserRepository userRepository;
    private static final String SALT = "guuguu,guuguu";
    
	/* (non-Javadoc)
	 * @see cn.ecust.bs.guuguu.service.UserService#register(cn.ecust.bs.guuguu.domain.User)
	 */
	@Override @Transactional
	public void register(User user) {
		user.setPassword(encode(user.getPassword()));
		userRepository.save(user);
	}

    private String encode(String password) {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String md5Hash = md5.encodePassword(password, SALT);
         return md5Hash;
       }

	/* (non-Javadoc)
	 * @see cn.ecust.bs.guuguu.service.UserService#createMeeting(cn.ecust.bs.guuguu.domain.Meeting, java.lang.String, cn.ecust.bs.guuguu.domain.MeetingRole, cn.ecust.bs.guuguu.domain.ClientType, java.lang.String)
	 */
	@Override @Transactional
	public void createOrParticipateMeeting(User user,Meeting meeting, String ip,
			ClientType clientType, String comment,Boolean create) {
		if(create)
		{
			UserCreateMeeting userCreateMeeting = template.createRelationshipBetween(user, meeting, UserCreateMeeting.class, RelationType.UserCreateMeeting, false);
			userCreateMeeting.setClientType(clientType);
			userCreateMeeting.setIp(ip);
			userCreateMeeting.setComment(comment);
			template.save(userCreateMeeting);
		}
		else
		{
			UserParticipateMeeting userParticipateMeeting = template.createRelationshipBetween(user, meeting, UserParticipateMeeting.class, RelationType.UserParticipateMeeting, false);
			userParticipateMeeting.setClientType(clientType);
			userParticipateMeeting.setIp(ip);
			userParticipateMeeting.setComment(comment);
			template.save(userParticipateMeeting);
		}
	

	}

	/* (non-Javadoc)
	 * @see cn.ecust.bs.guuguu.service.UserService#poll(cn.ecust.bs.guuguu.domain.User, cn.ecust.bs.guuguu.domain.MeetingTime)
	 */
	@Override @Transactional
	public UserPoll poll(User user, MeetingTime meetingTime) {
		UserPoll userPoll =template.createRelationshipBetween(user, meetingTime, UserPoll.class, RelationType.Poll, false);
		Integer count =meetingTime.getCount();
		if(count==null)
		{
			count =1;
		}
		else
		{
			count =count+1;
		}
		meetingTime.setCount(count);
		template.save(meetingTime);
		return userPoll;
	}
}
