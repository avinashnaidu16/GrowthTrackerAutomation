package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class DashboardPageObjects extends CommonToAllTests
{

	WebDriver driver;


	@FindBy(xpath = "//a[contains(@href,'users')]")
	public WebElement usersTab;
	
	@FindBy(xpath = "//a[contains(@href,'clients')]")
	public WebElement clientsTab;

	public DashboardPageObjects(WebDriver driver) {
		
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
}
