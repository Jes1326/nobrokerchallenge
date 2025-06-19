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
import com.capgemini.parameters.PropertyReader;
import com.capgemini.utils.Screenshots;

/*
 * Created By : Jeswanth.P.G
 * Reviewed By : Richa Singh
 * Test scenario: Verifying that a user can successfully book a move within the same city and reach the payment summary page.
*/

public class NB_PM_01 extends BaseReport{
	WebDriverWait wait;
	String baseURL;
	HomePage HP;
	Packer_N_Movers PM;
	Intra_City IC;
	String excelPath;
	String SC_Path;
	String propertyPath;
	
	@BeforeClass
	public void setUpClass() {

		excelPath = "src/test/resources/ExcelData/TestData.xlsx";
		propertyPath = "src/test/resources/PropertyData/Login.property";
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
	private void packersNmovers() throws Exception {
	    // Read 'From' and 'To' locations from Excel
	    String from = ExcelReader.getData(excelPath, "Within_City", 0, 1);
	    String to = ExcelReader.getData(excelPath, "Within_City", 1, 1);
	    
	    // Verify visibility of 'Menu' button
	    WebElement menu_btn = HP.getMenu();
	    if (menu_btn.isDisplayed()) {
	        test.pass("Menu button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Menu button is not visible.");
	        Assert.fail("Menu button not visible");
	    }

	    // Open the menu
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
	    Reporter.log("Menu Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
	    generateReportWithScreenshot("Click on Menu", SC_Path);

	    // Verify visibility of 'Packers & Movers' button
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
	    generateReportWithScreenshot("Click on Packers and Movers", SC_Path);

	    // Verify and clear 'From' input field
	    WebElement clear_btn = PM.getclear_from_inp();
	    if (clear_btn.isDisplayed()) {
	        test.pass("'Clear' button for 'From' input is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("'Clear' button for 'From' input is not visible.");
	        Assert.fail("From input not visible");
	    }

	    // Clear 'From' input
	    wait.until(ExpectedConditions.elementToBeClickable(clear_btn)).click();
	    Reporter.log("Cleared From", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "ClearedFrom");
	    generateReportWithScreenshot("Clear From Input", SC_Path);

	    // Verify cleared 'From' input
	    WebElement from_btn = PM.getShifting_From();
	    if (from_btn.isDisplayed()) {
	        test.pass("From is cleared");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("From input is not cleared.");
	        Assert.fail("From is not cleared");
	    }

	    // Enter 'From' location
	    wait.until(ExpectedConditions.visibilityOf(PM.getShifting_From())).sendKeys(from);
	    Reporter.log("Entered From", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "EnteredFrom");
	    generateReportWithScreenshot("Enter From", SC_Path);

	    // Validate 'From' input value
	    String from_txt = PM.getShifting_From().getAttribute("value");
	    String expectedFrom = "btm";
	    if (from_txt.equalsIgnoreCase(expectedFrom)) {
	        test.pass("'From' input value is correct: " + from_txt);
	        Assert.assertTrue(true);
	    } else {
	        test.fail("'From' input value mismatch. Expected: " + expectedFrom + ", Found: " + from_txt);
	        Assert.fail("From input value mismatch");
	    }

	    // Select 'From' suggestion
	    WebElement fromSelector = PM.getFromSelector();
	    if (fromSelector.isDisplayed() && fromSelector.isEnabled()) {
	        fromSelector.click();
	        Reporter.log("Select From INPUT", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectFrom");
	        generateReportWithScreenshot("Select From Input", SC_Path);
	        test.pass("From suggestion selected.");
	    } else {
	        test.fail("From suggestion not clickable.");
	        Assert.fail("From suggestion not clickable.");
	    }

	    // Clear and enter 'To' location
	    WebElement clearTo = PM.getclear_to_inp();
	    if (clearTo.isDisplayed() && clearTo.isEnabled()) {
	        clearTo.click();
	        Reporter.log("Cleared To", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "ClearedTo");
	        generateReportWithScreenshot("Clear To", SC_Path);
	        test.pass("To input cleared.");
	    } else {
	        test.fail("To input clear button not clickable.");
	        Assert.fail("To input clear failed.");
	    }

	    wait.until(ExpectedConditions.visibilityOf(PM.getShifting_To())).sendKeys(to);
	    Reporter.log("Entered To", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "EnteredTo");
	    generateReportWithScreenshot("Enter To", SC_Path);

	    // Validate 'To' input value
	    String to_txt = PM.getShifting_To().getAttribute("value");
	    String expectedTo = "HSR";
	    if (to_txt.equalsIgnoreCase(expectedTo)) {
	        test.pass("'To' input value is correct: " + to_txt);
	        Assert.assertTrue(true);
	    } else {
	        test.fail("'To' input value mismatch. Expected: " + expectedTo + ", Found: " + to_txt);
	        Assert.fail("To input value mismatch");
	    }

	    // Select 'To' suggestion
	    WebElement toSelector = PM.getTo_Selector();
	    if (toSelector.isDisplayed() && toSelector.isEnabled()) {
	        toSelector.click();
	        Reporter.log("Select To INPUT", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectTo");
	        generateReportWithScreenshot("Select To Input", SC_Path);
	        test.pass("To suggestion selected.");
	    } else {
	        test.fail("To suggestion not clickable.");
	        Assert.fail("To suggestion not clickable.");
	    }

	    // Click on 'Prices'
	    WebElement prices = PM.getPrices();
	    if (prices.isDisplayed() && prices.isEnabled()) {
	        prices.click();
	        Reporter.log("Click Prices", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "ClickPrices");
	        generateReportWithScreenshot("Click on Prices", SC_Path);
	        test.pass("Prices button clicked.");
	    } else {
	        test.fail("Prices button not clickable.");
	        Assert.fail("Prices button click failed.");
	    }

	    // Verify and select 'Living Room' category
	    WebElement living_room = IC.getLivingRoom();
	    if (living_room.isDisplayed()) {
	        test.pass("'Living Room' category is visible.");
	        living_room.click();
	        Reporter.log("Select Living Room", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectLivingRoom");
	        generateReportWithScreenshot("Select LivingRoom", SC_Path);
	    } else {
	        test.fail("'Living Room' category is not visible.");
	        Assert.fail("Living Room category not visible");
	    }

	    // Select items in the living room
	    WebElement sofa = IC.getSofa();
	    if (sofa.isDisplayed() && sofa.isEnabled()) {
	        sofa.click();
	        Reporter.log("Select Sofa", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectSofa");
	        generateReportWithScreenshot("Select Sofa", SC_Path);
	        test.pass("Sofa selected.");
	    } else {
	        test.fail("Sofa not clickable.");
	        Assert.fail("Sofa selection failed.");
	    }

	    WebElement oneSeater = IC.get1Seater();
	    if (oneSeater.isDisplayed() && oneSeater.isEnabled()) {
	        oneSeater.click();
	        Reporter.log("Select 1 Seater", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Select1Seater");
	        generateReportWithScreenshot("Select 1Seater", SC_Path);
	        test.pass("1 Seater selected.");
	    } else {
	        test.fail("1 Seater not clickable.");
	        Assert.fail("1 Seater selection failed.");
	    }

	    // Continue to next step
	    WebElement continueBtn = IC.getContinue();
	    if (continueBtn.isDisplayed() && continueBtn.isEnabled()) {
	        continueBtn.click();
	        Reporter.log("Click Continue", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Continue");
	        generateReportWithScreenshot("Click on Continue", SC_Path);
	        test.pass("Continue button clicked.");
	    } else {
	        test.fail("Continue button not clickable.");
	        Assert.fail("Continue button click failed.");
	    }

	    // Select date and time
	    WebElement date = IC.getDate();
	    if (date.isDisplayed() && date.isEnabled()) {
	        date.click();
	        Reporter.log("Select Date", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectDate");
	        generateReportWithScreenshot("Select Date", SC_Path);
	        test.pass("Date selected.");
	    } else {
	        test.fail("Date not clickable.");
	        Assert.fail("Date selection failed.");
	    }

	    WebElement time = IC.getTime();
	    if (time.isDisplayed() && time.isEnabled()) {
	        time.click();
	        Reporter.log("Select Time", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "SelectTime");
	        generateReportWithScreenshot("Select Time", SC_Path);
	        test.pass("Time selected.");
	    } else {
	        test.fail("Time not clickable.");
	        Assert.fail("Time selection failed.");
	    }

	    // Confirm selection
	    WebElement confirm = IC.getConfirm();
	    if (confirm.isDisplayed() && confirm.isEnabled()) {
	        confirm.click();
	        Reporter.log("Confirm Selection", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Confirm");
	        generateReportWithScreenshot("Click on Confirm", SC_Path);
	        test.pass("Selection confirmed.");
	    } else {
	        test.fail("Confirm button not clickable.");
	        Assert.fail("Confirm selection failed.");
	    }

	    // Verify confirmation element
	    WebElement done = IC.getInventory();
	    if (done.isDisplayed()) {
	        test.pass("Confirmation element is visible.");
	        done.click();
	        Reporter.log("Select Inventory", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Inventory");
	        generateReportWithScreenshot("Click on Inventory", SC_Path);
	    } else {
	        test.fail("Confirmation element is not visible.");
	        Assert.fail("Confirmation not available");
	    }

	    // Final step: Done
	    WebElement doneBtn = IC.getDone();
	    if (doneBtn.isDisplayed() && doneBtn.isEnabled()) {
	        doneBtn.click();
	        Reporter.log("Click Done", true);
	        SC_Path = Screenshots.takeScreenShot(driver, "Done");
	        generateReportWithScreenshot("Click Done", SC_Path);
	        test.pass("Done button clicked.");
	    } else {
	        test.fail("Done button not clickable.");
	        Assert.fail("Done button click failed.");
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
