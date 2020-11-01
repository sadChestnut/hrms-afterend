package com.example.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void sendSimpleMail(String sendTo, String title, String content) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(title);
        message.setText(content);
        
        mailSender.send(message);
	}

}
