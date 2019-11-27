package com.hias.apps.dto;

public class EmailDto {

	private PersonalizationEmailDto personal;
	
	private ContentEmailDto content;
	
	private EmailDetailDto from;
	
	private EmailDetailDto replay_to;
	
	public PersonalizationEmailDto getPersonal() {
		return personal;
	}

	public void setPersonal(PersonalizationEmailDto personal) {
		this.personal = personal;
	}

	public ContentEmailDto getContent() {
		return content;
	}

	public void setContent(ContentEmailDto content) {
		this.content = content;
	}

	public EmailDetailDto getFrom() {
		return from;
	}

	public void setFrom(EmailDetailDto from) {
		this.from = from;
	}

	public EmailDetailDto getReplay_to() {
		return replay_to;
	}

	public void setReplay_to(EmailDetailDto replay_to) {
		this.replay_to = replay_to;
	}
	
	
}
