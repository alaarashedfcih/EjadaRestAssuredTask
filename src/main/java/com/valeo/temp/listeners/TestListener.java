package com.valeo.temp.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.valeo.temp.utilities.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Properties;

@Slf4j
public class TestListener implements ITestListener
{

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private Logger logger = LoggerFactory.getLogger(TestListener.class);

	private String testReportFilePath;

	@Override
	public synchronized void onStart(ITestContext context)
	{
		logger.info("Extent Reports Version 5 Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context)
	{
		logger.info(("Extent Reports Version 5  Test Suite is ending!"));
		extent.flush();

		if(Constants.MAIL_CONFIG_FLAG.equalsIgnoreCase("TRUE")) {
			EmailUtils email = new EmailUtils();
			testReportFilePath = ExtentManager.filePathAndName;

			Properties prop = email.readFromPropertiesFile();
			email.sendTestReportEmail(prop.getProperty("email.user"), prop.getProperty("email.pass"),
					prop.getProperty("server.host"), prop.getProperty("server.port"), prop.getProperty("email.sender"),
					prop.getProperty("email.recepient"), prop.getProperty("email.recepientCC"),
					"[TEMP] E2E API Test Report on " + Constants.ENVIRONMENT_NAME,
					"Dears,\n\t\n\t Kindly find attached an HTML Report for TEMP E2E API Tests Execution Results after Deployment on "
							+ Constants.ENVIRONMENT_NAME + " Environment.\n\n Best Regards,\n Automation Team",
					ExtentManager.reportFileName, testReportFilePath);
		}
	}

	@Override
	public synchronized void onTestStart(ITestResult result)
	{
		logger.info((result.getMethod().getMethodName() + " started!"));

		ExtentTest extentTest =
				extent.createTest(result.getTestContext().getAttribute(result.getMethod().getMethodName()).toString(),
						result.getMethod().getDescription());

		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result)
	{
		logger.info((result.getMethod().getMethodName() + " Passed!"));
		test.get().pass("Test Passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result)
	{
		logger.info((result.getMethod().getMethodName() + " Failed!"));

		if (result.getThrowable() != null)
		{
			result.getThrowable().printStackTrace();
		}
		test.get().fail(result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result)
	{
		logger.info((result.getMethod().getMethodName() + " Skipped!"));
		if (result.getThrowable() != null)
		{
			result.getThrowable().printStackTrace();
		}
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

}