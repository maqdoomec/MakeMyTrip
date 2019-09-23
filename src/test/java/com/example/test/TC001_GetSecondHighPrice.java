package com.example.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.example.pages.FlightListPage;
import com.example.pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;

public class TC001_GetSecondHighPrice extends BaseTest{

	@Test
	public void test()
	{
		
		/* Setiing up the data
		 * Usually it will be read from external resource ( like excel, DB etc )
		 * 
		 */
		Map<String, String> data = new LinkedHashMap<>();		
		data.put("fromCityName","Mumbai, India");
		data.put("toCityName","Delhi, India");	
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd YYYY");
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 2); // Two days from now
		data.put("depatureDate",formatter.format(c.getTime()));		
		c.add(Calendar.DATE, 7);// 7 days from depature
		data.put("arrivalDate",formatter.format(c.getTime()));
		
		
		
		HomePage homePage = new HomePage(driver, exTest);
		homePage.selectFormAndSearchFlight(data);
		
		FlightListPage flightListPage = new FlightListPage(driver, exTest);
		String secondHighPrice = flightListPage.getSecondHighPrice();
		
		Reporter.log("Second High Price: "+secondHighPrice);
		System.out.println("Second High Price: "+secondHighPrice);
		exTest.log(LogStatus.PASS, " ", "<span style='font-weight:bold;'>Second High Price: "+secondHighPrice+"</span>");
		flightListPage.takeScreenShot("Price Obtained");
		
	}
	
	
}


