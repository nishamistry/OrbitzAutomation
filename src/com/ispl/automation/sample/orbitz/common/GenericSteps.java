package com.ispl.automation.sample.orbitz.common;

import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElement;


public class GenericSteps {
	@QAFTestStep
	public static void type(String loc, String str) {
		new QAFExtendedWebElement(loc).sendKeys(str);
	}

	@QAFTestStep
	public static void typeToElement(QAFWebElement ele, String str) {
		ele.sendKeys(str);
	}

	@QAFTestStep(stepName = "i am looking for an element")
	public static QAFWebElement getElement(String loc) {
		return new QAFExtendedWebElement(loc);
	}

	@QAFTestStep
	public static String join(String str1, String str2) {
		System.out.println("Inside join");
		return str1 + str2;
	}

}
