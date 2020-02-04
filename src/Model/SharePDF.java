package Model;

import java.util.Properties;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SharePDF {

    public boolean verify_email_address(String email) {
        boolean flag = true;
        String[] parts = email.split("@");

        if (parts.length != 2) {
            return false;
        }

        if (parts[0].equals("")) {
            return false;
        }

        for(int i = 0; i < parts[0].length(); i++) {
            if ( (((int)parts[0].charAt(i) >= 65) && ((int)parts[0].charAt(i) <= 90)) ||  //a-z
                    (((int)parts[0].charAt(i) >= 97) && ((int)parts[0].charAt(i) <= 122)) ||   //A-Z
                    ((int)parts[0].charAt(i) == 45) || 											//-
                    ((int)parts[0].charAt(i) == 46) ||											//.
                    (((int)parts[0].charAt(i) >= 48) && ((int)parts[0].charAt(i) <= 57)) ) { 	//0-9
                flag = true;
            }
            else {
                flag = false;
                break;
            }
        }

        if (flag == true) {
            if ( (parts[1].equals("gmail.com")) ||
                    (parts[1].equals("hotmail.com")) ||
                    (parts[1].equals("live.com")) ||
                    (parts[1].equals("yahoo.com")) ||
                    (parts[1].equals("nu.edu.pk"))) {

                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }

    }

    public void sendEmail(String file, String toEmail) {
        final String username = "p176011@nu.edu.pk";
        final String password = "wajahat.raza777";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("p176011@nu.edu.pk"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Pdf Reader");
            message.setText("PDF sent by PDF Reader");

            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
//            String file = "/home/wajahat/IdeaProjects/pdfReaderProject/Assignment 01-FALL 2019.pdf";
            String fileName = "file.pdf";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
