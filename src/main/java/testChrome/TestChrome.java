package testChrome;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TestChrome
{
	public static void main(final String[] args) throws IOException
	{
		// 璁剧疆 chrome 鐨勮矾寰�
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		// 鍒涘缓涓�涓� ChromeDriver 鐨勬帴鍙ｏ紝鐢ㄤ簬杩炴帴 Chrome
		@SuppressWarnings("deprecation")
		final ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\chromedriver_win32\\chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();
		// 鍒涘缓涓�涓� Chrome 鐨勬祻瑙堝櫒瀹炰緥
		final WebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

		// 璁╂祻瑙堝櫒璁块棶 Baidu
		driver.get("http://www.bing.com");
		// 鐢ㄤ笅闈唬鐮佷篃鍙互瀹炵幇
		// driver.navigate().to("http://www.baidu.com");

		// 鑾峰彇 缃戦〉鐨� title
		System.out.println("1 Page title is: " + driver.getTitle());

		// 閫氳繃 id 鎵惧埌 input 鐨� DOM
		//WebElement element = driver.findElement(By.id("kw"));
		final WebElement element = driver.findElement(By.id("sb_form_q"));
		// 杈撳叆鍏抽敭瀛�
		element.sendKeys("Hello World");

		// 鎻愪氦 input 鎵�鍦ㄧ殑 form
		element.submit();

		// 閫氳繃鍒ゆ柇 title 鍐呭绛夊緟鎼滅储椤甸潰鍔犺浇瀹屾瘯锛孴imeout 璁剧疆10绉�
		/*
		 * (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver d) {
		 * return d.getTitle().toLowerCase().endsWith("Hello World"); } });
		 */
		// 鏄剧ず鎼滅储缁撴灉椤甸潰鐨� title
		System.out.println("2 Page title is: " + driver.getTitle());

		// 鍏抽棴娴忚鍣�
		driver.quit();
		// 鍏抽棴 ChromeDriver 鎺ュ彛
		service.stop();

	}
}

