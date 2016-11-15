package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


public class StarbukEnrTest
{
	private AndroidDriver driver;

	@Test
	public void StarbukEnr() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElementById("item_layout_2").click();
		driver.findElementByXPath(
				"//android.widget.ScrollView/android.widget.TableLayout/android.widget.TableRow/android.widget.RelativeLayout[1]/android.widget.LinearLayout")
				.click();


		driver.findElementByXPath(
				"//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.TextView")
				.click();
		driver.findElementById("tvSLAMarketName").click();

		//		driver.findElementByXPath(
		//				"//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView")
		//				.click();
		//driver.findElementByXPath("//android.widget.ListView/android.widget.LinearLayout[1]").click();
		WebElement element = null;
		element = driver.findElementByXPath(
				"//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout");


		//element = driver.findElementByXPath("//android.widget.ListView");
		//element = driver.findElementById("com.hp.starbucksmanager:id/lvSLAStationListGroupTab");
		final int xStart = element.getLocation().getX();
		System.out.println("起始坐标为" + xStart);
		// 获取控件最大宽度
		final int xEnd = xStart + element.getSize().getWidth();
		System.out.println("结束坐标为" + xEnd);
		final int yStart = element.getLocation().getY();
		System.out.println("高度起始为" + yStart);
		final int yEnd = element.getSize().getHeight();
		System.out.println("高度为" + yEnd);
		//final int height = driver.manage().window().getSize().height;

		//按住控件右边界向左200像素的地方， 往左滑动到向左600像素的地方，停留3秒
		//driver.swipe(xEnd - 200, yStart + 20, xEnd - 600, yStart + 20, 3000);

		//用touchAction方法进行滑动
		//在move to 里的坐标是偏移量
		final TouchAction touchAction = new TouchAction(driver);
		touchAction.press(xEnd - 200, yStart + 20).moveTo(-400, 0).waitAction(3000).perform();
		//释放按钮
		touchAction.release();

		driver.findElementById("com.hp.starbucksmanager:id/holder").click();


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
