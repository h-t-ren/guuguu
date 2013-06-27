package cn.ecust.bs.guuguu.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public class UserInMeeting extends Relation {

	private static final long serialVersionUID = 1L;
	@StartNode private User user;
	@EndNode private Meeting meeting;
	

	private String ip;
	private MeetingRole roleInMeeting;
	private ClientType clientType;
	private VoteSatus voteSatus;
	private String comment;
	
	private String[] poll;

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

	public MeetingRole getRoleInMeeting() {
		return roleInMeeting;
	}

	public void setRoleInMeeting(MeetingRole roleInMeeting) {
		this.roleInMeeting = roleInMeeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public VoteSatus getVoteSatus() {
		return voteSatus;
	}

	public void setVoteSatus(VoteSatus voteSatus) {
		this.voteSatus = voteSatus;
	}

	/**
	 * @return the poll
	 */
	public String[] getPoll() {
		return poll;
	}

	/**
	 * @param poll the poll to set
	 */
	public void setPoll(String[] poll) {
		this.poll = poll;
	}

}
