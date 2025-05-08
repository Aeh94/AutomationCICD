package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pagesbjects.LandingPage;

public class standaloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait (driver,Duration.ofSeconds(2));
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage lndPg = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("tst1020@gmail.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Abcdefgh@10");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prd = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prd.findElement(By.cssSelector(" .card-body button:last-of-type")).click();
		
		
		WebElement msg = driver.findElement(By.cssSelector("#toast-container"));
		
		WebElement loading = driver.findElement(By.cssSelector(".ng-animating"));
		
		w.until(ExpectedConditions.invisibilityOf(loading));
		
		w.until(ExpectedConditions.visibilityOf(msg));
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartPrd = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartPrd.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//div[@class='cart'] //button[@class='btn btn-primary']")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment")));
		
		Actions a = new Actions(driver);
		WebElement cntry = driver.findElement(By.cssSelector(".user__address input"));
		a.sendKeys(cntry, "mor").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		
		driver.findElement(By.cssSelector(".ta-results button:last-child")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String cfmt = driver.findElement(By.cssSelector("hero-primary")).getText();
		
		Assert.assertTrue(cfmt.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();

		
	
	}
}
