package com.GrowthTrackerTests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GrowthTrackerTests.Base.CommonToAllTests;

public class LoginPageObjects_POM extends CommonToAllTests {

	WebDriver driver;


	public By userName = By.name("loginfmt");
    
	public By nextButton = By.xpath("//input[contains(@id,'idSIButton')]");

	public By password = By.name("passwd");
	
	public By confirmButton = By.id("idSIButton9");

	

	public LoginPageObjects_POM(WebDriver driver) {
		

		this.driver = driver;
		super.driver=driver;

	}
	
	
	
	

}
