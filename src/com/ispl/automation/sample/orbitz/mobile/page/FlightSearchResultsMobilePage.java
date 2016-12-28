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

import java.util.List;

import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.api.PageLocator;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebComponent;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.infostretch.automation.util.StringMatcher;
import com.infostretch.automation.util.StringUtil;
import com.ispl.automation.sample.orbitz.common.ResultCard;
import com.ispl.automation.sample.orbitz.mobile.page.FlightsSearchMobilePage.FlightsSearchMobileFormBean;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchResultsPageLocators;

/**
 * com.sample.automation.webdriver.pages.FlightSearchResultsPage.java
 * 
 * @author chirag
 */
public class FlightSearchResultsMobilePage extends
		OrbitzTemplateMobilePage<FlightsSearchMobilePage> implements
		FlightSearchResultsPageLocators {

	@FindBy(locator = RESULTS_ACTIONCARD)
	private List<ResultCardMobileImpl> cards;

	@FindBy(locator = FLIGHT_FROM_LABEL_LOC)
	private QAFWebElement from;

	@FindBy(locator = FLIGHT_TO_LABEL_LOC)
	private QAFWebElement to;

	@QAFTestStep(description = "Select result with index {0}")
	public TripDetailsMobilePage selectResultCard(String index) {
		int i = Double.valueOf(index).intValue();
		cards.get(i - 1).waitForPresent();
		cards.get(i - 1).waitForVisible();
		cards.get(i - 1).select();
		TripDetailsMobilePage tripDetailsMobilePage = new TripDetailsMobilePage();
		tripDetailsMobilePage.waitForPageToLoad();
		return tripDetailsMobilePage;
	}

	@Override
	protected void openPage(PageLocator arg0, Object... params) {
		String data = "test.flight.oneWay";
		if ((params != null) && (params.length > 0)
				&& StringUtil.isNotBlank((String) params[0])) {
			parent.searchFlight(params[0]);
		}
		parent.searchFlight(data);
	}

	@QAFTestStep(stepName = "launch Search Results Page with SearchData", description = "launch Search Results Page with SearchData {0}")
	public void launchPage(Object... args) {
		super.launchPage(null, args);
	}

	@FindBy(locator = RESULT_SUMMARY_LOC)
	private QAFWebElement resultsSummary;

	@FindBy(locator = SEE_MORE_RESULTS)
	private QAFExtendedWebElement moreResultLink;

	@Override
	public void waitForPageToLoad() {
		resultsSummary.waitForPresent();
	}

	public List<ResultCardMobileImpl> getResultCards() {
		return cards;
	}

	public boolean hasMoreResults() {
		return moreResultLink.isDisplayed();
	}

	public class ResultCardMobileImpl extends QAFWebComponent implements
			ResultCard {
		@FindBy(locator = INFO_DEPARTURE_TIME)
		private QAFWebElement departureTime;

		@FindBy(locator = RESULT_CARD_LOC)
		private QAFWebElement cardLink;

		@FindBy(locator = "css=.money.small-cents")
		private QAFWebElement cardPrice;
		@FindBy(locator = "css=.airline")
		private QAFWebElement cardAirline;

		public ResultCardMobileImpl(String locator) {
			super(locator);
		}

		@Override
		public void select() {
			cardAirline.click();
		}

		@Override
		public QAFWebElement getArrivalTimeEle() {
			return null;
		}

		@Override
		public QAFWebElement getDepartureTimeEle() {
			return departureTime;
		}

		@Override
		public QAFWebElement getCostEle() {
			return null;
		}

		@Override
		public QAFWebElement getFlightInfoEle() {
			return null;
		}

		@Override
		public String getCost() {
			return null;
		}

		public QAFWebElement getCardPrice() {
			return cardPrice;
		}

	}

	public QAFWebElement getFrom() {
		return from;
	}

	public QAFWebElement getTo() {
		return to;
	}

	@QAFTestStep(stepName = "verify Result Card at given index", description = "verify result card at index {0}")
	public void verifyCard(int i) {
		getResultCards().get(i).getCardPrice().verifyPresent("Flight Fees");
	}

	@QAFTestStep(stepName = "verify Search Result Page", description = "verify Search Result Page for Trip {0}")
	public void verifySearchResultMobilePage(Object data) {
		FlightsSearchMobileFormBean searchMobileFormBean = new FlightsSearchMobilePage().new FlightsSearchMobileFormBean();
		searchMobileFormBean.fillData(data);

		getFrom()
				.verifyText(
						StringMatcher.contains(searchMobileFormBean
								.getBeanData("from").toString()),
						"From Location Displayed in Results Page");
		getTo().verifyText(
				StringMatcher.contains(searchMobileFormBean.getBeanData("to")
						.toString()), "To Location Displayed in Results Page");
	}

}
