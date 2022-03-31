package Commons;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUI.PageUIs;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	  /**
	   * initial browser diver
	   * Configuration for browser
	   * return driver
	   */
	protected WebDriver initBroserDriver(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	  /**
	   * Wait for Element ready to click
	   */
	protected void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
	
	  /**
	   * Click to Element
	   */
	protected void clickToElement(WebDriver driver, String locator) {
		driver.findElement(By.xpath(locator)).click();
	}
	
	  /**
	   * Quit browser driver
	   */
	protected void tearDownBrowser(WebDriver driver) {
		driver.quit();;
	}

	  /**
	   * Click to Element By JavaScript
	   */
	protected void clickToElementByJS(WebDriver driver,String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}
	

	  /**
	   * Check Element display or not
	   *  return Boolean
	   */
	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	  /**
	   * GetText of Element
	   *  return Boolean
	   */
    protected String getTextElement(WebDriver driver,String locator) {

        return driver.findElement(By.xpath(locator)).getText();
    }

	  /**
	   *  Sleep in Seconds
	   */
    protected void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}
	  /**
	   * Scroll to Expected Element
	   */
    public void scrollToElement(WebDriver driver, String locator) {
    	jsExecutor = (JavascriptExecutor) driver;
    	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath(locator)));
    }
    
	  /**
	   * Scroll down 50 Pixel
	   */
    public void scrollTo50Pixel(WebDriver driver) {
    	jsExecutor = (JavascriptExecutor) driver;
    	jsExecutor.executeScript("window.scrollBy(0,50)");
    }
    
	  /**
	   * Move to Element
	   */
    public void moveToElement(WebDriver driver, String locator) {
    	Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(driver.findElement(By.xpath(locator))).perform();
    }
}
