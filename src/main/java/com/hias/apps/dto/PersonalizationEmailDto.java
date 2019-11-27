package com.hias.apps.dto;

public class PersonalizationEmailDto {

	private EmailDetailDto to;
	
	private ContentEmailDto content;
	
	public EmailDetailDto getTo() {
		return to;
	}

	public void setTo(EmailDetailDto to) {
		this.to = to;
	}

	public ContentEmailDto getContent() {
		return content;
	}

	public void setContent(ContentEmailDto content) {
		this.content = content;
	}


	
}
