package Kothai.TestComponents;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import kothai.pageobject.LandingPage;
import kothai.resources.ExtentReporterNG;

public class Btest extends ExtentReporterNG
{
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		
		//properties class
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\kothai\\resources\\GlobalData.properties");

		
		//FileInputStream fis = new FileInputStream("C:\\Users\\Kanmani\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\kothai\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");)
		
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");
		
		//if(browserName.equalsIgnoreCase("chrome"))
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions opt = new ChromeOptions();
		    WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless"))
           {
            	 opt.addArguments("headless");
           }
            
            driver = new ChromeDriver(opt);
            driver.manage().window().setSize(new Dimension(1440,900));
            
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();

		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();
	
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJasonToMap(String filePath) throws IOException
	{   
		//read jason to string:
		
		String jsonContent = FileUtils.readFileToString(new File(filePath) ,StandardCharsets.UTF_8);
		
		//convert String to hashmap  ==> we need Jackson DataBind dependancy
		
		 ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot pic = (TakesScreenshot)driver;
		File source = pic.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" + testCaseName +".png";   // or return file;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();  
		return landingpage;
	}
	
	ExtentReports extent = ExtentReporterNG.config();

	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{

		driver.close();
		extent.flush();
	}
	 
	
	
	
	
}
