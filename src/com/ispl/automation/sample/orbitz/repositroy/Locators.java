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

package com.ispl.automation.sample.orbitz.repositroy;

/**
 * com.sample.automation.webdriver.repository.Locators.java
 * 
 * @author chirag
 */
public interface Locators {
	String PROCESS_INDICATOR_LOC = "process.indicator.loc";

	public interface FlightSearchPageLocators {
		String TRIP_TYPE_OPT_LOC = "trip.type.opt.loc";
		String FROM_TXT_LOC = "from.txt.loc";
		String INCLUDE_FROM80_CHK_LOC = "include.from80.chk.loc";
		String TO_TXT_LOC = "to.txt.loc";
		String INCLUDE_TO80_CHK_LOC = "include.to80.chk.loc";

		String STARTDATE_CAL_ICON = "startDate.cal.icon";
		String ENDDATE_CAL_ICON = "endDate.cal.icon";
		String CAL_BODY = "cal.body";

		String LEAVE_DAY = "leave.day";
		String RETURN_DAY = "return.day";
		String INCLUDE_3DAY_FLEXI_SEARCH = "include.3day.flexi.search";
		String SELECT_LEAVE_TIME = "select.leave.time";
		String SELECT_RETURN_TIME = "select.return.time";
		String SELECT_ADULT_NUM = "select.adult.num";
		String SELECT_SENIOR_NUM = "select.senior.num";
		String SELECT_CHILD_NUM = "select.child.num";
		String SELECT_CHILD_AGE_1 = "select.child.age1";
		String SELECT_CHILD_AGE_2 = "select.child.age2";

	}

	public interface FlightSearchResultsPageLocators {

		String RESULT_SUMMARY_LOC = "result.summary.loc";
		String RESULT_CARD_LOC = "result.card.loc";
		String RESULTS_ACTIONCARD = "results.actionCard";
		String RESULT_CARD_PRICE = "results.card.price.loc";

		String SEE_MORE_RESULTS = "see.more.results";
		String INFO_DEPARTURE_TIME = "info.departure.time";

		String FLT_PRICE_LOC = "flt.price.loc";
		String FLT_SEL_LOC = "flt.sel.loc";

		String FLT_DATE_LOC = "flt.date.loc";

		String FLT_DEPT_TIME_LOC = "dept.time.loc";
		String FLT_DEPT_CITY_LOC = "dept.city.loc";
		String FLT_DEPT_AIRCODE_LOC = "flt.dept.aircode.loc";
		String FLT_ARR_LOC = "flt.arr.loc";
		String FLT_ARR_TIME_LOC = "arr.time.loc";
		String FLT_ARR_CITY_LOC = "arr.city.loc";
		String FLT_ARR_AIRCODE_LOC = "flt.arr.aircode.loc";
		String FLT_INFO_LOC = "flt.info.loc";
		String FLT_INFO_NAMENUM_LOC = "flt.info.namenum.loc";
		String FLIGHT_FROM_LABEL_LOC = "flight.from.label.loc";
		String FLIGHT_TO_LABEL_LOC = "flight.to.label.loc";

	}

	public interface TripDetailPageLocators {

		String PRODINFO_LOC = "prodinfo.loc";
		String CONTINUE_BTN_LOC = "continue.btn.loc";
		String SEE_DIFF_FLIGHTS = "see.different.flights";
		
		String FLIGHT_FROM_LABEL_LOC = "flight.from.label.tripdetailspage.loc";
		String FLIGHT_TO_LABEL_LOC = "flight.to.label.tripdetailspage.loc";

	}
	
}
