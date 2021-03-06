/**
 * 
 */
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
public class TimeSlot implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String timeSlot;
	private Integer seqence;
	private Integer count;

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
