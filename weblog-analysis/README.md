MyPooledWeblog.java - reads a weblog and tallies statistics on the log entries based on an input option choice.  Executed using the command line ‘java MyPooledWeblog logname option’ where option is one of the following: 1 = count number of accesses by each remotehost, 2 = count total bytes transmitted, and 3 = count total bytes by remotehost.

- Program Purpose:
		Introduces the Java Networking concepts of streams, threads, futures, and weblogs.
- Compile: javac MyPooledWeblog.java
- Execution: java MyPooledWeblog "access_log/access_log" <option>
- Notes: No user input is necessary, everything for this program is hardcoded
- Classes: 
	- MyPooledWeblog - contains everything necessary for proper program execution, as 
				specified in the program requirements.  This includes the main method, as
				well as one method for each command line option - accessesByRemoteHosts(),
				totalBytes(), and bytesByRemoteHost().  Additionally, there is a nested class
				LogEntry used to handle individual log entries.
	- LogEntry - nested in MyPooledWeblog - instance created for each individual log
				entry, associates a future to the entry and adds the class instance to queue.
- Variables:
	- NUM_THREADS - private final static int - used to set number of threads used (1500).
	- executor - ExecutorService - a fixed thread pool used to facilitate program execution.
	- results - Queue<LogEntry> - queue of LogEntry instances processed by program
	- task - LookupTask - used to process/store string log entries preparing for manipulation
	- future - Future<String> - stores futures that are associated with each entry
	- result - LogEntry - stores each entry and its associated futrure, added to queue
	- remoteHosts - Map<String, Integer> - used to store unique remoteHosts in web log
	- entryCount - int - used to store count of entries in web log
	- entry - String - used to store future associated with entry after processing
	- index - int - used in string manipulation, stores position for processing
	- address - String -  gets/stores address of host from log entry
	- ltotalBytes - long - used to store the total bytes in the weblog
	- entryBytes - long - used to store the total bytes in each entry
	- stringBytes - String - used for processing entries, determines whether entry included
			any transmission.
	- original - String - used to store the original entries in the web log
	- future - Future<String> - used to store futures associated with log entries

Test Plan
- Normal Case 1 (java MyPooledWeblog "access_log/access_log" 1):
		Runs program as constituted, without any alterations.
		Should display output as required in program specification.
		All testing built into program execution.
		> 1       206-15-133-153.dialup.ziplink.net
		> 1       lj1053.inktomisearch.com
		> 4       61.165.64.6
		> 19      proxy0.haifa.ac.il
		> 1       80.58.35.111.proxycache.rima-tde.net
		> 2       spica.ukc.ac.uk
		> 1       ipcorp-c8b07af1.terraempresas.com.br
		> 32      h24-70-69-74.ca.shawcable.net
		> 7       1-729.cnc.bc.ca
		> 28      ts04-ip92.hevanet.com
		> 1       h194n2fls308o1033.telia.com
		> 11      2-110.cnc.bc.ca
		> 3       61.9.4.61
		> 1       1513.cps.virtua.com.br
		> 1       adsl-64-173-42-65.dsl.snfc21.pacbell.net
		> 2       66-194-6-79.gen.twtelecom.net
		> 1       lj1052.inktomisearch.com
		> 2       lj1153.inktomisearch.com
		> 1       pd9e761cf.dip.t-dialin.net
		> 1       lj1025.inktomisearch.com
		> 29      market-mail.panduit.com
		> 2       lj1027.inktomisearch.com
		> 2       208-186-146-13.nrp3.brv.mn.frontiernet.net
		> 2       archserve.id.ucsb.edu
		> 10      3_343_lt_someone
		> 3       68-174-110-154.nyc.rr.com
		> 4       calcite.rhyolite.com
		> 14      prxint-sxb3.e-i.net
		> 6       h24-71-249-14.ca.shawcable.net
		> 2       acbf6930.ipt.aol.com
		> 1       pd95f99f2.dip.t-dialin.net
		> 22      ip68-228-43-49.tc.ph.cox.net
		> 1       lj1000.inktomisearch.com
		> 1       64-93-34-186.client.dsl.net
		> 1       alille-251-1-2-197.w82-124.abo.wanadoo.fr
		> 1       pd9eb1396.dip.t-dialin.net
		> 1       rouble.cc.strath.ac.uk
		> 1       lj1168.inktomisearch.com
		> 1       64.246.94.152
		> 1       lj1089.inktomisearch.com
		> 1       195.11.231.210
		> 1       ns.mou.cz
		> 1       206-15-133-181.dialup.ziplink.net
		> 16      ts05-ip44.hevanet.com
		> 9       crawl24-public.alexa.com
		> 4       mcl02.cnc.bc.ca
		> 3       67-131-107-5.dia.static.qwest.net
		> 7       lj1036.inktomisearch.com
		> 10      cpe-203-51-137-224.vic.bigpond.net.au
		> 13      200-55-104-193.dsl.prima.net.ar
		> 1       d97082.upc-d.chello.nl
		> 2       lj1164.inktomisearch.com
		> 1       vlp181.vlp.fi
		> 1       wwwcache.lanl.gov
		> 4       80.58.14.235.proxycache.rima-tde.net
		> 4       1-320.cnc.bc.ca
		> 7       142.27.64.35
		> 1       bh02i525f01.au.ibm.com
		> 1       osdlab.eic.nctu.edu.tw
		> 2       lj1156.inktomisearch.com
		> 1       lj1231.inktomisearch.com
		> 1       c-411472d5.04-138-73746f22.cust.bredbandsbolaget.se
		> 1       nb-bolz.cremona.polimi.it
		> 1       64.246.94.141
		> 4       194.151.73.43
		> 8       lj1024.inktomisearch.com
		> 3       ppp2.p33.is.com.ua
		> 1       korell2.cc.gatech.edu
		> 1       66-194-6-71.gen.twtelecom.net
		> 7       h24-70-56-49.ca.shawcable.net
		> 1       l07v-1-17.d1.club-internet.fr
		> 2       200.160.249.68.bmf.com.br
		> 1       px7wh.vc.shawcable.net
		> 1       wc03.mtnk.rnc.net.cable.rogers.com
		> 270     10.0.0.153
		> 3       wc09.mtnk.rnc.net.cable.rogers.com
		> 3       h24-68-45-227.gv.shawcable.net
		> 3       d207-6-9-183.bchsia.telus.net
		> 1       lj1216.inktomisearch.com
		> 1       prxint-sxb2.e-i.net
		> 1       195.230.181.122
		> 1       64-249-27-114.client.dsl.net
		> 12      p213.54.168.132.tisdip.tiscali.de
		> 2       grandpa.mmlc.northwestern.edu
		> 1       pc-030-040.eco.rug.nl
		> 2       lj1073.inktomisearch.com
		> 7       c-24-20-163-223.client.comcast.net
		> 1       lordgun.org
		> 1       212.21.228.26.dyn.user.ono.com
		> 1       lj1159.inktomisearch.com
		> 1       219.95.17.51
		> 1       80-219-148-207.dclient.hispeed.ch
		> 3       92-moc-6.acn.waw.pl
		> 1       12.22.207.235
		> 5       user-0c8hdkf.cable.mindspring.com
		> 4       ic8234.upco.es
		> 1       216.139.185.45
		> 1       lj1018.inktomisearch.com
		> 1       lj1120.inktomisearch.com
		> 5       spot.nnacorp.com
		> 14      128.227.88.79
		> 1       66.213.206.2
		> 20      207.195.59.160
		> 4       barrie-ppp108371.sympatico.ca
		> 11      208-38-57-205.ip.cal.radiant.net
		> 2       fw.kcm.org
		> 1       lj1007.inktomisearch.com
		> 1       lj1031.inktomisearch.com
		> 5       65-37-13-251.nrp2.roc.ny.frontiernet.net
		> 8       jacksonproject.cnc.bc.ca
		> 4       208.247.148.12
		> 1       lj1090.inktomisearch.com
		> 1       p5083cd5d.dip0.t-ipconnect.de
		> 1       lj1061.inktomisearch.com
		> 1       lj1016.inktomisearch.com
		> 14      212.92.37.62
		> 2       lj1162.inktomisearch.com
		> 1       d207-6-50-215.bchsia.telus.net
		> 13      lhr003a.dhl.com
		> 2       lj1223.inktomisearch.com
		> 23      mail.geovariances.fr
		> 5       pool-68-160-195-60.ny325.east.verizon.net
		> 452     64.242.88.10
		> 1       213.181.81.4
		> 7       mth-fgw.ballarat.edu.au
		> 13      pc3-registry-stockholm.telia.net
		> 2       watchguard.cgmatane.qc.ca
		> 1       4.37.97.186
		> 5       ladybug.cns.vt.edu
		> 1       lj1212.inktomisearch.com
		> 1       206-15-133-154.dialup.ziplink.net
		> 3       lj1117.inktomisearch.com
		> 2       lj1123.inktomisearch.com
		> 3       0x503e4fce.virnxx2.adsl-dhcp.tele.dk
		> 4       trrc02m01-40.bctel.ca
		> 3       mmscrm07-2.sac.overture.com
		> 5       lj1048.inktomisearch.com
		> 13      203.147.138.233
		> 2       c-24-11-14-147.client.comcast.net
		> 12      195.246.13.119
		> 12      216-160-111-121.tukw.qwest.net
		> 1       lj1088.inktomisearch.com
		> 3       dsl-80-43-113-44.access.uk.tiscali.com
		> 1       200.222.33.33
		> 1       lj1028.inktomisearch.com
		> 3       66-194-6-70.gen.twtelecom.net
		> 3       lj1008.inktomisearch.com
		> 7       business-145-253-208-009.static.arcor-ip.net
		> 1       lj1160.inktomisearch.com
		> 1       lj1115.inktomisearch.com
		> 3       ns3.vonroll.ch
		> 7       fw1.millardref.com
		> 3       lj1125.inktomisearch.com
		> 1       lj1105.inktomisearch.com
		> 12      ns.wtbts.org
		> 51      h24-71-236-129.ca.shawcable.net
		> 1       home.yeungs.net
		> 5       dialup-5-81.tulane.edu
		> 1       a213-84-36-192.adsl.xs4all.nl
		> 19      ogw.netinfo.bg
		> 1       ip-200-56-225-61-mty.marcatel.net.mx
		> 1       pd9e50809.dip.t-dialin.net
		> 1       lj1220.inktomisearch.com
		> 2       fw.aub.dk
		> 2       adsl-157-26-153.msy.bellsouth.net
		> 3       80.58.33.42.proxycache.rima-tde.net
		> 1       cacher2-ext.wise.edt.ericsson.se
		> 4       lj1145.inktomisearch.com
		> 4       yongsan-cache.korea.army.mil
		> 1       pntn02m05-129.bctel.ca
		> 1       2-238.cnc.bc.ca
		> 44      cr020r01-3.sac.overture.com
		> Total entries: 1546
		**All output as expected
Normal Case 2 (java MyPooledWeblog "access_log/access_log" 2):
		Runs program as constituted, without any alterations.
		Should display output as required in program specification.
		All testing built into program execution.
		> The total bytes transmitted was 12236649
		> The total entries examined was 1546
		**All output as expected (verified by manual calculations outside program)
Normal Case 3 (java MyPooledWeblog "access_log/access_log" 3):
		Runs program as constituted, without any alterations.
		Should display output as required in program specification.
		All testing built into program execution.
		> 0       206-15-133-153.dialup.ziplink.net
		> 209     lj1053.inktomisearch.com
		> 12224   61.165.64.6
		> 62152   proxy0.haifa.ac.il
		> 4114    80.58.35.111.proxycache.rima-tde.net
		> 3947    spica.ukc.ac.uk
		> 7368    ipcorp-c8b07af1.terraempresas.com.br
		> 150461  h24-70-69-74.ca.shawcable.net
		> 22838   1-729.cnc.bc.ca
		> 124082  ts04-ip92.hevanet.com
		> 5253    h194n2fls308o1033.telia.com
		> 110853  2-110.cnc.bc.ca
		> 7936    61.9.4.61
		> 309     1513.cps.virtua.com.br
		> 2300    adsl-64-173-42-65.dsl.snfc21.pacbell.net
		> 6338    66-194-6-79.gen.twtelecom.net
		> 68      lj1052.inktomisearch.com
		> 418     lj1153.inktomisearch.com
		> 2300    pd9e761cf.dip.t-dialin.net
		> 209     lj1025.inktomisearch.com
		> 99393   market-mail.panduit.com
		> 4824    lj1027.inktomisearch.com
		> 3378    208-186-146-13.nrp3.brv.mn.frontiernet.net
		> 3947    archserve.id.ucsb.edu
		> 62772   3_343_lt_someone
		> 16798   68-174-110-154.nyc.rr.com
		> 75383   calcite.rhyolite.com
		> 129557  prxint-sxb3.e-i.net
		> 26904   h24-71-249-14.ca.shawcable.net
		> 11763   acbf6930.ipt.aol.com
		> 2869    pd95f99f2.dip.t-dialin.net
		> 68712   ip68-228-43-49.tc.ph.cox.net
		> 7529    lj1000.inktomisearch.com
		> 2869    64-93-34-186.client.dsl.net
		> 2869    alille-251-1-2-197.w82-124.abo.wanadoo.fr
		> 2300    pd9eb1396.dip.t-dialin.net
		> 2869    rouble.cc.strath.ac.uk
		> 209     lj1168.inktomisearch.com
		> 0       64.246.94.152
		> 209     lj1089.inktomisearch.com
		> 6032    195.11.231.210
		> 2300    ns.mou.cz
		> 0       206-15-133-181.dialup.ziplink.net
		> 137620  ts05-ip44.hevanet.com
		> 64972   crawl24-public.alexa.com
		> 43518   mcl02.cnc.bc.ca
		> 16465   67-131-107-5.dia.static.qwest.net
		> 5415    lj1036.inktomisearch.com
		> 41851   cpe-203-51-137-224.vic.bigpond.net.au
		> 28333   200-55-104-193.dsl.prima.net.ar
		> 7368    d97082.upc-d.chello.nl
		> 5592    lj1164.inktomisearch.com
		> 2869    vlp181.vlp.fi
		> 2869    wwwcache.lanl.gov
		> 11985   80.58.14.235.proxycache.rima-tde.net
		> 43518   1-320.cnc.bc.ca
		> 36247   142.27.64.35
		> 2300    bh02i525f01.au.ibm.com
		> 269     osdlab.eic.nctu.edu.tw
		> 4828    lj1156.inktomisearch.com
		> 209     lj1231.inktomisearch.com
		> 2869    c-411472d5.04-138-73746f22.cust.bredbandsbolaget.se
		> 2300    nb-bolz.cremona.polimi.it
		> 0       64.246.94.141
		> 43518   194.151.73.43
		> 544     lj1024.inktomisearch.com
		> 10746   ppp2.p33.is.com.ua
		> 2869    korell2.cc.gatech.edu
		> 3169    66-194-6-71.gen.twtelecom.net
		> 27054   h24-70-56-49.ca.shawcable.net
		> 3169    l07v-1-17.d1.club-internet.fr
		> 13269   200.160.249.68.bmf.com.br
		> 7649    px7wh.vc.shawcable.net
		> 10936   wc03.mtnk.rnc.net.cable.rogers.com
		> 2189598 10.0.0.153
		> 32582   wc09.mtnk.rnc.net.cable.rogers.com
		> 20356   h24-68-45-227.gv.shawcable.net
		> 10281   d207-6-9-183.bchsia.telus.net
		> 209     lj1216.inktomisearch.com
		> 4022    prxint-sxb2.e-i.net
		> 2300    195.230.181.122
		> 7368    64-249-27-114.client.dsl.net
		> 69429   p213.54.168.132.tisdip.tiscali.de
		> 3947    grandpa.mmlc.northwestern.edu
		> 7368    pc-030-040.eco.rug.nl
		> 418     lj1073.inktomisearch.com
		> 35524   c-24-20-163-223.client.comcast.net
		> 2869    lordgun.org
		> 2869    212.21.228.26.dyn.user.ono.com
		> 209     lj1159.inktomisearch.com
		> 3169    219.95.17.51
		> 12846   80-219-148-207.dclient.hispeed.ch
		> 13759   92-moc-6.acn.waw.pl
		> 7368    12.22.207.235
		> 26861   user-0c8hdkf.cable.mindspring.com
		> 20941   ic8234.upco.es
		> 6051    216.139.185.45
		> 209     lj1018.inktomisearch.com
		> 5234    lj1120.inktomisearch.com
		> 29859   spot.nnacorp.com
		> 96238   128.227.88.79
		> 3169    66.213.206.2
		> 101980  207.195.59.160
		> 12067   barrie-ppp108371.sympatico.ca
		> 42134   208-38-57-205.ip.cal.radiant.net
		> 8568    fw.kcm.org
		> 68      lj1007.inktomisearch.com
		> 209     lj1031.inktomisearch.com
		> 27671   65-37-13-251.nrp2.roc.ny.frontiernet.net
		> 28921   jacksonproject.cnc.bc.ca
		> 12268   208.247.148.12
		> 3860    lj1090.inktomisearch.com
		> 7368    p5083cd5d.dip0.t-ipconnect.de
		> 209     lj1061.inktomisearch.com
		> 209     lj1016.inktomisearch.com
		> 72981   212.92.37.62
		> 7768    lj1162.inktomisearch.com
		> 3095    d207-6-50-215.bchsia.telus.net
		> 30310   lhr003a.dhl.com
		> 3883    lj1223.inktomisearch.com
		> 201567  mail.geovariances.fr
		> 18621   pool-68-160-195-60.ny325.east.verizon.net
		> 5745035 64.242.88.10
		> 7649    213.181.81.4
		> 38141   mth-fgw.ballarat.edu.au
		> 122885  pc3-registry-stockholm.telia.net
		> 11482   watchguard.cgmatane.qc.ca
		> 2446    4.37.97.186
		> 24099   ladybug.cns.vt.edu
		> 3169    lj1212.inktomisearch.com
		> 0       206-15-133-154.dialup.ziplink.net
		> 11180   lj1117.inktomisearch.com
		> 8876    lj1123.inktomisearch.com
		> 5025    0x503e4fce.virnxx2.adsl-dhcp.tele.dk
		> 12287   trrc02m01-40.bctel.ca
		> 204     mmscrm07-2.sac.overture.com
		> 340     lj1048.inktomisearch.com
		> 28136   203.147.138.233
		> 3947    c-24-11-14-147.client.comcast.net
		> 61543   195.246.13.119
		> 27810   216-160-111-121.tukw.qwest.net
		> 209     lj1088.inktomisearch.com
		> 7125    dsl-80-43-113-44.access.uk.tiscali.com
		> 2300    200.222.33.33
		> 209     lj1028.inktomisearch.com
		> 9507    66-194-6-70.gen.twtelecom.net
		> 627     lj1008.inktomisearch.com
		> 30494   business-145-253-208-009.static.arcor-ip.net
		> 68      lj1160.inktomisearch.com
		> 4156    lj1115.inktomisearch.com
		> 17915   ns3.vonroll.ch
		> 51879   fw1.millardref.com
		> 627     lj1125.inktomisearch.com
		> 209     lj1105.inktomisearch.com
		> 27736   ns.wtbts.org
		> 309105  h24-71-236-129.ca.shawcable.net
		> 2300    home.yeungs.net
		> 27978   dialup-5-81.tulane.edu
		> 3169    a213-84-36-192.adsl.xs4all.nl
		> 87276   ogw.netinfo.bg
		> 2869    ip-200-56-225-61-mty.marcatel.net.mx
		> 2869    pd9e50809.dip.t-dialin.net
		> 209     lj1220.inktomisearch.com
		> 13269   fw.aub.dk
		> 8568    adsl-157-26-153.msy.bellsouth.net
		> 17803   80.58.33.42.proxycache.rima-tde.net
		> 2869    cacher2-ext.wise.edt.ericsson.se
		> 5239    lj1145.inktomisearch.com
		> 12224   yongsan-cache.korea.army.mil
		> 3095    pntn02m05-129.bctel.ca
		> 3169    2-238.cnc.bc.ca
		> 627328  cr020r01-3.sac.overture.com
		> Total bytes transmitted was 12236649
		> The total entries examined was 1546
		**All output as expected (verified by manual calculations outside program)
Error Case (non-existant file):
		> Sorry, file was not found.  Please confirm file and try again.
***As this program is completely hardcoded, there is no user input at all, there are no
further cases to test.  The above demonstrates that the program works as required.
Discussion:
		The first three test cases demonstrate that each of the three options specified 
		in the requirements are working correctly.  These cases use the weblog provided
		in the course materials.  The results that the program displays were tested against
		manual calculations performed outside the program on the raw weblog.  All manual
		calculations matched up with the program results.
		The fourth test case tests the program when provided with a non-existant file.
		This was included as a very minimal error handling test.
