// COMP348 Assignment 3: LargestPrimeNumber.java
// Reference used:https://docs.oracle.com/javase/tutorial/rmi/overview.html

/*
Title: LargestPrimeNumber.java
Description: Remote Interface of a client/server system using Java RMI, such that the client 
			 can ask the server to generate a largest prime number within a range set by 
			 the user on the client.

Date: April 17th, 2015
Author: Dan Bonham AU#2312040
Version: 1.0
Copyright: 2015 Daniel R. Bonham
*/

/*
I declare that this assignment is my own work and that all material previously
written or published in any source by any other person has been duly
acknowledged in the assignment. I have not submitted this work, or a
significant part thereof, previously as part of any academic program. In
submitting this assignment I give permission to copy it for assessment
purposes only.
*/

/*
DOCUMENTATION
Program Purpose:
		Introduces the Java Networking concepts RMI.
Compile: javac compute\LargestPrimeNumber.java
Execution: n/a
Notes:  jar file created to be shared with other RMI program components.
		(jar cvf compute.jar compute\*.classes)
Classes: 
		LargestPrimeNumber - interface for the remote object.
Variables:
		range - BigInteger - used to store the range in which to find the largest prime
*/

/*
TEST PLAN
Normal Case 1:
		***All testing performed in client side documentation***
*/
package compute;

// imports all libraries necessary for interface
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.math.BigInteger;

public interface LargestPrimeNumber extends Remote { // remote interface
	BigInteger getLargestPrime(BigInteger range) throws RemoteException; // remote method
} // LargestPrimeNumber interface