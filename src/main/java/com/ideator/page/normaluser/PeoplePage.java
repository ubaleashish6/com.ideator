package com.ideator.page.normaluser;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.util.PageClass;
import com.ideator.util.PageObject;
import com.relevantcodes.extentreports.model.Log;

public class PeoplePage {
	WebDriver driver;
	PageClass pageClass;
	
	
	@FindBy(xpath = "//*[contains(@class, 'main-nav small_screen-hidden')]/a[3]")
	WebElement people;
	
	@FindBy(xpath =".//*[@id='skills-and-users']")
	WebElement search;
	
	
	
	@FindBy(xpath = "//div[1]/form/div[2]/button")
	WebElement findUser;
	
	
	

	public PeoplePage(PageClass pageClass) {
		this.pageClass = pageClass;
		PageFactory.initElements(pageClass.driver, this);
	}


	
	public void searchPeoplePage(PageObject pageObject, String string) {
		people.click();
		String value = pageObject.getConfigProperties().getField(string);
		pageClass.setText(search, value);
		findUser.click();
	}
	
	
	
	public boolean searchPeoplePageResult(String searchValue) {
		java.util.List<WebElement> users = pageClass.driver.findElements(By.xpath("//main/section[2]/div/div[2]"));
		for (WebElement i : users) {
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