package com.ProjectSpecificLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	  WebDriver driver = new SafariDriver();
	@BeforeTest
	public void openBrowser() {
		  driver.manage().window().maximize();
		  driver.navigate().to("https://www.floraindia.com/");
	}
	
  @Test(priority=1)
  public void search() throws IOException {	
	  //load properties file
	  File fs = new File("application.properties");
	  FileInputStream f = new FileInputStream(fs);
	  Properties pro = new Properties();
	  pro.load(f);	
 	driver.findElement(By.id(pro.getProperty("search_button_ID"))).sendKeys("yellow");
	  driver.findElement(By.className(pro.getProperty("search_Icon_class"))).click();  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  List<WebElement> total= driver.findElements(By.cssSelector(pro.getProperty("Total_Items_page_cssselector"))); 
	  int size = total.size();
	  System.out.println("Total item displayed:" +size);
  }
  
  @Test(enabled=false)
  public void test2() {
	  driver.findElement(By.id(pro.getProperty(""))).sendkeys();
  }
  
  @AfterTest
  public void closeBrowser()  {
	 driver.close();
  }
  
}
