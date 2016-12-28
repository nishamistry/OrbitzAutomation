/*************************************************************************
 * InfoStretch CONFIDENTIAL
 * __________________
 * [2006] - [2011] InfoStretch Corporation
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of InfoStretch Corporation and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to InfoStretch Corporation
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from InfoStretch Corporation.
 **********************************************************/

package com.ispl.automation.sample.orbitz.web.page;

import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.WebDriverBaseTestPage;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.api.PageLocator;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.infostretch.automation.util.StringMatcher;
import com.ispl.automation.sample.orbitz.common.FlightsSearchFormBean;
import com.ispl.automation.sample.orbitz.repositroy.Locators.TripDetailPageLocators;
import com.ispl.automation.sample.orbitz.web.page.FlightsSearchPage.FlightsSearchWebFormBean;

/**
 * com.sample.automation.webdriver.pages.TripDetailPage.java
 * 
 * @author chirag
 */
public class TripDetailPage extends
		WebDriverBaseTestPage<FlightSearchResultsPage> implements
		TripDetailPageLocators {

	@FindBy(locator = PRODINFO_LOC)
	public QAFExtendedWebElement productInfo;

	@FindBy(locator = FLIGHT_FROM_LABEL_LOC)
	private QAFWebElement from;

	@FindBy(locator = FLIGHT_TO_LABEL_LOC)
	private QAFWebElement to;

	@FindBy(locator = CONTINUE_BTN_LOC)
	public QAFExtendedWebElement continueBtn;

	@FindBy(locator = SEE_DIFF_FLIGHTS)
	private QAFExtendedWebElement seeDifferentFlightsLink;

	@Override
	protected void openPage(PageLocator loc, Object... arg1) {
		int index = (loc == null ? 1 : Integer.parseInt(loc.getLocator()));
		parent.getResultCards().get(index).select();
	}

	@Override
	public void waitForPageToLoad() {
		waitForAjaxToComplete();
		productInfo.waitForVisible();
	}

	public void clickSeeDifferentFlights() {
		seeDifferentFlightsLink.click();
	}

	public QAFWebElement getProductInfo() {
		return productInfo;
	}

	public QAFWebElement getFrom() {
		return from;
	}

	public QAFWebElement getTo() {
		return to;
	}

	public QAFExtendedWebElement getContinueBtn() {
		return continueBtn;
	}

	@QAFTestStep(stepName = "verify Trip Details Result Page", description = "Verify Trip Details Result Page for trip {0}")
	public void verifyTripDetailsWebPage(Object data) {
		getProductInfo().verifyPresent("Trip Details Section with link");
		FlightsSearchWebFormBean searchMobileFormBean = new FlightsSearchPage().new FlightsSearchWebFormBean();
		searchMobileFormBean.fillData(data);
		verifyTripDetailsWebPage(searchMobileFormBean);
	}

	public void verifyTripDetailsWebPage(
			FlightsSearchFormBean searchMobileFormBean) {
		String from = searchMobileFormBean.getBeanData("from").toString();
		String to = searchMobileFormBean.getBeanData("to").toString();
		getFrom().verifyText(StringMatcher.contains(from),
				"From Location Displayed in Results Page");
		getTo().verifyText(StringMatcher.contains(to),
				"To Location Displayed in Results Page");
	}

	@QAFTestStep(description = "Continue To Customize Trip Page")
	public FlightCustomizeTripPage continueToCustomizeTripPage() {
		continueBtn.click();
		FlightCustomizeTripPage customizeTripPage = new FlightCustomizeTripPage();
		customizeTripPage.waitForPageToLoad();
		return customizeTripPage;
	}
}
