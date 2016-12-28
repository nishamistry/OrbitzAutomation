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

package com.ispl.automation.sample.orbitz.web.step;

import com.infostretch.automation.core.ConfigurationManager;
import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.util.StringMatcher;
import com.ispl.automation.sample.orbitz.web.page.FlightCustomizeTripPage;
import com.ispl.automation.sample.orbitz.web.page.FlightTravelerInfoPage;
import com.ispl.automation.sample.orbitz.web.page.FlightsSearchPage;
import com.ispl.automation.sample.orbitz.web.page.TripDetailPage;

/**
 * com.sample.automation.webdriver.pages.FlightSearchResultsPage.java
 * 
 * @author chirag
 */
public class OrbitzWebSteps {

	@QAFTestStep(description = "Complete booking")
	public static void bookFlight() {
		TripDetailPage tripDetailsPage = new TripDetailPage();
		FlightCustomizeTripPage flightCustomizeTripPage = tripDetailsPage
				.continueToCustomizeTripPage();
		flightCustomizeTripPage.getPageTitle().verifyText(
				ConfigurationManager.getBundle().getString(
						"flight.customize.trip.page.title",
						"Customize your trip"), "Customize your trip");
		FlightTravelerInfoPage flightTravelerInfoPage = flightCustomizeTripPage
				.continueToTravelerInfo();
		flightTravelerInfoPage.getPageTitle().verifyText(
				ConfigurationManager.getBundle().getString(
						"flight.trip.traveler.info.page.title",
						"Customize your trip"),
				"Checkout: Traveler information");

	}

	@QAFTestStep(description = "Verify Error Messages on invalid Flight-Search-Form Submit")
	public static void verifyErrorMessagesOnBlankSearchForm() {
		FlightsSearchPage flightsSearchPage = new FlightsSearchPage();

		flightsSearchPage
				.getSearchForm()
				.findElement("css=fieldset p:nth-of-type(1)")
				.verifyText(
						StringMatcher
								.contains(ConfigurationManager
										.getBundle()
										.getString(
												"flight.search.form.blank.from.error.msg")),
						"Error Message on Blank From Field");

		flightsSearchPage
				.getSearchForm()
				.findElement("css=fieldset p:nth-of-type(2)")
				.verifyText(
						StringMatcher
								.contains(ConfigurationManager
										.getBundle()
										.getString(
												"flight.search.form.blank.to.error.msg")),
						"Error Message on Blank To Field");
	}
}
