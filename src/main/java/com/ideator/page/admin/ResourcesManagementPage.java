package com.ideator.page.admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.util.PageClass;
import com.ideator.util.PageObject;

public class ResourcesManagementPage {
		PageClass pageClass;
	
		@FindBy(xpath = "//*[text()='New Resource']")
		WebElement newResourcesButton;
	
		@FindBy(xpath = ".//input[@id='ideator_resource_name']")
		WebElement resourcename;
	
		@FindBy(xpath = ".//input[@id='ideator_resource_tagline']")
		WebElement resourcetagLine;
	
		@FindBy(xpath = ".//textarea[@id='ideator_resource_intro']")
		WebElement resourceIntro_Text;
	
		@FindBy(xpath = ".//textarea[@id='ideator_resource_description']")
		WebElement resource_description;
	
		@FindBy(xpath = ".//*[@id='ideator_resource_url']")
		WebElement resource_url;
	
		@FindBy(xpath = "//*[text()='Active']")
		WebElement clickActiveCheckBox;
	
		@FindBy(xpath = "//tags-input/div/div/input")
		WebElement resourcesTag;
	
		@FindBy(css = ".l-actions>input")
		WebElement submitButton;
	
		@FindBy(xpath = "//*[text()='View Resource']")
		WebElement viewResource;
	
		@FindBy(xpath = "//div[2]/div/h2")
		WebElement resourceNameArea;
	
		@FindBy(xpath = "//*[text()='Manage Resource Categories']")
		WebElement resourceCategoryLink;
	
		@FindBy(xpath = "//*[text()='New Category']")
		WebElement newCategoryButton;
	
		@FindBy(xpath = ".//input[@id='resource_category_name']")
		WebElement resourceCategoryName;
	
		@FindBy(xpath = "//section[1]/div/a[6]")
		WebElement resourceTab;
		private static Logger Log = Logger.getLogger(EnvironmentSettingPage.class.getName());

	
		public ResourcesManagementPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void createResources(PageObject pageObject) throws InterruptedException {
			resourceTab.click();
			newResourcesButton.click();
			String resourceName = pageObject.getConfigProperties().getField("Resource_Name");
			resourcename.sendKeys(resourceName);
			resourcetagLine.sendKeys(pageObject.getConfigProperties().getField("Resource_tagLine"));
			resourceIntro_Text.sendKeys(pageObject.getConfigProperties().getField("ResourceIntro_Text"));
			resource_description.sendKeys(pageObject.getConfigProperties().getField("Resource_description"));
			resource_url.sendKeys(pageObject.getConfigProperties().getField("Resource_url"));
			clickActiveCheckBox.click();
			JavascriptExecutor js = (JavascriptExecutor) pageClass.driver;
			WebElement element = pageClass.driver.findElement(By.cssSelector(".tags"));
			js.executeScript("arguments[0].setAttribute('style', 'display:block')", element);
			resourcesTag.sendKeys("crm");
			Thread.sleep(2000);
			resourcesTag.sendKeys(Keys.ARROW_DOWN);
			resourcesTag.sendKeys(Keys.ENTER);
			submitButton.click();
			Log.info(resourceName+ " is created and saved.");
		}
	
		public String createdResourceName(PageObject pageObject) {
			String parentHandle = pageClass.driver.getWindowHandle();// Current window
			viewResource.click();
			for (String winHandle : pageClass.driver.getWindowHandles()) {
				pageClass.driver.switchTo().window(winHandle); 
			}
			String resourcename = resourceNameArea.getText();
			pageClass.driver.close(); // close newly opened window when done with it
			pageClass.driver.switchTo().window(parentHandle); 
			return resourcename;
	
		}
	
		public boolean isSuccessMessageDisplay() {
			By successMessage = By.xpath("//*[contains(@class,'flash--success ng-binding')]");
			boolean isSuccessmessage = pageClass.isElementExist(successMessage, pageClass);
			return isSuccessmessage;
		}
	
		public void createResourcesCategory(PageObject pageObject) {
			resourceTab.click();
			resourceCategoryLink.click();
			newCategoryButton.click();
			resourceCategoryName.sendKeys(pageObject.getConfigProperties().getField("Resource_Category_Name"));
			submitButton.click();
			Log.info("Resource category is created.");
		}
	
		public void editResource(PageObject pageObject) throws InterruptedException {
			resourcename.clear();
			resourcename.sendKeys(pageObject.getConfigProperties().getField("Resource_Name_Edited"));
			submitButton.click();
			Thread.sleep(3000);
			Log.info("Resource is edited.");
		}
	}
