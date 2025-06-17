package com.capgemini.tests;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.capgemini.driversetup.SetupDriver;
import com.capgemini.pages.HomePage;
import com.capgemini.pages.HomeService;
import com.capgemini.pages.MyBookings;
import com.capgemini.pages.Packer_N_Movers;
import com.capgemini.parameters.ExcelReader;
import com.capgemini.utils.Screenshots;

public class NB_PM_05 extends BaseReport {

    String baseURL;
    HomePage HP;
    MyBookings MB;
    Packer_N_Movers PM;
    HomeService HS;
    String excelPath;
    String SC_Path;
    WebDriverWait wait;

    @BeforeClass
    public void setUpClass() throws InterruptedException {
        excelPath = "src/test/resources/ExcelData/TestData.xlsx";
        baseURL = "https://www.nobroker.in/";
        driver = SetupDriver.getDriver("chrome");
        driver.navigate().to(baseURL);
        Thread.sleep(2000);
        parentTest = extent.createTest(getClass().getSimpleName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        HP = new HomePage(driver);
        MB = new MyBookings(driver);
        PM = new Packer_N_Movers(driver);
        HS = new HomeService(driver);
    }

    @Test
    public void LoginIn() throws Exception {
        String phonenum = ExcelReader.getData(excelPath, "Login", 0, 1);

        // Step 1 - Click on login link
        HP.getlogin_link().click();
        Reporter.log("Clicked on Login link", true);
        SC_Path = Screenshots.takeScreenShot(driver, "LoginClicked");
        generateReportWithScreenshot("Click on login link", SC_Path);

        // Step 2 - Enter phone number
        HP.getphn_num_txt().sendKeys(phonenum);
        Reporter.log("Entered Phone number: " + phonenum, true);
        SC_Path = Screenshots.takeScreenShot(driver, "PhonenumEntered");
        generateReportWithScreenshot("Enter phone num", SC_Path);

        // Step 3 - Submit (manual OTP wait)
        Thread.sleep(30000);
        HP.getsubmit_btn().click();
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
    private void BackToPackFromHS() throws Exception {

        // Step 4 - Click on menu
        HP.getMenu().click();
        Reporter.log("Menu Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "MenuClicked");
        generateReportWithScreenshot("Click on menu", SC_Path);

        // Verify Painting button is visible
        WebElement Painting_btn = HP.getPainting_btn();
        if (Painting_btn.isDisplayed()) {
            test.pass("Painting button is visible.");
        } else {
            test.fail("Painting button is not visible.");
            Assert.fail("Painting button is not visible");
        }

        // Step 5 - Click on Painting and Cleaning
        Painting_btn.click();
        Reporter.log("Painting and Cleaning Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "PaintingAndCleaningClicked");
        generateReportWithScreenshot("Painting and Cleaning clicked", SC_Path);

        // Verify City button is visible
        WebElement city_btn = HS.getcity();
        if (city_btn.isDisplayed()) {
            test.pass("City button is visible.");
        } else {
            test.fail("City button is not visible.");
            Assert.fail("City button is not visible");
        }

        // Step 6 - Select City
        city_btn.click();
        Reporter.log("City Selected", true);
        SC_Path = Screenshots.takeScreenShot(driver, "CitySelected");
        generateReportWithScreenshot("City selected", SC_Path);

        // Verify Packers & Movers button is visible
        WebElement pack_btn = HS.getpack_n_move();
        if (pack_btn.isDisplayed()) {
            test.pass("Packers & Movers button is visible.");
        } else {
            test.fail("Packers & Movers button is not visible.");
            Assert.fail("Packers & Movers button is not visible");
        }

        // Step 7 - Click on Packers and Movers
        pack_btn.click();
        Reporter.log("Packers and Movers Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "PackerAndMoversClicked");
        generateReportWithScreenshot("Click on Packers and Movers", SC_Path);

        // Verify Within City button is visible
        WebElement packer_btn = HS.getwithincity();
        if (packer_btn.isDisplayed()) {
            test.pass("Within City button is visible.");
        } else {
            test.fail("Within City button is not visible.");
            Assert.fail("Within City button is not visible");
        }

        // Step 8 - Click on Within City
        packer_btn.click();
        Reporter.log("WithinCity Clicked", true);
        SC_Path = Screenshots.takeScreenShot(driver, "WithinCityClicked");
        generateReportWithScreenshot("Clicked on Within City", SC_Path);
    }
    
    @AfterClass()
    public void tearDownMethod() {
        if (driver != null) {
            driver.close();
            Reporter.log("Browser closed after test method", true);
        }
    }

}
