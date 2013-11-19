package cn.ecust.bs.guuguu.ws.domain;

import java.io.Serializable;

//import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-9-24
 */
//@XmlRootElement
public class GuuGuuUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	private String userName;
	private String email;
	private String mobile;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
}
