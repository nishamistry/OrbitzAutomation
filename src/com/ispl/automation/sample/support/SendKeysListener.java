package com.ispl.automation.sample.support;

import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;

import com.infostretch.automation.core.QAFTestBase;
import com.infostretch.automation.ui.webdriver.CommandTracker;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElementCommandAdapter;
import com.infostretch.automation.util.StringUtil;

public class SendKeysListener extends QAFWebElementCommandAdapter {
	@Override
	public void beforeCommand(QAFExtendedWebElement element, CommandTracker commandTracker) {
		if (commandTracker.getCommand().equalsIgnoreCase(
				DriverCommand.SEND_KEYS_TO_ELEMENT)) {
			element.clear();
			QAFTestBase.pause(500);
			// commandTracker.getParameters().remove("by");
			// you access/modify any parameter!
			String value = String.valueOf(commandTracker.getParameters().get("value"));
			if (StringUtil.isBlank(value)) {
				// No need to send key
				// ignore actual command
				commandTracker.setResponce(new Response());
			}
		}
	}
}
