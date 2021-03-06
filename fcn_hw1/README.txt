####################################################
###### SAHIL JAIN
###### sahjain@cs.stonybrook.edu
####################################################

##External Library
-- I used DNSJava (import org.xbill.DNS.*)

##High Level View of design
-- The class fcn_hw/DNSResolver.java contains the method
	fcn_hw.DNSResolver.resolveDNS(String, int)
	that takes two arguments - website name and the Type. This method resolves the DNS
	recursively by calling 
	fcn_hw.DNSResolver.resolveAddress(String, String, int)
	that takes a third argument as well, which is the root server which we need to contact.


##How to run the program

####################################################
Part A:
####################################################

NOTE: To view response, uncomment line 94 in 
fcn_hw.DNSResolver.resolveAddress(String, String, int)

The method 
fcn_hw.DNSResolver.resolveDNS(String, int)
takes two arguments - website name and the type of query and resolves the DNS.

To view the response at each step , uncomment the line 96
// System.out.println(response);
in the method:
fcn_hw.DNSResolver.resolveAddress(String, String, int)

The method 
fcn_hw.DNSResolver.resolveDNS(String, int)
call the method
fcn_hw.DNSResolver.resolveAddress(String, String, int) 
passing each of top level root to resolve DNS.

There could three possible scenarios:
1. The answer section in the DNS in non empty and therefore contains the final answer desired.
2. The answer section is empty, but the additional section (that contains the IP addresses of each of the 
authoritative section) is non empty. And therefore we try a backtracking solution corresping to each of the 
server address in this section.

3. The answer section and additional section is empty, however the authoritative isn't. This implies that we need to
find the IP address of the server names listed in the authoritative section (recursively) and then find the desired
IP Address.

The fourth trivial case would be all four sections would be null and therefore DNS resolution would fail.

To change the website name and queryType, make changes to  
fcn_hw.DNSResolver.website and
fcn_hw.DNSResolver.queryType

To run this programs as script, refer to the .sh file:
/fcn_hw/src/mydnsresolver.sh

####################################################
Part B:
####################################################

The main method of the class: fcn_hw.MyDig
calls the method used in part A to resolve the DNS query.
The expected output is listed in the file: mydig_output.txt

####################################################
Part C:
####################################################

The class fcn_hw.PerformanceMeasure has 3 method corresponding to each of the 
experiments as listed in Assignment.

Explanation of Results:
If you observe the CDF for all three experiments, it shows that time taken to resolve DNS query is in the 
following order:
Local DNS Server < Google's DNS < Root Servers

The reason for above observation is obvious.

For experiment 1, 80% of the queries took less than 40,000 ms.
For experiment 2, 80% of the queries took less than 5 ms.
For experiment 3, 80% of the queries took less than 10 ms.

The CDF graphs can be found in the root folder of this submission, named:
experiment_1.png
experiment_2.png
experiment_3.png

The results are listed in the file: performance_measure_PartC.txt

####################################################
Part D:
####################################################

Refer to the image file in the root directory -- dns_packet.png or
DNS_packet_partD.txt


