package com.ibm.test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.ibm.initialization.WebDriverLaunch;
import com.ibm.pages.AdminPage;
import com.ibm.pages.Login;

import com.sun.java.swing.plaf.windows.resources.windows;


public class BaseTest extends WebDriverLaunch {
	
	
	@Test(priority=0)
        public void adminCredentials() throws FileNotFoundException, IOException
        {
		String url = p.getProperty("url");
		String UserName = p.getProperty("user");
		String Password = p.getProperty("password");
        driver.get(url);
		Login login = new Login(driver,wait);
		login.enterEmailAddress(UserName);
		login.enterPassword(Password);
		login.clickOnLogin();

        }
	   
	   
	  @Test(priority=1)
	    public void EditReturnStatusNameAndVerify() throws InterruptedException, SQLException
	    {
	    	String searchReturnstatusname=p.getProperty("search");
	    	String searchNewReturnStatusName=p.getProperty("newreturnstatusname");
	    	
		   AdminPage adm=new AdminPage(driver,wait);
		   Thread.sleep(3000);
		   adm.clickOnSystem();
		   Thread.sleep(3000);
		   adm.clickOnReturns();
		   Thread.sleep(5000);
		   adm.clickOnReturnStatuses();
		   String table_name=p.getProperty("table");
		   String col_name=p.getProperty("coloumn");
			int exp= adm.countQuery(table_name);
			String exps=adm.singleDataQuery(table_name, col_name, searchReturnstatusname);
		   Thread.sleep(5000);
		   adm.searchReturnStatusNameToBeEdited(searchReturnstatusname);
		   adm.clickOnAction();
		   adm.selectEdit();
		   adm.inputNewReturnStatusName(searchNewReturnStatusName);
		   Thread.sleep(3000);
		   adm.clickOnSave();
		   int act=adm.countQuery(table_name);
		   Assert.assertEquals(act,exp );//record count validation
		   String acts=adm.singleDataQuery(table_name, col_name, searchReturnstatusname);
		   Assert.assertEquals(acts, null);//Validate the absence of old record
		   String acst=adm.singleDataQuery(table_name, col_name, searchNewReturnStatusName);
		   Assert.assertEquals(acst, searchNewReturnStatusName);//presence of modified record
		  
		 
	  }
}

