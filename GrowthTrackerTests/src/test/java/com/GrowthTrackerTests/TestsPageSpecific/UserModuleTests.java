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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.condition.AnyOf;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;

public class UserModuleTests extends CommonToAllTests {

	WebDriver driver;
	WebDriverWait wait;
	Properties properties;
	public String testName;

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException, InterruptedException {

		CommonToAllTests.initializeProperties();
		this.driver = CommonToAllTests.intializeDriver(driver);
		this.wait = CommonToAllTests.driverWait(driver);
		super.driver = this.driver;
		setWait(this.wait);

	}

	@Test(groups = "Regression", retryAnalyzer = RetryTest.class, enabled = true, priority = 3)
	@Description("Create user from Excel and verify its saved successfully")
	@Epic("PRF GLOBAL")
	@Feature("User Module")
	@Story("Functional")
	public void getUserDataFromExcelAndCreateUser() throws InterruptedException {

		//
		ReadExcel excel = new ReadExcel();

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", "Master Agency Admin");

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify the function of Add Master Agency Admin");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);
		WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(nextButton);

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		WebElement userTab = dashboard.waitUntilClickable(wait, dashboard.usersTab);
		Allure.step("User is logged in successfully");
		dashboard.clickElement(userTab);
		dashboard.waitforURL(wait, "User");
		dashboard.hardWait(Thread.currentThread(), 4000);
		UsersPageObjects_POM user = new UsersPageObjects_POM(driver);

		WebElement newUser = user.waitUntilClickable(wait, user.newUserButton);
		user.clickElement(newUser);
		Allure.step("Add new user form is opened");
		WebElement roleTextBox = user.waitUntilClickable(wait, user.roleTextControl);
		user.clickElement(roleTextBox);

		Map<String, String> mapTestData = excel.getUserXLData("UsersModule", "Verify the function of Add Master Agency Admin");
		System.out.println("User - " + mapTestData.get("UserType"));
		user.selectRole(this.wait, user.rolesSelect, mapTestData.get("UserType"));
		WebElement userName = user.waitUntilClickable(wait, user.name);
		user.sendText(userName, mapTestData.get("Name"));
		WebElement selectUser = user.waitForPresence(wait, user.selectUsername);
		user.clickElement(selectUser);
		Allure.step("User is selected from dropdown on Add new user page.");

		WebElement clientContainer = user.waitUntilRefreshed(wait, user.clientContainer);
		user.clickElement(clientContainer);
		Allure.step("Clients selected from dropdown on Add new user page - ");
		String[] clientsList = mapTestData.get("Client").split(",");

		for (String str : clientsList) {
			System.out.println(str.trim());
			WebElement inputTextClient = user.waitUntilRefreshed(wait, user.inputTextClient);
			user.sendText(inputTextClient, str.trim());
			hardWait(Thread.currentThread(), 3000);
			WebElement clientCheckbox = user.waitUntilClickable(wait, user.clientCheckbox);
			user.clickElement(clientCheckbox);

			final String uuid = UUID.randomUUID().toString();
			StepResult step = new StepResult().setStatus(Status.PASSED);
			Allure.getLifecycle().startStep(uuid, step);
			screenshot(str);
			Allure.getLifecycle().stopStep(uuid);
		}

		WebElement submit = user.waitForElement(wait, user.submitButton);
		user.clickElement(submit);
		Allure.step("Submit button clicked for adding New User");
		WebElement toast = user.waitUntilRefreshed(wait, user.toastMessage);

		String expectedText = mapTestData.get("ToastMessage");
		String actualText = toast.getText();
		System.out.println(expectedText);
		hardWait(Thread.currentThread(), 1000);
		screenshot();
		
		Allure.step("Results summary - ",Status.BROKEN);
		Allure.step("Actual Text in Toast is - " + actualText);
		if (expectedText.equals(actualText))
			Allure.step("Expected Text is - " + expectedText.toString());
		else
			Allure.step("Expected Text is - " + expectedText.toString(), Status.FAILED);
			

		assertThat(actualText).withFailMessage("Assertion Error : Toast Message does not match.")
				.isEqualTo(expectedText);

	}

	@Test(groups = "Regression", retryAnalyzer = RetryTest.class, enabled = true, priority = 1)
	@Description("Verify the function of cancel button")
	@Epic("PRF GLOBAL")
	@Feature("User Module")
	@Story("Functional")

	public void verifyCancelButtonOnAddnewUser() throws InterruptedException {

		//
		ReadExcel excel = new ReadExcel();

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", "Master Agency Admin");

		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify the function of cancel button");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);
		WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(nextButton);

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(),7000);
		WebElement userTab = dashboard.waitUntilClickable(wait, dashboard.usersTab);
		Allure.step("User is logged in successfully");
		dashboard.clickElement(userTab);
		dashboard.waitforURL(wait, "User");
		dashboard.hardWait(Thread.currentThread(), 5000);
		UsersPageObjects_POM user = new UsersPageObjects_POM(driver);

		WebElement newUser = user.waitUntilClickable(wait, user.newUserButton);
		user.clickElement(newUser);
		Allure.step("Add new user form is opened");
		WebElement roleTextBox = user.waitUntilClickable(wait, user.roleTextControl);
		user.clickElement(roleTextBox);

		Map<String, String> mapTestData = excel.getUserXLData("UsersModule", "Verify the function of cancel button");
		System.out.println("User - " + mapTestData.get("UserType"));
		user.selectRole(this.wait, user.rolesSelect, mapTestData.get("UserType"));
		WebElement userName = user.waitUntilClickable(wait, user.name);
		user.sendText(userName, mapTestData.get("Name"));
		WebElement selectUser = user.waitForPresence(wait, user.selectUsername);
		user.clickElement(selectUser);
		Allure.step("User is selected from dropdown");
		WebElement clientContainer = user.waitUntilRefreshed(wait, user.clientContainer);
		user.clickElement(clientContainer);
		Allure.step("Clients selected from dropdown --- ");
		String[] clientsList = mapTestData.get("Client").split(",");

		for (String str : clientsList) {
			System.out.println(str.trim());
			WebElement inputTextClient = user.waitUntilRefreshed(wait, user.inputTextClient);
			user.sendText(inputTextClient, str.trim());
			hardWait(Thread.currentThread(), 5000);
			WebElement clientCheckbox = user.waitUntilClickable(wait, user.clientCheckbox);
			user.clickElement(clientCheckbox);

			final String uuid = UUID.randomUUID().toString();
			StepResult step = new StepResult().setStatus(Status.PASSED);
			Allure.getLifecycle().startStep(uuid, step);
			screenshot(str);
			Allure.getLifecycle().stopStep(uuid);
		}

		WebElement cancel = user.waitForPresence(wait, user.cancelButtonOnPage);
		user.clickElement(cancel);
		Allure.step("Cancel Button is clicked on Add New User page");
		hardWait(Thread.currentThread(), 4000);
		WebElement grid = user.waitUntilRefreshed(wait, user.userGrid);

		hardWait(Thread.currentThread(), 1000);
		screenshot();

		Boolean boolVal = grid.isDisplayed();
		System.out.println("Grid displayed - " + boolVal.toString());
		hardWait(Thread.currentThread(), 4000);
		Allure.step("Results summary - ",Status.BROKEN);
		Allure.step("After clicking Cancel button, User Grid displayed is - " + boolVal);
		assertThat(boolVal).withFailMessage("Assertion Error : Grid not Displayed").isEqualTo(true);
		System.out.println(boolVal);

	}

	@Ignore
	@Test(dataProvider = "data-provider-ForUsernameAndPassword", dataProviderClass = ReadExcel.class, enabled = false)
	public void runTest(String username, String password) throws InterruptedException {
		LoginPageObjects login = new LoginPageObjects(driver);
		login.waitForElement(wait, login.userName);

		login.sendText(login.userName, username);

		login.clickElement(login.nextButton);

		WebElement pass = login.waitForElement(wait, login.password);
		login.sendText(pass, password);
		login.waitForElement(wait, login.nextButton);
		login.clickElement(login.nextButton);
		login.waitForElement(wait, login.nextButton);
		login.clickElement(login.nextButton);

		DashboardPageObjects dashboard = new DashboardPageObjects(driver);
		dashboard.waitforURL(wait, "Dashboard");
		hardWait(Thread.currentThread(), 7000);
		dashboard.waitForElement(wait, dashboard.usersTab);

		dashboard.clickElement(dashboard.usersTab);
		dashboard.waitforURL(wait, "User");
		UsersPageObjects user = new UsersPageObjects(driver);
		user.waitForElement(wait, user.searchBox);
		user.sendText(user.searchBox, "Avinash Naidu");
		//WebElement columnName = user.waitForElement(wait, user.columnName);
		WebElement delButton = user.waitForElement(wait, user.deleteButtonGrid);
		user.clickElement(delButton);
		user.waitForElement(wait, user.confirmationMsg);
		String actual = user.getText(user.confirmationMsg);

		String expected = user.getExpectedText("expectedConfirmationUserText");
		System.out.println("Actual Text - " + actual);
		System.out.println("Expected Text - " + expected);
		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Screenshot of Confirmation Message - ");
		Allure.getLifecycle().stopStep(uuid);
		Allure.step("Results summary - ",Status.BROKEN);
		Allure.step("Expected Text from Confirmation msg - " + expected);
		Allure.step("Actual Text - " + actual);

		Assert.assertEquals(actual, expected);

	}

	@Ignore
	@Test(groups = "Regression", enabled = false)

	@Description("Verify reading credentials from Excel")

	public void readExcelData() throws InterruptedException {
		properties = new Properties();
		String baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		String XLFilePath = baseDir + IConstants.XLFilePath;
		ReadExcel excelRead = new ReadExcel(XLFilePath);

	}

	@Test(groups = "Regression", retryAnalyzer = RetryTest.class, enabled = true, priority = 2)
	@Description("Verify the Clickables of Add new user button")
	@Epic("PRF GLOBAL")
	@Feature("User Module")
	@Story("UI Tests")

	public void VerifyAddNewUserClickable() throws InterruptedException {

		ReadExcel excel = new ReadExcel();

		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", "Master Agency Admin");
		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify the Clickables of Add new user button");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));

		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);
		WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(nextButton);

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		Allure.step("Logged in Global PRF Application");

		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		dashboard.hardWait(Thread.currentThread(), 7000);
		WebElement userTab = dashboard.waitUntilClickable(wait, dashboard.usersTab);
		dashboard.clickElement(userTab);
		dashboard.waitforURL(wait, "User");
		Allure.step("User page is opened successfully after clicking User Navigation button");
		dashboard.hardWait(Thread.currentThread(), 4000);
		UsersPageObjects_POM user = new UsersPageObjects_POM(driver);

		WebElement newUser = user.waitUntilClickable(wait, user.newUserButton);
		user.clickElement(newUser);
		Allure.step("New User page is opened successfully after clicking New User button");
		WebElement header = user.waitUntilClickable(wait, user.pageHeader);
		System.out.println("Header is - " + header.getText().toString());
		dashboard.hardWait(Thread.currentThread(), 6000);
		WebElement firstBreadCrum = user.waitUntilClickable(wait, user.firstBreadCrum);
		WebElement secondBreadCrum = user.waitUntilClickable(wait, user.secondBreadCrum);
		WebElement thirdBreadCrum = user.waitUntilClickable(wait, user.lastBreadCrum);
		screenshot();
		System.out.println("Breadcrum is - " + firstBreadCrum.getText().toString());
		System.out.println("Breadcrum is - " + secondBreadCrum.getText().toString());
		System.out.println("Breadcrum is - " + thirdBreadCrum.getText().toString());
		Allure.step("Header and BreadCrum from Add New User page retreived successfully");
		
		Allure.step("Results summary - ",Status.BROKEN);
		Allure.step("Header is - " + header.getText());
		Allure.step("Bread Crum is - " + firstBreadCrum.getText() + " / " + secondBreadCrum.getText() + " / "
				+ thirdBreadCrum.getText());

		SoftAssertions BreadCrumAsserts = new SoftAssertions();
		BreadCrumAsserts.assertThat(firstBreadCrum).withFailMessage("Assertion Error : First BreadCrum is null")
				.isNotEqualTo(null);
		BreadCrumAsserts.assertThat(secondBreadCrum).withFailMessage("Assertion Error : Second BreadCrum is null")
				.isNotEqualTo(null);
		BreadCrumAsserts.assertThat(thirdBreadCrum).withFailMessage("Assertion Error : Last BreadCrum is null")
				.isNotEqualTo(null);
		BreadCrumAsserts.assertAll();

	}

	@Test(groups = "RegressionQA", retryAnalyzer = RetryTest.class, enabled = true, priority = 4)
	@Description("Verify Users Assigned to Clients is visible in Clients Grid")
	@Epic("PRF GLOBAL")
	@Feature("User Module")
	@Story("UI Tests")
	public void testUsersAssignedToClient() throws Exception {

		// LoginPageObjects login=new LoginPageObjects(driver);
		ReadExcel excel = new ReadExcel();
		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", "Master Agency Admin");

		Map<String, String> mapTestData = excel.getUserXLData("UsersModule",
				"Verify Users Assigned to Clients is visible in Clients Grid");
		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Users Assigned to Clients is visible in Clients Grid");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));
		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);
		WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(nextButton);

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);
		Allure.step("Logged in Global PRF Application");

		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		hardWait(Thread.currentThread(), 7000);
		WebElement userTab = dashboard.waitUntilClickable(wait, dashboard.usersTab);
		dashboard.clickElement(userTab);
		dashboard.waitforURL(wait, "User");
		Allure.step("User page is opened successfully after clicking User Navigation button");
		UsersPageObjects_POM user = new UsersPageObjects_POM(driver);
		ClientsPageObjects clientpage = new ClientsPageObjects(driver);
		WebElement userText = user.waitUntilClickable(wait, user.searchBox);
		userText.clear();
		user.sendText(userText, mapTestData.get("Name"));
		hardWait(Thread.currentThread(), 7000);
		
		
		//WebElement columnName = user.waitForElement(wait, user.columnName);
		WebElement element = getGridColumnValueByText(mapTestData.get("Name"));
		WebElement columnName = user.waitForElement(wait, element);
		WebElement clientValues = user.waitForElement(wait, user.clientGridValue);
		String clientValuefromGrid = user.getText(clientValues);
		System.out.println(clientValuefromGrid);

		String[] clientsList = clientValuefromGrid.split(",");

		for (String str : clientsList) {
			System.out.println(str.trim());

		}
		Allure.step("Clients fetched successfully from User Grid.");
		WebElement dashboardClient = dashboard.waitForElement(wait, dashboard.clientsTab);
		dashboard.clickElement(dashboardClient);
		Allure.step("Client module navigation button from Navigation bar clicked successfully");
		hardWait(Thread.currentThread(), 7000);
		InputStream targetStream = null;
		int state = 1;
		String str = "";

		
		Allure.step("Results summary - ",Status.BROKEN);

		for (int i = 0; i < clientsList.length; i++) {
			clientpage.waitForElement(wait, clientpage.clientSearchBox);

			if (i == 0) {
				clientpage.sendText(clientpage.clientSearchBox, "");
				clientpage.sendText(clientpage.clientSearchBox, clientsList[i].trim());
				clientpage.clientSearchBox.clear();
				clientpage.waitUntilClickable(wait, clientpage.resetGridButton);
				clientpage.clickElement(clientpage.resetGridButton);
				clientpage.sendText(clientpage.clientSearchBox, clientsList[i].trim());

				File screenshot = CommonToAllTests.takeSnapShot(driver, IConstants.screenshotPath);
				targetStream = new FileInputStream(screenshot);

			} else
				clientpage.sendText(clientpage.clientSearchBox, clientsList[i].trim());

			WebElement userName = clientpage.waitForElement(wait, clientpage.clientPageGridValue);
			String users=userName.getText();
			Boolean varState = userName.getText().contains(mapTestData.get("Name"));
			if (varState) {
				System.out.println("User '" + mapTestData.get("Name") + "' is successfully linked to Company Name - "
						+ clientsList[i].trim());
				Allure.step("User '" + mapTestData.get("Name") + "' is successfully linked to Company Name - "
						+ clientsList[i].trim());
				
				final String uuid = UUID.randomUUID().toString();
				StepResult step = new StepResult().setStatus(Status.PASSED);
				Allure.getLifecycle().startStep(uuid, step);
				Allure.addAttachment("List of Users assigned to this client are - ", users);
				screenshot(clientsList[i].trim());
				Allure.getLifecycle().stopStep(uuid);
				
				

			} else {
				state = 0;
				Allure.step("User '" + mapTestData.get("Name") + "' is not linked to Company Name - "
						+ clientsList[i].trim() + " on Clients Grid Page.");
				final String uuid = UUID.randomUUID().toString();
				StepResult step = new StepResult().setStatus(Status.FAILED);
				Allure.getLifecycle().startStep(uuid, step);
				Allure.addAttachment("List of Users assigned to this client are - ", users);
				Allure.getLifecycle().stopStep(uuid);
				System.out.println("User '" + mapTestData.get("Name") + "' is not linked to Company Name - "
						+ clientsList[i].trim() + " on Clients Grid Page.");
				screenshot(clientsList[i].trim());
			}
			str = clientsList[i].trim() + "," + str;
			assertThat(state).withFailMessage("Assertion Error : Failed for client name - ", str).isEqualTo(1);

		}

	}

	public void screenshot(String ClientName) {

		InputStream myInputStream = new ByteArrayInputStream(
				((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

		Allure.addAttachment("Screenshot - " + ClientName, myInputStream);
	}

	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] Screenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Test(groups = "QA", retryAnalyzer = RetryTest.class, enabled = true, priority = 5)
	@Description("Verify Confirmation message while trying to delete any User")
	@Epic("PRF GLOBAL")
	@Feature("User Module")
	@Story("UI Tests")
	public void testConfirmationMsg() throws InterruptedException {

		// LoginPageObjects login=new LoginPageObjects(driver);
		ReadExcel excel = new ReadExcel();
		Map<String, String> userData = excel.getSpecifiedUserFromRole("SpecificUsers", "Master Agency Admin");

		Map<String, String> mapTestData = excel.getUserXLData("UsersModule",
				"Verify Confirmation message while trying to delete any User");
		LoginPageObjects login = new LoginPageObjects(driver);
		login.setTestCaseName("Verify Confirmation message while trying to delete any User");
		WebElement loginBy = login.waitUntilRefreshed(wait, login.userName);

		login.sendText(loginBy, userData.get("userName"));

		WebElement loginNext = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(loginNext);

		WebElement pass = login.waitUntilRefreshed(wait, login.password);
		login.sendText(pass, userData.get("password"));
		WebElement next = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(next);
		WebElement nextButton = login.waitUntilRefreshed(wait, login.nextButton);
		login.clickElement(nextButton);

		DashboardPageObjects_POM dashboard = new DashboardPageObjects_POM(driver);

		// dashboard.waitUntilClickable(wait, dashboard.dashboardIcon);
		dashboard.waitforURL(wait, "Dashboard");
		hardWait(Thread.currentThread(), 7000);
		WebElement userTab = dashboard.waitUntilClickable(wait, dashboard.usersTab);
		System.out.println("User is logged in successully");
		Allure.step("User is logged in successully");
		dashboard.clickElement(userTab);
		dashboard.waitforURL(wait, "User");
		UsersPageObjects_POM user = new UsersPageObjects_POM(driver);
		ClientsPageObjects clientpage = new ClientsPageObjects(driver);
		WebElement userText = user.waitForElement(wait, user.searchBox);
		user.sendText(userText, mapTestData.get("Name"));

		System.out.println("User name to be deleted entered in search box");
		Allure.step("User name to be deleted entered in search box on User Grid page.");
		WebElement element = getGridColumnValueByText(mapTestData.get("Name"));
		WebElement columnName = user.waitForElement(wait, element);
		WebElement delButton = user.waitForElement(wait, user.deleteButtonGrid);
		user.clickElement(delButton);

		System.out.println("Delete button clicked");
		Allure.step("Delete button clicked from Users Grid");
		WebElement confMsg= user.waitForElement(wait, user.confirmationMsg);
		String actual = user.getText(confMsg);

		String expected = mapTestData.get("ConfirmationMsg");
		System.out.println("Actual Text - " + actual);
		System.out.println("Expected Text - " + expected);

		final String uuid = UUID.randomUUID().toString();
		StepResult step = new StepResult().setStatus(Status.PASSED);
		Allure.getLifecycle().startStep(uuid, step);
		screenshot("Confirmation message screenshot - ");
		Allure.getLifecycle().stopStep(uuid);
		Allure.step("Results summary - ",Status.BROKEN);
		
		Allure.step("Actual Text in Confirmation message is - " + actual);

		if (expected.equals(actual))
			Allure.step("Expected Text from Confirmation message is - " + expected.toString());
		else
			Allure.step("Expected Text from Confirmation message is - " + expected.toString(), Status.FAILED);
			

		assertThat(actual).withFailMessage("Assertion Error : Failed Confirmation message not matching.")
				.isEqualTo(expected);

	}

	@AfterMethod
	public void closeBrowser() throws IOException {

		this.driver.quit();

	}

}