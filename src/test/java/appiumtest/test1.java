package appiumtest;//package��

import java.io.FileNotFoundException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import comLib.ComUtils;
import io.appium.java_client.android.AndroidDriver;



public class test1
{

	private AndroidDriver driver;

	@BeforeMethod
	public void calc() throws Exception
	{
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");// ��䲻�Ǳ����
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", ".Calculator");
		driver = new AndroidDriver(new URL("http://16.165.188.10:4723/wd/hub"), capabilities);

	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		driver.quit();
	}



	@Test
	public void add() throws FileNotFoundException
	{

		//final WebElement element = null;
		//driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
		System.out.println("asdfasf");
		driver.findElementById("com.android.calculator2:id/digit_1").click();
		//driver.findElementById("com.android.calculator2:id/digit_1").click();
		//driver.findElementById("com.android.calculator2:id/digit_1").click();
		//driver.findElementByName("1");
		//driver.findElement(By.id("digit_1")).click();
		//driver.findElement(By.id("digit_1")).click();
		//driver.findElementById("digit_1").click();
		//element = ComUtils.getObjElement(driver, "digit_1", "id");
		//element.click();
		driver.findElementById("com.android.calculator2:id/op_add").click();
		driver.findElementById("com.android.calculator2:id/digit_2").click();
		driver.findElementById("com.android.calculator2:id/eq").click();
		//driver.findElementById("com.android.calculator2:id/digit_1").click();
		//		final String result = driver.findElementById("com.android.calculator2:id/formula").getText();
		//		try
		//		{
		//			System.out.println("succeessful to login");
		//			ComUtils.snapshot(driver, "success.png");
		//		}
		//		catch (final Exception e)
		//		{
		//			ComUtils.snapshot(driver, "exception.png");
		//			e.printStackTrace();
		//		}
		//		System.out.println(result);
		ComUtils.snapshot(driver, "screen");
		driver.quit();
		comLib.ComUtils.logWriter("Jenkins  ", "C:\\Users\\shaozi-y\\workspace\\test-java\\log\\log20161008.txt", true);
		comLib.ComUtils.createNewFile("C:\\Users\\shaozi-y\\workspace\\test-java\\log\\log20161009.txt");
	}

}
