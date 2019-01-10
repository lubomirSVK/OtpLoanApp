package sk.otp;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Author:			Ľubomír Šima
 * Creation date: 	10.1.2019
 * App name:		OtpLoanApp
 */
public class OtpLoanApp {
	public static final String NAME = "Jozef";
	public static final String SURNAME = "Testovací";
	public static final String PHONE = "+421 800 123 456";
	public static final String EMAIL = "jozef.testovaci@email.com";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.marionette", "C:\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		//set url and make a connection
		String baseUrl = "https://www.otpbanka.sk/otp-hypo-uver";
		driver.get(baseUrl);

		// scroll to data view
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0,1000)", "");

		// init Web elements
		WebElement WebName = driver.findElement(By.name("fieldId-2704-name"));
		WebElement WebSurname = driver.findElement(By.name("fieldId-2707-surname"));
		WebElement WebPhone = driver.findElement(By.name("fieldId-2710-phone"));
		WebElement WebEmail = driver.findElement(By.name("fieldId-2713-email"));
		WebElement WebContactOffice = driver.findElement(
				By.xpath("//div[contains(@class, 'selectize-control tx-widget tx-field tx-combobox single')]"));
		WebElement WebCondition = driver
				.findElement(By.xpath("//div[contains(@class, 'tx-widget tx-field tx-checkbox')]/label"));
		WebElement WebSlider = driver
				.findElement(By.xpath("//div[contains(@class, 'Slider ui-draggable ui-draggable-handle')]"));
		WebElement WebSendBtn = driver.findElement(By.name("send"));

		//fill data
		WebName.sendKeys(NAME);
		Thread.sleep(200);
		WebSurname.sendKeys(SURNAME);
		Thread.sleep(200);
		WebPhone.sendKeys(PHONE);
		Thread.sleep(200);
		WebEmail.sendKeys(EMAIL);

		
		//show dropdown, click on value
		WebContactOffice.click();
		
		WebElement WebContactOffice2 = driver.findElement(
				By.xpath("//div[contains(@class, 'selectize-control tx-widget tx-field tx-combobox single')]/div[2]/div"));
	    Actions act = new Actions(driver);
		act.moveToElement(WebContactOffice2).click().perform();

		// click on condition
		WebCondition.click();

		//move the slider
		Dimension sliderSize = WebSlider.getSize();
		int sliderWidth = sliderSize.getWidth();
		int xCoord = WebSlider.getLocation().getX();

		Actions builder = new Actions(driver);
		builder.moveToElement(WebSlider).click().dragAndDropBy(WebSlider, xCoord + sliderWidth, 0).build().perform();

		//click the send button
		Thread.sleep(3000);
		WebSendBtn.click();
		Thread.sleep(5000);
		driver.close();
	}
}
