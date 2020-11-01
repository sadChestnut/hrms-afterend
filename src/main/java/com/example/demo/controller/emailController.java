package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.email.EmailService;


@Controller
@RequestMapping(value = {"/api"})
public class emailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = {"/sendemail"})
	@ResponseBody
	public String sendSimpleEmail(
		@RequestBody Map<String, Object> params
			)
	{
		String sendTo = params.get("sendTo").toString();
		emailService.sendSimpleMail(sendTo, "更新简历提醒", "您好，您已经三个月未更新简历了，请及时更新简历~");
		return "success";
	}

}
