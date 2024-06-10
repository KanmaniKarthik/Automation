package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Kothai.TestComponents.Btest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kothai.pageobject.CartPage;
import kothai.pageobject.CheckoutPage;
import kothai.pageobject.ConfirmationPage;
import kothai.pageobject.LandingPage;
import kothai.pageobject.ProductCatalog;

public class StepDefinitionImpl extends Btest
{
	public LandingPage landingpage;
	public ProductCatalog productcatalog;
	public CartPage cartpage;
	ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage= LaunchApplication();
	}
	
    @Given("^Logged in with email (.+) and password (.+)$")
    public void Logged_in_with_email_and_password(String email, String password)
    {
		productcatalog = landingpage.loginApplication(email,password);
		
    }
    
    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws InterruptedException
    {
		System.out.println("Successfully logged in!!");
    	List<WebElement> products = productcatalog.getProductList();  
		productcatalog.addProductCart(productName);
		Thread.sleep(2000);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) throws InterruptedException
    {
    	cartpage = productcatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName); 
		Assert.assertTrue(match);
		System.out.println("In Cart:"+" "+productName);
		CheckoutPage checkoutpage = cartpage.goTocheckOut(); 
    
        Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[2]")), "rahulshettyacademy").build().perform();
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[3]")), "Kanmani").build().perform();
		a.sendKeys(driver.findElement(By.xpath("(//*[@type='text'])[4]")), "rahulshettyacademy").build().perform();
	    driver.findElement(By.cssSelector("[class*='btn-primary']")).click();
	    System.out.println(driver.findElement(By.cssSelector(".field p")).getText());

		Thread.sleep(2000); 
        
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string)
    {
    	String message = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(message,string);
		System.out.println(message);
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void message_is_displayed(String str)
    {
    	Assert.assertEquals(str,landingpage.getErrorMessage());
        driver.close();
    }

}
