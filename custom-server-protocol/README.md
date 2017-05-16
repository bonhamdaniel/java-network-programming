PodServer.java - server that interacts with clients via telnet and includes the following key interactions: client connects to server, server responds with welcome message then a list of available poems and instructions for the client.  Waits for client poem selection and then responds by either displaying the poem for a valid request or by displaying an error message for an invalid request.  The server then terminates.

- Program Purpose:
		Introduces the Java Networking concepts of the ServerSocket and network application
		protocols.
Compile: javac PodServer.java
Execution: java PodServer <file of poems> <port> 
Notes:  A telnet client is used to test the server.  When a client connects, the server
		displays the poems to the client and accepts choice.  This is further documented in
		the test plan.  Each request is logged to a file called "requests" created in the
		program folder.  A protocol (finite state machine) is included in the program zip.
Classes: 
		PodServer - the bulk of the program, including main, which sets up a ServerSocket
		 	for a given file of poems, and runs on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.  Includes Logger instances.
		RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.  This is where communication protocol is implemented.
		MyFormatter - extends java.util.logging.Formatter.  Formats log entries to 
			specified file format.
Variables:
		auditLogger - Logger - used to log server requests to "requests" file
		errorLogger - Logger - used to log server errors to "errors" file
		consoleLogger - Logger - used to log server status to the console
		poems - File - holds the file containing the poems served by the PodServer
		rootDirectory - File - used to store the directory the server is to service
		port - int - used to store the port that the server will run on
		encoding - String - used to store the encoding used for the requested file
		pool - ExecutorService - used to handle threading, which is used for requests
		server - ServerSocket - used in PodServer start() to handle client requests
		server - PodServer - used in main as an instance of the implemented server
		connection - Socket - used to store the connection between client/server
		inFile - BufferedFileReader - used to input contents of poem file
		raw - OutputStream - used to store a raw output stream to the client, allows 
			different file types to be serviced
		out - Writer - used to store a Writer for the output stream
		in = InputStream - used to store an input stream from server
		requestLine - StringBuilder - used to store client request
		c - int - used to store each individual char as it is read from client request
		get - String - used to store a string version of client request
		poem - StringBuilder - used to build/display requested poem
		now - Date - used to store the current date
		requestHandler - FileHandler - used for Logger that logs requests (auditLogger)
		errorHandler - FileHandler - used for Logger that logs errors (errorLogger)
		formatter - MyFormatter - used to format log entries
		df - DateFormat - used to store/format the dates included in the log entries
		logEntry - String - used to store the ibformation included in a log entry
		builder - StringBuilder - used to store/build the formatted log entry
		choice - String - used to store the poem name requested by the client
		address - String - used to store the address of the client for logging
		invalid - boolean - used to flag whether a requested poem has been found
