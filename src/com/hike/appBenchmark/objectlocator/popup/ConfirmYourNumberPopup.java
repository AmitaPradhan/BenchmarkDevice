package com.hike.appBenchmark.objectlocator.popup;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;
import com.hike.appBenchmark.objectlocator.screen.PhoneNumberLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.PinScreen;

/**
 * @author harsh
 * @description  Adding screen related UiObjects
 *
 */
public class ConfirmYourNumberPopup extends UiAutomatorLibrary {

    private UiObject confirmYourNumberTitleLbl;
    private UiObject isYourNumberLbl;
    private UiObject editBtn;
    private UiObject confirmBtn;

    public ConfirmYourNumberPopup() {
	try {
		boolean found = waitForElement(getConfirmYourNumberTitleLbl(),
		    DEFAULT_TIMEOUT);
	    if (!found)
		Assert.fail("Confirm Your Number popup not shown"+returnScreenshot());
	    found = waitForElement(getConfirmBtn(), DEFAULT_TIMEOUT);
	    if (!found)
		Assert.fail("Confirm Your Number popup not shown"+returnScreenshot());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    Assert.fail("Confirm Your Number popup not shown"+returnScreenshot());

	} catch (UiObjectNotFoundException e) {
	    e.printStackTrace();
	    Assert.fail("Confirm Your Number popup not shown"+returnScreenshot());
	}
    }

    public UiObject getConfirmYourNumberTitleLbl() {
	confirmYourNumberTitleLbl = getElement(Locators.NAME,
		"Confirm your number");
	return confirmYourNumberTitleLbl;
    }

    public UiObject getIsYourNumberLbl(String msisdn) {
	isYourNumberLbl = getElement(Locators.NAME, "Is " + msisdn
		+ "your number?");
	return isYourNumberLbl;
    }

    public UiObject getEditBtn() {
	editBtn = getElement(Locators.NAME, "EDIT");
	return editBtn;
    }

    public UiObject getConfirmBtn() {
	confirmBtn = getElement(Locators.NAME, "CONFIRM");
	return confirmBtn;
    }

    public PhoneNumberLoginScreen clickOnEditBtn() {
	clickOnElement(editBtn);
	PhoneNumberLoginScreen scrn = new PhoneNumberLoginScreen();
	return scrn;
    }

    public PinScreen clickOnConfirmBtn() throws InterruptedException,
	    UiObjectNotFoundException {
	clickOnElement(confirmBtn);
	PinScreen pinScrn = new PinScreen(false);
	waitForElement(pinScrn.getPinLbl(), 10);
	return pinScrn;
    }

}
