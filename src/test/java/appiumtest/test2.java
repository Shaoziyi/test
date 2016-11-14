package appiumtest;//package��

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import comLib.ComUtils;
import comLib.GUIObj;
import io.appium.java_client.android.AndroidDriver;


public class test2
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
		capabilities.setCapability("appPackage", "com.innovaee.eorder");
		capabilities.setCapability("appActivity", ".mobile.view.MainViewActivity");
		driver = new AndroidDriver(new URL("http://16.165.188.10:4723/wd/hub"), capabilities);

	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		driver.quit();
	}



	@Test
	public void add() throws IOException
	{
		Map<String, GUIObj> objMap = new HashMap<String, GUIObj>();
		objMap = ComUtils.createWorkBook("homepage", "C:\\Users\\shaozi-y\\workspace\\test-java\\obj\\objects.xlsx");
		WebElement element = null;
		element = ComUtils.getObjElement(driver, "feed_type", objMap);


		String orderCount = "0";
		System.out.println(orderCount);
		element.click();
		//final WebElement element = driver.findElement(By.id("com.innovaee.eorder:id/feed_type_name"));

		driver.findElementByName("川菜").click();
		//driver.findElementByXPath("//android.widget.GridView[@text=\"口水鸡\"]").click();
		//按照节点查找text = 口水鸡的textview
		driver.findElementByXPath("//android.widget.TextView[contains(@text, '口水鸡')]").click();
		//按照节点找Xpath， imageView， 其中[1]表示第一个节点， 不是从[0]开始
		driver.findElementByXPath(
				"//android.widget.GridView[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[contains(@index, '0')]")
				.click();
		//driver.findElementByName("口水鸡").click();
		orderCount = driver.findElementById("com.innovaee.eorder:id/order_count").getText();
		System.out.println(orderCount);
		//driver.findElementByName("口水鸡").click();
		//driver.findElementByAndroidUIAutomator("1");
		//		driver.findElementByLinkText("川菜").click();
		//		driver.findElementByLinkText("水煮牛肉").click();
		orderCount = driver.findElementById("com.innovaee.eorder:id/order_count").getText();
		System.out.println(orderCount);
		driver.findElement(By.id("com.innovaee.eorder:id/order")).click();
		driver.quit();
	}

}
