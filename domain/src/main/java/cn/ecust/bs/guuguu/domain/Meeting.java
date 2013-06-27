package cn.ecust.bs.guuguu.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-27
 */
public class Meeting extends Node {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String location;
	private String description;
	
	private Date created;
	private Date updated;
	private Date closed;

	private DynamicProperties slots;
	
	private String url;
	private String adminUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getClosed() {
		return closed;
	}
	public void setClosed(Date closed) {
		this.closed = closed;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the slots
	 */
	public DynamicProperties getSlots() {
		return slots;
	}
	/**
	 * @param slots the slots to set
	 */
	public void setSlots(DynamicProperties slots) {
		this.slots = slots;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the adminUrl
	 */
	public String getAdminUrl() {
		return adminUrl;
	}
	/**
	 * @param adminUrl the adminUrl to set
	 */
	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}

}
