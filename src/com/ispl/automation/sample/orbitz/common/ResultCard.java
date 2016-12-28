package com.ispl.automation.sample.orbitz.common;

import com.infostretch.automation.ui.webdriver.QAFWebElement;


public interface ResultCard {
	void select();
	String getCost();
	QAFWebElement getArrivalTimeEle();
	QAFWebElement getDepartureTimeEle();
	QAFWebElement getCostEle();
	QAFWebElement getFlightInfoEle();
	
}
