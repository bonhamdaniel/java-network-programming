// Reference used: http://kodejava.org/how-do-i-create-a-custom-logger-formatter/

/*
Title: HTTPServerWLog.java
Description: Beginning with the FileHTTPServer, adds full logging capabilities to the 
	     program. The log file produced includes date and time, IP of requesting 
	     client, HTTP request, and HTTP response.

Date: April 12th, 2015
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

public class HTTPServerWLog {
	private final static Logger auditLogger = Logger.getLogger("requests"); // logging capabilities
	private final static Logger errorLogger = Logger.getLogger("errors"); // for errors
	private final static Logger consoleLogger = Logger.getLogger("console"); // for errors

	//private final static Logger auditLogger = Logger.getLogger("requests"); // for successful requests

	private final File rootDirectory; // stores root of served files
	private final int port; // port for server
	private final String encoding; // encoding scheme for file

	// constructor
	public HTTPServerWLog (File rootDirectory, int port, String encoding) throws IOException {
		if(!rootDirectory.isDirectory()) { // checks to make sure directory provided is valid
			throw new IOException(rootDirectory + " does not exist as a directory");
		}
		// assignes variables to info provided
		this.rootDirectory = rootDirectory;
		this.port = port;
		this.encoding = encoding;
	} // NonblockingFileHTTPServer()

	public void start() { // starts server running
		ExecutorService pool = Executors.newFixedThreadPool(100); // thread pool
		try (ServerSocket server = new ServerSocket(this.port)) { // serverSocket
			// logs to the console at startup
			consoleLogger.info("Accepting connections on port " + server.getLocalPort());
			consoleLogger.info("Document Root: " + rootDirectory);

			while(true) { // runs until program is terminated
				try {
					Socket connection = server.accept(); // accepts client connection
					pool.submit(new RequestProcessor(rootDirectory, "index.html", connection)); // submits request
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
				FileHandler requestHandler = new FileHandler("C:/users/Dan/mingw/COMP 348/Assignment2/requests", true);
				FileHandler errorHandler = new FileHandler("C:/users/Dan/mingw/COMP 348/Assignment2/errors", true);
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
			System.out.println("Usage: java HTTPServerWLog file port");
			return;
		} // if

		File docroot; // will store root of files
		try {
			docroot = new File(args[0]); // assigned to that provided at command line
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java HTTP docroot port");
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
			HTTPServerWLog server = new HTTPServerWLog(docroot, port, encoding);
			server.start();
		} catch(IOException ex) { // logs to error file
			errorLogger.severe(ex.getMessage());;
		} // catch
	} // main()

	private static class RequestProcessor implements Callable<Void> { // handles file requests
		// used to specify the date used in each log entry
		private static final DateFormat df = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");

		private File rootDirectory; // root of file directory
		private String indexFileName = "index.html"; // default filename if not provided
		private Socket connection; // connection to client

		// constructor
		public RequestProcessor(File rootDirectory, String indexFileName, Socket connection) {
			if(rootDirectory.isFile()) { // similar check to that above
				throw new IllegalArgumentException("rootDirectory must be a directory, not a file");
			}
			try {
				rootDirectory = rootDirectory.getCanonicalFile(); // gets path
			} catch(IOException ex) {}
			this.rootDirectory = rootDirectory; // sets root directory

			if(indexFileName != null) this.indexFileName = indexFileName; // sets file name
			this.connection = connection; // sets connection
		} // RequestProcessor

		@Override
		public Void call() { // performs file service
			String root = rootDirectory.getPath(); // gets path to directory

			try {
				Date now = new Date(); // gets current date/time for log entry
				String address = connection.getInetAddress().toString(); // gets address for log entry
				// string holds log entry, built gradually piece by piece
				String logEntry = address.substring(1) + ' ' + "- - [" + df.format(now) + "] \"";
				// sets up input/output streams
				OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
				Writer out = new OutputStreamWriter(raw);
				Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "US-ASCII");
				String method = "y";
				while (!method.equals("EXIT")) {
					StringBuilder requestLine = new StringBuilder(); // used to build client request
					while(true) { // continues until request fully input
						int c = in.read(); // gets char by char
						if(c == '\r' || c == '\n') break; // checks for end of request
						requestLine.append((char) c); // appends each char
					} // while

					String get = requestLine.toString(); // converts request to a string

					String[] tokens = get.split("\\s+"); // splits request into pieces
					method = tokens[0]; // gets method type of request
					logEntry += method + " " + root + "\\" + tokens[1]; // adds to log entry
					String version = ""; // sets default version value
					if(method.equals("GET")) { // services GET requests
						String fileName = tokens[1]; // gets filename from request
						if(fileName.endsWith("/")) fileName += indexFileName; // adds index if directory provided
						String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName); // gets content type of file
						if(tokens.length > 2) version = tokens[2]; // gets version if provided

						File theFile = new File(rootDirectory, fileName.substring(0, fileName.length())); // gets file with path
						if(theFile.canRead() && theFile.getCanonicalPath().startsWith(root)) { // checks file for validity
							byte[] theData = Files.readAllBytes(theFile.toPath()); // gets byte data from file
							if(version.startsWith("HTTP/")) { // checks version of requestor
								sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length); // sends header
							}
							// checks whether version included in request, adds to log appropriately
							if (!version.equals("")) logEntry += " " + version + "\" ";
							else logEntry += "\" ";
							logEntry += "200 " + theData.length + "\n"; // adds to log entry
							auditLogger.info(logEntry); // logs to requests file
							raw.write(theData); // writes raw data to client
							raw.flush(); // flushes stream
						} else { // builds response for when a file not found
							String body = new StringBuilder("<HTML>\r\n")
								.append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
								.append("</HEAD>\r\n")
								.append("<BODY>")
								.append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
								.append("</BODY></HTML>\r\n").toString();
							if(version.startsWith("HTTP/")) {
								sendHeader(out, "HTTP/1.0 404 File Not Found", "text/html; charset=utf-8", body.length());
							}
							// checks whether version included in request, adds to log appropriately						
							if (!version.equals("")) logEntry += " " + version + "\" ";
							else logEntry += "\" ";
							logEntry += "404 " + 0 + "\n"; // adds to log entry
							auditLogger.info(logEntry); // logs to requests file
							out.write(body); // writes response to client
							out.flush(); // flushes stream
						}
					} else { // builds response when request method not implemented
						String body = new StringBuilder("<HTML>\r\n")
							.append("<HEAD><TITLE>Not Implemented</TITLE>\r\n")
							.append("</HEAD>\r\n")
							.append("<BODY>")
							.append("<H1>HTTP Error 501: Not Implemented</H1>\r\n")
							.append("</BODY></HTML>\r\n").toString();
						if(version.startsWith("HTTP/")) {
							sendHeader(out, "HTTP/1.0 501 Not Implemented", "text/html; charset=utf-8", body.length());
						}
						// checks whether version included in request, adds to log appropriately						
						if (!version.equals("")) logEntry += " " + version + "\" ";
						else logEntry += "\" ";
						logEntry +=  "501 " + 0 + "\n"; // adds to log entry
						auditLogger.info(logEntry); // logs to requests file
						out.write(body); // writes response to client
						out.flush(); // flushes stream
					}
					while(true) { // continues until request fully served
						int c = in.read(); // gets char by char
						if(c == '*') break; // checks for end of file served
					} // while
					raw.write('\n');
					raw.flush();
				}
			} catch(IOException ex) {
				errorLogger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
			} finally {
				try {
					connection.close(); // closes connection to client
				} catch (IOException ex) {}
			}
			return null;
		} // call()

		// builds and sends header for response
		private void sendHeader(Writer out, String responseCode, String contentType, int length) throws IOException {
			Date now = new Date(); // gets current datestamp
			out.write(responseCode + "\r\n"); // adds response code
			out.write("Date: " + now + "\r\n"); // adds time
			out.write("Server: HTTPServerWLog\r\n"); // adds server
			out.write("Content-length: " + length + "\r\n"); // adds content length
			out.write("Content-type: " + contentType + "\r\n\r\n"); // adds content type
			out.flush(); // flushes stream
		} // sendHeader()
	} // RequestProcessor class
} // FileHTTPServer class

class MyFormatter extends java.util.logging.Formatter {
	public String format(LogRecord record) { // takes log entry and formats for logging
		StringBuilder builder = new StringBuilder(formatMessage(record));
		return (builder.toString());
	} // format()
} // MyFormatter class