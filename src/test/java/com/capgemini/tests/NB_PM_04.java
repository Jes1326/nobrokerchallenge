package com.capgemini.tests;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.capgemini.driversetup.SetupDriver;
import com.capgemini.pages.HomePage;
import com.capgemini.pages.MyBookings;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.PropertyReader;
import com.capgemini.utils.Screenshots;

/*
 * Created By : Jeswanth.P.G
 * Reviewed By : Richa Singh
 * Test scenario: Verifying that a user can navigate back to the Home page from the My Bookings section.
*/

public class NB_PM_04 extends BaseReport {

    WebDriverWait wait;
    String baseURL;
    HomePage HP;
    MyBookings MB;
    Packer_N_Movers PM;
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
        MB = new MyBookings(driver);
        PM = new Packer_N_Movers(driver);
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
    private void BacToHomeFromMB() throws Exception {

        // Step 1 - Click on the menu
        wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
        Reporter.log("Menu Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
        generateReportWithScreenshot("Click on menu", SC_Path);

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
        Reporter.log("Packers and movers Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "PackersnMoversClicked");
        generateReportWithScreenshot("Packernmovers clicked", SC_Path);

        // Store the current window handle to return later
        String PM_handle = driver.getWindowHandle();

        // Step 4 - Verify 'My Bookings' button is visible
        WebElement MyBooking_btn = PM.getMyBookings_btn();
        if (MyBooking_btn.isDisplayed()) {
            test.pass("MyBookings button is visible.");
        } else {
            test.fail("MyBookings button is not visible.");
            Assert.fail("MyBookings button is not visible");
        }

        // Step 5 - Click on 'My Bookings'
        wait.until(ExpectedConditions.elementToBeClickable(MyBooking_btn)).click();
        Reporter.log("Mybookings Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MyBookingsClicked");
        generateReportWithScreenshot("Click on mybookings", SC_Path);

        // Step 6 - Switch to the newly opened window
        wait.until(d -> driver.getWindowHandles().size() > 1);
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(PM_handle)) {
                driver.switchTo().window(win);
                break;
            }
        }
        SC_Path = Screenshots.takeScreenShot(driver, "SwitchedToNewWindow");
        Reporter.log("Switched to new window", true);
        generateReportWithScreenshot("Switch to new window", SC_Path);

        // Step 7 - Verify 'Home' button is visible
        WebElement home_btn = MB.gethome_btn();
        if (home_btn.isDisplayed()) {
            test.pass("Home button is visible.");
        } else {
            test.fail("Home button is not visible.");
            Assert.fail("Home button is not visible");
        }

        // Step 8 - Click on 'Home' button to return
        wait.until(ExpectedConditions.elementToBeClickable(home_btn)).click();
        Reporter.log("Home Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "HomeClicked");
        generateReportWithScreenshot("Click on home button to go back", SC_Path);
    }

    
    @AfterClass()
    public void tearDownMethod() {
        if (driver != null) {
            driver.close();
            Reporter.log("Browser closed after test method", true);
        }
    }

}
