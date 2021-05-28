package com.fdmgroup.Satinderjit_Selenium_Project;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.fdmgroup.util.DriverUtilities;

public class TC1 {
	private DriverUtilities driverUtilities;
	private WebDriver driver;

	@Before
	public void setup() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}

	@Test
	public void tc1() throws InterruptedException {
		driver.get(" http://automationpractice.com/index.php");

		// click on sign in button on homepage
		driver.findElement(By.xpath("//*[@title='Log in to your customer account']")).click();

		// entered the valid email address and clicked the create account
		driver.findElement(By.id("email_create")).sendKeys("amazamaz25@test.ca");
		driver.findElement(By.id("SubmitCreate")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys("Satinderjit");
		driver.findElement(By.id("customer_lastname")).sendKeys("Bangar");
		driver.findElement(By.id("passwd")).sendKeys("Password");
		driver.findElement(By.id("address1")).sendKeys("Dummy Street");
		driver.findElement(By.id("city")).sendKeys("that city");
		WebElement dropdown = driver.findElement(By.id("id_state"));
		Select select = new Select(dropdown);
		select.selectByValue("3");
		driver.findElement(By.id("postcode")).sendKeys("12457");
		driver.findElement(By.id("phone_mobile")).sendKeys("6477728754");
		driver.findElement(By.id("submitAccount")).click();
		assertEquals("My account - My Store", driver.getTitle());

		driver.findElement(By.xpath("//*[@title='Log me out']")).click();
		driver.findElement(By.id("email")).sendKeys("amazamaz25@test.ca");
		driver.findElement(By.id("passwd")).sendKeys("Password");
		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("My account - My Store", driver.getTitle());
		driver.close();
	}

}
