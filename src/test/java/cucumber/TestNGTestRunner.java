package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java", glue="stepDefinition",
                   monochrome=true, plugin= {"html:target/cucumber.html", 
                		   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  })
public class TestNGTestRunner extends AbstractTestNGCucumberTests
{
	
}
