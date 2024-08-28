package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
// this is child class for constructor
	
// step 1 : constructor
	WebDriver driver;
	
	public HomePage(WebDriver driver) {

// initelement yen kudukalana constructor separate ah oru class create panniruku (BasePage nu) pannanum athan super keyword ah use panndrom eadukanum 		
		super(driver);
		
	}
	
// step 2 : Locators	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement InkMyaccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement InkRegister;
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	
	
// step 3 : Action Method	
	public void clickMyAccount() {
		InkMyaccount.click();
	}
	
	public void clickRegister() {
		
		InkRegister.click();
		
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
	
	
	
	
	
	
	
	
}
