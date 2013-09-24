package cn.ecust.bs.guuguu.domain.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotJsons implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<TimeSlotJson> timeSlotJsons= new ArrayList<TimeSlotJson>(0);

	public List<TimeSlotJson> getTimeSlotJsons() {
		return timeSlotJsons;
	}

	public void setTimeSlotJsons(List<TimeSlotJson> timeSlotJsons) {
		this.timeSlotJsons = timeSlotJsons;
	}

}
