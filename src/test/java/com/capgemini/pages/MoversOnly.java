package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoversOnly{
	
	WebDriver drv;
	
	public MoversOnly(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
	
	@FindBy(xpath = "//img[@alt='catalog/movers_vehicle/Dost_big.png']")
	private WebElement truck;

	public WebElement getTruck() {
		return truck;
	}
	
	@FindBy(xpath = "//div[@class='-my-1 font-semibold text-16 md:text-17'][contains(.,'21 Jun')]")
	private WebElement date;

	public WebElement getDate() {
		return date;
	}
	
	@FindBy(xpath = "//div[contains(text(),'Afternoon')]")
	private WebElement timezone;

	public WebElement gettimezone() {
		return timezone;
	}
	
	@FindBy(xpath = "//div[contains(text(),'3PM-4PM')]")
	private WebElement timeinterval;

	public WebElement gettimeinterval() {
		return timeinterval;
	}
	
	@FindBy(xpath = "//button//div//div[contains(text(),'Schedule')]")
	private WebElement schedule;

	public WebElement getschedule() {
		return schedule;
	}
	
	@FindBy(xpath = "//button//div//div[text()='Confirm']")
	private WebElement confirm;

	public WebElement getconfirm() {
		return confirm;
	}
}
