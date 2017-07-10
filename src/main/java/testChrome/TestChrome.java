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
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException
	{
		//
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		//
		@SuppressWarnings("deprecation")
		final ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\chromedriver_win32\\chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();
		//
		final WebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

		//
		driver.get("http://www.bing.com");
		//
		// driver.navigate().to("http://www.baidu.com");

		//
		System.out.println("1 Page title is: " + driver.getTitle());

		//
		//WebElement element = driver.findElement(By.id("kw"));
		final WebElement element = driver.findElement(By.id("sb_form_q"));
		//
		element.sendKeys("Hello World");

		//
		element.submit();

		//
		/*
		 * (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver d) {
		 * return d.getTitle().toLowerCase().endsWith("Hello World"); } });
		 */
		//
		System.out.println("2 Page title is: " + driver.getTitle());

		//
		driver.quit();
		//
		service.stop();

	}
}

