package com.example.demo.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

	 @Value("${spring.mail.username}")
	 private String emailFrom;
	 
	 public String getEmailFrom() {
		 return emailFrom;
	 }
	 
	 public void setEmailFrom(String emailFrom) {
		 this.emailFrom = emailFrom;
	 }
} 
