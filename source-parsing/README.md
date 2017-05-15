SourceViewer.java - source viewer that incorporates a search function. When provided with a server URL or IP and a search string, examines the server for instances of the string and prints only those lines that contain the string. Executes using the command line "java SourceViewer [URL/IP] [search string]" where URL/IP is a valid URL or IP address and search string is the search string in double quotes.

- Program Purpose:
		Introduces the Java Networking concepts of URLs, URLConnections, and parsing.
- Compile: javac SourceViewer.java
- Execution: java SourceViewer URL/IP /<search string/>
- Notes: No user input is necessary outside of command line , everything for this program 
		is hardcoded
- Classes: 
	- SourceViewer - contains all necessary for proper program execution.  In this program
				all execution is done right from the main method.
- Variables:
	- u - URL - used to store the URL provided at the command line
	- uc - HttpURLConnection - used to open/store connection to provided URL
	- raw - InputStream - used to store raw input stream from HttpURLConnection
	- buffer - InputStream - used to store a buffer for the InputStream
	- reader - Reader - used to store a reader set up on the buffer
	- c - int - used to store the int version of each character read in
	- line - String - used to store each line input from the URL

Test Plan
- Normal Case 1 (java SourceViewer http://tsn.ca "Hutchison"):
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- All testing built into program execution.
		>                                                 <strong>Hutchison starts opener;
		> roster takes shape</strong>
		>
		><li><h4><a href="/related/tag?Tag=Drew Hutchison
		>">Drew Hutchison</a></h4></li>
		>                                        <img title='Drew Hutchison, The Canadian
		> Press' height='135' alt='Drew Hutchison' width='240' align='' src='/polopoly_fs
		>/1.233715.1426645946!/fileimage/httpImage/image.jpg_gen/derivatives/landscape_24
		>0/drew-hutchison.jpg' />
		>    ><h3>Hutchison starts opener; roster takes shape</h3></a>
		>
		>                    <p>Drew Hutchison has been named the Toronto Blue Jays Openi
		>ng Day starter for 2015. It was announced Tuesday that the 24-year-old right-han
		>der will be on the hill when the team opens its season at Yankee Stadium on Mond
		>ay.</p>

**All output as expected

- Normal Case 2 (java SourceViewer http://oreilly.com "search"):
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- All testing built into program execution.
		- >/<tr/>/<td id="search2" class="v2"/>
		- >/<div id="search-box" class="yui-skin-sam"/>
		- >/<form name="searchform" method="get" id="search-form" action="http://search.orei
		- >lly.com/"><div class="search">
		- >        <span id="search-input">
		- >            <div class="searchInput">
		- >            <span id="search-field"><input type="text" value="Search" name="q" m
		- >axlength="200" id="q" onfocus="this.value=checkIfDefault(this.value);"></span>
		- >            <span id="search-button"><input type="image" value="SEARCH" src="htt
		- >p://cdn.oreillystatic.com/images/oreilly/large-search.png" border="0" style="flo
		- >at:left" alt="Search" align="top"></span>
		- >                <p>O'Reilly spreads the knowledge of innovators through its tech
		- >nology books, online services, magazines, research, and tech conferences. Since
		- >1978, O'Reilly has been a chronicler and catalyst of leading-edge development, h
		- >oming in on the technology trends that really matter and galvanizing their adopt
		- >ion by amplifying "faint signals" from the alpha geeks who are creating the futu
		- >re. An active participant in the technology community, O'Reilly has a long histo
		- >ry of advocacy, meme-making, and evangelism.</p>

**All output as expected

- Normal Case 3 (java SourceViewer http://oreilly.com "!-- crazyegg"):
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- All testing built into program execution.
		- >\<!-- crazyegg code --\>
		- >\<!-- crazyegg code --\>

**All output as expected

- Error Case 1 (invalid URL: java SourceViewer h//orly.com "search"):
	- >h//orly.com is not a parseable URL
- Error Case 2 (both arguments not provided at command line):
	- >Usage is: java SourceViewer \<URL\> \<search string\>

***As this program is completely hardcoded, there is no user input at all, there are no
further cases to test.  The above demonstrates that the program works as required.

Discussion:
		The first three test cases demonstrate that the search option that is specified 
		in the requirements is working correctly.  These cases use the different sites
		& different strings.  The results that the program displays were tested against
		manual inspection performed outside the program on URL sources.  All manual
		inspections matched up with the program results.
		The fourth test case tests the program when provided with an invalid URL, while
		the fifth shows the result if both arguments are not provided at the command line.
		These were included as very minimal error handling tests.
