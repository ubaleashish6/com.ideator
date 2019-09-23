package com.ideator.page.superadmin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.WorkBookRead;
import com.ideator.util.PageClass;

public class SuperAdminLoginPage {

		WebDriver driver;
	
		@FindBy(id = "user-email")
		WebElement userEmail;
	
		@FindBy(xpath = ".//*[@id='user-email']")
		WebElement username;
	
		@FindBy(xpath = ".//*[@id='user-password']")
		WebElement password;
	
		@FindBy(xpath = ".//*[@name='commit']")
		WebElement loginButton;
	
		@FindBy(xpath = ".//*[@class='l-container change-community-wrapper']")
		WebElement communitySwitcher;
	
		@FindBy(xpath = ".//*[@class='placeholder']")
		WebElement ideatorCommunity;
		PageClass pageClass;
	
		private static Logger Log = Logger.getLogger(SuperAdminLoginPage.class.getName());
	
		public SuperAdminLoginPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void submitLoginCredential() {
			WorkBookRead wrb = new WorkBookRead();
			String[] loginData;
			try {
				loginData = wrb.ReadsheetLogin();
				String userName = loginData[0];
				String passWord = loginData[1];
				username.sendKeys(userName);
				password.sendKeys(passWord);
				loginButton.click();
				Log.info("Enter Username and Password and click on Submit Button");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	
		public boolean isCommunitySwitcherExist() {
			return communitySwitcher.isDisplayed();
		}
	
		public void clickOnCommunity() {
			ideatorCommunity.click();
			Log.info("Click On Ideator Community");
		}
	
		public String getAcceptanceUrl() {
			return pageClass.driver.getCurrentUrl();
		}
	}
