package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import Keywords.ApplicationKeywords;
import Keywords.GenericKeywords;

public class CreatePortFolioTest {
	
	
	@Test
	public void createPortFolioTest() throws IOException, InterruptedException
	{
		//no webdriver code
		//login
		//create
		//verify

		ApplicationKeywords app=new ApplicationKeywords();
		app.openBrowser("Chrome");
		app.navigate("url");
		app.type("username_css","abdulrahmanchaudhari@gmail.com");
		app.type("password_xpath","welcome123");
		//app.validateTitle();
		//app.validateText();
		app.click("login_submit_id");
		//app.type(null, null);
	}

}
