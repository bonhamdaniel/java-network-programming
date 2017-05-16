// references used:
// http://alvinalexander.com/java/javamail-search-unseen-unread-messages-mailbox-pop3
// http://www.compiletimeerror.com/2013/06/reading-email-using-javamail-api-example.html#.VTaa-lXBzGc
// https://metoojava.wordpress.com/2010/03/21/java-code-to-receive-mail-using-javamailapi/

/*
Title: GetMail.java
Description: Simple email program that queries an email server, using appropriate user 
             credentials, first to download a list of pending (unread) emails, giving 
             each email a reference number in the displayed list of pending emails, plus 
             the sender (in parentheses). This is done by executing the program without 
             any argument except user credentials:

             ‘java GetMail server user password’.

             A second invocation of the program, with the addition of the number of the 
             email as provided in the list of returned pending emails, retrieves that 
             email. This done by executing with arguments:

             'java GetMail server user password email#'

Date: April 21st, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// imports all libraries necessary for program
import java.io.IOException;  
import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.search.*;
import javax.mail.internet.*;
import com.sun.mail.pop3.POP3Store;  
  
public class GetMail{  
  
 public static void receiveEmail(String imapHost, String storeType, String user, String password, int option) {  
  try {  
   // sets all properties and gets the session object 
   Properties properties = new Properties();
   properties.put("mail.store.protocol", "imaps");  
   properties.put("mail.imaps.host", imapHost); 
   properties.put("mail.imaps.port", "993");
   properties.put("mail.imaps.starttls.enable", "true"); 
   Session emailSession = Session.getDefaultInstance(properties);  
     
   // creates the imap store object and connects with the server using info provided
   Store emailStore = emailSession.getStore(storeType);  
   emailStore.connect(imapHost, user, password);  
  
   // creates the folder object and opens it  
   Folder emailFolder = emailStore.getFolder("Inbox");  
   emailFolder.open(Folder.READ_WRITE);
   
   // retrieves the messages from the folder and displays result
   int unread = emailFolder.getUnreadMessageCount(); // gets count of unread
   if (option == 0) { // checks whether an opion was provided at commad line
     if (unread > 0) { // displays list of messages if any unread
        Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false)); // gets all unread messages
        for (int i = 0; i < messages.length; i++) { // lists all unread messages
        Message message = messages[i]; 
        System.out.println((i + 1) + ".  " + message.getSubject() + "   (" + message.getFrom()[0] + ")");    
      } // if (unread)
     } else { // displays message when no available unread messages
        System.out.println("No new messages to display.");
     } // else
  } else { // gets specified message when option provided
      Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false));
      Message message = messages[option - 1]; // gets specified message
      Multipart mp = (Multipart) message.getContent(); // creates multipart object
      BodyPart bp = mp.getBodyPart(0); // gets body of message
      System.out.println("\r\n" + (option) + ".  " + message.getSubject() + "   (" + message.getFrom()[0] + ")\r\n");
      System.out.println(bp.getContent()); // displays body of message
  } 
  
   // closes the store and folder objects  
   emailFolder.close(false);  
   emailStore.close();  
  
  } catch (MessagingException | IOException | ArrayIndexOutOfBoundsException e) { // catches errors connecting with provided info 
    System.out.println("There was an error connecting with the provided information");
    System.exit(1);
  } // catch
 } // receiveEmail  
  
 public static void main(String[] args) {  
  
  if (args.length != 3 && args.length != 4) { // checks that required args are provided
    System.out.println("Usage: java GetMail <server> <user> <password>");
    System.out.println("or");
    System.out.println("Usage: java GetMail <server> <user> <password> <message #>");
    System.exit(1);
  } // if (args.length)

  String host = args[0]; // retrieves server from args provided 
  String mailStoreType = "imaps"; // sets store type
  String username= args[1]; // retrieves username from args provided
  String password= args[2]; // retrieves password from args provided

  int option = 0; // sets option initially to 0 for testing
  if (args.length == 4) option = Integer.parseInt(args[3]); // sets option if arg provided

  // calls receiveEmail method with info provided
  receiveEmail(host, mailStoreType, username, password, option);  
  
 } // main  
} // GetMail class 
