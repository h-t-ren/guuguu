/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ecust.bs.guuguu.service.EmailSenderService;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/service-context.xml" })
public class EmailSenderTest {
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private VelocityEngine velocityEngine;

	@Test
	public void testSendEmail() throws Exception {
		String[] to = new String[] { "conceit_conceit@163.com" };
		String subject = "测试邮件";
		String[] files = new String[] { "c:/pass/pass.txt", "c:/22.log" };
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", "任宏涛");
		model.put("emailAddress", "conceit_conceit@163.com");
		emailSenderService.sendEmail(to, subject, model, files);
		
	}

}
