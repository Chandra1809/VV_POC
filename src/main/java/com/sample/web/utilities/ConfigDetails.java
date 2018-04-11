package com.sample.web.utilities;


/**
 * 
 * @author CIGNITI
 *
 */

public class ConfigDetails {


	private String testSuiteName;
	private String browser;
	private String appURL;

	
	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getAppUrl() {
		return appURL;
	}

	public void setAppUrl(String appURL) {
		this.appURL = appURL; 
	}

}

