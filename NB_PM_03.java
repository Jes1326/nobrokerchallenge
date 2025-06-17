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
import com.capgemini.utils.Screenshots;

public class NB_PM_03 extends BaseReport {

    WebDriverWait wait;
    String baseURL;
    HomePage HP;
    Packer_N_Movers PM;
    MoversOnly MO;
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
        MO = new MoversOnly(driver);
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
    }

    @Test(dependsOnMethods = "LoginIn")
    private void MoversOnly() throws Exception {
        String from = ExcelReader.getData(excelPath, "MoversOnly", 0, 1);
        String to = ExcelReader.getData(excelPath, "MoversOnly", 1, 1);

        wait.until(ExpectedConditions.elementToBeClickable(HP.getMenu())).click();
        Reporter.log("Menu opened", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MenuOpened");
        generateReportWithScreenshot("menu_opened", SC_Path);

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

        WebElement Movers_btn = PM.getMovers();
        if (Movers_btn.isDisplayed()) {
            test.pass("Movers only option is visible.");
        } else {
            test.fail("Movers only option is not visible.");
            Assert.fail("Movers only option is not visible");
        }

        wait.until(ExpectedConditions.elementToBeClickable(Movers_btn)).click();
        Reporter.log("Movers option selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MoversSelected");
        generateReportWithScreenshot("movers_selected", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_from_inp())).click();
        Reporter.log("'From' input cleared", true);
        SC_Path = Screenshots.takeScreenShot(driver, "FromInputCleared");
        generateReportWithScreenshot("from_input_cleared", SC_Path);

        wait.until(ExpectedConditions.visibilityOf(PM.getShifting_From())).sendKeys(from);
        Reporter.log("'From' location entered", true);
        SC_Path = Screenshots.takeScreenShot(driver, "FromLocationEntered");
        generateReportWithScreenshot("from_location_entered", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(PM.getFromSelector())).click();
        Reporter.log("'From' location selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "FromLocationSelected");
        generateReportWithScreenshot("from_location_selected", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(PM.getclear_to_inp())).click();
        Reporter.log("'To' input cleared", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ToInputCleared");
        generateReportWithScreenshot("to_input_cleared", SC_Path);

        wait.until(ExpectedConditions.visibilityOf(PM.getShifting_To())).sendKeys(to);
        Reporter.log("'To' location entered", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ToLocationEntered");
        generateReportWithScreenshot("to_location_entered", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(PM.getTo_Selector())).click();
        Reporter.log("'To' location selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ToLocationSelected");
        generateReportWithScreenshot("to_location_selected", SC_Path);

        WebElement Price_btn = PM.getMoversPrices();
        if (Price_btn.isDisplayed()) {
            test.pass("Price button is visible.");
        } else {
            test.fail("Price button is not visible.");
            Assert.fail("Price button is not visible");
        }

        wait.until(ExpectedConditions.elementToBeClickable(Price_btn)).click();
        Reporter.log("Clicked on View Prices", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ViewPricesClicked");
        generateReportWithScreenshot("view_prices_clicked", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(MO.getTruck())).click();
        Reporter.log("Truck selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "TruckSelected");
        generateReportWithScreenshot("truck_selected", SC_Path);

        WebElement Schedule_btn = MO.getschedule();
        if (Schedule_btn.isDisplayed()) {
            test.pass("Schedule button is visible.");
        } else {
            test.fail("Schedule button is not visible.");
            Assert.fail("Schedule button is not visible");
        }

        wait.until(ExpectedConditions.elementToBeClickable(Schedule_btn)).click();
        Reporter.log("Clicked on Schedule", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ScheduleClicked");
        generateReportWithScreenshot("schedule_clicked", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(MO.getDate())).click();
        Reporter.log("Date selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "DateSelected");
        generateReportWithScreenshot("date_selected", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(MO.gettimezone())).click();
        Reporter.log("Timezone selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "TimezoneSelected");
        generateReportWithScreenshot("timezone_selected", SC_Path);

        wait.until(ExpectedConditions.elementToBeClickable(MO.gettimeinterval())).click();
        Reporter.log("Time interval selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "TimeIntervalSelected");
        generateReportWithScreenshot("time_interval_selected", SC_Path);

        WebElement Confirm_btn = MO.getconfirm();
        if (Confirm_btn.isDisplayed()) {
            test.pass("Confirm button is visible.");
        } else {
            test.fail("Confirm button is not visible.");
            Assert.fail("Confirm button is not visible");
        }

        wait.until(ExpectedConditions.elementToBeClickable(Confirm_btn)).click();
        Reporter.log("Schedule confirmed", true);
        SC_Path = Screenshots.takeScreenShot(driver, "ScheduleConfirmed");
        generateReportWithScreenshot("schedule_confirmed", SC_Path);
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Browser closed after test method", true);
        }
    }

}
