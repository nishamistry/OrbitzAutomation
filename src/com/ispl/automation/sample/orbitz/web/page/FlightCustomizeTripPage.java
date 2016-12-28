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
import com.infostretch.automation.ui.api.WebDriverTestPage;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchResultsPageLocators;

/**
 * com.sample.automation.webdriver.pages.FlightSearchResultsPage.java
 * 
 * @author chirag
 */
public class FlightCustomizeTripPage extends
		WebDriverBaseTestPage<WebDriverTestPage> implements
		FlightSearchResultsPageLocators {

	@FindBy(locator = "flight.booking.page.title")
	private QAFWebElement pageTitle;

	String pageTitleValue = pageProps
			.getString("flight.customize.trip.page.title");

	@FindBy(locator = "flight.customizetrip.page.continue.btn")
	private QAFWebElement continueBookingBtn;

	public QAFWebElement getPageTitle() {
		return pageTitle;
	}

	public QAFWebElement getContinueBooking() {
		return continueBookingBtn;
	}

	@Override
	protected void openPage(PageLocator arg0, Object... arg1) {
		// TODO Auto-generated method stub

	}

	@QAFTestStep(description = "Continue to Traveler info page")
	public FlightTravelerInfoPage continueToTravelerInfo() {
		continueBookingBtn.click();
		FlightTravelerInfoPage flightTravelerInfoPage = new FlightTravelerInfoPage();
		flightTravelerInfoPage.waitForPageToLoad();
		return flightTravelerInfoPage;
	}
}
