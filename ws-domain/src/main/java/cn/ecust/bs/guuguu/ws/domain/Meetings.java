package cn.ecust.bs.guuguu.ws.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meetings implements Serializable {
	private List<MeetingForm> meetings=new ArrayList<MeetingForm>();

	public List<MeetingForm> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<MeetingForm> meetings) {
		this.meetings = meetings;
	}
}
