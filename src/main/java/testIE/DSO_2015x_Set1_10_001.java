package testIE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import comLib.ComUtils;


public class DSO_2015x_Set1_10_001
{
	static StringBuffer verificationErrors = new StringBuffer();
	static boolean acceptNextAlert = true;
	static String baseUrl;
	static WebDriver driver1;
	private static Map<String, ArrayList<String>> objMap;
	private static HashMap<String, String> dbMap;


	@SuppressWarnings("javadoc")
	public static void main(final String[] args) throws IOException
	{
		//		final String conString = "\"" + "jdbc:mysql://16.187.126.128:3306/test" + "\"" + "," + "\"" + "hive" + "\"" + "," + "\""
		//				+ "hive" + "\"";
		final String conString = "jdbc:mysql://16.187.126.128:3306/test";
		final String user = "hive";
		final String pass = "hive";
		dbMap = new HashMap<String, String>();
		dbMap = (HashMap<String, String>) ComUtils.connectDB(conString, user, pass, "select * from a1");

		// 鍒涘缓IE web driver

		// File("C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		//璁剧疆椤甸潰姣斾緥
		final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);

		//璁剧疆Webdriver璺緞
		final File file = new File("C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		final WebDriver driver = new InternetExplorerDriver(caps);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		baseUrl = "https://plmtest.pg.com/";
		// WebDriver driver = new InternetExplorerDriver();

		// 璁╂祻瑙堝櫒璁块棶 Bing
		//driver.get("http://www.google.com");
		driver.get(baseUrl);
		objMap = new HashMap<String, ArrayList<String>>();
		//objMap = ComUtils.createWorkBook("Home", "C:\\Automation\\PrivateObjs\\DSMObjs.xlsx");
		final String xPath = objMap.get("BR3DPlat").get(1);

		//driver.get(baseUrl + "/3DPassport/login?service=https%3A%2F%2Fplmtest.pg.com%2Fenovia%2F");
		driver.findElement(By.xpath(xPath)).clear();
		driver.findElement(By.xpath("html/body/div/form/fieldset/div[3]/label/input")).clear();
		driver.findElement(By.xpath("html/body/div/form/fieldset/div[3]/label/input")).sendKeys("dsotest27.im");
		//driver1.findElement(By.name("username")).clear();
		//driver1.findElement(By.name("username")).sendKeys("dsotest27.im");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Summer06");

		//driver.findElement(By.className("dark-grey uwa-button uwa-input")).click();

		driver.findElement(By.xpath("html/body/div/form/div/button")).submit();
		//driver1.findElement(By.xpath("//html/body/div[1]/form/div/input")).submit();
		//driver1.findElement(By.xpath("//html/body/div[1]/form/div/input")).click();
		//driver1.findElement(By.xpath("//input[@value='Log in']")).click();
		//driver.findElement(By.cssSelector("#CPNActions > a > label")).click();
		//driver.findElement(By.xpath("//div[5]/div[2]/ul/li/a/label")).click();
		driver.findElement(By.id("submitButton")).click();
		//driver.findElement(By.xpath(".//*[@id='submitButton']")).click();
		//driver1.findElement(By.xpath(".//*[@id='submitButton']")).click();
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



	/*
	 *
	 * public static void main(String[] args) {
	 *
	 * // 鍒涘缓IE web driver
	 *
	 *
	 * // File( "C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe" );
	 * //璁剧疆椤甸潰姣斾緥 DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	 * caps.setCapability("ignoreZoomSetting", true);
	 *
	 * //璁剧疆Webdriver璺緞 File file = new File(
	 * "C:\\Users\\shaozi-y\\workspace\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe" );
	 * System.setProperty("webdriver.ie.driver", file.getAbsolutePath()); WebDriver driver = new
	 * InternetExplorerDriver(caps); driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 *
	 * // WebDriver driver = new InternetExplorerDriver();
	 *
	 * // 璁╂祻瑙堝櫒璁块棶 Bing driver.get("https://plmtest.pg.com/"); // 鐢ㄤ笅闈唬鐮佷篃鍙互瀹炵幇 //
	 * driver.navigate().to("http://www.baidu.com");
	 *
	 *
	 * // 鍏抽棴娴忚鍣� driver.quit(); }
	 */
	public void tearDown() throws Exception
	{
		driver1.quit();
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
			driver1.findElement(by);
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
			driver1.switchTo().alert();
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
			final Alert alert = driver1.switchTo().alert();
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

}
