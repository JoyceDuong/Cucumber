package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Commons.*;
import PageUI.PageUIs;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class sleekSteps extends BaseTest {

	WebDriver driver;

	@Given("^I went to the Sleek SG Home page$")
	public void iWentToTheSleekSGHomePage() {
		PropertiesFile.setPropertiesFile();
		driver = initBroserDriver(PropertiesFile.getPropValue("url"));
	}

	@When("^I click on the Pricing link$")
	public void iClickOnThePricingLink() {
		waitForElementClickable(driver, PageUIs.PRICING_LINK);
		clickToElement(driver, PageUIs.PRICING_LINK);

	}

	@Then("^I should be navigated to the Sleek SG Pricing page$")
	public void iShouldBeNavigatedToTheSleekSGPricingPage() {
		Assert.assertTrue(isElementDisplayed(driver, PageUIs.PRICING_TEXT_VALIDATE));
	}

	@Then("^I quit browser$")
	public void iQuitBrowser() {
		tearDownBrowser(driver);
	}

	@And("^I click on LEARN MORE  button for Corporate secretary$")
	public void iClickOnLEARNMOREButtonForCorporateSecretary() {
		waitForElementClickable(driver, PageUIs.LEARN_MORE_BUTTON);
		clickToElement(driver, PageUIs.LEARN_MORE_BUTTON);
		sleepInSecond(3);
	}

	@When("^I click on accounting line by (\\d+)$")
	public void iClickOnAccountingLineBy(int no) {
		scrollToElement(driver, PageUIs.SCROLL_ELEMENT);
		scrollTo50Pixel(driver);
		moveToElement(driver, PageUIs.SCROLL_ELEMENT);

		switch (no) {
		case 1:
			waitForElementClickable(driver, PageUIs.PLAN_2_BUTTON);
			clickToElementByJS(driver, PageUIs.PLAN_2_BUTTON);
			break;
		case 2:
			waitForElementClickable(driver, PageUIs.PLAN_4_BUTTON);
			clickToElementByJS(driver, PageUIs.PLAN_4_BUTTON);
			break;
		case 3:
			waitForElementClickable(driver, PageUIs.PLAN_7_BUTTON);
			clickToElementByJS(driver, PageUIs.PLAN_7_BUTTON);
			break;
		}

	}

	@Then("^Verify by \"([^\"]*)\" noShareholders with \"([^\"]*)\" and pricePerYear with \"([^\"]*)\"$")
	public void verifyByNoShareholdersWithAndPricePerYearWith(String no, String noShareholders, String price) {
		String shareholderText = "";
		String priceText = "";

		switch (no) {
		case "1":
			shareholderText = getTextElement(driver, PageUIs.SHAREHOLDER_PLAN2_TEXT);
			priceText = getTextElement(driver, PageUIs.PRICE_PLAN2_TEXT);
			break;
		case "2":
			shareholderText = getTextElement(driver, PageUIs.SHAREHOLDER_PLAN4_TEXT);
			priceText = getTextElement(driver, PageUIs.PRICE_PLAN4_TEXT);
			break;
		case "3":
			shareholderText = getTextElement(driver, PageUIs.SHAREHOLDER_PLAN7_TEXT);
			priceText = getTextElement(driver, PageUIs.PRICE_PLAN7_TEXT);
			break;
		}

		Assert.assertEquals(noShareholders, shareholderText);
		Assert.assertEquals("S" + price, priceText);
	}

}
