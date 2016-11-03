package testIE;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


@SuppressWarnings("javadoc")
public class TestIEExample
{
	private static Map<String, String> dbMap;

	public static void main(final String[] args)
	{

		// 鍒涘缓IE web driver



		//璁剧疆椤甸潰姣斾緥
		final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);

		//璁剧疆Webdriver璺緞
		final File file = new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		final WebDriver driver = new InternetExplorerDriver(caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// WebDriver driver = new InternetExplorerDriver();

		// 璁╂祻瑙堝櫒璁块棶 Bing
		driver.get("http://www.google.com");
		// 鐢ㄤ笅闈唬鐮佷篃鍙互瀹炵幇
		// driver.navigate().to("http://www.baidu.com");

		// 鑾峰彇 缃戦〉鐨� title
		System.out.println("1 Page title is: " + driver.getTitle());

		// 閫氳繃 id 鎵惧埌 input鐨勮緭鍏ユ鍏冪礌

		final WebElement element = driver.findElement(By.id("lst-ib"));
		//WebElement element = driver.findElement(By.id("sb_form_q"));

		// 杈撳叆鍏抽敭瀛�
		element.sendKeys("Hello World");

		// 鎻愪氦 input 鎵�鍦ㄧ殑 form
		element.submit();

		// 閫氳繃鍒ゆ柇 title 鍐呭绛夊緟鎼滅储椤甸潰鍔犺浇瀹屾瘯锛孴imeout 璁剧疆10绉�
		/*
		 * (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver d) {
		 * return d.getTitle().toLowerCase().endsWith("ztree"); } });
		 */
		// 鏄剧ず鎼滅储缁撴灉椤甸潰鐨� title
		System.out.println("2 Page title is: " + driver.getTitle());

		// 鍏抽棴娴忚鍣�
		driver.quit();
	}
}
