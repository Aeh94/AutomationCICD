package rahulshettyacademy.Tests;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pagesbjects.CartPage;
import rahulshettyacademy.pagesbjects.ConfirmationPage;
import rahulshettyacademy.pagesbjects.OrdersPage;
import rahulshettyacademy.pagesbjects.ProductCatalog;
import rahulshettyacademy.pagesbjects.checkOutPage;
import rahulshettyacademy.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test (dataProvider="getData", groups = {"Purchase"}, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalog pc = lndPg.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		CartPage cp = pc.goToCart();
		
		Boolean match = cp.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		checkOutPage cop = cp.goToChkout();
		cop.selectCountry("morocco");
		ConfirmationPage cnfPg = cop.sumbitOrder();
		String cfmt = cnfPg.chkMsg();
		Assert.assertTrue(cfmt.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	@Test (dependsOnMethods = {"submitOrder"} )
	public void OrderHistoryTest() {
		ProductCatalog pc = lndPg.loginApplication("tst1020@gmail.com", "Abcdefgh@10");
		OrdersPage op = pc.goToOrders();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)} , {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "tst1020@gmail.com");
//		map1.put("password", "Abcdefgh@10");
//		map1.put("productName", "QWERTY");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "tst1030@gmail.com");
//		map2.put("password", "Abcdefgh@10");
//		map2.put("productName", "QWERTY");
//	return new Object[][]  {{map1} , {map2}};
	
//}
}
