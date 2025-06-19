package com.capgemini.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{
	
	WebDriver drv;
	
	@FindBy(xpath="//div[@class='px-1p border-l-1 border-l-solid border-l-header-grey cursor-pointer']")
	private WebElement login_link;
	
	@FindBy(xpath = "//input[@id='signUp-phoneNumber']")
	private WebElement phn_num_txt;
	
	@FindBy(xpath = "//button[@id='signUpSubmit']")
	private WebElement submit_btn;
	
	@FindBy(xpath = "(//div/a[text()='Packers and Movers'])[1]")
	private WebElement packers_btn;
	
	@FindBy(xpath = "//div/a[text()='Painting & Cleaning']")
	private WebElement painting_btn;
	
	@FindBy(xpath = "//div[@class='flex py-0 px-1.5p border-l-1 border-l-solid border-l-header-grey items-center cursor-pointer']")
	private WebElement Menu;
	
	@FindBy(xpath = "//div//div//span[text()='Jeswanth']")
	private WebElement profile;
	
	public HomePage(WebDriver drv) {
		this.drv = drv;
		PageFactory.initElements(drv, this);
	}
	
	public WebElement getlogin_link() {
		return login_link;
	}
	
	public WebElement getphn_num_txt() {
		return phn_num_txt;
	}
	
	public WebElement getsubmit_btn() {
		return submit_btn;
	}
	
	public WebElement getpackers_btn() {
		return packers_btn;
	}
	
	public WebElement getMenu() {
		return Menu;
	}
	
	public WebElement getPainting_btn() {
		return painting_btn;
	}
	
	public WebElement getProfile() {
		return profile;
	}
	
//	public void login(String phone) throws InterruptedException{
//		login_link.click();
//		Thread.sleep(2000);
//		phn_num_txt.sendKeys(phone);
//		Thread.sleep(30000);
//		submit_btn.click();
//	}

//	public void packers_n_movers() throws InterruptedException {
//		// TODO Auto-generated method stub
//		Thread.sleep(2000);
//		Menu.click();
//		Thread.sleep(2000);
//		packers_btn.click();
//	}

}
