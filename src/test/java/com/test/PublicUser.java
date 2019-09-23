	package com.test;
	
	import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
	import org.testng.Assert;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;
	
	import com.ideator.util.PageClass;
	import com.relevantcodes.extentreports.LogStatus;
	
	public class PublicUser extends PageClass {
		private static Logger Log = Logger.getLogger(AdminTest.class.getName());
		private String URL = "";
	
		@BeforeClass
		public void beforeClass() throws IOException {
			super.initilize();
			urlSetupACommunity();
		}
	
		@BeforeSuite
		public void beforeSuite() throws IOException, InterruptedException {
			super.beforeSuite();
		}
	
		@BeforeMethod
		public void beforeMethod() {
			boolean isLogin = isElementExist(By.cssSelector(".user__image-small"));
			if (!isLogin) {
				pageObject.getLoginPage().submitLoginCredential();
			}
		}
	
		@Test
		// Create new idea
		public void createNewIdea() throws InterruptedException {
			test = extent.startTest("createNewIdea");
			super.startTestCase("createNewIdea");
			Log.info("Verify create new idea");
			pageObject.getCreateIdeaPage().clickCreateNewIdea("Selenium Test Verification");
			String assertToTest = pageObject.getCreateIdeaPage().ideaCreateValidation();
			Assert.assertEquals("Selenium Test Verification", assertToTest);
			String URL = driver.getCurrentUrl();
			this.URL = URL;
			test.log(LogStatus.PASS, "Create idea Verified");
		}
	
		@Test
		 // Edit newly created idea
		 public void editNewIdea() throws InterruptedException {
		  test = extent.startTest("editNewIdea");
		  super.startTestCase("editNewIdea");
		  Log.info("Verify that Idea edit functionality is working fine");
		  pageObject.getEditIdeaPage().editIdea(URL);
		  String elevatorpitch = pageObject.getEditIdeaPage().editElevatorPitchValidation();
		  String IdeaN = pageObject.getEditIdeaPage().editIdeaNameValidation();
		  Assert.assertEquals("Edit elevator pitch",elevatorpitch);
		  Assert.assertEquals("Edit name done",IdeaN);
		  test.log(LogStatus.PASS, "Edit Create idea Verified");
		}
	
		@Test
		// Public ideas field verification
		public void publicTAbFunctionality() throws InterruptedException {
			test = extent.startTest("publicTAbFunctionality");
			super.startTestCase("publicTAbFunctionality");
			Log.info("Verify that Public Idea fields are only listed under public view");
			pageObject.getPublicIdeaPage().editPublicIdea(URL);
			String theProblemValue = pageObject.getPublicIdeaPage().theProblemValidation();
			String theSolutionValue = pageObject.getPublicIdeaPage().theSolutionValidation();
			Assert.assertEquals("The Problem", theProblemValue);
			Assert.assertEquals("The Solution", theSolutionValue);
			test.log(LogStatus.PASS, "Public fields are generating correctly");
		}
	
		@Test
		// Liked functionality check
		public void likeIdeaFunctionality() throws InterruptedException {
			test = extent.startTest("likeIdeaFunctionality");
			super.startTestCase("likeIdeaFunctionality");
			Log.info("Verify that Public Idea liked by the user are seen under Liked section");
			pageObject.getIdeaOverview().likeIdea(URL);
			pageObject.getIdeaOverview().moveToIdeasPage();
			pageObject.getIdeaOverview().likeTabPage();
			String ideaName = pageObject.getIdeaOverview().likeValidation();
			Assert.assertEquals("Edit name done", ideaName);
			test.log(LogStatus.PASS, "Like functionality is working fine.");
		}
	
		@Test
		// Search Idea functionality check
		public void searchIdeaFunctionality() throws InterruptedException {
			test = extent.startTest("searchIdeaFunctionality");
			super.startTestCase("searchIdeaFunctionality");
			Log.info("Verify that search functionality is working fine");
			pageObject.getIdeaOverview().searchIdeasPage();
			boolean a = pageObject.getIdeaOverview().searchIdeasPageResult("test");
			Assert.assertTrue(a);
			test.log(LogStatus.PASS, "Search functionality is working fine.");
		}
	
		@Test
		// Sort Idea functionality check
		public void sortIdeaFunctionality() throws InterruptedException {
			test = extent.startTest("sortIdeaFunctionality");
			super.startTestCase("sortIdeaFunctionality");
			Log.info("Verify that sort functionality is working fine");
			pageObject.getCreateIdeaPage().clickCreateNewIdea("Hello Second Idea");
			String assertToTest = pageObject.getCreateIdeaPage().ideaCreateValidation();
			Assert.assertEquals("Hello Second Idea", assertToTest);
			pageObject.getIdeaOverview().sortIdeasPage();
			boolean a = pageObject.getIdeaOverview().searchIdeasPageResult("Second");
			Assert.assertTrue(a);
			test.log(LogStatus.PASS, "Search functionality is working fine.");
		}
	
		@Test
		// User can Edit their Profile
		public void editMyProfile() throws InterruptedException {
			test = extent.startTest("editMyProfile");
			super.startTestCase("editMyProfile");
			Log.info("User can Edit their Profile");
			clickAvatarTab("My Profile");
			pageObject.getEditProfilePage().editprofile(pageObject);
			boolean successmessage = pageObject.getEditProfilePage().isSuccessMessageDisplay();
			Assert.assertTrue(successmessage);
			boolean result = pageObject.getEditProfilePage().isProfileUpdated(pageObject);
			Assert.assertTrue(result);
			test.log(LogStatus.PASS, "MyProfile is Edited");
		}
	
		@Test
		// Search People functionality check
		public void searchPeopleFunctionality() throws InterruptedException {
			test = extent.startTest("searchIdeaFunctionality");
			super.startTestCase("searchIdeaFunctionality");
			Log.info("Verify that search functionality is working fine");
			String first_name = pageObject.getConfigProperties().getField("first_name");
			String last_name = pageObject.getConfigProperties().getField("last_name");
			String expectedPeople = first_name + " " + last_name;
			pageObject.getPeoplePage().searchPeoplePage(pageObject, "first_name");
			Assert.assertTrue(pageObject.getPeoplePage().searchPeoplePageResult(expectedPeople));
			pageObject.getPeoplePage().searchPeoplePage(pageObject, "last_name");
			Assert.assertTrue(pageObject.getPeoplePage().searchPeoplePageResult(expectedPeople));
			pageObject.getPeoplePage().searchPeoplePage(pageObject, "bio");
			Assert.assertTrue(pageObject.getPeoplePage().searchPeoplePageResult(expectedPeople));
			pageObject.getPeoplePage().searchPeoplePage(pageObject, "accomplishments");
			Assert.assertTrue(pageObject.getPeoplePage().searchPeoplePageResult(expectedPeople));
			test.log(LogStatus.PASS, "Search functionality is working fine.");
			super.endTestCase();
		}
		

		@Test
		//Verify that the idea listing are present on which the user is currently working
		public void checkIdeaList() throws InterruptedException {
			test = extent.startTest("checkIdeaList");
			super.startTestCase("checkIdeaList");
			Log.info("Verify that the idea listing are present on which the user is currently working");
			pageObject.getCreateIdeaPage().clickCreateNewIdea("Selenium Test Verification"); //It Creates New Idea
			clickAvatarTab("My Profile");
			boolean result = pageObject.getCreateIdeaPage().isideaListingPresent();
			Assert.assertTrue(result);
			test.log(LogStatus.PASS, "Idea listing are present");
		}
		
		@Test
		//Verify that discussions posting functionality is working properly.
		public void postDiscussion() throws InterruptedException {
			test = extent.startTest("postDiscussion");
			super.startTestCase("postDiscussion");
			Log.info("Verify that discussions posting functionality is working properly.");
			pageObject.getDiscussionPage().createDiscussion(pageObject);
			boolean result = pageObject.getDiscussionPage().isDiscussionCreated(pageObject);
			Assert.assertTrue(result);
			test.log(LogStatus.PASS, "discussions posting functionality is working properly.");
		}
		
		@Test
		//Verify that user can add or delete his/her comment on discussion.
		public void commentOnDiscussion() throws InterruptedException {
			test = extent.startTest("commentOnDiscussion");
			super.startTestCase("commentOnDiscussion");
			Log.info("Verify that user can add or delete his/her comment on discussion.");
			pageObject.getDiscussionPage().commentDiscussion(pageObject);
			boolean result = pageObject.getDiscussionPage().isCommentAdded(pageObject);
			Assert.assertTrue(result);
			test.log(LogStatus.PASS, "User comment is added");
		}
		
		@Test
		//Verify that on clicking on profile picture or user name, user is redirecting to user pubic profile page.
		public void clickonProfilePic() throws InterruptedException {
			test = extent.startTest("clickonProfilePic");
			super.startTestCase("clickonProfilePic");
			Log.info("Verify that on clicking on profile picture or user name, user is redirecting to user pubic profile page.");
			String userName = pageObject.getConfigProperties().getField("name");
			pageObject.getDiscussionPage().clickOnProfilepic(pageObject,userName);
			Assert.assertTrue(pageObject.getDiscussionPage().isProfilePage());
			test.log(LogStatus.PASS, "Clicking on Profile pic User is redirected to User's public Profile page");
		}
		
		@Test
		//Verify that on clicking on delete button, the discussion has been deleted.
		public void deleteDiscussion() throws InterruptedException {
			test = extent.startTest("deleteDiscussion");
			super.startTestCase("deleteDiscussion");
			Log.info("Verify that on clicking on delete button, the discussion has been deleted.");
			pageObject.getDiscussionPage().deleteDiscussion(pageObject);
			boolean result = pageObject.getDiscussionPage().isDiscussionDeleted(pageObject);
			Assert.assertFalse(result);
			test.log(LogStatus.PASS, "Discussion has been deleted.");
		}
		
		
		@Test
		//Verify that user can remove team member if user has an access to it.
		public void add_delete_UserIntoIdea() throws InterruptedException {
			test = extent.startTest("addUserIntoIdea");
			super.startTestCase("addUserIntoIdea");
			Log.info("Verify that user can remove team member if user has an access to it.");
			pageObject.getCreateIdeaPage().clickCreateNewIdea("Selenium Test Verification"); //It Creates New Idea
			String URL = driver.getCurrentUrl();
			this.URL = URL;
			pageObject.getCreateIdeaPage().adduserIntoIdea(pageObject);
			Assert.assertTrue(pageObject.getCreateIdeaPage().isuserisadded());
			pageObject.getCreateIdeaPage().deleteUserFromIdea();
			Assert.assertFalse(pageObject.getCreateIdeaPage().isUserDeleted());
			test.log(LogStatus.PASS, "Clicking on Profile pic User is redirected to User's public Profile page");
		}
		
		@Test
		// Delete Idea From the System
		public void deleteIdea() throws InterruptedException {
			test = extent.startTest("deleteIdea");
			super.startTestCase("deleteIdea");
			Log.info("Delete Idea from table");
			pageObject.getCreateIdeaPage().deleteIdea(URL);
			Assert.assertTrue(pageObject.getCreateIdeaPage().isIdeaDeleted(URL));
			test.log(LogStatus.PASS, "Idea is deleted from 'My Ideas' Tab");
		}
		@Test
		// User is Sign Out from System
		public void signOut() throws InterruptedException {
			test = extent.startTest("signOut");
			super.startTestCase("signOut");
			Log.info("User is Sign Out from System");
			clickAvatarTab("Sign Out");
			test.log(LogStatus.PASS, "User is Sign Out from System");
		}
		
		@Test
		// Community Switcher is Working properly or not
		public void checkCommunitySwitcher() throws InterruptedException {
			test = extent.startTest("checkCommunitySwitcher");
			super.startTestCase("checkCommunitySwitcher");
			Log.info("Community Switcher is Working properly or not");
			clickAvatarTab("Switch community");
			test.log(LogStatus.PASS, "Community Switcher is Working properly");
		}
	
		@Test
		// Avtar Private Message link is Working Properly
		public void clickPrivateMessage() throws InterruptedException {
			test = extent.startTest("clickPrivateMessage");
			super.startTestCase("clickPrivateMessage");
			Log.info("Avtar Private Message link is Working Properly");
			clickAvatarTab("Private Messages");
			String current_PrivateMessagePageURl = pageObject.getPublicUserutilPage().PrivateMessagePageURL();
			String expectedURL = pageObject.getConfigProperties().getField("PrivateMessageURL");
			Assert.assertEquals(current_PrivateMessagePageURl, expectedURL);
			test.log(LogStatus.PASS, "Avtar Private Message link is working Properly");
		}
	
	
		@AfterMethod
		public void afterMethod(ITestResult result) throws IOException {
			super.teardown(result);
		}
	
		@AfterClass
		public void afterClass() {
			super.afterClass();
		}
	
		@AfterSuite
		public void afterSuite() {
			super.afterSuite();
		}
	}
