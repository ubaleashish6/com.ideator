	package com.ideator.page.superadmin;
	
	import java.io.IOException;
	import java.util.List;
	
	import org.apache.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	
	import com.ideator.util.PageClass;
	import com.ideator.util.PageObject;
	
	public class EditCommunityPage {
	
		WebDriver driver;

		@FindBy(id = "user-email")
		WebElement userEmail;
	
		@FindBy(xpath ="//*[text()='Edit']")
		WebElement editButton;
		
		@FindBy(css = ".user__image-small>img")
		WebElement avtar;
	
		@FindBy(xpath = "//a[contains(text(),'Communities')]")
		WebElement communityLink;
	
		@FindBy(xpath = "(//a[contains(text(),'Edit')])[15]")
		WebElement ucsdEditLink;
	
		@FindBy(id = "customOnboarding")
		WebElement customOnboard;
	
		@FindBy(id = "	ideatorLogoBranding")
		WebElement ideatorLogoBranding;
	
		@FindBy(xpath = "//button[@type='submit']")
		WebElement saveButton;
	
		@FindBy(xpath = ".//*[@id='public_signup_true']")
		WebElement platformSettingPublic;
	
		@FindBy(xpath = ".//*[@id='user-email']")
		WebElement commuUserName;
	
		@FindBy(xpath = ".//*[@id='open_community_false']")
		WebElement PlatformPublicClosed;
	
		@FindBy(xpath = ".//*[@id='open_community_true']")
		WebElement PlatformPublicOpen;
	
		@FindBy(xpath = ".//*[@id='public_signup_false']")
		WebElement PlatformPrivate;
		

		@FindBy(xpath = ".//*[@id='simplified']")
		WebElement ProfileSimple;
		
		@FindBy(xpath = "html/body/main/section/div/section[1]/div/div/a")
		WebElement EditProfile;
		
		@FindBy(xpath = ".//*[@id='edit_user_133']/div[2]/section[1]/div/span/div[1]/label")
		WebElement EditProfileLinks;
		
		@FindBy(xpath = ".//*[@id='detailed']")
		WebElement ProfileDetailed;
		
		@FindBy(xpath = ".//*[@id='ideaBranding']")
		WebElement PageEditor;
		
		private static final String CLICKONCOMMUNITY = "//*[text()='%s']//following::div[2]";
		
	
	
		PageClass pageClass;
	
		public EditCommunityPage(PageClass pageClass) {
			this.pageClass = pageClass;
			PageFactory.initElements(pageClass.driver, this);
		}
	
		private static Logger Log = Logger.getLogger(EditCommunityPage.class.getName());
	
		public void clickAvatarTab(String tabName) {
			Log.info("Click on "+tabName+" Tab");
			avtar.click();
			List<WebElement> elementList = pageClass.driver.findElements(By.cssSelector(".user-nav__link"));
			for (WebElement element : elementList) {
				if ((element.getText()).equals(tabName)) {
					element.click();
					break;
				}
			}
		}
	
		public void editCommunity(String communityname, PageObject pageObject) {
			String communityURL = pageObject.getConfigProperties().getField(communityname);
			communityLink.click();
			pageClass.driver.findElement(By.xpath("//a[contains(text(),'Load More')]")).click();
			pageClass.driver.findElement(By.xpath(String.format(CLICKONCOMMUNITY, communityURL))).click();
//			ucsdEditLink.click();
			Log.info("Go to Superadmin section and click on "+communityname+" Community");
		}

		public void editPlatformSettingPrivate() throws InterruptedException {
			Thread.sleep(4000);
			PlatformPrivate.click();
			Thread.sleep(4000);
			saveButton.click();
			Thread.sleep(4000);
			Log.info("Make platforn Setting Private and Save the setting");
		}
	
		public void editPlatformSettingPublicClosed() throws InterruptedException {
			Thread.sleep(4000);
			platformSettingPublic.click();
			PlatformPublicClosed.click();
			Thread.sleep(4000);
			saveButton.click();
			Thread.sleep(4000);
			Log.info("Make platforn Setting PublicClosed and Save the setting");
		}
		

		public void userProfileSettingsSimple() throws InterruptedException {
			Thread.sleep(4000);
			ProfileSimple.click();
			Thread.sleep(4000);
			saveButton.click();
			Thread.sleep(4000);
			Log.info("Click on Simple Profile Setting and Save the setting");
		}
		
		public void userProfileSettingsDetailed() throws InterruptedException {
			Thread.sleep(5000);
			ProfileDetailed.click();
			Thread.sleep(5000);
			saveButton.click();
			Thread.sleep(5000);
			Log.info("Click on Detailed Profile Setting and Save the setting");
		}
		
		public boolean userProfileCheck() throws InterruptedException {
			By link = By.xpath("//*[text()='Links']");
			boolean result = pageClass.isElementExist(link, pageClass);
			return result;
			}
		
		public boolean calenderIntegrationCheck() throws InterruptedException {
			editButton.click();
			By calenderInvite = By.xpath(".//input[@id='user_calendar_url']");
			boolean result = pageClass.isElementExist(calenderInvite, pageClass);
			return result;
			}
	
		
		public String PrivateCommunity(){
			return pageClass.driver.getCurrentUrl();
		}
		public String PublicCommunity() {
			return pageClass.driver.getCurrentUrl();
		}
		
		public void EditProfile() {
			EditProfile.click();
			Log.info("Click on Edit Profile Button");
		}
	

		public void editPlatformSettingPublicOpen() throws InterruptedException {
			Thread.sleep(5000);
			platformSettingPublic.click();
			PlatformPublicOpen.click();
			Thread.sleep(5000);
			saveButton.click();
			Thread.sleep(5000);
			Log.info("Make a platform Setting Public Open and Save the Setting");
		}

	
		public String PublicOpenCommunity() {
			return pageClass.driver.getCurrentUrl();
		}
		

		public void siteEditorSettingsON() throws InterruptedException{
			pageClass.changeToggel("customBranding", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("customBranding"));
			pageClass.setCheckbox(element, true);
			saveButton.click();
			Log.info("ON site Editor Toggle so that Site Editor Tab is Avaliable on Admin Section");
		}
		public void siteEditorSettingsOff() throws InterruptedException{
			pageClass.changeToggel("customBranding", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("customBranding"));
			pageClass.setCheckbox(element, false);
			saveButton.click();
			Log.info("OFF site Editor Toggle so that Site Editor Tab is not Avaliable on Admin Section");
		}
		
		public boolean checkSiteEditor(String communityName, PageObject pageObject) throws IOException {
			navigateCommunityWithoutLogout(communityName, pageObject);
			clickAvatarTab("Admin");
			By locator = By.xpath("//*[text()='Site Editor']");
			boolean check = pageObject.getEditCommunityPage().adminTabNavigation(locator);
			return check;
		}
		
		public void pageEditorSettingsOn() throws InterruptedException{
			pageClass.changeToggel("ideaBranding", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("ideaBranding"));
			pageClass.setCheckbox(element, true);
			saveButton.click();
			Thread.sleep(2000);
			Log.info("ON page Editor Toggle so that page Editor Tab is Avaliable on Admin Section");
		}
		public void pageEditorSettingsOff() throws InterruptedException{
			pageClass.changeToggel("ideaBranding", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("ideaBranding"));
			pageClass.setCheckbox(element, false);
			saveButton.click();
			Log.info("OFF page Editor Toggle so that page Editor Tab is not Avaliable on Admin Section");
		}
		public boolean checkPageEditor(String communityName, PageObject pageObject) throws IOException {
			navigateCommunityWithoutLogout(communityName, pageObject);
			clickAvatarTab("Admin");
			By locator = By.xpath("//*[text()='Page Editor']");
			boolean check = pageObject.getEditCommunityPage().adminTabNavigation(locator);
			return check;
		}
		
		
		public void poweredByLogoSettingsOn() throws InterruptedException{
			pageClass.changeToggel("ideatorLogoBranding", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("ideatorLogoBranding"));
			pageClass.setCheckbox(element, true);
			saveButton.click();
			Log.info("ON Powered by logo");
		}
		public void poweredByLogoSettingsOff() throws InterruptedException{
			pageClass.changeToggel("ideatorLogoBranding", pageClass);
			Thread.sleep(5000);
			WebElement element = pageClass.driver.findElement(By.id("ideatorLogoBranding"));
			Thread.sleep(5000);
			pageClass.setCheckbox(element, false);
			Thread.sleep(5000);
			saveButton.click();
			Log.info("OFF Powered by logo and Save the settings");
		}
		public boolean checkpoweredByLogoSettings(String communityName, PageObject pageObject) throws IOException {
			navigateCommunityWithoutLogout(communityName, pageObject);
			//clickAvatarTab("Admin");
			By locator = By.xpath("//div[2]/div[1]/img");
			boolean check = pageObject.getEditCommunityPage().adminTabNavigation(locator);
			return check;
		}
		
		public void calenderIntegrationOn() throws InterruptedException{
			pageClass.changeToggel("calendarIntegration", pageClass);
			WebElement element = pageClass.driver.findElement(By.id("calendarIntegration"));
			pageClass.setCheckbox(element, true);
			saveButton.click();
			Log.info("ON CalenderIntegration and Save the Settings");
		}
		public void calenderIntegrationOff() throws InterruptedException{
			pageClass.changeToggel("calendarIntegration", pageClass);
			Thread.sleep(5000);
			WebElement element = pageClass.driver.findElement(By.id("calendarIntegration"));
			Thread.sleep(5000);
			pageClass.setCheckbox(element, false);
			Thread.sleep(5000);
			saveButton.click();
			Log.info("OFF CalenderIntegration and Save the Settings");
		}
		

		public boolean adminTabNavigation(By locator){
			boolean check= pageClass.isElementExist(locator, pageClass);
			return check;
		}
		public void navigateCommunity(String communityName, PageObject pageObject) throws IOException {
			String url = pageObject.getConfigProperties().getCommunityURL(communityName);
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			pageObject.getConfigProperties().getAutoITCommunity();
			pageClass.driver.get(url);
			Log.info("Navigate "+communityName+" Without Logout");
			clickAvatarTab("Sign Out");
		}
		
		public void navigateCommunityWithoutLogout(String communityName, PageObject pageObject) throws IOException {
			String url = pageObject.getConfigProperties().getCommunityURL(communityName);
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			pageObject.getConfigProperties().getAutoITCommunity();
			pageClass.driver.get(url);
			Log.info("Navigate "+communityName+" Without Logout");
		}
	
		public void navigateCommunityclose(String communityName, PageObject pageObject) throws IOException {
			pageObject.getConfigProperties().getCommunityURL(communityName);
			pageClass.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			pageClass.driver.navigate().refresh();
			Log.info("Close new tab Opened Community and get back to main page");
		}
}