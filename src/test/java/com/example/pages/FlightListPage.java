package com.example.pages;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.example.config.ConfigConstants;
import com.example.utils.WebdriverUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FlightListPage extends WebdriverUtil {

	private static String objetResPath = ConfigConstants.OBJECT_PATH+"/FlightList.properties";
	
	public FlightListPage(WebDriver driver, ExtentTest exTest) {
		super(driver, objetResPath, exTest);		
	}

	public String getSecondHighPrice()
	{
		scrollToGetLastPrice();
		return getPrice();
	}
	
	
	private void scrollToGetLastPrice()
	{
		int allListSize = getElementsSize("FlightListLeftAll");
		while(true)
		{
			scrollIntoView("FlightListLeftLast");
			sleep(500);
			if(allListSize ==getElementsSize("FlightListLeftAll"))
				break;
			allListSize = getElementsSize("FlightListLeftAll");
		}
		
		exTest.log(LogStatus.INFO, " ", "<span style='font-weight:bold;'>Srcolled to last price</span>");
	}
	
	
	
	private String getPrice()
	{
		String leftHighPrice = getText("FlightListLeftSecondLast");
		String rightHighPrice = getText("FlightListRightSecondLast");
		
		if(leftHighPrice.trim().equals(rightHighPrice.trim()))
			return leftHighPrice;
		else if(Integer.parseInt(leftHighPrice.replaceAll("[^0-9]", ""))>Integer.parseInt(rightHighPrice.replaceAll("[^0-9]", "")))
			return leftHighPrice;
		else
		{
			scrollIntoView("FlightListRightSecondLast");			
			return rightHighPrice;
		}
			
	}
	
	
	
	}
