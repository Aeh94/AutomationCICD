package rahulshettyacademy.pagesbjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	

	public CartPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css=".cartSection h3")
	List<WebElement> prdtsTitles;

	@FindBy (xpath="//div[@class='cart'] //button[@class='btn btn-primary']")
	WebElement chkoutBtn;
	
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = prdtsTitles.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public checkOutPage goToChkout() {
		chkoutBtn.click();
		return new checkOutPage(driver);
		
	}
	
	

}
