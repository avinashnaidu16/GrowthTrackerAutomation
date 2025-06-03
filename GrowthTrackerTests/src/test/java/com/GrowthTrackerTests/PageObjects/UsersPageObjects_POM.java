package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class UsersPageObjects_POM extends CommonToAllTests {

	WebDriver driver;

	public By deleteButtonGrid = By.xpath("//span[contains(@class,'delete-icon')]");

	public By cancelButtononConfirm = By.xpath("//button[contains(text(),'Cancel')]");

	public By newUserButton = By.xpath("//button[contains(text(),'new User')]");

	public By confirmationMsg = By.xpath("//span[contains(@class,'confirm-dialog-message')]");

	public By searchBox = By.xpath("//input[contains(@class,'search-textbox')]");

	

	public By clientGridValue = By.xpath("//div[contains(@col-id,'clients') and @role='gridcell']");

	public static By roleTextControl = By.xpath("//span[contains(@class,'inputtext')]");

	public By rolesSelect = By.xpath("//li[@role='option']");

	public By name = By.xpath("//input[@name='username']");

	public By selectUsername = By.xpath("//span[contains(text(),'avinash')]");

	public By clientContainer = By.xpath("//div[contains(@class,'p-multiselect-label p-placeholder')]");

	public By inputTextClient = By.xpath("//input[contains(@class,'p-multiselect-filter p-inputtext p-component')]");
	
	public By clientCheckbox = By.xpath("//div[contains(@class,'p-checkbox-box')]");
	//
	
	public By submitButton = By.xpath("//button[@class='primary-btn']");
	
	public By toastMessage= By.xpath("//div[contains(@class,'p-toast-top-right')]");
	
	public By pageHeader = By.xpath("//div[@class='page-heading']");
	
	public By firstBreadCrum = By.xpath("//a[@class='ng-star-inserted' and contains(text(),'Dashboard')]");
	public By secondBreadCrum = By.xpath("//a[@class='ng-star-inserted' and contains(text(),'Users')]");
	public By lastBreadCrum = By.xpath("//span[contains(text(),'New User')]");
	
	public By cancelButtonOnPage = By.xpath("//button[contains(@class,'secondary-btn')]");


	public By userGrid = By.xpath("//div[contains(@class,'user-grid')]");
	
	
	//  
	public UsersPageObjects_POM(WebDriver driver) {

		this.driver = driver;
		super.driver = driver;

	}
}
