package fcn_hw;

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
		String website = "www.google.com.";
		int queryType = 1;
		String ipAddress = null;

		try {
			ipAddress = resolveDNS(website, queryType);
		} catch (Exception e) {
			// Do Nothing
		}
		if (ipAddress != null)
			System.out.println("DNS resolved for website : " + website + "   " + ipAddress);
		else
			System.out.println("Failed to resolve DNS resolved for: " + website);
	}

	public static String resolveDNS(String website, int queryType) {
		List<String> rootServers = getRootServerList();
		String ipAddress = null;

		for (int i = 0; i < rootServers.size(); i++) {
			String root = rootServers.get(i);
			try {
				ipAddress = resolveAddress(website, root, queryType);
			} catch (Exception e) {
				// Do nothing
			}
			if (ipAddress != null) {
				return ipAddress;
			}
		}
		return ipAddress;
	}

	@SuppressWarnings("unused")
	public static String resolveAddress(String website, String root, int queryType) {

		SimpleResolver resolver = null;
		Message query = null;
		Message response = null;
		try {
			resolver = new SimpleResolver(root);
			query = Message.newQuery(Record.newRecord(Name.fromString(website), queryType, DClass.IN));
			response = resolver.send(query);
		} catch (Exception e) {
			// Do Nothing
		}

		String ipAddress = null;

		if (response == null)
			return null;
		System.out.println(response);

		// check if answer set is not empty. If it's not empty return the first
		// value
		// else move forward
		List<String> answerList = getIPAddresses(response.sectionToString(1));
		if (answerList != null && answerList.size() > 0) {
			return answerList.get(0);
		}

		// check if authority is null -- return false to exit
		// else find recursive calls using additional information that contains
		// IP addressees of the auth. servers
		List<String> authorityListServers = getIPAddresses(response.sectionToString(2));
		if (authorityListServers == null || authorityListServers.size() < 1)
			return null;

		// find the list of ip Addresses in Additional section and use them to
		// find answer
		String result = response.sectionToString(3);
		List<String> ipAddressesAdditional = getIPAddresses(result);

		if (ipAddressesAdditional != null && ipAddressesAdditional.size() > 0) {
			// try each of the server one by one
			for (int i = 0; i < ipAddressesAdditional.size(); i++) {
				String currentRoot = ipAddressesAdditional.get(i);
				String address = resolveAddress(website, currentRoot, queryType);
				if (address != null)
					return address;
			}
		} else {

			// this is the last resort. This is the case when the server knows
			// the name of the server but not its IP Address. So we need to find
			// its IP before moving forward.

			for (int i = 0; i < authorityListServers.size(); i++) {
				String rootAddress = resolveDNS(authorityListServers.get(i), queryType);
				String address = resolveAddress(website, rootAddress, queryType);
				if (address != null)
					return address;
			}
		}
		return null;
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
