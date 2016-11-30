package com.grid.demo;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GridDemo
{
	/**
	 * @param nodeURL
	 *           node 节点的地址
	 * @param browser
	 *           node 节点的浏览器
	 * @throws IOException
	 */
	@Test(dataProvider = "data")
	public void openBaiduPageTest(final String nodeURL, final String browser) throws IOException
	{
		DesiredCapabilities desiredCapabilities;
		//      判断要打开的浏览器

		if (browser == "chrome")
		{
			desiredCapabilities = DesiredCapabilities.chrome();
		}
		else if (browser == "ie")
		{
			desiredCapabilities = DesiredCapabilities.internetExplorer();
		}
		else
		{
			desiredCapabilities = DesiredCapabilities.firefox();
		}

		//      拼接处要执行脚本的node 节点地址
		final String url = nodeURL + "/wd/hub";
		final WebDriver driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
		//      打开百度
		driver.get("http://www.baidu.com");
		System.out.println(browser + driver.getTitle());
		//      关闭浏览器
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod()
	{
	}

	@AfterMethod
	public void afterMethod()
	{
	}


	@DataProvider
	public Object[][] data()
	{
		return new Object[][]
		{
				{ "http://16.165.188.82:5555", "chrome" },
				//{ "http://16.165.188.82:5555", "ie" },
		};
	}
}
