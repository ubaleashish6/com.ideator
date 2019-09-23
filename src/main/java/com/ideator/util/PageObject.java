package com.ideator.util;

import org.openqa.selenium.WebDriver;

import com.ideator.common.ConfigProperties;
import com.ideator.common.WorkBookRead;
import com.ideator.page.LoginPage;
import com.ideator.page.admin.EnvironmentSettingPage;
import com.ideator.page.admin.ResourcesManagementPage;
import com.ideator.page.admin.UserGroupManagement;
import com.ideator.page.normaluser.CreateIdeaPage;
import com.ideator.page.normaluser.DiscussionPage;
import com.ideator.page.normaluser.EditIdeaPage;
import com.ideator.page.normaluser.EditProfilePage;
import com.ideator.page.normaluser.IdeaOverview;
import com.ideator.page.normaluser.PeoplePage;
import com.ideator.page.normaluser.PublicIdeaPage;
import com.ideator.page.normaluser.PublicUserutilPage;
import com.ideator.page.superadmin.EditCommunityPage;
import com.ideator.page.superadmin.SuperAdminLoginPage;

public class PageObject {

	protected WorkBookRead workBookRead;
	protected ConfigProperties configProperties;
	protected TakeScreenshot takeScreenshot;
	protected LoginPage loginPage;
	protected PageClass pageClass;
	protected EditCommunityPage editCommunityPage;
	protected SuperAdminLoginPage superAdminLoginPage;
	protected EnvironmentSettingPage environmentSettingPage;
	protected UserGroupManagement userGroupManagement;
	protected ResourcesManagementPage resourcesManagementPage;
	protected PublicUserutilPage publicUserutilPage;
	protected EditProfilePage editProfilePage;
	protected CreateIdeaPage createIdeaPage;
	protected EditIdeaPage editIdeaPage;
	protected PublicIdeaPage publicIdeaPage;
	protected IdeaOverview ideaOverview;
	protected PeoplePage peoplePage;
	protected DiscussionPage discussionPage;

	WebDriver driver;
	
		public PageObject(PageClass pageClass) {
			this.pageClass = pageClass;
		}
		
		public DiscussionPage getDiscussionPage() {
			if (discussionPage == null) {
				discussionPage = new DiscussionPage(pageClass);
				return discussionPage;
			}
			return discussionPage;
		}
	
		public DiscussionPage setDiscussionPage(DiscussionPage discussionPage) {
			return this.discussionPage = discussionPage;
		}
	
		public IdeaOverview getIdeaOverview() {
			if (ideaOverview == null) {
				ideaOverview = new IdeaOverview(pageClass);
				return ideaOverview;
			}
			return ideaOverview;
		}
	
		public IdeaOverview setIdeaOverview(IdeaOverview setIdeaOverview, IdeaOverview ideaOverview) {
			return this.ideaOverview = ideaOverview;
		}
	
		public PublicIdeaPage getPublicIdeaPage() {
			if (publicIdeaPage == null) {
				publicIdeaPage = new PublicIdeaPage(pageClass);
				return publicIdeaPage;
			}
			return publicIdeaPage;
		}
	
		public PublicIdeaPage setPublicIdeaPage(PublicIdeaPage publicIdeaPage) {
			return this.publicIdeaPage = publicIdeaPage;
		}
	
		public PeoplePage getPeoplePage() {
			if (peoplePage == null) {
				peoplePage = new PeoplePage(pageClass);
				return peoplePage;
			}
			return peoplePage;
		}
	
		public PeoplePage setPeoplePage(PeoplePage peoplePage) {
			return this.peoplePage = peoplePage;
		}
	
		public EditIdeaPage getEditIdeaPage() {
			if (editIdeaPage == null) {
				editIdeaPage = new EditIdeaPage(pageClass);
				return editIdeaPage;
			}
			return editIdeaPage;
		}
	
		public EditIdeaPage setEditIdeaPage(EditIdeaPage editIdeaPage) {
			return this.editIdeaPage = editIdeaPage;
		}
	
		public CreateIdeaPage getCreateIdeaPage() {
			if (createIdeaPage == null) {
				createIdeaPage = new CreateIdeaPage(pageClass);
				return createIdeaPage;
			}
			return createIdeaPage;
		}
	
		public CreateIdeaPage setCreateIdeaPage(CreateIdeaPage createIdeaPage) {
			return this.createIdeaPage = createIdeaPage;
		}
	
		public EditProfilePage getEditProfilePage() {
			if (editProfilePage == null) {
				editProfilePage = new EditProfilePage(pageClass);
				return editProfilePage;
			}
			return editProfilePage;
		}
	
		public EditProfilePage setEditProfilePage(EditProfilePage editProfilePage) {
			return this.editProfilePage = editProfilePage;
		}
	
		public PublicUserutilPage getPublicUserutilPage() {
			if (publicUserutilPage == null) {
				publicUserutilPage = new PublicUserutilPage(pageClass);
				return publicUserutilPage;
			}
			return publicUserutilPage;
		}
	
		public PublicUserutilPage setPublicUserutilPage(PublicUserutilPage publicUserutilPage) {
			return this.publicUserutilPage = publicUserutilPage;
		}
	
		public ResourcesManagementPage getResourcesManagementPage() {
			if (resourcesManagementPage == null) {
				resourcesManagementPage = new ResourcesManagementPage(pageClass);
				return resourcesManagementPage;
			}
			return resourcesManagementPage;
		}
	
		public ResourcesManagementPage setResourcesManagementPage(ResourcesManagementPage resourcesManagementPage) {
			return this.resourcesManagementPage = resourcesManagementPage;
		}
	
		public UserGroupManagement getUserGroupManagement() {
			if (userGroupManagement == null) {
				userGroupManagement = new UserGroupManagement(pageClass);
				return userGroupManagement;
			}
			return userGroupManagement;
		}
	
		public UserGroupManagement setUserGroupManagement(UserGroupManagement userGroupManagement) {
			return this.userGroupManagement = userGroupManagement;
		}
	
		public EditCommunityPage getEditCommunityPage() {
			if (editCommunityPage == null) {
				editCommunityPage = new EditCommunityPage(pageClass);
				return editCommunityPage;
			}
			return editCommunityPage;
		}
	
		public EditCommunityPage setEditCommunityPage(EditCommunityPage editCommunityPage) {
			return this.editCommunityPage = editCommunityPage;
		}
	
		public LoginPage getLoginPage() {
			if (loginPage == null) {
				loginPage = new LoginPage(pageClass);
				return loginPage;
			}
			return loginPage;
		}
	
		public void setLoginPage(LoginPage loginPage) {
			this.loginPage = loginPage;
		}
	
		public EnvironmentSettingPage getEnvironmentSettingPage() {
			return new EnvironmentSettingPage(driver);
		}
	
		public EnvironmentSettingPage setEnvironmentSettingPage(EnvironmentSettingPage environmentSettingPage) {
			if (environmentSettingPage != null) {
				return this.environmentSettingPage = environmentSettingPage;
			}
			return new EnvironmentSettingPage(driver);
	
		}
	
		public ConfigProperties getConfigProperties() {
			return new ConfigProperties(driver);
		}
	
		public ConfigProperties setConfigProperties(ConfigProperties configProperties) {
			if (configProperties != null) {
				return this.configProperties = configProperties;
			}
			return new ConfigProperties(driver);
	
		}
	
		public SuperAdminLoginPage getSuperAdminLoginPage() {
			if (superAdminLoginPage == null) {
				superAdminLoginPage = new SuperAdminLoginPage(pageClass);
				return superAdminLoginPage;
			}
			return superAdminLoginPage;
		}
	
		public SuperAdminLoginPage setSuperAdminLoginPage(SuperAdminLoginPage superAdminLoginPage) {
			return this.superAdminLoginPage = superAdminLoginPage;
		}
	
	
		public WorkBookRead getWorkBookRead() {
			return new WorkBookRead();
		}
	
		public WorkBookRead setWorkBookRead(WorkBookRead workBookRead) {
			if (workBookRead != null) {
				return this.workBookRead = workBookRead;
			}
			return new WorkBookRead();
		}
	
	}
