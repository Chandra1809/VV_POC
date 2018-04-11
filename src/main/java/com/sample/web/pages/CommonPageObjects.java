package com.sample.web.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.sample.web.base.CommonTestActions;


public class CommonPageObjects extends CommonTestActions  {

	public CommonPageObjects(WebDriver driver,ExtentTest reportsLogger) {
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}


}
