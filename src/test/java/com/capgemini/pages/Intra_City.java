package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Intra_City{
		
	WebDriver drv;
	
	public Intra_City(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Living Room']")
	private WebElement select_Living_Room;
	
	public WebElement getLivingRoom() {
		return select_Living_Room;
	}
	
	@FindBy(xpath = "//div[contains(text(),'Sofa')]")
	private WebElement select_Sofa;
	
	public WebElement getSofa() {
		return select_Sofa;
	}
	
	@FindBy(xpath = "//div[@id='increament']//*[name()='svg']")
	private WebElement select_1Seater_Sofa;
	
	public WebElement get1Seater() {
		return select_1Seater_Sofa;
	}
	
//	@FindBy(xpath = "//div[@id='app']//div[4]//div[1]//div[2]//div[1]"
//			+ "//*[name()='svg']//*[name()='g' and contains(@clip-path,'url(#clip0')]"
//			+ "//*[name()='path' and contains(@d,'M7 2.1875V')]")
//	private WebElement select_2Seater_Sofa;
//	
//	public WebElement get2Seater_Sofa() {
//		return select_2Seater_Sofa;
//	}
	
	@FindBy(xpath = "//div[contains(text(),'Continue')]")
	private WebElement select_Continue;
	
	public WebElement getContinue() {
		return select_Continue;
	}
	
	@FindBy(xpath = "//div[contains(@class,'flex flex-row justify-between overflow-auto gap-x-3 md:gap-x-1.4p hide-scroll-bar px-5')]"
			+ "//div[3]//div[2]")
	private WebElement select_Date;
	
	public WebElement getDate() {
		return select_Date;
	}
	
	@FindBy(xpath = "//div[contains(text(),'10AM-11AM')]")
	private WebElement select_Time;
	
	public WebElement getTime() {
		return select_Time;
	}
	
	@FindBy(xpath = "//div[contains(@class,'flex items-center justify-center')]"
			+ "//div[contains(text(),'Confirm')]")
	private WebElement select_Confirm;
	
	public WebElement getConfirm() {
		return select_Confirm;
	}
	
	@FindBy(xpath = "//img[@alt='arrow']")
	private WebElement select_Inventory;
	
	public WebElement getInventory() {
		return select_Inventory;
	}
	
	@FindBy(xpath = "//button[normalize-space()='Done']")
	private WebElement select_Done;
	
	public WebElement getDone() {
		return select_Done;
	}
	
	@FindBy(xpath = "(//div//label)[4]")
	private WebElement Multi_Layer_Packing;
	
	public WebElement getMulti_Layer_Packing() {
		return Multi_Layer_Packing;
	}
}
