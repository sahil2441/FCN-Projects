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


