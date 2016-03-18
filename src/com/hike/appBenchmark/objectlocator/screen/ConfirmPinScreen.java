/**
 *
 */
package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author yashpreet
 *
 */
public class ConfirmPinScreen extends UiAutomatorLibrary{
	private UiObject confirmYourPinLbl;
	private UiObject cancelBtn;
	private UiObject confirmBtn;
	private UiObject completeYour4DigitPinLbl;
	private UiObject yourNewUnlockPinLbl;


	public ConfirmPinScreen() {
		waitForScreenToLoad(true);
	}

	/**
	 * @param check
	 *            - true , wait for elements to load - false, no verification is
	 *            done
	 */
	public ConfirmPinScreen(boolean check) {
		waitForScreenToLoad(check);
	}

	public void waitForScreenToLoad(boolean check) {
		if (check) {
			try {
				boolean found = waitForElement(getConfirmYourPinLbl(),
						DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("confirm pin screen not shown"+returnScreenshot());
				found = waitForElement(getConfirmBtn(), DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("confirm pin screen not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("confirm pin screen not shown"+returnScreenshot());
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Hidden pin screen not shown"+returnScreenshot());
			}
		}

	}

	/**
	 * @return the confirmYourPinLbl
	 */
	public UiObject getConfirmYourPinLbl() {
		confirmYourPinLbl = getElement(Locators.NAME, "Confirm your pattern");
		return confirmYourPinLbl;
	}
	/**
	 * @return the cancelBtn
	 */
	public UiObject getCancelBtn() {
		cancelBtn = getElement(Locators.NAME, "CANCEL");
		return cancelBtn;
	}
	/**
	 * @return the confirmBtn
	 */
	public UiObject getConfirmBtn() {
		confirmBtn = getElement(Locators.NAME, "CONFIRM");
		return confirmBtn;
	}
	/**
	 * @return the completeYour4DigitPinLbl
	 */
	public UiObject getCompleteYour4DigitPinLbl() {
		completeYour4DigitPinLbl = getElement(Locators.NAME, "Complete your 4 digit PIN");
		return completeYour4DigitPinLbl;
	}
	/**
	 * @return the yourNewUnlockPinLbl
	 */
	public UiObject getYourNewUnlockPinLbl() {
		yourNewUnlockPinLbl = getElement(Locators.NAME, "Your new unlock PIN");
		return yourNewUnlockPinLbl;
	}

	public HomeScreen clickOnCancelBtn(){
		clickOnElement(getCancelBtn());
		HomeScreen homeScreen = new HomeScreen();
		return homeScreen;
	}
	public HomeScreen clickOnConfirmBtn(){
		clickOnElement(getConfirmBtn());
		HomeScreen homeScreen = new HomeScreen();
		return homeScreen;
	}
}
