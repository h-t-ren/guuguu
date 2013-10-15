package cn.ecust.bs.guuguu.rs.client;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import cn.ecust.bs.guuguu.ws.domain.GuuGuuUser;


public class UserInfoClient {
	public static void main(String[] args) {
		Client userClient = ClientBuilder.newClient();
		WebTarget target = userClient.target("http://219.220.221.183:8080/guuguu-rest/rest/user/{email}/info");
		Response response = target
				 				.resolveTemplate("email", "53564232111@qq.com")
				 				.request(MediaType.APPLICATION_JSON)
				 				.get();
		if(response.getStatus() == Status.OK.getStatusCode()){
			GuuGuuUser user = (GuuGuuUser) response.readEntity(GuuGuuUser.class);
			System.out.println("user name: " + user.getUserName()+", email: " + user.getEmail()+", mobile: " + user.getMobile());
		}else{
			System.out.println(response.getStatus() + " " + response.getStatusInfo());
		}
	}
}
