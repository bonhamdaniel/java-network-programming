// Reference used:https://docs.oracle.com/javase/tutorial/rmi/overview.html

/*
Title: LargestPrimeNumberRMIClient.java
Description: Client side of a client/server system using Java RMI, such that the client 
	     can ask the server to generate a largest prime number within a range set by 
	     the user on the client.

Date: April 17th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// imports all libraries necessary for program
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigInteger;
import compute.LargestPrimeNumber;

public class LargestPrimeNumberRMIClient { // RMI client
	public static void main (String[] args) {
		// creates and installs a security manager, protects access to system resources
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			String name = "LargestPrimeNumber"; // name for lookup of object
			Registry registry = LocateRegistry.getRegistry(args[0]); // synthesize remote reference to registry at server
			LargestPrimeNumber getPrime = (LargestPrimeNumber) registry.lookup(name); // looks up remote object by name
			BigInteger prime = getPrime.getLargestPrime(BigInteger.valueOf((Integer.parseInt(args[1])))); // passes specified range to remote method
			System.out.println(prime); // displays result
		} catch (Exception e) { // catches any RemoteExceptions experienced during execution
			System.err.println("LargestPrimeNumberRMIClient exception:");
			e.printStackTrace();
		} // catch
	} // main method
} // LargestPrimeNumberRMIClient class