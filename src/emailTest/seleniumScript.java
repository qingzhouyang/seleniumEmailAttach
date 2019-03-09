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

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class seleniumScript {
    private static WebDriver driver;
    private final static String SEND_EMAIL_URL = "https://mail.google.com/mail/u/0/#inbox?compose=new";
    private final String SEND_BTN = "send";
    
    
	public static void main(String[] args) throws Throwable{
    	System.setProperty("webdriver.chrome.driver", "I:\\tool\\driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		givenOnSendEmailPage();
		whenFillTo();
	}
	
	
    //Given("^I am logged in on a new email page$")
    public static void givenOnSendEmailPage() throws Throwable {

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
    	//   -enter password and clickI:\study\mcgill\2019\ecse 428\a2\CucumberTestAuto\image.png
        
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
        WebElement signInBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
        signInBtn.click();
        
        //   -go to send email page
        //driver.get(SEND_EMAIL_URL);
        WebElement composeBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div/div[1]/div/div")));
        composeBtn.click();
    }
    
    
    //Given("^When I fill an email address under ¡°To¡±$")
    public static void whenFillTo() throws Throwable {
    	
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("yangqingzhou.ken@gmail.com");
		
		driver.findElement(By.id(":pf")).sendKeys("selenium test");
		
		WebElement attach = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id(":qx")));
		
		attach.click();
		
		//Copy your file's absolute path to the clipboard
		StringSelection ss = new StringSelection("I:\\study\\mcgill\\2019\\ecse 428\\a2\\CucumberTestAuto\\image.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	
		
		//native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		
		//
        WebElement sendBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id(":p5")));
        sendBtn.click();
    }


}
