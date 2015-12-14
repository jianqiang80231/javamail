package javamail;

import java.io.File;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;

public class MailUtilsTest {

	public void sendMail() throws MessagingException,IOException{
		
		//创建一个Mail类的对象，参数指定发件人和收件人
		Mail mail = new Mail("1816367691@qq.com","734777497@qq.com");
		
		//添加收件人
		mail.addToAddress("123456@qq.com");
		
		//添加附件
		mail.addAttach(new AttachBean(new File("f:\\QQ聊天系统.rar"),"QQ聊天系统.rar"));
		//设置主题
		mail.setSubject("这是QQ聊天系统！");
		
		//设置正文
		mail.setContent("这里是正文内容!");
		
		 //创建Session对象所需三个参数：主机名、用户名、密码
		Session session = MailUtils.createSession("smtp.qq.com", "1816367691@qq.com", "邮箱密码");
		
		//发送邮件，需要Session和Mail对象
		MailUtils.send(session, mail);
		
	}
	
	public static void main(String[] args) {
		try {
			new MailUtilsTest().sendMail();
			System.out.println("邮件发送成功！");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败！");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败！");
		}
	}
}
