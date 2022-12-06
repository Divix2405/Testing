package base;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;


public class Bbase {
	public Properties prop;
    public void loadPropertiesFile() throws IOException {
		
		prop = new Properties();
		
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\Prop\\Property.properties");
		
		FileInputStream fis = new FileInputStream(propFile);
		
		prop.load(fis);
		
	}
	
           /*public WebDriver openBrowser(String browserName)
           {
					
					WebDriver driver = null;
					
					if(browserName.equals("chrome")) {
						
						driver = new ChromeDriver();
						
					}	
					else if(browserName.equals("edge")) {
						
						driver = new EdgeDriver();
						
					}
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					
					return driver;
		
		
	       }*/
           
     public String captureScreenshot(WebDriver driver,String testName) {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"\\SS\\"+testName+".png";
		File destSS = new File(dest);
		try {
			FileHandler.copy(source, destSS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest;
	}    
           
           

}
