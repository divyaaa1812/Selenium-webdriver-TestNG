# Selenium-webdriver-TestNG
Floraindia.com website Testing

Creating object Repository using properties file.

Object repositories in selenium can be created using key-value pair Technique.
Key represents the unique name given to the objects on a webpage.
Value represents property which uniquely identify webelements on a webpage. Example: xpath,css selectors,class,Id etc
Properties file is a text file. Below is approach for preparing this file.
1) create a text file in eclipse
2) Prepare test data(key=value) to identify web objects.
3) Read data from the file
4) use the properties file in test script.

Implemented Multibrowser Testing-TestNG:Testing on Chrome and safari browsers
1) used @Parameters annotation.
2) Created parameter with name="browser" and value = "name of browser"
Code as below:
multiBrowser.java-                                  
  @Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		if(browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			  System.setProperty("webdriver.chrome.driver", "/Users/nikhilesh/Desktop/chromedriver");
			driver=new ChromeDriver();
		}
 Testng.xml -
  <parameter name="browser" value = "Safari" />
  
Automated Testcases:
1) Testcase to validate search functionality with valid input
2) Testcase to validate select an item,delivery date,delivery address and adding to cart
3) Testcase to select a submenu item and print title of webpage opened

    
