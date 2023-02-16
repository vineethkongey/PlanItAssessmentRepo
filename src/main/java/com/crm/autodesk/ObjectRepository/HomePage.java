package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.autodesk.genericUtility.WebDriverUtility;
/**
 * created the class using pom design pattern and builder pattern
 * @author Vineeth
 *
 */
public class HomePage {
	private WebDriver driver;
	private WebDriverUtility wLib;

	/**
	 * declaring all the elements and making private
	 * */
	@FindBy(xpath  = "//a[@class='ico-login']") private WebElement loginLink;
	@FindBy(xpath = "//div[@class='header-links']//a[@class='account']") private WebElement accountIdLink;
	@FindBy(xpath = "//li[@id='topcartlink']//a[@class='ico-cart']") private WebElement gotoshoppingcartLink;
	@FindBy(xpath = "//a[@class='ico-logout']") private WebElement logoutLink;
	@FindBy(xpath = "//ul[@class='top-menu']//a[normalize-space(.)='Computers']") private WebElement computerCategory;
	@FindBy(xpath = "//ul[@class='top-menu']//a[normalize-space(.)='Desktops']") private WebElement desktopMenu;

	/**
	 * initializing all the elements and webdriver utility
	 * */
	public HomePage (WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used to click on login link in the homepage and returns to the login page
	 * @return
	 */
	public LoginPage clickLoginLink()
	{
		loginLink.click();
		return new LoginPage(driver,wLib);
	}
	/**
	 * this method is used to verify the givenaccount id with expected and actual where driver returns to the homepage
	 * @param expectedId
	 * @return
	 */
	public HomePage verifyAccountId(String expectedId) {
		String actualId = accountIdLink.getText();
		Assert.assertEquals(actualId, expectedId);	
		return this;
	}
	/**
	 * this method is used to click on cart link in the homepage and driver returns to the cart page
	 * @return
	 */
	public CartPage clickCartLink() {
		gotoshoppingcartLink.click();
		return new CartPage(driver,wLib);
	}
	/**
	 * this method is used to mouseover on the computer and returns the homepage
	 * @return
	 */
	public HomePage hoverOnComputer() {
		wLib.mouseOverOnElement(computerCategory);
		return this;
	}
	/**
	 * this method is used for the click on the destop menu and returns to the desktop page
	 * @return
	 */
	public DesktopPage selectDesktop() {
		desktopMenu.click();
		return new DesktopPage(driver,wLib);
	}
	/**
	 * this method is used to logout of the application and returns homepage
	 * @return
	 */
	public HomePage clickLogout() {
		logoutLink.click();
		return this;
	}
}
