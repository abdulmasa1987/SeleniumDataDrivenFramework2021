package Keywords;

import org.openqa.selenium.support.ui.Select;

public class ValidationKeywords extends GenericKeywords {
	
	public void validateTitle()
	{
		
	}
	
	public void validateText()
	{
		
	}
	
	
	public void validateElementPresent(String locatorkey)
	{
		boolean result=isElementPresent(locatorkey);
		//reportfailure("Element not found "+locatorkey,true);
		//report failure
		
	}
	public void validateSelectedValueInDropDown(String locatorKey, String option) {
		Select s = new Select(getElement(locatorKey));
		String text = s.getFirstSelectedOption().getText();
		if(!text.equals(option)){
			reportfailure("Option"+option+" not present in Drop Down "+locatorKey,true);
		}
		
	}
	
	
	public void validateSelectedValueNotInDropDown(String locatorKey, String option) {
		Select s = new Select(getElement(locatorKey));
		String text = s.getFirstSelectedOption().getText();
		if(text.equals(option)){
			reportfailure("Option"+option+" present in Drop Down "+locatorKey,true);
		}

	
	}
}
