/**
 *
 */
package com.hike.appBenchmark.objectlocator.screen;
import com.android.uiautomator.core.UiObject;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author yashpreet
 *
 */
public class DeviceHomeScreen extends UiAutomatorLibrary{
	private UiObject phoneLbl;
	private UiObject internetLbl;

// cant help native screen directly
	/**
	 * @return the phoneLbl
	 */
	public UiObject getPhoneLbl() {
		phoneLbl = getElement(Locators.CONTENT_DESCRIPTION, "Phone");
		return phoneLbl;
	}
	/**
	 * @return the internetLbl
	 */
	public UiObject getInternetLbl() {
		internetLbl = getElement(Locators.CONTENT_DESCRIPTION, "Internet");
		return internetLbl;
	}
	public WelcomeScreen clickShortcut(String name){
		clickOnElement(Locators.NAME, name);
		WelcomeScreen welcomeScreen=new WelcomeScreen();
		return welcomeScreen;
	}

}
