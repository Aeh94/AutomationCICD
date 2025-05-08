package rahulshettyacademy.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pagesbjects.CartPage;
import rahulshettyacademy.pagesbjects.ConfirmationPage;
import rahulshettyacademy.pagesbjects.LandingPage;
import rahulshettyacademy.pagesbjects.ProductCatalog;
import rahulshettyacademy.pagesbjects.checkOutPage;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage lndppg; 
	public ProductCatalog pdctCtl;
	public CartPage cp;
	public checkOutPage cop;
	public ConfirmationPage cnfPg;
	
	
	@Given ("I landed on Ecommerce page")
	public void landing_page() throws IOException {
		
		lndppg = launchApp();
	}
	
	@Given ("^I logged in with username (.+) and password (.+)$")
	public void loginApp(String username, String password) {
		pdctCtl = lndppg.loginApplication(username, password);
	}
	
	@When ("^I add the product (.+) to Cart$")
	public void addPdct (String product) throws InterruptedException {
		List<WebElement> products = pdctCtl.getProductList();
		pdctCtl.addProductToCart(product);
	}
	
	 @And ("checkout the (.+) and submit the order$")
	 public void sbmtOrd(String product) throws InterruptedException {
		cp = pdctCtl.goToCart();
		Boolean match = cp.verifyProductDisplay(product);
		Assert.assertTrue(match);
		cop = cp.goToChkout();
		cop.selectCountry("morocco");
		cop.sumbitOrder();
	 }
	
	 @Then ("{string} message is displayed on ConfirmationPage")
	 public void Confm_msg(String msg) { 
		String cfmt = cnfPg.chkMsg();
		Assert.assertTrue(cfmt.equalsIgnoreCase(msg));
		driver.close();
	 }
	 
	 @Then ("{string} message is displayed")
	 public void Error_msg(String msg) { 
		String err = lndppg.errCred();
		Assert.assertTrue(err.equalsIgnoreCase(msg));
		driver.close();
	 }
}
