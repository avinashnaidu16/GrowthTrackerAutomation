package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class UsersPageObjects extends CommonToAllTests
{

	WebDriver driver;


	@FindBy(xpath = "//span[contains(@class,'delete-icon')]")
	public WebElement deleteButtonGrid;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement cancelButtononConfirm;
	
	@FindBy(xpath = "//button[contains(text(),'new User')]")
	public WebElement newUserButton;
	

	@FindBy(xpath = "//span[contains(@class,'confirm-dialog-message')]")
	public WebElement confirmationMsg;
	
	@FindBy(xpath = "//input[contains(@class,'search-textbox')]")
	public WebElement searchBox;
	
	
	@FindBy(xpath = "//span[contains(text(),'Avinash Naidu')]")
	public WebElement columnName;
	
	@FindBy(xpath = "//div[contains(@col-id,'clients') and @role='gridcell']")
	public WebElement clientGridValue;
	
	
	@FindBy(xpath = "//span[contains(@class,'inputtext')]")
	public static WebElement roleTextControl;
	

	public By rolesSelect=By.xpath("//li[@role='option']");
	
	public UsersPageObjects(WebDriver driver) {

		this.driver = driver;
		super.driver=driver;
		
		PageFactory.initElements(driver, this);

	}
}
