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

public class SelectedComputerPage {
	private WebDriver driver;
	private WebDriverUtility wLib;

	/**
	 * decalaring all the web elements and making private 
	 * */
	@FindBy(xpath="//span[@itemprop='price']") private WebElement priceofDesktopTxt;
	@FindBy(xpath="//input[@class='qty-input']") private WebElement enterQuantityTextField;
	@FindBy(xpath="//div[@class='add-to-cart']//input[@value='Add to cart']") private WebElement addtocartBtn;
	@FindBy(xpath="//div[@class='bar-notification success']//p") private WebElement verifyproductmessage;
	@FindBy(xpath="//div[@class='bar-notification error']")	private WebElement quantityerrormsg;
	@FindBy(xpath="//span[@class='close']")	private WebElement closeMessage;

	/**
	 * initializing of webelements and webdriver using constructor
	 * */
	public SelectedComputerPage(WebDriver driver, WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used for selected computer price and get the price details and returns selected computerpage
	 * @return
	 */
	public SelectedComputerPage getPrice() {
		String price = priceofDesktopTxt.getText();
		System.out.println("Unit price of Computer with Default accessory ==> "+price);
		return this;
	}
	/**
	 * this method is used for to add quantity for selected computer page and returns selected computer page 
	 * @param quantity
	 * @return
	 */
	public SelectedComputerPage addQuantity(int quantity) {
		enterQuantityTextField.clear();
		enterQuantityTextField.sendKeys(String.valueOf(quantity));
		return this;
	}
	/**
	 * this method is used for add to cart for selected computer and returns selected computer page
	 * @return
	 */
	public SelectedComputerPage clickAddToCart() {
		addtocartBtn.click();
		return this;
	}
	/**
	 * this methd is used to validating the success message of added cart to the product and returns selected computer page
	 * @param expectedMessage
	 * @return
	 */
	public SelectedComputerPage verifySucessMsg(String expectedMessage) {
		wLib.waitForElementToVisible(verifyproductmessage);
		String actualMessage = verifyproductmessage.getText();
		Assert.assertEquals(actualMessage, expectedMessage);
		closeMessage.click();
		return this;
	}
	/**
	 * this method is used to get the homepage by scrolling up and returns to the homepage
	 * @return
	 */

	public HomePage getHomePage() {
		wLib.scrollToTop();
		return	new HomePage(driver,wLib);
	}



}
