package com.GrowthTrackerTests.TestsPageSpecific;

import com.Constants.IConstants;
import com.GrowthTrackerTests.Base.*;
import com.GrowthTrackerTests.PageObjects.*;
import com.GrowthTrackerTests.Utilities.*;
import com.google.common.io.Files;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.IntPredicate;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.net.MimeMessageBuilder;
import org.apache.logging.log4j.core.pattern.HtmlTextRenderer;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.condition.AnyOf;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;
import org.openqa.selenium.support.Color;

public class GrowthTrackerFormTests extends CommonToAllTests {

	WebDriver driver;
	WebDriverWait wait;
	Properties properties;
	public String testName;

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException, InterruptedException {

		this.driver = CommonToAllTests.intializeDriver(driver);
		this.wait = CommonToAllTests.driverWait(driver);
		super.driver = this.driver;
		setWait(this.wait);

	}

	@BeforeSuite(alwaysRun = true)
	public void deleteFolders() throws IOException, InterruptedException {

		CommonToAllTests.initializeProperties();
		CommonToAllTests.deleteAllureReportFolder();

	}

	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify Description for Different Opportunity types are correct displayed.")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void descriptionDifferentOpportunityTypes() throws InterruptedException {

		//
		String expTextProspect = null, expTextPitch, expTextWithoutPitch,role;
		ReadExcel excel = new ReadExcel();

		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify Description for Different Opportunity types are correct displayed.");
		role = mapTestData.get("Role");
		

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Description for Different Opportunity types are correct displayed.");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);

		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);


		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);

		WebElement textProspect = addOpportunityPage.waitUntilClickable(wait, addOpportunityPage.getTextProspect());
		WebElement textPitch = addOpportunityPage.waitUntilClickable(wait, addOpportunityPage.getTextPitch());
		WebElement textWithoutPitch = addOpportunityPage.waitUntilClickable(wait,
				addOpportunityPage.getTextWonWithoutPitch());

		ArrayList<WebElement> allOpportunityElements = new ArrayList<WebElement>();
		allOpportunityElements.add(textProspect);
		allOpportunityElements.add(textPitch);
		allOpportunityElements.add(textWithoutPitch);

		expTextProspect = mapTestData.get("ProspectDescription");
		expTextPitch = mapTestData.get("PitchDescription");
		expTextWithoutPitch = mapTestData.get("WonWithoutPitchDescription");

		ArrayList<String> expOpportunityTexts = new ArrayList<String>();
		expOpportunityTexts.add(expTextProspect);
		expOpportunityTexts.add(expTextPitch);
		expOpportunityTexts.add(expTextWithoutPitch);

		ArrayList<String> stringTextOpportunity = new ArrayList<String>();
		stringTextOpportunity.add("Prospect");
		stringTextOpportunity.add("Pitch");
		stringTextOpportunity.add("Won Without Pitch");

		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		Allure.step("Test Validation : - ", Teststep2 -> {
			int i = 0;
			for (WebElement element : allOpportunityElements) {

				String actualTextProspect1 = element.getText();
				String expTextProspect1 = expOpportunityTexts.get(i);
				String strOpportunityType = stringTextOpportunity.get(i);
				System.out.println("Actual Text - " + actualTextProspect1);
				System.out.println("Expected Text - " + expOpportunityTexts.get(i));
				Allure.step("Compare Text for Opportunity Type - " + strOpportunityType, Teststep3 -> {

					Allure.step("Expected Text - " + expTextProspect1);
					Allure.step("Actual Text - " + actualTextProspect1);
				});
				assertThat(actualTextProspect1).withFailMessage(
						"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
						.isEqualTo(expOpportunityTexts.get(i++));

			}

		});
	}
	
	
	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify Cancel button functionality")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyCancelButtonFunctionality() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Cancel button functionality");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement cancelButton = addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getCancelButton());
		cancelButton.click();
		
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		
		
		Boolean loaderImg = addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
		
		System.out.println(loaderImg);
		WebElement gridControl = null;
		if(loaderImg)
		{
			gridControl = addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getPitchGridColumn());
			status="true";
		}
			
		
		assertThat(status).withFailMessage(
				"Assertion Error - Grid not displayed")
				.isEqualTo("true");
		
		if(status.equalsIgnoreCase("true") && gridControl.isDisplayed())
			Allure.step("Successfully navigated to Prospect Grid, Cancel Button Functionality working as expected");
		
	}
	
	

	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify Radio buttons displayed correctly As per Opportunity Type selection")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyRadioButtonAsPerOpportunity() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Radio buttons displayed correctly As per Opportunity Type selection");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		
		
		
		WebElement labelProspect=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeProspect());
		WebElement labelPitch=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypePitch());
		WebElement labelWonWithout=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
		String[] radioBtnSequence = { "Prospect", "Pitch", "Won without a Pitch" };
		
		ArrayList<WebElement> allOpportunityElements = new ArrayList<WebElement>();
		allOpportunityElements.add(labelProspect);
		allOpportunityElements.add(labelPitch);
		allOpportunityElements.add(labelWonWithout);
		
		Allure.step("Test Validation : - ", Teststep2 -> {
		if(labelProspect!=null&&labelPitch!=null&&labelWonWithout!=null)
		{
			System.out.println("Not null");
			String statusText="";
			for(int i=0;i<radioBtnSequence.length;i++)
			{
				if(allOpportunityElements.get(i).getText().equalsIgnoreCase(radioBtnSequence[i]))
				{
					Allure.step("Radio button Label is correct for Opportunity Type :- "+allOpportunityElements.get(i).getText());
					statusText="pass";
					
				}
				else
					statusText="fail";
			}	
			
			assertThat(statusText).withFailMessage(
					"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
					.isEqualTo("pass");
		}
		});

	}
	
	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify Levels Of Opportunity gets correctly displayed as per Opportunity Type selected")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyLevelOfOpportunityAfterSelectingOpportunity() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Levels Of Opportunity gets correctly displayed as per Opportunity Type selected");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		
		
		
		WebElement labelProspect=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeProspect());
		WebElement labelPitch=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypePitch());
		WebElement labelWonWithout=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
		String[] radioBtnSequenceProspect = { "Local", "Multi Market - Centrally Led" };
		String[] radioBtnSequencePitch = { "Local / Multi Market - Locally Led", "Multi Market - Centrally Led" };
		
		labelProspect.click();
		Allure.step("Clicked on Prospect radio button");

		
		
		ArrayList<WebElement> allOpportunityElementsProspect = new ArrayList<WebElement>();
		allOpportunityElementsProspect.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelProspectLocal()));
		allOpportunityElementsProspect.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelProspectCentral()));
		
		
		
		
		
		
		Allure.step("Test Validation for Prospect , Level of Opportunity : - ", Teststep2 -> {
		if(labelProspect!=null)
		{
			
			System.out.println("Not null");
			for(int i=0;i<radioBtnSequenceProspect.length;i++)
			{
				String statusText="";
				if(allOpportunityElementsProspect.get(i).getText().equalsIgnoreCase(radioBtnSequenceProspect[i]))
				{
					Allure.step("Level displayed for Opportunity Type Prospect :- "+allOpportunityElementsProspect.get(i).getText());
					statusText="pass";
					final String uuid = UUID.randomUUID().toString();
					StepResult step = new StepResult().setStatus(Status.PASSED);
					Allure.getLifecycle().startStep(uuid, step);
					screenshot("Screenshot - ");
					Allure.getLifecycle().stopStep(uuid);
				}
				 else {
						System.out.println(allOpportunityElementsProspect.get(i).getText());
						System.out.println(radioBtnSequenceProspect[i]);
						Allure.step("Below Level not displayed for Opportunity Type Pitch :- "
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
									
						statusText="fail";
						
					}
				
				assertThat(statusText).withFailMessage(
						"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
						.isEqualTo("pass");
					
			}	
		}
		});

		dashboard.hardWait(Thread.currentThread(), 4000);
		labelPitch.click();
		Allure.step("Clicked on Pitch radio button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		ArrayList<WebElement> allOpportunityElementsPitch = new ArrayList<WebElement>();
		allOpportunityElementsPitch.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelPitchLocal()));
		allOpportunityElementsPitch.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelPitchCentral()));
		
		Allure.step("Test Validation for Pitch , Level of Opportunity : - ", Teststep2 -> {
			if(labelPitch!=null)
			{
				String statusText="";
				System.out.println("Not null");
				for(int i=0;i<radioBtnSequencePitch.length;i++)
				{
					if(allOpportunityElementsPitch.get(i).getText().equalsIgnoreCase(radioBtnSequencePitch[i]))
					{
						Allure.step("Level displayed for Opportunity Type Pitch :- "+allOpportunityElementsPitch.get(i).getText());
						statusText="pass";
						final String uuid = UUID.randomUUID().toString();
						StepResult step = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid, step);
						screenshot("Screenshot - ");
						Allure.getLifecycle().stopStep(uuid);
						
					} else {
						System.out.println(allOpportunityElementsPitch.get(i).getText());
						System.out.println(radioBtnSequencePitch[i]);
						Allure.step("Below Level not displayed for Opportunity Type Pitch :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Expected :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Actual :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						statusText="fail";
						
					}
					
					assertThat(statusText).withFailMessage(
							"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
							.isEqualTo("pass");
					
				}
			}
			});
		
		
		dashboard.hardWait(Thread.currentThread(), 4000);
		labelWonWithout.click();
		Allure.step("Clicked on Won Without a Pitch radio button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Boolean labelWonWithoutPitch=addOpportunityPage.elementVisible(driver,addOpportunityPage.getLabelLevelOfOpportunity());
		
		Allure.step("Test Validation for Won Without a Pitch", Teststep2 -> {
			if(labelWonWithout!=null)
			{
				String statusText="";
				System.out.println("Not null");
				
					if(labelWonWithoutPitch==true)
					{
						Allure.step("Level is getting displayed for Opportunity Type - Won without a pitch, which is incorrect",Status.FAILED);
						
						
					} else 
					{
						
						Allure.step("Level not displayed for Opportunity Type - Won without a pitch as Expected",Status.PASSED);
						
						statusText="pass";
					}
					
					assertThat(statusText).withFailMessage(
							"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
							.isEqualTo("pass");
					
	
			}
			});
		
	}
	
	
	

	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Prospects")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyCorrectHeaderDisplayedForModalDialogBoxes() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Prospects");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		
		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Prospects");
		
		String title = mapTestData.get("ProspectPageExpectedTitle");
		String myStr = "Split a string by spaces, and also punctuation.";
		String regex = "[,]";
		String[] modalTitles = title.split(regex);
		for (String s : modalTitles) {
		  System.out.println(s);
		}
		
		WebElement labelProspect=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeProspect());
		WebElement labelPitch=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypePitch());
		WebElement labelWonWithout=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
		String[] radioBtnSequenceProspect = { "Local", "Multi Market - Centrally Led" };
		String[] radioBtnSequencePitch = { "Local / Multi Market - Locally Led", "Multi Market - Centrally Led" };
		
	
		ArrayList<WebElement> allOpportunityElementsProspect = new ArrayList<WebElement>();
		
		
	
		
		
		
		Allure.step("Validate Headers of Modal Dialog box for different levels of Prospect : - ", Teststep2 -> {
		if(labelProspect!=null)
		{
			
			
			System.out.println("Not null");
			for(int i=0;i<radioBtnSequenceProspect.length;i++)
			{
				Allure.step("Validation "+(i+1)+" for - "+radioBtnSequenceProspect[i]+" level form header",Status.BROKEN);
				WebElement labelProspect1=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeProspect());
				dashboard.hardWait(Thread.currentThread(), 4000);
				labelProspect1.click();
				Allure.step("Clicked on Prospect radio button");
				
				allOpportunityElementsProspect.clear();
				allOpportunityElementsProspect.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelProspectLocal()));
				allOpportunityElementsProspect.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelProspectCentral()));
				
				
				dashboard.hardWait(Thread.currentThread(), 4000);
				String statusText="";
				if(allOpportunityElementsProspect.get(i).getText().equalsIgnoreCase(radioBtnSequenceProspect[i]))
				{
					Allure.step("Level displayed for Opportunity Type Prospect is :- "+allOpportunityElementsProspect.get(i).getText());
					allOpportunityElementsProspect.get(i).click();
					dashboard.hardWait(Thread.currentThread(), 4000);
					Allure.step("Clicked on Radio button - "+allOpportunityElementsProspect.get(i).getText());
					WebElement nextButton=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getNextButton());
					nextButton.click();
					Allure.step("Clicked on Next Button. ");
					dashboard.hardWait(Thread.currentThread(), 4000);
					WebElement modalTitle=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalHeaderTitle());
					System.out.println(modalTitle.getText());
					
					
					if(modalTitle.getText().equalsIgnoreCase(modalTitles[i]))
					{
					Allure.step("Fetched the Title of Modal Dialog Box as - "+modalTitle.getText());
					
					statusText="pass";
					final String uuid = UUID.randomUUID().toString();
					StepResult step = new StepResult().setStatus(Status.PASSED);
					Allure.getLifecycle().startStep(uuid, step);
					screenshot("Screenshot - ");
					Allure.getLifecycle().stopStep(uuid);
					
					WebElement cancelModal=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalCancelButton());
					cancelModal.click();
					dashboard.hardWait(Thread.currentThread(), 4000);
					
					
					driver.switchTo().defaultContent();
					}
					else
					{
						System.out.println(modalTitle.getText());
						System.out.println(radioBtnSequenceProspect[i]);
						Allure.step("Header for Modal dialog does not match :- "
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ modalTitle.getText(),Status.FAILED);
									
						statusText="fail";
					}
					
					
					
				}
				 else {
						System.out.println(allOpportunityElementsProspect.get(i).getText());
						System.out.println(radioBtnSequenceProspect[i]);
						Allure.step("Below Level not displayed for Opportunity Type :- "
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ allOpportunityElementsProspect.get(i).getText(),Status.FAILED);
									
						statusText="fail";
						
					}
				
				assertThat(statusText).withFailMessage(
						"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
						.isEqualTo("pass");
					
			}	
		}
		});
	
		
	}
	

	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Pitch")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyCorrectHeaderDisplayedForModalDialogBoxesForPitch() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Pitch");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		
		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Pitch");
		
		String title = mapTestData.get("PitchPageExpectedTitle");
		
		String regex = "[,]";
		String[] modalTitles = title.split(regex);
		for (String s : modalTitles) {
		  System.out.println(s);
		}
		
		WebElement labelProspect=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeProspect());
		WebElement labelPitch=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypePitch());
		WebElement labelWonWithout=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
		
		String[] radioBtnSequencePitch = { "Local / Multi Market - Locally Led", "Multi Market - Centrally Led" };
		
	
		ArrayList<WebElement> allOpportunityElementsPitch = new ArrayList<WebElement>();
		
		
	
		
		
		
		Allure.step("Validate Headers of Modal Dialog box for different levels of Pitch : - ", Teststep2 -> {
		if(labelPitch!=null)
		{
			
			
			System.out.println("Not null");
			for(int i=0;i<radioBtnSequencePitch.length;i++)
			{
				Allure.step("Validation "+(i+1)+" for - "+radioBtnSequencePitch[i]+" level form header",Status.BROKEN);
				
				WebElement labelPitch1=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypePitch());
				dashboard.hardWait(Thread.currentThread(), 4000);
				labelPitch1.click();
				Allure.step("Clicked on Pitch radio button");
				
				allOpportunityElementsPitch.clear();
				allOpportunityElementsPitch.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelPitchLocal()));
				allOpportunityElementsPitch.add(addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getLabelPitchCentral()));
				
				
				dashboard.hardWait(Thread.currentThread(), 4000);
				String statusText="";
				if(allOpportunityElementsPitch.get(i).getText().equalsIgnoreCase(radioBtnSequencePitch[i]))
				{
					Allure.step("Level displayed for Opportunity Type Pitch is :- "+allOpportunityElementsPitch.get(i).getText());
					allOpportunityElementsPitch.get(i).click();
					dashboard.hardWait(Thread.currentThread(), 4000);
					Allure.step("Clicked on Radio button - "+allOpportunityElementsPitch.get(i).getText());
					WebElement nextButton=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getNextButton());
					nextButton.click();
					Allure.step("Clicked on Next Button. ");
					dashboard.hardWait(Thread.currentThread(), 4000);
					WebElement modalTitle=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalHeaderTitle());
					System.out.println(modalTitle.getText());
					
					
					if(modalTitle.getText().equalsIgnoreCase(modalTitles[i]))
					{
					Allure.step("Fetched the Title of Modal Dialog Box as - "+modalTitle.getText());
					
					statusText="pass";
					final String uuid = UUID.randomUUID().toString();
					StepResult step = new StepResult().setStatus(Status.PASSED);
					Allure.getLifecycle().startStep(uuid, step);
					screenshot("Screenshot - ");
					Allure.getLifecycle().stopStep(uuid);
					
					WebElement cancelModal=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalCancelButton());
					cancelModal.click();
					dashboard.hardWait(Thread.currentThread(), 4000);
					
					
					driver.switchTo().defaultContent();
					}
					else
					{
						System.out.println(modalTitle.getText());
						System.out.println(radioBtnSequencePitch[i]);
						Allure.step("Header for Modal dialog does not match :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ modalTitle.getText(),Status.FAILED);
									
						statusText="fail";
					}
					
					
					
				}
				 else {
						System.out.println(allOpportunityElementsPitch.get(i).getText());
						System.out.println(radioBtnSequencePitch[i]);
						Allure.step("Below Level not displayed for Opportunity Type :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
									
						statusText="fail";
						
					}
				
				assertThat(statusText).withFailMessage(
						"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
						.isEqualTo("pass");
					
			}	
		}
		});
	
		
	}


	

	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Won without pitch")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyCorrectHeaderDisplayedForModalDialogBoxesForWonWithoutPitch() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Won without pitch");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilRefreshed(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		
		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify correct header displayed for Modal Dialog boxes after clicking Next button on Add Opportunity page for Won without pitch");
		
		String title = mapTestData.get("WonWithoutPitchPageTitle");
		
		String regex = "[,]";
		String[] modalTitles = title.split(regex);
		for (String s : modalTitles) {
		  System.out.println(s);
		}
		
		WebElement labelWonWithout=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
		
		String[] radioBtnSequencePitch = { "" };
		
		
		
		ArrayList<WebElement> allOpportunityElementsPitch = new ArrayList<WebElement>();
		
		
		
		
		
		Allure.step("Validate Headers of Modal Dialog box for Won Without Pitch : - ", Teststep2 -> {
		if(labelWonWithout!=null)
		{
			
			
			System.out.println("Not null");
			for(int i=0;i<radioBtnSequencePitch.length;i++)
			{
				Allure.step("Validation for Won without Pitch Modal header",Status.BROKEN);
				
				WebElement labelPitch1=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getInputTypeWonWithout());
				dashboard.hardWait(Thread.currentThread(), 4000);
				labelPitch1.click();
				Allure.step("Clicked on Won without Pitch radio button");
				
			
				
				dashboard.hardWait(Thread.currentThread(), 4000);
				String statusText="";
			
					
					
					WebElement nextButton=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getNextButton());
					nextButton.click();
					Allure.step("Clicked on Next Button. ");
					dashboard.hardWait(Thread.currentThread(), 4000);
					WebElement modalTitle=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalHeaderTitle());
					System.out.println(modalTitle.getText());
					
					
					if(modalTitle.getText().equalsIgnoreCase(modalTitles[i]))
					{
					Allure.step("Fetched the Title of Modal Dialog Box as - "+modalTitle.getText());
					
					statusText="pass";
					final String uuid = UUID.randomUUID().toString();
					StepResult step = new StepResult().setStatus(Status.PASSED);
					Allure.getLifecycle().startStep(uuid, step);
					screenshot("Screenshot - ");
					Allure.getLifecycle().stopStep(uuid);
					
					WebElement cancelModal=addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getModalCancelButton());
					cancelModal.click();
					dashboard.hardWait(Thread.currentThread(), 4000);
					
					
					}
					else
					{
						System.out.println(modalTitle.getText());
						System.out.println(radioBtnSequencePitch[i]);
						Allure.step("Header for Modal dialog does not match :- "
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Expected -"
								+ allOpportunityElementsPitch.get(i).getText(),Status.FAILED);
						Allure.step("Actual -"
								+ modalTitle.getText(),Status.FAILED);
									
						statusText="fail";
					}
					
					
					
				
				
				assertThat(statusText).withFailMessage(
						"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
						.isEqualTo("pass");
					
			}	
		}
		});
	
		
	}

	
	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify tooltip text on Add Local Prospect page")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyTooltipTextOnProspectPage() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify tooltip text on Add Local Prospect page");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		AddProspectPage_POM addProspectpage = new AddProspectPage_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		// Boolean loaderImg =
		// addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
		
		//WebElement dashboardPitchItem = dashboard.waitForPresence(wait, dashboard.getDashboardPitchItem());
		
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");

		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);

		/*
		 * Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
		 * "Verify tooltip text on Add Local Prospect page");
		 * 
		 * String title = mapTestData.get("WonWithoutPitchPageTitle");
		 * 
		 * String regex = "[,]"; String[] modalTitles = title.split(regex); for (String
		 * s : modalTitles) { System.out.println(s); }
		 * 
		 */

		WebElement labelProspect = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getInputTypeProspect());
		labelProspect.click();
		Allure.step("Clicked on Prospect as Type of Opportunity");

		WebElement labelLocalProspect = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getLabelProspectLocal());

		labelLocalProspect.click();
		
		Allure.step("Clicked on Local as Level of Opportunity");

		WebElement nextButton = addOpportunityPage.waitUntilElementVisible(wait, addOpportunityPage.getNextButton());

		nextButton.click();
		
		Allure.step("Clicked on Next Button to display Add Local Prospect form");
		
		

		dashboard.hardWait(Thread.currentThread(), 4000);
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		
		
		ArrayList<WebElement> allelementsforTooltip = new ArrayList<WebElement>();
		ArrayList<String> labelsTooltip = new ArrayList<String>();
		ArrayList<By> tooltiptextofElements = new ArrayList<By>();

		WebElement labelGHC = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelGHCInfoIcon());
		
		WebElement labelLevelOfOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelLevelOfOpportunityInfoIcon());
		WebElement labelDescription = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelDescriptionInfoIcon());
		WebElement labelStatus = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelStatusInfoIcon());
		WebElement labelEstAnnBillLocal = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnBillLocal());
		WebElement labelEstAnnBillUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnBillUS());
		WebElement labelEstAnnRevLocal = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnRevLocal());
		WebElement labelEstAnnRevUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnRevUS());
		WebElement labelSourceOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelSourceOfOpportunity());
		WebElement labelGrowthLead = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelGrowthLead());

		
		
		
		
		WebElement lblGHC = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblGHCInfo());
		
		WebElement lblLevelOfOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblLevelOfOpportunityInfoIcon());
		WebElement lblDescription = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblDescriptionInfoIcon());
		WebElement lblStatus = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblStatusInfoIcon());
		WebElement lblEstAnnBillLocal = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnBillLocal());
		WebElement lblEstAnnBillUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnBillUS());
		WebElement lblEstAnnRevLocal = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnRevLocal());
		WebElement lblEstAnnRevUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnRevUS());
		WebElement lblSourceOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblSourceOfOpportunity());
		WebElement lblGrowthLead = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblGrowthLead());

		
		
		
		By texttooltipGHC = addProspectpage.getTooltipGHC();
		By texttooltipLevelOfOpportunity = addProspectpage.getTooltipLevelOfOpportunity();
		By texttooltipDescription = 
				addProspectpage.getTooltipDescription();
		By texttooltipStatus =  addProspectpage.getTooltipStatus();
		By texttooltipEstAnnBillLocal = 
				addProspectpage.getTooltipEstimatedAnnualBilling();
		By texttooltipEstAnnBillUS = 
				addProspectpage.getTooltipEstimatedAnnualBillingUS();
		By texttooltipEstAnnRevLocal = 
				addProspectpage.getTooltipEstimatedAnnualRevenue();
		By texttooltipEstAnnRevUS = 
				addProspectpage.getTooltipEstimatedAnnualRevenueUS();
		By texttooltipSourceOpportunity = 
				addProspectpage.getTooltipSourceOfOpportunity();
		By texttooltipGrowthLead = 
				addProspectpage.getTooltipGrowthLead();

		allelementsforTooltip.add(labelGHC);
		allelementsforTooltip.add(labelLevelOfOpportunity);
		allelementsforTooltip.add(labelDescription);
		allelementsforTooltip.add(labelStatus);
		allelementsforTooltip.add(labelEstAnnBillLocal);
		allelementsforTooltip.add(labelEstAnnBillUS);
		allelementsforTooltip.add(labelEstAnnRevLocal);
		allelementsforTooltip.add(labelEstAnnRevUS);
		allelementsforTooltip.add(labelSourceOpportunity);
		allelementsforTooltip.add(labelGrowthLead);

		
		labelsTooltip.add(lblGHC.getText());
		labelsTooltip.add(lblLevelOfOpportunity.getText());
		labelsTooltip.add(lblDescription.getText());
		labelsTooltip.add(lblStatus.getText());
		labelsTooltip.add(lblEstAnnBillLocal.getText());
		labelsTooltip.add(lblEstAnnBillUS.getText());
		labelsTooltip.add(lblEstAnnRevLocal.getText());
		labelsTooltip.add(lblEstAnnRevUS.getText());
		labelsTooltip.add(lblSourceOpportunity.getText());
		labelsTooltip.add(lblGrowthLead.getText());
		
		
		
		tooltiptextofElements.add(texttooltipGHC);
		tooltiptextofElements.add(texttooltipLevelOfOpportunity);
		tooltiptextofElements.add(texttooltipDescription);
		tooltiptextofElements.add(texttooltipStatus);
		tooltiptextofElements.add(texttooltipEstAnnBillLocal);
		tooltiptextofElements.add(texttooltipEstAnnBillUS);
		tooltiptextofElements.add(texttooltipEstAnnRevLocal);
		tooltiptextofElements.add(texttooltipEstAnnRevUS);
		tooltiptextofElements.add(texttooltipSourceOpportunity);
		tooltiptextofElements.add(texttooltipGrowthLead);

		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify tooltip text on Add Local Prospect page");

		String expectedTooltips = mapTestData.get("TooltipsOnForm");
		
		String regex = "[:]";
		String[] tooltipsExpected = expectedTooltips.split(regex);
		for (String s : tooltipsExpected) {
			System.out.println(s);
		}

		Actions action = new Actions(driver);
		
		
		Allure.step("Test Validation - ", () -> {
			
			String statusFinal="pass";
			 
	                // ... data loading logic ...
	            
			for (int i = 0; i < tooltipsExpected.length; i++) 
			{
				
				System.out.println("Length - "+tooltipsExpected.length);
				action.moveToElement(allelementsforTooltip.get(i)).perform();
				String label=labelsTooltip.get(i);
				action.scrollByAmount(70, 70);
				

				WebElement tooltiptext = addProspectpage.waitUntilRefreshed(wait, tooltiptextofElements.get(i));

				if (tooltiptext.isDisplayed())
				{
					System.out.println("Displayed");
					if (tooltiptext.getText().equalsIgnoreCase(tooltipsExpected[i]))
					{
						System.out.println("Text match");
						System.out.println("Tooltip of field is - " + label);
						
						Allure.step("Tooltip of field "+label+" is - "+tooltiptext.getText());
					}
					else
					{
						
						
						System.out.println("Tooltip text not matching");
						
						Allure.step("Tooltip of field - "+label+" is not matching",Status.FAILED);
						Allure.step("Expected - "+tooltipsExpected[i],Status.FAILED);
						Allure.step("Actual - "+tooltiptext.getText(),Status.FAILED);
						final String uuid1 = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid1, step1);
						screenshot("Screenshot - ");
						Allure.getLifecycle().stopStep(uuid1);
						statusFinal="false";

					}
				}
				else
				{
					System.out.println("Not Displayed");
					
				}			
				
				dashboard.hardWait(Thread.currentThread(), 2000);	

				
				
			}
			
			assertThat(statusFinal).withFailMessage(
					"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
					.isEqualTo("pass");
			
		
			
		});
		
		
	}
	
	
	
	
	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify tooltip text on Add CL Prospect page")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("UI")
	public void verifyTooltipTextOnCLProspectPage() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify tooltip text on Add CL Prospect page");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		AddCLProspectPage_POM addProspectpage = new AddCLProspectPage_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		// Boolean loaderImg =
		// addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
		
		//WebElement dashboardPitchItem = dashboard.waitForPresence(wait, dashboard.getDashboardPitchItem());
		
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");

		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);

		/*
		 * Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
		 * "Verify tooltip text on Add Local Prospect page");
		 * 
		 * String title = mapTestData.get("WonWithoutPitchPageTitle");
		 * 
		 * String regex = "[,]"; String[] modalTitles = title.split(regex); for (String
		 * s : modalTitles) { System.out.println(s); }
		 * 
		 */

		WebElement labelProspect = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getInputTypeProspect());
		labelProspect.click();
		Allure.step("Clicked on Prospect as Type of Opportunity");

		WebElement labelCLProspect = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getLabelProspectCentral());

		labelCLProspect.click();
		
		Allure.step("Clicked on Multi Market - Centrally Led as Level of Opportunity");

		WebElement nextButton = addOpportunityPage.waitUntilElementVisible(wait, addOpportunityPage.getNextButton());

		nextButton.click();
		
		
		

		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Clicked on Next Button to display Add CL Prospect form");
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		
		
		ArrayList<WebElement> allelementsforTooltip = new ArrayList<WebElement>();
		ArrayList<String> labelsTooltip = new ArrayList<String>();
		ArrayList<By> tooltiptextofElements = new ArrayList<By>();

		WebElement labelGHC = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelGHCInfoIcon());
		
		WebElement labelLevelOfOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelLevelOfOpportunityInfoIcon());
		WebElement labelDescription = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelDescriptionInfoIcon());
		WebElement labelStatus = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelStatusInfoIcon());
		
		WebElement labelEstAnnBillUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnBillUS());
		
		WebElement labelEstAnnRevUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelEstimatedAnnRevUS());
		WebElement labelSourceOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLabelSourceOfOpportunity());
		WebElement labelGrowthLead = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLabelGrowthLead());

		
		
		
		
		WebElement lblGHC = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblGHCInfo());
		
		WebElement lblLevelOfOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblLevelOfOpportunityInfoIcon());
		WebElement lblDescription = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblDescriptionInfoIcon());
		WebElement lblStatus = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblStatusInfoIcon());
		
		WebElement lblEstAnnBillUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnBillUS());
		
		WebElement lblEstAnnRevUS = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblEstimatedAnnRevUS());
		WebElement lblSourceOpportunity = addProspectpage.waitUntilRefreshed(wait,
				addProspectpage.getLblSourceOfOpportunity());
		WebElement lblGrowthLead = addProspectpage.waitUntilRefreshed(wait, addProspectpage.getLblGrowthLead());

		
		
		
		By texttooltipGHC = addProspectpage.getTooltipGHC();
		By texttooltipLevelOfOpportunity = addProspectpage.getTooltipLevelOfOpportunity();
		By texttooltipDescription = 
				addProspectpage.getTooltipDescription();
		By texttooltipStatus =  addProspectpage.getTooltipStatus();
		
		By texttooltipEstAnnBillUS = 
				addProspectpage.getTooltipEstimatedAnnualBillingUS();
		
		By texttooltipEstAnnRevUS = 
				addProspectpage.getTooltipEstimatedAnnualRevenueUS();
		By texttooltipSourceOpportunity = 
				addProspectpage.getTooltipSourceOfOpportunity();
		By texttooltipGrowthLead = 
				addProspectpage.getTooltipGrowthLead();

		allelementsforTooltip.add(labelGHC);
		allelementsforTooltip.add(labelLevelOfOpportunity);
		allelementsforTooltip.add(labelDescription);
		allelementsforTooltip.add(labelStatus);
		
		allelementsforTooltip.add(labelEstAnnBillUS);
		
		allelementsforTooltip.add(labelEstAnnRevUS);
		allelementsforTooltip.add(labelSourceOpportunity);
		allelementsforTooltip.add(labelGrowthLead);

		
		labelsTooltip.add(lblGHC.getText());
		labelsTooltip.add(lblLevelOfOpportunity.getText());
		labelsTooltip.add(lblDescription.getText());
		labelsTooltip.add(lblStatus.getText());
		
		labelsTooltip.add(lblEstAnnBillUS.getText());
		
		labelsTooltip.add(lblEstAnnRevUS.getText());
		labelsTooltip.add(lblSourceOpportunity.getText());
		labelsTooltip.add(lblGrowthLead.getText());
		
		
		
		tooltiptextofElements.add(texttooltipGHC);
		tooltiptextofElements.add(texttooltipLevelOfOpportunity);
		tooltiptextofElements.add(texttooltipDescription);
		tooltiptextofElements.add(texttooltipStatus);
		
		tooltiptextofElements.add(texttooltipEstAnnBillUS);
		
		tooltiptextofElements.add(texttooltipEstAnnRevUS);
		tooltiptextofElements.add(texttooltipSourceOpportunity);
		tooltiptextofElements.add(texttooltipGrowthLead);

		Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
				"Verify tooltip text on Add CL Prospect page");

		String expectedTooltips = mapTestData.get("TooltipsOnForm");
		
		String regex = "[:]";
		String[] tooltipsExpected = expectedTooltips.split(regex);
		for (String s : tooltipsExpected) {
			System.out.println(s);
		}

		Actions action = new Actions(driver);
		
		
		Allure.step("Test Validation - ", () -> {
			
			String statusFinal="pass";
			 
	                // ... data loading logic ...
	            
			for (int i = 0; i < tooltipsExpected.length; i++) 
			{
				
				System.out.println("Length - "+tooltipsExpected.length);
				action.moveToElement(allelementsforTooltip.get(i)).perform();
				String label=labelsTooltip.get(i);
				action.scrollByAmount(70, 70);
				
				
				

				WebElement tooltiptext = addProspectpage.waitUntilRefreshed(wait, tooltiptextofElements.get(i));

				if (tooltiptext.isDisplayed())
				{
					System.out.println("Displayed");
					if (tooltiptext.getText().equalsIgnoreCase(tooltipsExpected[i]))
					{
						System.out.println("Text match");
						System.out.println("Tooltip of field is - " + label);
						
						Allure.step("Tooltip of field "+label+" is - "+tooltiptext.getText());
					}
					else
					{
						
						
						System.out.println("Tooltip text not matching");
						
						Allure.step("Tooltip of field - "+label+" is not matching",Status.FAILED);
						Allure.step("Expected - "+tooltipsExpected[i],Status.FAILED);
						Allure.step("Actual - "+tooltiptext.getText(),Status.FAILED);
						final String uuid1 = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid1, step1);
						screenshot("Screenshot - ");
						Allure.getLifecycle().stopStep(uuid1);
						statusFinal="false";

					}
				}
				else
				{
					System.out.println("Not Displayed");
					
				}			
				
				dashboard.hardWait(Thread.currentThread(), 2000);	

				
				
			}
			
			assertThat(statusFinal).withFailMessage(
					"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
					.isEqualTo("pass");
			
			
			
		});
				
	}

	
	
	
	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = true, priority = 3)
	@Description("Verify Add Local Pitch is working as expected")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("Functional")
	public void verifyAddLocalPitch() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Add Local Pitch is working as expected");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		AddCLProspectPage_POM addProspectpage = new AddCLProspectPage_POM(driver);
		
		
		AddPitchLocal_POM addPitchLocal = new AddPitchLocal_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		
		
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		// Boolean loaderImg =
		// addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
		
		//WebElement dashboardPitchItem = dashboard.waitForPresence(wait, dashboard.getDashboardPitchItem());
		
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Navigated to Opportunity Grid successfully");

		WebElement AddOppurtunityButton = OpportunityGridObject.waitUntilClickable(wait,
				OpportunityGridObject.getNewOpportunityButton());
		AddOppurtunityButton.click();
		Allure.step("Clicked on New Opportunity button");
		dashboard.hardWait(Thread.currentThread(), 4000);

		/*
		 * Map<String, String> mapTestData = excel.getPRFXLData("OpportunityModule",
		 * "Verify tooltip text on Add Local Prospect page");
		 * 
		 * String title = mapTestData.get("WonWithoutPitchPageTitle");
		 * 
		 * String regex = "[,]"; String[] modalTitles = title.split(regex); for (String
		 * s : modalTitles) { System.out.println(s); }
		 * 
		 */

		WebElement labelProspect = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getInputTypePitch());
		labelProspect.click();
		Allure.step("Clicked on Pitch as Type of Opportunity");

		WebElement labellocalPitch = addOpportunityPage.waitUntilElementVisible(wait,
				addOpportunityPage.getLabelPitchLocal());

		labellocalPitch.click();
		
		Allure.step("Clicked on Multi Market - Centrally Led as Level of Opportunity");

		WebElement nextButton = addOpportunityPage.waitUntilElementVisible(wait, addOpportunityPage.getNextButton());

		nextButton.click();
		
		
		

		dashboard.hardWait(Thread.currentThread(), 4000);
		Allure.step("Clicked on Next Button to display Add Local Pitch form");
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		
		
		Map<String, String> mapTestData = excel.getAddOpportunityXLData("AddLocalPitch",
		"Verify Add Local Pitch is working as expected");
		 
		String Country = mapTestData.get("Country");
		String City = mapTestData.get("City");
		String Agency = mapTestData.get("Agency");
		String GHCName = mapTestData.get("GHCName");
		String LevelOfOpportunity = mapTestData.get("LevelOfOpportunity");
		String Relationship = mapTestData.get("Relationship");
		String Industry = mapTestData.get("Industry");
		String SubIndustry = mapTestData.get("SubIndustry");
		String B2B = mapTestData.get("B2B");
		String StatusOpportunity = mapTestData.get("Status");
		String Scope = mapTestData.get("Scope");
		String SourceOfBillings = mapTestData.get("SourceOfBillings");
		String SourceOfOpportunity = mapTestData.get("SourceOfOpportunity");
		String WPPAgency = mapTestData.get("WPPAgency");
		String EstimatedAnnualBillingsLocal = mapTestData.get("EstimatedAnnualBillingsLocal");
		
		
		//Select Country field
		
		WebElement btnCountry = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnCountry());
		clickElement(btnCountry);	
		WebElement countryfield = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputCountry());
		selectDropdownValue(countryfield, Country,addPitchLocal);
		
		//Select City field
		WebElement btnCity = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnCity());	
		clickElement(btnCity);
		WebElement cityfield = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputCity());
		selectDropdownValue(cityfield, City,addPitchLocal);
		
		//Select Agency field
		WebElement btnAgency= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnAgency());		
		clickElement(btnAgency);
		WebElement agencyfield = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputAgency());
		selectDropdownValue(agencyfield, Agency,addPitchLocal);
		
		//Enter GHC Name
		WebElement inputGHC= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputGHC());		
		clickElement(inputGHC);
		inputGHC.sendKeys(GHCName);
		clickElement(btnAgency);
		
		//Select GHC
		dashboard.hardWait(Thread.currentThread(), 2000);
		WebElement selectGHC= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getSelectGHC());		
		clickElement(selectGHC);
		WebElement addBtn= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getAddButtonModalBox());
		clickElement(addBtn);
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		
		//Select Level field
		WebElement btnLevel= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnLevel());
		clickElement(btnLevel);
		WebElement levelfield = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputLevel());
		selectDropdownValue(levelfield, LevelOfOpportunity,addPitchLocal);
		
		//Select Relationship field
		WebElement btnRelationship= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnRelationship());
		clickElement(btnRelationship);
		WebElement relationshipfield = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputRelationship());
		selectDropdownValue(relationshipfield, Relationship,addPitchLocal);
		
		
		String generatedString = RandomStringUtils.randomAlphanumeric(6).toUpperCase();     System.out.println(generatedString);
		
		//Enter Advertiser / Product name
				WebElement inputAdvertiser= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputAdvertiser());		
				clickElement(inputAdvertiser);
				String textInputAdverstiserName="TSTQA"+generatedString;
				inputAdvertiser.sendKeys(textInputAdverstiserName);
				System.out.println("TSTQA"+generatedString);
		
		//Select Industry field
		WebElement btnIndustry= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnIndustry());
		clickElement(btnIndustry);
		WebElement industryField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputIndustry());
		selectDropdownValue(industryField, Industry,addPitchLocal);

		
		//Select Sub Industry field
		WebElement btnSubIndustry= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnSubIndustry());
		clickElement(btnSubIndustry);
		WebElement subindustryField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputSubIndustry());
		selectDropdownValue(subindustryField,SubIndustry,addPitchLocal);

		
		//Click B2B
		WebElement labelB2B= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getLabelB2B());
		clickElement(labelB2B);
		
		

		//Click B2B All 
		WebElement labelB2BAll= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getLabelB2BAll());
		clickElement(labelB2BAll);
		
		

		//Enter Description 
		WebElement txtDescription= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputtextDescription());
		txtDescription.sendKeys(textInputAdverstiserName);
		
		
		
		//select DateInvited field
		WebElement dateInvitedfield= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputDateInvited());
		clickElement(dateInvitedfield);
		
		WebElement dateInvited= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getDateDateInvited());
		clickElement(dateInvited);
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		
		//Select Status field
		WebElement btnStatus= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnStatus());
		clickElement(btnStatus);
		WebElement statusField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputStatus());
		selectDropdownValue(statusField, StatusOpportunity,addPitchLocal);
		
		
		//Select Scope field
		Actions action = new Actions(driver);
		WebElement labelScope= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getLabelScope());
		clickElement(labelScope);
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		WebElement labelOptions = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getlabelScopedropdownoptions());
		DropDownselect(labelOptions, Scope);
		
		
		WebElement radioBtnYesWPP= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getRadioBtnWPPOpenYes());
		clickElement(radioBtnYesWPP);
		
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		WebElement dateFinalPresentationfield= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputDateFinalPresentation());
		clickElement(dateFinalPresentationfield);
		
		WebElement dateFinalPresentation= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getDateFinalPresentation());
		clickElement(dateFinalPresentation);
		
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		// Enter Billings local value

		WebElement inputBillings = addPitchLocal.waitUntilRefreshed(wait,addPitchLocal.getInputEstimatedAnnualBillingLocal());
		inputBillings.sendKeys(EstimatedAnnualBillingsLocal);

		
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		// Select Source of Billings field
		WebElement btnSourceBilling= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnSourceBilling());
		clickElement(btnSourceBilling);
		WebElement sourceBillingField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputSourceBilling());
		selectDropdownValue(sourceBillingField, SourceOfBillings,addPitchLocal);
		
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		//Select Source of Opportunity field
		WebElement btnSourceOpportunity= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getBtnSourceOpportunity());
		clickElement(btnSourceOpportunity);
		WebElement sourceOpportunityField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputSourceOpportunity());
		selectDropdownValue(sourceOpportunityField, SourceOfOpportunity,addPitchLocal);
		
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		//Enter Auditor 
		WebElement txtAuditor= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputAuditor());
		txtAuditor.sendKeys(textInputAdverstiserName);
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		//select Date Contract Starts field
				WebElement dateContractStarts= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputDateContractStarts());
				clickElement(dateContractStarts);
				
				WebElement dateContractValue= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getDateContractStarts());
				clickElement(dateContractValue);
				dashboard.hardWait(Thread.currentThread(), 2000);

		//Enter Growth Lead 
		WebElement txtGrowthLead= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputGrowthLead());
		txtGrowthLead.sendKeys(textInputAdverstiserName);
				
		dashboard.hardWait(Thread.currentThread(), 2000);

		//Enter Account Lead 
		WebElement txtAccountLead= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputAccountLead());
		txtAccountLead.sendKeys(textInputAdverstiserName);
		
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		
		
				
				

		//Select WPP Agency field
		WebElement btnWPP= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getLabelWPPAgency());
		clickElement(btnWPP);
		WebElement wppField = addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getLabelWPPDropdownoptions());
		
		DropDownselect(wppField, WPPAgency);
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		

		//Enter Next Steps
		WebElement inputNextSteps= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getInputtextNextSteps());
		clickElement(inputNextSteps);
		inputNextSteps.sendKeys(textInputAdverstiserName);
				
		dashboard.hardWait(Thread.currentThread(), 2000);
		
		
		//Click on Submit Button
		
		WebElement buttonSubmit= addPitchLocal.waitUntilRefreshed(wait, addPitchLocal.getSubmitButton());
		clickElement(buttonSubmit);
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		//Wait for Loader to Disappear
		Boolean loaderImg = addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
			
			System.out.println(loaderImg);
			WebElement gridControl = null;
			if(loaderImg)
			{
				gridControl = addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getPitchGridColumn());
				status="true";
			}
			
		
		
		WebElement gridAdvertiserInput= OpportunityGridObject.waitUntilRefreshed(wait, OpportunityGridObject.getGridfilterInputAdvertiser());	
		gridAdvertiserInput.sendKeys(textInputAdverstiserName);
		
		
		dashboard.hardWait(Thread.currentThread(), 8000);
		
		ArrayList<WebElement> allelementsforTooltip = new ArrayList<WebElement>();
		ArrayList<String> labelsTooltip = new ArrayList<String>();
		ArrayList<By> tooltiptextofElements = new ArrayList<By>();


		List<WebElement> elements = driver.findElements(OpportunityGridObject.getGridColumnValues());

		for (WebElement element : elements) {
		    System.out.println("Column values text:" + element.getText());
		}
		

		WebElement advertiserLink=driver.findElement(By.linkText(textInputAdverstiserName));
		
		OpportunityGridObject.clickElement(advertiserLink);
		
		
		
		String expectedTooltips = mapTestData.get("TooltipsOnForm");
		
		String regex = "[:]";
		String[] tooltipsExpected = expectedTooltips.split(regex);
		for (String s : tooltipsExpected) {
			System.out.println(s);
		}


		elements = driver.findElements(OpportunityGridObject.getGridColumnValues());

		for (WebElement element : elements) {
		    System.out.println("Column values text:" + element.getText());
		}
		
		
		Allure.step("Test Validation - ", () -> {
			
			String statusFinal="pass";
			 
	                // ... data loading logic ...
	            
			for (int i = 0; i < tooltipsExpected.length; i++) 
			{
				
				System.out.println("Length - "+tooltipsExpected.length);
				action.moveToElement(allelementsforTooltip.get(i)).perform();
				String label=labelsTooltip.get(i);
				action.scrollByAmount(70, 70);
				

				WebElement tooltiptext = addProspectpage.waitUntilRefreshed(wait, tooltiptextofElements.get(i));

				if (tooltiptext.isDisplayed())
				{
					System.out.println("Displayed");
					if (tooltiptext.getText().equalsIgnoreCase(tooltipsExpected[i]))
					{
						System.out.println("Text match");
						System.out.println("Tooltip of field is - " + label);
						
						Allure.step("Tooltip of field "+label+" is - "+tooltiptext.getText());
					}
					else
					{
						
						
						System.out.println("Tooltip text not matching");
						
						Allure.step("Tooltip of field - "+label+" is not matching",Status.FAILED);
						Allure.step("Expected - "+tooltipsExpected[i],Status.FAILED);
						Allure.step("Actual - "+tooltiptext.getText(),Status.FAILED);
						final String uuid1 = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid1, step1);
						screenshot("Screenshot - ");
						Allure.getLifecycle().stopStep(uuid1);
						statusFinal="false";

					}
				}
				else
				{
					System.out.println("Not Displayed");
					
				}			
				
				dashboard.hardWait(Thread.currentThread(), 2000);	

				
				
			}
			
			assertThat(statusFinal).withFailMessage(
					"Assertion Error - One or more steps failed, please expand and review Test validation steps for exact failure")
					.isEqualTo("pass");
			
			
			
		});
				
	}
	
	
	
	
	

	
	@Test(groups = "Smoke", retryAnalyzer = RetryTest.class, enabled = false, priority = 3)
	@Description("Verify Add Local Pitch is working as expected")
	@Epic("Growth Tracker")
	@Feature("New Opportunity Module")
	@Story("Functional")
	public void verifyAddLocalPitch123() throws InterruptedException {

		//
		String status = null;
		ReadExcel excel = new ReadExcel();
		String role = "Master Agency Admin";

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", role);

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Add Local Pitch is working as expected");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);

		try {
			if (login.nextButton.isDisplayed()) {
				WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
				login.clickElement(nextButton);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Next Button Exception");
		}

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		OpportunityGridPage_POM OpportunityGridObject = new OpportunityGridPage_POM(driver);
		AddOpportunityPage_POM addOpportunityPage = new AddOpportunityPage_POM(driver);
		AddCLProspectPage_POM addProspectpage = new AddCLProspectPage_POM(driver);
		
		
		AddPitchLocal_POM addPitchLocal = new AddPitchLocal_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		
		
		Allure.step("Application Login successful");
		dashboard.hardWait(Thread.currentThread(), 4000);
		
		
		// Boolean loaderImg =
		// addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
		
		//WebElement dashboardPitchItem = dashboard.waitForPresence(wait, dashboard.getDashboardPitchItem());
		
		WebElement opportunityTab = null;
		if (role.equalsIgnoreCase("Master Agency Admin"))
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabMaster());
		else
			opportunityTab = dashboard.waitUntilClickable(wait, dashboard.getOpportunitiesTabOther());

		dashboard.clickElement(opportunityTab);

		dashboard.waitforURL(wait, "Opportunity");
		
		dashboard.hardWait(Thread.currentThread(), 4000);
		//Wait for Loader to Disappear
		Boolean loaderImg = addOpportunityPage.waitUntilElementInVisible(wait,addOpportunityPage.getLoaderImage());
			
			System.out.println(loaderImg);
			WebElement gridControl = null;
			if(loaderImg)
			{
				gridControl = addOpportunityPage.waitUntilElementVisible(wait,addOpportunityPage.getPitchGridColumn());
				status="true";
				System.out.println("In Loop");
			}
			
			
		Allure.step("Navigated to Opportunity Grid successfully");
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeAsyncScript("window.scrollBy(0,document.body.scrollHeight)");
		
		dashboard.hardWait(Thread.currentThread(), 7000);
				
	}


}
