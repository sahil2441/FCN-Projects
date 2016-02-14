package fcn_hw;

public class MyDig {
	static String website = "www.cs.stonybrook.edu.";
	static int queryType = 2;

	public static void main(String[] args) {
		DNSResolver resolver = new DNSResolver();
		resolver.resolveDNS(website, queryType);
	}

}
