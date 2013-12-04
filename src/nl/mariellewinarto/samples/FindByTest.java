package nl.mariellewinarto.samples;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindByTest {
	static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File objFileChrome = new File(
				"d:\\users\\winartma\\Desktop\\selenium-temp\\Benodigdheden\\Benodigdheden\\Opgave 3\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",
				objFileChrome.getAbsolutePath());
		driver = new ChromeDriver();
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		driver.get("file:///D:/users/winartma/Documents/GitHub/snippets/resources/find-by-test.html");
		driver.findElement(By.linkText("Smoke Sequential")).click();
		WebElement link = driver.findElement(By.partialLinkText("Sequential"));
		
	}

}
