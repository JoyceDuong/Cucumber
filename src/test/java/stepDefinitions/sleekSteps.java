package stepDefinitions;


import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	JavascriptExecutor js;
	String url = "https://sleek.com/sg/";

	@Given("^I went to the Sleek SG Home page$")
	public void iWentToTheSleekSGHomePage() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		//driver = new ChromeDriver();
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
	}
	
	@When("^I click on accounting line by (\\d+)$")
	public void iClickOnAccountingLineBy(int no)  {		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//div[@class='elementor-widget-wrap elementor-element-populated elementor-motion-effects-element']")));
		
		//p[text()='All plans include:']
		
		if (no == 1) {
			clickToElement(PageUIs.PLAN_2_BUTTON);
		} else if (no == 2) {
			clickToElement(PageUIs.PLAN_4_BUTTON);	
		}else  {
			clickToElement(PageUIs.PLAN_7_BUTTON);
		}
 
	}

	@Then("^Verify noShareholders with (\\d+) Shareholders and pricePerYear with \\$(\\d+)/year by (\\d+)$")
	public void verifyNoShareholdersWithShareholdersAndPricePerYearWith$YearBy(String noShareholders, String price, int no)  {
		
		String shareholderText;
		String priceText;
		if (no == 1) {
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN2_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN2_TEXT)).getText();
		} else if (no == 2) {	
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN4_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN4_TEXT)).getText();

		}else  {
			shareholderText = driver.findElement(By.xpath(PageUIs.SHAREHOLDER_PLAN7_TEXT)).getText();
			priceText =driver.findElement(By.xpath(PageUIs.PRICE_PLAN7_TEXT)).getText();
		}
	   
		Assert.assertEquals(shareholderText,noShareholders );
		Assert.assertEquals(priceText,"S"+ price);
	}


	public void waitForElementClickable( String xpath) {
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void clickToElement( String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
    }


