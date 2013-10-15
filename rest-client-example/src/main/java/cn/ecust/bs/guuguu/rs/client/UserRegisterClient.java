package cn.ecust.bs.guuguu.rs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;
import cn.ecust.bs.guuguu.ws.domain.Result;

public class UserRegisterClient {

	public static void main(String[] args) {
		Client userClient = ClientBuilder.newClient();
		WebTarget target = userClient
				.target("http://219.220.221.183:8080/guuguu-rest/rest/user/register");
		GuuGuuUser user = new GuuGuuUser();
		user.setEmail("53564232111@qq.com");
		user.setUserName("hongtao ren");
		user.setMobile("18916488290");
		Result result =target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user,MediaType.APPLICATION_JSON),Result.class);
	    System.out.println("result: " + result.getResult());
	}

}
