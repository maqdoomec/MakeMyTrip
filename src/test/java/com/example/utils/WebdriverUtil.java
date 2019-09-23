package com.example.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.example.config.ConfigConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebdriverUtil {
	
	private WebDriver driver;
	private ObjectUtility putility;
	private int elementWaitTime = ConfigConstants.ELEMENT_WAIT_TIME;
	protected ExtentTest exTest;
	

	public WebdriverUtil(WebDriver driver, String objetResPath, ExtentTest exTest) {
		this.driver = driver;
		this.exTest = exTest;
		if(objetResPath!=null)
			this.putility = new ObjectUtility(objetResPath);
		}

	public void setElementWaitTime(int elementWaitTime) {
		this.elementWaitTime = elementWaitTime;
	}
	
	public void setDefaultElementWaitTime() {
		this.elementWaitTime = ConfigConstants.ELEMENT_WAIT_TIME;
	}
	public void clickElement(String elementObjName)
	{
		try{
			getVisibleElement(elementObjName).click();
		}
		catch(Exception e)
		{
			Assert.fail("Error Occured while clicking element - "+elementObjName, e);
			takeScreenShot("Error Occured while clicking element - "+elementObjName);
		}
	}
	
	public void clickElement(String elementObjName,String replaceKeys,String replaceVlaues)
	{
		try{
			driver.findElement(putility.getObject(elementObjName,replaceKeys,replaceVlaues)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Error Occured while clicking element - "+elementObjName, e);
			takeScreenShot("Error Occured while clicking element - "+elementObjName);
		}
	}
	
	public void sendText(String elementObjName, String textValue)
	{
		try{
			getVisibleElement(elementObjName).sendKeys(textValue);
		}
		catch(Exception e)
		{
			Assert.fail("Error Occured while clicking element - "+elementObjName, e);
		}
	}
	
	public int getElementsSize(String elementObjName)
	{
		try{
			return driver.findElements(putility.getObject(elementObjName)).size();
		}
		catch(Exception e)
		{
			//log error statement
			return 0;
		}
	}
	
	public String getText(String elementObjName)
	{
		try{
			return getVisibleElement(elementObjName).getText();
		}
		catch(Exception e)
		{
			Assert.fail("Error Occured while getting text from element - "+elementObjName, e);
			takeScreenShot("Error Occured while getting text from element - "+elementObjName);
			return null;
		}
	}
	
	public void scrollIntoView(String fieldName)
	{
		WebElement elem = getVisibleElement(fieldName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		sleep(500); 

	}
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			try {
				throw e;
			} catch (Exception ex) {
			}
		}
	}
	
	public WebElement getVisibleElement(String fieldName)
	{
		WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
		By by = putility.getObject(fieldName);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
	}
	

	public boolean isElementPresentAndDisplay(String fieldName, String replaceKeys,String replaceVlaues)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
		By by = putility.getObject(fieldName, replaceKeys, replaceVlaues);
		return driver.findElement(by).isDisplayed();		 
		}catch(Exception e)
		{
			return false;
		}
		
		
	}
	
	public void takeScreenShot(String desc)
	{
		String screenshotB64 = "data:image/png;base64,"+((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);			
		exTest.log(LogStatus.INFO, "Snapshot for  " + desc + "  : "+ exTest.addBase64ScreenShot(screenshotB64));
	}



	
}
