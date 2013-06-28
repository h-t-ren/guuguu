/**
 * 
 */
package cn.ecust.bs.guuguu.test.service;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ecust.bs.guuguu.service.EmailSenderService;


/**
 * @author Hongtao Ren
 * email: hongtao.ren@gmail.com
 * created: 2013-6-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service-context.xml"})
public class EmailSenderTest {
	 @Autowired
	    private EmailSenderService emailSenderService;
	    @Autowired
	    private VelocityEngine velocityEngine;

	    @Test
	    public void testSendEmail() throws Exception {
	    	emailSenderService.sendEmail("conceit_conceit@163.com", "53564232@qq.com", "Test");
	    }
/*
	    @Test
	    public void testSendEmailWithAttachment() throws Exception {
	        emailSender.sendEmailWithAttachment("hongtao.ren@gmail.com", "baljit.garcha@cavalr.com", "Test",
	                "D:/git/guuguu/.gitignore", "gitignore");
	    }
	    */
}
