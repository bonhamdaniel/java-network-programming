// Code adapted from SendEmail.java found in the tutorial at 
// http://www.tutorialspoint.com/javamail_api/

/*
Title: SendProgram1.java
Description: Simple email program that connects to the smtp.gmail.com server and sends 
             an email to a primary recipient, a list of at least three secondary 
             recipients (cc) and at least three tertiary recipients (bcc). The entire 
             email, including all recipients plus subject and body, is read from an input 
             file specified on the command line when the program is started.

             The format of the file provided must be:

             Server: email server
             User: email account (also used as the From: in the email)
             Password: email account password
             To: primary recipient
             CC: comma separated list of secondary recipients
             BCC: comma separated list of tertiary recipients
             Subject: Email subject
             Body: multiple lies of text representing the body of the email.
             EOF
                   
             The email body terminates (and email is sent) when EOF (end of file) is read.

Date: April 19th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// imports all libraries necessary for program
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Flags;
import java.io.File;
import java.io.*;
import java.lang.String;

public class SendProgram1 {
   public static void main(String[] args) {
      // checks to make sure email file has been included
      if (args.length != 1) {
        System.out.println("Usage: java SendProgram1 <email file>");
        System.exit(1);
      } // if (args.length)

      try {
         File emailFile = new File(args[0]); // retrieves file containing email contents from command line
         BufferedReader inFile = new BufferedReader(new FileReader(emailFile)); // attaches buffered reader to file
         String host = (inFile.readLine()).substring(8); // retrieves server info from file
         String from = (inFile.readLine()).substring(6); // retrieves sender account from file
         final String username = from; // retrieves sender account username from file
         final String password = (inFile.readLine()).substring(10); // retrieves sender account password from file
         String to = (inFile.readLine()).substring(4); // retrieves primary receipient from file
         String ccLine = (inFile.readLine()).substring(4); // retrieves comma separated list of cc recipients from file
         String bccLine = (inFile.readLine()).substring(5); // retrieves comma separated list of bcc recipients from file
         String subject = (inFile.readLine()).substring(9); // retrieves subject line from file
         String body = (inFile.readLine()).substring(6); // retrieves body of email from file
         String line = ""; // used to store each line of contents as they are read from file
         while (!((line = inFile.readLine()).equals("EOF"))) // continues reading body until EOF encountered
            body += line; // adds each line of the message body

         Properties props = new Properties(); // used to store server properties
         props.put("mail.smtp.auth", "true"); // sets authentication as true
         props.put("mail.smtp.starttls.enable", "true"); // enables tls
         props.put("mail.smtp.host", host); // sets host to that specified in file
         props.put("mail.smtp.port", "587"); // sets port for server

         // Gets the Session object and sets necessary properties
         Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password);
   	         } // get PasswordAuthentication()
         }); // Session.getInstance()

         try {
      	   Message message = new MimeMessage(session); // creates default MimeMessage object
      	   message.setFrom(new InternetAddress(from)); // sets From: header field with sender from file
      	   // sets To: header field with primary recipient, cc recipients, and bcc recipients from file
      	   message.setRecipients(Message.RecipientType.TO,
                     InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC,
                     InternetAddress.parse(ccLine));
            message.setRecipients(Message.RecipientType.BCC,
                     InternetAddress.parse(bccLine));
      	   message.setSubject(subject); // sets Subject: header field with subject from file
            message.setText(body); // sets body of message with body from file
            message.setFlag(Flags.Flag.SEEN, false);

      	   Transport.send(message); // send the email message

      	   System.out.println("Sent message successfully...."); // displays successful transport

         } catch (MessagingException e) { // catches any MessagingExceptions sending email message
            throw new RuntimeException(e);
         } // catch(MessagingException)
      } catch (IOException e) { // catches any IO exceptions
         System.out.println(e);
      } // catch(IOException)
   } // main method
} // SendProgram1 class