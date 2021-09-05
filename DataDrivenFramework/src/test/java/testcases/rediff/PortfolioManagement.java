package testcases.rediff;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Keywords.ApplicationKeywords;
import testbase.BaseTest;

public class PortfolioManagement extends BaseTest {

	@Test
	public void createPortfolio(ITestContext context)
	{
 
		String portfolioName="Portfolio10111abx11";
		
		app.log("Creating Portfolio");
		System.out.println();
		app.click("createPortfolio_id");
		app.clear("porfolioname_id");
		app.type("porfolioname_id", portfolioName);
		app.click("createPortfolioButton_css");
        app.waitForPageToLoad();
        app.validateSelectedValueInDropDown("portfolioid_dropdown_id", portfolioName);
		//app.reportfailure("non critical failure",false);
		app.assertall();
		

	}
	
	@Test
	public void deletePortfolio()
	{
        
        String portfolioName="Portfolio10111abx11";

		app.log("Deleting Portfolio");
		app.wait(20);
		app.selectByVisibleText("portfolioid_dropdown_id", portfolioName);
        app.waitForPageToLoad();
        app.click("deletePortfolio_id");
        app.acceptAlert();
        app.waitForPageToLoad();
        app.validateSelectedValueNotInDropDown("portfolioid_dropdown_id", portfolioName);

	}
	
	@Test
	public void selectPortFolio(ITestContext context) {
		JSONObject data=(JSONObject) context.getAttribute("data");
		String portfolioname =(String) data.get("portfolioname");

		app.log("Selecting Profolio");
		app.selectByVisibleText("portfolioid_dropdown_id",portfolioname);
        app.waitForPageToLoad();
	}
}
