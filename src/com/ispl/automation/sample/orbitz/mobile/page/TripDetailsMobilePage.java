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

package com.ispl.automation.sample.orbitz.mobile.page;


import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.WebDriverBaseTestPage;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.api.PageLocator;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.ispl.automation.sample.orbitz.mobile.page.FlightsSearchMobilePage.FlightsSearchMobileFormBean;
import com.ispl.automation.sample.orbitz.repositroy.Locators.TripDetailPageLocators;

/**
 * com.sample.automation.webdriver.pages.TripDetailPage.java
 * 
 * @author smitarani
 */
public class TripDetailsMobilePage extends
		WebDriverBaseTestPage<FlightSearchResultsMobilePage> implements
		TripDetailPageLocators {

	@FindBy(locator = PRODINFO_LOC)
	public QAFExtendedWebElement productInfo;

	@FindBy(locator = FLIGHT_FROM_LABEL_LOC)
	private QAFWebElement from;

	@FindBy(locator = FLIGHT_TO_LABEL_LOC)
	private QAFWebElement to;

	@Override
	public void launchPage(PageLocator locator, Object... args) {
		super.launchPage(locator, args);
	}

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
//		productInfo.waitForVisible();
//		productInfo.assertPresent("Trip Detail Page");
	}

	public QAFWebElement getFrom() {
		return from;
	}

	public QAFWebElement getTo() {
		return to;
	}

	public void clickSeeDifferentFlights() {
		seeDifferentFlightsLink.click();
	}

	public QAFWebElement getProductInfo() {
		return productInfo;
	}

	@QAFTestStep(stepName = "verify Trip Details Result Page", description = "Verify Trip Details Result Page for trip {0}")
	public void verifyTripDetailsMobilePage(Object data) {
		if(pageProps.getString("target.platform").equalsIgnoreCase("web")){
			getProductInfo().verifyPresent("Trip Details Section with link");
		}
		
		FlightsSearchMobileFormBean flightsSearchMobileFormBean = new FlightsSearchMobilePage().new FlightsSearchMobileFormBean();
		flightsSearchMobileFormBean.fillData(data);
		/*String from = flightsSearchMobileFormBean.getBeanData("from")
				.toString();
		String to = flightsSearchMobileFormBean.getBeanData("to").toString();
		getFrom().verifyText(StringMatcher.contains(from),
				"From Location Displayed in Results Page");
		getTo().verifyText(StringMatcher.contains(to),
				"To Location Displayed in Results Page");*/
	}

	public FlightTravelerInfoMobilePage continueToTravelerInfoPage() {
		continueBtn.click();
		/*FlightCustomizeTripMobilePage flightCustomizeTripMobilePage = new FlightCustomizeTripMobilePage();
		flightCustomizeTripMobilePage.waitForPageToLoad();
		return flightCustomizeTripMobilePage;*/
		FlightTravelerInfoMobilePage flightTravelerInfoMobilePage = new FlightTravelerInfoMobilePage();
		flightTravelerInfoMobilePage.waitForPageToLoad();
		return flightTravelerInfoMobilePage;
	}
}
