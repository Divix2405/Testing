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

import Pageobj.Pincode;
import base.Bbase;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class pincode extends Bbase{
	public WebDriver driver;
	Pincode p;
	public void AcceptCookies() {
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
	}

	/*@After
	public void tearDown() {
		driver.quit();
	}*/
	 @Given("^User has opened the site$")
	    public void user_has_opened_the_site() throws IOException  {
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

	    @When("^Clicks on check$")
	    public void Clicks_on_check()  {
	    	p=new Pincode(driver);
	    	p.ClickonCheck();
	         
	    }

	    @When("^Clicked on wall stickers option$")
	    public void Clicked_on_wall_stickers_option()  {
	    	p=new Pincode(driver);
	    	p.clickwallsticker();
	    }
	    @Then("Service message is displayed")
	    public void service_message_is_displayed() {
	        p=new Pincode(driver);
	        Assert.assertEquals(p.Errormsg_Missing(),"Please enter valid pincode");
	    }


	    @When("^clicked on kids ships sailor birds- wall sticker and User clicks on Add to cart$")
	    public void clicked_on_kids_ships_sailor_birds_wall_sticker_and_user_clicks_on_add_to_cart()  {
	    	p=new Pincode(driver);
	    	String ct=driver.getWindowHandle();
			p.clickonkids();
			for(String tab:driver.getWindowHandles()) {
				if(!tab.equals(ct)) {
					driver.switchTo().window(tab);
				}
			}
			p.Addtocart_InsideItem();
	    }

	    @Then("^Service message is displayed the area is not serivceable$")
	    public void service_message_is_displayed_the_area_is_not_serivceable()  {
	    	p=new Pincode(driver);
	    	Assert.assertEquals(p.Errormsg_Invalid(), prop.getProperty("Fail_msg"));
	    }

	    @Then("^User should see enter pincode form$")
	    public void user_should_see_enter_pincode_form()  {
	    	p=new Pincode(driver);
	    	Assert.assertTrue(p.EnterPincodeForm());
	    }

	    @Then("^Service message is displayed the area is serivceable$")
	    public void service_message_is_displayed_the_area_is_serivceable()  {
	    	p=new Pincode(driver);
	    	Assert.assertTrue(p.Errormsg_Valid());
	    }

	    @Then("^Appropriate error message should be shown under pincode bar.$")
	    public void appropriate_error_message_should_be_shown_under_pincode_bar()  {
	    	p=new Pincode(driver);
	    	Assert.assertEquals(p.Errormsg_Missing(),"Please enter valid pincode");
	    }

	    @And("^navigated to shop section$")
	    public void navigated_to_shop_section()  {
	    	p=new Pincode(driver);
	    	p.gotoShopSection();
	    }

	    @And("^clicked on wall stickers option$")
	    public void clicked_on_wall_stickers_option()  {
	    	p=new Pincode(driver);
	    	p.clickwallsticker();
	    }

	    @And("^clicked on kids ships sailor birds- wall sticker and user enters a invalid six digit integer pincode$")
	    public void clicked_on_kids_ships_sailor_birds_wall_sticker_and_user_enters_a_invalid_six_digit_integer_pincode()  {
	    	p=new Pincode(driver);
	    	String ct=driver.getWindowHandle();
			p.clickonkids();
			for(String tab:driver.getWindowHandles()) {
				if(!tab.equals(ct)) {
					driver.switchTo().window(tab);
				}
			}
			p.EnterPincode_Invalid("000000");
	    }

	    @And("^User clicks add to cart on the first item$")
	    public void user_clicks_add_to_cart_on_the_first_item() throws InterruptedException  {
	    	p=new Pincode(driver);
	    	Thread.sleep(7000);
	    	p.Addtocart_BelowItem();
	    }

	    @When("^clicked on kids ships sailor birds- wall sticker and user enters nothing in pincode$")
	    public void clicked_on_kids_ships_sailor_birds_wall_sticker_and_user_enters_nothing_in_pincode()  {
	    	p=new Pincode(driver);
	    	//p.clickwallsticker();
			String ct=driver.getWindowHandle();
			p.clickonkids();
			for(String tab:driver.getWindowHandles()) {
				if(!tab.equals(ct)) {
					driver.switchTo().window(tab);
				}
			}
			p.EnterPincode_Nothing();
			
	    }

	    @And("^clicks on check$")
	    public void clicks_on_check()  {
	    	p=new Pincode(driver);
	    	p.ClickonCheck();
	    }

	    @When("^clicked on kids ships sailor birds- wall sticker and user enters a valid six digit integer pincode$")
	    public void clicked_on_kids_ships_sailor_birds_wall_sticker_and_user_enters_a_valid_six_digit_integer_pincode()  {
	    	p=new Pincode(driver);
	    	//p.clickwallsticker();
			String ct=driver.getWindowHandle();
			p.clickonkids();
			for(String tab:driver.getWindowHandles()) {
				if(!tab.equals(ct)) {
					driver.switchTo().window(tab);
				}
			}
			p.EnterPincode_Valid();
			
	    }
	
	

}
