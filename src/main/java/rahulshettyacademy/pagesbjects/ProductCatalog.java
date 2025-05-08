package rahulshettyacademy.pagesbjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahumshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{


	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By ProductsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	 
	By cnfmsg = By.cssSelector("#toast-container");
	
	By loading = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	
	public WebElement getProductByName(String ProductName) {
		
		WebElement prd = getProductList().stream().filter(product 
				-> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prd;
	}
	
	public void addProductToCart(String ProductName) throws InterruptedException {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(cnfmsg);
		waitForElementToDisappear(loading);
	}
	
	
	
	
}
