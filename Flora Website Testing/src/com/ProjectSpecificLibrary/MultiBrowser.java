package com.ProjectSpecificLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.generallibrary.Screenshots;

public class MultiBrowser extends Screenshots {
	WebDriver driver;
	Properties pro = new Properties();
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		if(browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			  System.setProperty("webdriver.chrome.driver", "/Users/nikhilesh/Desktop/chromedriver");
			driver=new ChromeDriver();
		}
//Not browser type, then launch website and maximise window*/
		  driver.navigate().to("https://www.floraindia.com");
		  driver.manage().window().maximize();
	}	
/*Testcase for search functionality on webpage*/

	 @Test(priority=1)
  		public void search() throws Exception {
		 String Title1=driver.getTitle();
		 System.out.println("page opened is :" +Title1);
		 //load properties file
	  try
	  {
	  File fs = new File("application.properties");
	  FileInputStream f = new FileInputStream(fs);
	  pro.load(f);
	  //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id(pro.getProperty("search_button_id"))).sendKeys("yellow");
	  this.takeSnapShot(driver);
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
	 	 
/*Testcase to validate items adding to cart*/
	 @Test(priority=2)
	 public void addToCart() throws Exception  {
		 driver.findElement(By.cssSelector(pro.getProperty("First_element_list_cssSelector"))).click();
		WebElement availabilitytextbox= driver.findElement(By.id(pro.getProperty("check_availability_id")));
		availabilitytextbox.sendKeys("banglore");
//take screenshot
		  this.takeSnapShot(driver);
/*  cursor to focus on text box clicking on it before performing keyboard operations*/
		availabilitytextbox.click();
/*Using Keyboard operations to select from dynamic search list */
		availabilitytextbox.sendKeys(Keys.ARROW_DOWN);
		availabilitytextbox.sendKeys(Keys.RETURN);
/* Explicit wait till the button is visible*/
		WebDriverWait w= new WebDriverWait(driver,30);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id(pro.getProperty("check_availability_button_id")))).click();
			/*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String str= driver.findElement(By.xpath(pro.getProperty("delivery_messages_id"))).getText();
	    	Assert.assertEquals(str,"Delivery is available"); */
/*Select date field and pick Todays date and clicking on addcart button */
	    driver.findElement(By.xpath(pro.getProperty("date_picker_xpath"))).click();
	    driver.findElement(By.xpath(pro.getProperty("date_select_xpath"))).click();
	    driver.findElement(By.xpath(pro.getProperty("addcart_button_xpath"))).click();
	   String Title2 = driver.getTitle();
	   Assert.assertEquals(Title2, "Shopping Cart");
	   }
/*Testcase to validate submenu item on webpage*/
	 @Test(priority=3)
	 public void submenuCake() {
		 try {
			 //Performing mouse action i.e mousehover to menu items and selecting submenu link using linkText()
		 Actions a = new Actions(driver);
		 a.moveToElement(driver.findElement(By.xpath(pro.getProperty("Primary_nav_birthday_xpath")))).build().perform();
		  this.takeSnapShot(driver);
		 driver.findElement(By.linkText("Regular Cakes")).click();
		  this.takeSnapShot(driver);
		 	//Get Title of webpage opened and display on console
		 String Title3 = driver.getTitle();
		 System.out.println("Page opened is :" +Title3);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
  
	  @AfterTest
	  public void closeBrowser()  {
	 driver.close();
	  }
  
}
