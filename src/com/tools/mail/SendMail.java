package com.tools.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * �ʼ�����
 * @author Administrator
 *
 */
public class SendMail {
	static MyAuthenticator myauth = new MyAuthenticator();
	
    /**
     * ����һ�������ı��ʼ�
     */
	public static void sendTxtMail(){
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "114.255.157.20"); //smtp��������ַ  쫿᣺114.255.157.20  163���䣺smtp.163.com
	   props.put("mail.smtp.auth", true);  //�Ƿ���Ҫ��֤
	   //props.put("mail.smtp.port", "25"); //�˿�
       //props.put("mail.transport.protocol", "smtp"); //Э��	   
	   
	   //���һ������authenticator��sessionʵ��
	   Session session = Session.getInstance(props,myauth);
	   session.setDebug(true);//��debugģʽ�����ӡ����ϸ�ڵ�console
	   //ʵ����һ��MimeMessage������abstract Message ������Ϊsession
	   Message message = new MimeMessage(session); 
	   try{
		   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com", "쫿���ƽ̨")); //���÷�����,ʹ��setXXX���õ��û���ʹ��addXXX���InternetAddress[]
		   message.setSubject("ֻ�Ǽ�򵥵����ı�����Ӵ��"); //���ñ��� 
		   message.setText("ֻ��һ����򵥵����ı�����Ӵ��"); //�����ı����� ��һ�ı�ʹ��setText,Multipart���Ӷ���ʹ��setContent
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com")); //���ý��շ�
		   //message.addRecipients(Message.RecipientType.TO, arg1);//���ö�����շ�
		   //message.setRecipients(Message.RecipientType.TO, arg1);

	       Transport.send(message); //ʹ��Transport��̬���������ʼ�
  
	   }catch(AddressException e){
		   //�˴�����AddressException�쳣  [The exception thrown when a wrongly formatted address is encountered.] 
	   }catch(MessagingException e){
		   //�˴�����MessagingException�쳣 [The base class for all exceptions thrown by the Messaging classes ]
	   }catch(Exception e){
		   
	   }  
	} 
	
	/**
	 * �������������ı��ļ�
	 */
	public static void sendTxtMail2(){
	   Properties props = new Properties();
	   Session session = Session.getInstance(props,null);
	   session.setDebug(true);//��debugģʽ�����ӡ����ϸ�ڵ�console
	   Message message = new MimeMessage(session); //ʵ����һ��MimeMessage������abstract Message ������Ϊsession
	   try
	   {
	   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com")); //���÷�����,ʹ��setXXX���õ��û���ʹ��addXXX���InternetAddress[]

	   message.setText("ֻ��һ����򵥵����ı�����Ӵ��"); //�����ı����� ��һ�ı�ʹ��setText,Multipart���Ӷ���ʹ��setContent

	   message.setSubject("ֻ�Ǽ�򵥵����ı�����Ӵ��"); //���ñ��� 

	   message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com")); //���ý��շ�

       /**
        * ʹ�þ�̬����ÿ�η�����Ҫ����һ����smtp�����������ӣ�������ֶ���������״̬ ��ͨ��session���tansport�����ӵ�mailserver����session�Ϳ���ʹ��Session.getDefaultInstance(props,null);���
        */
       Transport transport = session.getTransport("smtp");
	   transport.connect("114.255.157.20","jukuadsupport", "cnmmdcdts");
	   transport.sendMessage(message, message.getAllRecipients());
	   transport.close();
	   }catch(AddressException e)
	   {
		   //�˴�����AddressException�쳣  [The exception thrown when a wrongly formatted address is encountered.] 

	   }catch(MessagingException e)
	   {
		   //�˴�����MessagingException�쳣 [The base class for all exceptions thrown by the Messaging classes ]
	   }
	   
	}
	
	/**
	 * ����THML�ʼ�
	 */
	public static void sendTxtMail3(){
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "114.255.157.20"); //smtp��������ַ  쫿᣺114.255.157.20  163���䣺smtp.163.com
	   props.put("mail.smtp.auth", true);  //�Ƿ���Ҫ��֤
	   //props.put("mail.smtp.port", "25"); //�˿�
       //props.put("mail.transport.protocol", "smtp"); //Э��	   
	   
	   //���һ������authenticator��sessionʵ��
	   Session session = Session.getInstance(props,myauth);
	   session.setDebug(true);//��debugģʽ�����ӡ����ϸ�ڵ�console
	   //ʵ����һ��MimeMessage������abstract Message ������Ϊsession
	   Message message = new MimeMessage(session); 
	   try{
		   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com", "쫿�ƽ̨")); //���÷�����,ʹ��setXXX���õ��û���ʹ��addXXX���InternetAddress[]
		   message.setSubject("�Ի���ף��"); //���ñ��� 
		   message.setContent("<b><font color=\"green\">ƾ���ʼ��ɵ�����Է����2¥��ȡ����һ��!</font></b>","text/html;charset=utf8");//�����ı����� ��һ�ı�ʹ��setText,Multipart���Ӷ���ʹ��setContent
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress("721680641@qq.com")); //���ý��շ�
		   //message.addRecipients(Message.RecipientType.TO, arg1);//���ö�����շ�
		   //message.setRecipients(Message.RecipientType.TO, arg1);

	       Transport.send(message); //ʹ��Transport��̬���������ʼ�
  
	   }catch(AddressException e){
		   //�˴�����AddressException�쳣  [The exception thrown when a wrongly formatted address is encountered.] 
	   }catch(MessagingException e){
		   //�˴�����MessagingException�쳣 [The base class for all exceptions thrown by the Messaging classes ]
	   }catch(Exception e){
		   
	   }  
	} 
	
	//���ʹ��и������ʼ������ʼ���ÿ�����ֳ�ʼ��һ��bodypart��
	//�ʼ����ɶ���������,ÿ�����ֳ�Ϊһ���ʼ��岿��,��һ�� BodyPart �����,
	//���� MIME ���� �ʼ��������� MimeBodyPart �����.��Щ�ʼ�������ڳ�Ϊ Multipart ��������
	public static void sendMailWithAttachment(){
		Properties props = new Properties();
		Session session = Session .getDefaultInstance(props);
		Message message = new MimeMessage(session);
		try{
		message.setSubject("����Ǵ��и����ı���");
		message.setFrom(new InternetAddress("jukuadsupport@jukuad.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com"));
		Multipart multipart = new MimeMultipart();
		//ʵ����һ��bodypart���ڷ�װ����
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent("<font color='red'>����Ǵ��и�����HTML����</font>","text/html;charset=utf8");
		//���bodypart��multipart
		multipart.addBodyPart(bodyPart);
		//ÿһ������ʵ����һ��bodypart����ÿ������Ҳ��Ҫʵ����һ��bodypart
		bodyPart = new MimeBodyPart();
		//ʵ����DataSource(����jaf)������Ϊ�ļ��ĵ�ַ
		File file = new File("D:/logs/alert.log");
		DataSource dataSource = new FileDataSource(file.getAbsolutePath());
		//ʹ��datasourceʵ����datahandler
		DataHandler dataHandler = new DataHandler(dataSource);
		bodyPart.setDataHandler(dataHandler);
		//���ø������⣬ʹ��MimeUtility��������ת�룬������յ���������
		bodyPart.setFileName(javax.mail.internet.MimeUtility.encodeText(file.getName()));
		multipart.addBodyPart(bodyPart);
		message.setContent(multipart);
		Transport transport = session.getTransport("smtp");
		transport.connect("114.255.157.20","jukuadsupport", "cnmmdcdts");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		}catch(MessagingException  e)
		{}catch(UnsupportedEncodingException e){}
		
	}


	public static void main(String[] args) {
		sendTxtMail();
		//sendTxtMail2();
		//sendTxtMail3();
		//sendMailWithAttachment();
	}
}
