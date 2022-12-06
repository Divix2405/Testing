package stepdef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import Pageobj.Buy;
import base.Bbase;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class buying extends Bbase{
	public WebDriver driver;
	Buy b;
	public void AcceptCookies() {
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
	}

	/*@After
	public void tearDown() {
		driver.quit();
	}*/
	@Given("^User has opened url and signed in$")
    public void user_has_opened_url_and_signed_in() throws IOException, InterruptedException  {
		loadPropertiesFile();
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver=new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		driver.get("https://www.asianpaints.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		AcceptCookies();
		//driver.manage().window().maximize();
		b=new Buy(driver);
		b.signin_phoneno();
		Thread.sleep(2000);
		b.signin_otp();    
	}

    @When("^User clicks on buy now$")
    public void user_clicks_on_buy_now()  {
         b=new Buy(driver);
         b.clickbuynow();
    }

    @When("^User clicks on add to cart once$")
    public void user_clicks_on_add_to_cart_once()  {
    	b=new Buy(driver);
    	b.Insideitem_addtocartclick();
    }

    @When("^User click on bag icon$")
    public void user_click_on_bag_icon()  {
    	b=new Buy(driver);
    	b.BagIcon();
    }

    @Then("^User should see checkout page$")
    public void user_should_see_checkout_page()  {
    	b=new Buy(driver);
    	Assert.assertTrue(b.CheckoutpageText());
    }

    @Then("^user should see items in their cart$")
    public void user_should_see_items_in_their_cart()  {
    	b=new Buy(driver);
    	Assert.assertTrue(b.Iteminsidebag());
    }
    @Given("user has opened url")
    public void user_has_opened_url() throws IOException {
    	loadPropertiesFile();
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver=new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		driver.get("https://www.asianpaints.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		AcceptCookies();
		//driver.manage().window().maximize();
        
    }
    @Then("Empty bag should open")
    public void empty_bag_should_open() {
    	b=new Buy(driver);
    	Assert.assertEquals(b.EmptyCartMessage(), prop.getProperty("Empty_cart_msg"));
    }


    @Then("^User should see not see checkout page$")
    public void user_should_see_not_see_checkout_page()  {
    	b=new Buy(driver);
    	Assert.assertFalse(b.checkoutnotdisplayed());
    }

    

    @And("^navigate to shop section$")
    public void navigate_to_shop_section()  {
    	b=new Buy(driver);
    	b.gotoShopSection();
    }

    @And("^clicked on wall stickers$")
    public void clicked_on_wall_stickers()  {
    	b=new Buy(driver);
    	b.clickwallsticker();
    }

    @And("^clicked on KIDS SHIPS SAILOR BIRDS WALL STICKERS & DECALS BY ASIAN PAINTS and type a correct pincode$")
    public void clicked_on_kids_ships_sailor_birds_wall_stickers_decals_by_asian_paints_and_type_a_correct_pincode()  {
    	b=new Buy(driver);
		String ct=driver.getWindowHandle();
		b.clickonkids();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		b.FillPincode();
    }

    @And("^open the bag icon in top right corner$")
    public void open_the_bag_icon_in_top_right_corner()  {
         b=new Buy(driver);
         b.BagIcon_item();
         
    }
	
	
	

}
