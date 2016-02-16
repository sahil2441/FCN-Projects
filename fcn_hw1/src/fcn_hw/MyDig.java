package fcn_hw;

public class MyDig {
	static String website = "www.cs.stonybrook.edu.";
	static int queryType = 3;

	public static void main(String[] args) {
		DNSResolver resolver = new DNSResolver();
		try {
			resolver.resolveDNS(website, queryType);
		} catch (Exception e) {
			// Do Nothing

		}
	}

}
