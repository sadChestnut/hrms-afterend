package com.example.demo.email;

public interface EmailService {

	//发送邮件
	void sendSimpleMail(String sendTo, String title, String content);
}
