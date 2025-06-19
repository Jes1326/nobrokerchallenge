package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inter_City{
	
	WebDriver drv;
	
	public Inter_City(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Living Room']")
	private WebElement Select_Living_Room;
	
	public WebElement getLivingRoom() {
		return Select_Living_Room;
	}
	
	@FindBy(xpath = "//div[contains(text(),'Sofa')]")
	private WebElement select_Sofa;
	
	public WebElement getSofa() {
		return select_Sofa;
	}
	
	@FindBy(xpath = "//div[contains(@class,'flex flex-col relative pl-3p py-2 text-14 font-normal bg-white-pnm')]//div[2]//div[1]//div[2]//div[1]//*[name()='svg']")
	private WebElement select_2Seater_Sofa;
	
	public WebElement get2Seater() {
		return select_2Seater_Sofa;
	}
	
	@FindBy(xpath = "//div//button[contains(.,'Continue')]")
	private WebElement select_Continue;
	
	public WebElement getContinue() {
		return select_Continue;
	}
	
}
