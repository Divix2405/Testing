package Pageobj;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pincode {
	
	WebDriver driver;
	public Pincode(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")
	private WebElement shopSection;
	
	@FindBy(xpath="//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")
	private WebElement WallStickers;
	
	@FindBy(xpath="//*[@id=\'thin-banner-info\']/ul/li[1]/a/img")
	private WebElement kids_wall_sticker;
	
	@FindBy(xpath="//*[@id='thin-banner-info']/ul/li[1]/div[2]/a/span[1]")
	private WebElement AddToCart_BelowKids;
	
	@FindBy(xpath="//*[@id='header-minicart']/div/button")
	private WebElement BagIcon;
	
	@FindBy(xpath="//*[@id=\"addToCart\"]")
	private WebElement AddtoCart_InsideItem;
	
	@FindBy(xpath="//*[@id='cartPinCodeModal']/div/div/div[2]/form")
	private WebElement loginform_addtocart_belowkids;
	
	//@FindBy(xpath="//*[@id=\\\"cartPinCodeModal\\\"]/div/div/div[2]/form/legend/span") -[text inside enter pinode form]
	//private WebElement textbelow_addtocart_insideitem;
	
	@FindBy(xpath="//span[@class=\'spriteIcon-Firstfold profileIcon\']")
	private WebElement HomePageLoginIcon;
	
	@FindBy(xpath="//input[@class='pincode']")
	private WebElement Pincode_InsideItem;
	
	@FindBy(xpath="//span[normalize-space()='This Pin Code is not Serviceable.']")
	private WebElement TextBelowPincode_Fail;
	
	@FindBy(xpath="//*[@id='responseMessage']")
	private WebElement TextBelowPincode_Success;
	
	@FindBy(id="responseMessage")
	private WebElement TextBelowPincode_Missing;
	
	@FindBy(xpath="//div[@class='google-map-icon pincode-service-check']//a[@href='javascript:;']")
	private WebElement CheckPincodeButton;
	
	@FindBy(xpath="//*[@id='loginMobile']")
	private WebElement phonenumberinlogin;
	
	@FindBy(xpath="//button[@class='ctaText modal__variant-login--submit']")
	private WebElement submitbutton_phone;
	
	@FindBy(xpath="//*[@id='loginOtp']")
	private WebElement otparea;
	
	@FindBy(xpath="//*[@id=\\'validate-otp\\']/div[3]/button")
	private WebElement submitbutton_otp;
	
	@FindBy(xpath="//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")
	private WebElement wallstickers;
	
	@FindBy(xpath="//*[@id=\"cartPinCode\"]")
	private WebElement LoginForm_Addtocart_belowkids_input;
	
	public void Enter_Pincode_LoginForm() {
		LoginForm_Addtocart_belowkids_input.sendKeys("000000");
	}
	
	public String Errormsg_Missing() {
		return TextBelowPincode_Missing.getText();
	}
	
	public String Errormsg_Invalid() {
		return TextBelowPincode_Fail.getText();
	}
	
	public boolean Errormsg_Valid() {
		return !TextBelowPincode_Success.isDisplayed();
	}
	
	public void ClickonCheck() {
		CheckPincodeButton.click();
	}
	
	public void EnterPincode_Valid() {
		Pincode_InsideItem.sendKeys("211012");
	}
	
	public void EnterPincode_Invalid(String g) {
		Pincode_InsideItem.sendKeys(g);
	}
	
	public void EnterPincode_Nothing() {
		Pincode_InsideItem.sendKeys("");
	}
	
	public boolean EnterPincodeForm() {
		return loginform_addtocart_belowkids.isDisplayed();
	}
	
	public void Addtocart_BelowItem() {
		AddToCart_BelowKids.click();
	}
	/*public String ErrorMsgBelow_addtocart() {
		return textbelow_addtocart_insideitem.getText();
	}*/
	
	public void clickonkids() {
		kids_wall_sticker.click();
	}
	
	public void InputPincode_Invalid() {
		Pincode_InsideItem.sendKeys("000000");
	}
	
	public void Addtocart_InsideItem() {
		AddtoCart_InsideItem.click();
	}
	
	public void gotoShopSection() {
		shopSection.click(); 
	}
	
	public void clickwallsticker() {
		wallstickers.click();
	}
	
	public void signin_phoneno() {
		HomePageLoginIcon.click();
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
