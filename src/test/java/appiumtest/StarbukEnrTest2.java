package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;


public class StarbukEnrTest2
{
	private AndroidDriver driver;

	@Test
	public void StarbukEnr() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElementById("item_layout_2").click();
		driver.findElementByXPath(
				"//android.widget.ScrollView/android.widget.TableLayout/android.widget.TableRow/android.widget.RelativeLayout[2]/android.widget.LinearLayout")
				.click();
		String outPut;

		driver.findElementByXPath(
				"//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]")
				.getText();
		outPut = driver
				.findElementByXPath(
						"//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]")
				.getText();
		System.out.println(outPut);
		outPut = driver
				.findElementByXPath(
						"//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]")
				.getText();
		System.out.println(outPut);
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException
	{
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");// ��䲻�Ǳ����
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.hp.starbucksmanager");
		capabilities.setCapability("appActivity", ".SplashAct");
		driver = new AndroidDriver(new URL("http://16.165.188.10:4723/wd/hub"), capabilities);

	}

	@AfterMethod
	public void afterMethod()
	{

	}

}
