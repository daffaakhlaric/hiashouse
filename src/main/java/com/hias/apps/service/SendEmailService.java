package com.hias.apps.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class SendEmailService {

	@Autowired
	private ApplicationContext context;

	public String sendEmail(String fromEmail, String subject, String toEmail, String strContent, String emailCategory, Map<String, String> mapParam) throws IOException {
		Email from = new Email(fromEmail);
		String templateId = null;
		Personalization person = new Personalization();
		if("REGISTRATION".equals(emailCategory)) {
			for (Map.Entry<String, String> entry : mapParam.entrySet())
			{
				person.addDynamicTemplateData(entry.getKey(), entry.getValue());
			}
			person.addTo(new Email(toEmail));
			templateId = context.getEnvironment().getProperty("sendgrid.register.id");
		}else if("FORGOT".equals(emailCategory)) {
			for (Map.Entry<String, String> entry : mapParam.entrySet())
			{
				person.addDynamicTemplateData(entry.getKey(), entry.getValue());
			}
			person.addTo(new Email(toEmail));
			templateId = context.getEnvironment().getProperty("sendgrid.forgot.id");
		}
		Content content = new Content("text/html", strContent);
		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setSubject(subject);
		mail.addContent(content);
		mail.addPersonalization(person);
		mail.setTemplateId(templateId);

		SendGrid sg = new SendGrid(context.getEnvironment().getProperty("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			return String.valueOf(response.getStatusCode());
		} catch (IOException ex) {
			return ex.getMessage().toString();
		}
	}
}
