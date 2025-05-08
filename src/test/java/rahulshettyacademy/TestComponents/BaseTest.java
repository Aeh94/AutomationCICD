package rahulshettyacademy.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pagesbjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lndPg;
	
	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		
		Properties ptps = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//ressources//GlobalData.properties");
		ptps.load(fs);
		
		//java ternary operator
		String browserName = System.getProperty("browser")!=null	? System.getProperty("browser"): ptps.getProperty("browser");
		//ptps.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if (browserName.contains("headless")) {
			options.addArguments("headless");  //run tests without opening the browser
		}
		options.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900)); //run in full screen for headless
		
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();		
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();		
			EdgeOptions options = new EdgeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new EdgeDriver(options);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read Json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//string to HshMap -> jackson-databind
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports" + testCaseName + ".png";
		
	}
	
	//Extent reports
	
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApp() throws IOException {
		
		 driver = initializeDriver();
		 lndPg = new LandingPage(driver);
		 lndPg.goTo("https://rahulshettyacademy.com/client");
		 return lndPg;
		
	}
	
//	@AfterMethod (alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
	}
	
}
