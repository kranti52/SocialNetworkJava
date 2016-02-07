package com.myapp.Mailer;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import com.myapp.crypto.EncryptDecrypt;



public class VerifyMail {
	private String username="Your Email ID";
	private String password="Your Email Account Password";
	private String recipient;
	private EncryptDecrypt encoder;
	/*public static void main(String args[]) {

	 }*/
	public boolean sendForgotMail(String To) {
		this.recipient=To;
		System.out.println(recipient);
		String subject="Social app Verification Mail";
		String body;
		String encrypt_mail=null;
		/*body.setContent(body,"text/html;charset=utf-8");*/
		this.encoder=new EncryptDecrypt();
		try {
			encrypt_mail=this.encoder.encrypt(this.recipient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String encrypt_mail=this.encoder.encrypt(this.recipient);
		body="<div style='color:red'><h1 >Click on link to Reset your Password</h1></div><br><div><a href='http://localhost:8080/Socialweb/reset?email="+encrypt_mail+"'>Reset Password</a></div>";
		
		Properties props = System.getProperties();
		
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", this.username);
		props.put("mail.smtp.password", this.password);
		// props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.socketFactory.port", "javax.net.ssl.SSLSocketFactory");
		/*Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kraasiwal@gmail.com","hatedoublefacebitch");
            }
        };*/
		Session session = Session.getInstance(props);//,auth);

		try{
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));//new InternetAddress(recipient));
			message.setContent(body, "text/html; charset=utf-8");
			//message.setText("Please go");
			message.setSentDate(new Date());
			//Transport.send(message);
			Transport transport = session.getTransport("smtps");
	            transport.connect(host,this.username,this.password);
	            transport.sendMessage(message,message.getAllRecipients());
	            transport.close();
		}
		catch (AddressException ae) {
			System.out.println(ae);
			return false;

		}catch(MessagingException me){
			me.printStackTrace();
			System.out.println("not working");
			return false;
		}

		return true;
	}
	public boolean sendMail(String To) {
		this.recipient=To;
		System.out.println(recipient);
		String subject="Social app Verification Mail";
		String body;
		String encrypt_mail=null;
		/*body.setContent(body,"text/html;charset=utf-8");*/
		this.encoder=new EncryptDecrypt();
		try {
			encrypt_mail=this.encoder.encrypt(this.recipient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String encrypt_mail=this.encoder.encrypt(this.recipient);
		body="<div style='color:red'><h1 >Click on link to verify your mail</h1></div><br><div><a href='http://localhost:8080/Socialweb/verify?email="+encrypt_mail+"'>Verify Email</a></div>";
		
		Properties props = System.getProperties();
		
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", this.username);
		props.put("mail.smtp.password", this.password);
		// props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.socketFactory.port", "javax.net.ssl.SSLSocketFactory");
		/*Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kraasiwal@gmail.com","hatedoublefacebitch");
            }
        };*/
		Session session = Session.getInstance(props);//,auth);

		try{
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));//new InternetAddress(recipient));
			message.setContent(body, "text/html; charset=utf-8");
			//message.setText("Please go");
			message.setSentDate(new Date());
			//Transport.send(message);
			Transport transport = session.getTransport("smtps");
	            transport.connect(host,this.username,this.password);
	            transport.sendMessage(message,message.getAllRecipients());
	            transport.close();
		}
		catch (AddressException ae) {
			System.out.println(ae);
			return false;

		}catch(MessagingException me){
			me.printStackTrace();
			System.out.println("not working");
			return false;
		}

		return true;


	}
}
