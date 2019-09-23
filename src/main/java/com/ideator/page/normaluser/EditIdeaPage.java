package com.ideator.page.normaluser;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;
 public class EditIdeaPage extends IdeaPageWebElement{
		WebDriver driver;
		private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
		
		@FindBy(xpath = ".//*[@id='skills-and-users']")
		WebElement searchBox;
	
		PageClass pageClass;
	
		@FindBy(xpath = "//*[contains(@class, 'ng-binding link-no-answer')]")
		WebElement editIdeaName;
	
		@FindBy(xpath = ".//*[@class='l-actions']/input")
		WebElement saveButton;
	
		@FindBy(xpath = "//*[contains(@class, 'button button-secondary button-small')]")
		WebElement edit;
	
		@FindBy(xpath = ".//*[@id='idea_questions_solution']")
		WebElement theSolution;
		
		@FindBy(xpath="//div[3]/div/simple-format/p/span/span")
	     WebElement elevatorPitchOnDetail;
	
		public EditIdeaPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void editIdea(String URL) throws InterruptedException {
			pageClass.driver.get(URL);
			editIdeaName.click();
			ideaName.clear();
			ideaName.sendKeys("Edit name done");
			elevatorPitch.clear();
			elevatorPitch.sendKeys("Edit elevator pitch");
			theProblem.sendKeys("Problem Test");
			theSolution.sendKeys("Solution Test");
			JavascriptExecutor jse = (JavascriptExecutor)pageClass.driver;
			jse.executeScript("window.scrollBy(0,-10000)", "");
			saveButton.click();
			Thread.sleep(2000);
			Log.info("Idea is Edited and Saved ");
		}
	
		public String editIdeaNameValidation() {
			String EditIdeaName = editIdeaName.getText();
			return EditIdeaName;
		}
		public String editElevatorPitchValidation() {
			String EditElevatorPitch = elevatorPitchOnDetail.getText();
	     	 return EditElevatorPitch;		}
	
	}