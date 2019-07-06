package cn.myweb01.money01.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.InputStream;
import java.util.Properties;

/**
 * 发送邮件工具类
 */
public final class MailUtil {
	//163邮箱的第二种方式
	/*private static final String MAIL_HOST = "smtp.163.com";
	private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
	private static final String USER = "13248134961@163.com";
	// 这个秘密不是邮箱的密码，是要去163邮箱开通申请得到的
	private static final String PASSWORD = "ZXQzxq1992";*/



	/*//这是163邮箱的第一种设置
	private static String hostName = null;
	private static String authen_name = null;
	private static String authen_pwd = null;
	private static String charset = null;
	//加载配置文件信息
	static {
		Properties p = new Properties();
		InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			p.load(inputStream);
			hostName = p.getProperty("host.name");
			authen_name = p.getProperty("authen.name");
			authen_pwd = p.getProperty("authen.pwd");
			charset = p.getProperty("charset");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	private MailUtil(){}
	/**
	 * 发送邮件
	 * 参数一:发送邮件给谁
	 * 参数二:发送邮件的内容
	 */
	public static void sendMail(String toEmail, String emailMsg) throws Exception {
		/*//qq邮箱的设置
		//1_创建Java程序与163邮件服务器的连接对象
		Properties props = new Properties();
		InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		props.load(inputStream);
		//创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));//发件方的用户名和密码
			}
		};
		//执行验证
		Session session = Session.getInstance(props, auth);
		//2_创建一封邮件
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(props.getProperty("from")));//设置邮件发送方
		message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));//设置发件方式和邮件接收方
		message.setSubject("用户激活");//设置邮件标题
		message.setContent(emailMsg, "text/html;charset=UTF-8");//设置邮件内容
		//3_发送邮件
		Transport.send(message);*/






		/*//163邮箱的第一种设置
		//创建HtmlEmail对象

		HtmlEmail htmlEmail = new HtmlEmail();
		//设置邮件服务器地址
		htmlEmail.setHostName(hostName);
		//设置账号和密码
		htmlEmail.setAuthentication(authen_name, authen_pwd);
		//设置发件人
		htmlEmail.setFrom(authen_name);
		//设置编码
		htmlEmail.setCharset(charset);

		//不使用默认的25端口(被阿里ecs禁用),而是ssl加密和465端口
		*//*htmlEmail.setSSLOnConnect(true);
		htmlEmail.setSslSmtpPort("465");
		htmlEmail.setSSLCheckServerIdentity(false);*//*

		//设置收件人
		htmlEmail.addTo(toEmail);
		//设置邮件主题
		htmlEmail.setSubject("【注册激活邮件】");
		//设置邮件内容
		htmlEmail.setMsg(emailMsg);
		//发送邮件
		htmlEmail.send();*/

		//163邮箱的第二种设置
		Properties prop = new Properties();
		InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");
		prop.load(inputStream);
		/*prop.setProperty("mail.host", MAIL_HOST);
		prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.ssl.enable", "true");//非常重要
		prop.setProperty("mail.smtp.port", "465");*/

		Session session = Session.getInstance(prop);
		//开启debug模式，方便看程序发送Email的运行状态
		session.setDebug(true);
		Transport ts = session.getTransport();
		ts.connect(prop.getProperty("mail.host"), prop.getProperty("mail.user"), prop.getProperty("mail.password"));
		Message message = simpleMail(prop.getProperty("mail.user"),session, prop.getProperty("mail.subject"), emailMsg,toEmail);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();

	}

	/**
	 * 一封简单的只包含文本的邮件
	 */
	public static MimeMessage simpleMail(String user,Session session, String subject, String content,String toEmail) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(subject);
		message.setContent(content, "text/html;charset=UTF-8");
		return message;
	}

	/**
	 * 测试类
	 */
	public static void main(String[] args) throws Exception{
		String toEmail = "1439167393@qq.com";
		String emailMsg = "测试一下";
		sendMail(toEmail,emailMsg);
		System.out.println("发送成功。。。");
	}
}









