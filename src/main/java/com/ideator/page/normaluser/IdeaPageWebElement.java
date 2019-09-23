package com.ideator.page.normaluser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IdeaPageWebElement {
	 @FindBy(id="idea_name")
     WebElement ideaName;
	 
	 @FindBy(xpath="//*[@id='idea_description']")
     WebElement elevatorPitch;
	 
	 @FindBy(xpath=".//*[@id='idea_questions_problem']")
     WebElement theProblem;
     

}
