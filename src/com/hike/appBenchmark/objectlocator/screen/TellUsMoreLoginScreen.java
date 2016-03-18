package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author pooja
 *
 */
public class TellUsMoreLoginScreen extends UiAutomatorLibrary {
	private UiObject tellUsMoreTitleLbl;
	private UiObject nextBtn;
	private UiObject tickIcon;
	private UiObject pickOneLbl;
	private UiObject iAmABoyLbl;
	private UiObject iAmAGirlLbl;

	public TellUsMoreLoginScreen() {
		try {
			boolean found = waitForElement(getTellUsMoreTitleLbl(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("Tell us more screen not shown"+returnScreenshot());
			waitForElement(getIAmAGirlLbl(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("Tell us more screen not shown"+returnScreenshot());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Tell us more screen not shown"+returnScreenshot());

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Tell us more screen not shown"+returnScreenshot());

		}
	}

	public UiObject getTickIcon() {
		tickIcon=getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/arrow");
		return tickIcon;
	}

	public UiObject getTellUsMoreTitleLbl() {
		tellUsMoreTitleLbl = getElement(Locators.NAME,
				"Tell us more");
		return tellUsMoreTitleLbl;
	}

	public UiObject getNextBtn() {
		nextBtn = getElement(Locators.NAME, "Next");
		return nextBtn;
	}

	public UiObject getPickOneLbl() {
		pickOneLbl = getElement(Locators.NAME, "Pick One...");
		return pickOneLbl;
	}

	public UiObject getIAmABoyLbl() {
		iAmABoyLbl = getElement(Locators.NAME, "I am a boy");
		return iAmABoyLbl;
	}

	public UiObject getIAmAGirlLbl() {
		iAmAGirlLbl = getElement(Locators.NAME, "I am a girl");
		return iAmAGirlLbl;
	}

	public RestoreAccountBackupScreen clickOnNextBtn() {
		clickOnElement(getNextBtn());

		return new RestoreAccountBackupScreen();
	}

	public void clickOnIAmABoyLbl() {
		clickOnElement(getIAmABoyLbl());
	}

	public void clickOnIAmAGirlLbl() {
		clickOnElement(getIAmAGirlLbl());
	}

	public void clickOnTickIcon() {
		clickOnElement(getTickIcon());
	}
}
