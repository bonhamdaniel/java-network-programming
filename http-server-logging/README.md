HTTPServerWLog.java - beginning with the FileHTTPServer, adds full logging capabilities to the program. The log file produced includes date and time, IP of requesting client, HTTP request, and HTTP response.

- Program Purpose:
		Introduces the Java Networking concept of Logging.
- Compile: javac HTTPServerWLog.java
- Execution: java HTTPServerWLog [root directory] [port] 
- Notes:  A telnet client is used to test the server.  GET commands are sent from telnet for
		specified files to be searched for on the server.  This is further documented in
		the test plan.  Each request is logged to a file called "requests" created in the
		program folder.
- Classes: 
	- HTTPServerWLog - the bulk of the program, including main, which sets up a HTTP
			server for a given directory of files, and running on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.  Includes Logger instances.
	- RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.
	- MyFormatter - extends java.util.logging.Formatter.  Formats log entries to 
			specified file format.
- Variables:
	- auditLogger - Logger - used to log server requests to "requests" file
	- errorLogger - Logger - used to log server errors to "errors" file
	- consoleLogger - Logger - used to log server status to the console
	- rootDirectory - File - used to store the directory the server is to service
	- port - int - used to store the port that the server will run on
	- encoding - String - used to store the encoding used for the requested file
	- pool - ExecutorService - used to handle threading, which is used for requests
	- docroot - File - used to store directory specified from command line
	- server - HTTPServerWLog - used to an instance of the implemented server
	- indexFileName - String - used to store the index file for the directory
	- connection - Socket - used to store the connection between client/server
	- root - String - used to store the directory of the requested file
	- raw - OutputStream - used to store a raw output stream to the client, allows 
	- different file types to be serviced
	- out - Writer - used to store a Writer for the output stream
	- in = InputStream - used to store an input stream from server
	- requestLine - StringBuilder - used to store client request
	- c - int - used to store each individual char as it is read from client request
	- get - String - used to store a string version of client request
	- tokens - String[] - used to store a tokenized version of client request
	- method - String - used to store the type of client request
	- version - String - used to store the version of the client
	- fileName - String - used to store the file requested by the client
	- contentType - String - used to store the contentType of the file
	- theFile - File - used to store the File requested by the client
	- theData - byte[] - used to store the data from the requested file
	- body - String - used to store the message back when a file is not found
	- now - Date - used to store the current date
	- requestHandler - FileHandler - used for Logger that logs requests (auditLogger)
	- errorHandler - FileHandler - used for Logger that logs errors (errorLogger)
	- formatter - MyFormatter - used to format log entries
	- df - DateFormat - used to store/format the dates included in the log entries
	- logEntry - String - used to store the ibformation included in a log entry
	- builder - StringBuilder - used to store/build the formatted log entry

Test Plan
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 10:28:50 AM HTTPServerWLog start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 10:28:50 AM HTTPServerWLog start
		- > INFO: Document Root: Directory
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET McDavid.html HTTP/1.0
		- > HTTP/1.0 200 OK
		- > Date: Thu Apr 16 10:30:01 EDT 2015
		- > Server: HTTPServerWLog
		- > Content-length: 65186
		- > Content-type: text/html
		- > 
		- > 
		- > <!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&pid=160293 -->
 		- >                 <html><head><meta http-equiv="Content-Type" content="text/html;charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><table><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><span class="html-doctype">&lt;!DOCTYPE html&gt;</span> </td></tr><tr><td class="line-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;html <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-value">http://www.w3.org/1999/xhtml</span>" <span class="html-attribute-name">xml:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-attribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</span></td></tr><tr><td class="line-number" value="3"></td><td class="line-content"><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number" value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</span>Connor McDavid hockey statistics and profile at hockeydb.com<span class="html-tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></td><td class="line-content"><span class="html-tag">&lt;meta <span class="html-att
		.
		.
		.
	- Log after execution ("requests"):
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:30:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\McDavid.html HTTP/1.0" 200 65186

- Normal Case 2:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 10:28:50 AM HTTPServerWLog start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 10:28:50 AM HTTPServerWLog start
		- > INFO: Document Root: Directory
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET crosby.html HTTP/1.0
		- > HTTP/1.0 200 OK
		- > Date: Thu Apr 16 10:38:40 EDT 2015
		- > Server: HTTPServerWLog
		- > Content-length: 106742
		- > Content-type: text/html
		- > 
		- > \<!DOCTYPE html>
		- >                <!-- saved from url=(0057)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&pid=73288 -->
		- >                                \<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">                   <title>Sidney Crosby hockey statistics and profile at hockeydb.com</title>           <meta name="description" content="Statistics of Sidney Crosby, a hockey player from Cole Harbour, NS born Aug 7 1987 who was active from 2003 to 2015.">     <meta name="format-detection" content="telephone=no">ontent="width=device-width, initial-scale=1">                                              <link rel="StyleSheet" href="./index_files/standard.css" type="text/css" media="screen">                                                      <link rel="StyleSheet" href="./index_files/standard-print.css" type="text/css" media="print">                                                                   <link rel="StyleSheet" href="./index_files/standard-hh.css" type="text/css" media="handheld">
		.
		.
		.
	- Log after execution ("requests"):
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:30:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\McDavid.html HTTP/1.0" 200 65186
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:38:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\crosby.html HTTP/1.0" 200 106742

- Normal Case 3:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 10:44:26 AM HTTPServerWLog start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 10:44:26 AM HTTPServerWLog start
		- > INFO: Document Root: Directory
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET strome.html HTTP/1.0
		- > HTTP/1.0 200 OK
		- > Date: Thu Apr 16 10:45:27 EDT 2015
		- > Server: HTTPServerWLog
		- > Content-length: 62062
		- > Content-type: text/html
		- > 
		- > 
		- > \<!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&pid=170174 -->
		- >                  \<html><head><meta http-equiv="Content-Type" content="text/html;charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><table><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><span class="html-doctype">&lt;!DOCTYPE html&gt;</span> </td></tr><tr><td class="line-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;html <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-value">http://www.w3.org/1999/xhtml</span>" <span class="html-attribute-name">xml:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-attribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</span></td></tr><tr><td class="line-number" value="3"></td><td class="line-content"><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number" value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</span>Dylan Strome hockey statistics and profile at hockeydb.com<span class="html-tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></td><td class="line-content"><span class="html-tag">&lt;meta <span class="html-attribute-name">http-equiv</span>="<span class="html-attribute-value">Content-Type</span>" <span class="html-attribute-name">content</span>="<span class="html-attribute-value">text/html; charset=windows-1252</span>" /&gt;</span></td></tr><tr><td class="line-number" value="6"></td><td class="line-content"><span class="html-tag">&lt;meta <span class="html-attribute-name">name</span>="<span class="html-attribute-value">description</span>" <span class="html-attribute-name">content
		.
		.
		.
	- Log after execution ("requests"):
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:30:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\McDavid.html HTTP/1.0" 200 65186
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:38:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\crosby.html HTTP/1.0" 200 106742
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:45:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\strome.html HTTP/1.0" 200 62062

- Error Case 1 (file doesn't exist):
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 11:10:18 AM HTTPServerWLog start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 11:10:18 AM HTTPServerWLog start
		- > INFO: Document Root: Directory
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET rto HTTP/1.0
		- > HTTP/1.0 404 File Not Found
		- > Date: Thu Apr 16 11:11:28 EDT 2015
		- > Server: HTTPServerWLog
		- > Content-length: 117
		- > Content-type: text/html; charset=utf-8
		- > 
		- > <HTML>
		- > <HEAD><TITLE>File Not Found</TITLE>
		- > </HEAD>
		- > <BODY><H1>HTTP Error 404: File Not Found</H1>
		- > </BODY></HTML>
	- Log after execution ("requests"):
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:30:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\McDavid.html HTTP/1.0" 200 65186
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:38:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\crosby.html HTTP/1.0" 200 106742
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:45:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\strome.html HTTP/1.0" 200 62062
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:11:11:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\rto HTTP/1.0" 404 0

- Error Case 2 (invalid request type):
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 11:14:16 AM HTTPServerWLog start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 11:14:16 AM HTTPServerWLog start
		- > INFO: Document Root: Directory
	- Client command prompt: telnet localhost 80
	- Client command prompt: POST index.html HTTP/1.0
		- > <HTML>
		- > <HEAD><TITLE>Not Implemented</TITLE>
		- > </HEAD>
		- > <BODY><H1>HTTP Error 501: Not Implemented</H1>
		- > </BODY></HTML>
	- Log after execution ("requests"):
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:30:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\McDavid.html HTTP/1.0" 200 65186
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:38:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\crosby.html HTTP/1.0" 200 106742
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:10:45:10] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\strome.html HTTP/1.0" 200 62062
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:11:11:19] "GET C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\rto HTTP/1.0" 404 0
		- > 0:0:0:0:0:0:0:1 - - [16/Apr/2015:11:14:56] "POST C:\Users\Dan\mingw\COMP 348\
		- > Assignment2\Directory\index.html" 501 0

- Testing Log File with Program from Assignment 1:
	- Command prompt: java MyPooledWeblog "access_log/requests" 1
		- > 5       0:0:0:0:0:0:0:1
		- > Total entries: 5
		- Command prompt: java MyPooledWeblog "access_log/requests" 2
		- > The total bytes transmitted was 233990
		- > The total entries examined was 5
		- Command prompt: java MyPooledWeblog "access_log/requests" 3
		- > 233990  0:0:0:0:0:0:0:1
		- > Total bytes transmitted was 233990
		- > The total entries examined was 5

**All output as expected

Discussion: The first five test cases demonstrate that the log file ("requests") is correctly
		being written to on each request, with the information specific to each individual
		request.  The information is formatted identically to that of the access log files
		used in FileHTTPServer.  These cases log address, time, request, response code, and 
		bytes transmitted using the different webpages.  
		The results displayed were tested against manual inspection performed outside the 
		program on sources.  All manual inspections matched up with the program results.
		The last set of tests executes the log file produced by the program as input to
		the MyPooledWeblog application.  All results are as expected.

**To perform the set of tests with the program from MyPooledWeblog the "requests"
		log file must be copied over into the access_log directory in MyPooledWeblog directory.

**Raw output is sent when a file is successfully requested in order to allow
		different types of files to be served.
