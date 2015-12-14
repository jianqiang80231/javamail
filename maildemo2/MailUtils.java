package javamail;

import java.io.IOException;


import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * ���ã����ʼ�
 * @author Administrator
 *
 */
public class MailUtils {

	public static Session createSession(String host,final String username,final String password){
		Properties prop = new Properties();	
		prop.setProperty("mail.host", host);		//ָ������
		prop.setProperty("mail.smtp.auth", "true");	//ָ����֤Ϊtrue
		prop.setProperty("mail.smtp.starttls.enable", "true");//ʹ�� STARTTLS��ȫ����
		//������֤��
		Authenticator auth = new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);
			}
		};
		
		//��ȡSession����
		return Session.getInstance(prop, auth);
	}
		/**
		 * ����ָ�����ʼ�
		 */
		public static void send(Session session,final Mail mail)throws MessagingException,IOException{
			
			MimeMessage msg = new MimeMessage(session);
			
			//���÷�����
			msg.setFrom(new InternetAddress(mail.getFrom()));	
			
			//�����ռ���
			msg.addRecipients(RecipientType.TO,mail.getToAddress());
			
			//���ó���
			String cc = mail.getCcAddress();
			if(!cc.isEmpty()){
				msg.addRecipients(RecipientType.CC, cc);
			}
			
			//���ð���
			String bcc = mail.getBccAddress();
			if(!bcc.isEmpty()){
				msg.addRecipients(RecipientType.BCC, bcc);
			}
			
			//��������
			msg.setSubject(mail.getSubject());
			
			//��������������
			MimeMultipart parts = new MimeMultipart();
			
			//����һ������
			MimeBodyPart part = new MimeBodyPart();
			
			//�����ʼ��ı�����
			part.setContent(mail.getContent(),"text/html;charset=utf-8");
			
			//�Ѳ�����ӵ���������
			parts.addBodyPart(part);
			
			/*
			 * ��Ӹ���
			 */
			//��ȡ���и���
			List<AttachBean> attachBeanList = mail.getAttachList();
			if(attachBeanList != null){
				for(AttachBean attach : attachBeanList){
					
					//����һ������
					MimeBodyPart attachPart = new MimeBodyPart();
					
					//���ø����ļ�
					attachPart.attachFile(attach.getFile());
					
					//���ø����ļ���
					attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));
					String cid = attach.getCid();
					if(cid != null){
						attachPart.setContentID(cid);
					}
					parts.addBodyPart(attachPart);
				}
			}
			msg.setContent(parts);	//���ʼ���������
			Transport.send(msg);	//�����ʼ�
		}
}
