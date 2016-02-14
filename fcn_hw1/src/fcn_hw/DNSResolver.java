package fcn_hw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;

public class DNSResolver {
	final static String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	static List<String> ipAddress = null;

	public static void main(String[] args) {
		String website = "www.google.com.";
		String root = "192.5.5.241";

		while (true) {
			try {
				if (resolveAddress(website, root)) {
					System.out.println("DNS Resolved for :" + website);
					printIPAddress();
					break;
				} else
					System.out.println("Failed to Resolved DNS for :" + website);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static boolean resolveAddress(String website, String root) throws IOException {

		SimpleResolver resolver = new SimpleResolver(root);
		Message query = Message.newQuery(Record.newRecord(Name.fromString(website), Type.A, DClass.IN));
		Message response = resolver.send(query);
		System.out.println(response);

		// check if answer is empty. If yes, repeat the step recursively
		List<String> answerList = getIPAddresses(response.sectionToString(1));
		if (answerList != null && answerList.size() > 0) {
			ipAddress = answerList;
			return true;
		}

		// else recursively call this method to Domain servers that are lower in
		// Hierarchy
		String result = response.sectionToString(3);
		List<String> ipAddresses = getIPAddresses(result);

		if (ipAddresses == null || ipAddresses.size() < 1)
			return false;

		// try each of the server one by one -- kind of backtracking
		for (int i = 0; i < ipAddresses.size(); i++) {
			String currentAddress = ipAddresses.get(i);
			boolean flag = false;
			try {
				flag = resolveAddress(website, currentAddress);
			} catch (Exception e) {
				// Do nothing
			}
			if (flag)
				return true;
		}
		return false;
	}

	private static void printIPAddress() {
		if (ipAddress != null) {
			for (int i = 0; i < ipAddress.size(); i++) {
				System.out.println(ipAddress.get(i));
			}
		}
	}

	private static List<String> getIPAddresses(String input) {
		String[] resultArray = input.split("\\s+");
		List<String> ipAddresses = new ArrayList();

		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);

		for (int i = 0; i < resultArray.length; i++) {
			Matcher matcher = pattern.matcher(resultArray[i]);
			if (matcher.find())
				ipAddresses.add(resultArray[i]);
		}

		return ipAddresses;
	}
}
