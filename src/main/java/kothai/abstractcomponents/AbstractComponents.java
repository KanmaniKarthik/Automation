package kothai.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kothai.pageobject.CartPage;
import kothai.pageobject.OrderPage;

public class AbstractComponents
{
	WebDriver driver;
	public AbstractComponents(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By findBy)
	{
	    WebDriverWait holdon = new WebDriverWait(driver,Duration.ofSeconds(5));
	    holdon.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));     //By locator
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	    WebDriverWait holdon = new WebDriverWait(driver,Duration.ofSeconds(5));
	    holdon.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	} 	
	
	public void waitForElementToDisappear(WebElement elemen) throws InterruptedException
	{
	   /* WebDriverWait holdon = new WebDriverWait(driver,Duration.ofSeconds(5));
	    holdon.until(ExpectedConditions.visibilityOf(elemen));  */
		
		Thread.sleep(2000);
	}
}
