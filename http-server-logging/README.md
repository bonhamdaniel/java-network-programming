HTTPServerWLog.java - beginning with the FileHTTPServer, adds full logging capabilities to the program. The log file produced includes date and time, IP of requesting client, HTTP request, and HTTP response.

- Program Purpose:
		Introduces the Java Networking concept of Logging.
Compile: javac HTTPServerWLog.java
Execution: java HTTPServerWLog <root directory> <port> 
Notes:  A telnet client is used to test the server.  GET commands are sent from telnet for
		specified files to be searched for on the server.  This is further documented in
		the test plan.  Each request is logged to a file called "requests" created in the
		program folder.
Classes: 
		HTTPServerWLog - the bulk of the program, including main, which sets up a HTTP
			server for a given directory of files, and running on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.  Includes Logger instances.
		RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.
		MyFormatter - extends java.util.logging.Formatter.  Formats log entries to 
			specified file format.
Variables:
		auditLogger - Logger - used to log server requests to "requests" file
		errorLogger - Logger - used to log server errors to "errors" file
		consoleLogger - Logger - used to log server status to the console
		rootDirectory - File - used to store the directory the server is to service
		port - int - used to store the port that the server will run on
		encoding - String - used to store the encoding used for the requested file
		pool - ExecutorService - used to handle threading, which is used for requests
		docroot - File - used to store directory specified from command line
		server - HTTPServerWLog - used to an instance of the implemented server
		indexFileName - String - used to store the index file for the directory
		connection - Socket - used to store the connection between client/server
		root - String - used to store the directory of the requested file
		raw - OutputStream - used to store a raw output stream to the client, allows 
			different file types to be serviced
		out - Writer - used to store a Writer for the output stream
		in = InputStream - used to store an input stream from server
		requestLine - StringBuilder - used to store client request
		c - int - used to store each individual char as it is read from client request
		get - String - used to store a string version of client request
		tokens - String[] - used to store a tokenized version of client request
		method - String - used to store the type of client request
		version - String - used to store the version of the client
		fileName - String - used to store the file requested by the client
		contentType - String - used to store the contentType of the file
		theFile - File - used to store the File requested by the client
		theData - byte[] - used to store the data from the requested file
		body - String - used to store the message back when a file is not found
		now - Date - used to store the current date
		requestHandler - FileHandler - used for Logger that logs requests (auditLogger)
		errorHandler - FileHandler - used for Logger that logs errors (errorLogger)
		formatter - MyFormatter - used to format log entries
		df - DateFormat - used to store/format the dates included in the log entries
		logEntry - String - used to store the ibformation included in a log entry
		builder - StringBuilder - used to store/build the formatted log entry
