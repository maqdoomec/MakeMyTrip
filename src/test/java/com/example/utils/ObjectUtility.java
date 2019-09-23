package com.example.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * @date 
 * @author 
 * @description Property file containing methods to read values from property file
 */

public class ObjectUtility {
	
Properties prop = new Properties();	

	String fileName;
	public ObjectUtility()
	{
		
	}
	
	public ObjectUtility(String fileName)
	{
		this.fileName = fileName;
		try{
			InputStream input =  null;
			input = new FileInputStream(fileName);
			prop.load(input);
		}
		catch (IOException e) {
			Assert.fail("Error occured file reading Object Property File: "+fileName, e);
		}		
	}
	
	private String getProperty(String propertyKey){	
		
		try{
			return prop.getProperty(propertyKey);
		}catch(NullPointerException e){
			Assert.fail("Object: '"+propertyKey+"' "+" not found in file: "+fileName);
		}
		return null;
	}
	
	private By getObjectFromStr(String strObj)
	{
		By ret = null;	
				
		int index = strObj.indexOf("~");
		String key = strObj.substring(0,index);
		String value = strObj.substring(index+1,strObj.length());
				
		switch(key.toLowerCase())
		{
		case "class":
			ret = By.className(value);
			break;
		case "css":
			ret = By.cssSelector(value);
			break;
		case "id":
			ret = By.id(value);
			break;
		case "link":
			ret = By.linkText(value);
			break;
		case "name":
			ret = By.name(value);
			break;
		case "partiallink":
			ret = By.partialLinkText(value);
			break;
		case "tagname":
			ret = By.tagName(value);
			break;
		case "xpath":
			ret = By.xpath(value);
			break;
		}
		
		return ret;
	}
	
	public By getObject(String fieldName, String replaceKeys, String replaceValues)
	{
		String finalStrObj = getProperty(fieldName);
		String[] rKys = replaceKeys.split("\\~");
		String[] rVals = replaceValues.split("\\~");

		for (int i = 0; i < rKys.length; i++)
			finalStrObj = finalStrObj.replace(rKys[i], rVals[i]);

		By ret = getObjectFromStr(finalStrObj);
		
		return ret;
	}
	public By getObject(String fieldName)
	{			
		return getObjectFromStr(getProperty(fieldName));
	}
}