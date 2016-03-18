package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author harshverma
 *
 */
public class RegionalKeyboardPopScreen extends UiAutomatorLibrary{

	public RegionalKeyboardPopScreen() {
		waitForScreenToLoad(true);
	}

	public RegionalKeyboardPopScreen(boolean check) {
		if(check){
			waitForScreenToLoad(check);
		}
		else{
			if(isElementPresentOnScreen(getRegionalKeyboardTxt())){
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
				boolean found = waitForElement(getRegionalKeyboardTxt(),
						DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("Regional popup not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Regional popup not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Regional popup not shown"+returnScreenshot());
			}
		}
	}
	private UiObject regionalKeyboardTxt;
	private UiObject crossBtn;

	/**
	 * @return the regionalKeyboardTxt
	 */
	public UiObject getRegionalKeyboardTxt() {
		regionalKeyboardTxt=getElement(Locators.NAME,"Regional Keyboard");
		return regionalKeyboardTxt;
	}

	public UiObject getCrossBtn() {
		crossBtn = getElement(Locators.CONTENT_DESCRIPTION, "image_close");
		return  crossBtn;
	}

	public void clickOnCrossBtn()
	{
		clickOnElement(getCrossBtn());
	}
}
