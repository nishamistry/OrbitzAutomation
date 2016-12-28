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
import com.infostretch.automation.ui.api.WebDriverTestPage;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.ispl.automation.sample.orbitz.common.Calendar;
import com.ispl.automation.sample.orbitz.common.CalendarImpl;
import com.ispl.automation.sample.orbitz.common.FlightsSearchFormBean;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchPageLocators;

/**
 * com.sample.automation.webdriver.pages.FlightsPage.java
 * 
 * @author chirag
 */
public class FlightsSearchMobilePage extends
		OrbitzTemplateMobilePage<WebDriverTestPage> implements
		FlightSearchPageLocators {

	private FlightsSearchMobileFormBean searchForm;

	public FlightsSearchMobilePage() {
		super(NavTab.flights);
		searchForm = new FlightsSearchMobileFormBean();
	}

	@Override
	public void waitForPageToLoad() {
		super.waitForPageToLoad();
		waitForAjaxToComplete();
	}

	@QAFTestStep(description = "Search flight with: {0}")
	public FlightSearchResultsMobilePage searchFlight(Object searchData) {
		searchForm.fillData(searchData);

		searchForm.fillUiRequiredElements();
		FlightSearchResultsMobilePage flightSearchResultsMobilePage = searchForm
				.submit(new FlightSearchResultsMobilePage());
		flightSearchResultsMobilePage.waitForPageToLoad();
		flightSearchResultsMobilePage.waitForAjaxToComplete();
		return flightSearchResultsMobilePage;
	}

	@QAFTestStep(stepName = "Navigate to search flight page", description = "Navigate to search flight page")
	public void launchPage() {
		super.launchPage(null);
	}

	public FlightsSearchMobileFormBean getSearchForm() {
		return searchForm;
	}

	public class FlightsSearchMobileFormBean extends FlightsSearchFormBean
			implements FlightSearchPageLocators {

		@Override
		public void fillLeaveDate() {
			QAFWebElement calIcon = new QAFExtendedWebElement(
					STARTDATE_CAL_ICON);
			calIcon.click();
			Calendar calendar = new CalendarImpl(CAL_BODY);
			calendar.setDate(getLeaveDate());
			if (calendar.isPresent()) {
				calendar.close();
			}
		}

		@Override
		protected void fillReturnDate() {
			QAFWebElement calIcon = new QAFExtendedWebElement(ENDDATE_CAL_ICON);
			calIcon.click();
			Calendar calendar = new CalendarImpl(CAL_BODY);
			calendar.setDate(getReturnDate());
			if (calendar.isPresent()) {
				calendar.close();
			}
		}
	}

}
