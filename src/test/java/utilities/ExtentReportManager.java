package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	@Override
	public void onStart(ITestContext testContext) {
		
		/*SimpleDataFormat df = new SimpleDataFormat("yyyy.MM.dd.HH.mm.ss");
		  Data dt = new Data();
		  String currentdatatimesstamp=df.format(dt);
		 */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp       
		repName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\report\\"+repName);// specify location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of report
		sparkReporter.config().setReportName("opencart Functional Testing");// name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencard");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environement","QA");
		
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
				if(!includedGroups.isEmpty()) {
					extent.setSystemInfo("Groups", includedGroups.toString());
				}
				}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test = extent .createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS,result.getName()+"got successfully executed");
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
		
	
		@Override
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.SKIP,result.getName()+"got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
			
		}
		
		
		@Override
		public void onFinish(ITestContext testContext) {
			extent.flush();
			
			String pathOfExtentReport = System.getProperty("user.dir")+"\\report\\"+repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			/*
			  try{URL url = new
			  URL{"file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			  
			 //Create the email message 
			 *ImageHTmlEmail email = new ImageHtmlEmail();
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.googleemail.com");
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
             email.setSSLOnConnect(true);
			 email.setFrom("pavanoltraining@gmail.com");//sender
			 email.setSubject("Test Result");
			 email.setMsg("Please find Attached Report....");
			 email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
			 email.attach(url,"extent report","please check report..");
			 email.send(); //send the email
		      }
		      catch(Exception e) {e.printStackTrace();}
			 */
		}
	}





