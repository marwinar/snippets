package nl.mariellewinarto.samples;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitSample {

	public static void main(String[] args) {
		File objFileChrome = new File(
				"d:\\users\\winartma\\Desktop\\selenium-temp\\Benodigdheden\\Benodigdheden\\Opgave 3\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",
				objFileChrome.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		
		driver.get("file:///D:/users/winartma/Documents/GitHub/snippets/resources/delay.html");
		WebDriverWait wait = new WebDriverWait(driver, 20, 2000);
		WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.id("my_link")));
		link.click();
		
	}

}
