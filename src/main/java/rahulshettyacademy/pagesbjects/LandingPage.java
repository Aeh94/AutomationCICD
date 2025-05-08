package rahulshettyacademy.pagesbjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy (id="userPassword")
	WebElement passwordElt;
	
	@FindBy (id="login")
	WebElement submit;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errCredentials;
	
	public String errCred () {
		waitForElementToAppear2(errCredentials);
		return errCredentials.getText();
	}
	
	public ProductCatalog loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordElt.sendKeys(password);
		submit.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
		
	}
	
	
	public void goTo(String url)
	{
		driver.get(url);
	}
}
