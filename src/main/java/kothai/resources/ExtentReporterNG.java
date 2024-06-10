package kothai.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG
{
	
	public static ExtentReports config()
	{
		String path = System.getProperty("user.dir") + "//TestReports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);        //helper class
		reporter.config().setReportName("Web Automation Results");  
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();              //main class
		extent.attachReporter(reporter);          //attach the object of helper class, now it has the knowledge of that class
		extent.setSystemInfo("Tester", "KANMANI");
		return extent;
	}
	
}
