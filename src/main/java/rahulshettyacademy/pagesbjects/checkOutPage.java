package rahulshettyacademy.pagesbjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class checkOutPage extends AbstractComponent {

	WebDriver driver;
	
	public checkOutPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	


@FindBy (css="[placeholder='Select Country']")
private WebElement country;

@FindBy(css=".action__submit")
private WebElement submit;

@FindBy (css=".ta-results button:last-child")
private WebElement selectcountry;

By results = By.cssSelector(".ta-results");

public void selectCountry(String countryName) {
	
	Actions a = new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	waitForElementToAppear(results);
	selectcountry.click();
}

public ConfirmationPage sumbitOrder() throws InterruptedException {
	scrollPage(submit);
	submit.click();
	return new ConfirmationPage(driver);
}



	
}
