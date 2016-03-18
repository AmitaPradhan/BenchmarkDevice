package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.AndroidClassNames;
import com.hike.appBenchmark.common.Locators;

/**
 * @author pooja
 *
 */
public class PinScreen extends UiAutomatorLibrary {
	private UiObject pinLbl;
	private UiObject nextBtn;
	private UiObject tickIcon;
	private UiObject pleaseEnterPinLbl;
	private UiObject pinTxt;
	private UiObject didntGetPinLbl;
	private UiObject callMeLbl;
	private UiObject wrongPinLbl;
	private UiObject hikeICon;

	public PinScreen() {
		waitForScreenToLoad(true);
	}

	public PinScreen(boolean check) {
		waitForScreenToLoad(check);
	}

	public void waitForScreenToLoad(boolean check) {
		if (check) {
			try {
				boolean found = waitForElement(getPinLbl(), DEFAULT_TIMEOUT + 50);
				if (!found)
					Assert.fail("Pin Entering screen not shown"+returnScreenshot());
				found = waitForElement(getPleaseEnterPinLbl(), DEFAULT_TIMEOUT + 50);
				if (!found)
					Assert.fail("Pin Entering screen not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Pin Entering screen not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Pin Entering screen not shown"+returnScreenshot());

			}
		}
	}

	public UiObject getTickIcon() {
		// TODO NEED CONTENT DESCRIPTION
		tickIcon = getElementFromParent(getNextBtn(), Locators.CLASSNAME,
				AndroidClassNames.IMAGE_VIEW, 0);
		return tickIcon;
	}

	public UiObject getPinLbl() {
		pinLbl = getElement(Locators.NAME, "PIN");
		return pinLbl;
	}

	public UiObject getNextBtn() {
		nextBtn = getElement(Locators.NAME, "Next");
		return nextBtn;
	}

	public UiObject getPleaseEnterPinLbl() {
		pleaseEnterPinLbl = getElement(Locators.NAME,
				"Please enter the 4 digit PIN we sent you via SMS.");
		return pleaseEnterPinLbl;
	}

	public UiObject getPinTxt() {
		pinTxt = getElement(Locators.NAME, "PIN");
		return pinTxt;
	}

	/**
	 * @return the hikeICon
	 */
	public UiObject getHikeIcon() {
		hikeICon=getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/hike_logo");
		return hikeICon;
	}

	public UiObject getDidntGetPinLbl() {
		didntGetPinLbl = getElement(Locators.NAME,
				"Didn't get the PIN, we will call you");
		return didntGetPinLbl;
	}

	public UiObject getCallMeLbl() {
		callMeLbl = getElement(Locators.NAME, "Call me ");
		return callMeLbl;
	}

	public UiObject getWrongPinLbl() {
		wrongPinLbl = getElement(Locators.NAME, "Sorry, that's a wrong PIN!");
		return wrongPinLbl;
	}

	public AboutYouLoginScreen clickOnNextBtn() throws InterruptedException,
	UiObjectNotFoundException {
		clickOnElement(getNextBtn());
		AboutYouLoginScreen aboutYouLoginScreen = new AboutYouLoginScreen(false);
		return aboutYouLoginScreen;
	}

	public void clickOnCallMeLbl() {
		clickOnElement(getCallMeLbl());
	}

	public void clickOnPinTxt() {
		clickOnElement(getPinTxt());
	}

	public void enterTextPin(String msisdn) {
		enterText(getPinTxt(), msisdn);
	}
}
