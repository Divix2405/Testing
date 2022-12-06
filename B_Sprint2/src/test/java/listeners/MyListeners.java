package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Bbase;
import utils.ExtentReporter;

public class MyListeners extends Bbase implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Start");
		String testMethodName = result.getName();
		extentTest = extentReport.createTest(testMethodName);
		extentTest.log(Status.INFO,testMethodName+" started executing");
				
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String name=result.getName();
		System.out.println("success"+name);
		String testMethodName = result.getName();
		extentTest.log(Status.PASS,testMethodName+" got passed");
		
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		
		String nameF = result.getName();
		
		WebDriver driver = null;
		
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("fial"+nameF);
		
		String SSPath = captureScreenshot(driver,nameF);
		extentTest.addScreenCaptureFromPath(SSPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,nameF+" got failed");
		
	}

	

	@Override
	public void onStart(ITestContext context) {
		
		try {
			extentReport = ExtentReporter.getExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		
	}
	
}
