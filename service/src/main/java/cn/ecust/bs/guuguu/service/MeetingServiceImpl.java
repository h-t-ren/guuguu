package cn.ecust.bs.guuguu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ecust.bs.guuguu.repo.MeetingRepository;
import cn.ecust.bs.guuguu.repo.MeetingTimeRepository;
import cn.ecust.bs.guuguu.repo.UserRepository;
import cn.ecust.bs.guuguu.ws.domain.ClientType;
import cn.ecust.bs.guuguu.ws.domain.MeetingForm;
import cn.ecust.bs.guuguu.ws.domain.Role;
import cn.ecust.bs.guuguu.ws.domain.TimeSlot;
import cn.ecust.bs.guuguu.domain.Meeting;
import cn.ecust.bs.guuguu.domain.MeetingTime;
import cn.ecust.bs.guuguu.domain.User;
import cn.ecust.bs.guuguu.domain.json.TimeSlotJson;
import cn.ecust.bs.guuguu.domain.json.TimeSlotJsons;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MeetingTimeRepository meetingTimeRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserService userService;
	@Value("#{application_prop['urlContext']}") private String urlContext;
	@Override @Transactional
	public void createMeeting(MeetingForm meetingForm) throws Exception {
		Meeting meeting = new Meeting();
		meeting.setTitle(meetingForm.getTitle());
		meeting.setLocation(meetingForm.getLocation());
		meeting.setDescription(meetingForm.getDescription());
		meeting.setUserName(meetingForm.getCreator());
		meeting.setUserEmail(meetingForm.getCreatorEmail());
		Date created = new Date();
		meeting.setCreated(created);
		meeting.setUrl(urlContext+created.getTime());
		User user = userRepository.findByEmail(meetingForm.getCreatorEmail());
		if (user == null) {
			user = new User();
			user.setCreated(new Date());
			user.setEmail(meetingForm.getCreatorEmail());
			user.setLogin(meetingForm.getCreatorEmail());
			user.setUserName(meetingForm.getCreator());
			user.setRoles(new Role[] { Role.ROLE_USER });
			userRepository.save(user);
		}
		meetingRepository.save(meeting);
		String events = meetingForm.getEventsJson();
		if(events!=null)
		{
			ObjectMapper mapper = new ObjectMapper();
			TimeSlotJsons tsss = mapper.readValue(events, TimeSlotJsons.class);
			SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss",Locale.ENGLISH);
			int seq = 1;
			for(TimeSlotJson timeSlotJson:tsss.getTimeSlotJsons())
			{
				String start = timeSlotJson.getStart().replace(" GMT+0800", "");
				String end = timeSlotJson.getEnd().replace(" GMT+0800", "");
				Date dStart =sdf.parse(start);
				Date dEnd =sdf.parse(end);

				Calendar startCal=Calendar.getInstance(); 
				startCal.setTime(dStart);
				
				Calendar endCal=Calendar.getInstance(); 
				endCal.setTime(dEnd);
				
				MeetingTime t = new MeetingTime();
				t.setMeeting(meeting);
				t.setSeqence(seq);
				t.setDate(dStart);
				t.setTimeSlot(startCal.get(Calendar.HOUR_OF_DAY)+":"+startCal.get(Calendar.MINUTE)+"-"+endCal.get(Calendar.HOUR_OF_DAY)+":"+endCal.get(Calendar.MINUTE));
				meetingTimeRepository.save(t);
				seq++;
			}
		}
		else
		{
			int seq=1;
			for(TimeSlot ts:meetingForm.getTimeSlot())
			{
				MeetingTime t = new MeetingTime();
				t.setMeeting(meeting);
				t.setSeqence(seq);
				t.setDate(ts.getDate());
				t.setTimeSlot(ts.getTimeSlot());
				meetingTimeRepository.save(t);
				seq++;
			}
		}

		userService.createOrParticipateMeeting(user, meeting,
				meetingForm.getIp(), meetingForm.getClientType(), meetingForm.getComment(),
				true);
		String subject = "邀请您参加一个活动";
		
		String invitations = meetingForm.getInvitations();
		List<String> invitationLst = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(invitations, ";");
		while(st.hasMoreElements())
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", meetingForm.getCreator());
			String email =st.nextToken();
			model.put("url", urlContext+meeting.getCreated().getTime()+"/email/"+email);
			invitationLst.add(email);
			emailSenderService.sendEmail(new String[]{email}, subject, model, null);
		}
		invitationLst.add(meetingForm.getCreatorEmail());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", meetingForm.getCreator());
		model.put("url", urlContext+meeting.getCreated().getTime()+"/email/"+meetingForm.getCreatorEmail());
		emailSenderService.sendEmail(new String[]{meetingForm.getCreatorEmail()}, subject, model, null);
		String[] receivers = new String[invitationLst.size()];
		int i=0;
		for(String receiver:invitationLst)
		{
			receivers[i]=receiver;
			i++;
		}
		meeting.setReceivers(receivers);
		meetingRepository.save(meeting);
	}
	@Override @Transactional(readOnly=true)
	public Meeting findByUrl(String url) {
		return meetingRepository.findByUrl(url);
	}




}
