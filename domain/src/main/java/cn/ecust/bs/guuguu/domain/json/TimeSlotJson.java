package cn.ecust.bs.guuguu.domain.json;

import java.io.Serializable;

public class TimeSlotJson implements Serializable {

	private String start;
	private String end;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}


}
