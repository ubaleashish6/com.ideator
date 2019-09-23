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


public class SuperAdminTest extends PageClass {
		private static Logger Log = Logger.getLogger(SuperAdminTest.class.getName());
		
		@BeforeClass
		public void beforeClass() throws IOException {
			initilize();
			urlSetup();
		}
	
		@BeforeSuite
		public void beforeSuite() throws IOException, InterruptedException {
			super.beforeSuite();
		}
	
		@BeforeMethod
		public void beforeMethod() throws InterruptedException {
			boolean isLogin = isElementExist(By.cssSelector(".user__image-small"));
			if (!isLogin) {
				pageObject.getLoginPage().ideatorsubmitLoginCredential();
			}
		}
	
		@Test
		// Check if the user belong to more than one community
		public void CommunitySwitcher() throws InterruptedException {
			test = extent.startTest("isCommunitySwitcher");
			super.startTestCase("isCommunitySwitcher");
			Log.info("Check if the user belong to more than one community");
			boolean value = pageObject.getSuperAdminLoginPage().isCommunitySwitcherExist();
			Assert.assertTrue(value);
			Log.info("Community Switcher is Present");
			if (value) {
				pageObject.getSuperAdminLoginPage().clickOnCommunity();
			}
			String actualUrl = pageObject.getSuperAdminLoginPage().getAcceptanceUrl();
			Assert.assertEquals(actualUrl, "https://acceptance.ideator.com/");
			test.log(LogStatus.PASS, "Community Switcher is Present and User logged into Ideator ");
			super.endTestCase();
		}
	
		@Test
		// Community Platform settings Private
		public void platformSettingPrivate() throws InterruptedException, IOException {
			test = extent.startTest("platformSettingPrivate");
			Log.info("Community Platform Settings Private");
			super.startTestCase("platformSettingPrivate");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity",pageObject);
			pageObject.getEditCommunityPage().editPlatformSettingPrivate();
			pageObject.getEditCommunityPage().navigateCommunity("CybageAcceptance", pageObject);
			String actualurl = pageObject.getEditCommunityPage().PrivateCommunity();
			Assert.assertEquals(actualurl, "https://cybageacceptance.ideator.com/sign_in");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			pageObject.getSuperAdminLoginPage().submitLoginCredential();
			pageObject.getSuperAdminLoginPage().isCommunitySwitcherExist();
			pageObject.getSuperAdminLoginPage().clickOnCommunity();
			test.log(LogStatus.PASS, "Community Platform Setting Make Private");
			super.endTestCase();
		}
	
		@Test
		// Community Platform settings Public Closed
		public void platformSettingPublicClose() throws InterruptedException, IOException {
			test = extent.startTest("platformSettingPublic");
			Log.info("Community Platform Settings Public Closed");
			super.startTestCase("platformSettingublic");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity",pageObject);
			pageObject.getEditCommunityPage().editPlatformSettingPublicClosed();
			pageObject.getEditCommunityPage().navigateCommunity("CybageAcceptance", pageObject);
			String actualurl = pageObject.getEditCommunityPage().PublicCommunity();
			Assert.assertEquals(actualurl, "https://cybageacceptance.ideator.com/sign_in");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			pageObject.getSuperAdminLoginPage().submitLoginCredential();
			pageObject.getSuperAdminLoginPage().isCommunitySwitcherExist();
			pageObject.getSuperAdminLoginPage().clickOnCommunity();
			test.log(LogStatus.PASS, "Community Platform Settings Public Closed");
			super.endTestCase();
		}
	
		@Test
		// Community Platform settings Public Open
		public void platformSettingPublicOpen() throws InterruptedException, IOException {
			test = extent.startTest("platformSettingPublicOpen");
			Log.info("Community Platform Settings Public Open");
			super.startTestCase("platformSettingPublicOpen");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity",pageObject);
			pageObject.getEditCommunityPage().editPlatformSettingPublicOpen();
			pageObject.getEditCommunityPage().navigateCommunity("CybageAcceptance", pageObject);
			String actualurl = pageObject.getEditCommunityPage().PublicOpenCommunity();
			Assert.assertEquals(actualurl, "https://cybageacceptance.ideator.com/");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			pageObject.getSuperAdminLoginPage().submitLoginCredential();
			pageObject.getSuperAdminLoginPage().isCommunitySwitcherExist();
			pageObject.getSuperAdminLoginPage().clickOnCommunity();
			test.log(LogStatus.PASS, "Community Platform Settings is Public Open");
			super.endTestCase();
		}
	
		@Test
		// User Profile Settings Simplified
		public void simpleProfile() throws InterruptedException, IOException {
			test = extent.startTest("User Profile Simplified");
			Log.info("User Profile Simplified");
			super.startTestCase("User Profile Simplified");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().userProfileSettingsSimple();
			pageObject.getEditCommunityPage().navigateCommunityWithoutLogout("CybageAcceptance", pageObject);
			pageObject.getEditCommunityPage().clickAvatarTab("My Profile");
			pageObject.getEditCommunityPage().EditProfile();
			boolean getresultsimple = pageObject.getEditCommunityPage().userProfileCheck();
			Assert.assertFalse(getresultsimple);
			Log.info("User Profile Settings Simplified is passed");
			test.log(LogStatus.PASS, "User Profile Settings Simplified is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// User Profile Settings Detailed
		public void detailedProfile() throws InterruptedException, IOException {
			test = extent.startTest("User Profile Detailed");
			Log.info("User Profile Detailed");
			super.startTestCase("User Profile Detailed");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().userProfileSettingsDetailed();
			pageObject.getEditCommunityPage().navigateCommunityWithoutLogout("CybageAcceptance", pageObject);
			pageObject.getEditCommunityPage().clickAvatarTab("My Profile");
			pageObject.getEditCommunityPage().EditProfile();
			boolean getresultdetailed = pageObject.getEditCommunityPage().userProfileCheck();
			Assert.assertTrue(getresultdetailed);
			Log.info("User Profile Settings Detailed is passed");
			test.log(LogStatus.PASS, "User Profile Settings Detailed is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Site Editor Settings ON
		public void siteEditorOn() throws InterruptedException, IOException {
			test = extent.startTest("Site Editor Settings On");
			Log.info("Site Editor Settings On");
			super.startTestCase("Site Editor Settings On");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().siteEditorSettingsON();
			boolean result = pageObject.getEditCommunityPage().checkSiteEditor("CybageAcceptance", pageObject);
			Assert.assertTrue(result);
			Log.info("Site Editor Settings On is passed");
			test.log(LogStatus.PASS, "Site Editor Settings On is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Site Editor Settings OFF
		public void siteEditorOff() throws InterruptedException, IOException {
			test = extent.startTest("Site Editor Settings Off");
			Log.info("Site Editor Settings Off");
			super.startTestCase("Site Editor Settings Off");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().siteEditorSettingsOff();
			boolean result = pageObject.getEditCommunityPage().checkSiteEditor("CybageAcceptance", pageObject);
			Assert.assertFalse(result);
			Log.info("Site Editor Settings Off is passed");
			test.log(LogStatus.PASS, "Site Editor Settings Off is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Page Editor Settings On
		public void pageEditorOn() throws InterruptedException, IOException {
			test = extent.startTest("Page Editor Settings On");
			Log.info("Page Editor Settings On");
			super.startTestCase("Page Editor Settings On");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().pageEditorSettingsOn();
			boolean result = pageObject.getEditCommunityPage().checkPageEditor("CybageAcceptance", pageObject);
			Assert.assertTrue(result);
			Log.info("Page Editor Settings On is passed");
			test.log(LogStatus.PASS, "Page Editor Settings On is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Page Editor Settings Off
		public void pageEditorOff() throws InterruptedException, IOException {
			test = extent.startTest("Page Editor Settings Off");
			Log.info("Page Editor Settings Off");
			super.startTestCase("Page Editor Settings Off");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().pageEditorSettingsOff();
			boolean result = pageObject.getEditCommunityPage().checkPageEditor("CybageAcceptance", pageObject);
			Assert.assertFalse(result);
			Log.info("Page Editor Settings Off is passed");
			test.log(LogStatus.PASS, "Page Editor Settings Off is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Powered By Logo Settings On
		public void poewredByLogoOn() throws InterruptedException, IOException {
			test = extent.startTest("Powered By Logo Settings On");
			Log.info("Powered By Logo Settings On");
			super.startTestCase("Powered By Logo Settings On");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().poweredByLogoSettingsOn();
			boolean result = pageObject.getEditCommunityPage().checkpoweredByLogoSettings("CybageAcceptance", pageObject);
			Assert.assertTrue(result);
			Log.info("Powered By Logo Settings On is passed");
			test.log(LogStatus.PASS, "Powered By Logo Settings On is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Powered By Logo Settings Off
		public void poewredByLogoOff() throws InterruptedException, IOException {
			test = extent.startTest("Powered By Logo Settings Off");
			Log.info("Powered By Logo Settings Off");
			super.startTestCase("Powered By Logo Settings Off");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().poweredByLogoSettingsOff();
			boolean result = pageObject.getEditCommunityPage().checkpoweredByLogoSettings("CybageAcceptance", pageObject);
			Assert.assertFalse(result);
			Log.info("Powered By Logo Settings Off is passed");
			test.log(LogStatus.PASS, "Powered By Logo Settings Off is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Calender Integration Settings On
		public void calenderIntegrationOn() throws InterruptedException, IOException {
			test = extent.startTest("Calender Integration Settings On");
			Log.info("Calender Integration Settings On");
			super.startTestCase("Calender Integration Settings On");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().calenderIntegrationOn();
			pageObject.getEditCommunityPage().navigateCommunityWithoutLogout("CybageAcceptance", pageObject);
			pageObject.getEditCommunityPage().clickAvatarTab("My Profile");
			// pageObject.getEditCommunityPage().EditProfile();
			boolean getcalender = pageObject.getEditCommunityPage().calenderIntegrationCheck();
			Assert.assertTrue(getcalender);
			Log.info("Calender Integration Settings On is passed");
			test.log(LogStatus.PASS, "Calender Integration Settings On is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@Test
		// Calender Integration Settings Off
		public void calenderIntegrationOff() throws InterruptedException, IOException {
			test = extent.startTest("Calender Integration Settings Off");
			Log.info("Calender Integration Settings Off");
			super.startTestCase("Calender Integration Settings Off");
			pageObject.getEditCommunityPage().clickAvatarTab("Superadmin");
			pageObject.getEditCommunityPage().editCommunity("CybageAcceptanceCommunity", pageObject);
			pageObject.getEditCommunityPage().calenderIntegrationOff();
			pageObject.getEditCommunityPage().navigateCommunityWithoutLogout("CybageAcceptance", pageObject);
			pageObject.getEditCommunityPage().clickAvatarTab("My Profile");
			// pageObject.getEditCommunityPage().EditProfile();
			boolean getcalender = pageObject.getEditCommunityPage().calenderIntegrationCheck();
			Assert.assertFalse(getcalender);
			Log.info("Calender Integration Settings Off is passed");
			test.log(LogStatus.PASS, "Calender Integration Settings Off is passed");
			pageObject.getEditCommunityPage().navigateCommunityclose("CybageAcceptance", pageObject);
			super.endTestCase();
		}
	
		@AfterMethod
		public void afterMethod(ITestResult result) throws IOException {
			super.teardown(result);
			;
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
