package com.ibm.initialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class WebDriverLaunch {
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties p;
	public Connection c;
	public Statement s;
	public ResultSet rs;
	@BeforeSuite
	public void preSetForTest() throws FileNotFoundException, IOException {
		p = new Properties();
		p.load(new FileInputStream("TestData/data.properties"));
	}
		@BeforeTest
		public void Initialization() throws SQLException, InterruptedException {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver=new ChromeDriver();
			wait = new WebDriverWait(driver,50);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
			Thread.sleep(3000);
		    
		}

		@AfterSuite
		public void closeBrowser() {
			driver.quit();
		}
	}

