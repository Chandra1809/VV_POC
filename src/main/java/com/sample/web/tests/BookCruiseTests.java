package com.sample.web.tests;


import java.util.HashMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.web.base.TestBase;
import com.sample.web.pages.BookCruisePage;


public class BookCruiseTests extends TestBase {

	// Scenario to test the booking a Cruise Flow
	@DataProvider
	public Object[][] getData_test_Book_Cruise() {
		return xlsReader.getTestData("TestData","test_Book_Cruise");
	}

	@Test(dataProvider = "getData_test_Book_Cruise",priority=1)
	public void test_Book_Cruise(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Book_Cruise");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BookCruisePage bookCruisePage = new BookCruisePage(getDriver(),getCurrentLogger());
		bookCruisePage.selectDate();
		bookCruisePage.selectAdults();
		bookCruisePage.selectChildren();
		bookCruisePage.selectFrom();
		bookCruisePage.clickDestination();
		bookCruisePage.clickbtnBookTrip();
		bookCruisePage.switchWindow();
		bookCruisePage.SelectReturnDate();
		bookCruisePage.clickVehilces();
		bookCruisePage.clickCarIcon();		
		bookCruisePage.selectVehicle();
		bookCruisePage.selectOutwardSail();
		bookCruisePage.selectreturnSail();
		bookCruisePage.clickBtnBookTrip();
		
	}

	
	}