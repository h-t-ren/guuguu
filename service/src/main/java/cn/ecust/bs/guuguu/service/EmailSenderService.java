package cn.ecust.bs.guuguu.service;

/**
 * @author Hongtao Ren email: hongtao.ren@gmail.com created: 2013-6-28
 */
public interface EmailSenderService {

	public void sendEmail(String toEmailAddresses, String fromEmailAddress,
			String subject);

	public void sendEmailWithAttachment(String toEmailAddresses,
			String fromEmailAddress, String subject, String attachmentPath,
			String attachmentName);

}
