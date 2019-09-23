package com.ideator.page.normaluser;

import org.openqa.selenium.support.PageFactory;

import com.ideator.util.PageClass;

public class PublicUserutilPage {
	PageClass pageClass;
	
	public PublicUserutilPage(PageClass pageClass) {
		this.pageClass = pageClass;
		PageFactory.initElements(pageClass.driver, this);
	}

	public String PrivateMessagePageURL() {
		return pageClass.driver.getCurrentUrl();
	}
}
