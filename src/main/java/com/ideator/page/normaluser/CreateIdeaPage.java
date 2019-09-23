package com.ideator.page.normaluser;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.util.PageClass;
import com.ideator.util.PageObject;
 public class CreateIdeaPage {
		WebDriver driver;
		private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
		
		private static final String TEAMSECTION = "//*[text()='%s']//preceding::div[4]";
		
		@FindBy(xpath = "//*[contains(@class, 'page-nav')]/div/a[2]")
		WebElement myIdeas;
	
		@FindBy(id = "idea_name")
		WebElement ideaName;
	
		@FindBy(xpath = ".//*[@id='new_idea']/div[3]/div/select")
		WebElement category;
	
		@FindBy(xpath = "//*[contains(@class,'action-container ng-scope')]/a")
		WebElement createNewIdea;
	
		@FindBy(id = "idea_location")
		WebElement location;
	
		@FindBy(id = "idea_description")
		WebElement elevatorPitch;
	
		@FindBy(name = "commit")
		WebElement submitButton;
	
		@FindBy(xpath = "//*[contains(@class,'app-banner__copy')]/h2")
		WebElement ideaname;
	
		@FindBy(xpath = ".//*[@id='skills-and-users']")
		WebElement searchBox;
	
		@FindBy(xpath = ".//*[@id='ngdialog2']/div/div[1]/div[2]/div/div/div")
		WebElement doneButton;
	
		@FindBy(xpath = "//*[text()='Invite team members']")
		WebElement invite_team_member;
	
		@FindBy(xpath = "//tags-input/div/div/input")
		WebElement add_People;
	
		@FindBy(xpath = ".//textarea[@id='invitations_personal_message']")
		WebElement personal_message;
	
		@FindBy(xpath = ".//input[@id='invitation_role_team']")
		WebElement invitation_role_team;
	
		@FindBy(xpath = ".//*[contains(@value,'Send Invites')]")
		WebElement Send_Invites;
	
		@FindBy(xpath = "//*[text()='Back to the team page']")
		WebElement back_button;
	
		@FindBy(xpath = ".//*[@id='inboxfield']")
		WebElement sendInvite;
	
		@FindBy(xpath = ".//input[@id='user-nda']")
		WebElement user_nda;
	
		@FindBy(xpath = ".//*[contains(@value,'Accept the Invitation')]")
		WebElement accept_invitation_button;
	
		@FindBy(xpath = ".//input[@id='collaboration_role_ids_delete']")
		WebElement deleteCheckBox;
	
		@FindBy(id = "user-email")
		WebElement userEmail;
	
		@FindBy(id = "user-password")
		WebElement userPass;
	
		@FindBy(xpath = ".//input[@type='submit']")
		WebElement loginSubmitButton;
	
		PageClass pageClass;
	
		// private String URL = "";
	
		public CreateIdeaPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		public void clickCreateNewIdea(String string, PageObject pageObject) {
			myIdeas.click();
			if (pageClass.isElementExist(By.xpath("//*[text()='Create your first idea']"), pageClass)) {
				pageClass.driver.findElement(By.xpath("//*[text()='Create a new Idea']")).click();
			} else {
				createNewIdea.click();
			}
			if (pageClass.isElementExist(By.xpath("//*[text()='Tagline']"), pageClass)) {
				ideaName.sendKeys("Selenium Test Verification");
				pageClass.driver.findElement(By.xpath(".//input[@id='idea_tagline']")).sendKeys("Sample tag Name");
				elevatorPitch.sendKeys("Automation Test");
			} else {
	
				ideaName.sendKeys("Selenium Test Verification");
				Select dropdown = new Select(category);
				dropdown.selectByIndex(0);
				// location.sendKeys("Pune, Maharashtra, India");
				elevatorPitch.sendKeys("Automation Test");
			}
			submitButton.click();
			doneButton.click();
		}
	
		public void clickCreateNewIdea(String ideaname) {
			myIdeas.click();
			if (pageClass.isElementExist(By.xpath("//*[text()='Create your first idea']"), pageClass)) {
				pageClass.driver.findElement(By.xpath("//*[text()='Create a new Idea']")).click();
			} else {
				createNewIdea.click();
			}
			if (pageClass.isElementExist(By.xpath("//*[text()='Tagline']"), pageClass)) {
				ideaName.sendKeys(ideaname);
				pageClass.driver.findElement(By.xpath(".//input[@id='idea_tagline']")).sendKeys("Sample tag Name");
				elevatorPitch.sendKeys("Automation Test");
			} else {
				ideaName.sendKeys(ideaname);
				Select dropdown = new Select(category);
				dropdown.selectByIndex(0);
				elevatorPitch.sendKeys("Automation Test");
			}
			submitButton.click();
			doneButton.click();
			Log.info(ideaname + " idea is Created");
		}
		public boolean isideaListingPresent() {
			List<WebElement> elements = pageClass.driver.findElements(By.xpath("//div/section[3]/div"));
			for (WebElement element : elements) {
				if (element.getText().contains("Selenium Test Verification")) {
					return true;
				}
			}
			return false;
	
		}
	
		public void adduserIntoIdea(PageObject pageObject) throws InterruptedException {
			invite_team_member.click();
			WebElement element = pageClass.driver.findElement(By.cssSelector(".tags"));
			JavascriptExecutor js = (JavascriptExecutor) pageClass.driver;
			js.executeScript("arguments[0].setAttribute('style', 'display:block')", element);
			add_People.sendKeys(pageObject.getConfigProperties().getField("addUserInIdea"));
			Thread.sleep(3000);
			add_People.sendKeys(Keys.ARROW_DOWN);
			add_People.sendKeys(Keys.ENTER);
			personal_message.click();
			personal_message.sendKeys(pageObject.getConfigProperties().getField("personalMessage"));
			invitation_role_team.click();
			Send_Invites.click();
			Log.info("Idea invite is send to User");
			back_button.click();
			Thread.sleep(3000);
			clickAvatarTab("Sign Out");
			openMailinatorAndVerifyMail(pageObject.getConfigProperties().getField("addUserInIdea"), pageObject);
		}
	
		private void openMailinatorAndVerifyMail(String userName, PageObject pageObject) throws InterruptedException {
			String oldTab = pageClass.driver.getWindowHandle();
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			pageClass.driver.get("https://www.mailinator.com/");
			Log.info("Open Mailinator in New Tab");
			sendInvite.sendKeys(userName);
			pageClass.driver.findElement(By.cssSelector(".btn.btn-dark")).click();
			List<WebElement> mails = pageClass.driver.findElements(By.xpath("//div[2]/div[5]/div"));
			for (WebElement mail : mails) {
				if (mail.getText().contains("You have been invited to join an idea on")) {
					mail.click();
					break;
				}
			}
			pageClass.driver.switchTo().frame("publicshowmaildivcontent");
			String parentHandle = pageClass.driver.getWindowHandle();// Current Window
			pageClass.driver.findElement(By.xpath("//a[contains(text(),'Accept the invite')]")).click();
			for (String winHandle : pageClass.driver.getWindowHandles()) {
				pageClass.driver.switchTo().window(winHandle); 
			}// switch focus of WebDriver to the next found window handle (that's your newly opened window)
			String username = pageObject.getConfigProperties().getField("addUserInIdea");
			String passWord = pageObject.getConfigProperties().getField("password");
			signupSystem(username, passWord);
			user_nda.click();;
			Log.info("Click on NDA Checkbox for Ideator Agreement");
			accept_invitation_button.click();
			pageClass.driver.close(); // close newly opened window when done with it
			pageClass.driver.switchTo().window(parentHandle); // switch back to the original window
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			pageClass.driver.switchTo().window(oldTab); // switch back to the original window
			pageObject.getLoginPage().submitLoginCredential();
		}
	
		public void signupSystem(String username, String passWord) {
			userEmail.sendKeys(username);
			userPass.sendKeys(passWord);
			loginSubmitButton.click();
		}
	
		private void clickAvatarTab(String tabName) {
			pageClass.driver.findElement(By.cssSelector(".user__image-small")).click();
			List<WebElement> elementList = pageClass.driver.findElements(By.cssSelector(".user-nav__link"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals(tabName)) {
					element.click();
					break;
				}
			}
		}
		
		public boolean isuserisadded() throws InterruptedException {
			myIdeas.click();
			Thread.sleep(3000);
			pageClass.driver.findElement(By.xpath("//div[2]/section/div[1]/h3/a")).click();
			List<WebElement> elementList = pageClass.driver.findElements(By.xpath("//div[1]/section[1]"));
			for (WebElement element : elementList) {
				if ((element.getText()).contains("as sa")) {
					return true;
				}
			}
			return false;
			
		}
		
		public void deleteUserFromIdea() throws InterruptedException {
			pageClass.driver.findElement(By.xpath("//section[1]/div/div[1]/h3/a")).click();
			pageClass.driver.findElement(By.xpath(String.format(TEAMSECTION, "as sa"))).click();
			deleteCheckBox.click();
			Thread.sleep(3000);
			pageClass.driver.findElement(By.xpath(".//*[contains(@value,'Submit')]")).click();
			Thread.sleep(3000);
			pageClass.driver.findElement(By.xpath("//*[text()='Close']")).click();
			Log.info("User is deleted from Idea");
		}

		public boolean isUserDeleted() {
			pageClass.driver.navigate().back();
			pageClass.driver.navigate().refresh();
			List<WebElement> elementList = pageClass.driver.findElements(By.xpath("//div[1]/section[1]"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals("as sa")) {
					return true;
				}
			}
			return false;
		}
		
	
		
	
		public String ideaCreateValidation() {
			String ideaName = ideaname.getText();
			return ideaName;
		}

		public void deleteIdea(String uRL) throws InterruptedException {
			pageClass.driver.get(uRL);
//			myIdeas.click();
//			Thread.sleep(3000);
//			pageClass.driver.findElement(By.xpath("//div[2]/section/div[1]/h3/a")).click();
			pageClass.driver.findElement(By.xpath(".//*[@id='danger-zone']/div/a")).click();
			pageClass.driver.findElement(By.cssSelector(".button.button-red")).click();
			Thread.sleep(2000);
			pageClass.driver.findElement(By.xpath(" //*[text()='Close']"));
			Log.info("Idea is deleted");
		}

		public boolean isIdeaDeleted(String uRL) {
			pageClass.driver.get(uRL);
			By ohBoy = By.xpath(" //*[text()='Oh boy!']");
			boolean isOhBoyPresent = pageClass.isElementExist(ohBoy, pageClass);
			return isOhBoyPresent;
		}
	}