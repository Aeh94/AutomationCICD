package rahulshettyacademy.pagesbjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	

	public OrdersPage(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersNames;


	
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = ordersNames.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	

}
