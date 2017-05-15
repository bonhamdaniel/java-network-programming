/*
Title: SourceViewer.java
Description: Source viewer that incorporates a search function. When provided with a 
	     server URL or IP and a search string, examines the server for instances of 
	     the string and prints only those lines that contain the string. 

	     Executes using the command line java SourceViewer URL/IP search string 
	     where URL/IP is a valid URL or IP address and search string is the search 
	     string in double quotes.

Date: January 25th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// Program written exclusively through the use of "Java Network Programming" by Elliotte Rusty Harold

// All imports necessary for program
import java.io.*;
import java.net.*;

public class SourceViewer {
	public static void main(String[] args) {
		if (args.length > 1) { // ensures both arguments are provided
			try {
				URL u = new URL(args[0]); // gets URL provided at command line
				HttpURLConnection uc = (HttpURLConnection) u.openConnection(); // opens http connection to URL
				try (InputStream raw = uc.getInputStream()){ // gets raw input stream from connection
					InputStream buffer = new BufferedInputStream(raw); // adds buffer to input stream
					Reader reader = new InputStreamReader(buffer); // attaches reader to buffer
					int c; // used to store each character as read from reader
					String line = ""; // used to store each complete line from reader
					while ((c = reader.read()) != -1) { // loops until no more data
						if(c != '\n') // while not end of line
							line += (char)c; // builds line char by char
						else { // when at end of a line
							//if(line.indexOf(args[1]) > 0)
							if(line.contains(args[1])) // tests to see if line contains search string
								System.out.print(line + (char)c); // prints lines with search string
							line = ""; // resets string
						} // else
					} // while
				} // try
			} catch(MalformedURLException e) { // catches invalid URL
				System.err.println(args[0] + " is not a parseable URL");
			} catch(IOException e) { // catches other exceptions
				System.err.println(e);
			} // catch
		} // if
		else { // when both arguments have not been provided at the command line
			System.out.println("Usage is: java SourceViewer <URL> <search string>");
		} // else
	} // main
} // class SourceViewer