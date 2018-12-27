package com.ibm.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;
	

	@FindBy(xpath = "//*[@id='side-menu']/li[6]/a")
	WebElement systemEle;;
	@FindBy(xpath = "//*[@id='side-menu']/li[6]/ul/li[6]/a")
	WebElement returnsEle;
	@FindBy(xpath = "//*[@id='side-menu']/li[6]/ul/li[6]/ul/li[1]/a")
	WebElement returnStatusesEle;
	@FindBy(xpath = "//input[@type='search']")
	WebElement searchEle;
	@FindBy(xpath = "//*[@id='dataTableExample2']/tbody/tr/td[3]/div/button")
	WebElement actionEle;
	@FindBy(xpath = "//*[@id='dataTableExample2']/tbody/tr/td[3]/div/ul/li[1]/a")
	WebElement editEle;
	@FindBy(xpath = "//input[@value='experiment']")
	WebElement returnStatusNameEle;
	@FindBy(xpath = "//button[@title='Save']")
	WebElement saveEle;

	public AdminPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public void clickOnSystem() {
		systemEle.click();
	}

	public void clickOnReturns() {
		returnsEle.click();
	}

	public void clickOnReturnStatuses() {
		returnStatusesEle.click();
	}

	public int countQuery(String tab_name) throws SQLException {
		int count = 0;
		Connection c = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries",
				"foodsonfinger_atoz", "welcome@123");
		Statement s = c.createStatement();

		ResultSet rs = s.executeQuery("SELECT count(*) FROM " +tab_name);
		if (rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	public void searchReturnStatusNameToBeEdited(String rsn) {
		searchEle.sendKeys(rsn);
	}

	public void clickOnAction() {
		actionEle.click();
	}

	public void selectEdit() {
		editEle.click();
	}

	public void inputNewReturnStatusName(String nrsn) {
		returnStatusNameEle.clear();
		returnStatusNameEle.sendKeys(nrsn);
	}

	public void clickOnSave() {
		saveEle.click();
	}

	public String singleDataQuery(String tab_name,String col,String name_p) throws SQLException{
		String text=null;
		Connection con = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries", 
				"foodsonfinger_atoz",
				"welcome@123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM "+tab_name+" WHERE "+col+"='"+name_p+"'");
		if(rs.next()) {
		text = rs.getString(col);
		}
		return text;
	}

}
