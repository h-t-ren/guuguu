package cn.ecust.bs.guuguu.repo;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.RelationType;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserPoll;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface MeetingTimeRepository  extends GraphRepository<MeetingTime>{	
	
	
	 @Query( "start user=node({0}) " +
             " match user-[r:"+RelationType.Poll+"]->meetingTime " +
			 " where ID(meetingTime)={1} "+
             " return r") 
     public UserPoll checkIfUserPolled(User user,long meetingTimeId);
	 
}
