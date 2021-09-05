package Keywords;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import java.net.URL;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;


/*
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import reports.ExtentManager;
*/
public class GenericKeywords {
	
	public WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public ExtentTest test;
    public SoftAssert softassert;
	
    /*
	public void openBrowser(String browser)
	{
		log("Opening the Browser "+browser);
	
		if(browser.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("Mozilla"))
		{
			driver=new FirefoxDriver();
		}
		else if(browser.equals("IE"))
		{
			driver=new InternetExplorerDriver();
		}
		else if(browser.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		// dynamic wait- not pause
		   // global time out- all driver.findelement
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	*/
    
    public void openBrowser(String browser) {
		log("Opening The Browser "+ browser);
		if(prop.get("grid_run").equals("Y")) {
			
			DesiredCapabilities cap=new DesiredCapabilities();
			if(browser.equals("Mozilla")){
				
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browser.equals("Chrome")){
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				// hit the hub
				driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
			} catch (Exception e) {
			  e.printStackTrace();
			}
		}else {  //local machine
			if(browser.equals("Chrome")) {
				driver=new ChromeDriver();
			}else if(browser.equals("Mozilla")) {
				driver = new FirefoxDriver();
			}else if(browser.equals("Edge")) {
				driver = new EdgeDriver();
			}
		}
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
    }
    
	
	public void navigate(String urlkey)
	{
		log("Navigating to url "+envprop.getProperty(urlkey));
		driver.get(envprop.getProperty(urlkey));
		
	}
	
	
	
	public void click(String locatorkey)
	{
        System.out.println(locatorkey); 
		getElement(locatorkey).click();

	}
	
	
	public void type(String locatorkey,String data)
	{
		log("Typing "+data+" in "+locatorkey);
		getElement(locatorkey).sendKeys(data);
	}

	
	
	public void select(String locator,String data)
	{
		
	}
	

	public String getText(String locatorKey) {
		return getElement(locatorKey).getText();
	}
	
	public void clear(String locatorKey) {
		log("Clearing text field "+ locatorKey);
		getElement(locatorKey).clear();
	}
	
	public void clickEnterButton(String locatorKey) {
		log("Clinking enter button");
		getElement(locatorKey).sendKeys(Keys.ENTER);
	}
	
	public void selectByVisibleText(String locatorKey, String data) {
		Select s = new Select(getElement(locatorKey));
		s.selectByVisibleText(data);
	}
	
	
	// finds the row number of the data
	public int getRowNumWithCellData(String tableLocator, String data) {
		
		WebElement table = getElement(tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int rNum=0;rNum<rows.size();rNum++) {
			WebElement row = rows.get(rNum);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(int cNum=0;cNum<cells.size();cNum++) {
				WebElement cell = cells.get(cNum);
				System.out.println("Text "+ cell.getText());
				if(!cell.getText().trim().equals(""))
					if(data.startsWith(cell.getText()))
						return(rNum+1);
			}
		}
		
		return -1; // data is not found
	}
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		// ajax status
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(2);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(2);
			 i++;
				
			}
		
		}
	
	public void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void acceptAlert(){
		test.log(Status.INFO, "Switching to alert");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		try{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			test.log(Status.INFO, "Alert accepted successfully");
		}catch(Exception e){
				reportfailure("Alert not found when mandatory",true);
		}
		
	}

	
	
	public WebElement getElement(String locatorkey)
	{
			//is ElementPresent
		    //is Element Visible
           if(!isElementPresent(locatorkey))
				{
			//report failure
        	   log("Element not present "+locatorkey);
				}
		if(!isElementVisible(locatorkey))
				{
			//report failure
			log("Element not visible "+locatorkey);
				}
		
		    WebElement e=null;
	    	e=driver.findElement(getlocator(locatorkey));
	    	return e;
	}
	
	
	public boolean isElementPresent(String locatorkey)
	{
	    System.out.println("ABC"+prop.getProperty(locatorkey));
	    System.out.println("DEF"+prop.getProperty(locatorkey));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		try {	
			   wait.until(ExpectedConditions.presenceOfElementLocated(getlocator(locatorkey)));
		    }catch(Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isElementVisible(String locatorkey)
	{

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		try
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(getlocator(locatorkey)));
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
		
	
	
	public By getlocator(String locatorkey)
	{
		
		By by=null;
		if(locatorkey.endsWith("id"))
		{
		
			by= By.id(prop.getProperty(locatorkey));
		}
	    else if(locatorkey.endsWith("xpath"))
	    {
	    	by= By.xpath(prop.getProperty(locatorkey));

	    }
	    else if(locatorkey.endsWith("css"))
	    {
	    	by= By.cssSelector(prop.getProperty(locatorkey));

	    }
	    else if(locatorkey.endsWith("name"))
	    {
	    	by= By.name(prop.getProperty(locatorkey));

	    }
		return by;
	}
	
	public void setReport(ExtentTest test)
	{
		this.test=test;
	}
	
	//Reporting function
	public void log(String msg)
	{
		test.log(Status.INFO, msg);
	}
	
	

	//Reporting function
	public void reportfailure(String failuremsg,boolean stopOnFailure)
	{
		softassert.fail(failuremsg);    //failure in testng report
	    test.log(Status.FAIL, failuremsg); //failure in extent report
		takeScreenShot();
		if(stopOnFailure)  //report all the failures till now and stop the test
		{
			Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailure","Y");
			assertall();

		}
	}
	
	public void assertall()
	{
		softassert.assertAll();
	}
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
			//put screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void quit()
	{
		driver.quit();
	}
	
	
}
