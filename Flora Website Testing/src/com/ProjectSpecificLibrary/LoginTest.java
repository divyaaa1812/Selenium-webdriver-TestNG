package com.ProjectSpecificLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;
	Properties pro = new Properties();
	  @BeforeTest
	  public void openBrowser() {
		  System.setProperty("webdriver.chrome.driver", "/Users/nikhilesh/Desktop/chromedriver");
		  driver= new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		  driver.navigate().to("https://www.floraindia.com");
		  driver.manage().window().maximize();
		//}
	 // @Test(priority=1)
  		//public void search() {	
	  //load properties file
	  try
	  {
	  File fs = new File("application.properties");
	  FileInputStream f = new FileInputStream(fs);
	  pro.load(f);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(pro.getProperty("search_button_id"))).sendKeys("yellow");
	  driver.findElement(By.className(pro.getProperty("search_Icon_class"))).click();  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  List<WebElement> total= driver.findElements(By.cssSelector(pro.getProperty("Total_Items_page_cssselector"))); 
	  int size = total.size();
	  System.out.println("Total item displayed:" +size);

	  } catch(FileNotFoundException e) {
		  e.printStackTrace();
	  } catch(IOException e) {
		  e.printStackTrace();
	  }
	  }
	 @Test(enabled=false)
	  public void mouseover() {
		  WebElement act1 = driver.findElement(By.cssSelector(pro.getProperty("Primary_Nav_cssselector")));
		  Actions a = new Actions(driver);
		 a.moveToElement(act1).click().build().perform();
	  }
	 @Test(priority=2)
	 public void addToCart() {
		 driver.findElement(By.cssSelector(pro.getProperty("First_element_list_cssSelector"))).click();
		WebElement availabilitytextbox= driver.findElement(By.id(pro.getProperty("check_availability_id")));
		availabilitytextbox.sendKeys("Hyderabad");
		driver.findElement(By.className("overlay")).click();
		availabilitytextbox.sendKeys(Keys.ARROW_DOWN);
		availabilitytextbox.sendKeys(Keys.ENTER);
		driver.findElement(By.id(pro.getProperty("check_availability_button_id"))).click();
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("document.querySelector(\"button[id=checkdelivery]\").click()");
		String str= driver.findElement(By.id(pro.getProperty("delivery-messages"))).getText();
		System.out.println(str);
	 }
	  
  
	  @AfterTest
	  public void closeBrowser()  {
	 //driver.close();
	  }
  
}
