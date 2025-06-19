package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBookings{
	
	WebDriver drv;
		
	@FindBy(xpath = "//a[@href='/packers-and-movers']")
	private WebElement packnmove_btn;
	
	@FindBy(xpath = "//a[normalize-space()='Home']")
	private WebElement home_btn;
	
	public MyBookings(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}

//	public void Back_To_Home() {
//		// TODO Auto-generated method stub
//		home_btn.click();	
//	}
	
	public WebElement gethome_btn() {
		return home_btn;
	}
}
