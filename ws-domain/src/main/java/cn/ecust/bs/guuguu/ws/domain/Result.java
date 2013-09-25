package cn.ecust.bs.guuguu.ws.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-9-25
 */
@XmlRootElement
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

	
}
