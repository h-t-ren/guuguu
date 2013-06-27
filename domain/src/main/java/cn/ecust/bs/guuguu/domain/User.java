/**
 * 
 */
package cn.ecust.bs.guuguu.domain;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-25
 */
@NodeEntity
public class User extends Node {
	private static final long serialVersionUID = 1L;
	@Indexed(indexName=FieldIndex.LOGIN, unique=true) private String login;
	private String password;
	private Role[] roles;
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	 public Role[] getRoles() {
	        return roles;
	    }
	public void setRoles(Role[] roles) {
	this.roles = roles;
	}
}
