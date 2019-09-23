package com.ideator.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;



public class ConfigProperties {

		static Properties properties;
		WebDriver driver;
		static String screenshotPath; 
//		public static final String BASE_TESTFOLDER="";
		public static final String BASE_TESTFOLDER="/src/test/resources/";
		public final static String BASEFOLDER = "./src/main/resources/";
//		public final static String BASEFOLDER = "";
		private final static String config_path = BASEFOLDER+ "config.properties";
		static MultiValueMap sortedMethods = new MultiValueMap();
	
		public ConfigProperties(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			try {
				File src = new File(config_path);
				FileInputStream fis = new FileInputStream(src);
				properties = new Properties();
				properties.load(fis);
			} catch (Exception e) {
				System.out.println("Exception is :" + e.getMessage());
			}
		}
	
		public static String getApplicationURL() {
			String url = properties.getProperty("acceptanceurl");
			return url;
		}
	
		public String getAcceptanceApplicationURL() {
			String url = properties.getProperty("acceptanceapplicationurl");
			return url;
		}
	
		private String getWindowAuth_Community() {
			String base_authencation = properties.getProperty("windowsAunthecationAcceptance");
			String authencation = BASEFOLDER+base_authencation;
			return authencation;
		}
	
		private String getWindowAuth_Acceptance() {
			String base_superAdminauth = properties.getProperty("windowsAunthecationAcceptanceSuperAdmin");
			String superAdminauth = BASEFOLDER+base_superAdminauth;
			return superAdminauth;
		}
	
		public void getAutoITCommunity() throws IOException {
			String[] param = new String[] { getWindowAuth_Community(), "Authentication Required", "ideator", "acceptance" };
			Runtime.getRuntime().exec(param);
		}
	
		public void getAutoITAcceptance() throws IOException {
			String[] param = new String[] { getWindowAuth_Acceptance(), "Authentication Required", "ideator",
					"acceptance" };
			Runtime.getRuntime().exec(param);
		}
	
		public String getCommunityURL(String community) {
			String url = properties.getProperty(community);
			return url;
		}
	
		public String getUserName(String userName) {
			String url = properties.getProperty(userName);
			return url;
		}
	
		public String getPassword(String password) {
			String url = properties.getProperty(password);
			return url;
		}
	
		public String getField(String fieldname) {
			String field = properties.getProperty(fieldname);
			return field;
		}
	
		//set sorted method alphabetically
		public static void setSortedMethods(String _className, String _methodName) {
			sortedMethods.put(_className, _methodName);
		}
	
		public static Collection getSortedMethods(String _className) {
			Set entrySet = sortedMethods.entrySet();
			Iterator it = entrySet.iterator();
			while (it.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) it.next();
				if (mapEntry.getKey().toString().equals(_className)) {
					return sortedMethods.getCollection(mapEntry.getKey().toString());
				}
			}
			return null;
		}

		public static  String getScreenshotPath()
		{
		    return screenshotPath;
		}
		public static  void setScreenshotPath(String path) {
			screenshotPath = path;
		}

		public static String getFilePath(String filename) {
			try {
				File src = new File(config_path);
				FileInputStream fis = new FileInputStream(src);
				properties = new Properties();
				properties.load(fis);
			} catch (Exception e) {
				System.out.println("Exception is :" + e.getMessage());
			}
			String file_path = properties.getProperty(filename);
			return file_path;
		}
	}
