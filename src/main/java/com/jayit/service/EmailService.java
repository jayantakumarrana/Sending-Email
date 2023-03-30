package com.jayit.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String toEmail,String body,String subject,String attachment) throws MessagingException {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("ashutoshn683@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);


        //to attach a file 
        FileSystemResource fileSystemResource=  new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        javaMailSender.send(mimeMessage);
        System.out.printf("Mail sent successfully..");

	}

}
