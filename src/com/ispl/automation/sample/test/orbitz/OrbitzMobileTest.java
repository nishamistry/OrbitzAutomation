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

package com.ispl.automation.sample.test.orbitz;

import static com.ispl.automation.sample.orbitz.mobile.step.OrbitzMobileSteps.bookFlight;

import java.util.Map;

import org.testng.annotations.Test;

import com.infostretch.automation.testng.dataprovider.QAFDataProvider;
import com.infostretch.automation.ui.WebDriverTestCase;
import com.ispl.automation.sample.orbitz.mobile.page.FlightSearchResultsMobilePage;
import com.ispl.automation.sample.orbitz.mobile.page.FlightsSearchMobilePage;
import com.ispl.automation.sample.orbitz.mobile.page.TripDetailsMobilePage;

/**
 * com.ispl.automation.sample.test.orbitz.OrbitzMobileTest.java
 * 
 * @author chirag
 */
public class OrbitzMobileTest extends WebDriverTestCase {

	@Test(enabled = true)
	public void verifySearchResultCardPageAndSearchResultCard() {
		FlightsSearchMobilePage flightsSearchMobilePage = new FlightsSearchMobilePage();
		flightsSearchMobilePage.launchPage();
		// flightsSearchMobilePage.waitForPageToLoad();
		// flightsSearchMobilePage.waitForAjaxToComplete();
		FlightSearchResultsMobilePage flightSearchResultsMobilePage = flightsSearchMobilePage
				.searchFlight("test.flight.oneway");
		flightSearchResultsMobilePage
				.verifySearchResultMobilePage("test.flight.oneway");
		flightSearchResultsMobilePage.verifyCard(1);
	}

	@QAFDataProvider(key = "test.datadriven.flight")
	@Test
	public void verifyFlightInfoPage(Map<String, String> data) {
		FlightsSearchMobilePage flightsSearchMobilePage = new FlightsSearchMobilePage();
		flightsSearchMobilePage.launchPage();
		FlightSearchResultsMobilePage flightSearchResultsMobilePage = flightsSearchMobilePage
				.searchFlight("test.flight.oneway");
		TripDetailsMobilePage tripDetailsMobilePage = flightSearchResultsMobilePage
				.selectResultCard("2");
		tripDetailsMobilePage.verifyTripDetailsMobilePage("test.flight.oneway");
		bookFlight();
	}

	@Test
	public void bookingFlow() {
		FlightsSearchMobilePage flightsSearchMobilePage = new FlightsSearchMobilePage();
		flightsSearchMobilePage.launchPage();
		FlightSearchResultsMobilePage flightSearchResultsMobilePage = flightsSearchMobilePage
				.searchFlight("test.flight.oneway");
		flightSearchResultsMobilePage.selectResultCard("2");
		bookFlight();
	}

}
