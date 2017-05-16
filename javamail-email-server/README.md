JavaMail Email Application - email programs that:
  (a) queries an email server, using appropriate user credentials, first to download a list of pending (unread) emails, giving each email a reference number in the displayed list of pending emails, plus the sender (in parentheses). This is done by executing the program without any argument except user credentials: ‘java GetMail server user password’.
  (b) With the addition of the number of the email as provided in the list of returned pending emails, retrieves that email. This done by executing with arguments: 'java GetMail server user password email#'
  (c) connects to the smtp.gmail.com server and sends an email to a primary recipient, a list of at least three secondary recipients (cc) and at least three tertiary recipients (bcc). The entire email, including all recipients plus subject and body, is read from an input file specified on the command line when the program is started.
  (d) sends a message as in part c, but adds the capability of attaching a file.

Format of Sending Messages:

- Server: email server
- User: email account (also used as the From: in the email)
- Password: email account password
- To: primary recipient
- CC: comma separated list of secondary recipients
- BCC: comma separated list of tertiary recipients
- Subject: Email subject
- Body: multiple lies of text representing the body of the email.
- EOF
*The email body terminates (and email is sent) when EOF (end of file) is read.
*An attachment is provided after the file on the command line and is sent along
             with the email message.  Files accepted are images or .zip attachments.
 
 Retrieval Application (GetMail.java)
 
 - Program Purpose:
      Introduces the Java Networking concepts of receiving emails associated with the 
      JavaMail API.
- Compile: javac GetMail.java (Must first include javax.mail.jar in the CLASSPATH)
- Execution: java GetMail [server] [user] [password] or java GetMail [server] [user] [password] [email#]
- Notes:  The program must access a valid email account.
- Classes: 
        - GetMail - includes all required functionality. Only has the main method and the 
          method receiveMail.
- Variables:
      - properties - Properties - used to store the email server properties
      - emailSession - Session - used to store the Session used for the email Session
      - emailStore - Store - used to store the imaps Store used for receiving emails
      - emailFolder - Folder - used to store the Folder to access for receiving emails
      - unread - int - used to store the number of unread messages found
      - messages - Message[] - used to store all the unread messages found
      - message - Message - used to store the current message
      - mp - Multipart - used to store Multipart messages
      - bp - BodyPart - used to store the body of the message
      - host - String - used to store the server host used for receiving emails
      - mailStoreType - Store - used to store the type of Store used to receive emails
      - username - String - used to store the username of the account to access
      - password - String - used to store the password of the account to access
      - option - int - used to store the email # theuse would like to access.

