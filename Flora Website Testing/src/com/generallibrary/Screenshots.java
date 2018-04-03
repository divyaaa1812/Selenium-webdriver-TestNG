package com.generallibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	public void takeSnapShot(WebDriver d) {

        //Convert web driver object to TakeScreenshot
    //Call getScreenshotAs method to create image file

        File src = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        try {
            //Move image file to new destination

                File DestFile=new File("/Users/nikhilesh/eclipse/Flora Website Testing/Screenshots"+System.currentTimeMillis()+".jpg");

                //Copy file at destination

                FileUtils.copyFile(src, DestFile);

    } catch(IOException e)
        {
    	System.out.println(e.getMessage());
        }
	}
}

