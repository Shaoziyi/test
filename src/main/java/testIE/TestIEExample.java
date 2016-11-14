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

		// IE web driver



		//
		final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);

		//
		final File file = new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		final WebDriver driver = new InternetExplorerDriver(caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// WebDriver driver = new InternetExplorerDriver();

		//
		driver.get("http://www.google.com");
		//
		// driver.navigate().to("http://www.baidu.com");

		//
		System.out.println("1 Page title is: " + driver.getTitle());

		//

		final WebElement element = driver.findElement(By.id("lst-ib"));
		//WebElement element = driver.findElement(By.id("sb_form_q"));

		//
		element.sendKeys("Hello World");

		//
		element.submit();

		//
		/*
		 * (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver d) {
		 * return d.getTitle().toLowerCase().endsWith("ztree"); } });
		 */
		//
		System.out.println("2 Page title is: " + driver.getTitle());

		//
		driver.quit();
	}
}
