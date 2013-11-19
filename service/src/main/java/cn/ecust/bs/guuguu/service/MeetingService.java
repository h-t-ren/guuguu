package cn.ecust.bs.guuguu.service;


import java.util.List;

import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
public interface MeetingService {
	public void createMeeting(MeetingForm meetingForm) throws Exception;
	public Meeting findByUrl(String url);
	public Meeting findById(Long id);
	public List<Meeting> findYourCreatedMeetings(User user);
	public List<Meeting> findYourParticipateMeetings(User user);
}
