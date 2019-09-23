package com.ideator.page.normaluser;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;

public class IdeaOverview {
	WebDriver driver;
	PageClass pageClass;
	
	private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
	@FindBy(xpath = "//div/socializers-button/div/div")
	WebElement likeButton;
	
	@FindBy(id ="skills-and-users")
	WebElement search;
	
	@FindBy(xpath = "//section/div[1]/h3/a")
	WebElement likes;
	
	@FindBy(xpath = "//div/section[1]/div/a[3]")
	WebElement myLikesTab;

	@FindBy(xpath = "//*[contains(@class, 'main-nav small_screen-hidden')]/a[1]")
	WebElement ideasPage;
	
	@FindBy(xpath = "//div[1]/form/div[2]/button")
	WebElement findIdeas;
	
	@FindBy(xpath = "//form/div[3]/span/select")
	WebElement sort;
	

	public IdeaOverview(PageClass pageClass) {
		this.pageClass = pageClass;
		PageFactory.initElements(pageClass.driver, this);
	}

	public void likeIdea(String URL) {
		pageClass.driver.get(URL);
		String selectedvalue = likeButton.getAttribute("class");
		if (selectedvalue.equals("heart-icon ng-binding")) {
			likeButton.click();
		}
		Log.info("User like Idea");
	}
	
	public String likeValidation() {
		 String s1 = likes.getText();
	 return s1;
	}

	public void moveToIdeasPage() {
		ideasPage.click();
		Log.info("Navigate to Idea Page");
	}
	
	public void searchIdeasPage() {
		search.sendKeys("test");
		findIdeas.click();
		Log.info("keyword is type on search textbox and Click on Search Button");
	}
	
	public void sortIdeasPage() {
		ideasPage.click();
		sort.click();
		Select dropdown = new Select(sort);
		dropdown.selectByIndex(0);
	}
	public void likeTabPage() {
		myLikesTab.click();
		Log.info("Navigate to Liked idea Tab ");
	}

	public boolean searchIdeasPageResult(String searchValue) {
		java.util.List<WebElement> ideas = pageClass.driver.findElements(By.xpath("//main/div/section[2]/div[2]/div[2]"));
		for (WebElement i : ideas) {
			if (i.getText().contains(searchValue) ){
				return true;
			}
		}return false;
		
	}
	
	public boolean sortIdeasPageResult(String searchValue) {
		java.util.List<WebElement> ideas = pageClass.driver.findElements(By.xpath("//main/div/section[2]/div[2]/div[2]"));
		for (WebElement i : ideas) {
			if (i.getText().contains(searchValue) ){
				return true;
			}
		}return false;
	}		
}