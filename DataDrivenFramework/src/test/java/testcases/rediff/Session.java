package testcases.rediff;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import testbase.BaseTest;

public class Session extends BaseTest {
	
	@Test
	public void doLogin(ITestContext context)
	{

	test.log(Status.INFO,"Logging IN");
	app.openBrowser("Chrome");
	app.navigate("url");
	//int a=100/0;
	app.type("username_css","abdulrahmanchaudhari@gmail.com");
	
	//failure
	//app.reportfailure("First failure -Non crtical failure",false);
	app.type("password_xpath","welcome123");
	app.validateElementPresent("login_submit_id");
	app.click("login_submit_id");
	}
	
	
	@Test
    public void doLogout()
	{

	test.log(Status.INFO,"Logging out");

	}

}
