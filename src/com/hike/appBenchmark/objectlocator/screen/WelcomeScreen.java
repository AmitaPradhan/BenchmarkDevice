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
public class WelcomeScreen extends UiAutomatorLibrary {
    private UiObject hikeMessengerLogoIcon;
    private UiObject welcomeToLbl;
    private UiObject termsLbl;
    private UiObject getStartedBtn;
    private UiObject madeWithLoveInIndiaLbl;

    public WelcomeScreen() {
	waitForScreenToLoad(true);
    }

    /**
     * @param check
     *            - true , wait for elements to load - false, no verification is
     *            done
     */
    public WelcomeScreen(boolean check) {
	waitForScreenToLoad(check);

    }

    public void waitForScreenToLoad(boolean check) {
	if (check) {
	    try {
	    	boolean found = waitForElement(getWelcomeToLbl(),
			DEFAULT_TIMEOUT+10);
		if (!found)
		    Assert.fail("Welcome screen not shown"+returnScreenshot());
		found = waitForElement(getTermsLbl(), DEFAULT_TIMEOUT);
		if (!found)
		    Assert.fail("Welcome screen not shown"+returnScreenshot());
	    } catch (InterruptedException e) {
		e.printStackTrace();
		Assert.fail("Welcome screen not shown"+returnScreenshot());

	    } catch (UiObjectNotFoundException e) {
		e.printStackTrace();
		Assert.fail("Welcome screen not shown"+returnScreenshot());

	    }
	}
    }

    public UiObject getHikeMessengerLogoIcon() {
	hikeMessengerLogoIcon = getElement(Locators.CONTENT_DESCRIPTION,
		"hike messenger logo");
	return hikeMessengerLogoIcon;
    }

    public UiObject getWelcomeToLbl() {
	welcomeToLbl = getElement(Locators.NAME, "Welcome to");
	return welcomeToLbl;
    }

    public UiObject getTermsLbl() {
	termsLbl = getElement(Locators.NAME, "Terms");
	return termsLbl;
    }

    public UiObject getGetStartedBtn() {
	getStartedBtn = getElement(Locators.NAME, "Get Started");
	return getStartedBtn;
    }

    public UiObject getMadeWithLoveInIndiaLbl() {
	// TODO NEED CONTENT DESCRIPTION
	madeWithLoveInIndiaLbl = getElement(Locators.CLASSNAME,
		AndroidClassNames.IMAGE_VIEW, 0);
	return madeWithLoveInIndiaLbl;
    }

	public void clickOnHikeMessengerLogoIcon() {
	clickOnElement(getHikeMessengerLogoIcon());
    }

	public SignUpEnviromentSelectScreen tapAndHoldHikeMessengerLogoIcon() throws UiObjectNotFoundException
	{
		tapAndHoldElement(getHikeMessengerLogoIcon());
		return new SignUpEnviromentSelectScreen();
	}
    public void clickOnWelcomeToLbl() {
	clickOnElement(getWelcomeToLbl());
    }

    public void clickOnTermsLbl() {
	//TODO NAV TERMS
	clickOnElement(getTermsLbl());
    }

    public PhoneNumberLoginScreen clickOnGetStartedBtn()
	    throws InterruptedException, UiObjectNotFoundException {
	clickOnElement(getGetStartedBtn());
	PhoneNumberLoginScreen phoneNumberLoginScreen = new PhoneNumberLoginScreen();
	return phoneNumberLoginScreen;
    }

    public void clickOnMadeWithLoveInIndiaLbl() {
	clickOnElement(getMadeWithLoveInIndiaLbl());
    }

}
