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
public class OverflowMenuHomeScreen extends UiAutomatorLibrary {
	private UiObject newGroupLbl;
	private UiObject inviteFriendsLbl;
	private UiObject rewardsLbl;
	private UiObject settingsLbl;
	private UiObject statusLbl;
	private UiObject newBroadcastLbl;
	private UiObject newPhotoLbl;

	public OverflowMenuHomeScreen() {
		try {
			boolean found = waitForElement(getNewGroupLbl(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("Overflow menu on home screen is not shown"+returnScreenshot());
			found = waitForElement(getStatusLbl(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("Overflow menu on home screen not shown"+returnScreenshot());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Overflow menu on home screen not shown due to"
					+ e.getClass()+returnScreenshot());

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Overflow menu on home screen not shown due to"
					+ e.getClass()+returnScreenshot());
		}
	}

	public UiObject getNewGroupLbl() {
		newGroupLbl = getElement(Locators.NAME, "New Group");
		return newGroupLbl;
	}

	public UiObject getInviteFriendsLbl() {
		inviteFriendsLbl = getElement(Locators.NAME, "Invite Friends");
		return inviteFriendsLbl;
	}

	public UiObject getRewardsLbl() {
		rewardsLbl = getElement(Locators.NAME, "Rewards/Extras");
		return rewardsLbl;
	}

	public UiObject getSettingsLbl() {
		settingsLbl = getElement(Locators.NAME, "Settings");
		return settingsLbl;
	}

	public UiObject getStatusLbl() {
		statusLbl = getElement(Locators.NAME, "Status");
		return statusLbl;
	}

	public UiObject getNewBroadcastLbl() {
		newBroadcastLbl = getElement(Locators.NAME, "New Broadcast");
		return newBroadcastLbl;
	}

	public UiObject getNewPhotoLbl() {
		newPhotoLbl = getElement(Locators.NAME, "New Photo");
		return newPhotoLbl;
	}



	public void clickOnrewardsLbl() {
		//TODO NAV REWARDS SCREEN
		clickOnElement(getRewardsLbl());
	}


}
