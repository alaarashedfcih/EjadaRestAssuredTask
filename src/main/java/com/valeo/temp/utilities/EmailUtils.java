package com.valeo.temp.utilities;

import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class EmailUtils
{

	public void sendTestReportEmail(String user, String pass, String serverHost, String serverPort, String senderEmail,
			String recepientEmail, String recepientCCEmail, String emailSubject, String emailBody, String testReportFileName,
			String attachmentFilePath)
	{
		// Recipient's email ID needs to be mentioned.
		String to = recepientEmail;

		// Recipient's CC email ID needs to be mentioned.
		String cc = recepientCCEmail;

		// Sender's email ID needs to be mentioned
		String from = senderEmail;

		final String username = user;// change accordingly
		final String password = pass;// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = serverHost;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", serverPort);

		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		int count = 0;
		int maxTries = 3;
		while (count < maxTries)
		{
			try
			{
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));

				// Set Subject: header field
				message.setSubject(emailSubject);

				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Now set the actual message
				messageBodyPart.setText(emailBody);

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Part two is attachment
				messageBodyPart = new MimeBodyPart();
				String filename = testReportFileName;
				DataSource source = new FileDataSource(attachmentFilePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);

				log.info("Sent message successfully....");
				count = maxTries;

			}
			catch (MessagingException e)
			{
				if (++count == maxTries)
				{
					throw new RuntimeException(e);
				}
				else
				{
					log.info("Send Email Retry: " + count);
				}
			}
		}
	}

	public Properties readFromPropertiesFile()
	{
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream("src/main/resources/" + Constants.MAIL_CONFIG_FLAG + ".properties"))
		{
			// Load Properties File
			prop.load(input);

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return prop;
	}
}
