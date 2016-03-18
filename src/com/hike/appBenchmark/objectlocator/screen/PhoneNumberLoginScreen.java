package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.AndroidClassNames;
import com.hike.appBenchmark.common.Locators;
import com.hike.appBenchmark.objectlocator.popup.ConfirmYourNumberPopup;

/**
 * @author pooja
 *
 */
public class PhoneNumberLoginScreen extends UiAutomatorLibrary {

    private UiObject phoneNumberTitleLbl;
    private UiObject nextBtn;
    private UiObject tickIcon;
    private UiObject hiWhatsYourNumberLbl;
    private UiObject indiaLbl;
    private UiObject countryCodeLbl;
    private UiObject phoneNumberTxt;

    public PhoneNumberLoginScreen() {
    	waitForScreenToLoad(true);
    }

    public PhoneNumberLoginScreen(boolean check) {
    	waitForScreenToLoad(check);
    }
    public void waitForScreenToLoad(boolean check) {
    	if (check) {
    		try {
    			boolean found = waitForElement(getPhoneNumberTitleLbl(),
    			    DEFAULT_TIMEOUT);
    		    if (!found)
    			Assert.fail("Phone Number Entering screen not shown"+returnScreenshot());
    		    found = waitForElement(getHiWhatsYourNumberlbl(), DEFAULT_TIMEOUT);
    		    if (!found)
    			Assert.fail("Phone Number Entering screen not shown"+returnScreenshot());
    		} catch (InterruptedException e) {
    		    e.printStackTrace();
    		    Assert.fail("Phone Number Entering screen not shown"+returnScreenshot());

    		} catch (UiObjectNotFoundException e) {
    		    e.printStackTrace();
    		    Assert.fail("Phone Number Entering screen not shown"+returnScreenshot());

    		}
    	}
        }

    public UiObject getTickIcon() {
	// TODO NEED CONTENT DESCRIPTION
	tickIcon = getElementFromParent(nextBtn, Locators.CLASSNAME,
		AndroidClassNames.IMAGE_VIEW, 0);
	return tickIcon;
    }

    public UiObject getPhoneNumberTitleLbl() {
	phoneNumberTitleLbl = getElement(Locators.NAME, "Phone Number");
	return phoneNumberTitleLbl;
    }

    public UiObject getNextBtn() {
	nextBtn = getElement(Locators.NAME, "Next");
	return nextBtn;
    }

    public UiObject getHiWhatsYourNumberlbl() {
	hiWhatsYourNumberLbl = getElement(Locators.NAME,
		"Hi! What's your number?");
	return hiWhatsYourNumberLbl;
    }

    public UiObject getIndiaLbl() {
	indiaLbl = getElement(Locators.NAME, "India");
	return indiaLbl;
    }

    public UiObject getCountryCodeLbl() {
	countryCodeLbl = getElement(Locators.NAME, "91");
	return countryCodeLbl;
    }

    public UiObject getPhoneNumberTxt() {
	phoneNumberTxt = getElement(Locators.NAME, "Phone Number");
	return phoneNumberTxt;
    }

    public ConfirmYourNumberPopup clickOnNextBtn() throws InterruptedException,
	    UiObjectNotFoundException {
	clickOnElement(getNextBtn());
	ConfirmYourNumberPopup popup = new ConfirmYourNumberPopup();
	return popup;
    }

    public void clickOnIndiaLbl() {
	clickOnElement(getIndiaLbl());
    }

    public void clickOnCountryCodeLbl() {
	clickOnElement(getCountryCodeLbl());
    }

    public void clickOnPhoneNumberTxt() {
	clickOnElement(getPhoneNumberTxt());
    }

    public void enterTextPhoneNumber(String msisdn) {
	enterText(getPhoneNumberTxt(), msisdn);
    }

}
