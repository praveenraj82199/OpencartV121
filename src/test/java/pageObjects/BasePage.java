package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
// this parent class constructor
	
// this is page of all page object  class 	
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {

// ella page ku separate ah constructor ready pannanum ipa nama basepage la ye create pannirukom		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

}
