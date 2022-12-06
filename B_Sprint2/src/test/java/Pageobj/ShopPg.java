package Pageobj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPg {
	WebDriver driver;
	
	public ShopPg(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//make a shortcut for login icon in screen, submit button, take i/p for otp using scanner
	@FindBy(xpath="//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")
	private WebElement shopsection;
	
	@FindBy(xpath="//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")
	private WebElement WallStickers;
	
	@FindBy(xpath="//*[@id=\'thin-banner-info\']/ul/li[1]/a/img")
	private WebElement kids_wall_sticker;
	
	@FindBy(xpath="//*[@id='thin-banner-info']/ul/li[1]/div[2]/a/span[1]")
	private WebElement addtocart_belowkids;
	
	@FindBy(xpath="//*[@id='header-minicart']/div/button")
	private WebElement bagicon;
	
	@FindBy(id="addToCart")
	private WebElement addtocart_insideitem;
	
	@FindBy(xpath="//*[@id='slick-slide10']/a/picture/img")
	private WebElement Sliding_FirstImg;
	
	@FindBy(xpath="//img[@title='ap-shop-colour-selection-tool-thumbnail-asian-paints']") 
	private WebElement colortool;
	
	@FindBy(xpath="//*[@id=\"header-minicart\"]/div/div/div/p")
	private WebElement Text_InsideEmptyBag;
	
	@FindBy(xpath="//h2[@class='h2 section-header']")
	private WebElement textonredirectedpage;
	
	@FindBy(xpath="//*[@id='container']/div[1]/div[2]/section/div/div/div[2]/div[1]/h2")
	private WebElement textoncolortoolpage;
	
	public void ClickOnFirstSlidingImage(){
		Sliding_FirstImg.click();
	}
	public void ShopSection() {
		shopsection.click(); 
	}
	public void ColorSelectionTool() {
		colortool.click();
	}
	public void BagIcon() {
		bagicon.click();
	}
	public String EmptyCartMessage() {
		return Text_InsideEmptyBag.getText();
	}
	
	public String Redirectedpagetext() {
		return textonredirectedpage.getText();
	}
	
	public String Colortoolpagetext() {
		return textoncolortoolpage.getText();
	}
	
	public boolean Colortooldisplay() {
		return colortool.isDisplayed();
	}
	

}
