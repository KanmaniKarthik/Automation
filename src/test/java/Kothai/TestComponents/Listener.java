package Kothai.TestComponents;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import kothai.resources.ExtentReporterNG;

public class Listener extends Btest implements ITestListener
{
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ITestListener.super.isEnabled();
	}


	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.config();
	
	//Thread safe : it doesn't interrupt other overriding variables 
	//for parallel test
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	
	
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName()); 
		extentTest.set(test);  // assigns a unique thread id
	}
	
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().log(Status.FAIL, "Test failed");
		extentTest.get().fail(result.getThrowable()); // (for parallel test)
		//test.fail(result.getThrowable());  //(for series test) prints the error message in the report
		
		try
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e1)
		{
			
			e1.printStackTrace();
		}
		
		
	/*    String picPath = getScreenshot(result.getMethod().getMethodName());  //below code are surrounded by try catch exception isteadof these codes
		test.addScreenCaptureFromPath(picPath, "result.getMethod().getMethodName()");   */ 

		String picPath = null;
		try
		{
			picPath = getScreenshot(result.getMethod().getMethodName(), driver);
		} 
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(picPath, result.getMethod().getMethodName());
	}
	
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
	}
	
	
	
}