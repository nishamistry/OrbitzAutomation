package com.ispl.automation.sample.orbitz.common;

import java.util.Date;

import org.openqa.selenium.Keys;

import com.infostretch.automation.data.BaseFormDataBean;
import com.infostretch.automation.ui.WebDriverBaseTestPage;
import com.infostretch.automation.ui.annotations.UiElement;
import com.infostretch.automation.ui.annotations.UiElement.Type;
import com.infostretch.automation.ui.webdriver.QAFExtendedWebElement;
import com.infostretch.automation.util.Randomizer;
import com.ispl.automation.sample.orbitz.repositroy.Locators.FlightSearchPageLocators;

public abstract class FlightsSearchFormBean extends BaseFormDataBean implements
		FlightSearchPageLocators {

	// public enum TripTypes {
	// roundTrip("Round-trip"), oneWay("One-way"), multiCity("Multi-city");
	//
	// String innerText;
	//
	// TripTypes(String innerText) {
	// this.innerText = innerText;
	// }
	//
	// public String getInnerText() {
	// return innerText;
	// }
	//
	// };

	@Randomizer(skip = true)
	@UiElement(fieldLoc = TRIP_TYPE_OPT_LOC, fieldType = Type.optionbox, required = true, order = 1)
	protected String tripType;

	@UiElement(fieldLoc = FROM_TXT_LOC, required = true, order = 2)
	protected String from;

	@UiElement(fieldLoc = INCLUDE_FROM80_CHK_LOC, fieldType = Type.checkbox)
	protected boolean includeFrom80;

	@UiElement(fieldLoc = TO_TXT_LOC, required = true, order = 3)
	protected String to;

	@UiElement(fieldLoc = INCLUDE_TO80_CHK_LOC, fieldType = Type.checkbox)
	protected boolean includeTo80;

	@Randomizer(minval = 0, maxval = 3)
	@UiElement(fieldLoc = LEAVE_DAY, required = true, order = 4)
	protected Date leaveDate;

	@UiElement(fieldLoc = SELECT_LEAVE_TIME, fieldType = Type.selectbox)
	protected String leaveTime;

	@Randomizer(minval = 3, maxval = 6)
	@UiElement(fieldLoc = RETURN_DAY, dependsOnField = "tripType", dependingValue = "roundTrip", required = true, order = 5)
	protected Date returnDate;

	@UiElement(fieldLoc = SELECT_RETURN_TIME, dependsOnField = "tripType", dependingValue = "roundTrip", fieldType = Type.selectbox)
	protected String returnTime;

	@UiElement(fieldLoc = INCLUDE_3DAY_FLEXI_SEARCH, fieldType = Type.checkbox, dependsOnField = "tripType", dependingValue = "roundTrip")
	protected boolean search3Day = false;

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public void setFrom(String from) {
		this.from = from + Keys.ENTER;
	}

	public void setIncludeFrom80(boolean includeFrom80) {
		this.includeFrom80 = includeFrom80;
	}

	public void setTo(String to) {
		this.to = to + Keys.ENTER;
	}

	public void setIncludeTo80(boolean includeTo80) {
		this.includeTo80 = includeTo80;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getTripType() {
		return tripType;
	}

	public String getFrom() {
		return from;
	}

	public boolean isIncludeFrom80() {
		return includeFrom80;
	}

	public String getTo() {
		return to;
	}

	public boolean isIncludeTo80() {
		return includeTo80;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public boolean isSearch3Day() {
		return search3Day;
	}

	protected abstract void fillLeaveDate();

	protected abstract void fillReturnDate();

	public void fillTripType() {
		QAFExtendedWebElement opt = new QAFExtendedWebElement(
				"xpath=//*[contains(text(),'" + tripType + "')]");
		opt.waitForVisible();
		opt.click();
	}

	public <T extends WebDriverBaseTestPage<?>> T submit(T t) {
		new QAFExtendedWebElement("name=search").click();
		t.waitForPageToLoad();
		t.waitForAjaxToComplete();
		return t;
	}
}
