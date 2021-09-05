package runner;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String[] args) {
		TestNGRunner testNG = new TestNGRunner(1);
		testNG.createSuite("Stock Management", false);
		testNG.addListener("listener.MyTestNGListener");// later
		testNG.addTest("Add New Stock Test");
		testNG.addTestParameter("action", "addstock");
		List<String> includedMethods = new ArrayList<String>();
		includedMethods.add("selectPortFolio");
		testNG.addTestClass("testcases.rediff.PortfolioManagement", includedMethods);
		includedMethods = new ArrayList<String>();
		includedMethods.add("addNewStock");
		includedMethods.add("verifyStockPresent");
		includedMethods.add("verifyStockQuantity");
		includedMethods.add("verifyTransactionHistory");
		testNG.addTestClass("testcases.rediff.StockManagement", includedMethods);
		testNG.run();
		//testNG.createSuite("Stock Management", false);
		
		
	}

}
