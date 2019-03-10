/*
 * ECSE 428 Assignment B
 * 
 * 	Jiawei Ni   260615965
 *	jiawei.ni@mail.mcgill.ca
 *	
 *	Qingzhou Yang  260687570
 * 	qingzhou.yang@mail.mcgill.ca
 * 
 * */

package emailTest;
import java.util.concurrent.TimeUnit;
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

public class seleniumScript {
    private static WebDriver driver;
    private final static String SEND_EMAIL_URL = "https://mail.google.com/mail/u/0/#inbox?compose=new";
    private final static String SEND_BTN = "send";
    private final static String SENDER = "yangqingzhou.ken@gmail.com";
    private final static String PASSWORD = "password";
    private final static String RECIPIENT = "yangqingzhou.ken@gmail.com";
    private final static String RECIPIENTS = "yangqingzhou.ken@gmail.com, jiaweini0@gmail.com";
    private final static String ATTACHMENT = "I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image.png";
    private final static String ATTACHMENTS = "\"I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image.png\" \"I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image2.png\"";
    
    
	public static void main(String[] args) throws Throwable{
		
		//one recipient, one attachment
		init();
		login();
		composeEmail();
		fillRecipient(RECIPIENT);
		attach(ATTACHMENT);
		send();
		confirmSent();
		finish();
		
		//two recipients, one attachment
		init();
		login();
		composeEmail();
		fillRecipient(RECIPIENTS);
		attach(ATTACHMENT);
		send();
		confirmSent();
		finish();
		
		//one recipients, two attachments
		init();
		login();
		composeEmail();
		fillRecipient(RECIPIENT);
		attach(ATTACHMENTS);
		send();
		confirmSent();
		finish();
		
		//two recipients two attachments
		init();		
		login();
		composeEmail();
		fillRecipient(RECIPIENTS);
		attach(ATTACHMENTS);
		send();
		confirmSent();
		
		
	}
	
	// init state before tests
	private static void init() {
    	System.setProperty("webdriver.chrome.driver", "I:\\tool\\driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	//finish state
	private static void finish() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
    //Given("^I am logged in on a new email page$")
    public static void login() throws Throwable {

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
    
    private static void composeEmail() {
        WebElement composeBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div")));
        composeBtn.click();
    }
    
    
    //Given("^When I fill an email address under ¡°To¡±$")
    public static void fillRecipient(String recipient) {
    	
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(recipient);
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("selenium test");


    }
    private static void attach(String attchment) throws Throwable {
		WebElement attach = (new WebDriverWait(driver, 10))
	            .until(ExpectedConditions.elementToBeClickable(By.id(":qx")));

		attach.click();
		
		//Copy your file's absolute path to the clipboard
		StringSelection ss = new StringSelection(attchment);
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
    
    //click send button
	private static void send() throws InterruptedException {
		Thread.sleep(5000);
		
        WebElement sendBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id(":p5")));
        sendBtn.click();
        
	}
	// the email was sent
	private static void confirmSent() {
		   WebElement messageSent = (new WebDriverWait(driver, 10))
	        		.until(ExpectedConditions.elementToBeClickable(By.className("bAq")));
	    	
	        if (messageSent != null) {
	        	System.out.println("confirm: email sent");
	        }
	}


}
