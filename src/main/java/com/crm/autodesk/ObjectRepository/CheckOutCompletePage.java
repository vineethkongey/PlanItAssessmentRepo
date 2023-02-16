package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.autodesk.genericUtility.WebDriverUtility;
/**
 * created a class using pom design pattern and builder pattern
 * @author Vineeth
 *
 */
public class CheckOutCompletePage {
	private WebDriver driver;
	private WebDriverUtility wLib;
	
	/**
	 * declaring the webelements and making private
	 * */
	@FindBy(xpath="//div[@class='title']/strong") private WebElement validateOrderMessage;
	@FindBy(xpath="//ul[@class='details']/li[not(./a)]")	private WebElement validateOrderNumber;
	@FindBy(xpath="//input[@value='Continue']") private WebElement ordercompleteButton;

	/**
	 * initializing all the weblements and webdriver utility
	 * */
	public CheckOutCompletePage(WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used to validate the order messge and returns checkout complete page
	 * @param expectedOrderMessage
	 * @return
	 */
	public CheckOutCompletePage validateOrderMessage(String expectedOrderMessage) {
		String actualMessage = validateOrderMessage.getText();
		Assert.assertEquals(actualMessage, expectedOrderMessage);
		return this;
	}
	/**
	 * this method is used to get the orderId
	 * @return
	 */
	public CheckOutCompletePage getOrderNumber() {
		String orderNumber=	validateOrderNumber.getText().replaceAll("[^0-9]", "");
		System.out.println("OrderId ==> "+ orderNumber);
		return this;
	}
	/**
	 * this method is used to click on continue and returns homepage
	 * @return
	 */
	public HomePage clickContinue() {
		ordercompleteButton.click();
		return new HomePage(driver,wLib);
	}

}
