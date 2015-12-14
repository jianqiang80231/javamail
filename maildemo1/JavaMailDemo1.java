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
 * Java发送邮件一
 * 功能：发送文本内容
 * @author Administrator
 *
 */
public class JavaMailDemo1 {

	/**
	 * 1.创建Session对象
	 */
	public static Session createSession(String host,final String username,final String password){
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", host);	//指定主机名
		prop.setProperty("mail.smtp.auth", "true");	//使用安全验证
		prop.setProperty("mail.smtp.starttls.enable", "true");	//使用 STARTTLS安全连接
		
		//创建验证器
		Authenticator auth = new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);	//此处要求username和password是final类型
			}
		};
		
		return Session.getInstance(prop,auth);
	}
	
	/**
	 * 2.编写邮件
	 * @throws MessagingException 
	 */
	public static MimeMessage writeEmail(Session session,String from,String to,String subject,String content) throws MessagingException{
		//发件人地址
		InternetAddress fromAddress = new InternetAddress(from);
		//收件人地址
		InternetAddress toAddress = new InternetAddress(to);
		//创建MimeMessage对象
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(fromAddress);
		//设置收件人
		message.addRecipient(RecipientType.TO, toAddress);
		//设置主题
		message.setSubject(subject);
		//设置正文内容
		message.setContent(content,"text/html;charset=utf-8");
		
		return message;
	}
	
	/**
	 * 3.发送邮件
	 * @throws MessagingException 
	 */
	public static void sendEmail(MimeMessage message) throws MessagingException{
		Transport.send(message);
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = JavaMailDemo1.createSession("smtp.qq.com","1816367691@qq.com", "邮箱密码");
		MimeMessage message;
		try {
			message = JavaMailDemo1.writeEmail(session, "1816367691@qq.com", "734777497@qq.com", "Java发送邮件一", "你好，这是邮件正文！");
			JavaMailDemo1.sendEmail(message);
			System.out.println("邮件发送成功！");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败！");
		}
		
	}
	
}
