package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.Utils;

public class Base {
	
		WebDriver driver;
		public Properties prop;
		public Properties dataProp;
		
		public Base() {
			prop=new Properties();
			File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
			
			try {
				FileInputStream fis=new FileInputStream(propFile);
				prop.load(fis);
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			dataProp=new Properties();
			File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\testdata.properties");
			FileInputStream fis2;
			try {
				fis2 = new FileInputStream(dataPropFile);
				dataProp.load(fis2);
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	public WebDriver InitializeAndOpeningBrowser(String browserName) {
		
		
		if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
		
		return driver;
		
	}
	
	

}
