package com.ideator.page.normaluser;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;
import com.ideator.util.PageObject;

public class EditProfilePage {

		PageClass pageClass;
		private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
	
		@FindBy(xpath = "//*[text()='Edit']")
		WebElement editButton;
	
		@FindBy(xpath = ".//input[@id='user_first_name']")
		WebElement user_first_name;
	
		@FindBy(xpath = ".//input[@id='user_last_name']")
		WebElement user_last_name;
	
		@FindBy(xpath = ".//input[@id='location']")
		WebElement location;
	
		@FindBy(xpath = ".//*[contains(@name,'user[links][linkedin]')]")
		WebElement linkedin;
	
		@FindBy(xpath = ".//*[contains(@name,'user[links][facebook]')]")
		WebElement facebook;
	
		@FindBy(xpath = ".//*[contains(@name,'user[links][website]')]")
		WebElement website;
	
		@FindBy(xpath = "//*[text()='I invest in companies']")
		WebElement invester;
	
		@FindBy(xpath = ".//textarea[@id='user_bio']")
		WebElement user_bio;
	
		@FindBy(xpath = ".//textarea[@id='user_accomplishments']")
		WebElement user_accomplishments;
	
		@FindBy(css = ".button.button-secondary.ng-scope")
		WebElement view_MyProfileButton;
	
		@FindBy(xpath = "//section/div/section[1]/div/h3")
		WebElement nameArea;
	
		@FindBy(xpath = "//section/div/section[1]/div/span/p")
		WebElement bioArea;
	
		@FindBy(xpath = "//div/section[1]/div/p[2]")
		WebElement socialMedialinkArea;
	
		@FindBy(xpath = "//section[1]/div/div[1]/input")
		WebElement updateButton;
	
		public EditProfilePage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void editprofile(PageObject pageObject) {
			editButton.click();
			String name = pageObject.getConfigProperties().getField("first_name");
			pageClass.setText(user_first_name, name);
			pageClass.setText(user_last_name, pageObject.getConfigProperties().getField("last_name"));
			pageClass.setText(linkedin, pageObject.getConfigProperties().getField("linkedin"));
			pageClass.setText(facebook, pageObject.getConfigProperties().getField("facebook"));
			pageClass.setText(website, pageObject.getConfigProperties().getField("website"));
			invester.click();
			pageClass.setText(user_bio, pageObject.getConfigProperties().getField("bio"));
			pageClass.setText(user_accomplishments, pageObject.getConfigProperties().getField("accomplishments"));
			  JavascriptExecutor jse = (JavascriptExecutor)pageClass.driver;
				jse.executeScript("window.scrollBy(0,-10000)", "");
			updateButton.click();
			Log.info("User named"+name+" profile is Edited");
		}
	
		public boolean isSuccessMessageDisplay() {
			By successMessage = By.xpath("//*[text()='Your profile was successfully updated.']");
			boolean isSuccessmessage = pageClass.isElementExist(successMessage, pageClass);
			return isSuccessmessage;
		}
	
		public boolean isNameEdited(PageObject pageObject) {
			view_MyProfileButton.click();
			String output = nameArea.getText();
			String firstname = pageObject.getConfigProperties().getField("first_name");
			String lastname = pageObject.getConfigProperties().getField("last_name");
			String name = firstname + " " + lastname;
			if (output.equals(name)) {
				return true;
			}
			return false;
		}
	
		public boolean isBioAreaEdited(PageObject pageObject) {
			String output = bioArea.getText();
			String bioName = pageObject.getConfigProperties().getField("bio");
			if (output.equals(bioName)) {
				return true;
			}
			return false;
		}
	
		public boolean islinkUpdated(PageObject pageObject) {
			if (isLinkedInEdited(pageObject) && isFacebookEdited(pageObject) && isWebsiteEdited(pageObject)) {
				return true;
			}
			return false;
		}
	
		public boolean isLinkedInEdited(PageObject pageObject) {
			String socialMediaLink = pageObject.getConfigProperties().getField("linkedin");
			String link = pageClass.driver.findElement(By.xpath("//div/section[1]/div/p[2]/a[3]")).getText();
			if (link.equals(socialMediaLink)) {
				return true;
			}
			return false;
		}
	
		public boolean isFacebookEdited(PageObject pageObject) {
			String socialMediaLink = pageObject.getConfigProperties().getField("facebook");
			String link = pageClass.driver.findElement(By.xpath("//div/section[1]/div/p[2]/a[2]")).getText();
			if (link.equals(socialMediaLink)) {
				return true;
			}
			return false;
		}
	
		public boolean isWebsiteEdited(PageObject pageObject) {
			String socialMediaLink = pageObject.getConfigProperties().getField("website");
			String link = pageClass.driver.findElement(By.xpath("//div/section[1]/div/p[2]/a[1]")).getText();
			if (link.equals(socialMediaLink)) {
				return true;
			}
			return false;
	
		}
	
		public boolean isProfileUpdated(PageObject pageObject) {
			if (isNameEdited(pageObject) && isBioAreaEdited(pageObject) && islinkUpdated(pageObject)) {
				return true;
			}
			return false;
		}
	}
