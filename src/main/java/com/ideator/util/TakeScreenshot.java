package com.ideator.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class TakeScreenshot {
	private static final String PATH="/src/test/resources/Screenshot/";
//	private static final String SCREENSHOTPATH=" D:/TestNG Sample/Ideator_Auto/src/test/resources/Screenshot/";
	
	public static String onTestFailure(WebDriver driver,ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
//		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String screenshot_path="."+PATH+screenShotName;
		String dir = System.getProperty("user.dir");
		String path = dir.replace('\\', '/');
		String image_path=path+PATH+screenShotName;
		if (driver != null) {
		CaptureScreenShot(driver,screenshot_path);
		}
		return image_path;
	}
	
	
	public static void CaptureScreenShot(WebDriver driver, String screenshot_path) {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(screenshot_path));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception While taking Screenshot"+e.getMessage());
		}
	}

	public static String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

}


