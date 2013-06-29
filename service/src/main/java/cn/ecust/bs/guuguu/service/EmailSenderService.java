package cn.ecust.bs.guuguu.service;

import java.util.Map;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
public interface EmailSenderService {

	public void sendEmail(String[] toEmailAddresses, String subject,Map<String,Object> model
		,String[] files );

}
