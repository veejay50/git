package stepDefintions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Verify;

import methods.TestCaseFailed;
import cucumber.api.java.en.Then;
import env.BaseTest;
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
			
		//Step to navigate forward
		@Then("^I navigate forward")
		public void navigate_forward()
		{
			navigationObj.navigate("forward");
		}
			
		//Step to navigate backward
		@Then("^I navigate back")
		public void navigate_back()
		{
			navigationObj.navigate("back");
		}
	  
		//Close new window
		@Then("^I close new window$")
		public void close_new_window()
		{
			navigationObj.closeNewWindow();
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
			

		@Then("^verify that (ListView|GridView|MapView) is selected and other views are available$")
		public void verify_that_someView_is_selected_and_other_views_are_available(String view) throws Exception {
			if(view.equals("ListView")){
				Verify.verify(!driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[1]")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[1]")).getAttribute("title").equals("Grid view"));
				Verify.verify(!driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[2]")).getAttribute("class") .contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[2]")).getAttribute("title").equals("Map view"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/span")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/span")).getAttribute("title").equals("List view"));
			}
			else if(view.equals("GridView")){
				Verify.verify(!driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[1]")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[1]")).getAttribute("title").equals("List view"));
				Verify.verify(!driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[2]")).getAttribute("class") .contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/a[2]")).getAttribute("title").equals("Map view"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/span")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='content']/div[3]/div[3]/span")).getAttribute("title").equals("Grid view"));
			}
			else if(view.equals("MapView")){
				Verify.verify(!driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/a[1]")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/a[1]")).getAttribute("title").equals("List view"));
				Verify.verify(!driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/a[2]")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/a[2]")).getAttribute("title").equals("Grid view"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/span")).getAttribute("class").contains("is-active"));
				Verify.verify(driver.findElement(By.xpath("//*[@id='maps-header']/div/div/span/span")).getAttribute("title").equals("Map view"));
			}
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
