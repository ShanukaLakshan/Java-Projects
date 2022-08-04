
// import java.lang.System.Logger;
// import java.lang.System.Logger.Level;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
    public static void sendMail(String sendersMail) throws Exception {

        System.out.println("Preparing email !");

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        // prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String myEmail = "your_email";
        String password = "generated_app_password";

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        Message message = prepareMessage(session, myEmail, sendersMail);
        Transport.send(message);
        System.out.println("Done");
    }

    private static Message prepareMessage(Session session, String myEmail, String sendersMail) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendersMail));
            message.setSubject("My First Mail");
            message.setText("Hey Shanu");
            return message;
        } catch (Exception e) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
