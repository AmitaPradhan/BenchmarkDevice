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
public class AboutYouLoginScreen extends UiAutomatorLibrary {
	private UiObject aboutYouTitleLbl;
	private UiObject nextBtn;
	private UiObject tickIcon;
	private UiObject yourNameTxt;
	private UiObject howYoungAreYouTxt;
	private UiObject profileImageIcon;
	private UiObject cameraIcon;
	private UiObject backKey;

	public AboutYouLoginScreen() {
		waitForScreenToLoad(true);
	}

	public AboutYouLoginScreen(boolean check) {
		waitForScreenToLoad(check);
	}

	public void waitForScreenToLoad(boolean check) {
		if (check) {
			try {
				boolean found = waitForElement(getAboutYouTitleLbl(),
						DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("About you login screen not shown"+returnScreenshot());
				found = waitForElement(getNextBtn(), DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("About you login screen not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("About you login screen not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("About you login screen not shown"+returnScreenshot());

			}
		}

	}

	public UiObject getTickIcon() {
		tickIcon=getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/arrow");
		return tickIcon;
	}

	public UiObject getProfileImageIcon() {
		// TODO NEED CONTENT DESCRIPTION
		UiObject frame = getElement(Locators.CLASSNAME,
				AndroidClassNames.FRAME_LAYOUT, 1);
		profileImageIcon = getChild(frame, Locators.CLASSNAME,
				AndroidClassNames.IMAGE_VIEW, 0);
		return profileImageIcon;
	}

	public UiObject getCameraIcon() {
		cameraIcon=getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/profile");
		return cameraIcon;
	}

	public UiObject getAboutYouTitleLbl() {
		aboutYouTitleLbl = getElement(Locators.NAME, "About you");
		return aboutYouTitleLbl;
	}

	/**
	 * @return the backKey
	 */
	public UiObject getBackKey() {
		UiObject viewBack =getElement(Locators.CLASSNAME, AndroidClassNames.VIEW,0);
		UiObject framelayoutBack =getChild(viewBack, Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT,1);
		backKey = getChild(framelayoutBack, Locators.CLASSNAME,  AndroidClassNames.IMAGE_BUTTON , 0);
		return backKey;
	}

	public UiObject getNextBtn() {
		nextBtn = getElement(Locators.NAME, "Next");
		return nextBtn;
	}

	public UiObject getYourNameTxt() {
		yourNameTxt = getElement(Locators.NAME, "Your name");
		return yourNameTxt;
	}

	public UiObject getHowYoungAreYouTxt() {
		howYoungAreYouTxt = getElement(Locators.NAME, "How young are you?");
		return howYoungAreYouTxt;
	}

	public TellUsMoreLoginScreen clickonNextBtn() throws InterruptedException,
	UiObjectNotFoundException {
		clickOnElement(getNextBtn());
		TellUsMoreLoginScreen tellUsMoreLoginScreen = new TellUsMoreLoginScreen();
		return tellUsMoreLoginScreen;
	}

	public void clickonYourNameTxt() {
		clickOnElement(getYourNameTxt());
	}

	public void clickonHowYoungAreYouTxt() {
		clickOnElement(getHowYoungAreYouTxt());
	}

	public void enterTextName(String name) {
		enterText(getYourNameTxt(), name);
	}

	public void enterTextAge(String age) {
		enterText(getHowYoungAreYouTxt(), age);
	}

}
