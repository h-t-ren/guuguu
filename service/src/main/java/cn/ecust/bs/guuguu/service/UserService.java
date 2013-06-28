package cn.ecust.bs.guuguu.service;

import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserPoll;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface UserService {
	public void register(User user);
	public void createOrParticipateMeeting(User user,Meeting meeting,String ip,ClientType clientType,String comment,Boolean create);
	public UserPoll poll(User user,MeetingTime meetingTime);
}
