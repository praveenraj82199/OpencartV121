package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;     //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	    public static WebDriver driver;
	    public Logger logger; // Log4j
	    public Properties p; 
	
	    @BeforeClass(groups= {"Sanity","Regression","Master"})
	    @Parameters({"os","browser"})
	    public void setup(String os,String br) throws IOException {
		
        //Loading config.properties file
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
	    logger = LogManager.getLogger(this.getClass()); //log4j
		
		// This step is selenium  grid set up
	/*	
		if(p.getProperty("execution_ev").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
				
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
		}
			else {
				System.out.println("no matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("no matching browser"); return;
			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_ev").equalsIgnoreCase("local"))
		{
			 switch(br.toLowerCase())
				{
				case "chrome" : driver=new ChromeDriver(); break;
				case "edge"   : driver=new EdgeDriver(); break;
				case "firefox" : driver=new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name.."); return;
				}
		}*/
		
		// till This  line selenium  grid  
		
		
		// this is for normal browser execution in base class
		
	    switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge"   : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.."); return;
		} 
	    //till this line 
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//reading url from properties file.
		driver.manage().window().maximize();
		}
	
       @AfterClass(groups= {"Sanity","Regression","Master"})
        void tearDown() {
		driver.quit();
        }

        //org.apache.commons.lang3.RandomStringUtils;
        //regpage.setEmail("abcjohndavif@gmail.com"); ipdi email id kudutha *Warning: E-Mail Address is already registered!* ipdi output varum
        //athunala nama random ah email id kudukanum. ipdi kudutha every time random ah oru email id create aagum

        //ithu random string use pannalam
       
        public String randomString() {
	    String generatedstring=RandomStringUtils.randomAlphabetic(5);
	    return generatedstring;
        }
        
        //ithu random number use pannalam
        
        public String randomNumber() {
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	    }
        
        //ithu random string & number use pannalam
        
        public String randomAlphaNumeric() {
	    String generatedstring = RandomStringUtils.randomAlphabetic(3);
	    String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatednumber+"@"+generatedstring); // @ special char eathuvena add panalam.
	    }
        
        public String captureScreen(String tname)throws IOException{
    	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    	 
    	TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
    	File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
    	 
    	String targetFilePath=System.getProperty("user.dir")+"\\screenshorts\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
    	 
    	sourceFile.renameTo(targetFile);
    	return targetFilePath;
     }
        
        }
