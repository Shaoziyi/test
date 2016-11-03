package testFireFox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ExampleForFireFox  {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
    	
    	
        WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        driver.quit();
    }
}

/*
public class ExampleForFireFox  {
    public static void main(String[] args) {
    	// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
    	System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
    	// 创建一个 FireFox 的浏览器实例
        WebDriver driver = new FirefoxDriver();

        // 让浏览器访问 Baidu
        driver.get("http://www.baidu.com");
        // 用下面代码也可以实现
        //driver.navigate().to("http://www.bing.com");

        // 获取 网页的 title
        System.out.println("1 Page title is: " + driver.getTitle());

        // 通过 id 找到 input 的 DOM
        WebElement element = driver.findElement(By.id("kw"));

        // 输入关键字
        element.sendKeys("zTree");

        // 提交 input 所在的  form
        element.submit();
        
        // 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().endsWith("ztree");
            }
        });

        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());
        
        //关闭浏览器
        driver.quit();
    }
}
*/