package emailTest;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(
		features = "Features",
		glue = {"stepDefination"}
		)
public class CucumberRunner {

	
}
