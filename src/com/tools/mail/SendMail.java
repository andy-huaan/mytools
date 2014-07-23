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
 * 邮件发送
 * @author Administrator
 *
 */
public class SendMail {
	static MyAuthenticator myauth = new MyAuthenticator();
	
    /**
     * 方法一：发送文本邮件
     */
	public static void sendTxtMail(){
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "114.255.157.20"); //smtp服务器地址  飓酷：114.255.157.20  163邮箱：smtp.163.com
	   props.put("mail.smtp.auth", true);  //是否需要认证
	   //props.put("mail.smtp.port", "25"); //端口
       //props.put("mail.transport.protocol", "smtp"); //协议	   
	   
	   //获得一个带有authenticator的session实例
	   Session session = Session.getInstance(props,myauth);
	   session.setDebug(true);//打开debug模式，会打印发送细节到console
	   //实例化一个MimeMessage集成自abstract Message 。参数为session
	   Message message = new MimeMessage(session); 
	   try{
		   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com", "飓酷广告平台")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]
		   message.setSubject("只是简简单单的文本标题哟！"); //设置标题 
		   message.setText("只是一个简简单单的文本内容哟！"); //设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com")); //设置接收方
		   //message.addRecipients(Message.RecipientType.TO, arg1);//设置多个接收方
		   //message.setRecipients(Message.RecipientType.TO, arg1);

	       Transport.send(message); //使用Transport静态方法发送邮件
  
	   }catch(AddressException e){
		   //此处处理AddressException异常  [The exception thrown when a wrongly formatted address is encountered.] 
	   }catch(MessagingException e){
		   //此处处理MessagingException异常 [The base class for all exceptions thrown by the Messaging classes ]
	   }catch(Exception e){
		   
	   }  
	} 
	
	/**
	 * 方法二：发送文本文件
	 */
	public static void sendTxtMail2(){
	   Properties props = new Properties();
	   Session session = Session.getInstance(props,null);
	   session.setDebug(true);//打开debug模式，会打印发送细节到console
	   Message message = new MimeMessage(session); //实例化一个MimeMessage集成自abstract Message 。参数为session
	   try
	   {
	   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]

	   message.setText("只是一个简简单单的文本内容哟！"); //设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent

	   message.setSubject("只是简简单单的文本标题哟！"); //设置标题 

	   message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com")); //设置接收方

       /**
        * 使用静态方法每次发送需要建立一个到smtp服务器的链接，你可以手动控制连接状态 ，通过session获得tansport，连接到mailserver，而session就可以使用Session.getDefaultInstance(props,null);获得
        */
       Transport transport = session.getTransport("smtp");
	   transport.connect("114.255.157.20","jukuadsupport", "cnmmdcdts");
	   transport.sendMessage(message, message.getAllRecipients());
	   transport.close();
	   }catch(AddressException e)
	   {
		   //此处处理AddressException异常  [The exception thrown when a wrongly formatted address is encountered.] 

	   }catch(MessagingException e)
	   {
		   //此处处理MessagingException异常 [The base class for all exceptions thrown by the Messaging classes ]
	   }
	   
	}
	
	/**
	 * 发送THML邮件
	 */
	public static void sendTxtMail3(){
	   Properties props = new Properties();
	   props.put("mail.smtp.host", "114.255.157.20"); //smtp服务器地址  飓酷：114.255.157.20  163邮箱：smtp.163.com
	   props.put("mail.smtp.auth", true);  //是否需要认证
	   //props.put("mail.smtp.port", "25"); //端口
       //props.put("mail.transport.protocol", "smtp"); //协议	   
	   
	   //获得一个带有authenticator的session实例
	   Session session = Session.getInstance(props,myauth);
	   session.setDebug(true);//打开debug模式，会打印发送细节到console
	   //实例化一个MimeMessage集成自abstract Message 。参数为session
	   Message message = new MimeMessage(session); 
	   try{
		   message.setFrom(new InternetAddress("jukuadsupport@jukuad.com", "飓酷平台")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]
		   message.setSubject("赵华安祝福"); //设置标题 
		   message.setContent("<b><font color=\"green\">凭此邮件可到富瑞苑大厦2楼换取可乐一罐!</font></b>","text/html;charset=utf8");//设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress("721680641@qq.com")); //设置接收方
		   //message.addRecipients(Message.RecipientType.TO, arg1);//设置多个接收方
		   //message.setRecipients(Message.RecipientType.TO, arg1);

	       Transport.send(message); //使用Transport静态方法发送邮件
  
	   }catch(AddressException e){
		   //此处处理AddressException异常  [The exception thrown when a wrongly formatted address is encountered.] 
	   }catch(MessagingException e){
		   //此处处理MessagingException异常 [The base class for all exceptions thrown by the Messaging classes ]
	   }catch(Exception e){
		   
	   }  
	} 
	
	//发送带有附件的邮件，将邮件的每个部分初始化一个bodypart。
	//邮件是由多个部分组成,每个部分称为一个邮件体部分,是一个 BodyPart 类对象,
	//对于 MIME 类型 邮件来讲就是 MimeBodyPart 类对象.这些邮件体包含在成为 Multipart 的容器中
	public static void sendMailWithAttachment(){
		Properties props = new Properties();
		Session session = Session .getDefaultInstance(props);
		Message message = new MimeMessage(session);
		try{
		message.setSubject("这个是带有附件的标题");
		message.setFrom(new InternetAddress("jukuadsupport@jukuad.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1036661027@qq.com"));
		Multipart multipart = new MimeMultipart();
		//实例化一个bodypart用于封装内容
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent("<font color='red'>这个是带有附件的HTML内容</font>","text/html;charset=utf8");
		//添加bodypart到multipart
		multipart.addBodyPart(bodyPart);
		//每一个部分实例化一个bodypart，故每个附件也需要实例化一个bodypart
		bodyPart = new MimeBodyPart();
		//实例化DataSource(来自jaf)，参数为文件的地址
		File file = new File("D:/logs/alert.log");
		DataSource dataSource = new FileDataSource(file.getAbsolutePath());
		//使用datasource实例化datahandler
		DataHandler dataHandler = new DataHandler(dataSource);
		bodyPart.setDataHandler(dataHandler);
		//设置附件标题，使用MimeUtility进行名字转码，否则接收到的是乱码
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
