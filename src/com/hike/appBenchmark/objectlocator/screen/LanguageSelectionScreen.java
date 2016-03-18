package com.hike.appBenchmark.objectlocator.screen;
import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author harsh
 *
 */
public class LanguageSelectionScreen extends UiAutomatorLibrary {

	private UiObject languageTitleTxt;
	private UiObject doneBtn;
	/**
	 * Constructor to verify screen
	 */
	public LanguageSelectionScreen() {
		waitForScreenToLoad(true);
	}

	public LanguageSelectionScreen(boolean check) throws InterruptedException, UiObjectNotFoundException{
		waitForElement(getLanguageTitleTxt(), DEFAULT_TIMEOUT);
		if(check){
			waitForScreenToLoad(check);
		}
		else{

			if(isElementPresentOnScreen(getLanguageTitleTxt())){
				waitForScreenToLoad(true);
			}
			else{
				System.out.println("Skipping screen verification"+returnScreenshot());
			}
		}
	}
	public void waitForScreenToLoad(boolean check) {
		if(check)
		{
			try {
				Boolean found = waitForElement(getLanguageTitleTxt(),
						DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("Language selection screen not shown"+returnScreenshot());
				found = waitForElement(getDoneBtn(), DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("Language selection screen not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Language selection screen not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Language selection screen not shown"+returnScreenshot());
			}
		}
	}
	/**
	 * @return the languageTitleTxt
	 */
	public UiObject getLanguageTitleTxt() {
		languageTitleTxt=getElement(Locators.NAME,"Language");
		return languageTitleTxt;
	}
	/**
	 * @return the doneBtn
	 */
	public UiObject getDoneBtn() {
		doneBtn=getElement(Locators.NAME, "Done");
		return doneBtn;
	}
}
