/**
 * 
 */
package cn.ecust.bs.guuguu.service;

import cn.ecust.bs.guuguu.domain.ClientType;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingRole;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.UserInMeeting;
import cn.ecust.bs.guuguu.domain.UserPoll;
import cn.ecust.bs.guuguu.domain.VoteSatus;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public interface UserService {
	public void register(User user);
	public UserInMeeting inMeeting(User user,Meeting meeting,String ip,MeetingRole roleInMeeting,ClientType clientType,String comment,VoteSatus voteSatus);
	public UserPoll poll(User user,MeetingTime meetingTime);
}
