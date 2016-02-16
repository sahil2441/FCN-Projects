package fcn_hw;

import java.util.HashMap;
import java.util.Map;

public class PerformanceMeasure {
	static final int queryType = 1;

	static final String[] websiteList = { "www.google.com.", "www.Facebook.com.", "www.Youtube.com.", "www.Baidu.com.",
			"www.Yahoo.com.", "www.Amazon.com.", "www.Wikipedia.org.", "www.Qq.com.", "www.Google.co.in.",
			"www.Twitter.com.", "www.Live.com.", "www.Taobao.com.", "www.Sina.com.cn.", "www.Msn.com.",
			"www.Yahoo.co.jp.", "www.Linkedin.com.", "www.Weibo.com.", "www.Google.co.jp.", "www.Vk.com.",
			"www.Bing.com.", "www.Yandex.ru.", "www.Hao123.com.", "www.Ebay.com.", "www.Instagram.com.",
			"www.Google.de." };

	public static void main(String[] args) {

		try {
			// experiment3();
			// experiment1();
			experiment2();
		} catch (Exception e) {
			// Do Nothing

		}

	}

	/**
	 * Experiment using Google's server
	 * 
	 * @throws Exception
	 */
	private static void experiment3() throws Exception {
		String googleDNS1 = "4.4.4.4";
		String googleDNS2 = "8.8.8.8";
		long startTime, endTime, currentTime, totalTime = 0;
		DNSResolver resolver = new DNSResolver();

		// map to maintain frequency
		Map<Long, Integer> countMap = new HashMap();

		int count = 0;
		for (int i = 0; i < websiteList.length; i++) {
			int j = 0;
			while (j++ < 10) {
				startTime = System.currentTimeMillis();
				resolver.resolveAddress(websiteList[i], googleDNS2, queryType);
				endTime = System.currentTimeMillis();
				currentTime = endTime - startTime;
				System.out.println("Iteration number:" + j + ",Time taken to resolve DNS for: " + websiteList[i] + " : "
						+ currentTime + "ms");
				totalTime += currentTime;
				count++;

				// update the map
				if (countMap.get(currentTime) != null)
					countMap.put(currentTime, countMap.get(currentTime) + 1);
				else
					countMap.put(currentTime, 1);
			}
		}
		System.out.println("Average time is: " + totalTime / count);

	}

	/**
	 * Experiment using the local DNS server
	 * 
	 * @throws Exception
	 */

	private static void experiment2() throws Exception {
		String localDNS = "192.168.43.1";
		// String localDNS = "205.251.193.21";

		long startTime, endTime, currentTime, totalTime = 0;
		DNSResolver resolver = new DNSResolver();

		int count = 0;
		for (int i = 0; i < websiteList.length; i++) {
			int j = 0;
			while (j++ < 10) {
				startTime = System.currentTimeMillis();
				resolver.resolveAddress(websiteList[i], localDNS, queryType);
				endTime = System.currentTimeMillis();
				currentTime = endTime - startTime;
				System.out.println("Iteration number:" + j + ",Time taken to resolve DNS for: " + websiteList[i] + " : "
						+ currentTime + "ms");
				totalTime += currentTime;
				count++;
			}
		}
		System.out.println("Average time is: " + totalTime / count);
	}

	/**
	 * Experiment using root and TLDs
	 * 
	 * @throws Exception
	 */

	private static void experiment1() throws Exception {
		long startTime, endTime, currentTime, totalTime = 0;
		DNSResolver resolver = new DNSResolver();

		for (int i = 0; i < websiteList.length; i++) {
			int j = 0;
			while (j++ < 10) {
				startTime = System.currentTimeMillis();
				resolver.resolveDNS(websiteList[i], queryType);
				endTime = System.currentTimeMillis();
				currentTime = endTime - startTime;
				System.out.println("Iteration number:" + j + ",Time taken to resolve DNS for: " + websiteList[i] + " : "
						+ currentTime + "ms");
				totalTime += currentTime;
			}
			System.out.println("Average time for website: " + websiteList[i] + " is " + totalTime / 25);
		}
	}
}
