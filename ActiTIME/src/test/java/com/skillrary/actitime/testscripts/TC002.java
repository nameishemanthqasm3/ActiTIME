package com.skillrary.actitime.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillrary.actitime.pom.EnterTimeTrackPage;
import com.skillrary.actitime.pom.TaskPage;

public class TC002 extends BaseTest {
	
	String taskName = "Seltos manufacturing";
	String custName = "KIA";
	
	@BeforeMethod(alwaysRun=true)
	public void deleteCustomerBefore() {
		((TaskPage) homePage.clickOnMenuLink("Tasks")).deleteCustomer(custName);
	}
	
	@Test(description = "To Verify whether deleted task is Not displayed in the tasks Page")
	public void testTaskDeletion() {
		EnterTimeTrackPage entertimeTrackPage=(EnterTimeTrackPage) homePage.clickOnMenuLink("Time-Track");
		entertimeTrackPage.createTask("New Customer", custName, "Car Manufacturing", taskName, "10", "15", "October 2021");
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), true);
		entertimeTrackPage.deleteTask(taskName);
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), false);
	}
	
	@AfterMethod(alwaysRun=true)
	public void deleteCustomerAfter() {
		((TaskPage) homePage.clickOnMenuLink("Tasks")).deleteCustomer(custName);
	}


}
