package fcn_hw;

public class MyDig {
	static String website = "www.cs.stonybrook.edu.";
	static int queryType = 2;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		DNSResolver resolver = new DNSResolver();
		String address = null;
		try {
			address = resolver.resolveDNS(website, queryType);
		} catch (Exception e) {
			// Do Nothing
		}

		if (address != null)
			System.out.println("Resolved DNS for: " + website + " and query type: " + queryType + "   " + address);
		else
			System.out.println("Failed to resolve DNS resolved for: " + website);
	}
}
