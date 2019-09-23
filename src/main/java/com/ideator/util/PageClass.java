package com.ideator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ideator.common.ConfigProperties;
/*import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

public class PageClass {
	PageClass pageClass;
	protected PageObject pageObject = null ;
	FileInputStream fis = null;
	public WebDriver driver;
	private static final String BASE_PATH = ConfigProperties.getFilePath("screenshot_folder");
	private static final String PATH = ConfigProperties.BASE_TESTFOLDER+BASE_PATH;
	protected ExtentTest test;
	protected ExtentReports extent;
	private static Logger Log = Logger.getLogger(PageClass.class.getName());
	private String URL=null;
	private String screenshotFolderName= null;
	private static final String TABNAME = "//*[text()='%s']";
	private static final String COMMUNITYSETTINGELEMENT = ".//*[@id='%s']"; 
	
	public PageClass() {
	}

	public PageClass(WebDriver driver) {
		this.driver = driver;
	}

	public void initilize() throws IOException {
		driver=BrowserInfo.getBrowser();
//		driver=new FirefoxDriver();
		pageClass = new PageClass(driver);
		PropertyConfigurator.configure("Log4j.properties");
		pageObject = new PageObject(pageClass);
		
	}

	public void urlSetup() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageObject.getConfigProperties().getAutoITCommunity();
		URL = pageObject.getConfigProperties().getAcceptanceApplicationURL();
		driver.get(URL);
		driver.manage().window().maximize();
	}
	
	public void urlSetupACommunity() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageObject.getConfigProperties().getAutoITCommunity();
		URL =pageObject.getConfigProperties().getCommunityURL("CybageAcceptance"); 
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public String onTestFailure(WebDriver driver, ITestResult result) throws IOException {
		Log.info("***** Error :: " + result.getName() + " test has failed *****");
//		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String path = pageObject.getConfigProperties().getScreenshotPath();
//		createScreenshotFolder(testClassName);
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String screenshot_path = path +"/"+ screenShotName;
		if (driver != null) {
			CaptureScreenShot(driver, screenshot_path);
		}
		return screenshot_path;
	}

	private void CaptureScreenShot(WebDriver driver, String screenshot_path) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(screenshot_path));
			Log.info("Screenshot is taken");
		} catch (Exception e) {
			System.out.println("Exception While taking Screenshot" + e.getMessage());
		}
	}
	

	public void setCheckbox(WebElement we, boolean check) {
		if ((we.isSelected() && !check) || (!we.isSelected() && check)) {
			we.click();
		}
	}
	
	public void changeToggel(String element,PageClass pageClass) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) pageClass.driver;
		Thread.sleep(5000);
		  WebElement elementName = pageClass.driver.findElement(By.xpath(String.format(COMMUNITYSETTINGELEMENT, element)));
		  Thread.sleep(5000);
		  js.executeScript("arguments[0].setAttribute('style', 'display:block')", elementName);
		
	}

	private String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}
	
	public void deleteDirectory() throws IOException{
		String path = getSystemPath() + PATH;
		File dir = new File(path);
		File[] content = dir.listFiles();
		for (File filename : content) {
			FileUtils.deleteDirectory(filename);
			//			filename.delete();
		}
	}

	public String createScreenshotFolder(String className) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH_mm_ss");
		Date date = new Date();
		String dateTime = dateFormat.format(date);
		String currentTimeStamp = dateTime.replace("/", "");
		String FolderName = className.concat(currentTimeStamp);
		screenshotFolderName = getSystemPath() + PATH + FolderName;
		File file = new File(screenshotFolderName);
		file.mkdirs();
		return screenshotFolderName;
	}

	String getSystemPath() {
		String filePath = System.getProperty("user.dir").replace("\\", "/");
//		String filePath="";
		return filePath;
	}
	
	//SuperAdmin community login
	public void superAdminLoginCommunity() {
		  pageObject.getSuperAdminLoginPage().submitLoginCredential();
		  pageObject.getSuperAdminLoginPage().isCommunitySwitcherExist();
		  pageObject.getSuperAdminLoginPage().clickOnCommunity();
		 }
	
	//Click on any Tab on Avtar:send only Tabname to that Method

	public void teardown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			String screenshot_path = onTestFailure(driver, result);
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, image);
			Log.info("Testcase : "+result.getName()+" is failed");
			pageObject.getWorkBookRead().writeSheetTDD(result.getStatus(), result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
			Log.info(result.getName()+"is Skipped");
			pageObject.getWorkBookRead().writeSheetTDD(result.getStatus(), result.getName());
		} else {
			test.log(LogStatus.PASS, "Test passed");
			Log.info(result.getName()+" method is Passed");
			pageObject.getWorkBookRead().writeSheetTDD(result.getStatus(), result.getName());
		}
		extent.endTest(test);
		driver.get(URL+"/ideas");
	}
	
	public static void startTestCase(String TestCaseName) {
		Log.info("$$$$$$$$$      " + TestCaseName + "     $$$$$$$$$$");
	}
	
	public static void endTestCase() {
		Log.info("XXXXXXXX       " + "-E---N---D-" + "     XXXXXXXXXX");
	}


	public void beforeSuite() throws IOException, InterruptedException {
		if (extent == null) {
			String base_reportPath = ConfigProperties.getFilePath("report_path");
			String reportPath =ConfigProperties.BASE_TESTFOLDER+base_reportPath;
			extent = new ExtentReports(reportPath+"Report.html", true);
			extent.addSystemInfo("Selenium Version", "2.48.0").addSystemInfo("Environment", "Acceptance");
		}
//		createScreenshotFolder(className);
	}

	public void afterSuite() {
		extent.flush();
	}

	public void afterClass() {
		pageClass.driver.close();
	}
	
	public boolean isElementExist(By locator) {
		try {
			pageClass.driver.findElement(locator);
		} catch (NoSuchElementException ex) {
			return false;
		}
		return true;
	}

	public boolean isElementExist(By locator,PageClass pageClass) {
		try {
			pageClass.driver.findElement(locator);
		} catch (NoSuchElementException ex) {
			return false;
		}
		return true;
	}

	public void clickOnTab(String tabName) {
		pageClass.driver.findElement(By.xpath(String.format(TABNAME, tabName))).click();
	}
	public void setText(WebElement we, String txt,PageClass pageClass) {
		we.clear();
		we.sendKeys(txt);
	}
	public void javascriptExecutor(WebElement elementName, PageClass pageClass) {
		JavascriptExecutor js = (JavascriptExecutor) pageClass.driver;
		js.executeScript("arguments[0].setAttribute('style', 'display:block')",
				elementName);

	}

	public void setCheckbox(WebElement we, boolean check, PageClass pageClass) {
		if ((we.isSelected() && !check) || (!we.isSelected() && check)) {
			we.click();
		}
	}
		
	public void setText(WebElement we, String txt) {
		we.clear();
		we.sendKeys(txt);
	}

	public void clickAvatarTab(String tabName) {
		Log.info("Click on "+tabName+" Tab");
		pageClass.driver.findElement(By.cssSelector(".user__image-small")).click();
		List<WebElement> elementList = pageClass.driver.findElements(By.cssSelector(".user-nav__link"));
		for (WebElement element : elementList) {
			if ((element.getText()).equals(tabName)) {
				element.click();
				break;
			}
		}
	}
}
