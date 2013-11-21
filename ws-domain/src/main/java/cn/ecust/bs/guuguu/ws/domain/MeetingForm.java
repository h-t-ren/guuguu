package cn.ecust.bs.guuguu.ws.domain;

import java.io.Serializable;
import java.util.Date;

//import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-29
 */
//@XmlRootElement
public class MeetingForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String location;
	private String description;
	private String creator;
	private String creatorEmail;
	private Date created;
	private TimeSlot[] timeSlot;
	private String[] receivers;
	
	private String ip;
	private ClientType clientType;
	private String comment;
	
	//only used by MVC
	private String eventsJson;
	private String invitations; 
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
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEventsJson() {
		return eventsJson;
	}
	public void setEventsJson(String eventsJson) {
		this.eventsJson = eventsJson;
	}
	public String getInvitations() {
		return invitations;
	}
	public void setInvitations(String invitations) {
		this.invitations = invitations;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	
}
