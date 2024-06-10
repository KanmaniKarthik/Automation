package kothai.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kothai.abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents
{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;        //assigning life of driver to my test
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement confirmationMessage;
	
	public  String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	
	
}
