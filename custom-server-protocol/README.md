PodServer.java - server that interacts with clients via telnet and includes the following key interactions: client connects to server, server responds with welcome message then a list of available poems and instructions for the client.  Waits for client poem selection and then responds by either displaying the poem for a valid request or by displaying an error message for an invalid request.  The server then terminates.

- Program Purpose:
		Introduces the Java Networking concepts of the ServerSocket and network application
		protocols.
- Compile: javac PodServer.java
- Execution: java PodServer [file of poems] [port] 
- Notes:  A telnet client is used to test the server.  When a client connects, the server
		displays the poems to the client and accepts choice.  This is further documented in
		the test plan.  Each request is logged to a file called "requests" created in the
		program folder.  A protocol (finite state machine) is included in the program folder.
- Classes: 
	- PodServer - the bulk of the program, including main, which sets up a ServerSocket
		 	for a given file of poems, and runs on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.  Includes Logger instances.
	- RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.  This is where communication protocol is implemented.
	- MyFormatter - extends java.util.logging.Formatter.  Formats log entries to 
			specified file format.
- Variables:
	- auditLogger - Logger - used to log server requests to "requests" file
	- errorLogger - Logger - used to log server errors to "errors" file
	- consoleLogger - Logger - used to log server status to the console
	- poems - File - holds the file containing the poems served by the PodServer
	- rootDirectory - File - used to store the directory the server is to service
	- port - int - used to store the port that the server will run on
	- encoding - String - used to store the encoding used for the requested file
	- pool - ExecutorService - used to handle threading, which is used for requests
	- server - ServerSocket - used in PodServer start() to handle client requests
	- server - PodServer - used in main as an instance of the implemented server
	- connection - Socket - used to store the connection between client/server
	- inFile - BufferedFileReader - used to input contents of poem file
	- raw - OutputStream - used to store a raw output stream to the client, allows 
		different file types to be serviced
	- out - Writer - used to store a Writer for the output stream
	- in - InputStream - used to store an input stream from server
	- requestLine - StringBuilder - used to store client request
	- c - int - used to store each individual char as it is read from client request
	- get - String - used to store a string version of client request
	- poem - StringBuilder - used to build/display requested poem
	- now - Date - used to store the current date
	- requestHandler - FileHandler - used for Logger that logs requests (auditLogger)
	- errorHandler - FileHandler - used for Logger that logs errors (errorLogger)
	- formatter - MyFormatter - used to format log entries
	- df - DateFormat - used to store/format the dates included in the log entries
	- logEntry - String - used to store the ibformation included in a log entry
	- builder - StringBuilder - used to store/build the formatted log entry
	- choice - String - used to store the poem name requested by the client
	- address - String - used to store the address of the client for logging
	- invalid - boolean - used to flag whether a requested poem has been found

Test Plan
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- Server command prompt: java PodServer mypoems.txt 80
		- > Apr 16, 2015 2:51:51 PM PodServer start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 2:51:51 PM PodServer start
		- > INFO: From file: mypoems.txt
	- Client command prompt: telnet localhost 80
		- > Welcome to Poem of the Day (PoD)
		- > 
		- > Today's available poems are:
		- > Love Apart
		- > I Love You
		- > Mask
		- > Unseen
		- > Welcome To Hell
		- > 
		- > Please choose your poem by entering the title:
	- Client command prompt: Mask
		- > 
		- > I was once sad and lonely,
		- > Having nobody to comfort me,
		- > So I wore a mask that always smiled;
		- > To hide my feelings behind a lie.
		- > 
		- > Before long, I had many friends;
		- > With my mask, I was one of them.
		- > But deep inside, I still felt empty,
		- > Like I was missing a part of me.
		- > 
		- > Nobody could hear my cries at night
		- > For I designed my mask to hide the lies.
		- > Nobody could see the pain I was feeling
		- > For I designed my mask to be laughing.
		- > 
		- > Behind all the smiles were the tears
		- > And behind all the comfort were the fears.
		- > Everything you think you see,
		- > Wasn't everything there was to me.
		- > 
		- > Day by day,
		- > I was slowly dying.
		- > I couldn't go on,
		- > There was something missing..
		- > 
		- > Until now I'm still searching
		- > For the thing that'll stop my crying.
		- > For someone who'll erase my fears,
		- > For the person who'll wipe my tears.
		- > 
		- > But till then I'll keep on smiling.
		- > Hiding behind this mask I'm wearing.
		- > Hoping one day I can smile,
		- > Till then, I'll be here.. waiting.
		- > 
		- > ┬® Matt
		- > 
		- > Source: http://www.familyfriendpoems.com/poem/my-mask-that-always-smiled#ixzz3XU
		- > fYHV00
		- > Family Friend Poems

**All output as expected

-Normal Case 2:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- (Server still running)
	- Client command prompt: telnet localhost 80
		- > Welcome to Poem of the Day (PoD)
		- > 
		- > Today's available poems are:
		- > Love Apart
		- > I Love You
		- > Mask
		- > Unseen
		- > Welcome To Hell
		- > 
		- > Please choose your poem by entering the title:
	- Client command prompt: Love Apart
		- > 
		- > Even though we are miles apart
		- > You are never far from my heart
		- > I loved you then
		- > I love you now
		- > It's always when and
		- > Never how
		- > Take me back to yesterday
		- > All the wonderful things you had to say
		- > I loved you then
		- > I love you now
		- > It's always when
		- > And never how
		- > I see your eyes
		- > I feel you near
		- > Although you're not
		- > Really here
		- > 
		- > ┬® Rhonda L. Luther
		- > 
		- > Source: http://www.familyfriendpoems.com/poem/love-apart#ixzz3XUUtWGbJ
		- > Family Friend Poems

**All output as expected

- Normal Case 3:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification and perform logging.
	- (Server still running)
	- Client command prompt: telnet localhost 80
		- > Welcome to Poem of the Day (PoD)
		- > 
		- > Today's available poems are:
		- > Love Apart
		- > I Love You
		- > Mask
		- > Unseen
		- > Welcome To Hell
		- > 
		- > Please choose your poem by entering the title:
	- Client command prompt: Welcome To Hell
		- >
		- > 'Welcome to Hell," the sign should've read,
		- > Reaching your destination-all in your head!
		- > "Last call for the train heading to Nowhere Fast,"
		- > The memories you create will forever last.
		- > You want to buy a ticket, What's the cost, you ask?
		- > Just hop on board, we'll talk once your trashed.
		- > Close your eyes and picture something grand,
		- > No peeking! Now trust me, and give me your hand!
		- > To a beach with water and the sun shining down,
		- > Open up! No beach here, you're hell bound.
		- > Of course there's water! But it's for your rig and spoon,
		- > Lil' girl, don't be afraid- 14 years old isn't that soon?
		- > The men don't bite, but you'll be messed up beyond belief,
		- > When you do pass out, not remembering- a relief.
		- > Ashamed to face Mommy! Got to have that coke!
		- > Shooting dope everyday, A girl with dreams lost all hope.
		- > I laugh at you as you toss your life in the wind,
		- > To far gone... it's us till' the end.
		- > I'll be there when you loose your pride,
		- > When you forget your morals, I'm at your side.
		- > You'll cheat and steal to have that fix,
		- > Won't take baby to the doctor although she's sick.
		- > Getting a pill- definitely #1 on the list,
		- > Oops. Another appointment baby missed.
		- > Nanny buys diapers because Mommy stays high,
		- > Daddy hits Mommy and the children cry.
		- > After years of this bliss the kids got took,
		- > Mommy is a junkie and fast becoming a crook.
		- > You'll land in jail, a drug addict you remain,
		- > Your heart turns cold as you play the game.
		- > Do not pass go- strip your dignity right here,
		- > This old man wants you, dry your tears,
		- > Quote a price! Self respect long forgotten,
		- > You'd sell your soul to the devil for an Oxycontin.
		- > I told you girl the destination is in your head!
		- > "Welcome To Hell!" Next stop... Well, she's dead.
		- > I told you that I'd stick it out till' the end,
		- > For me, you traded your dreams and kids,
		- > Your Addiction, Life, and your faithful Friend.
		- > 
		- > ┬® Nelly Barnes
		- > 
		- > Source: http://www.familyfriendpoems.com/poem/welcome-to-hell#ixzz3XUgYqKCz
		- > Family Friend Poems

**All output as expected

- Error Case 1 (poem doesn't exist):
	- Server command prompt: java HTTPServerWLog "Directory" 80
		- > Apr 16, 2015 3:01:36 PM PodServer start
		- > INFO: Accepting connections on port 80
		- > Apr 16, 2015 3:01:36 PM PodServer start
		- > INFO: From file: mypoems.txt
	- Client command prompt: telnet localhost 80
		- > Welcome to Poem of the Day (PoD)
		- > 
		- > Today's available poems are:
		- > Love Apart
		- > I Love You
		- > Mask
		- > Unseen
		- > Welcome To Hell
		- > 
		- > Please choose your poem by entering the title: U
		- > Invalid choice, must choose from choices listed.

**All output as expected

- Error Case 2 (invalid file provided):
	- Server command prompt: java PodServer mypoem.txt 80
		- > Provided file does not exist.

**All output as expected

Discussion: The first three test cases demonstrate that the program is working correctly by 
		displaying the initial message that the server is up and running, by displaying a
		welcome and additional information to the client upon a connection being made, and 
		by displaying the correct poem when selected by the user. 
		The results displayed were tested against manual inspection performed outside the 
		program on the poems.  All manual inspections matched up with the program results.
		The fourth test demonstrates the servers response when an invalid poem selection is
		made, while the fifth demonstrates that the program displays a message and exits if a
		file provided at the command line does not exist.  All results are as expected.
