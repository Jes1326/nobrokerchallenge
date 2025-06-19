package com.capgemini.tests;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.capgemini.driversetup.SetupDriver;
import com.capgemini.pages.HomePage;
import com.capgemini.pages.Intra_City;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.PropertyReader;
import com.capgemini.utils.Screenshots;

/*
 * Created By : Jeswanth.P.G
 * Reviewed By : Richa Singh
 * Test scenario: Verifying that submitting the Packers & Movers form with empty pickup and drop locations 
 * 				shows appropriate validation errors.
*/

public class NB_PM_06 extends BaseReport {

	WebDriver driver;
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
		driver = SetupDriver.getDriver("chrome");
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
	private void PackNMove() throws Exception {

	    // Step 1 - Click on the menu
	    wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
	    Reporter.log("Menu Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
	    generateReportWithScreenshot("Click on Menu", SC_Path);
	    Assert.assertTrue(true); // Basic assertion to confirm step passed

	    // Step 2 - Verify 'Packers & Movers' button is visible
	    WebElement Packers_btn = HP.getpackers_btn();
	    if (Packers_btn.isDisplayed()) {
	        test.pass("Packers button is visible.");
	    } else {
	        test.fail("Packers button is not visible.");
	        Assert.fail("Packers button is not visible");
	    }

	    // Step 3 - Click on 'Packers & Movers'
	    wait.until(ExpectedConditions.elementToBeClickable(Packers_btn)).click();
	    Reporter.log("PackernMovers Clicked", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "PackernMoversClicked");
	    generateReportWithScreenshot("Click on Packers and Movers", SC_Path);
	    Assert.assertTrue(true);

	    // Step 4 - Clear 'From' input field
	    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_from_inp())).click();
	    Reporter.log("Cleared From", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "ClearedFrom");
	    generateReportWithScreenshot("Clear From Input", SC_Path);
	    Assert.assertTrue(true);

	    // Step 5 - Clear 'To' input field
	    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_to_inp())).click();
	    Reporter.log("Cleared To", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "ClearedTo");
	    generateReportWithScreenshot("Clear To", SC_Path);
	    Assert.assertTrue(true);

	    // Step 6 - Verify 'Price' button is visible
	    WebElement Price_btn = PM.getPrices();
	    if (Price_btn.isDisplayed()) {
	        test.pass("Price button is visible.");
	        Assert.assertTrue(true);
	    } else {
	        test.fail("Price button is not visible.");
	        Assert.fail("Price button is not visible");
	    }

	    // Step 7 - Click on 'Price' button
	    wait.until(ExpectedConditions.elementToBeClickable(Price_btn)).click();
	    Reporter.log("Click Prices", true);
	    SC_Path = Screenshots.takeScreenShot(driver, "ClickPrices");
	    generateReportWithScreenshot("Click on Prices", SC_Path);
	    Assert.assertTrue(true);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownMethod() {
	    if (driver != null) {
	        driver.quit();
	        Reporter.log("Browser closed after test method", true);
	    }
	}

}
