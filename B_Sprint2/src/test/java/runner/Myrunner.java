package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin={"html:Reports/cucumberReportFin.html"},features="src/test/java/feature",
glue="stepdef")
public class Myrunner extends AbstractTestNGCucumberTests {
	
	

}
