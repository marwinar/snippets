package nl.mariellewinarto.samples;

import static org.junit.Assert.assertTrue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class SimpleExample {
	
	private enum Browser {
		INTERNET_EXPLORER, FIREFOX, CHROME, REMOTE_FIREFOX
	}

	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "off"); 
		System.out.println("test");
		Browser activeBrowser = Browser.CHROME;
		driver = getNewWebDriver(activeBrowser);

		baseUrl = "D:\\sample-html\\form.html";
		// Set driver timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	private WebDriver getNewWebDriver(Browser activeBrowser) throws MalformedURLException {
		switch (activeBrowser) {
		case INTERNET_EXPLORER:
			File objFile = new File(
					"d:\\users\\winartma\\Desktop\\selenium-temp\\Benodigdheden\\Benodigdheden\\Opgave 3\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", objFile.getAbsolutePath());

			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);

			return new InternetExplorerDriver(ieCapabilities);
		case FIREFOX:
			return new FirefoxDriver();
		case CHROME:
			File objFileChrome = new File(
					"d:\\users\\winartma\\Desktop\\selenium-temp\\Benodigdheden\\Benodigdheden\\Opgave 3\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					objFileChrome.getAbsolutePath());

			return new ChromeDriver();
		case REMOTE_FIREFOX:
			// 127.0.0.1:4444/wd/hub
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			URL huburl = new URL("http://127.0.0.1:4444/wd/hub");
			driver = new RemoteWebDriver(huburl, capability);
		default:
			return new FirefoxDriver();
		}
	}

	@Test
	public void testChildElements() {
		driver.get(baseUrl);
		WebElement rootElement = driver.findElement(By.name("something"));
		WebElement a = rootElement.findElement(By.name("a"));
		WebElement b = rootElement.findElement(By.name("b"));
		WebElement c = rootElement.findElement(By.name("c"));
		
		a.sendKeys("first child element [a]");
		b.sendKeys("password");
		c.submit();
	}
	
	@Test
	public void testScroll() {
		driver.get("http://stackoverflow.com/questions/9443067/scrolling-using-selenium-webdriver?answertab=active");
		WebElement element = driver.findElement(By.id("tabs")).findElement(By.className("youarehere"));
		Point p = element.getLocation();
	    ((JavascriptExecutor) driver).executeScript("window.scroll(" + p.getX() + "," + (p.getY() + 200) + ");");
	    
	}
	
	@Test
	public void testOpgave3() throws Exception {
//		WebDriver driver2 = setWebDriver(Browser.REMOTE_FIREFOX);
		driver.get(baseUrl + "/rekenhulpen/motorrijtuigenbelasting/");
		driver.findElement(By.id("selectVoertuig")).click();
		new Select(driver.findElement(By.id("selectVoertuig")))
				.selectByVisibleText("Personenauto");
		driver.findElement(By.cssSelector("option[value=\"personenauto\"]"))
				.click();
		new Select(driver.findElement(By.id("SelProvincie")))
				.selectByVisibleText("Zeeland");
		//waitForManualInput();

//		driver2.get("http://www.google.com");
		
		new Select(driver.findElement(By.id("SelBrandstof")))
				.selectByVisibleText("Benzine");
		new Select(driver.findElement(By.id("SelGewichtsklasseAlgemeen")))
				.selectByVisibleText("1251 t/m 1350");
		driver.findElement(By.cssSelector("option[value=\"1251\"]")).click();
		driver.findElement(By.id("but_bereken")).click();
		try {
			String actual = driver
					.findElement(By.cssSelector("#divResult > p")).getText();
			assertTrue(actual
					.matches("Motorrijtuigenbelasting per kwartaal: .*165,00\nMotorrijtuigenbelasting per jaar: .* 660,00"));

		} catch (Error e) {
			verificationErrors.append(e.toString());
		} finally {
			driver.close();
//			driver2.close();
		}
	}

	private void waitForManualInput() {
		System.out.println("waiting for input");
		
		JOptionPane optionPane = new JOptionPane(
                "The only way to close this dialog is by\n"
                + "pressing one of the following buttons.\n"
                + "Do you understand?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
		
		JDialog dialog = new JDialog();
		JLabel label = new JLabel("Please wait...");
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Please Wait...");
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(
		    JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setModal(true);
		dialog.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent we) {
			        System.out.println("Thwarted user attempt to close window.");
			    }
		});
		dialog.setVisible(true);
	}
}
