
#Compile
javac -cp /Users/sahiljain/Google_Drive/SBU/Academics/Spring_16/CSE534/fcn_hw/fcn_hw1/org.xbill.dns_2.1.7.jar /Users/sahiljain/Google_Drive/SBU/Academics/Spring_16/CSE534/fcn_hw/fcn_hw1/src/fcn_hw/DNSResolver.java

#Run from one level above main class
# Go to the parent directory of the directory that contains the package
cd /Users/sahiljain/Google_Drive/SBU/Academics/Spring_16/CSE534/fcn_hw/fcn_hw1/src/ 

# Run the program by giving the jar in classpath
java -jar /Users/sahiljain/Google_Drive/SBU/Academics/Spring_16/CSE534/fcn_hw/fcn_hw1/org.xbill.dns_2.1.7.jar fcn_hw.DNSResolver www.facebook.com 