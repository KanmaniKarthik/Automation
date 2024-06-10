package kothai.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kothai.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents
{

	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProductNames;  
	
	@FindBy(css=".totalRow button")
	WebElement button;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderDisplay(String ProductName)
	{
		Boolean match = ProductNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckoutPage goTocheckOut()
	{
		button.click();
		return new CheckoutPage(driver);
	}

	
	
	

	
	
}
