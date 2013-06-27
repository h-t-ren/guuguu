package cn.ecust.bs.guuguu.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public class UserPoll extends Relation {

	private static final long serialVersionUID = 1L;
	@StartNode private User user;
	@EndNode private MeetingTime meetingTime;
	
	public MeetingTime getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(MeetingTime meetingTime) {
		this.meetingTime = meetingTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
