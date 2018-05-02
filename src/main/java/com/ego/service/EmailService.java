/*******************************************************************************
 * CONFIDENTIAL
 *******************************************************************************/
package com.ego.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * @author EricAr
 *
 */
@Service
public class EmailService {
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail() throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

				Context context = new Context();
				context.setVariable("sender", "Eric");
				String html = templateEngine.process("email", context);
				
				helper.setTo("ericar@stlghana.com");
				helper.setText(html, true);
				helper.setSubject("test");
				helper.addInline("logo.png", new ClassPathResource("/static/images/avatar-1.jpg"));
			emailSender.send(message);
	}
	
	 public void sendSimpleMessage(
		      String to, String subject, String text) {
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
		    }
}
