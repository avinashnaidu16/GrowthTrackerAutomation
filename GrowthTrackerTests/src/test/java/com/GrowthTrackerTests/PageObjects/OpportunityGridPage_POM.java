package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class OpportunityGridPage_POM extends CommonToAllTests

{
	
	WebDriver driver;

	private By newOpportunityButton=By.xpath("//button/span[contains(text(),'New Opportunity')]");
	
	private By mainBreadCrumbBtn=By.xpath("//i[contains(@class,'ui-grid-icon-menu')]");
	private By breadCrumbCity=By.xpath("//li[contains(text(),'City')]//i[contains(@class,'cancel')]");
	
	private By gridRow=By.xpath("//div[contains(@ng-class,'ui-grid-tree-header-row')]//div[contains(@ui-grid-row,'row')]");
	
	private By gridfilterInputAdvertiser=By.xpath("//div[contains(@title,'Advertiser/Product')]//following-sibling::div//input[contains(@class,'ui-grid-filter-input')]");
	
	
	private By gridColumnValues=By.xpath("//div[contains(@ng-class,'ui-grid-tree-header-row')]//div[contains(@ui-grid-row,'row')]//div//div");
	
	
	public By getGridColumnValues() {
		return gridColumnValues;
	}
	public OpportunityGridPage_POM(WebDriver driver) {
		

		this.driver = driver;
		super.driver=driver;

	}
	public By getNewOpportunityButton() {
		return newOpportunityButton;
	}
	public By getGridfilterInputAdvertiser() {
		return gridfilterInputAdvertiser;
	}

	

}
