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
	recursively.


##How to run the program

Part A:
The method 
fcn_hw.DNSResolver.resolveDNS(String, int)
takes two arguments - website name and the type of query and resolves the DNS.

To view the response at each step , uncomment the line 68
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

Part B:
The main method of the class: fcn_hw.MyDig
calls the method used in part A to resolve the DNS query.
The expected output is listed in the file: mydig_output.txt

Part C:
The class fcn_hw.PerformanceMeasure has 3 method corresponding to each of the 
experiments as listed in Assignment.

The CDF graphs can be found in the root folder of this submission, named:
experiment_1.png
experiment_2.png
experiment_3.png

Part D:


