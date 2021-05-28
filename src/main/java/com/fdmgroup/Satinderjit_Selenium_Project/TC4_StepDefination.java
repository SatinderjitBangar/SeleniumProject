package com.fdmgroup.Satinderjit_Selenium_Project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.util.DriverUtilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class TC4_StepDefination {
	private DriverUtilities driverUtilities;
	private WebDriver driver;

	@Before
	public void setup() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}

	@Given("I login to system")
	public void Login() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("amazamaz1@test.ca");
		driver.findElement(By.id("passwd")).sendKeys("Password");
		driver.findElement(By.id("SubmitLogin")).click();
	}

	@When("I go to orders history")
	public void goToOrderHistory() {
		driver.findElement(By.xpath("//*[@title='View my customer account']")).click();
		driver.findElement(By.xpath("//*[@title='Orders']")).click();
	}

	@Then("I calculate the total by going through each row and totaling them individually based on the payment type")
	public void CalculateTotals() {
		List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id='order-list']/tbody/tr"));
		double paymentByCheckTotal = 0, paymentByBankWire = 0;

		for (WebElement row : tableRows) {
			String order_total = row.findElement(By.xpath(".//td[3]")).getText().substring(1, 6);
			double amount = Double.valueOf(order_total);

			if (row.findElement(By.xpath(".//td[4]")).getText().contains("Payment by check")) {
				paymentByCheckTotal = paymentByCheckTotal + amount;
			} else if (row.findElement(By.xpath(".//td[4]")).getText().contains("Bank wire")) {
				paymentByBankWire = paymentByBankWire + amount;
			}

		}
		System.out.println("Total amount paid by Check is : " + paymentByCheckTotal);
		System.out.println("Total amount paid by Bank Wire is : " + paymentByBankWire);
		driver.findElement(By.xpath("//*[@title='Log me out']")).click();
	}

}
