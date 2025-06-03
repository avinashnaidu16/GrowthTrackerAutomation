package com.GrowthTrackerTests.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {
	private static final Logger LOG = LogManager.getLogger("RetryTest.class");
	private static final int maxTry = 1;
	private int count = 0;

	@Override
	public boolean retry(final ITestResult iTestResult) {

		Throwable testResult = iTestResult.getThrowable();
		System.out.println("Trace Message is --- " + testResult.getStackTrace());
		System.out.println("Local Message is --- " + testResult.getLocalizedMessage());
		System.out.println("Message is --- " + testResult.getMessage());
		if (!(testResult.getMessage().contains("Assertion Error")||testResult.getMessage().contains("element click intercepted")
				||testResult.getMessage().contains("Cannot invoke \"String.equalsIgnoreCase(String)"))) {
			if (!iTestResult.isSuccess()) {

				if (this.count < maxTry) {
					LOG.info("Retrying test " + iTestResult.getName() + " with status "
							+ getResultStatusName(iTestResult.getStatus()) + " for the " + (this.count + 1)
							+ " time(s).");
					this.count++;
					return true;
				}
			}
		}
		return false;
	}

	public String getResultStatusName(final int status) {
		String resultName = null;
		if (status == 1) {
			resultName = "SUCCESS";
		}
		if (status == 2) {
			resultName = "FAILURE";
		}
		if (status == 3) {
			resultName = "SKIP";
		}
		return resultName;
	}
}