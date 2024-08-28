package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	 
	
@Test(groups={"Regression","Master"})
    public void verify_account_registration() {
	
	
	logger.info("**** Starting TC001_AccountRegistrationTest **** ");
	try {
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	logger.info("Clicked on MyAccount Link ");
	hp.clickRegister();
	logger.info("Clicked on Register Link ");
	
	AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
// option 1 ithu namala oru name,email,input kudupom
	/*regpage.setFirstName("john");
	regpage.setLastName("David");
    regpage.setEmail("abcjohndavif@gmail.com");
    regpage.setTelephone("23456785");
	regpage.setPassword("xyz123456");
	regpage.setConfirmPassword("xyz123456");
	regpage.setPrivacyPolicy();
	regpage.clickContinue();*/
	
// This is dynamically pass data
// option 2 randoma oru name ,email ,aathuvavey generate pannum intha steps
	logger.info("Providing customer details... ");
    regpage.setFirstName(randomString().toUpperCase());
	regpage.setLastName(randomString().toUpperCase());
    regpage.setEmail(randomString()+"@gmail.com");
	regpage.setTelephone("randomNumber()");
	
// ithu eathukuna ipo randomAlphaNumeric()	nu nama set password & setConfirmPassword kuduthurukom ithu ena panum na 
// automatic ah password generate pannu athuna 	set password ku oru password kudukum next setConfirmPassword oru password kudukum 
// ipo oru application la set password & setConfirmPassword same dhan irukonum . ipdi kudutha error varum athunala nama randomAlphaNumeric() ah
// password inkura object save panni  randomAlphaNumeric() pathila (paasword) object ah nama  set password & setConfirmPassword kudukonum
	
	String password = randomAlphaNumeric();
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	//OPTION 1 regpage.setPassword( randomAlphaNumeric()); //ipdi kututha error varum
	//OPTION 2 regpage.setConfirmPassword( randomAlphaNumeric()); //ipdi kututha error varum
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	
	logger.info("Validating expected message.. ");
	
	String confmsg = regpage.getConfirmationMsg();
	//if(confmsg.equals("Your Account Has Been Created!!!")) //ipdi kudutha logs la debug and error kamikum
   if(confmsg.equals("Your Account Has Been Created!"))   // ipdi kudutha logs la debug and error kaamikathu
	{
		Assert.assertTrue(true);
	}
	else {
		logger.error("Test Failed..");
		logger.debug("Debug logs..");
		Assert.assertTrue(false);
	}
	
	//Assert.assertEquals(confmsg, "Your Account Has Been Created!"); this hard asset use panna vendam
	}
  catch(Exception e) {
	  
	  Assert.fail();
  }
	logger.info("**** Finished TC001_AccountRegistrationTest **** ");
 }
}


























