package Pageobj;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Buy {
	
	WebDriver driver;
	
	public Buy(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")
	private WebElement shopSection;
	
	@FindBy(xpath="//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")
	private WebElement wallstickers;
	
	@FindBy(xpath="//*[@id=\'thin-banner-info\']/ul/li[1]/a/img")
	private WebElement kids_wall_sticker;
	
	@FindBy(xpath="//*[@id='header-minicart']/div/button")
	private WebElement bagicon_homepage;
	
	@FindBy(xpath="//*[@id='container']/header/div/div[3]/div[2]/div[2]/button")
	private WebElement bagicon_insideitem;
	
	@FindBy(xpath="//*[@id=\"addToCart\"]")
	private WebElement AddtoCart_InsideItem;
	
	@FindBy(xpath="//div[@id='slick-slide10']//img[@title='AP_Shop_Banner_Festive_2A_desktop']")
	private WebElement Sliding_FirstImg;
	
	@FindBy(xpath="//span[@class=\'spriteIcon-Firstfold profileIcon\']")
	private WebElement HomePageLoginIcon;
	
	@FindBy(xpath="//*[@id='container']/header/div/div[3]/div[2]/div[2]/div/div/ul/li/a")
	private WebElement Item_InsideBag;
	
	@FindBy(xpath="//*[@id='header-minicart']/div/div/div/p")
	private WebElement Text_InsideEmptyBag;
	
	@FindBy(xpath="//input[@id='loginMobile']")
	private WebElement phonenumberinlogin;
	
	@FindBy(xpath="//button[@class='ctaText modal__variant-login--submit']")
	private WebElement submitbutton_phone;
	
	@FindBy(xpath="//*[@id='loginOtp']")
	private WebElement otparea;
	
	@FindBy(xpath="//*[@id='validate-otp']/div[3]/button")
	private WebElement submitbutton_otp;
	
	@FindBy(xpath="//a[@id='checkout']")
	private WebElement buynowbutton;
	
	@FindBy(xpath="//*[@id='checkOutTab-1']/div/div[2]/h2")
	private WebElement checkoutpagetext;
	
	@FindBy(xpath="//input[@class='pincode']")
	private WebElement Pincode_InsideItem;
	
	public boolean checkoutnotdisplayed() {
	    try{
	       return(!checkoutpagetext.isDisplayed());
	    }
	    catch(Exception e){
	       return false;
	    }
	}
	public boolean CheckoutpageText() {
		//in not checkout it will give false
		return checkoutpagetext.isDisplayed();
	}
	public String EmptyCartMessage() {
		return Text_InsideEmptyBag.getText();
	}

	public void FillPincode() {
		Pincode_InsideItem.sendKeys("211012");
	}
	
	public boolean Iteminsidebag() {
		return Item_InsideBag.isDisplayed();
	}
	
	public void Insideitem_addtocartclick() {
		AddtoCart_InsideItem.click();
	}

	public void clickbuynow() {
		buynowbutton.click();
	}
	
	public void BagIcon() {
		bagicon_homepage.click();
	}
	
	public void BagIcon_item() {
		bagicon_insideitem.click();
	}
	public void clickonkids() {
		kids_wall_sticker.click();
	}
	
	public void gotoShopSection() {
		shopSection.click(); 
	}
	
	public void clickwallsticker() {
		wallstickers.click();
	}
	
	public void signin_phoneno() throws InterruptedException {
		HomePageLoginIcon.click();
		Thread.sleep(2000);
		phonenumberinlogin.sendKeys("9575203684");
		submitbutton_phone.click();
		
	}
	
	public void signin_otp() {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter otp:");
		String s=sc.nextLine();
		otparea.sendKeys(s);
		submitbutton_otp.click();
		
	}
	
	
	

}
