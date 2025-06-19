package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeService{
		
	WebDriver drv;
	
	@FindBy(xpath = "//div[@id='hs_packers_movers']//div[@class='h-3p w-3p relative']")
	private WebElement pack_n_move;
	
	@FindBy(xpath = "//div//div[text()='Within City']")
	private WebElement within_city_btn;
	
	@FindBy(xpath = "//img[@alt='Bangalore']")
	private WebElement city_btn;
	
	public HomeService(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
	
	public WebElement getpack_n_move() {
		return pack_n_move;
	}
	
	public WebElement getwithincity() {
		return within_city_btn;
	}
	
	public WebElement getcity() {
		return city_btn;
	}
}
