package com.GrowthTrackerTests.Utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Allure;

public class Listener implements ITestListener {

	private WebDriver driver = null;

	@Override
	public void onFinish(ITestContext contextFinish) {
		System.out.println("onFinish method finished - ");
		
		getDriver().quit();
	}

	@Override
	public void onStart(ITestContext contextStart) {
		System.out.println("onStart method started - ");
		

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Method failed with certain success percentage - " + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Method failed - " + result.getName());

		try {
			setDriver((WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Throwable testResult = result.getThrowable();
		System.out.println("entering Screenshot method");
//		if (!(testResult.getMessage().contains("Assertion Error"))) 
//		{
			if (!result.isSuccess()) {
				System.out.println("Screenshot method");
				InputStream myInputStream = new ByteArrayInputStream(
						((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));

				Allure.addAttachment("Screenshot on Test Failure - ", myInputStream);
				System.out.println("Screenshot taken");
				
			//}
		}

		/*
		 * LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
		 * System.out.println("Log1"); StringBuilder logs = new StringBuilder();
		 * System.out.println("Log2"); for (org.openqa.selenium.logging.LogEntry entry :
		 * logEntries) { logs.append(new Date(entry.getTimestamp()) + " " +
		 * entry.getLevel() + " " + entry.getMessage());
		 * logs.append(System.lineSeparator());
		 * 
		 * } System.out.println(logs); System.out.println("Log3");
		 * Allure.addAttachment("Console log: ", logs.toString());
		 */		

		getDriver().quit();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Method skipped - " + result.getName());
		try {
			setDriver((WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getDriver().quit();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Method started - " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Method passed - " + result.getName());

		try {
			setDriver((WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getDriver().quit();

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
