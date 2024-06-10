package kothai.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kothai.abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents
{
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver = driver;        //assigning life of driver to my test
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	//PageFactory:
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(id="toast-container")
	WebElement fadeout;
	
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage =By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductName(String ProductName)
	{
		WebElement item = products.stream().filter(product->
	         product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	    return item;
	}
	
	public void addProductCart(String ProductName)
	{
		WebElement item = getProductName(ProductName);
		item.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(fadeout);
		
		

	}
	
	
	
}
