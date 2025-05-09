package rahulshettyacademy.pagesbjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".hero-primary")
	WebElement cnfMsg;
	
	
	public String chkMsg() {
		return cnfMsg.getText();
	}
	
	
	
}
