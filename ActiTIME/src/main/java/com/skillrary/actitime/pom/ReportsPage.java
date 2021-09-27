package com.skillrary.actitime.pom;

import org.openqa.selenium.WebDriver;

import com.skillrary.actitime.generics.WebActionUtil;

public class ReportsPage extends BasePage {
	public ReportsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - Enter Time-Track", "https://demo.actitime.com/user/submit_tt.do");
	}


}
