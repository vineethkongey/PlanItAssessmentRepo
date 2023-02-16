package com.crm.autodesk.ObjectRepository;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;
/**
 * created the class using pom design pattern and builder pattern
 * @author Vineeth
 *
 */
public class CheckoutPage {
	private WebDriver driver;
	private WebDriverUtility wLib;

	/**
	 * declaring all the webelements and making private
	 * */
	@FindBy(xpath="//select[@id='billing-address-select']")	private WebElement billingAddressDropdown;
	@FindBy(xpath="//input[@id='BillingNewAddress_Address1']") private  WebElement address1TxtField;
	@FindBy(xpath="//select[@id= 'BillingNewAddress_CountryId']") private WebElement countryDropDown;
	@FindBy(xpath="//input[@id= 'BillingNewAddress_City']") private WebElement cityNameTxtField;
	@FindBy(xpath="//input[@id= 'BillingNewAddress_ZipPostalCode']") private WebElement postalcodeTxtField;
	@FindBy(xpath="//input[@id= 'BillingNewAddress_PhoneNumber']")	private WebElement phoneNumTxtField;
	@FindBy(xpath="//div[@id='billing-buttons-container']//input[@title='Continue']") private WebElement billingContinueBtn;
	@FindBy(xpath="//span[@id='billing-please-wait']") private WebElement billingWait;



	@FindBy(xpath="//select[@id='shipping-address-select']") private WebElement shippingAddressDropdown;
	@FindBy(xpath="//div[@id='shipping-buttons-container']//input[@title='Continue']") private WebElement shippingContinueBtn;
	@FindBy(xpath="//span[@id='shipping-please-wait']") private WebElement shippingWait;


	private String nextAirRadioButton="//div[@class='method-name']/label[contains(.,'%s')]/preceding-sibling::input";
	@FindBy(xpath="//div[@id='shipping-method-buttons-container']//input[@value='Continue']") private WebElement shippingMethodContinueBtn;
	@FindBy(xpath="//span[@id='shipping-method-please-wait']") private WebElement shippingMethodWait;

	@FindBy(xpath="//input[@value='Payments.CashOnDelivery']") private WebElement cashonDelivery;
	@FindBy(xpath="//div[@id='payment-method-buttons-container']//input[@value='Continue']") private WebElement paymentMethodContinueBtn;
	@FindBy(xpath="//span[@id='payment-method-please-wait']") private WebElement paymentMethodWait;

	@FindBy(xpath="//div[@class='info']//p") private WebElement validatePaymentInfo;
	@FindBy(xpath="//div[@id='payment-info-buttons-container']//input[@value='Continue']") private WebElement paymentInfoContinueBtn;
	@FindBy(xpath="//span[@id='payment-info-please-wait']") private WebElement paymentInfoWait;

	@FindBy(xpath="//input[@value='Confirm']") private WebElement confirmButton;
	@FindBy(xpath="//div[@class='page-title']/h1[.='Thank you']") private WebElement completedMessage;

	/**
	 * initializing all the webelements and webdriverutility
	 * */
	public CheckoutPage(WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * 
	 * @param target
	 * @param changeData
	 * @return
	 */
	private WebElement xpathToWebElement(String target, String changeData) {
		String xpath = String.format(target, changeData);
		return driver.findElement(By.xpath(xpath));
	}
	/**
	 * this method is used to entering the mandatory fields in the billing address from excel  
	 * and returns checkoutpage
	 * @param eLib
	 * @return
	 */
	public CheckoutPage billingAddress(ExcelUtility eLib) {
		try {
			wLib.handleDropDown(billingAddressDropdown, 0);
			wLib.scrollToDown();
			billingContinueBtn.click();
		}
		catch (Exception e) {
			Map<String, String> map = eLib.getData("billingAddress");
			wLib.handleDropDown(countryDropDown, map.get("country"));
			cityNameTxtField.sendKeys( map.get("city"));
			address1TxtField.sendKeys( map.get("address1"));
			postalcodeTxtField.sendKeys( map.get("postalcode"));
			phoneNumTxtField.sendKeys( map.get("phone"));
			billingContinueBtn.click();

		}
		wLib.waitForElementInVisible(billingWait);
		return this;
	}
	/**
	 * this method is used to shipping adress and returns checkout page
	 * @return
	 */
	public CheckoutPage shippingAddress() {
		wLib.handleDropDown(shippingAddressDropdown, 0);
		wLib.scrollToDown();
		shippingContinueBtn.click();
		wLib.waitForElementInVisible(shippingWait);
		return this;
	}
	/**
	 * this method is used to click on next air radio button in the shipping method and 
	 * returns checkout page
	 * @param shippingMethod
	 * @return
	 */
	public CheckoutPage shippingMethod(String shippingMethod) {
		WebElement shippingMethodEle = xpathToWebElement(nextAirRadioButton, shippingMethod);
		shippingMethodEle.click();
		wLib.scrollToDown();
		shippingMethodContinueBtn.click();
		wLib.waitForElementInVisible(shippingMethodWait);
		return this;
	}
	/**
	 * this method is used to visibility of payment method and to click on payment continue button and
	 * returns checkout page
	 * @return
	 */
	public CheckoutPage paymentMethod() {
		wLib.scrollToDown();
		paymentMethodContinueBtn.click();
		wLib.waitForElementInVisible(paymentMethodWait);
		return this;
	}
	/**
	 * this method is used to validate the payment successful information message and
	 * returns checkout page
	 * @param expectedMessage
	 * @return
	 */
	public CheckoutPage verifyPaymentInformation( String expectedMessage) {
		String actualText = validatePaymentInfo.getText();
		Assert.assertEquals(actualText, expectedMessage);
		wLib.scrollToDown();
		paymentInfoContinueBtn.click();
		wLib.waitForElementInVisible(paymentInfoWait);
		return this;
	}
	/**
	 * this method is used to click on confirm button to move checkout complete page and 
	 * returns checkout complete pate
	 * @return
	 */
	public CheckOutCompletePage clickConfirm() {
		wLib.scrollToDown();
		confirmButton.click();
		wLib.waitForElementToVisible(completedMessage);
		return new CheckOutCompletePage(driver,wLib);
	}
}
