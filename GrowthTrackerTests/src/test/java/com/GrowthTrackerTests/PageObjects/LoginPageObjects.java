package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class LoginPageObjects extends CommonToAllTests {

	WebDriver driver;

	@FindBy(name = "loginfmt")
	public WebElement userName;

	@FindBy(xpath = "//input[contains(@id,'idSIButton')]")
	public WebElement nextButton;

	@FindBy(name = "passwd")
	public WebElement password;

	@FindBy(id = "idSIButton9")
	public WebElement confirmButton;
	
	
	

	public LoginPageObjects(WebDriver driver) {
		
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	
	

}
