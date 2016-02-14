package fcn_hw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.SimpleResolver;

public class DNSResolver {
	static List<String> ipAddress = null;

	public static void main(String[] args) {
		String website = "www.cs.stonybrook.edu.";
		int queryType = 1;
		resolveDNS(website, queryType);
	}

	public static void resolveDNS(String website, int queryType) {
		List<String> rootServers = getRootServerList();
		boolean success = false;

		for (int i = 0; i < rootServers.size(); i++) {
			if (success)
				break;
			String root = rootServers.get(i);
			while (true) {
				try {
					if (resolveAddress(website, root, queryType)) {
						System.out.println("DNS Resolved for :" + website);
						printIPAddress();
						success = true;
						break;
					} else
						System.out.println("Failed to Resolved DNS for :" + website);

				} catch (IOException e) {
					// Do nothing
				}
			}
		}
	}

	public static void resolveDNSUsingCustomRoot(String website, int queryType, String root) {
		boolean success = false;
		while (true) {
			try {
				if (resolveAddress(website, root, queryType)) {
					System.out.println("DNS Resolved for :" + website);
					printIPAddress();
					success = true;
					break;
				} else
					System.out.println("Failed to Resolved DNS for :" + website);

			} catch (IOException e) {
				// Do nothing
			}
		}
	}

	public static boolean resolveAddress(String website, String root, int queryType) throws IOException {

		SimpleResolver resolver = new SimpleResolver(root);
		Message query = Message.newQuery(Record.newRecord(Name.fromString(website), queryType, DClass.IN));
		Message response = resolver.send(query);
		if (response == null)
			return false;
		// System.out.println(response);

		// check if answer is empty. If yes, repeat the step recursively
		List<String> answerList = getIPAddresses(response.sectionToString(1));
		if (answerList != null && answerList.size() > 0) {
			ipAddress = answerList;
			return true;
		}

		// check if authority is null -- return true to exit
		List<String> authorityList = getIPAddresses(response.sectionToString(2));
		if (authorityList == null || authorityList.size() < 1)
			return true;

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
				flag = resolveAddress(website, currentAddress, queryType);
			} catch (Exception e) {
				// Do nothing
			}
			if (flag)
				return true;
		}
		return false;
	}

	public static void printIPAddress() {
		if (ipAddress != null) {
			for (int i = 0; i < ipAddress.size(); i++) {
				System.out.println(ipAddress.get(i));
			}
		}
	}

	public static List<String> getIPAddresses(String input) {
		String[] resultArray = input.split("\\s+");
		List<String> ipAddresses = new ArrayList();

		for (int i = 1; i < resultArray.length; i++) {
			if ((i + 1) % 5 == 0)
				ipAddresses.add(resultArray[i]);
		}

		return ipAddresses;
	}

	public static List<String> getRootServerList() {
		String[] array = { "198.41.0.4", "192.228.79.201", "192.33.4.12", "199.7.91.13", "192.203.230.10",
				"192.5.5.241", "192.112.36.4", "198.97.190.53", "192.36.148.17", "192.58.128.30", "193.0.14.129",
				"199.7.83.42", "202.12.27.33", };
		return Arrays.asList(array);
	}

}
