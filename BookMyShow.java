package actiTIME;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookMyShow {

	public static void main(String[] args) throws AWTException, InterruptedException {

		String BMS_Url = "https://in.bookmyshow.com/explore/home/";
		String Mail_Url = "https://yopmail.com/";
		String MailId = "rangeeshg@yopmail.com";
		String Expected_Result = "Hi, Guest";
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(BMS_Url);
		driver.manage().deleteAllCookies();
		driver.switchTo().activeElement().sendKeys("Bengaluru");
		driver.findElement(By.xpath("//li[@class='sc-cDnByv cscznh']")).click();
		driver.findElement(By.xpath("//div[.='Sign in']")).click();
		driver.findElement(By.xpath("(//div[@class='sc-kMkxaj cUkuEf'])[2]")).click();
		driver.findElement(By.id("emailId")).sendKeys(MailId);
		driver.findElement(By.xpath("//button[@class='sc-kWtpeL fMffSV']")).click();

		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(500);
		Set<String> Windows = driver.getWindowHandles();
		List<String> Tabs = new ArrayList<String>(Windows);

		driver.switchTo().window(Tabs.get(1));
		driver.get(Mail_Url);
		driver.findElement(By.id("login")).sendKeys(MailId);
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();

		driver.switchTo().frame("ifmail");
		String OTP = driver.findElement(By.xpath("(//td)[11]")).getText();

		driver.switchTo().window(Tabs.get(0));
		driver.findElement(By.xpath("//input[@class='sc-gvZAcH gkRWDL']")).sendKeys(OTP);
		driver.findElement(By.xpath("//button[.='Continue']")).click();

		String Actual_Result = driver.findElement(By.xpath("//span[@class='sc-gEkIjz iBRucH']")).getText();

		if (Actual_Result.equals(Expected_Result)) {
			System.out.println("User is successfully signed in, and User Name :"+Actual_Result +" is displayed.");
		} else {
			System.out.println("Sign-in validation failed");
		}
	}
}
