package com.ideator.page;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.common.WorkBookRead;
import com.ideator.util.PageClass;
import com.ideator.util.PageObject;

public class LoginPage extends PageClass {

		WebDriver driver;
	
		@FindBy(id = "user-email")
		WebElement userEmail;
	
		@FindBy(id = "user-password")
		WebElement userPass;
	
		@FindBy(xpath = ".//input[@type='submit']")
		WebElement submitButton;
	
		@FindBy(xpath = "//a[contains(text(),'Login')]")
		WebElement loginButton;
	
		@FindBy(id = "user-first-name")
		WebElement userFirstName;
	
		@FindBy(id = "user-last-name")
		WebElement userLastName;
	
		@FindBy(css = "input[name=commit]")
		WebElement submitSignupButton;
		PageClass pageClass;
	
		@FindBy(css = ".user__image-small>img")
		WebElement avtar;
	
		private static Logger Log = Logger.getLogger(LoginPage.class.getName());
	
		public LoginPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void submitLoginCredential() {
			Log.info("Click on Login Button");
			WorkBookRead wrb = new WorkBookRead();
			String[] loginData;
			try {
				loginData = wrb.ReadsheetLogin();
				String userName = loginData[0];
				String passWord = loginData[1];
				boolean isLoginButtonPresent = pageClass.isElementExist(By.xpath("//a[contains(text(),'Login')]"),
						pageClass);
				if (isLoginButtonPresent) {
					loginButton.click();
				}
				userEmail.sendKeys(userName);
				userPass.sendKeys(passWord);
				submitButton.click();
				Log.info("Enter Username and Password and click on Submit Button");
	
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	
		
		public void ideatorsubmitLoginCredential() {
			Log.info("Click on Login Button");
			WorkBookRead wrb = new WorkBookRead();
			String[] loginData;
			try {
				loginData = wrb.ReadsheetLogin();
				String userName = loginData[0];
				String passWord = loginData[1];
				userEmail.sendKeys(userName);
				userPass.sendKeys(passWord);
				submitButton.click();
				Log.info("Enter Username and Password and click on Submit Button");
	
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		public void enterUserCrendential(String userName, String passWord, PageObject pageObject) {
			String username = pageObject.getConfigProperties().getUserName(userName);
			String password = pageObject.getConfigProperties().getPassword(passWord);
			boolean isLoginButtonPresent = pageClass.isElementExist(By.xpath("//a[contains(text(),'Login')]"), pageClass);
			if (isLoginButtonPresent) {
				loginButton.click();
			}
			userEmail.sendKeys(username);
			userPass.sendKeys(password);
			submitButton.click();
			Log.info("Enter Username and Password and click on Submit Button");
		}
	
		public void clickAvatarTab(String tabName) {
			Log.info("Click on clickSuperAdmin Tab");
			avtar.click();
			List<WebElement> elementList = driver.findElements(By.cssSelector(".user-nav__link"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals(tabName)) {
					element.click();
					break;
				}
			}
		}
	
		public void submitSignUpCredential(String signupUserEmail) throws InterruptedException {
			setText(userFirstName, "First1");
			// userFirstName.clear();
			// userFirstName.sendKeys("First");
			userLastName.clear();
			userLastName.sendKeys("Last1");
			userEmail.sendKeys(signupUserEmail);
			userPass.sendKeys("cybage@123");
			submitSignupButton.click();
			Thread.sleep(5000);
		}
		//
	}
