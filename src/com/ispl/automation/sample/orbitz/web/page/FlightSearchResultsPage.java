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

import java.util.List;

import org.openqa.selenium.By;

import com.infostretch.automation.step.QAFTestStep;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebComponent;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.infostretch.automation.util.StringMatcher;
import com.ispl.automation.sample.orbitz.common.ResultCard;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchResultsPageLocators;
import com.ispl.automation.sample.orbitz.web.page.FlightsSearchPage.FlightsSearchWebFormBean;

/**
 * com.sample.automation.webdriver.pages.FlightSearchResultsPage.java
 * 
 * @author chirag
 */
public class FlightSearchResultsPage extends
		OrbitzTemplatePage<FlightsSearchPage> implements
		FlightSearchResultsPageLocators {
	@FindBy(locator = RESULT_CARD_LOC)
	private List<ResultCardWebImpl> cards;

	private QAFExtendedWebElement moreResultLink;

	@FindBy(locator = RESULT_SUMMARY_LOC)
	private QAFWebElement resultsSummary;

	@FindBy(locator = FLIGHT_FROM_LABEL_LOC)
	private QAFWebElement from;

	@FindBy(locator = FLIGHT_TO_LABEL_LOC)
	private QAFWebElement to;

	@FindBy(locator = "css=.matchingResults")
	private QAFWebElement matchingResults;

	public FlightSearchResultsPage() {
		super(NavTab.Flights);
		moreResultLink = new QAFExtendedWebElement(
				By.partialLinkText("more result"));
	}

	public boolean hasMoreResults() {
		return moreResultLink.isPresent() && moreResultLink.isDisplayed();
	}

	@Override
	public void waitForPageToLoad() {
		resultsSummary.waitForPresent();
		matchingResults.waitForPresent();
	}

	public List<ResultCardWebImpl> getResultCards() {
		return cards;
	}

	public QAFWebElement getFrom() {
		return from;
	}

	public QAFWebElement getTo() {
		return to;
	}

	public class ResultCardWebImpl extends QAFWebComponent implements
			ResultCard, FlightSearchResultsPageLocators {
		@FindBy(locator = FLT_ARR_TIME_LOC)
		private QAFWebElement arrivalTimeEle;

		@FindBy(locator = "css=.money.small-cents")
		private QAFWebElement cardPrice;

		@FindBy(locator = FLT_SEL_LOC)
		private QAFWebElement selectBtn;

		public ResultCardWebImpl(String locator) {
			super(locator);
		}

		@Override
		public void select() {
			selectBtn.waitForPresent();
			selectBtn.click();
		}

		@Override
		public QAFWebElement getArrivalTimeEle() {
			return arrivalTimeEle;
		}

		@Override
		public QAFWebElement getDepartureTimeEle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public QAFWebElement getCostEle() {
			// TODO Auto-generated method stub
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

	@QAFTestStep(stepName = "verify Search Result Page", description = "verify Search Result Page for trip")
	public void verifySearchResultPage(Object data) {
		FlightsSearchWebFormBean searchMobileFormBean = new FlightsSearchPage().new FlightsSearchWebFormBean();
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

	@QAFTestStep(description = "Select result with index {0}")
	public TripDetailPage selectResultCard(String i) {
		Double d = Double.parseDouble(i);
		int j = d.intValue();
		cards.get(j - 1).select();
		TripDetailPage tripDetailPage = new TripDetailPage();
		tripDetailPage.waitForPageToLoad();
		tripDetailPage.waitForAjaxToComplete();
		return tripDetailPage;
	}

	@QAFTestStep(stepName = "verify Result Card at given index", description = "verify web result card at index {0}")
	public void verifyCard(String i) {
		Double d = Double.parseDouble(i);
		int j = d.intValue();
		getResultCards().get(j).getCardPrice().verifyPresent("Flight Fees");
	}

}
