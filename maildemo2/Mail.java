package javamail;

import java.util.ArrayList;

import java.util.List;

/**
 * �ʼ���
 * ��Ҫ���ã��û��������롢�ռ��ˡ����ͣ���ѡ�������ͣ���ѡ�������⡢���ݺ͸�������ѡ��
 * ˵����
 * 		�������⣺	setSubject();
 * 		�������ģ�	setContent();
 * 		���÷����ˣ�	setFrom();
 * 		����ռ��ˣ�	addToAddress();
 * 		��Ӹ�����	addAttch();
 * ���������ࣺnew AttachBean(new File(""),"filename");
 * @author Administrator
 *
 */
public class Mail {

	private String from;	//������
	private StringBuilder toAddress = new StringBuilder();	//�ռ���
	private StringBuilder ccAddress = new StringBuilder();	//����
	private StringBuilder bccAddress = new StringBuilder();	//����
	private String subject;	//����
	private String content;	//����
	//�����б�
	private List<AttachBean> attachList = new ArrayList<AttachBean>();
	
	public Mail(){
		
	}
	public Mail(String from,String to){
		this(from,to,null,null);
	}
	public Mail(String from,String to,String subject,String content){
		this.from = from;
		this.toAddress.append(to);
		this.subject = subject;
		this.content = content;
	}
	/**
	 * ��ȡ������
	 * @return from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * ���÷�����
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * ��ȡ�ռ���
	 * @return
	 */
	public String getToAddress() {
		return toAddress.toString();
	}
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * ��������
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getContent() {
		return content;
	}
	/**
	 * ������������
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getCcAddress() {
		return ccAddress.toString();
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getBccAddress() {
		return bccAddress.toString();
	}
	/**
	 * ����ռ��ˣ������Ƕ����
	 * @return
	 */
	public void addToAddress(String to){
		if(this.toAddress.length() > 0){
			this.toAddress.append(",");
		}
		this.toAddress.append(to);
	}
	
	/**
	 * ��ӳ����ˣ������Ƕ��������
	 */
	public void addCcAddress(String cc){
		if(this.ccAddress.length() > 0){
			this.ccAddress.append(",");
		}
		this.ccAddress.append(cc);
	}
	/**
	 * ��Ӱ����ˣ������Ƕ��������
	 */
	public void addBccAddress(String bcc){
		if(this.bccAddress.length() > 0){
			this.bccAddress.append(",");
		}
		this.bccAddress.append(bcc);
	}
	
	/**
	 *��Ӹ�����������Ӷ������ 
	 */
	public void addAttach(AttachBean attachBean){
		this.attachList.add(attachBean);
	}
	
	/**
	 * ��ȡ���и���
	 */
	public List<AttachBean> getAttachList() {
		return this.attachList;
	}
	
	
}
