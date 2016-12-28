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

package com.ispl.automation.sample.orbitz.common;

import java.util.Date;
import java.util.List;

import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.ui.webdriver.QAFWebComponent;
import com.infostretch.automation.ui.webdriver.QAFWebElement;
import com.infostretch.automation.util.DateUtil;
import com.infostretch.automation.util.StringMatcher;

/**
 * com.ispl.automation.sample.components.CalendarImpl.java
 * 
 * @author chirag
 */
public class CalendarImpl extends QAFWebComponent implements Calendar {
	protected static final String CAL_DATE_FORMAT = "yyyy-M-d";

	@FindBy(locator = "css=.monthName")
	private List<QAFWebElement> monthLabels;

	@FindBy(locator = "css=.arrowPrev>img")
	private QAFWebElement navPrevLink;

	@FindBy(locator = "css=.arrowNext>*")
	private QAFWebElement navNextLink;

	@FindBy(locator = "css=.closeBar>span")
	private QAFWebElement closeBtn;

	public CalendarImpl(String locator) {
		super(locator);
	}

	@Override
	public void setDate(Date date) {
		QAFWebElement dayEle = getDayElement(date);
		setDate(dayEle);
	}

	@Override
	public void close() {
		closeBtn.click();
	}

	private QAFWebElement getDayElement(Date date) {
		String loc = String.format("css=*[data-date='%s']",
				DateUtil.getFormatedDate(date, CAL_DATE_FORMAT));
		return new QAFExtendedWebElement(this, loc);
	}

	/**
	 * This calendar implementation only allows to select date from current
	 * displayed month or future dates
	 * 
	 * @param dayEle
	 */
	private void setDate(QAFWebElement dayEle) {
		if (dayEle.isPresent()) {
			dayEle.click();
		} else {
			String month = monthLabels.get(0).getText();
			navNextLink.click();
			monthLabels.get(0).waitForNotText(
					StringMatcher.containsIgnoringCase(month));
			setDate(dayEle);
		}
	}
}
