/*
Title: MyPooledWeblog.java
Description: Reads a weblog and tallies statistics on the log entries based on an input 
	     option choice.  Executed using the command line ‘java MyPooledWeblog logname 
             option’ where option is one of the following:

	     1 = count number of accesses by each remotehost; 
	     2 = count total bytes transmitted; 
	     3 = count total bytes by remotehost.

Date: January 25th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/


// Program written exclusively through the use of "Java Network Programming" by Elliotte Rusty Harold

// All imports necessary for program		
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MyPooledWeblog {
	private final static int NUM_THREADS = 1500; // determines threads used for execution

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS); // Executor service
		Queue<LogEntry> results = new LinkedList<LogEntry>(); // Queue for execution

		// Opens web log provided on command line and reads input
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));) {
			for (String entry = in.readLine(); entry != null; entry = in.readLine()) { // continues until file complete
				LookupTask task = new LookupTask(entry); // creates LookupTask for each entry
				Future<String> future = executor.submit(task); // gets futurefor each task
				LogEntry result = new LogEntry(entry, future); // associates task/future in LogEntry instance
				results.add(result); // adds to queue
			}
		} catch(IOException ex) { // catches non-existant file
			System.out.println("Sorry, file was not found.  Please confirm file and try again.");
			System.exit(1);
		}

		// switch determines option provided on command line
		switch(args[1]) {
			case("1"): 	accessesByRemoteHosts(results);
					 	break;
			case("2"): 	totalBytes(results);
						break;
			case("3"):	bytesByRemoteHost(results);
						break;
			default:	System.out.println("Invalid option - must be 1, 2, or 3");
						break;
		}
		executor.shutdown();
	} // main


	// Method called for option 1
	public static void accessesByRemoteHosts(Queue<LogEntry> results) {
		Map<String, Integer> remoteHosts = new ConcurrentHashMap<String, Integer>(); // stores unique hosts
		int entryCount = 0; // counts entries in log
		for (LogEntry result : results) { // cycles through all of queue counting accesses by host
			try {
				String entry = result.future.get();
				int index = (entry).indexOf(' ');
				String address = entry.substring(0, index);
				//System.out.println(entry);
				if(remoteHosts.containsKey(address)) // host already in list
					remoteHosts.put(address, remoteHosts.get(address) + 1);
				else // new host
					remoteHosts.put(address, 1);
			} catch(InterruptedException | ExecutionException e) {
				System.out.println(e);
			} // catch
		} // for
		for(String host : remoteHosts.keySet()) { // cycles through and displays results
			System.out.println(remoteHosts.get(host)+ "\t" + host);
			entryCount += remoteHosts.get(host); // adds to count of total entries
		} // for
		System.out.println("Total entries: " + entryCount);
	} //accessesByRemoteHosts


	// Method called for option 2
	public static void totalBytes(Queue<LogEntry> results) {
		long totalBytes = 0; // stores total bytes transmitted in complete log
		long entryBytes = 0; // stores bytes for each entry
		int entryCount = 0; // counts entries in log
		for (LogEntry result : results) { // cycles through all entries
			try {
				String entry = result.future.get();
				int index = (entry).lastIndexOf(' ');
				String stringBytes = entry.substring(index + 1);
				entryCount++; // increments count of entries
				if (!stringBytes.equals("-")) // determines if current entry included transmission
					entryBytes = Long.parseLong(stringBytes); // gets bytes from current entry
				totalBytes += entryBytes; // adds current entry bytes to total
			} catch(InterruptedException | ExecutionException e) {
				System.out.println(e);
			} // catch
		} // for
		// Displays results
		System.out.println("The total bytes transmitted was " + totalBytes);
		System.out.println("The total entries examined was " + entryCount);
	} // totalBytes

	// Method called for option 3
	public static void bytesByRemoteHost(Queue<LogEntry> results) {
		Map<String, Long> remoteHosts = new ConcurrentHashMap<String, Long>(); // stores unique hosts
		int entryCount = 0; // stores count of entries in log
		long entryBytes = 0; // stores bytes in each entry
		long totalBytes = 0; // stores total bytes in log
		for (LogEntry result : results) { // cycles through queue and performs computations
			try {
				String entry = result.future.get();
				int index = (entry).indexOf(' ');
				String address = entry.substring(0, index);
				int lastIndex = (entry).lastIndexOf(' ');
				String stringBytes = entry.substring(lastIndex + 1);
				entryCount++;
				if (!stringBytes.equals("-")) // determines if current entry involved transmission of bytes
					entryBytes = Long.parseLong(stringBytes);
				if(remoteHosts.containsKey(address)) // adds to existant host total
					remoteHosts.put(address, remoteHosts.get(address) + entryBytes);
				else // adds new host
					remoteHosts.put(address, entryBytes);
			} catch(InterruptedException | ExecutionException e) {
				System.out.println(e);
			} // catch
		} // for
		for(String host : remoteHosts.keySet()) {
			System.out.println(remoteHosts.get(host)+ "\t" + host);
			totalBytes += remoteHosts.get(host);
		} // for
		// Displays results
		System.out.println("Total bytes transmitted was " + totalBytes);
		System.out.println("The total entries examined was " + entryCount);
	} // bytesByRemoteHost

	private static class LogEntry {
		String original; // stores original individual entries
		Future<String> future; // future associated with each entry


		// Constructor
		LogEntry(String original, Future<String> future){
			this.original = original;
			this.future = future;
		} // constructor
	} // class LogEntry
} // class MyPooledWeblog