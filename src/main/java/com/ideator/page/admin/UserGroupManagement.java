package com.ideator.page.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ideator.util.PageClass;

public class UserGroupManagement {
		private static final String EDITGROUPLINK = "//*[text()='%s']//following::a[1]";
		private static final String DELETEGROUPLINK = "//*[text()='%s']//following::a[2]";
		private static final String USERMAIL = "//*[text()='%s']//div[1]//a";
		private static final String MANAGEGROUPLINK = "//*[text()='%s']//li[2]//a";
		private static final String GROUPNAME = "//label[text()='%S']//preceding::div[1]";
		private static final String GROUP = "//*[text()='%s']";
		private static final String CONFORMBUTTON = "//label[text()='%S']//following::button";
		
	
		@FindBy(css = ".user__image-small")
		WebElement avtar;
	
		@FindBy(xpath = "//*[text()='New Group']")
		WebElement newGroup;
	
		@FindBy(xpath = ".//*[@id='name']")
		WebElement groupNameField;
	
		@FindBy(xpath = "//*[@type='submit']")
		WebElement saveButton;
	
		@FindBy(xpath = ".//*[contains(@name,'moveUserForm')]")
		WebElement managegroupList;
	
		@FindBy(xpath = "//*[@type='submit']")
		WebElement conformButton;
	
		@FindBy(xpath = "//select")
		WebElement allUsersDropdown;
	
		@FindBy(xpath = "//*[@type='submit']")
		WebElement deleteButton;
	
		@FindBy(xpath = "//*[text()='Community Admin']")
		WebElement communityAdmin;
	
		@FindBy(xpath = "//*[text()='Submit']")
		WebElement submitButton;
	
		@FindBy(xpath = "//*[text()='Close']")
		WebElement closeButton;
	
		@FindBy(xpath = ".//*[@id='ngdialog1']//div[1]/img")
		WebElement CheckBox;
	
		@FindBy(xpath = "//img[starts-with(@src, '/assets/checkmark-green')]/@src")
		WebElement clickONAdminRoleCheckBox;
	
		@FindBy(xpath = "//img[starts-with(@src, '/assets/checkmark-white')]/@src")
		WebElement clickOFFAdminRoleCheckBox;
	
		PageClass pageClass;
		private static Logger Log = Logger.getLogger(EnvironmentSettingPage.class.getName());
	
		public UserGroupManagement(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void clickAvatarTab(String tabName) {
			Log.info("Click on " + tabName + " Tab");
			JavascriptExecutor jse = (JavascriptExecutor)pageClass.driver;
			jse.executeScript("window.scrollBy(0,-10000)", "");
			avtar.click();
			List<WebElement> elementList = pageClass.driver.findElements(By.cssSelector(".user-nav__link"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals(tabName)) {
					element.click();
					break;
				}
			}
		}
	
		public void makeAdminRole() throws InterruptedException {
			Log.info("MakeAdminRole method is start");
			Actions action = new Actions(pageClass.driver);
			WebElement we = pageClass.driver.findElement(By.xpath("//*[text()='light1@mailinator.com']//div[1]//a"));
			action.moveToElement(we).click().build().perform();
			pageClass.driver.findElement(By.xpath("//*[text()='light1@mailinator.com']//li[1]//a")).click();
			Thread.sleep(3000);
			if (CheckBox.getAttribute("src").contains("white")) {
				communityAdmin.click();
			}
			Log.info("Click on Communtity admin checkbox for make user admin role ");
			submitButton.click();
			closeButton.click();
			Thread.sleep(3000);
			clickAvatarTab("Sign Out");
			Log.info("Signout from system");
		}
	
		public void removeAdminRole() throws InterruptedException {
			Actions action = new Actions(pageClass.driver);
			WebElement we = pageClass.driver.findElement(By.xpath("//*[text()='light1@mailinator.com']//div[1]//a"));
			action.moveToElement(we).click().build().perform();
			pageClass.driver.findElement(By.xpath("//*[text()='light1@mailinator.com']//li[1]//a")).click();
			Thread.sleep(3000);
			if (CheckBox.getAttribute("src").contains("green")) {
				communityAdmin.click();
			}
			Log.info("Click on Communtity admin checkbox for remove user admin role admin");
			Thread.sleep(3000);
			submitButton.click();
			closeButton.click();
			Thread.sleep(3000);
			clickAvatarTab("Sign Out");
			Log.info("Signout from system");
		}
	
		public boolean isAdminTabPresent() {
			Log.info("Click on Admin Tab");
			avtar.click();
			List<WebElement> elementList = pageClass.driver.findElements(By.cssSelector(".user-nav__link"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals("Admin")) {
					element.click();
					clickAvatarTab("Sign Out");
					return true;
				}
			}
			clickAvatarTab("Sign Out");
			return false;
		}
	
		public void creategroup(String groupName) {
			newGroup.click();
			groupNameField.sendKeys(groupName);
			saveButton.click();
			Log.info(groupName+" is created and saved");
		}
	
		public boolean isGroupPresent(String groupName) {
			By group = By.xpath(String.format(GROUP, groupName));
			boolean result = pageClass.isElementExist(group, pageClass);
			return result;
		}
	
		public void editgroup(String groupName, String updatedGroupName) {
			pageClass.driver.findElement(By.xpath(String.format(EDITGROUPLINK, groupName))).click();
			pageClass.setText(groupNameField, updatedGroupName);
			saveButton.click();
			Log.info(groupName+" is updated by this "+updatedGroupName+" group");
		}
	
		public void addUserToGroup(String userNameToAdd, String groupName) {
			Actions action = new Actions(pageClass.driver);
			WebElement we = pageClass.driver.findElement(By.xpath(String.format(USERMAIL, userNameToAdd)));
			action.moveToElement(we).click().build().perform();
			pageClass.driver.findElement(By.xpath(String.format(MANAGEGROUPLINK, userNameToAdd))).click();
			pageClass.driver.findElement(By.xpath(String.format(GROUPNAME, groupName))).click();
			pageClass.driver.findElement(By.xpath(String.format(CONFORMBUTTON, groupName))).click();
			Log.info(userNameToAdd+" is added in "+groupName+" group");
		}
	
		public boolean isUserAddedToGroup(String userNameToAdd, String groupName) throws InterruptedException {
			allUsersDropdown.click();
			Select mySelectElm = new Select(pageClass.driver.findElement(By.xpath("//select")));
			mySelectElm.selectByVisibleText(groupName);
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.RETURN);
			Thread.sleep(2000);
			By user = By.xpath(String.format(USERMAIL, userNameToAdd));
			boolean isUserAdded = pageClass.isElementExist(user, pageClass);
			return isUserAdded;
		}
	
		public void deleteGroup(String updatedGroupName) {
			pageClass.driver.findElement(By.xpath(String.format(DELETEGROUPLINK, updatedGroupName))).click();
			deleteButton.click();
			Log.info(updatedGroupName+" group is deleted.");
		}
	
		public boolean isGroupDeleted(String updatedGroupName) {
			boolean result = pageClass.isElementExist(By.linkText(updatedGroupName), pageClass);
			return result;
		}
	
	}
