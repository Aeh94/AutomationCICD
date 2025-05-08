package rahumshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pagesbjects.CartPage;
import rahulshettyacademy.pagesbjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait (driver,Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear2(WebElement elt) {
		WebDriverWait w = new WebDriverWait (driver,Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOf(elt));
	}

	public void waitForElementToDisappear(By findBy) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait w = new WebDriverWait (driver,Duration.ofSeconds(2));
//		w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	
	public CartPage goToCart() {
		cart.click();
		CartPage crtPg = new CartPage(driver);
		return  crtPg;
	
	}
	
	public OrdersPage goToOrders() {
		orders.click();
		OrdersPage ordPg = new OrdersPage(driver);
		return ordPg;
	
	}
	
	public void scrollPage(WebElement element) throws InterruptedException {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		jse.executeScript("arguments[0].click()", element);
		
//		waitForElementToAppear2 (element);

		
	}
	
	
	

}
