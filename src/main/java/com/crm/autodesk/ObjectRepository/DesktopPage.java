package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebDriverUtility;

/**
 * created the class using pom design pattern and builder pattern
 * @author Vineeth
 *
 */
public class DesktopPage {
	private WebDriver driver;
	private WebDriverUtility wLib;
	

	private String selectComputerXpath="//a[.='%s']";
	
	/*constructor is used for initializing of all the elements and webdriver*/

	public DesktopPage(WebDriver driver,WebDriverUtility wLib) {
		this.driver=driver;
		this.wLib=wLib;
		PageFactory.initElements(driver, this);
	}
	/**
	 * this method is used to convert dynamic xpath into webelemet
	 * @param target
	 * @param changeData
	 * @return
	 */
	private WebElement xpathToWebElement(String target, String changeData) {
		String xpath = String.format(target, changeData);
		return driver.findElement(By.xpath(xpath));
	}
	/**
	 * this method is used to click on specified computer
	 * @param computerName
	 * @return
	 */
	public SelectedComputerPage selectComputer(String computerName) {
		xpathToWebElement(selectComputerXpath, computerName).click();
		return	new SelectedComputerPage(driver,wLib);
	}

}
