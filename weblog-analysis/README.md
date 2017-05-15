MyPooledWeblog.java - reads a weblog and tallies statistics on the log entries based on an input option choice.  Executed using the command line ‘java MyPooledWeblog logname option’ where option is one of the following: 1 = count number of accesses by each remotehost, 2 = count total bytes transmitted, and 3 = count total bytes by remotehost.

- Program Purpose:
		Introduces the Java Networking concepts of streams, threads, futures, and weblogs.
- Compile: javac MyPooledWeblog.java
- Execution: java MyPooledWeblog "access_log/access_log" <option>
- Notes: No user input is necessary, everything for this program is hardcoded
- Classes: 
		MyPooledWeblog - contains everything necessary for proper program execution, as 
				specified in the program requirements.  This includes the main method, as
				well as one method for each command line option - accessesByRemoteHosts(),
				totalBytes(), and bytesByRemoteHost().  Additionally, there is a nested class
				LogEntry used to handle individual log entries.
		LogEntry - nested in MyPooledWeblog - instance created for each individual log
				entry, associates a future to the entry and adds the class instance to queue.
Variables:
		NUM_THREADS - private final static int - used to set number of threads used (1500).
		executor - ExecutorService - a fixed thread pool used to facilitate program execution.
		results - Queue<LogEntry> - queue of LogEntry instances processed by program
		task - LookupTask - used to process/store string log entries preparing for manipulation
		future - Future<String> - stores futures that are associated with each entry
		result - LogEntry - stores each entry and its associated futrure, added to queue
		remoteHosts - Map<String, Integer> - used to store unique remoteHosts in web log
		entryCount - int - used to store count of entries in web log
		entry - String - used to store future associated with entry after processing
		index - int - used in string manipulation, stores position for processing
		address - String -  gets/stores address of host from log entry
		ltotalBytes - long - used to store the total bytes in the weblog
		entryBytes - long - used to store the total bytes in each entry
		stringBytes - String - used for processing entries, determines whether entry included
				any transmission.
		original - String - used to store the original entries in the web log
		future - Future<String> - used to store futures associated with log entries
