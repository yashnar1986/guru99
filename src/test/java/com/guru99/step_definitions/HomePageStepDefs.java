package com.guru99.step_definitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;

import com.guru99.pages.HomePage;
import com.guru99.utilities.Driver;
import com.guru99.utilities.LoggerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.guru99.utilities.ExcelUtils;

import com.guru99.utilities.ConfigReader;

public class HomePageStepDefs {
	
	Logger logger = LoggerUtils.getLogger(HomePageStepDefs.class);
	HomePage homepage = new HomePage();
	String actual = "";
	String expected = "";
	
	@Given("I am in the home page")
	public void i_am_in_the_home_page() {
		logger.info("Navigating to a Home page");
		Driver.getDriver().get(ConfigReader.getConfiguration("url"));
		logger.info("Verifying the title of Home Page");
		actual = Driver.getDriver().getTitle();
		expected = "Meet Guru99 - Free Training Tutorials & Video for IT Courses";
		assertEquals(expected, actual);
		logger.info("Title of the Home page should be " + expected);
	}
	
	@When("I check following {string} in Tutorials Library")
	public void i_check_following_in_Tutorials_Library(String section) {
		logger.info("I am selecting the section " + section);
		actual = homepage.getSectionName(section).getText();
		expected = section.toUpperCase();
		assertEquals(actual, expected);
		logger.info("Name of the section should be " + section);
	}

	@Then("I should be able to verify the topics {int} in that {string}")
	public void i_should_be_able_to_verify_the_topics_in_that(Integer count, String section) {
		logger.info("I am counting the topics of the " + section);
		Integer actual = homepage.getTopic(section).size();
		Integer expected = count;
		assertEquals(actual, expected);
		logger.info("I verify that " + section + " has " + count + " of topics");
	}



	@Then("I start typing to the search box and press the enter and save the count of result in the excel doc")
	public void i_start_typing_to_the_search_box_and_press_the_enter() {
		ExcelUtils sheet = new ExcelUtils("Data.xlsx", "sheet1");
		List<Map<String, String>> allRows = sheet.getDataAsList();
		for (int i = 0; i < allRows.size(); i++) {

			Map<String, String> row = allRows.get(i);

			logger.info("I am typing the " + row.get("Course Topics") + " in the search box and press ENTER key");
			homepage.searchBox.sendKeys(row.get("Course Topics") + Keys.ENTER);
			String text = homepage.searchResultInfo.getText();
			String resultTotal = text.substring(6, 7).concat(text.substring(8, 11));
			logger.info("I save the result " + resultTotal +" in the excel doc");
			sheet.setCellData(resultTotal, "Results Count", i+1);
			logger.info("I should be able to see the results for the searched " + row.get("Course Topics") + " course in the page");
			actual = homepage.searchedCourse.getText();
			expected = row.get("Course Topics");
			logger.info("I'm navigating to the Home page to search another course");
			Driver.getDriver().get(ConfigReader.getConfiguration("url"));
			
		}
	}


}
