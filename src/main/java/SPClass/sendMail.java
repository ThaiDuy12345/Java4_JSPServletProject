package SPClass;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class sendMail {
	public static void send(String sEmail, String sPassword, String rEmail, String sTitle, String sDescription) throws AddressException, MessagingException {
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.starttls.enable", "true");
        pro.setProperty("mail.smtp.host", "smtp.gmail.com");
        pro.setProperty("mail.smtp.port", "587");
		
		Authenticator aut = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sEmail, sPassword);
			}
		};
		Session s = Session.getInstance(pro, aut);
		MimeMessage msg = new MimeMessage(s);
		msg.setFrom(new InternetAddress(sEmail));
		msg.setRecipients(Message.RecipientType.TO, rEmail);
		msg.setSubject(sTitle,"utf8");
		msg.setText(sDescription,"utf8","html");
		msg.setReplyTo(msg.getFrom());
		
		Transport.send(msg);
	}
}
