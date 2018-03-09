package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;

public class baseclass {
		
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public static ExcelReader reader = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testexcel.xlsx");
			
	@BeforeSuite
	//This method contains all the initialization
	public void setUp(){
	
		if(driver==null){
			
			try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
				log.debug("Configuration Properties File");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
				log.debug("OR properties file with FIS");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
				log.debug("Loaded OR properties file");
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			
			if(config.getProperty("browser").equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome browser launch");		
			}else if(config.getProperty("browser").equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox browser launch");
			}else if(config.getProperty("browser").equalsIgnoreCase("ie") || config.getProperty("browser").equalsIgnoreCase("InternetExplorer")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE Browser launch");
			}
			driver.get(config.getProperty("testurl"));
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("time")), TimeUnit.SECONDS);
		}	

	}
	
	@AfterSuite
	public void tearDown(){
		
		driver.quit();
		log.debug("Test Case execution completed");
		
	}
	
	
	

}
