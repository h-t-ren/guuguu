/**
 * 
 */
package cn.ecust.bs.guuguu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingRole;
import cn.ecust.bs.guuguu.domain.RelationType;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserInMeeting;
import cn.ecust.bs.guuguu.domain.VoteSatus;
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
	public UserInMeeting inMeeting(User user,Meeting meeting, String ip,
			MeetingRole roleInMeeting, ClientType clientType, String comment,VoteSatus voteSatus) {
		
		UserInMeeting userInMeeting = template.createRelationshipBetween(user, meeting, UserInMeeting.class, RelationType.UserInMeeting, false);
		userInMeeting.setClientType(clientType);
		userInMeeting.setIp(ip);
		userInMeeting.setComment(comment);
		userInMeeting.setRoleInMeeting(roleInMeeting);
		userInMeeting.setVoteSatus(voteSatus);
		template.save(userInMeeting);
		return userInMeeting;
	}
}
