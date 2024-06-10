package Kothai.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyOfXyz
{
	public static void main(String[] args) throws InterruptedException
	{
		String ProductName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("mhkanmani1996@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("HarryPotter@1");
		driver.findElement(By.id("login")).click();
		Assert.assertTrue(true);
		System.out.println("Successfully logged in!!");
		WebDriverWait holdon = new WebDriverWait(driver,Duration.ofSeconds(5));
		holdon.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement item = products.stream().filter(product->
	    product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	    item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    holdon.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));	

	    holdon.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-star-inserted"))); //works slowly
	
	
	//holdon.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));     //works little bit fast
			
	driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); 
	
	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	Assert.assertTrue(match);
	System.out.println("In Cart:"+" "+ProductName);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	//Actions class:
	Thread.sleep(3000);
	Actions b = new Actions(driver);
	b.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[2]")), "rahulshettyacademy").build().perform();
	b.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[3]")), "Kanmani").build().perform();
	b.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[4]")), "rahulshettyacademy").build().perform();
    driver.findElement(By.cssSelector("[class*='btn-primary']")).click();
    System.out.println(driver.findElement(By.cssSelector(".field p")).getText());
	b.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India")
	     .build().perform();
	
	List<WebElement> options = driver.findElements(By.cssSelector("[placeholder='Select Country']"));
	for(WebElement option : options)
	{
		if(option.getText().equalsIgnoreCase("India"))
		{
			option.click();
			break;
		} 	
	} 
	holdon.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='ng-star-inserted'])[2]")));
	driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();	
	holdon.until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector(".action__submit")));
	
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	WebElement Submit = driver.findElement(By.cssSelector(".action__submit"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();",Submit);
	String message = driver.findElement(By.className("hero-primary")).getText();
	
	System.out.println(message);	
	
	driver.close();
	}
   
}
