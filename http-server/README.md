FileHTTPServer.java - an HTTP server able to display HTML web pages as well as embedded multimedia content such as .pdf files or images (.jpg or .png).

- Program Purpose:
		Introduces the Java Networking concepts of server I/O, 
		Sockets, ServerSockets, etc.
- Compile: javac FileHTTPServer.java
- Execution: java FileHTTPServer <root directory> <port> 
- Notes:  A telnet client is used to test the server.  GET commands are sent from telnet for
		specified files to be searched for on the server.  This is further documented in
		the test plan.
- Classes: 
	- FileHTTPServer - the bulk of the program, including main, which sets up a HTTP
			server for a given directory of files, and running on a given port.  Listens
			for clients on the given port and uses individual threads to handle each
			request through the RequestProcessor class.
	- RequestProcessor implements Callable<Void> - handles each individual request that
			is serviced by the server.
- Variables:
	- logger - Logger - used to store/provide logging capabilities
	- rootDirectory - File - used to store the directory the server is to service
	- port - int - used to store the port that the server will run on
	- encoding - String - used to store the encoding used for the requested file
	- pool - ExecutorService - used to handle threading, which is used for requests
	- docroot - File - used to store directory specified from command line
	- server - FileHTTPServer - used to an instance of the implemented server
	- indexFileName - String - used to store the index file for the directory
	- connection - Socket - used to store the connection between client/server
	- root - String - used to store the directory of the requested file
	- raw - OutputStream - used to store a raw output stream to the client, allows 
			different file types to be serviced
	- out - Writer - used to store a Writer for the output stream
	- in - InputStream - used to store an input stream from server
	- requestLine - StringBuilder - used to store client request
	- c - int - used to store each individual char as it is read from client request
	- get - String - used to store a string version of client request
	- tokens - String[] - used to store a tokenized version of client request
	- method - String - used to store the type of client request
	- version - String - used to store the version of the client
	- fileName - String - used to store the file requested by the client
	- contentType - String - used to store the contentType of the file
	- theFile - File - used to store the File requested by the client
	- theData - byte[] - used to store the data from the requested file
	- body - String - used to store the message back when a file is not found
	- now - Date - used to store the current date

Test Plan
- Normal Case 1:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Server command prompt: java FileHTTPServer "Directory" 80
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET McDavid.html HTTP/1.0
		- > HTTP/1.0 200 OK
		- > Date: Thu Apr 02 16:38:24 EDT 2015
		- > Server: FileHTTPServer
		- > Content-length: 65186
		- > Content-type: text/html
		- > 
		- > 
		- > <!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter
		- > =Y&pid=160293 -->
		<!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&pid=160293 -->
<!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&pid=160293 -->
                                                                                                 <html><head><meta http-
equiv="Content-Type" content="text/html; charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><tab
le><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><span class="html-doctype">&lt;!DOCTYPE ht
ml&gt;</span> </td></tr><tr><td class="line-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;h
tml <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-value">http://www.w3.org/1999/xhtml</spa
n>" <span class="html-attribute-name">xml:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-a
ttribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</span></td></tr><tr><td class="line-number
" value="3"></td><td class="line-content"><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number
" value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</span>Connor McDavid hockey statistics an
d profile at hockeydb.com<span class="html-tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></t
d><td class="line-content"><span class="html-tag">&lt;meta <span class="html-attribute-name">http-equiv</span>="<span cl
ass="html-attribute-value">Content-Type</span>" <span class="html-attribute-name">content</span>="<span class="html-attr
ibute-value">text/html; charset=windows-1252</span>" /&gt;</span></td></tr><tr><td class="line-number" value="6"></td><t
d class="line-content"><span class="html-tag">&lt;meta <span class="html-attribute-name">name</span>="<span class="html-
attribute-value">description</span>" <span class="html-attribute-name">content</span>="<span class="html-attribute-value
">Statistics of Connor McDavid, a hockey player from Richmond Hill, ONT born Jan 13 1997 who was active from 2012 to 201
5.</span>" /&gt;</span></td></tr><tr><td class="line-number" value="7"></td><td class="line-content"><span class="html-t
ag">&lt;meta <span class="html-attribute-name">name</span> = "<span class="html-attribute-value">format-detection</span>
" <span class="html-attribute-name">content</span> = "<span class="html-attribute-value">telephone=no</span>" /&gt;</spa
n></td></tr><tr><td class="line-number" value="8"></td><td class="line-content"><span class="html-tag">&lt;meta <span cl
ass="html-attribute-name">name</span>="<span class="html-attribute-value">viewport</span>" <span class="html-attribute-n
ame">content</span>="<span class="html-attribute-value">width=device-width, initial-scale=1</span>"&gt;</span></td></tr>
<tr><td class="line-number" value="9"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="10"
></td><td class="line-content"><span class="html-tag">&lt;link <span class="html-attribute-name">rel</span>="<span class
="html-attribute-value">StyleSheet</span>" <span class="html-attribute-name">href</span>="<a class="html-attribute-value
 html-resource-link" target="_blank" href="http://www.hockeydb.com/styles/standard.css">/styles/standard.css</a>" <span
class="html-attribute-name">type</span>="<span class="html-attribute-value">text/css</span>" <span class="html-attribute
-name">media</span>="<span class="html-attribute-value">screen</span>" /&gt;</span></td></tr><tr><td class="line-number"
 value="11"></td><td class="line-content"><span class="html-tag">&lt;link <span class="html-attribute-name">rel</span>="
<span class="html-attribute-value">StyleSheet</span>" <span class="html-attribute-name">href</span>="<a class="html-attr
ibute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/styles/standard-print.css">/styles/standar
d-print.css</a>" <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/css</span>" <spa
n class="html-attribute-name">media</span>="<span class="html-attribute-value">print</span>" /&gt;</span></td></tr><tr><
td class="line-number" value="12"></td><td class="line-content"><span class="html-tag">&lt;link <span class="html-attrib
ute-name">rel</span>="<span class="html-attribute-value">StyleSheet</span>" <span class="html-attribute-name">href</span
>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/styles/standard-hh.c
ss">/styles/standard-hh.css</a>" <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/
css</span>" <span class="html-attribute-name">media</span>="<span class="html-attribute-value">handheld</span>" /&gt;</s
pan></td></tr><tr><td class="line-number" value="13"></td><td class="line-content"><br></td></tr><tr><td class="line-num
ber" value="14"></td><td class="line-content"><span class="html-tag">&lt;script <span class="html-attribute-name">type</
span>='<span class="html-attribute-value">text/javascript</span>' <span class="html-attribute-name">async</span> <span c
lass="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://w
ww.hockeydb.com/js/all.js">/js/all.js</a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td clas
s="line-number" value="15"></td><td class="line-content"><span class="html-tag">&lt;script <span class="html-attribute-n
ame">type</span>="<span class="html-attribute-value">text/javascript</span>"&gt;</span></td></tr><tr><td class="line-num
ber" value="16"></td><td class="line-content"> &lt;!--</td></tr><tr><td class="line-number" value="17"></td><td class="l
ine-content">  BW_preloadImages('home','league','team','forums','mr','contact');</td></tr><tr><td class="line-number" va
lue="18"></td><td class="line-content"> --&gt;</td></tr><tr><td class="line-number" value="19"></td><td class="line-cont
ent"><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="20"></td><td class="line-
content"><br></td></tr><tr><td class="line-number" value="21"></td><td class="line-content"><span class="html-tag">&lt;s
cript <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/javascript</span>" <span cl
ass="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="https://a
pis.google.com/js/plusone.js">https://apis.google.com/js/plusone.js</a>"&gt;</span><span class="html-tag">&lt;/script&gt
;</span></td></tr><tr><td class="line-number" value="22"></td><td class="line-content"><br></td></tr><tr><td class="line
-number" value="23"></td><td class="line-content"><span class="html-tag">&lt;script&gt;</span>!function(d,s,id){var js,f
js=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/
widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");<span class="html-tag">&lt;/script&g
t;</span></td></tr><tr><td class="line-number" value="24"></td><td class="line-content"><br></td></tr><tr><td class="lin
e-number" value="25"></td><td class="line-content"><span class="html-tag">&lt;link <span class="html-attribute-name">rel
</span>="<span class="html-attribute-value">canonical</span>" <span class="html-attribute-name">href</span>="<a class="h
tml-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/ihdb/stats/pdisplay.php?pid=160293
">http://www.hockeydb.com/ihdb/stats/pdisplay.php?pid=160293</a>" /&gt;</span></td></tr><tr><td class="line-number" valu
e="26"></td><td class="line-content"><span class="html-tag">&lt;/head&gt;</span></td></tr><tr><td class="line-number" va
lue="27"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="28"></td><td class="line-content
"><span class="html-tag">&lt;body <span class="html-attribute-name">itemscope</span> <span class="html-attribute-name">i
temtype</span>="<span class="html-attribute-value">http://schema.org/WebPage</span>"&gt;</span></td></tr><tr><td class="
line-number" value="29"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="30"></td><td clas
s="line-content"><span class="html-tag">&lt;script&gt;</span></td></tr><tr><td class="line-number" value="31"></td><td c
lass="line-content">  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){</td></tr><tr><td clas
s="line-number" value="32"></td><td class="line-content">  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.
createElement(o),</td></tr><tr><td class="line-number" value="33"></td><td class="line-content">  m=s.getElementsByTagNa
me(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)</td></tr><tr><td class="line-number" value="34"></td><td class
="line-content">  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');</td></tr><tr><td class="l
ine-number" value="35"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="36"></td><td class
="line-content">  ga('create', 'UA-197752-1', 'hockeydb.com');</td></tr><tr><td class="line-number" value="37"></td><td
class="line-content">  ga('send', 'pageview');</td></tr><tr><td class="line-number" value="38"></td><td class="line-cont
ent"><br></td></tr><tr><td class="line-number" value="39"></td><td class="line-content"><span class="html-tag">&lt;/scri
pt&gt;</span></td></tr><tr><td class="line-number" value="40"></td><td class="line-content"><br></td></tr><tr><td class=
"line-number" value="41"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">
id</span>="<span class="html-attribute-value">hdb_logo_div</span>" <span class="html-attribute-name">style</span>="<span
 class="html-attribute-value">float:left</span>"&gt;</span></td></tr><tr><td class="line-number" value="42"></td><td cla
ss="line-content">   <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribut
e-value html-external-link" target="_blank" href="http://www.hockeydb.com/">/</a>"&gt;</span>          </td></tr><tr><td
 class="line-number" value="43"></td><td class="line-content">   <span class="html-tag">&lt;img <span class="html-attrib
ute-name">id</span>="<span class="html-attribute-value">hdb_logo</span>" <span class="html-attribute-name">src</span>="<
a class="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/images/logo.gif">/images
/logo.gif</a>" <span class="html-attribute-name">alt</span>="<span class="html-attribute-value">hockey DB</span>" <span
class="html-attribute-name">width</span>="<span class="html-attribute-value">237</span>" <span class="html-attribute-nam
e">height</span>="<span class="html-attribute-value">100</span>" <span class="html-attribute-name">style</span>="<span c
lass="html-attribute-value">border:0;</span>" /&gt;</span><span class="html-tag">&lt;/a&gt;</span></td></tr><tr><td clas
s="line-number" value="44"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td cla
ss="line-number" value="45"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="46"></td><td
class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">id</span>="<span class="html-attri
bute-value">topad</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">float:left;
 text-align:center; width:728px; height:90px; padding-top:10px</span>"&gt;</span></td></tr><tr><td class="line-number" v
alue="47"></td><td class="line-content"><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>=
"<span class="html-attribute-value">text/javascript</span>" <span class="html-attribute-name">src</span>="<a class="html
-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/common/ad_leader.php?nopop=">/common/
ad_leader.php?nopop=</a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" v
alue="48"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number"
value="49"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="50"></td><td class="line-conte
nt">  <span class="html-tag">&lt;div <span class="html-attribute-name">id</span>="<span class="html-attribute-value">hea
derbar</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">clear:both; width:965p
x</span>"&gt;</span></td></tr><tr><td class="line-number" value="51"></td><td class="line-content">     <span class="htm
l-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" target="_
blank" href="http://www.hockeydb.com/">/</a>" <span class="html-attribute-name">onmouseover</span>="<span class="html-at
tribute-value">BW_imgOver('home')</span>" <span class="html-attribute-name">onmouseout</span>="<span class="html-attribu
te-value">BW_imgOut('home')</span>"&gt;</span></td></tr><tr><td class="line-number" value="52"></td><td class="line-cont
ent">       <span class="html-tag">&lt;img <span class="html-attribute-name">src</span>="<a class="html-attribute-value
html-resource-link" target="_blank" href="http://www.hockeydb.com/images/menu_home.gif">/images/menu_home.gif</a>" <span
 class="html-attribute-name">alt</span>="<span class="html-attribute-value">home</span>" <span class="html-attribute-nam
e">width</span>="<span class="html-attribute-value">59</span>" <span class="html-attribute-name">height</span>="<span cl
ass="html-attribute-value">48</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value"
>border:0;</span>" <span class="html-attribute-name">id</span>="<span class="html-attribute-value">menu_home</span>" /&g
t;</span><span class="html-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="53"></td><td class="line-c
ontent">     <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value
html-external-link" target="_blank" href="http://www.hockeydb.com/ihdb/stats/leagues.html">/ihdb/stats/leagues.html</a>"
 <span class="html-attribute-name">onmouseover</span>="<span class="html-attribute-value">BW_imgOver('league')</span>" <
span class="html-attribute-name">onmouseout</span>="<span class="html-attribute-value">BW_imgOut('league')</span>"&gt;</
span></td></tr><tr><td class="line-number" value="54"></td><td class="line-content">       <span class="html-tag">&lt;im
g <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href=
"http://www.hockeydb.com/images/menu_league.gif">/images/menu_league.gif</a>" <span class="html-attribute-name">alt</spa
n>="<span class="html-attribute-value">search by league</span>" <span class="html-attribute-name">width</span>="<span cl
ass="html-attribute-value">126</span>" <span class="html-attribute-name">height</span>="<span class="html-attribute-valu
e">48</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">border:0;</span>" <span
 class="html-attribute-name">id</span>="<span class="html-attribute-value">menu_league</span>" /&gt;</span><span class="
html-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="55"></td><td class="line-content">     <span cla
ss="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" ta
rget="_blank" href="http://www.hockeydb.com/ihdb/stats/teams.html">/ihdb/stats/teams.html</a>" <span class="html-attribu
te-name">onmouseover</span>="<span class="html-attribute-value">BW_imgOver('team')</span>" <span class="html-attribute-n
ame">onmouseout</span>="<span class="html-attribute-value">BW_imgOut('team')</span>"&gt;</span></td></tr><tr><td class="
line-number" value="56"></td><td class="line-content">       <span class="html-tag">&lt;img <span class="html-attribute-
name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/image
s/menu_team.gif">/images/menu_team.gif</a>" <span class="html-attribute-name">alt</span>="<span class="html-attribute-va
lue">team</span>" <span class="html-attribute-name">width</span>="<span class="html-attribute-value">116</span>" <span c
lass="html-attribute-name">height</span>="<span class="html-attribute-value">48</span>" <span class="html-attribute-name
">style</span>="<span class="html-attribute-value">border:0;</span>" <span class="html-attribute-name">id</span>="<span
class="html-attribute-value">menu_team</span>" /&gt;</span><span class="html-tag">&lt;/a&gt;</span></td></tr><tr><td cla
ss="line-number" value="57"></td><td class="line-content">     <span class="html-tag">&lt;a <span class="html-attribute-
name">href</span>="<a class="html-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/vb/"
>/vb/</a>" <span class="html-attribute-name">onmouseover</span>="<span class="html-attribute-value">BW_imgOver('forums')
</span>" <span class="html-attribute-name">onmouseout</span>="<span class="html-attribute-value">BW_imgOut('forums')</sp
an>"&gt;</span></td></tr><tr><td class="line-number" value="58"></td><td class="line-content">       <span class="html-t
ag">&lt;img <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_bl
ank" href="http://www.hockeydb.com/images/menu_forums.gif">/images/menu_forums.gif</a>" <span class="html-attribute-name
">alt</span>="<span class="html-attribute-value">forums</span>" <span class="html-attribute-name">width</span>="<span cl
ass="html-attribute-value">73</span>" <span class="html-attribute-name">height</span>="<span class="html-attribute-value
">48</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">border:0;</span>" <span
class="html-attribute-name">id</span>="<span class="html-attribute-value">menu_forums</span>" /&gt;</span><span class="h
tml-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="59"></td><td class="line-content">     <span clas
s="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" tar
get="_blank" href="http://www.hockeydb.com/scoreboard">/scoreboard</a>" <span class="html-attribute-name">onmouseover</s
pan>="<span class="html-attribute-value">BW_imgOver('mr')</span>" <span class="html-attribute-name">onmouseout</span>="<
span class="html-attribute-value">BW_imgOut('mr')</span>"&gt;</span></td></tr><tr><td class="line-number" value="60"></t
d><td class="line-content">       <span class="html-tag">&lt;img <span class="html-attribute-name">src</span>="<a class=
"html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/images/menu_mr.gif">/images/menu
_mr.gif</a>" <span class="html-attribute-name">alt</span>="<span class="html-attribute-value">morning report</span>" <sp
an class="html-attribute-name">width</span>="<span class="html-attribute-value">112</span>" <span class="html-attribute-
name">height</span>="<span class="html-attribute-value">48</span>" <span class="html-attribute-name">style</span>="<span
 class="html-attribute-value">border:0;</span>" <span class="html-attribute-name">id</span>="<span class="html-attribute
-value">menu_mr</span>" /&gt;</span><span class="html-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value=
"61"></td><td class="line-content">     <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a c
lass="html-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/feedback.html">/feedback.ht
ml</a>" <span class="html-attribute-name">onmouseover</span>="<span class="html-attribute-value">BW_imgOver('contact')</
span>" <span class="html-attribute-name">onmouseout</span>="<span class="html-attribute-value">BW_imgOut('contact')</spa
n>"&gt;</span></td></tr><tr><td class="line-number" value="62"></td><td class="line-content">       <span class="html-ta
g">&lt;img <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_bla
nk" href="http://www.hockeydb.com/images/menu_contact.gif">/images/menu_contact.gif</a>" <span class="html-attribute-nam
e">alt</span>="<span class="html-attribute-value">contact us</span>" <span class="html-attribute-name">width</span>="<sp
an class="html-attribute-value">93</span>" <span class="html-attribute-name">height</span>="<span class="html-attribute-
value">48</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">border:0;</span>" <
span class="html-attribute-name">id</span>="<span class="html-attribute-value">menu_contact</span>" /&gt;</span><span cl
ass="html-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="63"></td><td class="line-content">   <span
class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="64"></td><td class="line-content"><br><
/td></tr><tr><td class="line-number" value="65"></td><td class="line-content"><span class="html-tag">&lt;div <span class
="html-attribute-name">id</span>="<span class="html-attribute-value">hb_black</span>"&gt;</span></td></tr><tr><td class=
"line-number" value="66"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">
id</span>="<span class="html-attribute-value">hb_image</span>"&gt;</span></td></tr><tr><td class="line-number" value="67
"></td><td class="line-content">  <span class="html-tag">&lt;img <span class="html-attribute-name">src</span>="<a class=
"html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/images/hdr_hockeyDB.gif">/images
/hdr_hockeyDB.gif</a>" <span class="html-attribute-name">alt</span>="<span class="html-attribute-value">home</span>" /&g
t;</span></td></tr><tr><td class="line-number" value="68"></td><td class="line-content"><span class="html-tag">&lt;/div&
gt;</span></td></tr><tr><td class="line-number" value="69"></td><td class="line-content"><br></td></tr><tr><td class="li
ne-number" value="70"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">id<
/span>="<span class="html-attribute-value">hb_red</span>"&gt;</span></td></tr><tr><td class="line-number" value="71"></t
d><td class="line-content">    <span class="html-tag">&lt;form <span class="html-attribute-name">name</span>="<span clas
s="html-attribute-value">player</span>" <span class="html-attribute-name">method</span>="<span class="html-attribute-val
ue">get</span>" <span class="html-attribute-name">action</span>="<span class="html-attribute-value">/ihdb/stats/findplay
er.php</span>"&gt;</span></td></tr><tr><td class="line-number" value="72"></td><td class="line-content">      <span clas
s="html-tag">&lt;div <span class="html-attribute-name">id</span>="<span class="html-attribute-value">hb_form1</span>"&gt
;</span><span class="html-tag">&lt;input <span class="html-attribute-name">autocorrect</span>="<span class="html-attribu
te-value">off</span>" <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text</span>" <sp
an class="html-attribute-name">name</span>="<span class="html-attribute-value">full_name</span>" <span class="html-attri
bute-name">class</span>="<span class="html-attribute-value">inp2</span>" <span class="html-attribute-name">id</span>="<s
pan class="html-attribute-value">hb_search_resp</span>"/&gt;</span><span class="html-tag">&lt;/div&gt;</span></td></tr><
tr><td class="line-number" value="73"></td><td class="line-content">      <span class="html-tag">&lt;div <span class="ht
ml-attribute-name">id</span>="<span class="html-attribute-value">hb_form2</span>"&gt;</span><span class="html-tag">&lt;i
nput <span class="html-attribute-name">name</span>="<span class="html-attribute-value">imageField</span>" <span class="h
tml-attribute-name">type</span>="<span class="html-attribute-value">image</span>" <span class="html-attribute-name">alt<
/span>="<span class="html-attribute-value">search for player</span>" <span class="html-attribute-name">src</span>="<a cl
ass="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/images/btn_player-search.gif
">/images/btn_player-search.gif</a>" /&gt;</span><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line
-number" value="74"></td><td class="line-content">      <span class="html-tag">&lt;div <span class="html-attribute-name"
>id</span>="<span class="html-attribute-value">hb_ad</span>" <span class="html-attribute-name">style</span>="<span class
="html-attribute-value">float:left; margin-left:10px;</span>"&gt;</span><span class="html-tag">&lt;script <span class="h
tml-attribute-name">type</span>="<span class="html-attribute-value">text/javascript</span>" <span class="html-attribute-
name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/commo
n/ad_redbar.php">/common/ad_redbar.php</a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span><span class="html-tag
">&lt;/div&gt;</span> </td></tr><tr><td class="line-number" value="75"></td><td class="line-content">    <span class="ht
ml-tag">&lt;/form&gt;</span></td></tr><tr><td class="line-number" value="76"></td><td class="line-content"><span class="
html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="77"></td><td class="line-content"><span class=
"html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="78"></td><td class="line-content"><br></td></
tr><tr><td class="line-number" value="79"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html
-attribute-name">id</span>="<span class="html-attribute-value">fb-root</span>"&gt;</span><span class="html-tag">&lt;/div
&gt;</span></td></tr><tr><td class="line-number" value="80"></td><td class="line-content"><span class="html-tag">&lt;scr
ipt&gt;</span>(function(d, s, id) {</td></tr><tr><td class="line-number" value="81"></td><td class="line-content">  var
js, fjs = d.getElementsByTagName(s)[0];</td></tr><tr><td class="line-number" value="82"></td><td class="line-content">
if (d.getElementById(id)) return;</td></tr><tr><td class="line-number" value="83"></td><td class="line-content">  js = d
.createElement(s); js.id = id;</td></tr><tr><td class="line-number" value="84"></td><td class="line-content">  js.src =
"//connect.facebook.net/en_US/all.js#xfbml=1&amp;appId=172068129524955";</td></tr><tr><td class="line-number" value="85"
></td><td class="line-content">  fjs.parentNode.insertBefore(js, fjs);</td></tr><tr><td class="line-number" value="86"><
/td><td class="line-content">}(document, 'script', 'facebook-jssdk'));<span class="html-tag">&lt;/script&gt;</span></td>
</tr><tr><td class="line-number" value="87"></td><td class="line-content"><span class="html-tag">&lt;script <span class=
"html-attribute-name">type</span>="<span class="html-attribute-value">text/javascript</span>"&gt;</span></td></tr><tr><t
d class="line-number" value="88"></td><td class="line-content">&lt;!--</td></tr><tr><td class="line-number" value="89"><
/td><td class="line-content">function ImageCollection(images) {</td></tr><tr><td class="line-number" value="90"></td><td
 class="line-content">  this.images = images;</td></tr><tr><td class="line-number" value="91"></td><td class="line-conte
nt">  this.i = 0;</td></tr><tr><td class="line-number" value="92"></td><td class="line-content">  this.next = function(i
mg) {</td></tr><tr><td class="line-number" value="93"></td><td class="line-content">    this.i++;</td></tr><tr><td class
="line-number" value="94"></td><td class="line-content">    if (this.i == images.length)</td></tr><tr><td class="line-nu
mber" value="95"></td><td class="line-content">    this.i = 0;</td></tr><tr><td class="line-number" value="96"></td><td
class="line-content">    img.src = images[this.i];</td></tr><tr><td class="line-number" value="97"></td><td class="line-
content">  }</td></tr><tr><td class="line-number" value="98"></td><td class="line-content">}</td></tr><tr><td class="lin
e-number" value="99"></td><td class="line-content">//--&gt;</td></tr><tr><td class="line-number" value="100"></td><td cl
ass="line-content"><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="101"></td><
td class="line-content"><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="ht
ml-attribute-value">text/javascript</span>"&gt;</span></td></tr><tr><td class="line-number" value="102"></td><td class="
line-content">  &lt;!-- // </td></tr><tr><td class="line-number" value="103"></td><td class="line-content">function sc(c
t,pid) {</td></tr><tr><td class="line-number" value="104"></td><td class="line-content">script = document.createElement(
'script');</td></tr><tr><td class="line-number" value="105"></td><td class="line-content">script.id = 'script' + pid;</t
d></tr><tr><td class="line-number" value="106"></td><td class="line-content">script.src = '/ihdb/stats/switch_content.ph
p?sc=' + ct + '&amp;pid=' + pid;</td></tr><tr><td class="line-number" value="107"></td><td class="line-content">document
.getElementsByTagName('head')[0].appendChild(script);</td></tr><tr><td class="line-number" value="108"></td><td class="l
ine-content">}</td></tr><tr><td class="line-number" value="109"></td><td class="line-content"> //  --&gt;</td></tr><tr><
td class="line-number" value="110"></td><td class="line-content"><span class="html-tag">&lt;/script&gt;</span></td></tr>
<tr><td class="line-number" value="111"></td><td class="line-content"><span class="html-tag">&lt;script <span class="htm
l-attribute-name">type</span>="<span class="html-attribute-value">text/javascript</span>" <span class="html-attribute-na
me">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://www.hockeydb.com/common/
related_pd_div_wrap.php?div_on=Y">/common/related_pd_div_wrap.php?div_on=Y</a>"&gt;</span><span class="html-tag">&lt;/sc
ript&gt;</span></td></tr><tr><td class="line-number" value="112"></td><td class="line-content"><span class="html-tag">&l
t;div <span class="html-attribute-name">class</span>="<span class="html-attribute-value">multitable</span>"&gt;</span></
td></tr><tr><td class="line-number" value="113"></td><td class="line-content"><span class="html-tag">&lt;div <span class
="html-attribute-name">class</span>="<span class="html-attribute-value">tablebg st </span>"&gt;</span></td></tr><tr><td
class="line-number" value="114"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html-attribute
-name">itemscope</span> <span class="html-attribute-name">itemtype</span>="<span class="html-attribute-value">http://sch
ema.org/Person</span>" <span class="html-attribute-name">class</span>="<span class="html-attribute-value">vw</span>"&gt;
</span></td></tr><tr><td class="line-number" value="115"></td><td class="line-content"><span class="html-tag">&lt;div <s
pan class="html-attribute-name">class</span>="<span class="html-attribute-value">v1</span>"&gt;</span></td></tr><tr><td
class="line-number" value="116"></td><td class="line-content"><span class="html-tag">&lt;h1 <span class="html-attribute-
name">itemprop</span>="<span class="html-attribute-value">name</span>" <span class="html-attribute-name">class</span>="<
span class="html-attribute-value">title</span>"&gt;</span>Connor McDavid<span class="html-tag">&lt;/h1&gt;</span></td></
tr><tr><td class="line-number" value="117"></td><td class="line-content"><span class="html-tag">&lt;div <span class="htm
l-attribute-name">class</span>="<span class="html-attribute-value">v1-1</span>"&gt;</span></td></tr><tr><td class="line-
number" value="118"></td><td class="line-content">Center -- shoots L<span class="html-tag">&lt;br /&gt;</span></td></tr>
<tr><td class="line-number" value="119"></td><td class="line-content">Born Jan 13 1997 -- <span class="html-tag">&lt;spa
n <span class="html-attribute-name">itemprop</span>="<span class="html-attribute-value">homeLocation</span>"&gt;</span>R
ichmond Hill, ONT<span class="html-tag">&lt;/span&gt;</span> <span class="html-tag">&lt;br /&gt;</span>[18 yrs. ago] <sp
an class="html-tag">&lt;br /&gt;</span>Height 6.01 -- Weight 187 [185 cm/85 kg]<span class="html-tag">&lt;br /&gt;</span
></td></tr><tr><td class="line-number" value="120"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</sp
an></td></tr><tr><td class="line-number" value="121"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</
span></td></tr><tr><td class="line-number" value="122"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;
</span></td></tr><tr><td class="line-number" value="123"></td><td class="line-content"><span class="html-tag">&lt;div <s
pan class="html-attribute-name">class</span>="<span class="html-attribute-value">v1-5</span>"&gt;</span></td></tr><tr><t
d class="line-number" value="124"></td><td class="line-content">&amp;nbsp;</td></tr><tr><td class="line-number" value="1
25"></td><td class="line-content"><span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="
html-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/ihdb/cards/carddisplay.php?pid=16
0293">/ihdb/cards/carddisplay.php?pid=160293</a>" <span class="html-attribute-name">class</span>="<span class="html-attr
ibute-value">button sprite</span>"&gt;</span><span class="html-tag">&lt;/a&gt;</span> </td></tr><tr><td class="line-numb
er" value="126"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-nu
mber" value="127"></td><td class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">style</
span>="<span class="html-attribute-value">clear:left</span>" <span class="html-attribute-name">id</span>="<span class="h
tml-attribute-value">content160293</span>"&gt;</span></td></tr><tr><td class="line-number" value="128"></td><td class="l
ine-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="129"></td><td class=
"line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribut
e-value">social_container</span>"&gt;</span></td></tr><tr><td class="line-number" value="130"></td><td class="line-conte
nt"><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribute-value">so
cial</span>"&gt;</span><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-
attribute-value">g-plusone</span>" <span class="html-attribute-name">data-size</span>="<span class="html-attribute-value
">small</span>" <span class="html-attribute-name">data-href</span>="<span class="html-attribute-value">http://www.hockey
db.com/ihdb/stats/pdisplay.php?pid=160293</span>"&gt;</span><span class="html-tag">&lt;/div&gt;</span>&amp;nbsp;<span cl
ass="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="131"></td><td class="line-content"><span
class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribute-value">social</span
>"&gt;</span><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribute-
value">fb-like</span>" <span class="html-attribute-name">data-href</span>="<span class="html-attribute-value">http://www
.hockeydb.com/ihdb/stats/pdisplay.php?filter=Y&amp;amp;pid=160293</span>" <span class="html-attribute-name">data-width</
span>="<span class="html-attribute-value">150</span>" <span class="html-attribute-name">data-layout</span>="<span class=
"html-attribute-value">button_count</span>" <span class="html-attribute-name">data-show-faces</span>="<span class="html-
attribute-value">false</span>" <span class="html-attribute-name">data-send</span>="<span class="html-attribute-value">fa
lse</span>"&gt;</span><span class="html-tag">&lt;/div&gt;</span>&amp;nbsp;<span class="html-tag">&lt;/div&gt;</span></td
></tr><tr><td class="line-number" value="132"></td><td class="line-content"><span class="html-tag">&lt;div <span class="
html-attribute-name">class</span>="<span class="html-attribute-value">social</span>"&gt;</span><span class="html-tag">&l
t;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" target="_blank" hr
ef="https://twitter.com/share">https://twitter.com/share</a>" <span class="html-attribute-name">class</span>="<span clas
s="html-attribute-value">twitter-share-button</span>" <span class="html-attribute-name">data-lang</span>="<span class="h
tml-attribute-value">en</span>"&gt;</span>Tweet<span class="html-tag">&lt;/a&gt;</span>&amp;nbsp;<span class="html-tag">
&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="133"></td><td class="line-content"><span class="html-tag
">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="134"></td><td class="line-content"><span class="html-t
ag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribute-value">v3</span>"&gt;</span></td
></tr><tr><td class="line-number" value="135"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</span></
td></tr><tr><td class="line-number" value="136"></td><td class="line-content"><span class="html-tag">&lt;table <span cla
ss="html-attribute-name">class</span>="<span class="html-attribute-value">sortable autostripe st reg</span>" <span class
="html-attribute-name">id</span>="<span class="html-attribute-value">160293</span>"&gt;</span></td></tr><tr><td class="l
ine-number" value="137"></td><td class="line-content"><span class="html-tag">&lt;thead&gt;</span></td></tr><tr><td class
="line-number" value="138"></td><td class="line-content"><span class="html-tag">&lt;tr&gt;</span></td></tr><tr><td class
="line-number" value="139"></td><td class="line-content"><span class="html-tag">&lt;th <span class="html-attribute-name"
>colspan</span>="<span class="html-attribute-value">3</span>" <span class="html-attribute-name">class</span> = "<span cl
ass="html-attribute-value">l</span>" <span class="html-attribute-name">style</span>="<span class="html-attribute-value">
background-color:white; padding:0px;</span>"&gt;</span></td></tr><tr><td class="line-number" value="140"></td><td class=
"line-content"><span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="141"></td><td class
="line-content"><span class="html-tag">&lt;th <span class="html-attribute-name">class</span>="<span class="html-attribut
e-value">c_sec_head rtab</span>" <span class="html-attribute-name">colspan</span>="<span class="html-attribute-value">6<
/span>"&gt;</span>Regular Season<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="14
2"></td><td class="line-content"><span class="html-tag">&lt;th <span class="html-attribute-name">class</span>="<span cla
ss="html-attribute-value">c_sec_head rtab</span>" <span class="html-attribute-name">colspan</span>="<span class="html-at
tribute-value">5</span>"&gt;</span>Playoffs<span class="html-tag">&lt;/th&gt;</span><span class="html-tag">&lt;/tr&gt;</
span></td></tr><tr><td class="line-number" value="143"></td><td class="line-content"><span class="html-tag">&lt;tr <span
 class="html-attribute-name">class</span>="<span class="html-attribute-value">c_sort_head</span>"&gt;</span></td></tr><t
r><td class="line-number" value="144"></td><td class="line-content"><span class="html-tag">&lt;th <span class="html-attr
ibute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>Season<span class="html-tag">&lt;/th&gt
;</span></td></tr><tr><td class="line-number" value="145"></td><td class="line-content"><span class="html-tag">&lt;th <s
pan class="html-attribute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>Team<span class="ht
ml-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="146"></td><td class="line-content"><span class="h
tml-tag">&lt;th <span class="html-attribute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>L
ge<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="147"></td><td class="line-conten
t"><span class="html-tag">&lt;th&gt;</span>GP<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-numb
er" value="148"></td><td class="line-content"><span class="html-tag">&lt;th&gt;</span>G<span class="html-tag">&lt;/th&gt
;</span></td></tr><tr><td class="line-number" value="149"></td><td class="line-content"><span class="html-tag">&lt;th&gt
;</span>A<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="150"></td><td class="line
-content"><span class="html-tag">&lt;th&gt;</span>Pts<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="l
ine-number" value="151"></td><td class="line-content"><span class="html-tag">&lt;th&gt;</span>PIM<span class="html-tag">
&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="152"></td><td class="line-content"><span class="html-tag"
>&lt;th&gt;</span>+/-<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="153"></td><td
 class="line-content"><span class="html-tag">&lt;th&gt;</span>GP<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><
td class="line-number" value="154"></td><td class="line-content"><span class="html-tag">&lt;th&gt;</span>G<span class="h
tml-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="155"></td><td class="line-content"><span class="
html-tag">&lt;th&gt;</span>A<span class="html-tag">&lt;/th&gt;</span></td></tr><tr><td class="line-number" value="156"><
/td><td class="line-content"><span class="html-tag">&lt;th&gt;</span>Pts<span class="html-tag">&lt;/th&gt;</span></td></
tr><tr><td class="line-number" value="157"></td><td class="line-content"><span class="html-tag">&lt;th&gt;</span>PIM<spa
n class="html-tag">&lt;/th&gt;</span><span class="html-tag">&lt;/tr&gt;</span></td></tr><tr><td class="line-number" valu
e="158"></td><td class="line-content"><span class="html-tag">&lt;/thead&gt;</span></td></tr><tr><td class="line-number"
value="159"></td><td class="line-content"><span class="html-tag">&lt;tbody&gt;</span></td></tr><tr><td class="line-numbe
r" value="160"></td><td class="line-content"><span class="html-tag">&lt;tr&gt;</span><span class="html-tag">&lt;td <span
 class="html-attribute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>2012-13<span class="ht
ml-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="161"></td><td class="line-content"><span class="h
tml-tag">&lt;td <span class="html-attribute-name">class</span>="<span class="html-attribute-value">l hhw </span>"&gt;</s
pan><span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-exte
rnal-link" target="_blank" href="http://www.hockeydb.com/ihdb/stats/leagues/seasons/teams/0008792013.html">/ihdb/stats/l
eagues/seasons/teams/0008792013.html</a>"&gt;</span>Erie Otters<span class="html-tag">&lt;/a&gt;</span><span class="html
-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="162"></td><td class="line-content"><span class="htm
l-tag">&lt;td <span class="html-attribute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>OHL
<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="163"></td><td class="line-content"
><span class="html-tag">&lt;td&gt;</span>63<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number
" value="164"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>25<span class="html-tag">&lt;/td&gt;
</span></td></tr><tr><td class="line-number" value="165"></td><td class="line-content"><span class="html-tag">&lt;td&gt;
</span>41<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="166"></td><td class="line
-content"><span class="html-tag">&lt;td&gt;</span>66<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="li
ne-number" value="167"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>36<span class="html-tag">&l
t;/td&gt;</span></td></tr><tr><td class="line-number" value="168"></td><td class="line-content"><span class="html-tag">&
lt;td&gt;</span>-24<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="169"></td><td c
lass="line-content"><span class="html-tag">&lt;td&gt;</span><span class="html-tag">&lt;/td&gt;</span><span class="html-t
ag">&lt;td&gt;</span><span class="html-tag">&lt;/td&gt;</span><span class="html-tag">&lt;td&gt;</span><span class="html-
tag">&lt;/td&gt;</span><span class="html-tag">&lt;td&gt;</span><span class="html-tag">&lt;/td&gt;</span><span class="htm
l-tag">&lt;td&gt;</span><span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="170"></td>
<td class="line-content"><span class="html-tag">&lt;/tr&gt;</span></td></tr><tr><td class="line-number" value="171"></td
><td class="line-content"><span class="html-tag">&lt;tr&gt;</span><span class="html-tag">&lt;td <span class="html-attrib
ute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>2013-14<span class="html-tag">&lt;/td&gt;
</span></td></tr><tr><td class="line-number" value="172"></td><td class="line-content"><span class="html-tag">&lt;td <sp
an class="html-attribute-name">class</span>="<span class="html-attribute-value">l hhw </span>"&gt;</span><span class="ht
ml-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" target="
_blank" href="http://www.hockeydb.com/ihdb/stats/leagues/seasons/teams/0008792014.html">/ihdb/stats/leagues/seasons/team
s/0008792014.html</a>"&gt;</span>Erie Otters<span class="html-tag">&lt;/a&gt;</span><span class="html-tag">&lt;/td&gt;</
span></td></tr><tr><td class="line-number" value="173"></td><td class="line-content"><span class="html-tag">&lt;td <span
 class="html-attribute-name">class</span>="<span class="html-attribute-value">l</span>"&gt;</span>OHL<span class="html-t
ag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="174"></td><td class="line-content"><span class="html-
tag">&lt;td&gt;</span>56<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="175"></td>
<td class="line-content"><span class="html-tag">&lt;td&gt;</span>28<span class="html-tag">&lt;/td&gt;</span></td></tr><t
r><td class="line-number" value="176"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>71<span clas
s="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="177"></td><td class="line-content"><span cla
ss="html-tag">&lt;td&gt;</span>99<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="1
78"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>20<span class="html-tag">&lt;/td&gt;</span></t
d></tr><tr><td class="line-number" value="179"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>47<
span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="180"></td><td class="line-content">
<span class="html-tag">&lt;td&gt;</span>14<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number"
 value="181"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>4<span class="html-tag">&lt;/td&gt;</
span></td></tr><tr><td class="line-number" value="182"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</
span>15<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="183"></td><td class="line-c
ontent"><span class="html-tag">&lt;td&gt;</span>19<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line
-number" value="184"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>2<span class="html-tag">&lt;/
td&gt;</span></td></tr><tr><td class="line-number" value="185"></td><td class="line-content"><span class="html-tag">&lt;
/tr&gt;</span></td></tr><tr><td class="line-number" value="186"></td><td class="line-content"><span class="html-tag">&lt
;tr&gt;</span><span class="html-tag">&lt;td <span class="html-attribute-name">class</span>="<span class="html-attribute-
value">l</span>"&gt;</span>2014-15<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="
187"></td><td class="line-content"><span class="html-tag">&lt;td <span class="html-attribute-name">class</span>="<span c
lass="html-attribute-value">l hhw </span>"&gt;</span><span class="html-tag">&lt;a <span class="html-attribute-name">href
</span>="<a class="html-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/ihdb/stats/lea
gues/seasons/teams/0008792015.html">/ihdb/stats/leagues/seasons/teams/0008792015.html</a>"&gt;</span>Erie Otters<span cl
ass="html-tag">&lt;/a&gt;</span><span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="18
8"></td><td class="line-content"><span class="html-tag">&lt;td <span class="html-attribute-name">class</span>="<span cla
ss="html-attribute-value">l</span>"&gt;</span>OHL<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-
number" value="189"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>47<span class="html-tag">&lt;/
td&gt;</span></td></tr><tr><td class="line-number" value="190"></td><td class="line-content"><span class="html-tag">&lt;
td&gt;</span>44<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="191"></td><td class
="line-content"><span class="html-tag">&lt;td&gt;</span>76<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td cla
ss="line-number" value="192"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>120<span class="html-
tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="193"></td><td class="line-content"><span class="html
-tag">&lt;td&gt;</span>48<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="194"></td
><td class="line-content"><span class="html-tag">&lt;td&gt;</span>60<span class="html-tag">&lt;/td&gt;</span></td></tr><
tr><td class="line-number" value="195"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>4<span clas
s="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="196"></td><td class="line-content"><span cla
ss="html-tag">&lt;td&gt;</span>2<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="19
7"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>4<span class="html-tag">&lt;/td&gt;</span></td>
</tr><tr><td class="line-number" value="198"></td><td class="line-content"><span class="html-tag">&lt;td&gt;</span>6<spa
n class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" value="199"></td><td class="line-content"><sp
an class="html-tag">&lt;td&gt;</span>2<span class="html-tag">&lt;/td&gt;</span></td></tr><tr><td class="line-number" val
ue="200"></td><td class="line-content"><span class="html-tag">&lt;/tr&gt;</span></td></tr><tr><td class="line-number" va
lue="201"></td><td class="line-content"><span class="html-tag">&lt;/tbody&gt;</span></td></tr><tr><td class="line-number
" value="202"></td><td class="line-content"><span class="html-tag">&lt;tfoot&gt;</span></td></tr><tr><td class="line-num
ber" value="203"></td><td class="line-content"><span class="html-tag">&lt;/tfoot&gt;</span></td></tr><tr><td class="line
-number" value="204"></td><td class="line-content"><span class="html-tag">&lt;/table&gt;</span></td></tr><tr><td class="
line-number" value="205"></td><td class="line-content"><span class="html-tag">&lt;p&gt;</span><span class="html-tag">&lt
;a <span class="html-attribute-name">href</span>="" <span class="html-attribute-name">onclick</span>="<span class="html-
attribute-value">window.open('embed.php?pid=160293','','toolbar=0,menubar=0,resizable=0,width=450,height=225,top=400,lef
t=400'); return false;</span>"&gt;</span>Embed Connor McDavid stats!<span class="html-tag">&lt;/a&gt;</span></td></tr><t
r><td class="line-number" value="206"></td><td class="line-content"> | <span class="html-tag">&lt;a <span class="html-at
tribute-name">href</span>="<a class="html-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.
com/ihdb/stats/viewastext.php?a1b133ad=f729ca1b&pid=160293">viewastext.php?a1b133ad=f729ca1b&amp;amp;pid=160293</a>" <sp
an class="html-attribute-name">rel</span>="<span class="html-attribute-value">nofollow</span>"&gt;</span>View as text<sp
an class="html-tag">&lt;/a&gt;</span><span class="html-tag">&lt;/p&gt;</span></td></tr><tr><td class="line-number" value
="207"></td><td class="line-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" val
ue="208"></td><td class="line-content"><br></td></tr><tr><td class="line-number" value="209"></td><td class="line-conten
t"><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-attribute-value">tab
lebg st</span>"&gt;</span></td></tr><tr><td class="line-number" value="210"></td><td class="line-content"><span class="h
tml-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="211"></td><td class="line-content"><span class=
"html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/javascript<
/span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank"
 href="http://www.hockeydb.com/common/related_pd_wrap_small.php?fn=Connor&ln=McDavid&pid=160293&fn_dm=KNR&ln_dm=MKTFT">/
common/related_pd_wrap_small.php?fn=Connor&amp;amp;ln=McDavid&amp;amp;pid=160293&amp;amp;fn_dm=KNR&amp;amp;ln_dm=MKTFT</
a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="212"></td><td cl
ass="line-content"><span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="213"></td><td
class="line-content"><span class="html-tag">&lt;div <span class="html-attribute-name">class</span>="<span class="html-at
tribute-value">sponsor</span>"&gt;</span></td></tr><tr><td class="line-number" value="214"></td><td class="line-content"
><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">tex
t/javascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" ta
rget="_blank" href="http://www.hockeydb.com/common/ad_cube_above_sky.php">/common/ad_cube_above_sky.php</a>"&gt;</span><
span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="215"></td><td class="line-conte
nt"><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">
text/javascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link"
 target="_blank" href="http://www.hockeydb.com/common/ad_multi_sky.php">/common/ad_multi_sky.php</a>"&gt;</span><span cl
ass="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="216"></td><td class="line-content"><sp
an class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/ja
vascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target
="_blank" href="http://www.hockeydb.com/common/ad_cube_below_box.php">/common/ad_cube_below_box.php</a>"&gt;</span><span
 class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="217"></td><td class="line-content">
<span class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="218"></td><td class="line-content
"><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">te
xt/javascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" t
arget="_blank" href="http://www.hockeydb.com/common/related_pd_wrap.php?fn=Connor&ln=McDavid&pid=160293&fn_dm=KNR&ln_dm=
MKTFT">/common/related_pd_wrap.php?fn=Connor&amp;amp;ln=McDavid&amp;amp;pid=160293&amp;amp;fn_dm=KNR&amp;amp;ln_dm=MKTFT
</a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="219"></td><td
class="line-content"><span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-
attribute-value">text/javascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value ht
ml-resource-link" target="_blank" href="http://www.hockeydb.com/common/related_pd_div_wrap.php?div_on=N">/common/related
_pd_div_wrap.php?div_on=N</a>"&gt;</span><span class="html-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-numb
er" value="220"></td><td class="line-content">  <span class="html-tag">&lt;div <span class="html-attribute-name">id</spa
n>="<span class="html-attribute-value">footer_bar</span>"&gt;</span></td></tr><tr><td class="line-number" value="221"></
td><td class="line-content">     <span class="html-tag">&lt;div <span class="html-attribute-name">id</span>="<span class
="html-attribute-value">footer_cell</span>"&gt;</span></td></tr><tr><td class="line-number" value="222"></td><td class="
line-content">        <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribu
te-value html-external-link" target="_blank" href="http://www.hockeydb.com/">/</a>" <span class="html-attribute-name">cl
ass</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Home<span class="html-tag">&lt;/a&gt;</span> |
<span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-external
-link" target="_blank" href="http://www.hockeydb.com/advertise.html">/advertise.html</a>" <span class="html-attribute-na
me">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Advertise<span class="html-tag">&lt;/a&gt
;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value ht
ml-external-link" target="_blank" href="http://www.hockeydb.com/feedback.html">/feedback.html</a>" <span class="html-att
ribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Feedback<span class="html-tag">&
lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-
value html-external-link" target="_blank" href="http://www.hockeydb.com/copyright.html">/copyright.html</a>" <span class
="html-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Usage<span class="html
-tag">&lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-att
ribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/privacy.html">/privacy.html</a>" <span cl
ass="html-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Privacy<span class=
"html-tag">&lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="htm
l-attribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/faq.html">/faq.html</a>" <span class
="html-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>FAQ<span class="html-t
ag">&lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attri
bute-value html-external-link" target="_blank" href="http://www.hockeydb.com/links.html">/links.html</a>" <span class="h
tml-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Links<span class="html-ta
g">&lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attrib
ute-value html-external-link" target="_blank" href="http://www.hockeydb.com/credits.html">/credits.html</a>" <span class
="html-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Credits<span class="ht
ml-tag">&lt;/a&gt;</span> | <span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-a
ttribute-value html-external-link" target="_blank" href="http://www.hockeydb.com/help.html">/help.html</a>" <span class=
"html-attribute-name">class</span>="<span class="html-attribute-value">revlink</span>"&gt;</span>Help!<span class="html-
tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="223"></td><td class="line-content">     <span class="
html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="224"></td><td class="line-content">     <span
class="html-tag">&lt;div <span class="html-attribute-name">id</span>="<span class="html-attribute-value">bottom_cell</sp
an>"&gt;</span></td></tr><tr><td class="line-number" value="225"></td><td class="line-content">        <span class="html
-tag">&lt;div <span class="html-attribute-name">style</span>="<span class="html-attribute-value">float:left</span>"&gt;<
/span><span class="html-tag">&lt;a <span class="html-attribute-name">href</span>="<a class="html-attribute-value html-ex
ternal-link" target="_blank" href="http://www.hockeydb.com/">/</a>"&gt;</span><span class="html-tag">&lt;img <span class
="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" target="_blank" href="http://www.h
ockeydb.com/images/logo_small.gif">/images/logo_small.gif</a>" <span class="html-attribute-name">alt</span>="<span class
="html-attribute-value">hockeyDB.com</span>" <span class="html-attribute-name">width</span>="<span class="html-attribute
-value">115</span>" <span class="html-attribute-name">height</span>="<span class="html-attribute-value">38</span>" <span
 class="html-attribute-name">style</span>="<span class="html-attribute-value">border:0;</span>" /&gt;</span><span class=
"html-tag">&lt;/a&gt;</span> &amp;nbsp; &amp;nbsp; Copyright 1998-2011 hockeyDB.com. All rights reserved.<span class="ht
ml-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="226"></td><td class="line-content">     <span cl
ass="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="227"></td><td class="line-content">  <spa
n class="html-tag">&lt;/div&gt;</span></td></tr><tr><td class="line-number" value="228"></td><td class="line-content"><b
r></td></tr><tr><td class="line-number" value="229"></td><td class="line-content"><span class="html-tag">&lt;a <span cla
ss="html-attribute-name">href</span>="<a class="html-attribute-value html-external-link" target="_blank" href="https://p
lus.google.com/100154460718852627672?rel=author">https://plus.google.com/100154460718852627672?rel=author</a>"&gt;</span
>Google<span class="html-tag">&lt;/a&gt;</span></td></tr><tr><td class="line-number" value="230"></td><td class="line-co
ntent"><br></td></tr><tr><td class="line-number" value="231"></td><td class="line-content"><span class="html-comment">&l
t;!-- Start Quantcast tag --&gt;</span></td></tr><tr><td class="line-number" value="232"></td><td class="line-content"><
span class="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/
javascript</span>" <span class="html-attribute-name">src</span>="<a class="html-attribute-value html-resource-link" targ
et="_blank" href="http://edge.quantserve.com/quant.js">http://edge.quantserve.com/quant.js</a>"&gt;</span><span class="h
tml-tag">&lt;/script&gt;</span></td></tr><tr><td class="line-number" value="233"></td><td class="line-content"><span cla
ss="html-tag">&lt;script <span class="html-attribute-name">type</span>="<span class="html-attribute-value">text/javascri
pt</span>"&gt;</span>_qacct="p-bf8ND2mCqtl_o";quantserve();<span class="html-tag">&lt;/script&gt;</span></td></tr><tr><t
d class="line-number" value="234"></td><td class="line-content"><span class="html-tag">&lt;noscript&gt;</span></td></tr>
<tr><td class="line-number" value="235"></td><td class="line-content">&lt;a href="http://www.quantcast.com/p-bf8ND2mCqtl
_o" target="_blank"&gt;&lt;img src="http://pixel.quantserve.com/pixel/p-bf8ND2mCqtl_o.gif" style="display: none; border:
0;" height="1" width="1" alt="Quantcast"/&gt;&lt;/a&gt;</td></tr><tr><td class="line-number" value="236"></td><td class=
"line-content"><span class="html-tag">&lt;/noscript&gt;</span></td></tr><tr><td class="line-number" value="237"></td><td
 class="line-content"><br></td></tr><tr><td class="line-number" value="238"></td><td class="line-content"><span class="h
tml-comment">&lt;!-- End Quantcast tag --&gt;</span></td></tr><tr><td class="line-number" value="239"></td><td class="li
ne-content"><br></td></tr><tr><td class="line-number" value="240"></td><td class="line-content"><br></td></tr><tr><td cl
ass="line-number" value="241"></td><td class="line-content"><span class="html-tag">&lt;/body&gt;</span></td></tr><tr><td
 class="line-number" value="242"></td><td class="line-content"><span class="html-tag">&lt;/html&gt;</span></td></tr><tr>
<td class="line-number" value="243"></td><td class="line-content"><span class="html-end-of-file"></span></td></tr></tbod
y></table></body></html><HTML>

**All output as expected

- Normal Case 2:
	- Runs program as constituted, without any alterations.
	- Should display output as required in program specification.
	- Server command prompt: java FileHTTPServer "Directory" 80
	- Client command prompt: telnet localhost 80
	- Client command prompt: GET crosby.html HTTP/1.0
		> HTTP/1.0 200 OK
		> Date: Thu Apr 02 16:52:04 EDT 2015
		> Server: FileHTTPServer
		> Content-length: 82571
		> Content-type: text/html
		> 
		> <!DOCTYPE html>
		>                <!-- saved from url=(0066)http://www.hockeydb.com/ihdb/stats/pdis
		> play.php?filter=Y&pid=73288 -->
		>                                <html xmlns="http://www.w3.org/1999/xhtml" xml:la
		> ng="en" lang="en"><head><meta http-equiv="Content-Type" content="text/html; char
		> set=windows-1252">
		>                   <title>Sidney Crosby hockey statistics and profile at hockeydb
		> .com</title>
		> 
		>             <meta name="description" content="Statistics of Sidney Crosby, a hoc
		> key player from Cole Harbour, NS born Aug 7 1987 who was active from 2003 to 201
		> 5.">
		>     <meta name="format-detection" content="telephone=no">
		> ontent="width=device-width, initial-scale=1">
		> 
		>                                              <link rel="StyleSheet" href="./inde
		> x_files/standard.css" type="text/css" media="screen">
		>                                                      <link rel="StyleSheet" href
		> ="./index_files/standard-print.css" type="text/css" media="print">
		>                                                                   <link rel="Sty
		> leSheet" href="./index_files/standard-hh.css" type="text/css" media="handheld">
		.
		.
		.
		**All output as expected
Normal Case 3:
		Runs program as constituted, without any alterations.
		Should display output as required in program specification.
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   GET strome.html HTTP/1.0
		> HTTP/1.0 200 OK
		> Date: Thu Apr 02 16:58:01 EDT 2015
		> Server: FileHTTPServer
		> Content-length: 62062
		> Content-type: text/html
		> 
		> 
		> <!-- saved from url=(0067)http://www.hockeydb.com/ihdb/stats/pdisplay.php?filter
		> =Y&pid=170174 -->
		>                  <html><head><meta http-equiv="Content-Type" content="text/html;
		>  charset=windows-1252"></head><body><div class="line-gutter-backdrop"></div><tab
		> le><tbody><tr><td class="line-number" value="1"></td><td class="line-content"><s
		> pan class="html-doctype">&lt;!DOCTYPE html&gt;</span> </td></tr><tr><td class="l
		> ine-number" value="2"></td><td class="line-content"><span class="html-tag">&lt;h
		> tml <span class="html-attribute-name">xmlns</span>="<span class="html-attribute-
		> value">http://www.w3.org/1999/xhtml</span>" <span class="html-attribute-name">xm
		> l:lang</span>="<span class="html-attribute-value">en</span>" <span class="html-a
		> ttribute-name">lang</span>="<span class="html-attribute-value">en</span>"&gt;</s
		> pan></td></tr><tr><td class="line-number" value="3"></td><td class="line-content
		> "><span class="html-tag">&lt;head&gt;</span></td></tr><tr><td class="line-number
		> " value="4"></td><td class="line-content"><span class="html-tag">&lt;title&gt;</
		> span>Dylan Strome hockey statistics and profile at hockeydb.com<span class="html
		> -tag">&lt;/title&gt;</span></td></tr><tr><td class="line-number" value="5"></td>
		> <td class="line-content"><span class="html-tag">&lt;meta <span class="html-attri
		> bute-name">http-equiv</span>="<span class="html-attribute-value">Content-Type</s
		> pan>" <span class="html-attribute-name">content</span>="<span class="html-attrib
		> ute-value">text/html; charset=windows-1252</span>" /&gt;</span></td></tr><tr><td
		>  class="line-number" value="6"></td><td class="line-content"><span class="html-t
		> ag">&lt;meta <span class="html-attribute-name">name</span>="<span class="html-at
		> tribute-value">description</span>" <span class="html-attribute-name">content</sp
		.
		.
		.
		**All output as expected
Error Case 1 (file doesn't exist):
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   GET rto HTTP/1.0
		>HTTP/1.0 404 File Not Found
		>Date: Thu Apr 02 17:00:25 EDT 2015
		>Server: FileHTTPServer
		>Content-length: 117
		>Content-type: text/html; charset=utf-8
		>
		><HTML>
		><HEAD><TITLE>File Not Found</TITLE>
		></HEAD>
		><BODY><H1>HTTP Error 404: File Not Found</H1>
		></BODY></HTML>
		**All output as expected
Error Case 2 (invalid request type):
		Server command prompt: java FileHTTPServer "Directory" 80
		Client command prompt: telnet localhost 80
							   POST index.html HTTP/1.0
		><HTML>
		><HEAD><TITLE>Not Implemented</TITLE>
		></HEAD>
		><BODY><H1>HTTP Error 501: Not Implemented</H1>
		></BODY></HTML>
		**All output as expected
Example Output from Server Command Prompt:
		>Apr 02, 2015 4:50:22 PM FileHTTPServer start
		>INFO: Accepting connections on port 80
		>Apr 02, 2015 4:50:22 PM FileHTTPServer start
		>INFO: Document Root: Directory
		>Apr 02, 2015 4:50:35 PM FileHTTPServer$RequestProcessor call
		>INFO: /0:0:0:0:0:0:0:1:54364 GET index.html HTTP/1.0

		The first three test cases demonstrate that the search option that is specified 
		in the requirements is working correctly, different files can be requested of the
		server, not just a signle one as seen in the text example.  These cases use the 
		different webpages, with embedded multimedia and are displayed.  The results that 
		the program displays were tested against manual inspection performed outside the 
		program on sources.  All manual inspections matched up with the program results.
		The fourth test case tests the program when provided with an invalid file, while
		the fifth shows the result when a method other than GET is attempted.
		These were included as very minimal error handling tests.

		**Raw output is sent when a file is successfully requested in order to allow
		different types of files to be served.
