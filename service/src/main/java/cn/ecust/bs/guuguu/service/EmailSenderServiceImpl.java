package cn.ecust.bs.guuguu.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
@Service("emailSenderService") 
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Value("#{email_prop['template']}")
	private String template;

	@Override
	public void sendEmail(String toEmailAddresses, String fromEmailAddress,
			String subject) {
		sendEmail(toEmailAddresses,fromEmailAddress,subject,null,null);
	}

	@Override
	public void sendEmailWithAttachment(String toEmailAddresses,
			String fromEmailAddress, String subject, String attachmentPath,
			String attachmentName) {
		sendEmail(toEmailAddresses,fromEmailAddress,subject,attachmentPath,attachmentName);
	}

	private void sendEmail(final String toEmailAddresses, final String fromEmailAddress,
			final String subject, final String attachmentPath, final String attachmentName) {
		
		System.out.println("template " + template);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true);
				message.setTo(toEmailAddresses);
				message.setFrom(new InternetAddress(fromEmailAddress));
				message.setSubject(subject);
				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, template, "UTF-8", null);
				message.setText(body, true);
				if (!StringUtils.isBlank(attachmentPath)) {
					FileSystemResource file = new FileSystemResource(
							attachmentPath);
					message.addAttachment(attachmentName, file);
				}
			}
		};
		this.mailSender.send(preparator);
	}

}
