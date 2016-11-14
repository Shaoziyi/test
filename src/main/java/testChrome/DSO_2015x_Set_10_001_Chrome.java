package testChrome;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class DSO_2015x_Set_10_001_Chrome
{

	static StringBuffer verificationErrors = new StringBuffer();
	static boolean acceptNextAlert = true;
	static String baseUrl;
	static WebDriver driver;

	public static void main(final String[] args) throws IOException
	{
		// chrome path
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		//
		@SuppressWarnings("deprecation")
		final ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\chromedriver_win32\\chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();
		//
		baseUrl = "https://plmtest.pg.com/";
		final WebDriver driver1 = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

		//
		baseUrl = "https://plmtest.pg.com/";
		// WebDriver driver = new InternetExplorerDriver();

		//
		//driver.get("http://www.google.com");
		driver1.get(baseUrl);
		//driver.get(baseUrl + "/3DPassport/login?service=https%3A%2F%2Fplmtest.pg.com%2Fenovia%2F");
		final WebElement element = driver1.findElement(By.xpath("html/body/div/form/fieldset/div[3]/label/inpit"));
		element.clear();
		driver1.findElement(By.xpath("html/body/div/form/fieldset/div[3]/label/input")).sendKeys("dsotest27.im");
		//driver.findElement(By.name("username")).clear();
		//driver.findElement(By.name("username")).sendKeys("dsotest27.im");
		driver1.findElement(By.name("password")).clear();
		driver1.findElement(By.name("password")).sendKeys("Summer06");

		//driver.findElement(By.className("dark-grey uwa-button uwa-input")).click();

		//driver.findElement(By.xpath("//html/body/div[1]/form/div/input")).submit();
		driver.findElement(By.xpath("//html/body/div[1]/form/div/input]")).click();
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		//driver.findElement(By.cssSelector("#CPNActions > a > label")).click();
		//driver.findElement(By.xpath("//div[5]/div[2]/ul/li/a/label")).click();
		driver.findElement(By.xpath(".//*[@id='submitButton']")).click();
		driver.findElement(By.xpath(".//*[@id='submitButton']")).click();
		driver.findElement(By.xpath("html/body/div[19]/div[1]/span")).click();
		driver.findElement(By.xpath("html/body/div[19]/div[2]/div[5]/div[2]/ul/li[1]/a/label")).click();

		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | ModalDialog1471507720558 | 30000]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=ModalDialog1471507720558 | ]]
		new Select(driver.findElement(By.id("TypeActualId"))).selectByVisibleText("Packaging Assembly Part");
		// ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
		new Select(driver.findElement(By.id("StageId"))).selectByVisibleText("Experimental");
		driver.findElement(By.id("TitleId")).clear();
		driver.findElement(By.id("TitleId")).sendKeys("ttt");
		driver.findElement(By.name("SpellCheck")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | spchplmtest_pg_com | 30000]]
		driver.findElement(By.id("DescriptionId")).clear();
		driver.findElement(By.id("DescriptionId")).sendKeys("ttt");
		driver.findElement(By.name("OrganizationDisplay")).clear();
		driver.findElement(By.name("OrganizationDisplay")).sendKeys("t");
		new Select(driver.findElement(By.id("SegmentId"))).selectByVisibleText("A&RP Bottoms/Remnants");
		driver.findElement(By.name("ChangeTemplateDisplay")).clear();
		driver.findElement(By.name("ChangeTemplateDisplay")).sendKeys("t");
		driver.findElement(By.name("CODisplay")).clear();
		driver.findElement(By.name("CODisplay")).sendKeys("t");
	}


	public void tearDown() throws Exception
	{
		driver.quit();
		final String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString))
		{
			System.out.println("1 fail catching:" + verificationErrorString);
		}
	}

	private boolean isElementPresent(final By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch (final NoSuchElementException e)
		{
			return false;
		}
	}

	private boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (final NoAlertPresentException e)
		{
			return false;
		}
	}

	private String closeAlertAndGetItsText()
	{
		try
		{
			final Alert alert = driver.switchTo().alert();
			final String alertText = alert.getText();
			if (acceptNextAlert)
			{
				alert.accept();
			}
			else
			{
				alert.dismiss();
			}
			return alertText;
		}
		finally
		{
			acceptNextAlert = true;
		}
	}
	/*
	 *
	 * public static void main(String[] args) {
	 *
	 * // create IE web driver
	 *
	 *
	 * // File( "C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe" ); //set the
	 * display rate of the page DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	 * caps.setCapability("ignoreZoomSetting", true);
	 *
	 * //set Webdriver path File file = new File(
	 * "C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe" );
	 * System.setProperty("webdriver.ie.driver", file.getAbsolutePath()); WebDriver driver = new
	 * InternetExplorerDriver(caps); driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 *
	 * // WebDriver driver = new InternetExplorerDriver();
	 *
	 * // browser to read Bing driver.get("https://plmtest.pg.com/"); // or use following code //
	 * driver.navigate().to("http://www.baidu.com");
	 *
	 *
	 * // close driver.quit(); }
	 */
}
