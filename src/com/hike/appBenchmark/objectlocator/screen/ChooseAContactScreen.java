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
 * @author harshverma
 *
 */
public class ChooseAContactScreen extends UiAutomatorLibrary{

	private UiObject chooseAContact;
	private UiObject BackKeychooseAContact;
	private UiObject search;
	/**
	 * Constructor to verify screen
	 */
	public ChooseAContactScreen() {
		waitForScreenToLoad(true);
	}

	public ChooseAContactScreen(boolean check) {
		waitForScreenToLoad(check);
	}


    public void waitForScreenToLoad(boolean check) {
	if (check) {
		try {
			boolean found = waitForElement(getChooseAContact(),
					DEFAULT_TIMEOUT);

			if (!found)
				Assert.fail("Choose a contact screen not found"+returnScreenshot());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Choose a contact screen not found"+returnScreenshot());

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Choose a contact screen not found"+returnScreenshot());
		}
	}
    }
	public UiObject getChooseAContact() {
		chooseAContact=getElement(Locators.NAME,"Choose a contact");
		return chooseAContact;
	}
	public UiObject getBackKeychooseAContact() {
		BackKeychooseAContact=getElement(Locators.CONTENT_DESCRIPTION,"Navigate up");
		return BackKeychooseAContact;
	}
	public UiObject getSearch() {
		search=getElement(Locators.CONTENT_DESCRIPTION,"Search");
		return search;
	}

	public void clickOnChooseAContact()
	{
		clickOnElement(getChooseAContact());
	}

	public void clickOnBackKeychooseAContact()
	{
		// void as it return different screens handle in test case itself
		clickOnElement(getBackKeychooseAContact());

	}

	public void clickOnSearch()
	{
		clickOnElement(getSearch());
	}

}
