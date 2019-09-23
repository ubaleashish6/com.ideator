package com.test;

import java.io.IOException;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
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
//import com.relevantcodes.extentreports.LogStatus;

public class AdminTest extends PageClass {
	 private static Logger Log = Logger.getLogger(AdminTest.class.getName());

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
		// Make user Community Admin
		public void addAdminRole() throws InterruptedException, IOException {
			test = extent.startTest("addAdminRole");
			super.startTestCase("addAdminRole");
			Log.info("Make user Community Admin");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Users");
			pageObject.getUserGroupManagement().makeAdminRole();
			pageObject.getLoginPage().enterUserCrendential("light1User", "light1Pass", pageObject);
			Assert.assertTrue(pageObject.getUserGroupManagement().isAdminTabPresent());
			test.log(LogStatus.PASS, "Admin role is Added to user");
		}
	
		// Remove Admin role from User
		@Test
		public void removeAdminRole() throws InterruptedException, IOException {
			test = extent.startTest("removeAdminRole");
			super.startTestCase("removeAdminRole");
			Log.info("Remove Admin role from User");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Users");
			pageObject.getUserGroupManagement().removeAdminRole();
			pageObject.getLoginPage().enterUserCrendential("light1User", "light1Pass", pageObject);
			Assert.assertFalse(pageObject.getUserGroupManagement().isAdminTabPresent());
			test.log(LogStatus.PASS, "Admin role is removed from user");
		}
	
		//Create User group
		@Test
		public void createGroup() {
			test = extent.startTest("createGroup");
			super.startTestCase("createGroup");
			Log.info("Create User group");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Groups");
			String groupName = "Selenium Test";
			pageObject.getUserGroupManagement().creategroup(groupName);
			Assert.assertTrue(pageObject.getUserGroupManagement().isGroupPresent(groupName));
			test.log(LogStatus.PASS, "User Group is Created");
		}
	
		//Add users to the newly created group
		@Test
		public void addUserToGroup() throws InterruptedException, IOException {
			test = extent.startTest("addUserToGroup");
			super.startTestCase("addUserToGroup");
			Log.info("Add users to the newly created group");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Users");
			String userNameToAdd = "light1@mailinator.com";
			String groupName = "Selenium Test";
			pageObject.getUserGroupManagement().addUserToGroup(userNameToAdd, groupName);
			Assert.assertTrue(pageObject.getUserGroupManagement().isUserAddedToGroup(userNameToAdd, groupName));
			test.log(LogStatus.PASS, "User is added in to Group");
			super.endTestCase();
		}
	
		//edit user group
		@Test
		public void editUserGroup() {
			test = extent.startTest("editUserGroup");
			super.startTestCase("editUserGroup");
			Log.info("edit user group");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Groups");
			String groupName = "Selenium Test";
			String updatedGroupName = "Selenium Test Group";
			pageObject.getUserGroupManagement().editgroup(groupName, updatedGroupName);
			Assert.assertTrue(pageObject.getUserGroupManagement().isGroupPresent(updatedGroupName));
			test.log(LogStatus.PASS, "User Group is Edited");
		}
	
		//Delete user group
		@Test
		public void deleteGroup() {
			test = extent.startTest("deleteGroup");
			super.startTestCase("deleteGroup");
			Log.info("Delete user group");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			clickOnTab("Manage Groups");
			String updatedGroupName = "Selenium Test Group";
			pageObject.getUserGroupManagement().deleteGroup(updatedGroupName);
			Assert.assertFalse(pageObject.getUserGroupManagement().isGroupDeleted(updatedGroupName));
			test.log(LogStatus.PASS, "User Group is Edited");
		}
	
		// Create and Edit Resource
		@Test
		public void create_edit_Resource() throws InterruptedException {
			test = extent.startTest("create_edit_Resource");
			super.startTestCase("create_edit_Resource");
			Log.info("Create and Edit Resource");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			pageObject.getResourcesManagementPage().createResources(pageObject);
			Assert.assertTrue(pageObject.getResourcesManagementPage().isSuccessMessageDisplay());
			String createdResource = pageObject.getResourcesManagementPage().createdResourceName(pageObject);
			String expectedResourceName = pageObject.getConfigProperties().getField("Resource_Name");
			Assert.assertEquals(createdResource, expectedResourceName);
			test.log(LogStatus.PASS, "New Resource is Created");
			pageObject.getResourcesManagementPage().editResource(pageObject);
			Assert.assertTrue(pageObject.getResourcesManagementPage().isSuccessMessageDisplay());
			String editedResource = pageObject.getResourcesManagementPage().createdResourceName(pageObject);
			String expectedresourceName = pageObject.getConfigProperties().getField("Resource_Name_Edited");
			Assert.assertEquals(editedResource, expectedresourceName);
			test.log(LogStatus.PASS, "New Resource is edited");
		}
	
		// Create Resource Category
		@Test
		public void createResourceCategory() {
			test = extent.startTest("createResourceCategory");
			super.startTestCase("createResourceCategory");
			Log.info("Create Resource Category");
			pageObject.getUserGroupManagement().clickAvatarTab("Admin");
			pageObject.getResourcesManagementPage().createResourcesCategory(pageObject);
			Assert.assertTrue(pageObject.getResourcesManagementPage().isSuccessMessageDisplay());
			test.log(LogStatus.PASS, "New Resource Category is Created");
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
