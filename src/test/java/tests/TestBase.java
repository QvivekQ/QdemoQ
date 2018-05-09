package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver = null;

	@BeforeSuite
	public void setup() throws IOException {

		BasicConfigurator.configure();

		System.setProperty("webdriver.chrome.driver", "/home/qolsys/Driver/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
 		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver();
		// To maximize browser
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// To open facebook
		driver.get("https://www.facebook.com");

	}

	@AfterSuite
	// Test cleanup
	public void TeardownTest() {
		TestBase.driver.quit();
	}

}
