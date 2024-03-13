 package or.jsp.SpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import or.jsp.SpringBoot.model.Merchant;
import or.jsp.SpringBoot.model.User;
import static or.jsp.SpringBoot.util.ApplicationConstants.VERIFY_USER;
import static or.jsp.SpringBoot.util.ApplicationConstants.VERIFY_MERCHANT;;
@Service
public class ECommerceApplicationMailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String WelcomeMail(Merchant merchant,HttpServletRequest request) {
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String actual_url=url+VERIFY_MERCHANT+merchant.getToken();
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		try {
			helper.setTo(merchant.getEmail());
			helper.setSubject("Activate your Account");
			helper.setText(actual_url);
			javaMailSender.send(message);
			return "Verification success";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Verification failed";
		}
	}
	
	
	public String WelcomeMailuser(User user,HttpServletRequest request) {
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String actual_url=url+VERIFY_USER+user.getToken();
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Activate your Account");
			helper.setText(actual_url);
			javaMailSender.send(message);
			return "Verification success";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Verification failed";
		}
	}
}
