package cn.ecust.bs.guuguu.form;

import java.io.Serializable;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-29
 */
public class MeetingForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String location;
	private String description;
	private String creator;
	private String creatorEmail;
	private TimeSlot[] timeSlot;
	private String[] receivers;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorEmail() {
		return creatorEmail;
	}
	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	public TimeSlot[] getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot[] timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String[] getReceivers() {
		return receivers;
	}
	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	
}
