package tests;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import Excel.excel;
import Pageobj.Buy;
import Pageobj.Pincode;
import Pageobj.ShopPg;
import base.Bbase;
//import utils.ExcelReader;

public class TestNGDemo extends Bbase{
	public WebDriver driver;
	Buy b=new Buy(driver);
	Pincode p=new Pincode(driver);
	public void AcceptCookies() {
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		loadPropertiesFile();
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver=new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		driver.get("https://www.asianpaints.com/");
		driver.manage().deleteAllCookies();
		AcceptCookies();
		//driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=13)
	public void Shop_Redirection(){
		//Success
		ShopPg s=new ShopPg(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		s.ShopSection();
		String ct=driver.getWindowHandle();
		s.ClickOnFirstSlidingImage();
		for(String tab:driver.getWindowHandles()) {//try making this as func in stepdef and call it
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		Assert.assertEquals(s.Redirectedpagetext(),"Self-dhesive Wallpaper Range" );	
	}
	@Test(priority=1)
	public void Shop_ColorSelection_Display(){
		//Success
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		ShopPg s=new ShopPg(driver);
		s.ShopSection();
		Assert.assertTrue(s.Colortooldisplay());
		
	}
	@Test(priority=2)
	public void Shop_ColorSelection_Content(){
		//Success
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ShopPg s=new ShopPg(driver);
		s.ShopSection();
		//AcceptCookies();
		String ct=driver.getWindowHandle();
		s.ColorSelectionTool();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		Assert.assertEquals(s.Colortoolpagetext(), prop.getProperty("ColorMSG"));
	}
	@Test(priority=3)
	public void Shop_EmptyCart(){
		//success
		ShopPg s=new ShopPg(driver);
		//AcceptCookies();
		s.BagIcon();
		Assert.assertEquals(s.EmptyCartMessage(), prop.getProperty("Empty_cart_msg"));
	}
	@Ignore//(priority=4)
	public void Buy_SeeCheckoutPage() throws InterruptedException{
		//Successs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Buy b=new Buy(driver);
		AcceptCookies();
		b.signin_phoneno();
		Thread.sleep(2000);
		b.signin_otp();
		b.gotoShopSection();
		b.clickwallsticker();
		String ct=driver.getWindowHandle();
		b.clickonkids();
		
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		b.FillPincode();
		b.clickbuynow();
		Assert.assertTrue(b.CheckoutpageText());
	}
	@Ignore//(priority=5)
	public void Buy_ItemInsideCart() throws InterruptedException{
		//success
		Buy b=new Buy(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		b.signin_phoneno();
		b.signin_otp();
		b.gotoShopSection();
		b.clickwallsticker();
		String ct=driver.getWindowHandle();
		b.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		b.FillPincode();
		b.Insideitem_addtocartclick();
		b.BagIcon_item();
		Assert.assertTrue(b.Iteminsidebag());
	}
	@Test(priority=6)
	public void Buy_NoLoginNoBuy(){
		//success
		Buy b=new Buy(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		b.gotoShopSection();
		b.clickwallsticker();
		String ct=driver.getWindowHandle();
		b.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		b.FillPincode();
		b.clickbuynow();
		Assert.assertFalse(b.checkoutnotdisplayed());
		
	}
	@Test(priority=7)
	public void Buy_AccessBag(){
		//success
		Buy b=new Buy(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		b.BagIcon();
		//fix prop ; error this.prop missing
		Assert.assertEquals(b.EmptyCartMessage(), prop.getProperty("Empty_cart_msg"));
	}
	
	@Test(priority=8,dataProvider="getData")
	public void Pincode_InvalidPincode (String g,String h){
		Pincode p=new Pincode(driver);
		//AcceptCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		p.gotoShopSection();
		p.clickwallsticker();
		String ct=driver.getWindowHandle();
		p.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		p.EnterPincode_Invalid(g);
		p.ClickonCheck();
		Assert.assertEquals(p.Errormsg_Invalid(), prop.getProperty("Fail_msg"));
	}
	@DataProvider
	public Object[][] getData() throws IOException{
		
        Object[][] data = excel.readData();
		
		return data;
	}
	@Test(priority=9)
	public void Pincode_PincodeFormDisplay() throws InterruptedException{
		//success
		Pincode p=new Pincode(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		p.gotoShopSection();
		//AcceptCookies();
		p.clickwallsticker();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		Thread.sleep(6000);
		p.Addtocart_BelowItem();
		Assert.assertTrue(p.EnterPincodeForm());
	}
	@Test(priority=10)
	public void Pincode_MissingPincode(){
		//success
		Pincode p=new Pincode(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		p.gotoShopSection();
		p.clickwallsticker();
		String ct=driver.getWindowHandle();
		p.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		p.EnterPincode_Nothing();
		p.ClickonCheck();
		Assert.assertEquals(p.Errormsg_Missing(),"Please enter valid pincode");
	}
	@Test(priority=11)
	public void Pincode_ValidPincode(){
		//success
		Pincode p=new Pincode(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		p.gotoShopSection();
		p.clickwallsticker();
		String ct=driver.getWindowHandle();
		p.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		p.EnterPincode_Valid();
		p.ClickonCheck();
		Assert.assertTrue(p.Errormsg_Valid());
	}
	@Test(priority=12)
	public void Pincode_ItemNotInCart(){
		//success
		Pincode p=new Pincode(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AcceptCookies();
		p.gotoShopSection();
		p.clickwallsticker();
		String ct=driver.getWindowHandle();
		p.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		p.Addtocart_InsideItem();
		Assert.assertEquals(p.Errormsg_Missing(),"Please enter valid pincode");
	}
	/*
	@Ignore
	public void searchForAvalidProduct() throws InterruptedException {
		// TODO Auto-generated method stub
		/*driver=new ChromeDriver();
		driver.manage().window().maximize();*/
		
		/*code to reach chekcout page from buy now
		driver.get("https://www.asianpaints.com/online-shop/wall-stickers/kids-collection/kids-ships-sailor-birds.html");
		driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("211012");
		driver.findElement(By.xpath("//a[@id='checkout']")).click();//buy now button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\'loginMobile\']")).sendKeys("9575203684");
		driver.findElement(By.xpath("//button[@class='ctaText modal__variant-login--submit']")).click();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter otp:");on
		String s= sc.nextLine();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='loginOtp']")).sendKeys(s);
		driver.findElement(By.xpath("//*[@id=\'validate-otp\']/div[3]/button")).click();
		Thread.sleep(8000);
		System.out.println(driver.findElement(By.xpath("//*[@id=\"checkOutTab-1\"]/div/div[2]/h2")).getText())//price detail text on checkoutpage
		*/
		
		
		/*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://www.asianpaints.com/online-shop.html");
		driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		String ct=driver.getWindowHandle();
    	driver.findElement(By.xpath("//div[@id='slick-slide10']//img[@title='AP_Shop_Banner_Festive_2A_desktop']")).click();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		driver.switchTo().window(driver.getCurrentUrl());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"container\"]/div[1]/div[2]/section/div/div/div[2]/div[1]/h2/text()")).getText(),"Self-Adhesive Wallpaper Range");
		*/
		//color selectuin tool icon
		//driver.findElement(By.xpath("//img[@title=\"ap-shop-colour-selection-tool-thumbnail-asian-paints\"]")).click();
		
		//text on color selection page
		//Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"container\"]/div[1]/div[2]/section/div/div/div[2]/div[1]/h2")).isDisplayed());
		
		
		/*give correct pincode and get sucess text
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.asianpaints.com/online-shop/wall-stickers/kids-collection/kids-ships-sailor-birds.html");
		driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("211012");
		driver.findElement(By.xpath("//div[@class='google-map-icon pincode-service-check']//a[@href='javascript:;']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='responseMessage']")).isDisplayed());*/
		
		//home page login icon
		//driver.findElement(By.xpath("//span[@class=\'spriteIcon-Firstfold profileIcon\']")).click();
		
		//click on accept cookie
		//driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		
		/*irrelevant but might be imp
		{
		//JavascriptExecutor js = (JavascriptExecutor) driver;
	    //Find element by link text and store in variable "Element"      		
	    //WebElement Element = driver.findElement(By.xpath("//*[@id=\'thin-banner-info\']/ul/li[1]/div[2]/a/span[1]"));
	    //This will scroll the page till the element is found		
	    //js.executeScript("arguments[0].scrollIntoView();", Element);
		}*/
		
		/*add to cart bleow frst item sometimes-(need to scroll so that add to cart is visible and pincode form is displayed
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.asianpaints.com/online-shop/wall-stickers.html");
		driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='thin-banner-info']/ul/li[1]/div[2]/a/span[1]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\'cartPinCodeModal\']/div/div/div[2]/form")).isDisplayed());*/
		
		
		//shop section
		//driver.findElement(By.xpath("//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")).click();
		
		//wall sticker
		//driver.findElement(By.xpath("//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")).click();
		
		//add to cart(inside first wall stikcer item
		/*driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("211012");
		driver.findElement(By.xpath("//*[@id=\"addToCart\"]")).click();*/
		
		//clicking on add to cart inside item gives this message in form
		//Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"cartPinCodeModal\"]/div/div/div[2]/form/legend/span")).getText(),"Please enter your serviceable Pin Code to add Product to Cart");
		
		//bag icon
		//driver.findElement(By.xpath("//*[@id='container']/header/div/div[3]/div[2]/div[2]/button")).click();
		
		//item inside bag
		//Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"container\"]/header/div/div[3]/div[2]/div[2]/div/div/ul/li/a")).isDisplayed());
		
		//  text inside emtpy bag
		//Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"header-minicart\"]/div/div/div/p")).getText(),"Your cart is empty");
		
		/*to disable popoups etc
		add/	
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);*/
		
		/*driver=openBrowser("chrome");
		driver.get("https://www.asianpaints.com/");
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-notifications");
		driver.manage().deleteAllCookies();*/
		
		/*cheking if image clicks in shop or not
		
		driver.get("https://www.asianpaints.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")).click();
		String ct=driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@id='slick-slide10']//img[@title='AP_Shop_Banner_Festive_2A_desktop']")).click();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		//driver.switchTo().window(driver.getCurrentUrl());
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='h2 section-header']")).getText(),"Self-Adhesive Wallpaper Range" );
		*/
		/*trying to get to pincode in wall and stickers in shop section
		
		driver.get("https://www.asianpaints.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/a/div[1]/picture/img")).click();
		driver.findElement(By.xpath("//button[@id='cartPinCodeEditBtn']")).click();//EDIT field does not appear in test environment*/
		
		
		
		//try this to disable popups- Chromeoptions d=new Chromeoptions();
		//driver.get("https://www.asianpaints.com/");
		//Bag icon- driver.findElement(By.xpath("//button[@class=\'iconLinks iconLinks__cart cart-js-handle spriteIcon-Firstfold cartIcon border-0 bg-transparent track_minicart\']")).click();
		
		/*login from homepage
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[@class='spriteIcon-Firstfold profileIcon']")).click();
		
		//phone field inside login
		driver.findElement(By.xpath("//*[@id=\'loginMobile\']")).sendKeys("9575203684");
		
		//submit button
		driver.findElement(By.xpath("//button[@class='ctaText modal__variant-login--submit']")).click();
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter otp:");
		String s= sc.nextLine();
		//Thread.sleep(1000);
		
		//otp enter area
		driver.findElement(By.xpath("//*[@id='loginOtp']")).sendKeys(s);
		
		//otp submit button
		driver.findElement(By.xpath("//*[@id=\'validate-otp\']/div[3]/button")).click();*/
		
		/*click on shop got to wall stickers and click on first element and switch focus
		driver.findElement(By.xpath("//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")).click();
		driver.findElement(By.xpath("//*[@id=\'onetrust-accept-btn-handler\']")).click();//cookies accept
		driver.findElement(By.xpath("//img[@title='ap-shop-wall-stickers-thumbnail-asian-paints']")).click();
		driver.findElement(By.xpath("//*[@id=\'thin-banner-info\']/ul/li[1]/a/img")).click();
		String ct=driver.getWindowHandle();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		*/
		
		/*00000 for incorrect , blank for no pincode
		 
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("000000");
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("");
		
		Check button next to pincode
		driver.findElement(By.xpath("//div[@class='google-map-icon pincode-service-check']//a[@href='javascript:;']")).click();
		
		Error msg when wrong pincode
		Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space()='This Pin Code is not Serviceable.']")).getText(),"This Pin Code is not Serviceable.");
		
		Error message when no pincode
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\'responseMessage\']/span")).getText(),"Please enter valid pincode");
		
		/*entering phone no and otp from buy now button 
		 
		driver.findElement(By.xpath("//input[@class='pincode']")).sendKeys("211012");
		driver.findElement(By.xpath("//a[@id='checkout']")).click();
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\'loginMobile\']")).sendKeys("9575203684");
		driver.findElement(By.xpath("//button[@class='ctaText modal__variant-login--submit']")).click();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter otp:");
		String s= sc.nextLine();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='loginOtp']")).sendKeys(s);
		driver.findElement(By.xpath("//*[@id=\'validate-otp\']/div[3]/button")).click();
	}*/
	/*@Ignore
	public void ClickableImage() throws InterruptedException {
		
		//driver=openBrowser("chrome");//using base class
		//driver.get("https://www.asianpaints.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//span[@class='iconTextLinks__text visible-in-Desktop'][normalize-space()='SHOP']")).click();
		String ct=driver.getWindowHandle();
		Thread.sleep(3000);//to click on second img
		driver.findElement(By.xpath("//*[@id=\"slick-slide11\"]/a/picture/img")).click();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		//driver.switchTo().window(driver.getCurrentUrl());
		//Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='h2 section-header']")).getText(),"Self-Adhesive Wallpaper Range" );
    }		*/

}
