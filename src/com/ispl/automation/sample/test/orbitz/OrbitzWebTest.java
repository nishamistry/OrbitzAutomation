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

import java.util.Map;

import org.testng.annotations.Test;

import com.infostretch.automation.testng.dataprovider.QAFDataProvider;
import com.infostretch.automation.ui.WebDriverTestCase;
import com.ispl.automation.sample.orbitz.web.page.FlightSearchResultsPage;
import com.ispl.automation.sample.orbitz.web.page.FlightsSearchPage;
import com.ispl.automation.sample.orbitz.web.page.TripDetailPage;
import static com.ispl.automation.sample.orbitz.web.step.OrbitzWebSteps.bookFlight;

/**
 * com.ispl.automation.sample.test.orbitz.OrbitzWebTest.java
 * 
 * @author chirag
 */
public class OrbitzWebTest extends WebDriverTestCase {

	@QAFDataProvider(key = "test.datadriven.flight")
	@Test(description = "Search flight with mulitple data set", enabled = true)
	public void verifyFlightInfoPage1(Map<String, String> data) {
		FlightsSearchPage flightsSearchPage = new FlightsSearchPage();
		flightsSearchPage.launchPage();
		FlightSearchResultsPage flightSearchResultsPage = flightsSearchPage
				.searchFlight(data);
		TripDetailPage tripDetailsPage = flightSearchResultsPage
				.selectResultCard("1");
		tripDetailsPage.verifyTripDetailsWebPage(flightsSearchPage
				.getSearchFormBean());

	}

	@Test(description = "Verify Result Card and Search ResultPage content")
	public void verifySearchResultCardPageAndSearchResultCard() {
		FlightsSearchPage flightsSearchPage = new FlightsSearchPage();
		flightsSearchPage.launchPage();
		FlightSearchResultsPage flightSearchResultsPage = flightsSearchPage
				.searchFlight("test.flight.oneway");
		flightSearchResultsPage.verifySearchResultPage("test.flight.oneway");
		flightSearchResultsPage.verifyCard("1");
	}

	@Test(enabled = true, description = "Verify Flight Information Page")
	public void verifyFlightInfoPage() {
		FlightsSearchPage flightsSearchPage = new FlightsSearchPage();
		flightsSearchPage.launchPage();
		FlightSearchResultsPage flightSearchResultsPage = flightsSearchPage
				.searchFlight("test.flight.oneway");
		TripDetailPage tripDetailsPage = flightSearchResultsPage
				.selectResultCard("1");
		tripDetailsPage.verifyTripDetailsWebPage("test.flight.oneway");
	}

	@Test(description = "Booking Flow")
	public void bookingFlow() {
		FlightsSearchPage flightsSearchPage = new FlightsSearchPage();
		flightsSearchPage.launchPage();
		FlightSearchResultsPage flightSearchResultsPage = flightsSearchPage
				.searchFlight("test.flight.oneway");
		flightSearchResultsPage.selectResultCard("1");
		bookFlight();
	}
}
