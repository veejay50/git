package stepDefintions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Verify;

import methods.TestCaseFailed;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import env.BaseTest;
import gherkin.formatter.model.DataTableRow;
import junit.framework.Assert;

public class PredefinedStepDefinitions implements BaseTest
{
	//Navigation Steps
	
		//Step to navigate to specified URL
		@Then("^I navigate to \"([^\"]*)\"$")
		public void navigate_to(String link)
		{
			navigationObj.navigateTo(link);
		}
		
		@Given("^I am on the QAWorks Site$")
		public void i_am_on_the_QAWorks_Site()
		{
			navigationObj.navigateTo("http://www.qaworks.com");
		}
	  
		@Then("^I should be able to contact QAWorks with the following information$")
		public void i_should_be_able_to_contact_QAWorks_with_the_following_information(DataTable userDetailsTable) throws Exception {
			   
				clickObj.click("css", "#menu > li:nth-child(1) > a");
			
			//Write the code to handle Data Table
			      HashMap<String, String> userDetailsMap = new HashMap<String, String>();
			        for (DataTableRow row : userDetailsTable.getGherkinRows()) {
			        	userDetailsMap.put(row.getCells().get(0),
			                    row.getCells().get(1));
			        }
			    		inputObj.enterText("id", userDetailsMap.get("name"), "ctl00_MainContent_NameBox");
			    		inputObj.enterText("id", userDetailsMap.get("email"), "ctl00_MainContent_EmailBox");
			    		inputObj.enterText("id", userDetailsMap.get("message"), "ctl00_MainContent_MessageBox");

			    		clickObj.click("id", "ctl00_MainContent_SendButton");
		}

		// step to maximize browser
		@Then("^I maximize browser window$")
		public void maximize_browser()
		{
			navigationObj.maximizeBrowser();
		}
				
		//Step to close the browser
		@Then("^I close browser$")
		public void close_browser()
		{
			navigationObj.closeDriver();
		}
				 		
	// step to select dropdown list
	@Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_option_from_dropdown_selected(String option,String by,String type,String accessName,String state) throws Exception
	{
		miscmethodObj.validateLocator(type);
		boolean flag = state.equals("selected");
		new Select(driver.findElement(By.id(accessName))).selectByValue(option);
		assertionObj.isOptionFromDropdownSelected(type,by,option,accessName,flag);
	}
	
	//Input steps
	
	// enter text into input field steps
	@Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void enter_text(String text, String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		inputObj.enterText(type, text, accessName);
	}

	// select option by text/value from dropdown
	@Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_dropdown(String option,String optionBy,String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		miscmethodObj.validateOptionBy(optionBy);
		inputObj.selectOptionFromDropdown(type,optionBy, option, accessName);
	}

	// select option by text/value from multiselect
	@Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown(String option,String optionBy, String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		miscmethodObj.validateOptionBy(optionBy);
		inputObj.selectOptionFromDropdown(type,optionBy, option, accessName);
	}
	
	// select option by index from multiselect
	@Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown_by_index(String option, String type, String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		inputObj.selectOptionFromDropdown(type,"selectByIndex", option, accessName);
	}
	
	//Click element Steps 
	
		// click on web element
		@Then("^I click on element having (.+) \"(.*?)\"$") 
		public void click(String type,String accessName) throws Exception
		{
			miscmethodObj.validateLocator(type);
			clickObj.click(type, accessName);
		}
			  		
		// steps to click on link
		@Then("^I click on link having text \"(.*?)\"$")
		public void click_link(String accessName)
		{
			clickObj.click("linkText", accessName);
		}
		
		//Progress methods
		
		// wait for specific period of time
		@Then("^I wait for (\\d+) sec$")
		public void wait(String time) throws NumberFormatException, InterruptedException
		{
			progressObj.wait(time);
		}
		
		
	//wait for specific element to display for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
	public void wait_for_ele_to_display(String duration, String type, String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		progressObj.waitForElementToDisplay(type, accessName, duration);
	}
}
