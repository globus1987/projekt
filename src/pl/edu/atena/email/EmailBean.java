package pl.edu.atena.email;

import lombok.Data;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Stateless
@Data
public class EmailBean {

	public EmailBean() {
		throw new IllegalStateException("Utility class");
	}

	public void sendGMXText(MessageEmailBean mess) throws MessagingException {
		String sender = "akademiajava@gmx.com";
		String pass = "Akademia123";
		String receiver = "globus1987@gmail.com";

		Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "mail.gmx.net");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", sender);
		properties.put("mail.smtp.password", pass);
		properties.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("mail.smtp.user"),
						properties.getProperty("mail.smtp.password"));
			}
		});

		MimeMessage message = new MimeMessage(mailSession);
		InternetAddress addressTo = new InternetAddress(receiver);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(mess.getText().toString(), "UTF-8", "plain");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		message.setRecipient(Message.RecipientType.TO, addressTo);
		message.setFrom(new InternetAddress(sender));
		message.setSubject(mess.getSubject());
		message.setContent(multipart);
		message.setSender(new InternetAddress("dupa@Dupa.pl"));

		Transport.send(message);
	}
}
