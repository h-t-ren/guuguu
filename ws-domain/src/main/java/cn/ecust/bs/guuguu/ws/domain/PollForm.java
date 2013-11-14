package cn.ecust.bs.guuguu.ws.domain;

import java.io.Serializable;

//import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-29
 */
//@XmlRootElement
public class PollForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idMeeting;
	private Long[] idSlots;
	private String email;
	private ClientType clientType;
    private String ip;
	private String comment;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(Long idMeeting) {
		this.idMeeting = idMeeting;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Long[] getIdSlots() {
		return idSlots;
	}

	public void setIdSlots(Long[] idSlots) {
		this.idSlots = idSlots;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
