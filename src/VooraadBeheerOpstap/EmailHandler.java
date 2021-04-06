package VooraadBeheerOpstap;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {

    public void sendEmail() {
        //String to = "l.tunders@praktijkmemo.nl";
        String to = "jasonroffel@hotmail.nl";
        String from = "VooraadBeheerOpstap@opstapvooraad.nl";
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
            message.setSubject("Test email");
            message.setText("Dit is een test email voor Ludy, om te kijken of deze goed aankomt");

            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendStockLowEmail(Article article) {
        //String to = "l.tunders@praktijkmemo.nl";
        String to = "jasonroffel@hotmail.nl";
        String from = "VooraadBeheerOpstap@opstapvooraad.nl";
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

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Beste ontvanger, \n\n")
                .append("De minimum vooraad voor artikel: ").append(article.getName()).append(" is bereikt\n")
                .append("De huidige vooraad is: ").append(article.getCurrentStock()).append(" ")
                .append("en de minimum vooraad is: ").append(article.getMinimumStock()).append(".\n")
                .append("Geadviseerd wordt om nieuwe artikelen te bestellen.\n")
                .append("De barcode voor dit artikel is: ").append(article.getScan()).append(". \n\n")
                .append("Met vriendelijke groeten, \n\n")
                .append("VooraadBeheer praktijk memo");



        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Vooraadmimimum bereikt voor " + article.getName());
            message.setText(stringBuilder.toString());

            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
