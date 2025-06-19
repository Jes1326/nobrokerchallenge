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
import com.capgemini.pages.MoversOnly;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.ExcelReader;
import com.capgemini.parameters.PropertyReader;
import com.capgemini.utils.Screenshots;

/*
 * Created By : Jeswanth.P.G
 * Reviewed By : Richa Singh
 * Test scenario: Verifying that a user can book the "Movers Only" service and proceed to the payment summary.
*/

public class NB_PM_03 extends BaseReport {

    WebDriverWait wait;
    String baseURL;
    HomePage HP;
    Packer_N_Movers PM;
    MoversOnly MO;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        parentTest = extent.createTest(getClass().getSimpleName());

        HP = new HomePage(driver);
        PM = new Packer_N_Movers(driver);
        MO = new MoversOnly(driver);
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
    private void MoversOnly() throws Exception {
        // Read 'From' and 'To' locations from Excel
        String from = ExcelReader.getData(excelPath, "MoversOnly", 0, 1);
        String to = ExcelReader.getData(excelPath, "MoversOnly", 1, 1);

        // Open the menu
        wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
        Reporter.log("Menu opened", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MenuOpened");
        generateReportWithScreenshot("menu_opened", SC_Path);

        // Verify and click on 'Packers & Movers'
        WebElement packers_btn = HP.getpackers_btn();
        if (packers_btn.isDisplayed()) {
            test.pass("Packers & Movers button is visible.");
        } else {
            test.fail("Packers & Movers button is not visible.");
            Assert.fail("Packers & Movers button not visible");
        }

        wait.until(ExpectedConditions.elementToBeClickable(packers_btn)).click();
        Reporter.log("Clicked on Packers and Movers", true);
        SC_Path = Screenshots.takeScreenShot(driver, "PackersMoversClicked");
        generateReportWithScreenshot("packers_movers_clicked", SC_Path);

        // Verify and click on 'Movers Only' option
        WebElement Movers_btn = PM.getMovers();
        if (Movers_btn.isDisplayed()) {
            test.pass("Movers only option is visible.");
        } else {
            test.fail("Movers only option is not visible.");
            Assert.fail("Movers only option is not visible");
        }

     // Click on 'Movers'
        if (Movers_btn.isDisplayed() && Movers_btn.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(Movers_btn)).click();
            Reporter.log("Movers option selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "MoversSelected");
            generateReportWithScreenshot("movers_selected", SC_Path);
            test.pass("Movers option clicked.");
        } else {
            test.fail("Movers option is not clickable.");
            Assert.fail("Movers option is not clickable.");
        }

//                // Clear and enter 'From' location
//                if (PM.getclear_from_inp().isDisplayed() && PM.getclear_from_inp().isEnabled()) {
//                    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_from_inp())).click();
//                    Reporter.log("'From' input cleared", true);
//                    SC_Path = Screenshots.takeScreenShot(driver, "FromInputCleared");
//                    generateReportWithScreenshot("from_input_cleared", SC_Path);
//                    test.pass("'From' input cleared.");
//                } else {
//                    test.fail("'From' input clear button is not clickable.");
//                    Assert.fail("'From' input clear button is not clickable.");
//                }

        WebElement fromInput = PM.getShifting_From();
        if (fromInput.isDisplayed() && fromInput.isEnabled()) {
            wait.until(ExpectedConditions.visibilityOf(fromInput)).sendKeys(from);
            Reporter.log("'From' location entered", true);
            SC_Path = Screenshots.takeScreenShot(driver, "FromLocationEntered");
            generateReportWithScreenshot("from_location_entered", SC_Path);
            test.pass("'From' location entered.");
        } else {
            test.fail("'From' input field is not available.");
            Assert.fail("'From' input field is not available.");
        }

        WebElement fromSelect = PM.getFromMovers();
        if (fromSelect.isDisplayed() && fromSelect.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(fromSelect)).click();
            Reporter.log("'From' location selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "FromLocationSelected");
            generateReportWithScreenshot("from_location_selected", SC_Path);
            test.pass("'From' location selected.");
        } else {
            test.fail("'From' location selector is not clickable.");
            Assert.fail("'From' location selector is not clickable.");
        }

//                // Clear and enter 'To' location
//                if (PM.getclear_to_inp().isDisplayed() && PM.getclear_to_inp().isEnabled()) {
//                    wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_to_inp())).click();
//                    Reporter.log("'To' input cleared", true);
//                    SC_Path = Screenshots.takeScreenShot(driver, "ToInputCleared");
//                    generateReportWithScreenshot("to_input_cleared", SC_Path);
//                    test.pass("'To' input cleared.");
//                } else {
//                    test.fail("'To' input clear button is not clickable.");
//                    Assert.fail("'To' input clear button is not clickable.");
//                }

        WebElement toInput = PM.getShifting_To();
        if (toInput.isDisplayed() && toInput.isEnabled()) {
            wait.until(ExpectedConditions.visibilityOf(toInput)).sendKeys(to);
            Reporter.log("'To' location entered", true);
            SC_Path = Screenshots.takeScreenShot(driver, "ToLocationEntered");
            generateReportWithScreenshot("to_location_entered", SC_Path);
            test.pass("'To' location entered.");
        } else {
            test.fail("'To' input field is not available.");
            Assert.fail("'To' input field is not available.");
        }

        WebElement toSelect = PM.getToMovers();
        if (toSelect.isDisplayed() && toSelect.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(toSelect)).click();
            Reporter.log("'To' location selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "ToLocationSelected");
            generateReportWithScreenshot("to_location_selected", SC_Path);
            test.pass("'To' location selected.");
        } else {
            test.fail("'To' location selector is not clickable.");
            Assert.fail("'To' location selector is not clickable.");
        }

        // Verify and click on 'View Prices'
        WebElement Price_btn = PM.getMoversPrices();
        if (Price_btn.isDisplayed()) {
            test.pass("Price button is visible.");
            if (Price_btn.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(Price_btn)).click();
                Reporter.log("Clicked on View Prices", true);
                SC_Path = Screenshots.takeScreenShot(driver, "ViewPricesClicked");
                generateReportWithScreenshot("view_prices_clicked", SC_Path);
                test.pass("View Prices clicked.");
            } else {
                test.fail("Price button is not clickable.");
                Assert.fail("Price button is not clickable.");
            }
        } else {
            test.fail("Price button is not visible.");
            Assert.fail("Price button is not visible.");
        }

        // Select truck option
        WebElement truck = MO.getTruck();
        if (truck.isDisplayed() && truck.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(truck)).click();
            Reporter.log("Truck selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "TruckSelected");
            generateReportWithScreenshot("truck_selected", SC_Path);
            test.pass("Truck selected.");
        } else {
            test.fail("Truck option is not clickable.");
            Assert.fail("Truck option is not clickable.");
        }

        // Verify and click on 'Schedule'
        WebElement Schedule_btn = MO.getschedule();
        if (Schedule_btn.isDisplayed()) {
            test.pass("Schedule button is visible.");
            if (Schedule_btn.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(Schedule_btn)).click();
                Reporter.log("Clicked on Schedule", true);
                SC_Path = Screenshots.takeScreenShot(driver, "ScheduleClicked");
                generateReportWithScreenshot("schedule_clicked", SC_Path);
                test.pass("Schedule clicked.");
            } else {
                test.fail("Schedule button is not clickable.");
                Assert.fail("Schedule button is not clickable.");
            }
        } else {
            test.fail("Schedule button is not visible.");
            Assert.fail("Schedule button is not visible.");
        }

        // Select date
        WebElement date = MO.getDate();
        if (date.isDisplayed() && date.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(date)).click();
            Reporter.log("Date selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "DateSelected");
            generateReportWithScreenshot("date_selected", SC_Path);
            test.pass("Date selected.");
        } else {
            test.fail("Date is not clickable.");
            Assert.fail("Date is not clickable.");
        }

        // Select timezone
        WebElement timezone = MO.gettimezone();
        if (timezone.isDisplayed() && timezone.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(timezone)).click();
            Reporter.log("Timezone selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "TimezoneSelected");
            generateReportWithScreenshot("timezone_selected", SC_Path);
            test.pass("Timezone selected.");
        } else {
            test.fail("Timezone is not clickable.");
            Assert.fail("Timezone is not clickable.");
        }

        // Select time interval
        WebElement timeInterval = MO.gettimeinterval();
        if (timeInterval.isDisplayed() && timeInterval.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(timeInterval)).click();
            Reporter.log("Time interval selected", true);
            SC_Path = Screenshots.takeScreenShot(driver, "TimeIntervalSelected");
            generateReportWithScreenshot("time_interval_selected", SC_Path);
            test.pass("Time interval selected.");
        } else {
            test.fail("Time interval is not clickable.");
            Assert.fail("Time interval is not clickable.");
        }

        // Verify and click on 'Confirm'
        WebElement Confirm_btn = MO.getconfirm();
        if (Confirm_btn.isDisplayed()) {
            test.pass("Confirm button is visible.");
            if (Confirm_btn.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(Confirm_btn)).click();
                Reporter.log("Schedule confirmed", true);
                SC_Path = Screenshots.takeScreenShot(driver, "ScheduleConfirmed");
                generateReportWithScreenshot("schedule_confirmed", SC_Path);
                test.pass("Schedule confirmed.");
            } else {
                test.fail("Confirm button is not clickable.");
                Assert.fail("Confirm button is not clickable.");
            }
        } else {
            test.fail("Confirm button is not visible.");
            Assert.fail("Confirm button is not visible.");
        }
    }

    
    @AfterClass(alwaysRun = true)
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Browser closed after test method", true);
        }
    }

}
