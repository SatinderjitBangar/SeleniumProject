package com.fdmgroup.Satinderjit_Selenium_Project;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features", tags = "@CheckIfUpperCase,@CalculateTheTotal", glue = "com\\fdmgroup\\Satinderjit_Selenium_Project")
public class TestRunner {

}