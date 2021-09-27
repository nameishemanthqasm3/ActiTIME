package com.skillrary.actitime.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.generics.WebActionUtil;

public class UsersPage extends BasePage {
	
	EnterTimeTrackPage enterTimeTrackPage;
	
	@FindBy(xpath="//div[text()='New User']")
	private WebElement newUserButton;
	
	@FindBy(id="createUserPanel_firstNameField")
	private WebElement firstNameTextField;
	
	@FindBy(id="createUserPanel_middleNameField")
	private WebElement MITextField;
	
	@FindBy(id="createUserPanel_lastNameField")
	private WebElement lastNameTextField;
	
	@FindBy(id="createUserPanel_emailField")
	private WebElement emailTextField;
	
	@FindBy(xpath="//div[contains(@class,'simpleListMenuButton')]/div[@class='downIcon']")
	private WebElement departmentDropDown;
	
	@FindBy(xpath="//div[@class='itemsContainer']/div")
	private List<WebElement> departmentDropDownOptionsList;
	
	@FindBy(id="createUserPanel_hireDatePlaceholder")
	private WebElement hireDateCalendar;
	
	@FindBy(xpath="//div[contains(@class,'overtimeSelectorPlaceholder')]/table[not(contains(@class,'hide'))]//button")
	private WebElement overtimeDropDown;
		
	@FindBy(xpath="//div[contains(@class,'overtimeSelectorMenu')]//li")
	private List<WebElement> overtimeDropDownOptionsList;
	
	@FindBy(xpath="//div[text()='Save & Send Invitation']")
	private WebElement saveAndSendInvitationButton;
	
	@FindBy(xpath="//div[contains(@class,'createUserPanel')]//span[text()='Close']")
	private WebElement closeIcon;
	
	@FindBy(className="userNameSpan")
	private List<WebElement> usersList;
	
	@FindBy(xpath="//div[@class='iScrollVerticalScrollbar iScrollLoneScrollbar' and contains(@style,'visible')]/div")
	private WebElement scrollBarElement;
	
	@FindBy(xpath="//div[text()='DELETE']")
	private WebElement deleteButton;
	
	public UsersPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - User List", "https://demo.actitime.com/administration/userlist.do");
		enterTimeTrackPage = new EnterTimeTrackPage(driver, webActionUtil);
	}

	public WebElement getNewUserButton() {
		return newUserButton;
	}

	public WebElement getFirstNameTextField() {
		return firstNameTextField;
	}

	public WebElement getMITextField() {
		return MITextField;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getEmailTextField() {
		return emailTextField;
	}

	public WebElement getDepartmentDropDown() {
		return departmentDropDown;
	}

	public List<WebElement> getDepartmentDropDownOptionsList() {
		return departmentDropDownOptionsList;
	}

	public WebElement getHireDateCalendar() {
		return hireDateCalendar;
	}

	public WebElement getOvertimeDropDown() {
		return overtimeDropDown;
	}

	public List<WebElement> getOvertimeDropDownOptionsList() {
		return overtimeDropDownOptionsList;
	}

	public WebElement getSaveAndSendInvitationButton() {
		return saveAndSendInvitationButton;
	}

	public WebElement getCloseIcon() {
		return closeIcon;
	}

	public List<WebElement> getUsersList() {
		return usersList;
	}
	
	public void selectOptionInDepartment(String option) {
		for(WebElement ele:departmentDropDownOptionsList) {
			if(ele.getText().contains(option)) {
				webActionUtil.click(ele);
				break;
			}
		}
	}
	
	public void selectOptionInOvertime(String option) {
		for(WebElement ele:overtimeDropDownOptionsList) {
			if(ele.getText().contains(option)) {
				webActionUtil.click(ele);
				break;
			}
		}
	}
	
	public void addNewUser(String firstName, String MI, String lastName,
						   String emailId,String day, String monthAndyear,
						   String deptOption, String overtimeOption) {
		int currentCount=usersList.size();
		webActionUtil.click(newUserButton);
		webActionUtil.enterData(firstNameTextField, firstName);
		webActionUtil.enterData(MITextField, MI);
		webActionUtil.enterData(lastNameTextField, lastName);
		webActionUtil.enterData(emailTextField, emailId);
		webActionUtil.click(departmentDropDown);
		selectOptionInDepartment(deptOption);
		webActionUtil.click(hireDateCalendar);
		enterTimeTrackPage.selectDate(day, monthAndyear);
		webActionUtil.scrollBarElement(scrollBarElement, 50);
		webActionUtil.click(overtimeDropDown);
		selectOptionInOvertime(overtimeOption);
		webActionUtil.click(saveAndSendInvitationButton);
		webActionUtil.click(closeIcon);
		webActionUtil.waitForNumberOfElementsToBeMore(By.className("userNameSpan"), currentCount);
	}
	
	public boolean verifyUser(String firstName) {
		for(WebElement ele:usersList) {
			if(ele.getText().contains(firstName)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteUser(String firstName) {
		int currentCount=usersList.size();
		for(WebElement ele:usersList) {
			if(ele.getText().contains(firstName)) {
				webActionUtil.click(ele);
				break;
			}
		}
		webActionUtil.click(deleteButton);
		webActionUtil.acceptAlert("delete this user");
		webActionUtil.waitForNumberOfElementsToBeLess(By.className("userNameSpan"), currentCount);
	}
}
