package com.crm.autodesk.genericUtility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.crm.autodesk.ObjectRepository.HomePage;
/**
 * this class contains implementation to the annotations
 * @author Vineeth
 *
 */
public class BaseClass {

	protected WebDriver driver;
	protected ExcelUtility eLib=new ExcelUtility();
	protected FileUtility fLib=new FileUtility();
	protected WebDriverUtility wLib=new WebDriverUtility();
	protected String email ;
	protected String password;
	protected HomePage hp ;
	/**
	 * in this annotation fetching the data of common data from property file 
	 * launch the browser and do the browser settings like maximize the browser and  
	 * launching the application
	 * @throws Throwable
	 */
	@BeforeMethod
	public void loginToApp() throws Throwable
	{
		String browser=fLib.getPropertyKeyValue("browser");
		String url=fLib.getPropertyKeyValue("url");
		driver=wLib.launchBrowser(browser);
		wLib.waitForPageToLoad();
		wLib.maximizeWindow();
		wLib.launchApplication(url);
		System.out.println("======Browser Launched======");
		email = fLib.getPropertyKeyValue("email");
		password = fLib.getPropertyKeyValue("password");
		hp= new HomePage(driver,wLib);
	}
	/**
	 * in this annotation that helps to close the browser
	 */
	@AfterMethod
	public void logoutApp()
	{
		driver.quit();
		System.out.println("========Browser closed========");
	}
	

	
	
}