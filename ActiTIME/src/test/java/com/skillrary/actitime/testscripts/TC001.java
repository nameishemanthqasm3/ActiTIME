package com.skillrary.actitime.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillrary.actitime.generics.ExcelLibrary;
import com.skillrary.actitime.pom.EnterTimeTrackPage;
import com.skillrary.actitime.pom.TaskPage;

public class TC001 extends BaseTest {
	
	EnterTimeTrackPage entertimeTrackPage;
	String taskName;
	String custName;
	String menuName1;
	String menuName2;
	String sheetName = "TC001";
	int rowNumber = 1;
	
	
	@BeforeMethod(alwaysRun=true)
	public void deleteCustomerBefore() {
		taskName=ExcelLibrary.getStringData(sheetName, rowNumber, 0);
		menuName1=ExcelLibrary.getStringData(sheetName, rowNumber, 2);
		menuName2=ExcelLibrary.getStringData(sheetName, rowNumber, 3);
		custName=ExcelLibrary.getStringData(sheetName, rowNumber, 1);
		
		TaskPage taskPage = (TaskPage) homePage.clickOnMenuLink(menuName1);
		taskPage.deleteCustomer(custName);
		entertimeTrackPage = (EnterTimeTrackPage)homePage.clickOnMenuLink(menuName2);
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), false);
	}
	
	@Test(description = "To Verify whether Created task is displayed in the tasks Page")
	public void testTaskCreation() {
		
		String custOption=ExcelLibrary.getStringData(sheetName, rowNumber, 4);
		String projectName=ExcelLibrary.getStringData(sheetName, rowNumber, 5);
		String estimateHours=ExcelLibrary.getStringData(sheetName, rowNumber, 6).split("\\.")[0];
		String day=ExcelLibrary.getStringData(sheetName, rowNumber, 7).split("\\.")[0];
		String monthAndYear=ExcelLibrary.getStringData(sheetName, rowNumber, 8);
		
		entertimeTrackPage.createTask(custOption, custName, projectName, taskName, estimateHours, day, monthAndYear);
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), true);
	}
	
	@AfterMethod(alwaysRun=true)
	public void deleteCustomerAfter() {
		TaskPage taskPage = (TaskPage) homePage.clickOnMenuLink(menuName1);;
		taskPage.deleteCustomer(custName);
		entertimeTrackPage = (EnterTimeTrackPage) homePage.clickOnMenuLink(menuName2);
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), false);
	}


}
