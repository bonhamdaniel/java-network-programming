FileHTTPServer.java - an HTTP server able to display HTML web pages as well as embedded multimedia content such as .pdf files or images (.jpg or .png).

- Program Purpose:
		Introduces the Java Networking concepts of server I/O, 
		Sockets, ServerSockets, etc.
- Compile: javac FileHTTPServer.java
- Execution: java FileHTTPServer <root directory> <port> 
- Notes:  A telnet client is used to test the server.  GET commands are sent from telnet for
		specified files to be searched for on the server.  This is further documented in
		the test plan.
- Classes: 
	- FileHTTPServer - the bulk of the program, including main, which sets up a HTTP
			server for a given directory of files, and running on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.
	- RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.
- Variables:
	- logger - Logger - used to store/provide logging capabilities
	- rootDirectory - File - used to store the directory the server is to service
	- port - int - used to store the port that the server will run on
	- encoding - String - used to store the encoding used for the requested file
	- pool - ExecutorService - used to handle threading, which is used for requests
	- docroot - File - used to store directory specified from command line
	- server - FileHTTPServer - used to an instance of the implemented server
	- indexFileName - String - used to store the index file for the directory
	- connection - Socket - used to store the connection between client/server
	- root - String - used to store the directory of the requested file
	- raw - OutputStream - used to store a raw output stream to the client, allows 
			different file types to be serviced
	- out - Writer - used to store a Writer for the output stream
	- in - InputStream - used to store an input stream from server
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

Test Plan
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Server command prompt: java FileHTTPServer "Directory" 80
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET McDavid.html HTTP/1.0
		- > HTTP/1.0 200 OK
		- > Date: Thu Apr 02 16:38:24 EDT 2015
		- > Server: FileHTTPServer
		- > Content-length: 65186
		- > Content-type: text/html
		- > 
		- > 
		- > \<!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter
		- > =Y&pid=160293 -->
 		- >                 \<html><head><meta http-equiv="Content-Type" content="text/html;
		- >  charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><tab
		- > le><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><s
		- > pan class="html-doctype">&lt;!DOCTYPE html&gt;</span> </td></tr><tr><td class="l
		- > ine-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;h
		- > tml <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-
		- > value">http://www.w3.org/1999/xhtml</span>" <span class="html-attribute-name">xm
		- > l:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-a
		- > ttribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</s
		- > pan></td></tr><tr><td class="line-number" value="3"></td><td class="line-content
		- > "><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number
		- > " value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</
		- > span>Connor McDavid hockey statistics and profile at hockeydb.com<span class="ht
		- > ml-tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></t
		- > d><td class="line-content"><span class="html-tag">&lt;meta <span class="html-att
		- .
		- .
		- .

**All output as expected

- Normal Case 2:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Server command prompt: java FileHTTPServer "Directory" 80
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET crosby.html HTTP/1.0
		> HTTP/1.0 200 OK
		> Date: Thu Apr 02 16:52:04 EDT 2015
		> Server: FileHTTPServer
		> Content-length: 82571
		> Content-type: text/html
		> 
		> <!DOCTYPE html>
		>                <!-- saved from url=(0066)http://www.hockeydb.com/ihdb/stats/pdis
		> play.php?filter=Y&pid=73288 -->
		>                                <html xmlns="http://www.w3.org/1999/xhtml" xml:la
		> ng="en" lang="en"><head><meta http-equiv="Content-Type" content="text/html; char
		> set=windows-1252">
		>                   <title>Sidney Crosby hockey statistics and profile at hockeydb
		> .com</title>
		> 
		>             <meta name="description" content="Statistics of Sidney Crosby, a hoc
		> key player from Cole Harbour, NS born Aug 7 1987 who was active from 2003 to 201
		> 5.">
		>     <meta name="format-detection" content="telephone=no">
		> ontent="width=device-width, initial-scale=1">
		> 
		>                                              <link rel="StyleSheet" href="./inde
		> x_files/standard.css" type="text/css" media="screen">
		>                                                      <link rel="StyleSheet" href
		> ="./index_files/standard-print.css" type="text/css" media="print">
		>                                                                   <link rel="Sty
		> leSheet" href="./index_files/standard-hh.css" type="text/css" media="handheld">
		.
		.
		.
		**All output as expected
Normal Case 3:
		Runs program as constituted, without any alterations.
		Should display output as required in program specification.
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   GET strome.html HTTP/1.0
		> HTTP/1.0 200 OK
		> Date: Thu Apr 02 16:58:01 EDT 2015
		> Server: FileHTTPServer
		> Content-length: 62062
		> Content-type: text/html
		> 
		> 
		> <!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter
		> =Y&pid=170174 -->
		>                  <html><head><meta http-equiv="Content-Type" content="text/html;
		>  charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><tab
		> le><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><s
		> pan class="html-doctype">&lt;!DOCTYPE html&gt;</span> </td></tr><tr><td class="l
		> ine-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;h
		> tml <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-
		> value">http://www.w3.org/1999/xhtml</span>" <span class="html-attribute-name">xm
		> l:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-a
		> ttribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</s
		> pan></td></tr><tr><td class="line-number" value="3"></td><td class="line-content
		> "><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number
		> " value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</
		> span>Dylan Strome hockey statistics and profile at hockeydb.com<span class="html
		> -tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></td>
		> <td class="line-content"><span class="html-tag">&lt;meta <span class="html-attri
		> bute-name">http-equiv</span>="<span class="html-attribute-value">Content-Type</s
		> pan>" <span class="html-attribute-name">content</span>="<span class="html-attrib
		> ute-value">text/html; charset=windows-1252</span>" /&gt;</span></td></tr><tr><td
		>  class="line-number" value="6"></td><td class="line-content"><span class="html-t
		> ag">&lt;meta <span class="html-attribute-name">name</span>="<span class="html-at
		> tribute-value">description</span>" <span class="html-attribute-name">content</sp
		.
		.
		.
		**All output as expected
Error Case 1 (file doesn't exist):
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   GET rto HTTP/1.0
		>HTTP/1.0 404 File Not Found
		>Date: Thu Apr 02 17:00:25 EDT 2015
		>Server: FileHTTPServer
		>Content-length: 117
		>Content-type: text/html; charset=utf-8
		>
		><HTML>
		><HEAD><TITLE>File Not Found</TITLE>
		></HEAD>
		><BODY><H1>HTTP Error 404: File Not Found</H1>
		></BODY></HTML>
		**All output as expected
Error Case 2 (invalid request type):
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   POST index.html HTTP/1.0
		><HTML>
		><HEAD><TITLE>Not Implemented</TITLE>
		></HEAD>
		><BODY><H1>HTTP Error 501: Not Implemented</H1>
		></BODY></HTML>
		**All output as expected
Example Output from Server Command Prompt:
		>Apr 02, 2015 4:50:22 PM FileHTTPServer start
		>INFO: Accepting connections on port 80
		>Apr 02, 2015 4:50:22 PM FileHTTPServer start
		>INFO: Document Root: Directory
		>Apr 02, 2015 4:50:35 PM FileHTTPServer$RequestProcessor call
		>INFO: /0:0:0:0:0:0:0:1:54364 GET index.html HTTP/1.0

		The first three test cases demonstrate that the search option that is specified 
		in the requirements is working correctly, different files can be requested of the
		server, not just a signle one as seen in the text example.  These cases use the 
		different webpages, with embedded multimedia and are displayed.  The results that 
		the program displays were tested against manual inspection performed outside the 
		program on sources.  All manual inspections matched up with the program results.
		The fourth test case tests the program when provided with an invalid file, while
		the fifth shows the result when a method other than GET is attempted.
		These were included as very minimal error handling tests.

		**Raw output is sent when a file is successfully requested in order to allow
		different types of files to be served.
