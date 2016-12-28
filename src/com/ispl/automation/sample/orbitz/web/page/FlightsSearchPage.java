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

import com.infostretch.automation.core.MessageTypes;
import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.api.WebDriverTestPage;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.infostretch.automation.util.Reporter;
import com.ispl.automation.sample.orbitz.common.Calendar;
import com.ispl.automation.sample.orbitz.common.CalendarImpl;
import com.ispl.automation.sample.orbitz.common.FlightsSearchFormBean;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchPageLocators;

/**
 * com.sample.automation.webdriver.pages.FlightsPage.java
 * 
 * @author chirag
 */
public class FlightsSearchPage extends OrbitzTemplatePage<WebDriverTestPage>
		implements FlightSearchPageLocators {

	private FlightsSearchWebFormBean searchFormBean;

	@FindBy(locator = "css=div#search")
	private QAFExtendedWebElement searchForm;

	public FlightsSearchPage() {
		super(NavTab.Flights);
		searchFormBean = new FlightsSearchWebFormBean();
	}

	@QAFTestStep(description = "Search flight with: {0}")
	public FlightSearchResultsPage searchFlight(Object searchData) {
		searchFormBean.fillData(searchData);
		Reporter.log(searchFormBean.toString(), MessageTypes.Info);
		searchFormBean.fillUiRequiredElements();
		return searchFormBean.submit(new FlightSearchResultsPage());
	}

	@QAFTestStep(stepName = "Navigate to search flight page", description = "Navigate to search flight page")
	public void launchPage() {
		super.launchPage(null);
		driver.manage().window().maximize();
	}

	public FlightsSearchFormBean getSearchFormBean() {
		return searchFormBean;
	}

	public QAFExtendedWebElement getSearchForm() {
		return searchForm;
	}

	public class FlightsSearchWebFormBean extends FlightsSearchFormBean
			implements FlightSearchPageLocators {

		@Override
		public void fillLeaveDate() {
			QAFWebElement calIcon = new QAFExtendedWebElement(LEAVE_DAY);
			calIcon.click();
			Calendar calendar = new CalendarImpl(CAL_BODY);
			calendar.setDate(leaveDate);
			if (calendar.isPresent()) {
				calendar.close();
			}
		}

		@Override
		protected void fillReturnDate() {
			QAFWebElement calIcon = new QAFExtendedWebElement(RETURN_DAY);
			calIcon.click();
			Calendar calendar = new CalendarImpl(CAL_BODY);
			calendar.setDate(returnDate);
			if (calendar.isPresent()) {
				calendar.close();
			}
		}

	}

}
