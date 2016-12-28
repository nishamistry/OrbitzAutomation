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

package com.ispl.automation.sample.orbitz.mobile.step;

import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.util.StringMatcher;
import com.ispl.automation.sample.orbitz.mobile.page.FlightTravelerInfoMobilePage;
import com.ispl.automation.sample.orbitz.mobile.page.TripDetailsMobilePage;

/**
 * com.sample.automation.webdriver.pages.FlightSearchResultsPage.java
 * 
 * @author chirag
 */
public class OrbitzMobileSteps {

	@QAFTestStep(description = "Complete booking")
	public static void bookFlight() {
		TripDetailsMobilePage tripDetailsPage = new TripDetailsMobilePage();
		FlightTravelerInfoMobilePage flightTravelerInfoPage = tripDetailsPage
				.continueToTravelerInfoPage();
		flightTravelerInfoPage.getPageTitle().verifyText(
				StringMatcher.containsIgnoringCase("Traveler information"),
				"Traveler information");
	}
	
	@QAFTestStep(description = "User login with {0} and {1}")
	public static void dologin(String user, String pwd){
		System.out.println("Login mobile User: " + user + "\t Password: " + pwd);
	}
	
	@QAFTestStep(description = "User is on Home page")
	public static void navigateToHome(){
		System.out.println("navigate to mobile home.....");
	}

	@QAFTestStep(description = "It should show error message {0} on screen")
	public static void verifyAlert(String error){
		System.out.println("Error: " + error);
	}

}
