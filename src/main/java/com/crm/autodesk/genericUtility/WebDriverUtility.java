package com.crm.autodesk.genericUtility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * It contains WebDriver Specific Reusable methods/actions
 * @author Vineeth
 *
 */
public class WebDriverUtility {
	private WebDriver driver;
	/**
	 * This mehtod is used to launch browser and get driver instance
	 * @param browser
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		return driver;
	}

	/**
	 * It wait for page to load before identifying any synchronized element in DOM [HTML - DOCUMENT]
	 * @param driver
	 */
	public void waitForPageToLoad() 
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	/**
	 * This Method is used to launch application
	 * @param url
	 */
	public void launchApplication(String url) {
		driver.get(url);
	}




	/**
	 * It is Used to EXPLICITLY wait for the element to be clickable in GUI and check for specific element
	 * @param driver
	 * @param element
	 */
	public void waitForElementToVisible(WebElement element ){
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * it is used to explicitly wait for the element to be clickable in gui and check for the specific element
	 * @param element
	 */
	public void waitForElementInVisible(WebElement element ){
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}



	/**
	 * Used to handle the dropdown and select element by index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}


	/**
	 * Used to handle the dropdown and select element by visible text
	 * @param element
	 * @param text
	 */
	public void handleDropDown(WebElement element, String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}



	/** 
	 * Used to do mouse hover on the located element
	 * @param driver
	 * @param element
	 */
	public void mouseOverOnElement(WebElement element)
	{
		Actions act =new Actions(driver);
		act.moveToElement(element).perform();
	}


	/**
	 * This method is used to scroll to top of the webpage
	 */
	public void scrollToTop()
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	/**
	 * This method is used to scroll to Bottom of the webpage
	 */
	public void scrollToDown()
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	/**
	 * used to Maximize the browser
	 * @param driver
	 */
	public void maximizeWindow()
	{
		driver.manage().window().maximize();
	}
}
