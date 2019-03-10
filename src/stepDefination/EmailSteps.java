package stepDefination;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.server.HttpChannelState.Action;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class EmailSteps {
    private static WebDriver driver;
    private final static String SEND_EMAIL_URL = "https://mail.google.com/mail/u/0/#inbox?compose=new";
    private final static String SEND_BTN = "send";
    private final static String SENDER = "yangqingzhou.ken@gmail.com";
    private final static String PASSWORD = "password";
    private final static String RECIPIENT = "yangqingzhou.ken@gmail.com";
    private final static String RECIPIENTS = "yangqingzhou.ken@gmail.com, jiaweini0@gmail.com";
    private final static String ATTACHMENT = "I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image.png";
    private final static String ATTACHMENTS = "\"I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image.png\" \"I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image2.png\"";
    
    
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "I:\\tool\\driver\\chromedriver_win32\\chromedriver.exe");
	}
    
	@Given("^I am logged in on a new email page$")
	public void I_am_logged_in_on_a_new_email_page() throws Throwable {
    	System.setProperty("webdriver.chrome.driver", "I:\\tool\\driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		//login
		//   -get on login page
		driver.get(SEND_EMAIL_URL);
		//   -enter email and click next
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("yangqingzhou.ken@gmail.com");
		WebElement nextBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id("identifierNext")));
        nextBtn.click();
    	
        
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
        WebElement signInBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
    	Thread.sleep(1000);
        signInBtn.click();
        
	}

	@When("^I add an email address under \"To\"$")
	public void I_add_an_email_address_under_To_() throws Throwable {
        WebElement composeBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div")));
        composeBtn.click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(RECIPIENT);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("selenium test");

	}
	

	@When("^I add two email addresses under \"To\"$")
	public void I_add_two_email_addresses_under_To_() throws Throwable {
        WebElement composeBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div")));
        composeBtn.click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(RECIPIENTS);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("selenium test");

	}

	@And("^I click \"attach\" icon$")
	public void I_click_attach_icon() throws Throwable {
		WebElement attach = (new WebDriverWait(driver, 10))
	            .until(ExpectedConditions.elementToBeClickable(By.id(":qx")));

		attach.click();
		

		
	}

	@And("^I select image in popup$")
	public void I_select_image_in_popup() throws Throwable {
		//Copy your file's absolute path to the clipboard
		StringSelection ss = new StringSelection(ATTACHMENT);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	
		
		//native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}
	
	@And("^I select images in popup$")
	public void I_select_images_in_popup() throws Throwable {
		//Copy your file's absolute path to the clipboard
		StringSelection ss = new StringSelection(ATTACHMENTS);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	
		
		//native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	@And("^I press \"Send\"$")
	public void I_press_Send_() throws Throwable {
		Thread.sleep(5000);
		
        WebElement sendBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id(":p5")));
        sendBtn.click();

	}

	@Then("^the email is sent$")
	public void the_email_is_sent() throws Throwable {
		   WebElement messageSent = (new WebDriverWait(driver, 10))
	        		.until(ExpectedConditions.elementToBeClickable(By.className("bAq")));
	    	
	        if (messageSent != null) {
	        	System.out.println("confirm: email sent");
	        }
			driver.quit();
	}
	
	@AfterClass
	public void finish() {
		driver.manage().deleteAllCookies();
	}

}
