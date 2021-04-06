package VooraadBeheerOpstap;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {

    public void sendEmail() {
        String to = "jasonroffel@hotmail.nl";
        String from = "VooraadBeheerOpstap@praktijkmemo.nl";
        String host = "localhost";
        String user = "VooraadBeheerOpstap";
        String pass = "root";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", host);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Vooraadminimum bereikt");
            message.setText("Minimum vooraad is bereikt voor een artikel, check de vooraad in de applicatie");

            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
