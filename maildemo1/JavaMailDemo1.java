package bb;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Java�����ʼ�һ
 * ���ܣ������ı�����
 * @author Administrator
 *
 */
public class JavaMailDemo1 {

	/**
	 * 1.����Session����
	 */
	public static Session createSession(String host,final String username,final String password){
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", host);	//ָ��������
		prop.setProperty("mail.smtp.auth", "true");	//ʹ�ð�ȫ��֤
		prop.setProperty("mail.smtp.starttls.enable", "true");	//ʹ�� STARTTLS��ȫ����
		
		//������֤��
		Authenticator auth = new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);	//�˴�Ҫ��username��password��final����
			}
		};
		
		return Session.getInstance(prop,auth);
	}
	
	/**
	 * 2.��д�ʼ�
	 * @throws MessagingException 
	 */
	public static MimeMessage writeEmail(Session session,String from,String to,String subject,String content) throws MessagingException{
		//�����˵�ַ
		InternetAddress fromAddress = new InternetAddress(from);
		//�ռ��˵�ַ
		InternetAddress toAddress = new InternetAddress(to);
		//����MimeMessage����
		MimeMessage message = new MimeMessage(session);
		//���÷�����
		message.setFrom(fromAddress);
		//�����ռ���
		message.addRecipient(RecipientType.TO, toAddress);
		//��������
		message.setSubject(subject);
		//������������
		message.setContent(content,"text/html;charset=utf-8");
		
		return message;
	}
	
	/**
	 * 3.�����ʼ�
	 * @throws MessagingException 
	 */
	public static void sendEmail(MimeMessage message) throws MessagingException{
		Transport.send(message);
	}
	
	/**
	 * ����
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = JavaMailDemo1.createSession("smtp.qq.com","1816367691@qq.com", "��������");
		MimeMessage message;
		try {
			message = JavaMailDemo1.writeEmail(session, "1816367691@qq.com", "734777497@qq.com", "Java�����ʼ�һ", "��ã������ʼ����ģ�");
			JavaMailDemo1.sendEmail(message);
			System.out.println("�ʼ����ͳɹ���");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("�ʼ�����ʧ�ܣ�");
		}
		
	}
	
}
