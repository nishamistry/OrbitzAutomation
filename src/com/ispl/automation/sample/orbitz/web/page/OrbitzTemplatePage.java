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

import org.openqa.selenium.By;

import com.infostretch.automation.core.ConfigurationManager;
import com.infostretch.automation.ui.WebDriverBaseTestPage;
import com.infostretch.automation.ui.annotations.FindBy;
import com.infostretch.automation.ui.api.PageLocator;
import com.infostretch.automation.ui.api.WebDriverTestPage;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;

/**
 * com.sample.automation.webdriver.pages.OrbitzTemplatepat.java
 * 
 * @author chirag
 */
public class OrbitzTemplatePage<P extends WebDriverTestPage> extends
		WebDriverBaseTestPage<P> {
	public QAFExtendedWebElement progressIndicator;

	protected NavTab currtab;
	@FindBy(locator = "")
	public QAFExtendedWebElement secondarySec;

	public enum NavTab {

		QuickSearch("Quick Search", "/"), VacationPackages("Vacation Packages",
				"/"), Hotels("/hotels"), Flights(ConfigurationManager
				.getBundle().getString("flights.value"), "/"
				+ ConfigurationManager.getBundle().getString("flights.value")), Cars(
				"/App/DisplayCarSearch"), Cruises("/cruises?partnerUrl=CRUISES"), Activities(
				"/shop/oassearch"), Deals("/App/PrepareDealsHome");

		private String label, relurl;

		private NavTab(String url) {
			label = name();
			relurl = url;
		}

		private NavTab(String label, String url) {
			this.label = label;
			relurl = url;
		}

		public QAFExtendedWebElement getTab() {
			String loc = String
					.format("{'locator':'xpath=//*[contains(@class,\"headerMain\")]//a[contains(text(),\"%s\")]';'desc':' %s link'}",
							label, label);
			return new QAFExtendedWebElement(loc);
		}

		public String getLabel() {
			return label;
		}

		public void open() {
			getTab().waitForVisible();
			getTab().click();
		}

		public boolean isActive() {
			return new QAFExtendedWebElement(
					By.xpath("//li[contains(@class,'currentTab') and contains(.,'"
							+ label + "')]")).isPresent();
		}

		public void waitForLoad() {
			new QAFExtendedWebElement(
					By.xpath("//li[contains(@class,'currentTab') and contains(.,'"
							+ label + "')]")).assertPresent(label + " page");
		}

		public String getUrl() {
			return relurl;
		}
	}

	public OrbitzTemplatePage(NavTab tab) {
		currtab = tab;
		/*
		 * progressIndicator = new
		 * QAFExtendedWebElement(By.xpath(Locators.PROCESS_INDICATOR_LOC));
		 */
	}

	@Override
	public boolean isPageActive(PageLocator loc, Object... args) {
		return false;// currtab.isActive();
	}

	@Override
	protected void openPage(PageLocator arg0, Object... arg1) {
		driver.get("");
		if (!currtab.getTab().isPresent()) {
			currtab.open();
		} else {
			currtab.open();
		}
		/*
		 * * driver.get
		 */
	}

	@Override
	public void waitForPageToLoad() {
		currtab.waitForLoad();
		waitForAjaxToComplete();
	}

	/*
	 * @Override public void waitForAjaxToComplete() {
	 * super.waitForAjaxToComplete(); progressIndicator.waitForNotPresent(); }
	 */

}
