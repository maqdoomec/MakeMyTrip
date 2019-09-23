package com.example.pages;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.example.config.ConfigConstants;
import com.example.utils.WebdriverUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends WebdriverUtil {

	private static String objetResPath = ConfigConstants.OBJECT_PATH+"/Home.properties";
	
	public HomePage(WebDriver driver, ExtentTest exTest) {		
		super(driver, objetResPath, exTest);	
		
	}

	public void selectFormAndSearchFlight(Map<String, String> data)
	{
		selectFromCity(data.get("fromCityName"));
		selectToCity(data.get("toCityName"));
		selectDepatureAndArrivalDate(data.get("depatureDate"), data.get("arrivalDate"));
		clickElement("SearchButton");
		sleep(1000);
		getVisibleElement("FlightListAll");
	}
	
	private void selectFromCity(String cityName)
	{
		clickElement("FromCityLabel");
		sleep(300);
		sendText("FromCityOptionInputFilter", cityName);
		sleep(500);
		clickElement("FromCityOptionDynamic", "$cityname$", cityName);
		sleep(200);
		exTest.log(LogStatus.PASS, " ", "<span style='font-weight:bold;'>From City Selected</span>");
		takeScreenShot("From City Selected");
	}
	
	private void selectToCity(String cityName)
	{
		//clickElement("ToCityLabel");
		//sleep(300);
		sendText("ToCityOptionInputFilter", cityName);
		sleep(500);
		clickElement("ToCityOptionDynamic", "$cityname$", cityName);
		sleep(200);
		exTest.log(LogStatus.PASS, " ", "<span style='font-weight:bold;'>To City Selected</span>");
		takeScreenShot("To City Selected");
	}
	
	private void selectDepatureAndArrivalDate(String depatureDateFormat, String arrivalDateFormat)
	{
		//clickElement("DepatureDateLabel");
		//sleep(300);
		for(int i=0;i<30;i++)
		{			
			if(!isElementPresentAndDisplay("CalnderDynamicDaySelector", "$date$", depatureDateFormat))
			{
				clickElement("nextMonth");
				sleep(1000);
			}
			else
				break;
		}
		
		clickElement("CalnderDynamicDaySelector", "$date$", depatureDateFormat);
		sleep(500);
		
		exTest.log(LogStatus.PASS, " ", "<span style='font-weight:bold;'>Depature Date Selected</span>");
		takeScreenShot("Depature Date Selected");
		
		clickElement("ReturnDateLabel");
		sleep(300);
		for(int i=0;i<30;i++)
		{
			if(!isElementPresentAndDisplay("CalnderDynamicDaySelector", "$date$", arrivalDateFormat))
			{
				clickElement("nextMonth");
				sleep(1000);
			}
			else
				break;
		}
		
		clickElement("CalnderDynamicDaySelector", "$date$", arrivalDateFormat);
		sleep(300);
		
		exTest.log(LogStatus.PASS, " ", "<span style='font-weight:bold;'>Arrival Date Selected</span>");
		takeScreenShot("Arrival Date Selected");
		
	}
	
	

	
	
	}
