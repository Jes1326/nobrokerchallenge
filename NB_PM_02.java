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
import com.capgemini.parameters.ExcelReader;
import com.capgemini.utils.Screenshots;

public class NB_PM_02 extends BaseReport {

	WebDriverWait wait;
	String baseURL;
	HomePage HP;
	Packer_N_Movers PM;
	Inter_City ICI;
	String excelPath;
	String SC_Path;
	
	@BeforeClass
	public void setUpClass() {
		excelPath = "src/test/resources/ExcelData/TestData.xlsx";
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
	    String phonenum = ExcelReader.getData(excelPath, "Login", 0, 1);

	    wait.until(ExpectedConditions.elementToBeClickable(HP.getlogin_link())).click();
	    Reporter.log("Clicked on Login link", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "LoginClicked");
	    generateReportWithScreenshot("Click on login link", SC_Path);

	    wait.until(ExpectedConditions.visibilityOf(HP.getphn_num_txt())).sendKeys(phonenum);
	    Reporter.log("Entered Phone number: " + phonenum, true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PhonenumEntered");
	    generateReportWithScreenshot("Enter phone num", SC_Path);

	    Thread.sleep(30000); // OTP wait
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getsubmit_btn())).click();
	    Reporter.log("Submitted", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "SubmitButtonClicked");
	    generateReportWithScreenshot("submit", SC_Path);

	    // Verify profile element is visible
	    WebElement profileElement = HP.getProfile();
	    if (profileElement.isDisplayed()) {
	        test.pass("Profile element is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Profile element is not visible.");
	        Assert.fail("Profile element is not displayed");
	    }
	}

	@Test(dependsOnMethods = "LoginIn")
	private void Between_Cities() throws Exception {

	    wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
	    Reporter.log("Menu Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
	    generateReportWithScreenshot("Clicked on Menu", SC_Path);

	    // Verify Packers & Movers button is visible
	    WebElement packers_btn = HP.getpackers_btn();
	    if (packers_btn.isDisplayed()) {
	        test.pass("Packers & Movers button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Packers & Movers button is not visible.");
	        Assert.fail("Packers & Movers button not visible");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(packers_btn)).click();
	    Reporter.log("PackernMovers Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PackernMoversClicked");
	    generateReportWithScreenshot("Clicked on PackersnMovers", SC_Path);

	    // Verify Between Cities option is visible
	    WebElement Between_Cities_btn = PM.getBetweenCities();
	    if (Between_Cities_btn.isDisplayed()) {
	        test.pass("Between Cities option is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Between Cities option is not visible.");
	        Assert.fail("Between Cities option is not visible");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(Between_Cities_btn)).click();
	    Reporter.log("Between Cities Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "BetweenCitiesClicked");
	    generateReportWithScreenshot("Clicked on Between Cities", SC_Path);

	    wait.until(ExpectedConditions.elementToBeClickable(PM.getDate_txt())).click();
	    Reporter.log("Clicked on Date field", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "DateField");
	    generateReportWithScreenshot("Select Date", SC_Path);

	    wait.until(ExpectedConditions.elementToBeClickable(PM.getDate())).click();
	    Reporter.log("Selected a date", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "SelectDate");
	    generateReportWithScreenshot("Selected Date", SC_Path);

	    // Verify Price button is visible
	    WebElement Price_btn = PM.getPrice_withinCity();
	    if (Price_btn.isDisplayed()) {
	        test.pass("Price button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Price button is not visible.");
	        Assert.fail("Price button is not visible");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(Price_btn)).click();
	    Reporter.log("Clicked on 'Price within City'", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PriceWithinCity");
	    generateReportWithScreenshot("Click on Price within City", SC_Path);

	    wait.until(ExpectedConditions.elementToBeClickable(ICI.getLivingRoom())).click();
	    Reporter.log("Clicked on 'Living Room'", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "LivingRoom");
	    generateReportWithScreenshot("Select Living Room", SC_Path);

	    wait.until(ExpectedConditions.elementToBeClickable(ICI.getSofa())).click();
	    Reporter.log("Clicked on 'Sofa'", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "Sofa");
	    generateReportWithScreenshot("Select Sofa", SC_Path);

	    wait.until(ExpectedConditions.elementToBeClickable(ICI.get2Seater())).click();
	    Reporter.log("Clicked on '2 Seater Sofa'", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "2Seater");
	    generateReportWithScreenshot("Select 2Seater Sofa", SC_Path);

	    // Verify Continue button is visible
	    WebElement Continue_btn = ICI.getContinue();
	    if (Continue_btn.isDisplayed()) {
	        test.pass("Continue button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Continue button is not visible.");
	        Assert.fail("Continue button is not visible");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(Continue_btn)).click();
	    Reporter.log("Clicked on 'Continue'", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "Continue");
	    generateReportWithScreenshot("Select Continue", SC_Path);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownMethod() {
	    if (driver != null) {
	        driver.close();
	        Reporter.log("Browser closed after test method", true);
	    }
	}


}
