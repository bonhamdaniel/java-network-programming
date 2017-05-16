Client & Server using Java RMI - system using Java RMI, such that the client can ask the server to generate a largest prime number within a range set by the user on the client.

Server Application:
- ***rmiregistry must be running before server starts*** 
	- type "start rmiregistry" at command line
- ***all necessary jars and classes must be in network accessible folder***
	- classes/compute.jar
	- classes/LargestPrimeNumberRMIServer.class
	- classes/LargestPrimeNumberRMIClient.class
	- classes/compute/LargestPrimeNumber.class
- Program Purpose:
		Introduces the Java Networking concepts RMI.
- Compile: javac -cp c:\home\public_html\classes\compute.
		 jar LargestPrimeNumberRMIServer.java
- Execution: java -cp %cd%;c:\home\public_html\classes\ -Djava.rmi.server.codebase=file:/c:\home\public_html\classes\ -Djava.security.policy=server.policy LargestPrimeNumberRMIServer
- Notes:  The client side of the program is run from a different command prompt.
- Classes: 
	- LargestPrimeRMIServer - includes all server functionality. Only has the constructor
		and the main method.
- Variables:
	- prime - boolean - used to store value that flags whether a number is prime
	- max - BigInteger - used to store the max prime number in the given range
	- range - BigInteger - used to store the range of numbers to use, given by client
	- name - String - used to store the object name, passed to registry
	- server - LargestPrimeNumber - used to store an instance of the RMI server
	- stub - LargestPrimeNumber - used to store the stub exported to the RMI runtime
	- registry - Registry - used to store the RMI registry

Client Application
- Program Purpose:
		Introduces the Java Networking concepts RMI.
- Compile: javac -cp c:\home\public_html\classes\compute.jar LargestPrimeNumberRMIClient.java
- Execution: java -cp %cd%;c:\home\public_html\classes -Djava.rmi.server.codebase=file:/c:\home\public_html\classes -Djava.security.policy=client.policy LargestPrimeNumberRMIClient [server_host] [range]
- Notes:  The server side of the program is run from a different command prompt.
- Classes: 
	- LargestPrimeNumberRMIClient - includes all server functionality. Only has the main method.
- Variables:
	- name - String - used to store the name used to lookup the remote object
	- registry - Registry - used to store remote reference to registry at server
	- getPrime - LargestPrimeNumber - used to store remote object retrieved by looking up
				   name.
	- prime - BigInteger - used to store result of invoking remote method

Server Test Plan
- ***Server must be running before client starts***
- ***all necessary jars and classes must be in network accessible folder***
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Server command prompt: java -cp %cd%;c:\home\public_html\classes\ -Djava.rmi.server.codebase=file:/c:\home\public_html\classes\ -Djava.security.policy=server.policy LargestPrimeNumberRMIServer
		- > LargestPrimeNumberRMIServer bound

Discussion: This simply demonstrates that the server runs properly.  All testing of the actual
		interaction with the client and of the program computation is contained in the client
		side documentation.
		
Client Test Plan
- ***Server must be running before client starts***
- ***all necessary jars and classes must be in network accessible folder***
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Client command prompt: java -cp %cd%;c:\home\public_html\classes -Djava.rmi.server.codebase=file:/c:\home\public_html\classes -Djava.security.policy=client.policy LargestPrimeNumberRMIClient DanSamsung 5789
		- > 5783

**Output correct**

- Normal Case 2:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Client command prompt: java -cp %cd%;c:\home\public_html\classes -Djava.rmi.server.codebase=file:/c:\home\public_html\classes -Djava.security.policy=client.policy LargestPrimeNumberRMIClient DanSamsung 487
		- > 479

**Output correct**

- Normal Case 3:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Client command prompt: java -cp %cd%;c:\home\public_html\classes -Djava.rmi.server.codebase=file:/c:\home\public_html\classes -Djava.security.policy=client.policy LargestPrimeNumberRMIClient DanSamsung 9999
		- > 9973

**Output correct**

- Exception Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Client command prompt: java -cp %cd%;c:\home\public_html\classes -Djava.rmi.server.codebase=file:/c:\home\public_html\classes -Djava.security.policy=client.policy LargestPrimeNumberRMIClient DanSamsung -56
		- > 0

**Output correct**

Discussion: The first three test cases demonstrate that the program is working correctly by 
		displaying the correct result given the specified range. 
		The results displayed were tested against manual inspection performed outside the 
		program on the ranges.  All manual inspections matched up with the program results.
		The fourth case shows the result when a negative number is provided.

***Argument provided at command line must be a valid number, no strings***
