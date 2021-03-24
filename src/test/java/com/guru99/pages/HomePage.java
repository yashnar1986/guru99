package com.guru99.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99.utilities.Driver;

public class HomePage {

	public HomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public List<WebElement> getTopic(String topic) {
		Map<String, Integer> map = new HashMap<>();
		map.put("Testing", 0);
		map.put("Must Learn!", 10);
		String customXpath = "//ul[@id='java_technologies']";
		List<WebElement> listOfSections = Driver.getDriver().findElements(By.xpath(customXpath));
		WebElement section = listOfSections.get(map.get(topic));
		List<WebElement> topicsOfSection = section.findElements(By.tagName("li"));
		return topicsOfSection;
	}

	public WebElement getSectionName(String name) {
		WebElement sectionName = Driver.getDriver().findElement(By.xpath("//b[.='" + name + "']"));
		return sectionName;
	}

	@FindBy(xpath = "//input[@id='gsc-i-id2']")
	public WebElement searchBox;

	@FindBy(xpath = "//div[@id='resInfo-1']")
	public WebElement searchResultInfo;
	
	@FindBy(xpath = "//span[@class='gcsc-find-more-on-google-query']")
	public WebElement searchedCourse;
	
	

}
