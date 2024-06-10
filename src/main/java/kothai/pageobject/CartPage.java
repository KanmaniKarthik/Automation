package kothai.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kothai.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents
{

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct; 
	
	@FindBy(css=".totalRow button")
	WebElement button;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String ProductName)
	{
		Boolean match = cartProduct.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckoutPage goTocheckOut()
	{
		button.click();
		return new CheckoutPage(driver);
	}

	
	
	

	
	
}
