Client & Server using Java RMI - system using Java RMI, such that the client can ask the server to generate a largest prime number within a range set by the user on the client.

- Program Purpose:
		Introduces the Java Networking concepts RMI.
Compile: javac -cp c:\app\Dan\product\12.1.0\dbhome_1\rda\da\public_html\classes\compute.
		 jar LargestPrimeNumberRMIClient.java
Execution: java -cp c:users\dan\mingw\comp_348\assignment3;c:\app\Dan\product\12.1.0\db
		   home_1\rda\da\public_html\classes\ -Djava.rmi.server.codebase=file:/c:/app/Dan
		   /product/12.1.0/dbhome_1/rda/da/public_html/classes/ -Djava.security.policy=cl
		   ient.policy LargestPrimeNumberRMIClient DanSamsung <range>
Notes:  The server side of the program is run from a different command prompt.
Classes: 
		LargestPrimeNumberRMIClient - includes all server functionality. Only has the main method.
Variables:
		name - String - used to store the name used to lookup the remote object
		registry - Registry - used to store remote reference to registry at server
		getPrime - LargestPrimeNumber - used to store remote object retrieved by looking up
				   name.
		prime - BigInteger - used to store result of invoking remote method
