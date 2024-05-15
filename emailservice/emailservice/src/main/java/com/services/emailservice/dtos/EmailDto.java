package com.services.emailservice.dtos;



import com.services.emailservice.entities.Email;


public record EmailDto(String mailFrom,String mailTo,String mailSubject,String mailText) {

	public EmailDto(Email email) {
		this(email.getMailFrom(), email.getMailTo(), email.getMailSubject(),email.getMailText());
	}
}
