package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() throws IOException {
		
		ExtentReports extentReporter=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html");
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(extentReportFile);
		
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("Tutorials Ninja Test Automation");
		sparkReport.config().setDocumentTitle("Automation Report");
		sparkReport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReporter.attachReporter(sparkReport);
		
		Properties configProp=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
		FileInputStream fisconfig;
		try {
			fisconfig = new FileInputStream(propFile);
			configProp.load(fisconfig);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReporter.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReporter.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReporter.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReporter.setSystemInfo("Password", configProp.getProperty("validPassword"));
		
		extentReporter.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReporter.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReporter.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReporter;
		
	}

}
