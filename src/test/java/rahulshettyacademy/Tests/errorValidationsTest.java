package rahulshettyacademy.Tests;



import java.io.IOException;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pagesbjects.CartPage;
import rahulshettyacademy.pagesbjects.ProductCatalog;

public class errorValidationsTest extends BaseTest{
	
	@Test (groups="errorHandlings")
	public void credentialErrorTst() throws IOException, InterruptedException {
	

		lndPg.loginApplication("tst78552@gmaedfil.com", "Abdfdcdefgh@10");
		
		Assert.assertEquals(lndPg.errCred(),"Incorrect email or password.");
	
	}
	
	@Test (groups="errorHandlings")
	public void ProductErrorTst() throws IOException, InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";
		ProductCatalog pc = lndPg.loginApplication("tst1020@gmail.com", "Abcdefgh@10");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCart();
		Boolean match = cp.verifyProductDisplay("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
	}
}
