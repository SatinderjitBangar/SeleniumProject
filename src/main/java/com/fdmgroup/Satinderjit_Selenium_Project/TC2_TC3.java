package com.fdmgroup.Satinderjit_Selenium_Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.Before;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fdmgroup.util.DriverUtilities;

public class TC2_TC3 {
	private DriverUtilities driverUtilities;
	private WebDriver driver;

	@Before
	public void setup() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}

	@Test
	public void tc2() throws IOException, InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(500);

		// Check if the page is redirected to Forgot your password page
		assertEquals("Forgot your password - My Store", driver.getTitle());

		driver.findElement(By.id("email")).sendKeys("summybngr94@gmail.com");
		driver.findElement(By.xpath("//*[@id='form_forgotpassword']/fieldset/p/button")).click();
		String alertText = driver.findElement(By.xpath("//*[@id='center_column']/div/p")).getText();

		// User is taken to a confirmation page which shows the customer's email address
		// that they have used to reset
		assertTrue(alertText.contains("summybngr94@gmail.com"));

		driver.findElement(By.xpath("//*[@id='center_column']/ul/li/a")).click();

		// Checks if the user is sent back to authentication page
		assertEquals("Login - My Store", driver.getTitle());
	}

	@Test
	public void tc3() throws IOException, InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("summybngr94@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();
		Thread.sleep(200);

		// Click T-shirts link
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img")))
				.build().perform(); // Hover over the tshirt
		Thread.sleep(200);

		// Hover over T-shirt and Add a T-shirt to the cart
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]/span")).click();
		Thread.sleep(4000);

		// popup is shown with message Product successfully added to your shopping cart
		String actual_message = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
		assertTrue(actual_message.contains("Product successfully added to your shopping cart"));

		// Click Continue Shopping
		driver.findElement(By.xpath("//span[@title='Continue shopping']/span")).click();

		// Click the Dresses link
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();

		// Hover over dress and Add a dress to the cart
		action.moveToElement(
				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"))).build()
				.perform();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]")).click();

		Thread.sleep(2000);
		// Click proceed to checkout
		driver.findElement(By.xpath("//*[@title='Proceed to checkout']/span")).click();
		Thread.sleep(1000);
		// Click proceed to checkout shopping cart summary
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();

		Thread.sleep(1000);
		// Click Proceed to checkout Address page
		driver.findElement(By.xpath("//*[@name='processAddress']")).click();

		// Tick the terms and conditions Click Proceed to checkout
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath("//*[@name='processCarrier']")).click();

		// Getting the price of dress and shirt added
		double price_shirt = Double
				.valueOf(driver.findElement(By.id("total_product_price_1_1_509172")).getText().substring(1, 6));
		double price_dress = Double
				.valueOf(driver.findElement(By.id("total_product_price_3_13_509172")).getText().substring(1, 6));
		DecimalFormat df = new DecimalFormat("#.##");
		double totalPrice = price_shirt + price_dress + 2; // added 2 dollar for shipping

		assertEquals("44.51", df.format(totalPrice));

		// Click Pay by bank Wire
		driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();

		// click I confirm my order
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();

		// Click back to orders
		driver.findElement(By.xpath("//a[@title='Back to orders']")).click();

		// Check the last order placed displayed by comparing the total of the last
		// order
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		String cost = rows.get(1).findElement(By.xpath(".//td[3]")).getText();
		assertEquals("$44.51", cost);
		driver.close();

	}

}
