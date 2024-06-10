package kothai.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kothai.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);               //passing the driver from child to parent (LP to AC)
		this.driver = driver;        //assigning life of driver to my test
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory:
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css=".toast-bottom-right")
	WebElement errorMessage;
	
	//Actions:
	
	public ProductCatalog loginApplication(String email,String passcode)
	{
		useremail.sendKeys(email);
		Password.sendKeys(passcode);
		submit.click();
	    
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
