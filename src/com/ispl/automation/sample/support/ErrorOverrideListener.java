package com.ispl.automation.sample.support;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;

import com.infostretch.automation.ui.webdriver.CommandTracker;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebDriver;
import com.infostretch.automation.ui.webdriver.QAFWebDriverCommandAdapter;
import com.infostretch.automation.util.StringUtil;

public class ErrorOverrideListener extends QAFWebDriverCommandAdapter {
	Log logger = LogFactory.getLog(getClass());

	@Override
	public void beforeCommand(QAFExtendedWebDriver driver,
			CommandTracker commandTracker) {
		super.beforeCommand(driver, commandTracker);
		String command = commandTracker.getCommand();
		@SuppressWarnings("unused")
		Map<String, Object> params = commandTracker.getParameters();
		if (command.equalsIgnoreCase(DriverCommand.GET_CURRENT_WINDOW_HANDLE)) {
			commandTracker.setResponce(new Response());
		}

	}

	@Override
	public void onFailure(QAFExtendedWebDriver driver,
			CommandTracker commandTracker) {
		super.onFailure(driver, commandTracker);

		commandTracker.getException().printStackTrace();
		// commandTracker.setException(null);
	}

	@Override
	public void onInitialize(QAFExtendedWebDriver driver) {
		if (StringUtil.isNotBlank((String) driver.getCapabilities()
				.getCapability("app"))) {
			for (String win : driver.getWindowHandles()) {
				driver.switchTo().window(win);
			}
		}
	}
}
