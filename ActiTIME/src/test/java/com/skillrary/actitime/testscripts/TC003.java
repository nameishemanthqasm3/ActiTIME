package com.skillrary.actitime.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.skillrary.actitime.generics.ExcelLibrary;
import com.skillrary.actitime.pom.UsersPage;

public class TC003 extends BaseTest {
	UsersPage usersPage;
	String firstName;
	
	@Test(description="To verify whether a newly added used is displayed in the users list")
	public void testAddUser() {
		String sheetName = "TC003";
		int rowNumber = 1;
		
		String menuName=ExcelLibrary.getStringData(sheetName, rowNumber, 0);
		firstName=ExcelLibrary.getStringData(sheetName, rowNumber, 1);
		String MI=ExcelLibrary.getStringData(sheetName, rowNumber, 2);
		String lastName=ExcelLibrary.getStringData(sheetName, rowNumber, 3);
		String emailId=ExcelLibrary.getStringData(sheetName, rowNumber, 4);
		String day=ExcelLibrary.getStringData(sheetName, rowNumber, 5).split("\\.")[0];
		String monthAndYear=ExcelLibrary.getStringData(sheetName, rowNumber, 6);
		String deptOption=ExcelLibrary.getStringData(sheetName, rowNumber, 7);
		String overtimeOption=ExcelLibrary.getStringData(sheetName, rowNumber, 8);
				
		usersPage =(UsersPage) homePage.clickOnMenuLink(menuName);
		usersPage.addNewUser(firstName, MI, lastName, emailId, day, monthAndYear, deptOption, overtimeOption);
		Assert.assertTrue(usersPage.verifyUser(firstName));
	}
	
	@AfterMethod
	public void deleteUser() {
		usersPage.deleteUser(firstName);
		Assert.assertFalse(usersPage.verifyUser(firstName));
	}
}
