package com.fdmgroup.Satinderjit_Selenium_Project;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.fdmgroup.util.DriverUtilities;

public class TC5_TC6 {
	private DriverUtilities driverUtilities;
	private WebDriver driver;

	@Before
	public void setup() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}

	@Test
	public void tc5() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@title='Recover your forgotten password']")).click();
		driver.findElement(By.id("email")).sendKeys("bzonkabzonka@hotmail.co.uk");
		driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button")).click();
		String error_text = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/ol/li")).getText();
		assertEquals("There is no account registered for this email address.", error_text);
	}

	@Test
	public void tc6() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("summybngr94@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();

		driver.findElement(By.xpath("//*[@title='View my customer account']")).click();
		driver.findElement(By.xpath("//*[@title='Orders']")).click();

		driver.findElement(By.xpath("//*[@title='Reorder']")).click();
		Thread.sleep(2000);

		// click continue shopping
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[2]")).click();

		// Click women tab
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();

		// Hover over blouse
		Actions action = new Actions(driver);
		action.moveToElement(
				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img"))).build()
				.perform();

		Thread.sleep(3000);
		// click add to cart
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/div[2]/a[1]")).click();

		Thread.sleep(4000);

		driver.findElement(By.xpath("//*[@title='Proceed to checkout']")).click();

		String expectedAmount = driver.findElement(By.id("total_price")).getText();

		// Click proceed to checkout shopping cart summary
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();

		Thread.sleep(1000);
		// Click Proceed to checkout Address page
		driver.findElement(By.xpath("//*[@name='processAddress']")).click();

		// Tick the terms and conditions Click Proceed to checkout
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath("//*[@name='processCarrier']")).click();

		// Click on pay by check
		driver.findElement(By.xpath("//*[@title='Pay by check.']")).click();

		// click I confirm my order
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();

		// Click back to orders
		driver.findElement(By.xpath("//a[@title='Back to orders']")).click();

		// Checking if the user is router back to Order history page
		assertEquals("Order history - My Store", driver.getTitle());

		// check if the new order is placed and the total is correct
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		String actualCostOnOrderHistory = rows.get(1).findElement(By.xpath(".//td[3]")).getText();

		assertEquals(expectedAmount, actualCostOnOrderHistory);

		driver.close();

	}
}
