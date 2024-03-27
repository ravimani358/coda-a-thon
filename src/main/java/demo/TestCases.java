package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;


public class TestCases {
    WebDriver driver;
    WebDriverWait wait;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws AWTException, InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        driver.findElement(By.xpath("(//span[text()='Name']/following::input)[1]")).sendKeys("Ravisankar N");
        driver.findElement(By.xpath("(//span[contains(text(),'practicing')]/following::textarea)[1]")).sendKeys("yes iam practicing daily");
        driver.findElement(By.xpath("//div[@role='radio']/following::span[contains(text(),'0 - 2')]")).click();
        driver.findElement(By.xpath("//div[@role='checkbox']/following::span[contains(text(),'Java')]")).click();
        driver.findElement(By.xpath("//div[@role='checkbox']/following::span[contains(text(),'Selenium')]")).click();
        driver.findElement(By.xpath("//div[@role='checkbox']/following::span[contains(text(),'TestNG')]")).click();
        driver.findElement(By.xpath("//span[text()='Choose']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Mr'])[2]")));
        driver.findElement(By.xpath("(//span[text()='Mr'])[2]")).click();
        //======
        String currentdateandtime = String.valueOf(java.time.LocalDateTime.now());
		String [] date_time = currentdateandtime.split("T");
		String [] formate_date = date_time[0].split("-");
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=formate_date.length-1; i>=0; i--){
			sb.append(formate_date[i]);
			if(i>0) {
				sb.append("-");
			}
		}
		
		String Full_date = sb.toString(); //---> check the code
		
		//String Full_date = "02-01-1997";
		System.out.println(Full_date);
		
		String date = Full_date.substring(0, 2);
		String date_value;
		String month_value;
		int final_month =0 ;
		int final_date =0;
		int final_year = 0;
		
		int month = Integer.parseInt(Full_date.substring(3, 5));
		System.out.println(month);
		
		int year = Integer.parseInt(Full_date.substring(6, 10));
		System.out.println(year);
		
		if(Integer.parseInt(date)-7<=0) {
			System.out.println("checking>>>>>>>>>>>>>>");
			final_date = 30+(Integer.parseInt(date)-7);
			//int pre_final_month = month-1;
			if((month-1)<=0) {
				final_month = 12-(month-1);
			}else {
				final_month = month-1;
			}
			if(final_month<=0) {
				year = year-1;
			}else {
				year = year;
			}
			
		}else {
			final_date = Integer.parseInt(date)-7;
			final_month = month;
			final_year = year;
		}
				
		if(final_date<=9) {
			date_value = "0"+final_date;
			System.out.println(date_value);
		}else {
			date_value = String.valueOf(final_date);
			System.out.println(date_value);
		}
		if(final_month<=9) {
			month_value = "0"+final_month;
			System.out.println(month_value);
		}else {
			month_value = String.valueOf(final_month);
			System.out.println(month_value);
		}
		
		String date_field_value = month_value+"/"+date_value+"/"+year;
		System.out.println(date_field_value);	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='date']")));
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys(date_field_value);
		
        //======
        String hours = (date_time[1].substring(0,2));
		System.out.println(hours);
		String minits = (date_time[1].substring(3,5));
		System.out.println(minits);
        driver.findElement(By.xpath("//input[@aria-label='Hour']")).sendKeys(hours);
        driver.findElement(By.xpath("//input[@aria-label='Minute']")).sendKeys(minits);
        driver.navigate().to("https://www.amazon.in/");
        driver.switchTo().alert().dismiss();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Submit']")));
		driver.findElement(By.xpath("//span[text()='Submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thanks')]")));
        String submition_note = driver.findElement(By.xpath("//div[contains(text(),'Thanks')]")).getText();
        if(submition_note.contains("Thanks")){
            System.out.println("all condition pass");
        }else{
            System.out.println("All condition fail");
        }
		System.out.println("end Test case: testCase01");
    }


}