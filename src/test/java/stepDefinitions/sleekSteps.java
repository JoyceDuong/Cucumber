package stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUI.PageUIs;
import cucumber.api.DataTable;
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
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@When("^I click on the Pricing link$")
	public void iClickOnThePricingLink() {
		String pricingXpath = "//ul[@id='menu-mega-menu']//a[text()='Pricing']";
		//waitForElementClickable( pricingXpath);
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pricingXpath)));

		driver.findElement(By.xpath(pricingXpath)).click();

	}

	@Then("^I should be navigated to the Sleek SG Pricing page$")
	public void iShouldBeNavigatedToTheSleekSGPricingPage() {
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=concat('Singapore',\"'s best value for money!\")]"))
				.isDisplayed());
	}

	@Then("^I quit browser$")
	public void iQuitBrowser() {
		driver.quit();
	}

	@When("^I click on LEARN MORE  button for Corporate secretary$")
	public void iClickOnLEARNMOREButtonForCorporateSecretary() {
		
		String learnMoreButton = "//span[text()=' Corporate secretary ']/parent::h3/parent::div/parent::div/parent::div/parent::div/following-sibling::div[4]//span[text()=' Learn more ']/parent::span/parent::a";
		waitForElementClickable(PageUIs.LEARN_MORE_BUTTON);
		driver.findElement(By.xpath(learnMoreButton)).click();
	}

	@Then("^Verify corporate secretary details are correct:$")
	public void verifyCorporateSecretaryDetailsAreCorrect(DataTable table) {
		String plan2Xpath = "//div[@id='step2']";
		String plan4Xpath = "//div[@id='step4']";
		String plan7Xpath = "//div[@id='step7']";

		List<Map<String, String>> data = table.asMaps(String.class, String.class);
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);",driver.findElement(By.xpath("//div[@data-id='ffa77f5']//p[@class='elementor-heading-title elementor-size-default']")));
		
		
		waitForElementClickable(plan2Xpath);
		driver.findElement(By.xpath(plan2Xpath)).click();
		String shareholder02Text = driver.findElement(By.xpath("//div[@data-id='2009123']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		String price02Text =driver.findElement(By.xpath("//div[@data-id='82e3849']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		Assert.assertEquals(shareholder02Text, data.get(0).get("noShareholders"));
		Assert.assertEquals(price02Text,"S"+data.get(0).get("pricePerYear"));		
		

		waitForElementClickable(plan4Xpath);
		driver.findElement(By.xpath(plan4Xpath)).click();
		String shareholder04Text = driver.findElement(By.xpath("//div[@data-id='56bd308']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		String price04Text =driver.findElement(By.xpath("//div[@data-id='7a0cfe6']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		Assert.assertEquals(shareholder04Text, data.get(1).get("noShareholders"));
		Assert.assertEquals(price04Text,"S"+data.get(1).get("pricePerYear"));
		
		waitForElementClickable(plan7Xpath);
		driver.findElement(By.xpath(plan7Xpath)).click();
		String shareholder07Text = driver.findElement(By.xpath("//div[@data-id='31d595b']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		String price07Text =driver.findElement(By.xpath("//div[@data-id='a05d362']//p[@class='elementor-heading-title elementor-size-default']")).getText();
		Assert.assertEquals(shareholder07Text, data.get(2).get("noShareholders"));
		Assert.assertEquals(price07Text,"S"+data.get(2).get("pricePerYear"));
		

	}

	public void waitForElementClickable( String xpath) {
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
    }


