package com.guru99.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith (Cucumber.class)
@CucumberOptions (
		
		plugin = {
				"html:target/built-in-html-report",
				"json:target/Cucumber.json "
		
		},
		tags = "@regression",
		features = "src/test/resources/features/",
		glue = "com/guru99/step_definitions"
//		,strict = true
//		,dryRun = true
		,monochrome =true
		,stepNotifications = true
//		,snippets = SnippetType.CAMELCASE
		)
public class CukeRunner {

}
