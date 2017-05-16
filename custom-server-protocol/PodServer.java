/*
Title: PodServer.java
Description: Server that interacts with clients via telnet and includes the following key 
	     interactions: client connects to server, server responds with welcome message
	     then a list of available poems and instructions for the client.  Waits for
	     client poem selection and then responds by either displaying the poem for a 
             valid request or by displaying an error message for an invalid request.  The 
	     server then terminates.

	     A textfile containing the poems to be served is provided at the command line.

Date: April 16th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// Program written exclusively through the use of "Java Network Programming" by Elliotte Rusty Harold

// All imports necessary for program
import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.String;

public class PodServer {
	private final static Logger auditLogger = Logger.getLogger("requests"); // logging capabilities
	private final static Logger errorLogger = Logger.getLogger("errors"); // for errors
	private final static Logger consoleLogger = Logger.getLogger("console"); // for errors

	private final File poems; // file with poems to serve from
	private final int port; // port for server
	private final String encoding; // encoding scheme for file

	// constructor
	public PodServer (File poems, int port, String encoding) throws IOException {
		// assignes variables to info provided
		this.poems = poems;
		this.port = port;
		this.encoding = encoding;
	} // NonblockingFileHTTPServer()

	public void start() { // starts server running
		ExecutorService pool = Executors.newFixedThreadPool(100); // thread pool
		try (ServerSocket server = new ServerSocket(this.port)) { // serverSocket
			// logs to the console at startup
			consoleLogger.info("Accepting connections on port " + server.getLocalPort());
			consoleLogger.info("From file: " + poems);

			while(true) { // runs until program is terminated
				try {
					Socket connection = server.accept(); // accepts client connection
					pool.submit(new RequestProcessor(poems, "1", connection)); // submits request
				} catch (IOException ex) { // logs to error file
					errorLogger.log(Level.WARNING, "Exception accepting connection", ex);
				} catch (RuntimeException ex) { // logs to error file
					errorLogger.log(Level.SEVERE, "Unexpected error" + ex);
				} // catch
			} // while
		} catch (IOException ex) { // logs to error file
			errorLogger.log(Level.SEVERE, "Could not start server", ex);
		} // catch
	} // run()

	public static void main(String[] args) {
		
			try {
				// Sets up FileHandlers for auditLogger and errorLogger
				FileHandler requestHandler = new FileHandler("C:/users/Dan/mingw/COMP 348/Assignment3/requests", true);
				FileHandler errorHandler = new FileHandler("C:/users/Dan/mingw/COMP 348/Assignment3/errors", true);
				MyFormatter formatter = new MyFormatter();
				requestHandler.setFormatter(formatter); // specifies formatting
				errorHandler.setFormatter(new SimpleFormatter()); // specifies formatting
				errorHandler.setEncoding("UTF-8"); // specifies encoding
				auditLogger.addHandler(requestHandler); // adds custom handler
				errorLogger.addHandler(errorHandler); // adds custom handler
			}catch (IOException ex) {
				System.out.println(ex);
			}

		if(args.length == 0) { // checks for arguments from command line
			System.out.println("Usage: java PodServer file port");
			return;
		} // if

		File poems; // will store root of files
		try {
			poems = new File(args[0]); // assigned to that provided at command line
			if (!poems.exists()) { // checks to see if file provided at command line exists
				System.out.println("Provided file does not exist.");
				System.exit(0); // exits if invalid poem file
			} // if
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java PodServer file port");
			return;
		}

		try {
			// set the port to listen on
			int port;
			try {
				port = Integer.parseInt(args[1]); // assigns port specified at command line
				if(port < 1 || port > 65535) port = 80; // checks port for validity
			} catch(RuntimeException ex) {
				port = 80; // defaults to 80 if problems encountered
			} // catch

			String encoding = "UTF-8"; // default encoding
			if(args.length > 2) encoding = args[2]; // gets specified encoding if provided

			// sets up and starts server instance
			PodServer server = new PodServer(poems, port, encoding);
			server.start();
		} catch(IOException ex) { // logs to error file
			errorLogger.severe(ex.getMessage());;
		} // catch
	} // main()

	private static class RequestProcessor implements Callable<Void> { // handles file requests
		// used to specify the date used in each log entry
		private static final DateFormat df = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");

		private File poems; // file that contains poems
		private String choice = "1"; // comtains clients choice of poems
		private Socket connection; // connection to client

		// constructor
		public RequestProcessor(File poems, String choice, Socket connection) {
			this.poems = poems; // sets root directory

			if(choice != null) this.choice = choice; // sets file name
			this.connection = connection; // sets connection
		} // RequestProcessor

		@Override
		public Void call() { // performs file service
			try {
				Date now = new Date(); // gets current date/time for log entry
				String address = connection.getInetAddress().toString(); // gets address for log entry
				// string holds log entry, built gradually piece by piece
				String logEntry = address.substring(1) + ' ' + "- - [" + df.format(now) + "] \"";
				// sets up file/input/output streams
				BufferedReader inFile = new BufferedReader(new FileReader(poems));
				OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
				Writer out = new OutputStreamWriter(raw);
				Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "US-ASCII");
				
				// displays welcome and selection information to client, along with instruction
				out.write("Welcome to Poem of the Day (PoD)\r\n\n"
					+ "Today's available poems are:\r\n"
					+ "Love Apart\r\nI Love You\r\nMask\r\nUnseen\r\nWelcome To Hell\r\n\n"
					+ "Please choose your poem by entering the title:\r\n\n");
				out.flush();

				StringBuilder requestLine = new StringBuilder(); // used to build client request
				while(true) { // continues until request fully input
					int c = in.read(); // gets char by char
					if(c == '\r' || c == '\n') break; // checks for end of request
					requestLine.append((char) c); // appends each char
				} // while

				String get = requestLine.toString(); // converts request to a string

				logEntry += "request for: " + get + "\""; // adds to log entry

				String line; // inputs/contains each line as its read from the file
				StringBuilder poem = new StringBuilder(""); // used to store/build selected poem
				boolean invalid = true; // used to flag whether poem selection is valid
				while ((line = inFile.readLine()) != null) { // reads file line by line
					if (line.equals(get)) { // checks titles for a match with client choice
						invalid = false; // sets to false if a match occurs
						while (!(line = inFile.readLine()).equals("*****")) // marks end of a poem
							poem.append(line + "\r\n"); // appends each line from selected poem
					} // if
				} // while
				if (invalid) { // displays error message if choice is invalid
					poem.append("Invalid choice, must choose from choices listed.");
					logEntry += " unsuccesful\n"; // adds to log entry
				} else
					logEntry += " succesful\n"; // adds to log entry
				out.write(poem.toString()); // writes result to client
				auditLogger.info(logEntry); // logs to requests file
				out.flush(); // flushes stream
			} catch(IOException ex) {
				errorLogger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
			} finally {
				try {
					connection.close(); // closes connection to client
				} catch (IOException ex) {}
			}
			return null;
		} // call()
	} // RequestProcessor class
} // FileHTTPServer class

class MyFormatter extends java.util.logging.Formatter {
	public String format(LogRecord record) { // takes log entry and formats for logging
		StringBuilder builder = new StringBuilder(formatMessage(record));
		return (builder.toString());
	} // format()
} // MyFormatter class