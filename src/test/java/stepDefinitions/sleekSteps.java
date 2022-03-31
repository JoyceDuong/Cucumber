package stepDefinitions;


import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUI.PageUIs;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class sleekSteps {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String url = "https://sleek.com/sg/";

	@Given("^I went to the Sleek SG Home page$")
	public void iWentToTheSleekSGHomePage() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@When("^I click on the Pricing link$")
	public void iClickOnThePricingLink() {
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(PageUIs.PRICING_LINK)));
		driver.findElement(By.xpath(PageUIs.PRICING_LINK)).click();

	}

	@Then("^I should be navigated to the Sleek SG Pricing page$")
	public void iShouldBeNavigatedToTheSleekSGPricingPage() {
		Assert.assertTrue(driver.findElement(By.xpath(PageUIs.PRICING_TEXT_VALIDATE)).isDisplayed());
	}

	@Then("^I quit browser$")
	public void iQuitBrowser() {
		driver.quit();
	}

	@And("^I click on LEARN MORE  button for Corporate secretary$")
	public void iClickOnLEARNMOREButtonForCorporateSecretary() {
		
		waitForElementClickable(PageUIs.LEARN_MORE_BUTTON);
		driver.findElement(By.xpath(PageUIs.LEARN_MORE_BUTTON)).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@When("^I click on accounting line by (\\d+)$")
	public void iClickOnAccountingLineBy(int no)  {		
		jsExecutor = (JavascriptExecutor) driver;	

		jsExecutor.executeScript("arguments[0].scrollIntoView(false);",driver.findElement(By.xpath("//p[text()='Common add-ons that are not included in the yearly price:']")));
		jsExecutor.executeScript("window.scrollBy(0,50)");
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(driver.findElement(By.xpath("//p[text()='Common add-ons that are not included in the yearly price:']"))).perform();
		//waitForElementVisible("//div[@class='initial-message-bubble']");
		
		

		if (no == 1) {
			waitForElementClickable(PageUIs.PLAN_2_BUTTON);
			clickToElementByJS(PageUIs.PLAN_2_BUTTON);
//			clickToElement(PageUIs.PLAN_2_BUTTON);
//			WebElement button2 = driver.findElement(By.xpath(PageUIs.PLAN_2_BUTTON));
//			Actions actionBuilder = new Actions(driver);
//			actionBuilder.click(button2).build().perform();
			
		} else if (no == 2) {
			System.out.println("Đã vào click 2");
			waitForElementClickable(PageUIs.PLAN_4_BUTTON);
			clickToElementByJS(PageUIs.PLAN_4_BUTTON);
//			clickToElement(PageUIs.PLAN_4_BUTTON);	
//			WebElement button2 = driver.findElement(By.xpath(PageUIs.PLAN_4_BUTTON));
//			Actions actionBuilder = new Actions(driver);
//			actionBuilder.click(button2).build().perform();
		}else  {
			waitForElementClickable(PageUIs.PLAN_7_BUTTON);
			clickToElementByJS(PageUIs.PLAN_7_BUTTON);
//			clickToElement(PageUIs.PLAN_7_BUTTON);
//			WebElement button2 = driver.findElement(By.xpath(PageUIs.PLAN_7_BUTTON));
//			Actions actionBuilder = new Actions(driver);
//			actionBuilder.click(button2).build().perform();
		}
 
	}
	

	@Then("^Verify by (\\d+) noShareholders with (\\d+) Shareholders and pricePerYear with \\$(\\d+)/year$")
	public void verifyByNoShareholdersWithShareholdersAndPricePerYearWith$Year(int no, String noShareholders, String price) {

		String shareholderText;
		String priceText;
		
		System.out.println(noShareholders);
		System.out.println(price);
		System.out.println(no);
		
		if (no == 1) {
			System.out.println("Đã vào verify 1");
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN2_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN2_TEXT)).getText();
		} else if (no == 2) {	
			System.out.println("Đã vào verify 2");
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN4_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN4_TEXT)).getText();

		}else  {
			System.out.println("Đã vào verify 3");
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN7_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN7_TEXT)).getText();
		}
	   
		Assert.assertEquals(noShareholders, shareholderText);
		Assert.assertEquals("S"+ price,priceText);
	}


	public void waitForElementClickable( String xpath) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void clickToElement( String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	   
    public void clickToElementByJS( String xpath) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(xpath));
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    public void waitForElementVisible(String locator){
        explicitWait = new WebDriverWait(driver,20);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
	
    }


