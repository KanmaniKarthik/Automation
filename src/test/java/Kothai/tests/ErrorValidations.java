package Kothai.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;


import Kothai.TestComponents.Btest;
import Kothai.TestComponents.Retry;
import kothai.pageobject.CartPage;
import kothai.pageobject.CheckoutPage;
import kothai.pageobject.ConfirmationPage;
import kothai.pageobject.ProductCatalog;

 
public class ErrorValidations extends Btest
{
	
	
	//public static void main(String[] args) throws InterruptedException
	
		@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException
		{
		//String ProductName = "ADIDAS ORIGINAL";	    
    	landingpage.loginApplication("mhkanmani1996@gmail.com", "HarryPotter");
    	Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
		}
		
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException
		{
			
		String ProductName = "ADIDAS ORIGINAL";		
		ProductCatalog productcatalog = landingpage.loginApplication("mhkanmani1996@gmail.com", "HarryPotter@1");
		Assert.assertTrue(true);
		System.out.println("Successfully logged in!!");
		List<WebElement> products = productcatalog.getProductList();  
		productcatalog.addProductCart(ProductName);
		Thread.sleep(2000);
		CartPage cartpage = productcatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("Zara");
		Assert.assertFalse(false);
		}
}
 