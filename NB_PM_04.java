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
import com.capgemini.parameters.ExcelReader;
import com.capgemini.utils.Screenshots;

public class NB_PM_04 extends BaseReport {

    WebDriverWait wait;
    String baseURL;
    HomePage HP;
    MyBookings MB;
    Packer_N_Movers PM;
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
        MB = new MyBookings(driver);
        PM = new Packer_N_Movers(driver);
    }

    @Test
    public void LoginIn() throws Exception {
        String phonenum = ExcelReader.getData(excelPath, "Login", 0, 1);

        // Step 1 - Click on login link
        wait.until(ExpectedConditions.elementToBeClickable(HP.getlogin_link())).click();
        Reporter.log("Clicked on Login link", true);
        SC_Path = Screenshots.takeScreenShot(driver, "LoginClicked");
        generateReportWithScreenshot("Click on login link", SC_Path);

        // Step 2 - Enter phone number
        wait.until(ExpectedConditions.visibilityOf(HP.getphn_num_txt())).sendKeys(phonenum);
        Reporter.log("Entered Phone number: " + phonenum, true);
        SC_Path = Screenshots.takeScreenShot(driver, "PhonenumEntered");
        generateReportWithScreenshot("Enter phone num", SC_Path);

        // Step 3 - Submit (manual OTP wait)
        Thread.sleep(30000); // OTP wait
        wait.until(ExpectedConditions.elementToBeClickable(HP.getsubmit_btn())).click();
        Reporter.log("Submitted", true);
        SC_Path = Screenshots.takeScreenShot(driver, "SubmitButtonClicked");
        generateReportWithScreenshot("submit", SC_Path);

        // Verify profile element is visible
        WebElement profileElement = HP.getProfile();
        if (profileElement.isDisplayed()) {
            test.pass("Profile element is visible.");
        } else {
            test.fail("Profile element is not visible.");
            Assert.fail("Profile element is not displayed");
        }
    }

    @Test(dependsOnMethods = "LoginIn")
    private void BacToHomeFromMB() throws Exception {

        // Step 4 - Click on menu
        wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
        Reporter.log("Menu Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
        generateReportWithScreenshot("Click on menu", SC_Path);

        // Verify Packers button is visible
        WebElement Packers_btn = HP.getpackers_btn();
        if (Packers_btn.isDisplayed()) {
            test.pass("Packers button is visible.");
        } else {
            test.fail("Packers button is not visible.");
            Assert.fail("Packers button is not visible");
        }

        // Step 5 - Click on Packers and Movers
        wait.until(ExpectedConditions.elementToBeClickable(Packers_btn)).click();
        Reporter.log("Packers and movers Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "PackersnMoversClicked");
        generateReportWithScreenshot("Packernmovers clicked", SC_Path);

        String PM_handle = driver.getWindowHandle();

        // Verify MyBookings button is visible
        WebElement MyBooking_btn = PM.getMyBookings_btn();
        if (MyBooking_btn.isDisplayed()) {
            test.pass("MyBookings button is visible.");
        } else {
            test.fail("MyBookings button is not visible.");
            Assert.fail("MyBookings button is not visible");
        }

        // Step 6 - Click on My Bookings
        wait.until(ExpectedConditions.elementToBeClickable(MyBooking_btn)).click();
        Reporter.log("Mybookings Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MyBookingsClicked");
        generateReportWithScreenshot("Click on mybookings", SC_Path);

        // Step 7 - Switch to new window
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

        // Verify Home button is visible
        WebElement home_btn = MB.gethome_btn();
        if (home_btn.isDisplayed()) {
            test.pass("Home button is visible.");
        } else {
            test.fail("Home button is not visible.");
            Assert.fail("Home button is not visible");
        }

        // Step 8 - Click on home button to go back
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
