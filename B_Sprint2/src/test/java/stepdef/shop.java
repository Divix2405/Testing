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

import Pageobj.ShopPg;
import base.Bbase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class shop extends Bbase{
	ShopPg s;
	public WebDriver driver;
	public void AcceptCookies() {
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
	}

	/*@After
	public void tearDown() {
		driver.quit();
	}*/
	
	@Given("^User has opened the site in browser$")
    public void user_has_opened_the_site_in_browser() throws IOException  {
		loadPropertiesFile();
		Map<String, Object> prefs=new HashMap<String,Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		options.addArguments("--disable-notifications");
		driver.get("https://www.asianpaints.com/");
		driver.manage().deleteAllCookies();
		AcceptCookies();
		//driver.manage().window().maximize();
    }

    @When("^navigated to Shop section$")
    public void navigated_to_shop_section2()  {
    	 s=new ShopPg(driver);
         s.ShopSection();
    }

    @When("^User clicks on bag icon in the header in right corner$")
    public void user_clicks_on_bag_icon_in_the_header_in_right_corner()  {
    	s=new ShopPg(driver);
         s.BagIcon();
    }

    @Then("^User clicks on first sliding image and should be redirected to respective site$")
    public void user_clicks_on_first_sliding_image_and_should_be_redirected_to_respective_site() throws InterruptedException  {
    	String ct=driver.getWindowHandle();
    	s=new ShopPg(driver);
    	Thread.sleep(2000);
		s.ClickOnFirstSlidingImage();
		for(String tab:driver.getWindowHandles()) {//try making this as func in stepdef and call it
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		Assert.assertEquals(s.Redirectedpagetext(),"Self-Adhesive Wallpaper Range" );
    }

    @Then("^User should see color selection tool category$")
    public void user_should_see_color_selection_tool_category()  {
    	s=new ShopPg(driver);
    	Assert.assertTrue(s.Colortooldisplay());
    }

    @Then("^User clicks on color selection tool and User should see content inside color selection tool$")
    public void user_clicks_on_color_selection_tool_and_user_should_see_content_inside_color_selection_tool()  {
    	String ct=driver.getWindowHandle();
    	s=new ShopPg(driver);
		s.ColorSelectionTool();
		for(String tab:driver.getWindowHandles()) {
			if(!tab.equals(ct)) {
				driver.switchTo().window(tab);
			}
		}
		Assert.assertEquals(s.Colortoolpagetext(), prop.getProperty("ColorMSG"));
    }

    @Then("^User should see the message that cart is empty$")
    public void user_should_see_the_message_that_cart_is_empty()  {
    	s=new ShopPg(driver);
    	Assert.assertEquals(s.EmptyCartMessage(), prop.getProperty("Empty_cart_msg"));
    }

    @And("^Navigated to shop section$")
    public void navigated_to_shop_section1()  {
    	s=new ShopPg(driver);
        s.ShopSection();
    }
	
	

}