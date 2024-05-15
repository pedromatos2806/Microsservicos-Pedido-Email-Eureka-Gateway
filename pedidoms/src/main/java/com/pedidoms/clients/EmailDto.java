package com.pedidoms.clients;

import org.springframework.http.ResponseEntity;



public record EmailDto(String mailFrom, String mailTo, String mailSubject, String mailText) implements EmailClient  {

	@Override
	public ResponseEntity<EmailDto> sendEmail(EmailDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
