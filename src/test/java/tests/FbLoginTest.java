package tests;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.FbHomePage;
import pages.FbLoginPage;

public class FbLoginTest extends TestBase {

	 @Test

	//@Test(dataProviderClass = CutomDataProvider.class, dataProvider = "empLogin")
	//public void FBLogin(String arg1, String arg2) {
	 public void init() {
			//for logging
			BasicConfigurator.configure();

			FbLoginPage loginpage = PageFactory.initElements(driver, FbLoginPage.class);
			loginpage.setEmail("username");
			loginpage.setPassword("password");
			loginpage.clickOnLoginButton();

			FbHomePage homepage = PageFactory.initElements(driver, FbHomePage.class);
			homepage.clickOnProfileDropdown();
			homepage.verifyLoggedInUserNameText();
			homepage.clickOnLogoutLink();
		
	}

}