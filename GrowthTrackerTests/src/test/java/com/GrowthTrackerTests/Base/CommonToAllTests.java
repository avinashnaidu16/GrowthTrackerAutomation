package com.GrowthTrackerTests.Base;

import com.Constants.*;

import static com.GrowthTrackerTests.Base.CommonToAllTests.appUrl;
import static com.GrowthTrackerTests.Base.CommonToAllTests.waitSeconds;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Constants.IConstants;
import com.GrowthTrackerTests.PageObjects.AddPitchLocal_POM;
import com.GrowthTrackerTests.PageObjects.DashboardPageObjects_POM;
import com.GrowthTrackerTests.PageObjects.GrowthTrackerFormPageObjects_POM;
import com.GrowthTrackerTests.PageObjects.UsersPageObjects;
import com.GrowthTrackerTests.PageObjects.UsersPageObjects_POM;
import com.GrowthTrackerTests.Utilities.Listener;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import net.bytebuddy.asm.Advice.This;

public class CommonToAllTests extends Listener implements IConstants {

	public WebDriver driver;
	private WebDriverWait wait;
	static String browser;
	static String appUrl;
	static String waitSeconds;
	public static String userName;
	public static String password;
	static String expectedConfirmationUserText;
	static Properties properties;

	
	public String path;
	public FileInputStream fis = null;
	
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	Integer rowCount, colCount;
	

	
	public static void initializeProperties() {
		properties = new Properties();
		String baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		String propFilePath = baseDir + IConstants.propFilePath;
		try {
			FileInputStream fileInputStream = new FileInputStream(propFilePath);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		browser = properties.getProperty("browser");

		appUrl = properties.getProperty("appUrl");
		
		waitSeconds = properties.getProperty("seconds");
		userName = properties.getProperty("userName");
		password = properties.getProperty("passWord");
		expectedConfirmationUserText = properties.getProperty("expectedConfirmationUserText");

	}
	
	public static void deleteAllureReportFolder() throws IOException {
		properties = new Properties();
		String baseDir = System.getProperty("user.dir");
		System.out.println(baseDir);
		String AllureFilePath = baseDir + IConstants.AllureReportFolder;
		
		System.out.println(AllureFilePath);
		File file=new File(AllureFilePath);
		
		String[] entries = file.list();for (String s : entries) {
		    File currentFile = new File(file.getPath(), s);
		    currentFile.delete();
		    System.out.println("File deleted successfully");      
		}
		
		Boolean bool= file.delete();
		if (bool) 
		{
		    System.out.println("Allure Report Folder deleted successfully");                 
		} else 
		{
			System.out.println("Allure Report Folder delete failed");
			
		}

		
	}
	
	public void hardWait(Thread Thread, int seconds) throws InterruptedException
	{
		Thread.sleep(seconds);
	}
	
	public WebElement getGridColumnValueByText(String text) throws InterruptedException
	{
		 By columnName = By.xpath("//span[contains(text(),'"+text+"')]");
		 WebElement element=driver.findElement(columnName);
		 return element;
	}
	
	public void screenshot(String ClientName) 
	{
	    
	    InputStream myInputStream = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)); 
	    
	    Allure.addAttachment("Screenshot - "+ClientName, myInputStream);
	}
	

	public void screenshot() 
	{
	    
	    InputStream myInputStream = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)); 
	    
	    Allure.addAttachment("Screenshot - ", myInputStream);
	}
	
	public void setTestCaseName(String testCaseName)
	{
		 AllureLifecycle lifecycle = Allure.getLifecycle();
			lifecycle.updateTestCase(testResult -> testResult.setName(testCaseName));
			
	}


	public static WebDriver intializeDriver(WebDriver driver) {
		
		if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions option = new EdgeOptions();
			
			option.addArguments("inprivate");
			option.setAcceptInsecureCerts(true);
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			driver = new EdgeDriver(option);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

			driver.get(appUrl);
			Allure.step("Application URL - "+appUrl);
			

		}
		
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--inprivate");
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(option);
			driver.manage().window().maximize();

			driver.get(appUrl);

		}
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("inprivate");
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new FirefoxDriver(option);
			driver.manage().window().maximize();

			driver.get(appUrl);

		}
		return driver;

	}

	public static WebDriverWait driverWait(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(waitSeconds)));
		
		return wait;
	}

	public WebElement waitForElement(WebDriverWait wait, WebElement Element) {
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(Element));		
		return webElement;
	}
	
	public WebElement waitForElementwithText(WebDriverWait wait, WebElement Element,String text) {
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(Element));		
		return webElement;
	}
	
	public static File takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		return DestFile;
		
	}
	
	public WebElement waitForElement(WebDriverWait wait, By by) {
		WebElement newEle=driver.findElement(by);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOf(newEle));		
		return webElement;
	}
	
	
	

	public WebElement waitForPresence(WebDriverWait wait, By Element) {
		WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(Element));
		return webElement;
	}
	
	public WebElement waitUntilRefreshed(WebDriverWait wait, By Element) {
		WebElement WB=driver.findElement(Element);
		WebElement webElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(WB)));
		return webElement;
	}
	
	public WebElement waitUntilClickable(WebDriverWait wait, By Element) {
		
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(Element));
		return webElement;
	}
	public void waitforURL(WebDriverWait wait, String page) throws InterruptedException {

		if (page.equalsIgnoreCase("Dashboard")) 
		{
			
			wait.until(ExpectedConditions
					.urlToBe("https://insidemedia.sharepoint.com/sites/GrowthTracker_Test/Pages/Index.aspx#/"));
		}
		if (page.equalsIgnoreCase("Opportunity")) {
			
			wait.until(ExpectedConditions
					.urlToBe("https://insidemedia.sharepoint.com/sites/GrowthTracker_Test/Pages/Index.aspx#/opportunities"));
		}
		
		
		
	}
	
	public String getBackgroundColor(WebElement Element) {
		String backGroundcolor = Element.getCssValue("background-color");
		
		
		System.out.println("Back  - "+backGroundcolor);
		
		String hex = Color.fromString(backGroundcolor).asHex();
		System.out.println("Back color - "+hex);
		return hex;
	}
	
	public String getBorderColor(WebElement Element) {
		String bordercolorTop = Element.getCssValue("border-top-color");
		String bordercolorbottom = Element.getCssValue("border-bottom-color");
		String bordercolorLeft = Element.getCssValue("border-left-color");
		String bordercolorRight = Element.getCssValue("border-right-color");
		String border = Element.getCssValue("border");
		System.out.println("Border  - "+border);
		String backGroundcolorTopHex = Color.fromString(bordercolorTop).asHex();
		String backGroundcolorbottomHex = Color.fromString(bordercolorbottom).asHex();
		String backGroundcolorLeftHex = Color.fromString(bordercolorLeft).asHex();
		String backGroundcolorRightHex = Color.fromString(bordercolorRight).asHex();
		System.out.println("Border color - "+backGroundcolorTopHex);
		System.out.println("Border color - "+backGroundcolorbottomHex);
		System.out.println("Border color - "+backGroundcolorLeftHex);
		System.out.println("Border color - "+backGroundcolorRightHex);
		
		if(backGroundcolorTopHex.equalsIgnoreCase(backGroundcolorRightHex))
		{
			if(backGroundcolorLeftHex.equalsIgnoreCase(backGroundcolorbottomHex))
				return backGroundcolorbottomHex;
		}
		else
		{
			return "failed";
		}
		return backGroundcolorbottomHex;
	}
	
	public WebElement waitUntilElementVisible(WebDriverWait wait, By Element) {
		
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
		return webElement;
	}
	
	public Boolean elementVisible(WebDriver driver, By Element) {
		
		Boolean state = driver.findElement(Element).isDisplayed();
		return state;
	}
	
	public Boolean waitUntilElementInVisible(WebDriverWait wait, By Element) {
		WebElement WB=driver.findElement(Element);
		Boolean state=wait.until(ExpectedConditions.invisibilityOfElementLocated(Element));
		return state;
		
	}
	
	public WebElement waitUntilClickable(WebDriverWait wait, WebElement Element) {
		
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(Element));
		return webElement;
	}
	
	
	public WebElement waitUntilRefreshed(WebDriverWait wait, WebElement Element) {
		
		WebElement webElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(Element)));
		return webElement;
	}
	
	
	public void sendText(WebElement Element, String str) {
		
		Element.click();
		
		Element.clear();
		Element.sendKeys(str);

	}

	public void clickElement(WebElement Element) {
		Element.click();

	}

	public String getText(WebElement Element) {
		return Element.getText();

	}

	public String getExpectedText(String actual) {
		return expectedConfirmationUserText = properties.getProperty(actual);

	}
	
	
	public void selectRole(WebDriverWait wait, By select, String role) throws InterruptedException 
	{
		
		
		
		Thread.sleep(5000);
		WebElement elem=driver.findElement(select);
		waitForElement(wait, elem);
		List<WebElement> allOptions = driver.findElements(select);
		System.out.println(allOptions.size());

		for (WebElement el : allOptions) 
		{
			
			if (el.getText().contains(role)) {
				waitForElement(wait, el);
				el.click();
			}
		}
		

	}
	
	public Integer verifyRequiredFieldLabelsOnPage(By requiredField, WebDriver driver) throws InterruptedException 
	{
		
		List<WebElement> list=driver.findElements(requiredField);
		System.out.println(list.size());
		return list.size();
		
	}
	
	public String verifyUnsavedChangesPopupOnLeftNavigation(List<WebElement> list, WebDriver driver, By unsavedChangesPopup, By unsavedChangesPopupBtn) throws InterruptedException 
	{

		String result = "PASS";
		WebElement popup,CancelBtn;
		
		for (WebElement element : list) 
		{
			
			System.out.println(list.indexOf(element));
			element.click();
			Thread.sleep(3000);
			popup = driver.findElement(unsavedChangesPopup);
			CancelBtn = driver.findElement(unsavedChangesPopupBtn);
			

			if (popup.isDisplayed()) 
			{
				if(list.indexOf(element)<4)
				{
					Allure.step("Unsaved changes popup successfully displayed on clicking Left navigation element - "+ element.getText(), Status.SKIPPED);
					
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
				}
				else
				{

					Allure.step("Unsaved changes popup successfully displayed on clicking Bread Crumb element - "+ element.getAccessibleName(), Status.SKIPPED);
					
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
					
				}			
				CancelBtn.click();
				Allure.step(CancelBtn.getText()+" Button clicked successfully", Status.PASSED);
				Thread.sleep(3000);
				
			} 
			else 
			{
				
				if(list.indexOf(element)<4)
				{
					Allure.step("Unsaved changes popup display failed on clicking Left navigation element"+element.getAccessibleName(), Status.FAILED); 
					  
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.FAILED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
				}
				else
				{

					Allure.step("Unsaved changes popup display failed on clicking Bread Crumb element - "+element.getAccessibleName(), Status.FAILED);
					final String uuid = UUID.randomUUID().toString();
					StepResult step1 = new StepResult().setStatus(Status.FAILED);
					Allure.getLifecycle().startStep(uuid, step1);
					screenshot();
					Allure.getLifecycle().stopStep(uuid);
					
					
					
				}			
			
				
				result = "FAIL";
			}

			
		}

		return result;
	}
	
	
	public String verifyUnsavedChangesPopupOnLeftNavigationNoButton(WebElement element, WebDriver driver, By unsavedChangesPopup, By unsavedChangesPopupNoBtn) throws InterruptedException 
	{

		String result="Pass";
		WebElement popup,noBbutton;
		popup = driver.findElement(unsavedChangesPopup);
		noBbutton = driver.findElement(unsavedChangesPopupNoBtn);
		

			if (popup.isDisplayed()) 
			{
			
				String accName=element.getAccessibleName().toString();
				System.out.println(accName);
				Allure.step("Unsaved changes popup successfully displayed on clicking Bread Crumb element - "+ element.getAccessibleName(), Status.PASSED);										
				noBbutton.click();
				hardWait(Thread.currentThread(), 4000);
				Allure.step("No Button on Popup clicked successfully - "+ element.getAccessibleName(), Status.PASSED);
				System.out.println(element.getAccessibleName());
				
				
				if(accName.equalsIgnoreCase("Dashboard"))
				{
					GrowthTrackerFormPageObjects_POM prf=new GrowthTrackerFormPageObjects_POM(driver);
					Boolean element1= driver.findElement(prf.getPRFGrid()).isDisplayed();
					
					System.out.println(element1);
					if(element1.equals(true))
					{

						Allure.step("Dashboard page is not displayed while navigating from Breadcrumb",Status.FAILED);
						final String uuid1 = UUID.randomUUID().toString();
						StepResult step11 = new StepResult().setStatus(Status.FAILED);
						Allure.getLifecycle().startStep(uuid1, step11);
						screenshot();
						Allure.getLifecycle().stopStep(uuid1);
						result="Fail";
						return result;
						
					}
				}
				final String uuid = UUID.randomUUID().toString();
				StepResult step1 = new StepResult().setStatus(Status.BROKEN);
				Allure.getLifecycle().startStep(uuid, step1);
				screenshot();
				Allure.getLifecycle().stopStep(uuid);
				
			} 
			else 
			{							

					Allure.step("Unsaved changes popup display failed on clicking Bread Crumb element - "+element.getAccessibleName(), Status.FAILED);
					final String uuid = UUID.randomUUID().toString();
					StepResult step1 = new StepResult().setStatus(Status.FAILED);
					Allure.getLifecycle().startStep(uuid, step1);
					screenshot();
					Allure.getLifecycle().stopStep(uuid);	
					result="Fail";
					return result;
				
			}

			return result;
		}

	
	
	

	
	public String verifyUnsavedChangesPopup(List<WebElement> list, WebDriver driver, By unsavedChangesPopup, By unsavedChangesPopupBtn) throws InterruptedException 
	{

		String result = "PASS";
		WebElement popup,CancelBtn;
		
		for (WebElement element : list) 
		{
			
			System.out.println(list.indexOf(element));
			element.click();
			Thread.sleep(3000);
			popup = driver.findElement(unsavedChangesPopup);
			CancelBtn = driver.findElement(unsavedChangesPopupBtn);
			

			if (popup.isDisplayed()) 
			{
				if(list.indexOf(element)<3)
				{
					Allure.step("Unsaved changes popup successfully displayed on clicking Top line navigation element - Logo Images", Status.SKIPPED);
					
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
				}
				else
				{

					Allure.step("Unsaved changes popup successfully displayed on clicking Top line navigation element - "+ element.getAccessibleName(), Status.SKIPPED);
					
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.PASSED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
					
				}			
				CancelBtn.click();
				Allure.step(CancelBtn.getText()+" Button clicked successfully", Status.PASSED);
				Thread.sleep(3000);
			} 
			else 
			{
				
				if(list.indexOf(element)<3)
				{
					Allure.step("Unsaved changes popup display failed on clicking Top line navigation element - Logo Images"+element.getAccessibleName(), Status.FAILED); 
					  
						
						final String uuid = UUID.randomUUID().toString();
						StepResult step1 = new StepResult().setStatus(Status.FAILED);
						Allure.getLifecycle().startStep(uuid, step1);
						screenshot();
						Allure.getLifecycle().stopStep(uuid);
						
					
				}
				else
				{

					Allure.step("Unsaved changes popup display failed on clicking Top line navigation element - "+element.getAccessibleName(), Status.FAILED);
					final String uuid = UUID.randomUUID().toString();
					StepResult step1 = new StepResult().setStatus(Status.FAILED);
					Allure.getLifecycle().startStep(uuid, step1);
					screenshot();
					Allure.getLifecycle().stopStep(uuid);
					
					
					
				}			
			
				
				result = "FAIL";
			}

			
		}

		return result;
	}
	
	
	public void DropDownselect(WebElement RevenueBookingTypeList, String BookingType)
	{
		
		List<WebElement> options = RevenueBookingTypeList.findElements(By.tagName("li"));
		System.out.println("Size - "+options.size());
		for (WebElement option : options)
		{
			
			
			  if (option.getText().equals(BookingType)) 
			  { 
				  option.click(); 
				  break; 
			  }
			 
		}
		
		
	}
	

	  

	// Waits
	public void implicitWait() {
		// DriverManagerTL.getDriver().manage().timeouts().implicitlyWait(10,
		// TimeUnit.SECONDS);
	}
	// Driver Call

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	
	public void selectDropdownValue(WebElement countryfield, String country, AddPitchLocal_POM pitchLocal) throws InterruptedException
	{
		
		countryfield.sendKeys(country);
		countryfield.sendKeys(Keys.ENTER);
		pitchLocal.hardWait(Thread.currentThread(), 4000);
	}
	
	public void selectDate(AddPitchLocal_POM pitchLocal,String month, WebDriver driver, WebElement dateFinal) throws InterruptedException
	{
		
		WebElement dateField= dateFinal.findElement(By.xpath("//span[contains(text(),'"+month+"')]"));
		dateField.click();
		pitchLocal.hardWait(Thread.currentThread(), 4000);
	}
}
