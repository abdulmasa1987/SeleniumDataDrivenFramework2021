package Keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class ApplicationKeywords extends ValidationKeywords {
	
	
	public ApplicationKeywords() throws IOException
	{
		String path=System.getProperty("user.dir")+"//src//test//resources//env.properties";
		prop=new Properties();
		FileInputStream fs=new FileInputStream(path);
		prop.load(fs);
		String env=prop.getProperty("env")+".properties";
		System.out.println(env);
		path=System.getProperty("user.dir")+"//src//test//resources//"+env;
		System.out.println(path);
		fs=new FileInputStream(path);
        envprop=new Properties();
        envprop.load(fs);
        System.out.println("How");
        System.out.println(prop.getProperty("createPortfolio_id"));
        softassert=new SoftAssert();
		
	}
	
	public void login()
	{
		
	}

	
	public void defaultLogin() {
		navigate("url");
		type("username_css", envprop.getProperty("admin_user_name"));
		type("password_xpath", envprop.getProperty("admin_password"));
		click("login_submit_id");
		waitForPageToLoad();
		wait(5);
		
	}
	
	
	public int findCurrentStockQuantity(String companyName) {
		log("Finding current stock quantity for "+ companyName);
		int row = getRowNumWithCellData("stocktable_css",companyName);
		if(row==-1) {
			log("Current Stock Quantity is 0 as Stock not present in list");
			return 0;
		}
		// table#stock > tbody > tr:nth-child(2) >td:nth-child(4)
		String quantity = driver.findElement(By.cssSelector(prop.getProperty("stocktable_css")+" > tr:nth-child("+row+") >td:nth-child(4)")).getText();
		log("Current stock Quantity "+quantity);
		return Integer.parseInt(quantity);
	}
	
	
	public void selectDateFromCalendar(String date) {
		log("Selecting Date "+date);
		
		try {
			Date currentDate = new Date();
			Date dateToSel=new SimpleDateFormat("d-MM-yyyy").parse(date);
			String day=new SimpleDateFormat("d").format(dateToSel);
			String month=new SimpleDateFormat("MMMM").format(dateToSel);

			String year=new SimpleDateFormat("yyyy").format(dateToSel);

			String monthYearToBeSelected=month+" "+year;
			System.out.println(monthYearToBeSelected);

			String monthYearDisplayed=getElement("monthyear_css").getText();
			System.out.println(monthYearDisplayed);
          
			while(!monthYearToBeSelected.equals(monthYearDisplayed)) {
				click("datebackButoon_xpath");
				monthYearDisplayed=getElement("monthyear_css").getText();
			}
			driver.findElement(By.xpath("//td[text()='"+day+"']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goToTransactionHistory(String companyName) {
	    log("Selecting the company row "+companyName );
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if(row==-1) {
			log("Stock not present in list");
			// report failure
		}
		driver.findElement(By.cssSelector(prop.getProperty("stocktable_css")+" > tr:nth-child("+row+") >td:nth-child(1)")).click();
		driver.findElement(By.cssSelector(prop.getProperty("stocktable_css")+"  tr:nth-child("+row+") input.equityTransaction" )).click();
		
	}

	
	public void goToBuySell(String companyName) {
		log("Selecting the company row "+companyName );
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if(row==-1) {
			log("Stock not present in list");
		}
		driver.findElement(By.cssSelector(prop.getProperty("stocktable_css")+" > tr:nth-child("+row+") >td:nth-child(1)")).click();
		driver.findElement(By.cssSelector(prop.getProperty("stocktable_css")+"  tr:nth-child("+row+") input.buySell" )).click();
		
	}
	
	
	
	public  void selectDateFromCalendar()
	{
		
	}
	
	public  void verifyStockAdded()
	{
		
	}
	
	
	
	
}
