package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// future la AccountRegistrationPage la method add panna ithu la add pannanum

public class AccountRegistrationPage extends BasePage {
	
// intha step kandipa kudukanu for every page object module
	
// this is constructor class
	
public AccountRegistrationPage(WebDriver driver) {
                super(driver);
}

 // this is locator class

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
// intha step validation find pandrathuku but pom class la validation find panna mudiyathu
// athunala kela try catch method use pandrom	
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
// This is Action Method
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	
	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}
	
    public void clickContinue() {
    	// solution 1
		btnContinue.click();
		
//click method work aagalana kela irukura method ah work aagum	(optional)	
		
		//solution 2
		//btnContinue.submit();
		
		//solution 3
		//Action act = new Action(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//solution 4
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.exectuteScript("arguments[0].click();",btnContinue);
		
		//solution 5
		//btnContinue.sendKeys(Key.RETURN);
		
		//solution 6
		//WebdriverWait myWait = new WebDriverWait(driver,Duration.ofseconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
    }
    
    public String getConfirmationMsg() {
    	try {
  // ithu ena pannum na Your Account Has Been Created! ipdi vantha  string ku return pannum
    		return(msgConfirmation.getText());
    	}
    	catch(Exception e) {
  // ithu ena pannum na Your Account Has Been Created! ipdi varalana
    		return(e.getMessage());
    	}
		
    }
		
	}

