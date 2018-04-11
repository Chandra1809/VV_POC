package com.sample.web.base;



import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/*
 * @author CIGNITI
 *
 */

public class CommonTestActions implements IConstants {


	public WebDriver driver;
	public ExtentTest reportsLogger;
	public static ExtentReports reports;
	public static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();


	private static Logger logger = LoggerFactory.getLogger(CommonTestActions.class);


	public static ExtentReports getReports(){
		return reports;
	}

	public WebDriver getDriver(){
		return webDriver.get();
	}

	public void setDriver(WebDriver driver){
		webDriver.set(driver);
	}

	public void setCurrentLogger(ExtentTest test) {
		extentTest.set(test);
	}

	public ExtentTest getCurrentLogger() {
		return extentTest.get();
	}

	public synchronized void startTest(String testName) {
		ExtentTest test = reports.startTest(testName);
		setCurrentLogger(test);
	}

	public void clickButton(WebElement ele) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void moveToElement(WebElement element) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void selectItemByText(WebElement ele,String option,String CustomMessage) {
		Select select = new Select(ele);
		select.selectByVisibleText(option);
		logger.info("Selected '" + option + "' Option from " + CustomMessage + " dropdown");
		reportsLogger.log(LogStatus.INFO, "Selected '" + option + "' Option from " + CustomMessage + " dropdown");

	}

	public void selectItemByIndex(WebElement ele,int itemIndex,String customMessage){
		try{
			Select select = new Select(ele);
			select.selectByIndex(itemIndex);
			logger.info("Selected item at index: " + itemIndex + " From " + customMessage);
			reportsLogger.log(LogStatus.INFO, "Selected item at index: " + itemIndex + " From " + customMessage);
		}catch(NoSuchElementException e){
			reportsLogger.log(LogStatus.FAIL, "Unable to Select item at index: " + itemIndex + " From " + customMessage);
			logger.error(e.getMessage());
			throw e; 
		} 
	}

	public void selectItemByValue(WebElement ele,String value,String customMessage){
		try{
			Select select = new Select(ele);
			select.selectByValue(value);
			logger.info("Selected '" + value + "' From " + customMessage);
			reportsLogger.log(LogStatus.INFO, "Selected '" + value + "' From " + customMessage); 
		}catch(NoSuchElementException e){
			reportsLogger.log(LogStatus.ERROR, "Unable to Select '" + value + "' From " + customMessage);
			logger.error(e.getMessage());
			throw e; 
		} 
	}

	public void waitForElementToBeVisible(WebElement ele, long timeOutInSeconds) {
		try{
			(new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOf(ele));
			Assert.assertTrue(true,"Element is Present and Visible");
		}catch(Exception e){
			Assert.assertTrue(false,"Element is Present but not Visible");
		}
	}

	public void waitForElementToBePresent(By locator, long timeOutInSeconds) {
		try{
			(new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
			Assert.assertTrue(true,"Element is Present");
		}catch(Exception e){
			Assert.assertTrue(false,"Element is not Present");
		}
	}


	public boolean isElementDisplayed(WebElement element){
		try{
			if(element.isDisplayed()){
				logger.info(element.getText()+ " is displayed");
				reportsLogger.log(LogStatus.INFO, element.getText() +" Element Visible");
				return true;
			}else{
				reportsLogger.log(LogStatus.ERROR, "Element is Not Visible");
				return false;
			}
		}catch(NoSuchElementException exp){
			return false;
		}
	}


	public int genRandomNum(int endRange){
		Random randomGenerator = new Random();
		int randIndex = randomGenerator.nextInt(endRange-1);
		if (randIndex == 0)
			randIndex = randIndex + 1;
		return randIndex;
	}

}




