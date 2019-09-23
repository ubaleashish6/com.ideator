package com.ideator.page.normaluser;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;

public class PublicIdeaPage extends IdeaPageWebElement {
	private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
	
    @FindBy(xpath=".//*[@id='skills-and-users']")
    WebElement searchBox;
    
    @FindBy(xpath=".//*[@id='public_problem_switch']")
    WebElement problemCheckbox;
    
    @FindBy(xpath=".//*[@id='public_solution_switch']")
    WebElement solutionCheckbox;
    
    @FindBy(xpath="//*[contains(@class, 'ng-binding link-no-answer')]")
    WebElement editIdeaName;
    
    @FindBy(xpath=".//*[@class='l-actions']/input")
    WebElement saveButton;
    
    @FindBy(xpath="//*[contains(@class, 'button button-secondary button-small')]")
    WebElement edit;
    
    @FindBy(xpath=".//textarea[@id='idea_description']")
    WebElement elevatorPitch;
  
    @FindBy(xpath=".//*[@id='idea_questions_problem']")
    WebElement theProblem;
    
    @FindBy(xpath="//div[2]/section[2]/div[1]/h3/a")
    WebElement theProblemOnPublic;
    
    @FindBy(xpath=".//*[@id='idea_questions_solution']")
    WebElement theSolution;
    
    @FindBy(xpath="//div[2]/section[3]/div[1]/h3/a")
    WebElement theSolutionOnPublic;
    
    String s = "/public";
    PageClass pageClass;
    
    public PublicIdeaPage(PageClass pageClass) {
 		this.pageClass = pageClass;
 		PageFactory.initElements(pageClass.driver, this);
 	}
        

		public void editPublicIdea(String URL) throws InterruptedException{
        pageClass.driver.get(URL);
        editIdeaName.click();
        pageClass.setText(ideaName, "Edit name done", pageClass);
        pageClass.setText(elevatorPitch, "Edit elevator pitch", pageClass);
        pageClass.setText(theProblem, "Problem Test", pageClass);
        pageClass.javascriptExecutor(problemCheckbox, pageClass);
        pageClass.setCheckbox(problemCheckbox, true,pageClass);
        pageClass.setText(theSolution, "Solution Test", pageClass);
        pageClass.javascriptExecutor(solutionCheckbox, pageClass);
        pageClass.setCheckbox(solutionCheckbox, true,pageClass);
        JavascriptExecutor jse = (JavascriptExecutor)pageClass.driver;
		jse.executeScript("window.scrollBy(0,-10000)", "");
        saveButton.click();
        Thread.sleep(2000);
        pageClass.driver.get(URL+s);
        Log.info("Idea is Edited and Saved");
        //We have to add the problem solution webelement validation on 
        }

		
		public String theProblemValidation() {
			 String s1 = theProblemOnPublic.getText();
     	 return s1;
		}
		

		public String theSolutionValidation() {
			 String s2 = theSolutionOnPublic.getText();
    	 return s2;
		}
}

