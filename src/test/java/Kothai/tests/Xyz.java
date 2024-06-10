package Kothai.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import Kothai.TestComponents.Btest;
import kothai.pageobject.CartPage;
import kothai.pageobject.CheckoutPage;
import kothai.pageobject.ConfirmationPage;
import kothai.pageobject.LandingPage;
import kothai.pageobject.OrderPage;
import kothai.pageobject.ProductCatalog;

 
public class Xyz extends Btest
{
	String ProductName = "ADIDAS ORIGINAL";
	
	//public static void main(String[] args) throws InterruptedException
	
	    
	
		@Test(dataProvider="collectData", groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
		    
		LandingPage landingpage = LaunchApplication();
		
		
		ProductCatalog productcatalog = landingpage.loginApplication(input.get("email"), input.get("Password"));
		Assert.assertTrue(true);
		System.out.println("Successfully logged in!!");
		List<WebElement> products = productcatalog.getProductList();  
		productcatalog.addProductCart(input.get("Product"));
		Thread.sleep(2000);
		CartPage cartpage = productcatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("Product")); 
		Assert.assertTrue(match);
		System.out.println("In Cart:"+" "+input.get("Product"));
		CheckoutPage checkoutpage = cartpage.goTocheckOut(); 
    
        Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[2]")), "rahulshettyacademy").build().perform();
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[3]")), "Kanmani").build().perform();
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[4]")), "rahulshettyacademy").build().perform();
	    driver.findElement(By.cssSelector("[class*='btn-primary']")).click();
	    System.out.println(driver.findElement(By.cssSelector(".field p")).getText());

		Thread.sleep(2000); 
        
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String message = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(message,"THANKYOU FOR THE ORDER.");
		System.out.println(message);
		}
		
		@Test(dependsOnMethods=("submitOrder" ))
		public void OrderHistoryTest()
		{
			ProductCatalog productcatalog = landingpage.loginApplication("mhkanmani1996@gmail.com","HarryPotter@1");
			OrderPage orderpage = productcatalog.goToOrderPage();
			Assert.assertTrue(orderpage.VerifyOrderDisplay(ProductName));
			
		}
		
		@DataProvider
		public Object[][] collectData() throws IOException
		{
			
		/*	HashMap<String,String> map1 = new HashMap<String,String>();
		    map1.put("email", "mhkanmani1996@gmail.com");
		    map1.put("Password", "HarryPotter@1");
		    map1.put("Product", "ADIDAS ORIGINAL");
		    
		    HashMap<String,String> map2 = new HashMap<String,String>();
		    map2.put("email", "ranimurugan@gmail.com");
		    map2.put("Password", "ChickenStall@1");
		    map2.put("Product", "ZARA COAT 3");     */
		    
			
			List<HashMap<String, String>> data = getJasonToMap(System.getProperty("user.dir")
					+"\\src\\test\\java\\Kothai\\Data\\PurchaseOrder.jason");
			return new Object[][]{{data.get(0)},{data.get(1)}};              //object can accept any kind of data type
		}
		
}
