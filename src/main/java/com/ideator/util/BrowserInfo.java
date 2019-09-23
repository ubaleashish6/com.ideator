package com.ideator.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.ideator.common.ConfigProperties;

public class BrowserInfo {
	static WebDriver driver;
//	private static final String PATH = "/src/test/resources/Firefox Extension/";
	
	private final static String BASE_PATH = ConfigProperties.getFilePath("firefox_ext_path");
	private final static String PATH =ConfigProperties.BASE_TESTFOLDER+BASE_PATH;
	

	public static WebDriver getBrowser() throws IOException {
		PageClass pageClass = new PageClass();
		String FolderName = pageClass.getSystemPath() + PATH ;
		
		
		String firebugPath = FolderName+"firebug-2.0.8.xpi";
		String firepathPath = FolderName+"firepath-0.9.7-fx.xpi";
		
		//Create a new Profile for the new settings
		FirefoxProfile profile = new FirefoxProfile();
		// Pass the XPIs path to the profile
		profile.addExtension(new File(firebugPath));
		profile.addExtension(new File(firepathPath));
		
		//Set default Firebug preferences and FirePath preferences
		String ext = "extensions.firebug.";
		String ext1 = "extensions.firepath.";
		
		profile.setPreference(ext + "currentVersion", "3.0.0");
		profile.setPreference(ext1 + "currentVersion", "0.9.7");
//		profile.setPreference(ext + "allPagesActivation", "on");
//		profile.setPreference(ext + "defaultPanelName", "net");
//		profile.setPreference(ext + "net.enableSites", true);
		FirefoxOptions option=new FirefoxOptions();
		option.setProfile(profile);
		driver = new FirefoxDriver(option);
		System.out.println("firefox is open");
		driver.manage().window().maximize();
		return driver;
	}

}
