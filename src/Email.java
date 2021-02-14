

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Email {
	public Email(String toEmailAdd, String msgText ){
		String toEmail = toEmailAdd;
		String fromEmail = "powersurgelmt12@gmail.com";
		String pass = "Power@123";
		String subj = "Power Surge";
		
		Properties props = new Properties();    
	    props.put("mail.smtp.host", "smtp.gmail.com");    
	    props.put("mail.smtp.socketFactory.port", "465");    
	    props.put("mail.smtp.socketFactory.class",    
	              "javax.net.ssl.SSLSocketFactory");    
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.port", "465");  
		
	    Session session = Session.getDefaultInstance(props,    
	            new javax.mail.Authenticator() {    
	            protected PasswordAuthentication getPasswordAuthentication() {    
	            return new PasswordAuthentication(fromEmail,pass);  
	            }    
	    });
		
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subj);
			msg.setText(msgText);
			Transport.send(msg);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}

	}
}
