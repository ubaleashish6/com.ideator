	package com.ideator.page.normaluser;
	
	import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;
	import com.ideator.util.PageObject;
	
	public class DiscussionPage {
		PageClass pageClass;
		
		private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
	
		@FindBy(xpath = ".//input[@id='discussion_name']")
		WebElement discussion_name;
	
		@FindBy(xpath = ".//textarea[@id='discussion_description']")
		WebElement discussion_description;
	
		@FindBy(xpath = "//*[@type='submit']")
		WebElement postButton;
	
		@FindBy(xpath = "//comments-feed/div/div[2]/div[2]/textarea")
		WebElement commentArea;
	
		@FindBy(css = ".comment-icon.icon-link.comment-icon-discussion.ng-binding")
		WebElement commentIcon;
	
		@FindBy(xpath = "//comments-feed/div/div[2]/div[2]/div/div")
		WebElement commentPostButton;
	
		private static final String profilePic = "//*[text()='%s']//preceding::div[1]";
		
		
	
		public DiscussionPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void createDiscussion(PageObject pageObject) throws InterruptedException {
			Thread.sleep(4000);
			pageClass.driver.get(pageObject.getConfigProperties().getField("discussion_url"));
			String discussion_Name = pageObject.getConfigProperties().getField("discussion_name");
			discussion_name.sendKeys(discussion_Name);
			discussion_name.click();
			discussion_description.sendKeys(pageObject.getConfigProperties().getField("discussion_description"));
			postButton.click();
			Thread.sleep(3000);
			Log.info(discussion_Name + " Discussion is Created");
	
		}
	
		public boolean isDiscussionCreated(PageObject pageObject) {
			List<WebElement> elements = pageClass.driver.findElements(By.xpath("//div/div/section/div"));
			for (WebElement element : elements) {
				if (element.getText().contains(pageObject.getConfigProperties().getField("discussion_name"))) {
					return true;
				}
			}
			return false;
		}
	
		public void commentDiscussion(PageObject pageObject) throws InterruptedException {
			pageClass.driver.get(pageObject.getConfigProperties().getField("discussion_url"));
			commentIcon.click();
			commentArea.sendKeys(pageObject.getConfigProperties().getField("discussion_comment"));
			commentPostButton.click();
			Thread.sleep(3000);
			Log.info("Comment is added on Discussion");
		}
	
		public boolean isCommentAdded(PageObject pageObject) {
			List<WebElement> elements = pageClass.driver.findElements(By.xpath("//comments-feed/div/div[1]"));
			for (WebElement element : elements) {
				if (element.getText().contains(pageObject.getConfigProperties().getField("discussion_comment"))) {
					return true;
				}
			}
			return false;
		}
	
		public void deleteDiscussion(PageObject pageObject) throws InterruptedException {
			pageClass.driver.get(pageObject.getConfigProperties().getField("discussion_url"));
			commentIcon.click();
			pageClass.driver.findElement(By.xpath("//section/div[2]/div/div[3]/div")).click();
			Log.info("Discussion is deleted");
			Thread.sleep(3000);
		}

		public boolean isDiscussionDeleted(PageObject pageObject) {
			List<WebElement> elements = pageClass.driver.findElements(By.xpath("//div/div/section/div"));
			for (WebElement element : elements) {
				if (element.getText().contains(pageObject.getConfigProperties().getField("discussion_name"))) {
					return true;
				}
			}
			return false;
		}

		public void clickOnProfilepic(PageObject pageObject,String userName) {
			pageClass.driver.get(pageObject.getConfigProperties().getField("discussion_url"));
			pageClass.driver.findElement(By.xpath(String.format(profilePic, userName))).click();
			Log.info("Click on Profile pic");
		}

		public boolean isProfilePage() {
			String url = pageClass.driver.getCurrentUrl();
			if(url.contains("/public")){
				return true;
			}
			return false;
			
		}
	
	}
