package com.services.emailservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.emailservice.dtos.EmailDto;
import com.services.emailservice.entities.Email;
import com.services.emailservice.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	EmailService emailService;
	
	
	@PostMapping("/send")
	public ResponseEntity<Email> sendEmail(@RequestBody EmailDto data){
		return new ResponseEntity<Email>(emailService.sendEmail(data), HttpStatus.CREATED);
	}
}
