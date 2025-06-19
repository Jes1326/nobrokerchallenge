package com.capgemini.tests;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;	
import org.testng.Reporter;
import org.testng.annotations.*;

import com.capgemini.driversetup.SetupDriver;
import com.capgemini.pages.HomePage;
import com.capgemini.pages.Inter_City;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.PropertyReader;
import com.capgemini.utils.Screenshots;

/*
 * Created By : Jeswanth.P.G
 * Reviewed By : Richa Singh
 * Test scenario: Verifying that a user can book an intercity move and navigate to the payment summary page.
*/

public class NB_PM_02 extends BaseReport {

	WebDriverWait wait;
	String baseURL;
	HomePage HP;
	Packer_N_Movers PM;
	Inter_City ICI;
	String excelPath;
	String SC_Path;
	String propertyPath;
	
	@BeforeClass
	public void setUpClass() {
		excelPath = "src/test/resources/ExcelData/TestData.xlsx";
	    propertyPath = "src/test/resources/PropertyData/Login.property";
		baseURL = "https://www.nobroker.in/";
		driver = SetupDriver.getDriver("chrome");
		driver.navigate().to(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parentTest = extent.createTest(getClass().getSimpleName());
		
		HP = new HomePage(driver);
		PM = new Packer_N_Movers(driver);
		ICI = new Inter_City(driver);
	}
	
	@Test
	public void LoginIn() throws Exception {
	    // Read phone number from Property File(sheet: login, 8919902900)
		String phonenum = PropertyReader.getPropertyData(propertyPath,"login");
		
	    // Wait for the login link to be clickable and click it
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getlogin_link())).click();
	    Reporter.log("Clicked on Login link", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "LoginClicked");
	    generateReportWithScreenshot("Click on login link", SC_Path);

	    // Wait for the phone number input field to be visible and enter the phone number
	    wait.until(ExpectedConditions.visibilityOf(HP.getphn_num_txt())).sendKeys(phonenum);
	    Reporter.log("Entered Phone number: " + phonenum, true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PhonenumEntered");
	    generateReportWithScreenshot("Enter phone num", SC_Path);

	    // Wait for OTP manually (30 seconds pause)
	    Thread.sleep(30000); 

	    // Click the submit button after OTP is entered
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getsubmit_btn())).click();
	    Reporter.log("Submitted", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "SubmitButtonClicked");
	    generateReportWithScreenshot("submit", SC_Path);

	    // Validate the profile name after login
	    WebElement profileElement = HP.getProfile();
	    String ActualName = profileElement.getText();

	    // Check if the profile name matches the expected value
	    if (ActualName.equals("Jeswanth")) {
	        test.pass("Profile name is correct: " + ActualName);
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Profile name mismatch. Expected: Jeswanth, Found: " + ActualName);
	        Assert.assertTrue(false);
	    }
	}


	@Test(dependsOnMethods = "LoginIn")
	private void Between_Cities() throws Exception {

	    // Click on the menu icon
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
	    Reporter.log("Menu Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
	    generateReportWithScreenshot("Clicked on Menu", SC_Path);

	    // Verify that the 'Packers & Movers' button is visible
	    WebElement packers_btn = HP.getpackers_btn();
	    if (packers_btn.isDisplayed()) {
	        test.pass("Packers & Movers button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Packers & Movers button is not visible.");
	        Assert.fail("Packers & Movers button not visible");
	    }

	    // Click on 'Packers & Movers'
	    wait.until(ExpectedConditions.elementToBeClickable(packers_btn)).click();
	    Reporter.log("PackernMovers Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PackernMoversClicked");
	    generateReportWithScreenshot("Clicked on PackersnMovers", SC_Path);

	    // Verify that the 'Between Cities' option is visible
	    WebElement Between_Cities_btn = PM.getBetweenCities();
	    if (Between_Cities_btn.isDisplayed()) {
	        test.pass("Between Cities option is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Between Cities option is not visible.");
	        Assert.fail("Between Cities option is not visible");
	    }

	 // Click on 'Between Cities'
	    if (Between_Cities_btn.isDisplayed() && Between_Cities_btn.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(Between_Cities_btn)).click();
	        Reporter.log("Between Cities Clicked", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "BetweenCitiesClicked");
	        generateReportWithScreenshot("Clicked on Between Cities", SC_Path);
	        test.pass("Clicked on 'Between Cities' button.");
	    } else {
	        test.fail("'Between Cities' button is not clickable.");
	        Assert.fail("'Between Cities' button is not clickable.");
	    }

	    // Select a date field
	    WebElement dateField = PM.getDate_txt();
	    if (dateField.isDisplayed() && dateField.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
	        Reporter.log("Clicked on Date field", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "DateField");
	        generateReportWithScreenshot("Select Date", SC_Path);
	        test.pass("Clicked on Date field.");
	    } else {
	        test.fail("Date field is not clickable.");
	        Assert.fail("Date field is not clickable.");
	    }

	    // Select a date
	    WebElement date = PM.getDate();
	    if (date.isDisplayed() && date.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(date)).click();
	        Reporter.log("Selected a date", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectDate");
	        generateReportWithScreenshot("Selected Date", SC_Path);
	        test.pass("Date selected.");
	    } else {
	        test.fail("Date is not selectable.");
	        Assert.fail("Date is not selectable.");
	    }

	    // Verify that the 'Price' button is visible
	    WebElement Price_btn = PM.getPrice_withinCity();
	    if (Price_btn.isDisplayed()) {
	        test.pass("Price button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Price button is not visible.");
	        Assert.fail("Price button is not visible");
	    }

	    // Click on 'Price within City'
	    if (Price_btn.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(Price_btn)).click();
	        Reporter.log("Clicked on 'Price within City'", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "PriceWithinCity");
	        generateReportWithScreenshot("Click on Price within City", SC_Path);
	        test.pass("Clicked on 'Price within City'.");
	    } else {
	        test.fail("'Price within City' button is not clickable.");
	        Assert.fail("'Price within City' button is not clickable.");
	    }

	    // Select 'Living Room' category
	    WebElement livingRoom = ICI.getLivingRoom();
	    if (livingRoom.isDisplayed() && livingRoom.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(livingRoom)).click();
	        Reporter.log("Clicked on 'Living Room'", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "LivingRoom");
	        generateReportWithScreenshot("Select Living Room", SC_Path);
	        test.pass("Living Room selected.");
	    } else {
	        test.fail("Living Room category is not clickable.");
	        Assert.fail("Living Room category is not clickable.");
	    }

	    // Select 'Sofa'
	    WebElement sofa = ICI.getSofa();
	    if (sofa.isDisplayed() && sofa.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(sofa)).click();
	        Reporter.log("Clicked on 'Sofa'", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Sofa");
	        generateReportWithScreenshot("Select Sofa", SC_Path);
	        test.pass("Sofa selected.");
	    } else {
	        test.fail("Sofa is not clickable.");
	        Assert.fail("Sofa is not clickable.");
	    }

	    // Select '2 Seater Sofa'
	    WebElement twoSeater = ICI.get2Seater();
	    if (twoSeater.isDisplayed() && twoSeater.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(twoSeater)).click();
	        Reporter.log("Clicked on '2 Seater Sofa'", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "2Seater");
	        generateReportWithScreenshot("Select 2Seater Sofa", SC_Path);
	        test.pass("2 Seater Sofa selected.");
	    } else {
	        test.fail("2 Seater Sofa is not clickable.");
	        Assert.fail("2 Seater Sofa is not clickable.");
	    }

	    // Verify that the 'Continue' button is visible
	    WebElement Continue_btn = ICI.getContinue();
	    if (Continue_btn.isDisplayed()) {
	        test.pass("Continue button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Continue button is not visible.");
	        Assert.fail("Continue button is not visible");
	    }

	    // Click on 'Continue'
	    if (Continue_btn.isEnabled()) {
	        wait.until(ExpectedConditions.elementToBeClickable(Continue_btn)).click();
	        Reporter.log("Clicked on 'Continue'", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Continue");
	        generateReportWithScreenshot("Select Continue", SC_Path);
	        test.pass("Continue button clicked.");
	    } else {
	        test.fail("Continue button is not clickable.");
	        Assert.fail("Continue button is not clickable.");
	    }
	}

	
	@AfterClass(alwaysRun = true)
	public void tearDownMethod() {
	    if (driver != null) {
	        driver.close();
	        Reporter.log("Browser closed after test method", true);
	    }
	}


}
