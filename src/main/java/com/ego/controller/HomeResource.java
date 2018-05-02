/*******************************************************************************
 * CONFIDENTIAL
 *******************************************************************************/
package com.ego.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ego.service.EmailService;

import lombok.RequiredArgsConstructor;


/**
 * @author EricAr
 *
 */
@RestController
@RequiredArgsConstructor
public class HomeResource {
	
	private final EmailService emailService;
	
	@GetMapping("sendEmail")
	public void sendEmail() throws MessagingException, IOException {
		emailService.sendEmail();
	}
	
	@GetMapping("sendSimpleMessage")
	public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, IOException {
		to = "ardeneric77@gmail.com";
		subject = "test";
		text = "Kindly ignore test mail";
		emailService.sendSimpleMessage(to, subject, text);
	}

}
