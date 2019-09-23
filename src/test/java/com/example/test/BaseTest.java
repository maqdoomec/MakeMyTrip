package com.example.test;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.example.config.ConfigConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

	protected WebDriver driver;
	private ExtentReports extentRep;
	ExtentTest exTest;
	@BeforeSuite
	public void beforeSuite()
	{
		extentRep = new ExtentReports("./report/mytrip_"+new Date().getTime()+".html", false);
		
	}
	@BeforeTest
	public void setUp(ITestContext context)
	{
		
		exTest = extentRep.startTest(context.getCurrentXmlTest().getName());
		
		System.setProperty("webdriver.chrome.driver", ConfigConstants.DRIVER_PATH + "/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--allow-running-insecure-content");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(ConfigConstants.APP_URL);
		exTest.log(LogStatus.INFO, "Launch Application", "<span style='font-weight:bold;'>App Application</span>");
	}
	
	@AfterMethod
	public void afterTestFailedCheck(ITestResult result) {		  
		  
		  if(ITestResult.FAILURE ==result.getStatus()){		
			  
			  exTest.log(LogStatus.FAIL, "Error Ocuured in while executing the test case.", "Exception trace:<br/><br/> "
					  			+StringEscapeUtils.escapeHtml3(ExceptionUtils.getStackTrace(result.getThrowable())).replace("\n", "<br/>"));
		  }		 
	  }  
	  
	@AfterTest
	public void tearDown()
	{
		driver.quit();		
		extentRep.endTest(exTest);
	}
	
	@AfterSuite
	public void afterSuite()
	{
		extentRep.flush();
		extentRep.close();
		
	}
}
