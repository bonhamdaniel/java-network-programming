// Reference used:https://docs.oracle.com/javase/tutorial/rmi/overview.html

/*
Title: LargestPrimeNumberRMIServer.java
Description: Server side of a client/server system using Java RMI, such that the client 
	     can ask the server to generate a largest prime number within a range set by 
	     the user on the client.

Date: April 17th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

// imports all libraries necessary for program execution
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.math.BigInteger;
import compute.LargestPrimeNumber;

public class LargestPrimeNumberRMIServer implements LargestPrimeNumber {

	public LargestPrimeNumberRMIServer() { // server constructor
		super();
	} // LargestPrimeNumberRMIServer() constructor

	// implementation for remote method specified in LargestPrimeNumber interface
	public BigInteger getLargestPrime(BigInteger range) {
		boolean prime; // used to test for prime
		BigInteger max = BigInteger.valueOf(0); // used to store max prime in given range

		try {
			for(long i = 2; i < range.longValue(); i++) { // iterates from 2 to 1000 to test for primes
				prime = true; // initially sets prime true
				for(long j = 2; j < i; j++) { // loops from 2 to current number to check for divisibility
					if(i % j == 0) // a modulus of 0 means the number isn't prime
						prime = false; // sets bool to false as number isn't prime
				} // for(j)
				if(prime) // tests whether current i is primw
					max = BigInteger.valueOf(i); // assigns new max value
			} // for(i)

			return max; // returns max primw in given range
		} catch (Exception e) {
			return BigInteger.valueOf(0);
		} // catch
	} // getLargestPrime() method

	public static void main(String[] args) {
		// creates and installs a security manager, protects access to system resources
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			String name = "LargestPrimeNumber"; // name for object
			LargestPrimeNumber server = new LargestPrimeNumberRMIServer(); // instance of server
			LargestPrimeNumber stub = (LargestPrimeNumber) UnicastRemoteObject.exportObject(server, 0); // exports server to RMI runtime
			Registry registry = LocateRegistry.getRegistry(); // gets RMI registry
			registry.rebind(name, stub); // adds name to RMI registry running
			System.out.println("LargestPrimeNumberRMIServer bound"); // displays successful binding
		} catch (Exception e) { // catches any RemoteExceptions experienced during execution
			System.err.println("LargestPrimeNumberRMIServer exception:");
			e.printStackTrace();
		} // catch
	} // main() method
} // LargestPrimeNumberRMIServer class
