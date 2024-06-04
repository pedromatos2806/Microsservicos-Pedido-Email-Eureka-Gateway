package com.pedidoms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("email-api")
public interface EmailClient {
	
	@PostMapping (value = "/email/send")
	public ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto dto);
	
}