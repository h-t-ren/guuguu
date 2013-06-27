package cn.ecust.bs.guuguu.domain;

import java.util.Date;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-27
 */
public class MeetingTime extends Node {

	private static final long serialVersionUID = 1L;
	
	private Date date;
	private String timeSlot;
	
	private Integer seqence;
	private Integer count;
	
	@RelatedTo(type=RelationType.MeetingHasTimeSlots, direction = Direction.INCOMING)@Fetch
	private Meeting meeting;

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Integer getSeqence() {
		return seqence;
	}

	public void setSeqence(Integer seqence) {
		this.seqence = seqence;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}



}
