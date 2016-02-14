package fcn_hw;

public class PerformanceMeasure {
	static final int queryType = 1;

	static final String[] websiteList = { "www.google.com.", "www.Facebook.com.", "www.Youtube.com.", "www.Baidu.com.",
			"www.Yahoo.com.", "www.Amazon.com.", "www.Wikipedia.org.", "www.Qq.com.", "www.Google.co.in.",
			"www.Twitter.com.", "www.Live.com.", "www.Taobao.com.", "www.Sina.com.cn.", "www.Msn.com.",
			"www.Yahoo.co.jp.", "www.Linkedin.com.", "www.Weibo.com.", "www.Google.co.jp.", "www.Vk.com.",
			"www.Bing.com.", "www.Yandex.ru.", "www.Hao123.com.", "www.Ebay.com.", "www.Instagram.com.",
			"www.Google.de." };

	public static void main(String[] args) {

		// experiment1();
		experiment2();
		// experiment3();

	}

	private static void experiment3() {
		String googleDNS1 = "4.4.4.4";
		String googleDNS2 = "8.8.8.8";
		long startTime, endTime, currentTime, totalTime = 0;
		DNSResolver resolver = new DNSResolver();

		int count = 0;
		for (int i = 0; i < websiteList.length; i++) {
			int j = 0;
			while (j++ < 10) {
				startTime = System.currentTimeMillis();
				resolver.resolveDNSUsingCustomRoot(websiteList[i], queryType, googleDNS2);
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

	private static void experiment2() {
		String localDNS = "192.168.43.1";
		long startTime, endTime, currentTime, totalTime = 0;
		DNSResolver resolver = new DNSResolver();

		int count = 0;
		for (int i = 0; i < websiteList.length; i++) {
			int j = 0;
			while (j++ < 10) {
				startTime = System.currentTimeMillis();
				resolver.resolveDNSUsingCustomRoot(websiteList[i], queryType, localDNS);
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

	private static void experiment1() {
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
