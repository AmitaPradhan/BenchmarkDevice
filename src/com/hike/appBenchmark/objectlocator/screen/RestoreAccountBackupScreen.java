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
public class RestoreAccountBackupScreen extends UiAutomatorLibrary {
	private UiObject restoreAccountLbl;
	private UiObject skipLbl;
	private UiObject backupFoundLbl;
	private UiObject wouldYouLbl;
	private UiObject somethingWentDown;
	private UiObject restoreBtn;
	private UiObject stickerBtn;

	public RestoreAccountBackupScreen() {
		waitForScreenToLoad(true);
	}

	/**
	 * @param check
	 *            - true , wait for elements to load - false, no verification is
	 *            done
	 */
	public RestoreAccountBackupScreen(boolean check) {
		waitForScreenToLoad(check);

	}

	public void waitForScreenToLoad(boolean check) {
		if (check) {
			try {
				boolean found = waitForElement(getRestoreAccountLbl(),
						DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("Restore Backup not shown"+returnScreenshot());
				found = waitForElement(getSkipLbl(), DEFAULT_TIMEOUT);
				if (!found)
					Assert.fail("Restore Backup not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Restore Backup not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Restore Backup not shown"+returnScreenshot());

			}
		}

	}

	public UiObject getRestoreAccountLbl() {
		restoreAccountLbl = getElement(Locators.NAME, "Restore Account");
		return restoreAccountLbl;
	}

	public UiObject getSkipLbl() {
		skipLbl = getElement(Locators.NAME, "Skip");
		return skipLbl;
	}

	public UiObject getBackupFoundLbl() {
		backupFoundLbl = getElement(Locators.NAME, "Backup found");
		return backupFoundLbl;
	}

	public UiObject getWouldYouLbl() {
		wouldYouLbl = getElement(Locators.NAME,
				"would you like to restore your account?");
		return wouldYouLbl;
	}

	/**
	 * @return the restoreBtn
	 */
	public UiObject getRestoreBtn() {
		restoreBtn=getElement(Locators.CONTENT_DESCRIPTION, "Restore (Backup found)");
		return restoreBtn;
	}

	/**
	 * @return the stickerBtn
	 */
	public UiObject getStickerBtn() {
		UiObject fLayout=getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 1);
		UiObject lLayout=getChild(fLayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject lLayout2=getChild(lLayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 1);
		stickerBtn=getChild(lLayout2, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 0);
		return stickerBtn;
	}

	/**
	 * @return the somethingWentDown
	 */
	public UiObject getSomethingWentDown() {
		somethingWentDown=getElement(Locators.NAME, "Something went wrong.");
		return somethingWentDown;
	}

	public HomeScreen clickOnSkipBtn() {
		clickOnElement(getSkipLbl());
		HomeScreen scrn = new HomeScreen(false);
		return scrn;
	}

	public void clickOnStickerButton()
	{
		clickOnElement(getStickerBtn());
	}

	public void clickOnRestoreBackupButton() throws UiObjectNotFoundException
	{
		UiObject imgView= getElement(Locators.CLASSNAME,AndroidClassNames.IMAGE_VIEW,3);
		UiObject lLayout= getElement(Locators.CLASSNAME,AndroidClassNames.LINEAR_LAYOUT,0);
		device.click(imgView.getBounds().centerX(),imgView.getBounds().bottom+(lLayout.getBounds().bottom- imgView.getBounds().bottom)/3);
	}

	public void clickOnRestoreBtnLandscapeMode() throws UiObjectNotFoundException
	{
		UiObject fLayout1= getElement(Locators.CLASSNAME,AndroidClassNames.FRAME_LAYOUT,1);
		UiObject lLayout1= getChild(fLayout1,Locators.CLASSNAME,AndroidClassNames.LINEAR_LAYOUT,1);
		device.click(lLayout1.getBounds().centerX(),lLayout1.getBounds().bottom-5);

	}
}
