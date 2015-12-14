package javamail;

import java.io.File;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;

public class MailUtilsTest {

	public void sendMail() throws MessagingException,IOException{
		
		//����һ��Mail��Ķ��󣬲���ָ�������˺��ռ���
		Mail mail = new Mail("1816367691@qq.com","734777497@qq.com");
		
		//����ռ���
		mail.addToAddress("123456@qq.com");
		
		//��Ӹ���
		mail.addAttach(new AttachBean(new File("f:\\QQ����ϵͳ.rar"),"QQ����ϵͳ.rar"));
		//��������
		mail.setSubject("����QQ����ϵͳ��");
		
		//��������
		mail.setContent("��������������!");
		
		 //����Session���������������������������û���������
		Session session = MailUtils.createSession("smtp.qq.com", "1816367691@qq.com", "��������");
		
		//�����ʼ�����ҪSession��Mail����
		MailUtils.send(session, mail);
		
	}
	
	public static void main(String[] args) {
		try {
			new MailUtilsTest().sendMail();
			System.out.println("�ʼ����ͳɹ���");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("�ʼ�����ʧ�ܣ�");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�ʼ�����ʧ�ܣ�");
		}
	}
}
