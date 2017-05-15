SourceViewer.java - source viewer that incorporates a search function. When provided with a server URL or IP and a search string, examines the server for instances of the string and prints only those lines that contain the string. Executes using the command line "java SourceViewer [URL/IP] [search string]" where URL/IP is a valid URL or IP address and search string is the search string in double quotes.

- Program Purpose:
		Introduces the Java Networking concepts of URLs, URLConnections, and parsing.
Compile: javac SourceViewer.java
Execution: java SourceViewer URL/IP <search string>
Notes: No user input is necessary outside of command line , everything for this program 
		is hardcoded
Classes: 
		SourceViewer - contains all necessary for proper program execution.  In this program
				all execution is done right from the main method.
Variables:
		u - URL - used to store the URL provided at the command line
		uc - HttpURLConnection - used to open/store connection to provided URL
		raw - InputStream - used to store raw input stream from HttpURLConnection
		buffer - InputStream - used to store a buffer for the InputStream
		reader - Reader - used to store a reader set up on the buffer
		c - int - used to store the int version of each character read in
		line - String - used to store each line input from the URL
