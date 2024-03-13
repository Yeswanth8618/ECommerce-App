package org.jsp.mailsemdingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class Mailsendercontroller {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value(value="yesh")
	private String token;
	@PostMapping("/send-mail")
	public String sendemail(@RequestParam String email,HttpServletRequest request) {
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "/verify")+"?token="+token;
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		try {
			helper.setSubject("Account Verification");
			helper.setTo(email);
			helper.setText(url);
			javaMailSender.send(message);
			return "mail sended successfully" ;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "can't send mail" ;
		}
	}
	@GetMapping("/verify")
	public String verify(@RequestParam String token) {
		if(this.token.equals(token))
			return "verification successfull";
		else
			return "cannot verify";
	}
}
