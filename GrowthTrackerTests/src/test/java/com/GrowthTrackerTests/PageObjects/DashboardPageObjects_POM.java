package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class DashboardPageObjects_POM extends CommonToAllTests
{

	WebDriver driver;


	public By usersTab = By.xpath("//a[contains(@href,'users')]");
    
	public By clientsTab = By.xpath("//a[contains(@href,'clients')]");
	
	public By dashboardIcon = By.xpath("//div[contains(@class,'page-heading') and //div[contains(text(),'Dashboard')]]");

	private By OpportunitiesTabMaster = By.xpath("//ul[contains(@class,'tab-header')]/li[2]/a");
	
	private By OpportunitiesTabOther = By.xpath("//ul[contains(@class,'tab-header')]/li[1]/a");
	
	private By dashboardPitchItem=By.xpath("//li[contains(@data-testid,'pbi-grid-tile-item')][11]");

	public DashboardPageObjects_POM(WebDriver driver) {
		

		this.driver = driver;
		super.driver=driver;

	}

	public By getOpportunitiesTabMaster() {
		return OpportunitiesTabMaster;
	}

	public void setOpportunitiesTabMaster(By opportunitiesTabMaster) {
		OpportunitiesTabMaster = opportunitiesTabMaster;
	}

	public By getOpportunitiesTabOther() {
		return OpportunitiesTabOther;
	}

	public void setOpportunitiesTabOther(By opportunitiesTabOther) {
		OpportunitiesTabOther = opportunitiesTabOther;
	}

	public By getDashboardPitchItem() {
		return dashboardPitchItem;
	}

	
}
