package com.crm.autodesk.ObjectRepository;

import java.util.List;

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
public class CartPage {
	private WebDriver driver;
	private WebDriverUtility wLib;

	/**
	 * declaring all the elements and making private
	 * */
	@FindBy(xpath  = "//input[@name='removefromcart']") private List<WebElement> selectcheckbox;
	@FindBy(xpath  = "//input[@name='updatecart']") private WebElement updateshoppingcart;
	@FindBy(xpath = " //ul[@class='top-menu']//a[@href='/computers']") private WebElement mouseovertocomputer;
	@FindBy(xpath ="//a[@class='hover' and @href='/desktops']")	private WebElement desktops;
	@FindBy(xpath = "//span[@class='product-unit-price']") private WebElement productUnitPrice;
	@FindBy(xpath ="//span[.='Sub-Total:']/../following-sibling::td//span[@class='product-price']")	private WebElement subtotal;
	@FindBy(xpath="//input[@id='termsofservice']") private WebElement agreecheckbox;
	@FindBy(xpath="//button[@id='checkout']") private WebElement checkoutButton;

	/**
	 * initializing all the webelements and webdriver utility
	 * */
	public CartPage(WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used to clear the cart and select the checkbox and returns the homepage
	 * @return
	 */
	public HomePage clearCart()
	{
		try {	
			for (WebElement checkbox : selectcheckbox) {
				checkbox.click();
			}	
			updateshoppingcart.click();
		}
		catch (Exception e) {
		}
		return new HomePage(driver,wLib);
	}
	/**
	 * this method is used to validate the subtotal price and returns cart page
	 * @param noOfUnit
	 * @return
	 */
	public CartPage validateSubTotal(int noOfUnit) {
		double unitPrice = Double.parseDouble(productUnitPrice.getText());
		System.out.println("Unit Price of Computer with Extra Accessory ==> "+unitPrice);
		double totalSubTotal=unitPrice*noOfUnit;
		double  actualSubTotal= Double.parseDouble(subtotal.getText());
		System.out.println("Total Sub Total for "+noOfUnit+" computers ==> "+actualSubTotal);
		Assert.assertEquals(actualSubTotal, totalSubTotal);
		return this;
	}
	/**
	 * this method is used to click on agree check box and check button returns checkout page
	 * @return
	 */
	public CheckoutPage clickCheckout() {
		agreecheckbox.click();
		checkoutButton.click();
		return new CheckoutPage(driver,wLib);
	}

}


