package com.sample.web.pages;



import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BookCruisePage extends CommonPageObjects  {

	private static Logger logger = LoggerFactory.getLogger(BookCruisePage.class);

	@FindBy(xpath="//table[@class='calendarBody']//*[@date-iso='2018-11-06']")
	public WebElement dtSelectDate;

	@FindBy(xpath="//input[@id='passengers-adults']/following-sibling::button[@class='increase']")
	public WebElement selectAdults;

	@FindBy(xpath="//input[@id='passengers-juniors']/following-sibling::button[@class='increase']")
	public WebElement selectChildren;

	@FindBy(xpath="//label[contains(@class,'destinationPortButton')]/span[text()='Stockholm']")
	public WebElement destination;

	@FindBy(xpath="//a[text()='Book your trip' and @class='searchButton']")
	public WebElement btnBookYourTrip;


	public BookCruisePage(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}


	public void selectDate() throws Exception{
		dtSelectDate.click();
		logger.info("Selected the Depature Date Successfully");
		reportsLogger.log(LogStatus.INFO, "Selected the Depature Date Successfully");
	}

	public void selectAdults() throws Exception{
		selectAdults.click();
		logger.info("Selected No.Of Adults Successfully");
		reportsLogger.log(LogStatus.INFO, "Selected No.Of Adults Successfully");
	}

	public void selectChildren() throws Exception{
		selectChildren.click();
		logger.info("Selected No.Of Children Successfully");
		reportsLogger.log(LogStatus.INFO, "Selected No.Of Children Successfully");
	}

	public void selectFrom() throws Exception{
		driver.findElement(By.xpath("//span[text()='Helsinki']/following-sibling::span[@class='icon']")).click();
		List <WebElement> options =driver.findElements(By.xpath("//*[contains(@class,'departurePortDropdown open')]/*[@class='list']/child::li"));
		for(WebElement ele:options) {
			String strFrom = ele.getText();
			if(strFrom.equalsIgnoreCase("Riga")) {
				ele.click();
				logger.info("Selected " + strFrom + " Successfully From Dropdown");
				reportsLogger.log(LogStatus.INFO, "Selected" + strFrom + "Successfully From Dropdown");
				break;
			}
		}

	}

	public void clickDestination() throws Exception{
		destination.click();
		logger.info("Selected Destination as " + "'" + "Stockholm" + "'" + "Successfully");
		reportsLogger.log(LogStatus.INFO, "Selected Destination as " + "'" + "Stockholm" + "'" + "Successfully");
	}

	public void clickbtnBookTrip() throws Exception{
		btnBookYourTrip.click();
		logger.info("Successfully Clicked on Book Your Trip Button");
		reportsLogger.log(LogStatus.INFO, "Successfully Clicked on Book Your Trip Button");
	}


	public void switchWindow() throws Exception{
		String mainWindow=driver.getWindowHandle();
		Set<String> windows=driver.getWindowHandles();
		Iterator <String> itr = windows.iterator();
		while(itr.hasNext()) {
			String childWindow=itr.next();
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}


	public void SelectReturnDate() throws Exception{
		Thread.sleep(25000);
		driver.findElement(By.xpath("//table[@class='dayReturns']//*[@data-ship-code='ROMANTIKA']/td[contains(@data-datetime,'2018-11-10')]")).click();
		logger.info("Successfully Selected Return Date");
		reportsLogger.log(LogStatus.INFO, "Successfully Selected Return Date");
	}


	public void clickVehilces() throws Exception{
		driver.findElement(By.xpath("//div[@class='siteContent booking']//span[@class='vehicles']")).click();
		logger.info("Successfully Clicked on Vehicles");
		reportsLogger.log(LogStatus.INFO, "Successfully Clicked on Vehicles");
	}

	public void clickCarIcon() throws Exception{
		Thread.sleep(25000);
		driver.findElement(By.xpath("//div[@class='ddTitle']/*[@class='carIcon vehicles-white-novehicle']")).click();
		logger.info("Successfully Clicked on Car Icon");
		reportsLogger.log(LogStatus.INFO, "Successfully Clicked on Car Icon	");
	}

	public void selectVehicle() throws Exception{
		driver.findElement(By.xpath("//table[@class='vehicleDropDown']//td[@class='description']/*[text()='Motorcycle']")).click();
		logger.info("Successfully Selected MotoCycle as Vehicle");
		reportsLogger.log(LogStatus.INFO, "Successfully Selected MotoCycle as Vehicle");
	}

	public void selectOutwardSail() throws Exception{
		driver.findElement(By.xpath("//table[contains(@class,'sailOptions')]//td[contains(@class,'outwardSail')]//button[@value='SUIT']/span[contains(text(),'cabin')]")).click();
		logger.info("Successfully Selected Outward Sail Option");
		reportsLogger.log(LogStatus.INFO, "Successfully Selected Outward Sail Option");
	}
	
	public void selectreturnSail() throws Exception{
		driver.findElement(By.xpath("//table[contains(@class,'sailOptions')]//td[contains(@class,'returnSail')]//button[@value='SUIT']/span[contains(text(),'cabin')]")).click();
		logger.info("Successfully Selected Return Sail Option");
		reportsLogger.log(LogStatus.INFO, "Successfully Selected Return Sail Option");
	}
	
	
	public void clickBtnBookTrip() throws Exception{
		driver.findElement(By.xpath("//button[@class='pay' and text()='Book the trip']")).click();
		logger.info("Successfully Clicked on Book the Trip Button");
		reportsLogger.log(LogStatus.INFO, "Successfully Clicked on Book the Trip Button");
	}
}
