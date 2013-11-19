package cn.ecust.bs.guuguu.repo;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.RelationType;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserParticipateMeeting;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface MeetingRepository extends GraphRepository<Meeting>{	
	
	 public Meeting findByUrl(String url);
	 @Query( "start meeting=node({0}) " +
             " match meeting-[r:"+RelationType.MeetingHasTimeSlots+"]->meetingTimes " +
             " return meetingTimes order by meetingTimes.seqence") 
     public List<MeetingTime> getMeetingTimes(Meeting meeting);
	 
	 
	 
	 @Query( "start meeting=node({0}) " +
             " match meeting<-[r:"+RelationType.UserParticipateMeeting+"]-users " +
             " return r")
     public List<UserParticipateMeeting> getUserParticipateMeeting(Meeting meeting);
	 
	 @Query( "start meeting=node({0}) " +
             " match meeting<-[r:"+RelationType.UserParticipateMeeting+"]-users " +
             " return users")
     public List<User> getPaticipates(Meeting meeting);
	
	 @Query( "start user=node({0}) " +
             " match user-[r:"+RelationType.UserCreateMeeting+"]->meeting " +
             " return meeting") 
	 public List<Meeting> findYourCreatedMeetings(User user);
	 
	 @Query( "start user=node({0}) " +
             " match user-[r:"+RelationType.UserParticipateMeeting+"]->meeting " +
             " return meeting") 
	 public List<Meeting> findYourParticipateMeetings(User user);
	 
}
