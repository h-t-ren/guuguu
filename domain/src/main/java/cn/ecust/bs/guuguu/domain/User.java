package cn.ecust.bs.guuguu.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.Indexed;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
public class User extends Node {
	private static final long serialVersionUID = 1L;
	@Indexed(indexName=FieldIndex.LOGIN, unique=true) private String login;
	private String password;
	private String name;
	@Indexed private String email;
	private String mobile;
	private Date created;
	private Role[] roles;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    public Role[] getRoles() {
	        return roles;
	    }
	public void setRoles(Role[] roles) {
	this.roles = roles;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
