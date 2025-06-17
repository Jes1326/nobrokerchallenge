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
import com.capgemini.pages.Intra_City;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.ExcelReader;
import com.capgemini.utils.Screenshots;

public class NB_PM_01 extends BaseReport {
	WebDriverWait wait;
	String baseURL;
	HomePage HP;
	Packer_N_Movers PM;
	Intra_City IC;
	String excelPath;
	String SC_Path;
	
	@BeforeClass
	public void setUpClass() {
		excelPath = "src/test/resources/ExcelData/TestData.xlsx";
		baseURL = "https://www.nobroker.in/";
		driver = SetupDriver.getDriver("edge");
		driver.navigate().to(baseURL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parentTest = extent.createTest(getClass().getSimpleName());
		
		HP = new HomePage(driver);
		PM = new Packer_N_Movers(driver);
		IC = new Intra_City(driver);
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
		
		WebElement profileElement = HP.getProfile();
		String ActualName = profileElement.getText();
		if(profileElement.getText().equals("Jeswanth")) {
			test.pass("Profile name is correct: " + ActualName);
			Assert.assertTrue(true);
		}
		else {
			test.fail("Profile name mismatch. Expected: Jeswanth, Found: " + ActualName);
			Assert.assertTrue(false);
		}
	}
		
	@Test(dependsOnMethods = "LoginIn")
	private void packersNmovers() throws Exception {
		String from = ExcelReader.getData(excelPath, "Within_City", 0, 1);
		String to = ExcelReader.getData(excelPath, "Within_City", 1, 1);
		
		wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
		Reporter.log("Menu Clicked", true);
		SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
		generateReportWithScreenshot("Click on Menu", SC_Path);
		
		WebElement packers_btn = HP.getpackers_btn();
		if (packers_btn.isDisplayed()) {
		    test.pass("Packers & Movers button is visible.");
		    Assert.assertTrue(true);
		} else {
		    test.fail("Packers & Movers button is not visible.");
		    Assert.fail("Packers & Movers button not visible");
		}

		
		wait.until(ExpectedConditions.elementToBeClickable(HP.getpackers_btn())).click();
		Reporter.log("PackernMovers Clicked", true);
		SC_Path = Screenshots.takeScreenShot(driver, "PackernMoversClicked");
		generateReportWithScreenshot("Click on Packers and Movers", SC_Path);
		
		// Verify that the 'Clear' button for the 'From' input field is visible
		WebElement clear_btn = PM.getclear_from_inp();
		if (clear_btn.isDisplayed()) {
		    test.pass("'Clear' button for 'From' input is visible.");
		    Assert.assertTrue(true);
		} else {
		    test.fail("'Clear' button for 'From' input is not visible.");
		    Assert.fail("From input not visible");
		}

		
		wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_from_inp())).click();
		Reporter.log("Cleared From", true);
		SC_Path = Screenshots.takeScreenShot(driver, "ClearedFrom");
		generateReportWithScreenshot("Clear From Input", SC_Path);
		
		wait.until(ExpectedConditions.visibilityOf(PM.getShifting_From())).sendKeys(from);
		Reporter.log("Entered From", true);
		SC_Path = Screenshots.takeScreenShot(driver, "EnteredFrom");
		generateReportWithScreenshot("Enter From", SC_Path);
		
		// Verify that the 'From' input field contains the expected value
		String from_txt = PM.getShifting_From().getAttribute("value");
		String expectedFrom = "btm";
		if (from_txt.equalsIgnoreCase(expectedFrom)) {
		    test.pass("'From' input value is correct: " + from_txt);
		    Assert.assertTrue(true);
		} else {
		    test.fail("'From' input value mismatch. Expected: " + expectedFrom + ", Found: " + from_txt);
		    Assert.fail("From input value mismatch");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(PM.getFromSelector())).click();
		Reporter.log("Select From INPUT", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectFrom");
		generateReportWithScreenshot("Select From Input", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_to_inp())).click();
		Reporter.log("Cleared To", true);
		SC_Path = Screenshots.takeScreenShot(driver, "ClearedTo");
		generateReportWithScreenshot("Clear To", SC_Path);
		
		wait.until(ExpectedConditions.visibilityOf(PM.getShifting_To())).sendKeys(to);
		Reporter.log("Entered To", true);
		SC_Path = Screenshots.takeScreenShot(driver, "EnteredTo");
		generateReportWithScreenshot("Enter To", SC_Path);
		
		// Verify that the 'To' input field contains the expected value
		String to_txt = PM.getShifting_To().getAttribute("value");
		String expectedTo = "HSR";
		if (to_txt.equalsIgnoreCase(expectedTo)) {
		    test.pass("'To' input value is correct: " + to_txt);
		    Assert.assertTrue(true);
		} else {
		    test.fail("'To' input value mismatch. Expected: " + expectedTo + ", Found: " + to_txt);
		    Assert.fail("To input value mismatch");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(PM.getTo_Selector())).click();
		Reporter.log("Select To INPUT", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectTo");
		generateReportWithScreenshot("Select To Input", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(PM.getPrices())).click();
		Reporter.log("Click Prices", true);
		SC_Path = Screenshots.takeScreenShot(driver, "ClickPrices");
		generateReportWithScreenshot("Click on Prices", SC_Path);
			
		// Verify that the 'Living Room' category is visible on the page
		WebElement living_room = IC.getLivingRoom();
		if (living_room.isDisplayed()) {
		    test.pass("'Living Room' category is visible.");
		    Assert.assertTrue(true);
		} else {
		    test.fail("'Living Room' category is not visible.");
		    Assert.fail("Living Room category not visible");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getLivingRoom())).click();
		Reporter.log("Select Living Room", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectLivingRoom");
		generateReportWithScreenshot("Select LivingRoom", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getSofa())).click();
		Reporter.log("Select Sofa", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectSofa");
		generateReportWithScreenshot("Select Sofa", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.get1Seater())).click();
		Reporter.log("Select 1 Seater", true);
		SC_Path = Screenshots.takeScreenShot(driver, "Select1Seater");
		generateReportWithScreenshot("Select 1Seater", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getContinue())).click();
		Reporter.log("Click Continue", true);
		SC_Path = Screenshots.takeScreenShot(driver, "Continue");
		generateReportWithScreenshot("Click on Continue", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getDate())).click();
		Reporter.log("Select Date", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectDate");
		generateReportWithScreenshot("Select Date", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getTime())).click();
		Reporter.log("Select Time", true);
		SC_Path = Screenshots.takeScreenShot(driver, "SelectTime");
		generateReportWithScreenshot("Select Time", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getConfirm())).click();
		Reporter.log("Confirm Selection", true);
		SC_Path = Screenshots.takeScreenShot(driver, "Confirm");
		generateReportWithScreenshot("Click on Confirm", SC_Path);
		
		// Verify that the confirmation element (e.g., 'Done' button or message) is visible
		WebElement done = IC.getInventory();
		if (done.isDisplayed()) {
		    test.pass("Confirmation element is visible.");
		    Assert.assertTrue(true);
		} else {
		    test.fail("Confirmation element is not visible.");
		    Assert.fail("Confirmation not available");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getInventory())).click();
		Reporter.log("Select Inventory", true);
		SC_Path = Screenshots.takeScreenShot(driver, "Inventory");
		generateReportWithScreenshot("Click on Inventory", SC_Path);
		
		wait.until(ExpectedConditions.elementToBeClickable(IC.getDone())).click();
		Reporter.log("Click Done", true);
		SC_Path = Screenshots.takeScreenShot(driver, "Done");
		generateReportWithScreenshot("Click Done", SC_Path);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownMethod() {
	    if (driver != null) {
	        driver.close();
	        Reporter.log("Browser closed after test method", true);
	    }
	}

}
