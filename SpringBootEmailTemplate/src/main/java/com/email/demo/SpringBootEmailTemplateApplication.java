package com.email.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.email.model.Mail;
import com.email.service.EmailSenderService;

@SpringBootApplication
@ComponentScan("com.email.service")
public class SpringBootEmailTemplateApplication implements ApplicationRunner{
	
	@Autowired
    private EmailSenderService emailService;
	
	private static Logger log = LoggerFactory.getLogger(SpringBootEmailTemplateApplication.class); 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailTemplateApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
        Mail mail = new Mail();
        mail.setFrom("abdulalsowdhs@gmail.com");
        mail.setMailTo("soudh2000@gmail.com");
        
        sendFakeNewsLetter(mail);
        sendInlinedCssEmail(mail);
	}
	
	private void sendFakeNewsLetter(Mail mail) throws MessagingException, IOException {
		
		log.info("START... Sending Subscription Letter");
		
        mail.setSubject("Email with Spring boot and thymeleaf template!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Kirithigan");
        model.put("location", "Chennai");
        model.put("sign", "Kirithigan@gmail.com");
        model.put("type", "SUBSCRIPTION");
        mail.setProps(model);

        emailService.sendEmail(mail);
        log.info("END... Sending Subscription Letter");
	}
	
    private void sendInlinedCssEmail(Mail mail) throws MessagingException, IOException {
		
    	log.info("START...Sending Reservation Email");

        mail.setSubject("Email with Inlined CSS Responsive Thymeleaf Template!");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Vitthal!");
        model.put("address", "Company Inc, 3 Abbey Road, San Francisco CA 94102");
        model.put("sign", "Vitthal@gmail.com");
        model.put("type", "TRANSACTIONAL");
        mail.setProps(model);

        emailService.sendEmail(mail);
        log.info("END... Sending Reservation Email");
	}
}
