package com.skillrary.actitime.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.generics.WebActionUtil;

public class HomePage extends BasePage {
	@FindBy(id="logoutLink")
	private WebElement logoutLink;
	
	@FindBy(linkText="Time-Track")
	private WebElement timeTrackLink;
	
	@FindBy(linkText="Tasks")
	private WebElement tasksLink;
	
	@FindBy(linkText="Reports")
	private WebElement reportsLink;
	
	@FindBy(linkText="Users")
	private WebElement usersLink;
	
	@FindBy(id="closeProjectLightBoxBtn")
	private WebElement closePopUpIcon;
	
	
	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public WebElement getTasksLink() {
		return tasksLink;
	}

	public WebElement getReportsLink() {
		return reportsLink;
	}

	public WebElement getUsersLink() {
		return usersLink;
	}

	public WebElement getClosePopUpIcon() {
		return closePopUpIcon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getTimeTrackLink() {
		return timeTrackLink;
	}
	
	public BasePage clickOnMenuLink(String menuName) {
		menuName = menuName.toLowerCase();
		switch(menuName){
			case "time-track":webActionUtil.click(timeTrackLink); 
							  return new EnterTimeTrackPage(driver, webActionUtil);
			case "users":webActionUtil.jsClick(usersLink); 
						 return new UsersPage(driver, webActionUtil);
			case "reports":webActionUtil.click(reportsLink); 
						   return new ReportsPage(driver, webActionUtil);
			case "tasks": webActionUtil.justJsClick(tasksLink); 
						 return new TaskPage(driver, webActionUtil);
			default : return null;
		}
	}
	
	public void logout() {
		webActionUtil.jsClick(logoutLink);
	}


}
