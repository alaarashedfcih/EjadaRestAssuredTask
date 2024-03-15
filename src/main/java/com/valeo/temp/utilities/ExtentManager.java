package com.valeo.temp.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ExtentManager
{
	private static ExtentReports extent;
	private static String platform;
	public static String reportFileName =
			"TEMP_E2E_API_ExecutionReport" + "_" + new SimpleDateFormat("dd-MM-yyyy hh-mm-ss-ms").format(new Date())
					+ ".html";
	private static String macPath = System.getProperty("user.dir") + "/TestReport";
	private static String linuxPath = System.getProperty("user.dir") + "/TestReport";
	private static String windowsPath = System.getProperty("user.dir") + "\\TestReport";
	private static String macReportFileLoc = macPath + "/" + reportFileName;
	private static String linuxReportFileLoc = linuxPath + "/" + reportFileName;
	private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

	public static String filePathAndName;

	public static ExtentReports getInstance()
	{
		if (extent == null)
		{
			createInstance();
		}
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance()
	{
		platform = getCurrentPlatform();
		filePathAndName = getReportFileLocation(platform);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePathAndName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("TEMP E2E API Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config()
				.setReportName("TEMP E2E API Execution Report on " + Constants.ENVIRONMENT_NAME + " Environment");
		htmlReporter.config().setTimelineEnabled(true);

		extent = new ExtentReports();
		extent.setSystemInfo("OS", getCurrentPlatform().toString());

		if (Constants.RUN_ON_REMOTE_SERVER_FLAG.equalsIgnoreCase("TRUE"))
		{
			ExtentKlovReporter klov = new ExtentKlovReporter("TEMP E2E API");
			try
			{
				klov.loadInitializationParams(
						new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/klov.properties"));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			// For Testing on Server
			extent.attachReporter(htmlReporter, klov);
		}
		else
		{
			// For Testing Locally
			extent.attachReporter(htmlReporter);
		}

		return extent;
	}

	// Select the extent report file location based on platform
	private static String getReportFileLocation(String platform)
	{
		String reportFileLocation = null;
		switch (platform)
		{
			case "MAC":
				reportFileLocation = macReportFileLoc;
				createDirectoryPath(macPath);
				log.info("ExtentReport Path for MAC: " + macPath + "\n");
				break;
			case "WINDOWS":
				reportFileLocation = winReportFileLoc;
				createDirectoryPath(windowsPath);
				log.info("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
				break;
			case "LINUX":
				reportFileLocation = linuxReportFileLoc;
				createDirectoryPath(linuxPath);
				log.info("ExtentReport Path for LINUX: " + linuxPath + "\n");
				break;
			default:
				log.info("ExtentReport path has not been set! There is a problem!\n");
				break;
		}
		return reportFileLocation;
	}

	// Create the report path if it does not exist
	private static void createDirectoryPath(String path)
	{
		File testDirectory = new File(path);
		if (!testDirectory.exists())
		{
			if (testDirectory.mkdir())
			{
				log.info("Directory: " + path + " is created!");
			}
			else
			{
				log.info("Failed to create directory: " + path);
			}
		}
		else
		{
			log.info("Directory already exists: " + path);
		}
	}

	// Get current platform
	public static String getCurrentPlatform()
	{
		if (platform == null)
		{
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win"))
			{
				platform = "WINDOWS";
			}
			else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix"))
			{
				platform = "LINUX";
			}
			else if (operSys.contains("mac"))
			{
				platform = "MAC";
			}
		}
		return platform;
	}

}
