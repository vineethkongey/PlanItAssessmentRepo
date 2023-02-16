package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebDriverUtility;
/**
 * created the class using pom design pattern and builder pattern
 * @author Vineeth
 *
 */
public class LoginPage {
	private WebDriver driver;
	private WebDriverUtility wLib;

	/**
	 * declaring all the elements and making private
	 * */
	@FindBy(id = "Email") private WebElement emailTxtField;
	@FindBy(id = "Password") private WebElement passwordTxtField;
	@FindBy(xpath = "//input[@value='Log in']")	private WebElement loginBtn;
	@FindBy(id = "RememberMe") private WebElement rememberMeCheckBox;

	/**
	 * initializing all the web elements and webdriver using constructor
	 * */
	public LoginPage(WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used for login actions providing username and password and returns to the homepage
	 * @param userName
	 * @param password
	 * @return
	 */
	public HomePage loginAction(String userName, String password)
	{
		emailTxtField.sendKeys(userName);
		passwordTxtField.sendKeys(password);
		rememberMeCheckBox.click();
		loginBtn.click();
		return new HomePage(driver,wLib);
	}
}
