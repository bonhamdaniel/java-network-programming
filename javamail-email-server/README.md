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


Send Mail Application (SendProgram1.java)
- Program Purpose:
      Introduces the Java Networking concepts of sending emails associated with the 
      JavaMail API.
- Compile: javac SendProgram1.java (Must first include javax.mail.jar in the CLASSPATH)
- Execution: java SendProgram1 thisfile.txt
- Notes:  The program must use a valid email account.
- Classes: 
  - SendProgram1 - includes all required functionality. Only has the main method.
- Variables:
   - emailFile - File - used to store the file provided at the command line, which 
            contains the contents of the email to be sent.
   - inFile - BufferedReader - used to store buffered reader attached to the email file
   - host - String - used to store the email server used, retrieved from the email file
   - from - String - used to store the account to send the email from, retrieved from 
            the email file
   - username - String - used to store the username of the sender account, retrieved from
            the email file
   - password - String - used to store the password of the sender account, retrieved from
            the email file
   - to - String - used to store the primary recipient of the email, retrieved from the 
            email file
   - ccLine - String - used to store a line of comma separated cc addresses for the email, 
            retrieved from the email file
   - bccLine - String - used to store a line of comma separated bcc addresses for the email,
            retrieved from the email file
   - subject - String - used to store the subject of the email, retrieved from the email file
   - body - String - used to store the body of the email, retrieved from the email file
   - line - String - used to store each line as it is read in from the email file
   - props - Properties - used to store the server properties used to send the email
   - session - Session - used to store the Session instance used to send the email
   - message - Message - used to store the various components of the email message

Send with Attachment Application (SendProgramWAttachment.java)
- Program Purpose:
      Introduces the Java Networking concepts of sending emails associated with the 
      JavaMail API, as well as including an attachment with the email.
- Compile: javac SendProgramWAttachment.java (Must first include javax.mail.jar in the CLASSPATH)
- Execution: java SendProgramWAttachment thisfile.txt [attachment file]
- Notes:  The program must access a valid email account.
- Classes: 
   - SendProgramWAttachment - includes all required functionality. Only has the main method.
- Variables:
   - emailFile - File - used to store the file provided at the command line, which 
            contains the contents of the email to be sent.
   - inFile - BufferedReader - used to store buffered reader attached to the email file
   - host - String - used to store the email server used, retrieved from the email file
   - from - String - used to store the account to send the email from, retrieved from 
            the email file
   - username - String - used to store the username of the sender account, retrieved from
            the email file
   - password - String - used to store the password of the sender account, retrieved from
            the email file
   - to - String - used to store the primary recipient of the email, retrieved from the 
            email file
   - ccLine - String - used to store a line of comma separated cc addresses for the email, 
            retrieved from the email file
   - bccLine - String - used to store a line of comma separated bcc addresses for the email,
            retrieved from the email file
   - subject - String - used to store the subject of the email, retrieved from the email file
   - body - String - used to store the body of the email, retrieved from the email file
   - line - String - used to store each line as it is read in from the email file
   - props - Properties - used to store the server properties used to send the email
   - session - Session - used to store the Session instance used to send the email
   - message - Message - used to store the various components of the email message
   - messageBodyPart - BodyPart - used to store a body part included in the message
   - multipart - Multipart - used to store a default Multipart used for message
   - filename - String - used to store the attachment file provided at the command line
   - source - DataSource - used to store a FileDatSource created with attachment file

GetMail Test Plan
- ***all necessary jars and classes must be in CLASSPATH***
- Normal Case 1:
   - Runs program as constituted, without any alterations.
   - Should get any unread emails in the specified account/folder.
   - Command prompt: java GetMail imap.gmail.com bonhamdaniel@gmail.com *************
        - > 1.  Drive to the Hoop with this Basketball Playoff Contest   (DraftKings <email@e.draftkings.com>)
        - > 2.  Free Starbucks Coffee Tomorrow (Just Bring a Cup!), Sales at Lululemon, Amazon.ca, Zara, Costco + More   ("RedFlagDeals.com Bargains Insider" <newsletter@e.redflagdeals.com>)

**Output correct**
(A manuel check of my inbox shows only these two unread messages)

- Normal Case 2:
   - Runs program as constituted, without any alterations.
   - Should get any unread emails in the specified account/folder.
   - Command prompt: java GetMail imap.gmail.com bonhamdaniel@gmail.com *********** 2
        - > 2.  Free Starbucks Coffee Tomorrow (Just Bring a Cup!), Sales at Lululemon, Amazon.ca, Zara, Costco + More   ("RedFlagDeals.com Bargains Insider" <newsletter@e.redflagdeals.com>)
        - > 
        - > 
        - > To view this email as a web page, go
        - > http://cl.s6.exct.net/?qs=70de6d0c1ec19e8a246bf397dc45d61b5f91e31b48485ee8d857e5
        - > 3754dcae83
        - > here .
        - > 
        - > http://cl.s6.exct.net/?qs=70de6d0c1ec19e8acc88f181a813f86e0e0b9ffd1469d1c0836cb7
        - > 254906ec1a
        - > 
        - > http://cl.s6.exct.net/?qs=70de6d0c1ec19e8a9c0fa99f4e7aeb68e564651f3c0a37c495cd2c
        - > 66495278ca
        - > 
        - > http://cl.s6.exct.net/?qs=70de6d0c1ec19e8ac56293c56f2a8d539a53838bf2709f644eddd2
        - > a65bc906d4
        - > 
        - > http://cl.s6.exct.net/?qs=70de6d0c1ec19e8a71b5a06c6cd14b82c905a84357e3702eba9a53
        - > 4ac961aea8
        - > 
        - > FEATURED DEAL OF THE WEEKApril 21, 2015
        - > DEALS OF THE WEEKApril 21, 2015
        - > 
        - > 15 MOST POPULAR DEALS OF THE WEEK
        - > April 21, 2015
        - > 
        - > .
        - > .
        - > .
  
**Output correct**
(A manuel check of the message shoes it is displaying correctly)

- Normal Case 3:
    - Runs program as constituted, without any alterations.
    - Should get any unread emails in the specified account/folder.
    - Command prompt: java GetMail imap.gmail.com bonhamdaniel@gmail.com *************
        - > 1.  Drive to the Hoop with this Basketball Playoff Contest   (DraftKings <email@
        - > e.draftkings.com>)
        - > 2.  COMP306 R2: Textbook Source Code Won't Work   (alexandrabr7 <noreply@athabascau.ca>)

**Output correct**
      (Showing that the message read in Test Run 2 is no longer unread - a new message added)

- Exception Case 1 (not enough args provided):
    - Runs program as constituted, without any alterations.
    - Should display error message and exit.
    - Command prompt: java SendProgram1 imap.gmail.com bonhamdaniel@gmail.com
        - > Usage: java GetMail [server] [user] [password] or Usage: java GetMail [server] [user] [password] [message #] (message displayed and program exits)

**Output correct**

- Exception Case 2 (problem with provided arguments - incorrect password):
    - Runs program as constituted, without any alterations.
    - Should display error message and exit.
    - Command prompt: java GetMail imap.gmail.com bonhamdaniel@gmail.com fhsgsfdgdfgds
        - > There was an error connecting with the provided information (message displayed and program exits)

**Output correct**

Discussion: The first test case demonstrates that the program successfully retrieves only unread
      messages from the specified account.
      The second test case demonstrates correct display of an email selected by number 
      from the list provided by the program.
      The third test case demonstrates that once an email has been read it no longer
      shows up in the unread list provided by the program.
      The exception test cases demonstrate how the program handles caseswhere either not
      enough or incorrect information in provided.  It simply displays a usage message 
      and exits.

SendProgram1 Test Program
- ***all necessary jars and classes must be in CLASSPATH***
- ***thisfile.txt included in folder - must be in specified format***
- Normal Case 1:
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided to specified recipient.
    - Command prompt: java SendProgram1 thisfile.txt
        - > Sent message successfully....
    - Email received in receipient inbox
        - > from: ***@gmail.com
        - > to: ***@gmail.com
        - > cc: ***@khicommunity.com,
        - > ***@hotmail.com,
        - > ***@facebook.com
        - > bcc:  ***@hotmail.com,
        - > ***@ymail.com,
        - > ***@gmail.com
        - > date: Tue, Apr 21, 2015 at 2:13 PM
        - > subject:  Email Server Project
        - > mailed-by:  gmail.com
        - > Sorry, I'm doing a project and needed to use 7 different e-mail addresses 
        - > to send a message to from a server that I had to program.  You may get inundated 
        - > with this message :/

**Output correct**

***No other normal testing necessary, as this demonstrates that the email specified in the
   file provided at the command line is successfully sent to the recipients inbox.

- Exception Case 1 (no file provided at command line):
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided to specified recipient.
    - Command prompt: java SendProgram1 thisfile.txt
        - > Usage: java SendProgram1 [email file] (message displayed and program exits)

**Output correct**

Discussion: The first test case demonstrates that the program successfully retrieves the email
      information from the file spcified at the command line and transports the email
      message to all intended recipients.  All recipients included verified that they did
      indeed receive the messages send via the program.
      The exception test case demonstrates how the program handles the case where a file
      is not included on the command line.  It simply displays a usage message and exits.
 
***File provided at command line must be a valid email file in proper format***

SendProgramWAttachment Test Plan
- ***all necessary jars and classes must be in CLASSPATH***
- ***thisfile.txt included in folder - must be in specified format***
- ***kess.jpg file included in folder***
- Normal Case 1:
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided, and attachment to specified recipients.
    - Command prompt: java SendProgramWAttachment thisfile.txt kess.jpg
        - > Sent message successfully....
    - Email received in receipient inbox
        - > from: ***@gmail.com
        - > to: ***@gmail.com
        - > cc: ***@khicommunity.com,
        - > ***@hotmail.com,
        - > ***@facebook.com
        - > bcc:  ***@hotmail.com,
        - > ***@ymail.com,
        - > ***@gmail.com
        - > date: Tue, Apr 21, 2015 at 2:32 PM
        - > subject:  Email Server Project
        - > mailed-by:  gmail.com
        - > Sorry, I'm doing a project and needed to used 7 different e-mail addresses 
        - > to send a message to from a server that I had to program.  You may get inundated 
        - > with this message :/

***kess.jpg ttachment image was displayed in the email below message body***
**Output correct**

- Normal Case 2:
    - ***iso.png file included in folder***
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided, and attachment to specified recipients.
    - Command prompt: java SendProgramWAttachment thisfile.txt iso.png
        - > Sent message successfully....
    - Email received in receipient inbox
        - > from: ***@gmail.com
        - > to: ***@gmail.com
        - > cc: ***@khicommunity.com,
        - > ***@hotmail.com,
        - > ***@facebook.com
        - > bcc:  ***@hotmail.com,
        - > ***@ymail.com,
        - > ***@gmail.com
        - > date: Tue, Apr 21, 2015 at 2:36 PM
        - > subject:  Email Server Project
        - > mailed-by:  gmail.com
        - > Sorry, I'm doing a school project and needed to used 7 different e-mail addresses 
        - > to send a message to from a server that I had to program.  You may get inundated 
        - > with this message :/

***iso.png attachment image was displayed in the email below message body***
**Output correct**

- Normal Case 3:
    - ***example.zip file included in folder***
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided, and attachment to specified recipients.
    - Command prompt: java SendProgramWAttachment thisfile.txt example.zip
        - > Sent message successfully....
    - Email received in receipient inbox
      > from: ***@gmail.com
        - > to: ***@gmail.com
        - > cc: ***@khicommunity.com,
        - > ***@hotmail.com,
        - > ***@facebook.com
        - > bcc:  ***@hotmail.com,
        - > ***@ymail.com,
        - > ***@gmail.com
        - > date: Tue, Apr 21, 2015 at 2:38 PM
        - > subject:  Email Server Project
        - > mailed-by:  gmail.com
        - > Sorry, I'm doing a project and needed to used 7 different e-mail addresses 
        - > to send a message to from a server that I had to program.  You may get inundated 
        - > with this message :/

***example.zip attachment was included in the email below message body***
**Output correct**
***No other normal testing necessary, as this demonstrates that the email specified in the
   file and attachment provided at the command line is successfully sent to the recipients inbox.

- Exception Case 1 (no attachment provided at command line):
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided to specified recipient.
    - Command prompt: java SendProgramWAttachment thisfile.txt
        - > Usage: java SendProgramWAttachment [email file] [attachment] (message displayed and program exits)

**Output correct**

- Exception Case 2 (no email file provided at command line):
    - Runs program as constituted, without any alterations.
    - Should send email included in file provided to specified recipient.
    - Command prompt: java SendProgramWAttachment example.zip
        - > Usage: java SendProgramWAttachment [email file] [attachment] (message displayed and program exits)

**Output correct**

Discussion: The first three test cases demonstrate that the program successfully retrieves the email
      information from the file specified at the command line and transports the email
      message and attachment (jpg, png, zip) to all intended recipients.  All recipients included 
      verified that they did indeed receive the messages sent via the program.
      The exception test cases demonstrate how the program handles the case where a file
      is not included on the command line.  It simply displays a usage message and exits.

***File provided at command line must be a valid email file in proper format***
