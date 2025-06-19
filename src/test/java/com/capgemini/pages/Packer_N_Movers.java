package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Packer_N_Movers{
	
	WebDriver drv;
	
	public Packer_N_Movers(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
		
	
//	Within City
	
	@FindBy(xpath = "//div[contains(@controlid,'pnmLandingPageFromRelocationFormLocalitySearch')]"
			+ "//span[contains(@class,'right-input-icon')]"
			+ "//*[name()='svg']")
	private WebElement clear_from_inp;
	
	public WebElement getclear_from_inp() {
		return clear_from_inp;
	}
	
	@FindBy(xpath = "//input[@id='pnmLandingPageFromRelocationFormLocalitySearch']")
	private WebElement Shifting_From;
	
	public WebElement getShifting_From() {
		return Shifting_From;
	}
	
	@FindBy(xpath = "//strong[normalize-space()='BTM Layout 1st Stage']")
	private WebElement ShiftingFrom_selector;
	
	public WebElement getFromSelector() {
		return ShiftingFrom_selector;
	}
	
	@FindBy(xpath = "//div[contains(@controlid,'pnmLandingPageToRelocationLocalitySearch')]"
			+ "//span[contains(@class,'right-input-icon')]"
			+ "//*[name()='svg']")
	private WebElement clear_to_inp;
	
	public WebElement getclear_to_inp() {
		return clear_to_inp;
	}
	
	@FindBy(xpath = "//input[@id='pnmLandingPageToRelocationLocalitySearch']")
	private WebElement Shifting_To;
	
	public WebElement getShifting_To() {
		return Shifting_To;
	}
	
	@FindBy(xpath = "//strong[normalize-space()='HSR layout Sector 2, 1st Stage, BTM Layout']")
	private WebElement ShiftingTo_selector;
	
	public WebElement getTo_Selector() {
		return ShiftingTo_selector;
	}
	
	@FindBy(xpath = "//div[@id='pnmGetRealPriceButtonDesktop']")
	private WebElement CheckPrices_btn;
	
	public WebElement getPrices() {
		return CheckPrices_btn;
	}
	
//	Mybookings
	
	@FindBy(xpath = "//button/span[text()='My Bookings']")
	private WebElement MyBookings_btn;
	
	public WebElement getMyBookings_btn() {
		return MyBookings_btn;
	}
	
//	Between Cities
	
	@FindBy(xpath = "//div[@id='interCityMove']")
	private WebElement Between_cities;
	
	public WebElement getBetweenCities() {
		return Between_cities;
	}
	
	@FindBy(xpath = "//input[@id='fromCity']")
	private WebElement fromCity;
	
	public WebElement getfromCity() {
		return fromCity;
	}
	
	@FindBy(xpath = "//input[@id='toCity']")
	private WebElement toCity;
	
	public WebElement gettoCity() {
		return toCity;
	}
	
	@FindBy(xpath = "//li[normalize-space()='Bangalore (Karnataka)']")
	private WebElement selectFrom;
	
	public WebElement getFrom() {
		return selectFrom;
	}
	
	@FindBy(xpath = "//li[normalize-space()='Chennai (Tamilnadu)']")
	private WebElement selectTo;
	
	public WebElement getTo() {
		return selectTo;
	}
	
	@FindBy(xpath = "//input[@placeholder='Shifting Date']")
	private WebElement Date_txt;
	
	public WebElement getDate_txt() {
		return Date_txt;
	}
	
	@FindBy(xpath = "//div[@aria-label='day-25']")
	private WebElement Datepicker;
	
	public WebElement getDate() {
		return Datepicker;
	}
	
	@FindBy(xpath = "//div[@id='pnmGetRealPriceButtonDesktop']")
	private WebElement Prices;
	
	public WebElement getPrice_withinCity() {
		return Prices;
	}
	@FindBy(xpath = "//div[@class='movers-only-text-container']")
	private WebElement movers_Only;
	
	public WebElement getMovers() {
		return movers_Only;
	}
	
	@FindBy(xpath = "//div[@id='pnmGetRealPriceButtonDesktop']")
	private WebElement Moversprices;

	public WebElement getMoversPrices() {
		return Moversprices;
	}
	
	@FindBy(xpath = "//strong[contains(@class,'text-15')]")
	private WebElement fromMovers;

	public WebElement getFromMovers() {
		return fromMovers;
	}
	
	@FindBy(xpath = "//strong[normalize-space()='HSR layout Sector 2, 1st Stage, BTM Layout']")
	private WebElement toMovers;

	public WebElement getToMovers() {
		return toMovers;
	}
	
	@FindBy(xpath = "//div[contains(text(),'Please enter your pickup locality')]")
	private WebElement errorFrom;

	public WebElement getFromError() {
		return errorFrom;
	}
	
	@FindBy(xpath = "//div[contains(text(),'Please enter your destination locality')]")
	private WebElement errorTo;

	public WebElement getToError() {
		return errorTo;
	}
}