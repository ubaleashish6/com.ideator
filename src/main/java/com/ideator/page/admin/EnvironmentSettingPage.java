package com.ideator.page.admin;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class EnvironmentSettingPage {

		WebDriver driver;
	
		@FindBy(css = ".user__image-small>img")
		WebElement avtar;
	
		public EnvironmentSettingPage() {
	
		}
	
		// private static Logger Log =
		// Logger.getLogger(EnvironmentSettingPage.class.getName());
	
		public EnvironmentSettingPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
	}
