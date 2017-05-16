// Reference used:https://docs.oracle.com/javase/tutorial/rmi/overview.html

/*
Title: LargestPrimeNumber.java
Description: Remote Interface of a client/server system using Java RMI, such that the client 
	     can ask the server to generate a largest prime number within a range set by 
	     the user on the client.

Date: April 17th, 2015
Author: Dan Bonham
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

package compute;

// imports all libraries necessary for interface
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigInteger;

public interface LargestPrimeNumber extends Remote { // remote interface
	BigInteger getLargestPrime(BigInteger range) throws RemoteException; // remote method
} // LargestPrimeNumber interface
