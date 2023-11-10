package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.Pages.AccountPage;
import com.tutorialNinja.qa.Pages.Homepage;
import com.tutorialNinja.qa.Pages.LoginPage;
import com.tutorialsninja.qa.base.Base;

import utils.Utils;

public class LoginTest extends Base{
	
	public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		
		driver=InitializeAndOpeningBrowser(prop.getProperty("browserName"));
		Homepage homePage=new Homepage(driver);
		homePage.clickMyAccountButton();
		homePage.selectLoginOption();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1,dataProvider ="TestSupply")
	
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		
		
		AccountPage accountPage=new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusInformation());
	}
	
	@DataProvider(name="TestSupply")
	public Object[][] supplyTestData(){
		
			Object[][] data= Utils.getTestDataFromExcel("Login");			
			return data;
	}
	
	
	
	
	@Test(priority=2)
	
	public void verifyLoginWithInValidCredentials() {
	
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utils.emailTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickLoginButton();
		
		String actualResult = loginPage.getWarningMessageForNoEmailPassword();
		String expectedConvertedResult=dataProp.getProperty("noMatchForEmailPassword");
		Assert.assertEquals(actualResult, expectedConvertedResult);
		
	}
	
	
	

	

}
