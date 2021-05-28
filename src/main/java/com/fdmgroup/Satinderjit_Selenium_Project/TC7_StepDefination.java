package com.fdmgroup.Satinderjit_Selenium_Project;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.util.DriverUtilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class TC7_StepDefination {
	private DriverUtilities driverUtilities;
	private WebDriver driver;

	@Before
	public void setup() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}

	@Given("User logIn to system")
	public void Login() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("amazamaz1@test.ca");
		driver.findElement(By.id("passwd")).sendKeys("Password");
		driver.findElement(By.id("SubmitLogin")).click();
	}

	@When("User goes to orders history")
	public void goToOrderHistory() {
		driver.findElement(By.xpath("//*[@title='View my customer account']")).click();
		driver.findElement(By.xpath("//*[@title='Orders']")).click();
	}

	@Then("I test that all Order references contain only letters and is capitalised")
	public void checkUpperCaseAndLetter() {
		List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id='order-list']/tbody/tr"));
		for (WebElement row : tableRows) {
			String order_number = row.findElement(By.xpath(".//td[1]/a")).getText();

			// Checks if the charachters are all upercase
			assertTrue(order_number.matches("^[A-Z]*$"));

		}
		driver.close();
	}

}
