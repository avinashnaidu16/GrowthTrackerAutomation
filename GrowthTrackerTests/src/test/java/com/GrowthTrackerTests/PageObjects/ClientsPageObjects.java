package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class ClientsPageObjects extends CommonToAllTests
{
	WebDriver driver;


	
	@FindBy(xpath = "//div[contains(@col-id,'ghcUserDisplayNames') and @role='gridcell']")
	public WebElement clientPageGridValue;
	
	@FindBy(xpath = "//input[contains(@class,'search-textbox')]")
	public WebElement clientSearchBox;
	
	@FindBy(xpath = "//span[contains(text(),'')]")
	public WebElement userNames;
	
	@FindBy(xpath = "//i[@class='pi pi-refresh reset-icon']")
	public WebElement resetGridButton;
	
	
	
	//
	
	
	
	public ClientsPageObjects(WebDriver driver) 
	{

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
}
