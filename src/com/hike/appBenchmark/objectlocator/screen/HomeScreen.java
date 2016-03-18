package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.common.Locators;

/**
 * @author pooja
 *
 */
public class HomeScreen extends ConversationScreen {
	private UiObject startANewChatIcon;
	private UiObject hikeLogoIcon;
	private UiObject overflowIcon;
	private UiObject tapNewMessageIconLbl;
	private UiObject searchIcon;
	private UiObject timelineIcon;
	private UiObject stickerTxt;
	private UiObject nudgeTxt;
	private UiObject locationTxt;
	private UiObject fileTxt;
	private UiObject photoTxt;
	private UiObject contactTxt;
	private UiObject nowLbl;
	private UiObject sentYouAStickerTxt;

	public HomeScreen()
	{
		waitForScreenToLoad(true);
	}

	public HomeScreen(boolean check)
	{
		waitForScreenToLoad(check);
	}

	public void waitForScreenToLoad(boolean check) {
		if(check)
		{
			try {
				boolean found = waitForElement(getStartANewChatIcon(),
						DEFAULT_TIMEOUT + 50);
				if (!found)
					Assert.fail("Home screen not shown"+returnScreenshot());
				found = waitForElement(getTimelineIcon(), DEFAULT_TIMEOUT + 30);
				if (!found)
					Assert.fail("Home screen not shown"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Home screen not shown"+returnScreenshot());

			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Home screen not shown"+returnScreenshot());
			}
		}
	}

	public UiObject getStartANewChatIcon() {
		startANewChatIcon = getElement(Locators.CONTENT_DESCRIPTION,
				"Start a new chat");
		return startANewChatIcon;
	}

	public UiObject getHikeLogoIcon() {
		hikeLogoIcon = getElement(Locators.CONTENT_DESCRIPTION, "hikeIcon");
		return hikeLogoIcon;
	}

	public UiObject getOverflowIcon() {
		overflowIcon = getElement(Locators.CONTENT_DESCRIPTION, "Overflow menu");
		return overflowIcon;
	}

	public UiObject getTapNewMessageIconLbl() {
		tapNewMessageIconLbl = getElement(Locators.NAME,
				"Tap 'new message' icon on top" + "\n" + "to start a chat");
		return tapNewMessageIconLbl;
	}

	public UiObject getSearchIcon() {
		searchIcon = getElement(Locators.CONTENT_DESCRIPTION, "Search");
		return searchIcon;
	}

	public UiObject getTimelineIcon() {
		timelineIcon = getElement(Locators.CONTENT_DESCRIPTION, "Timeline");
		return timelineIcon;
	}

	public UiObject getStickerTxt() {
		stickerTxt = getElement(Locators.NAME, "Sticker");
		return stickerTxt;
	}


	public UiObject getLocationTxt() {
		locationTxt = getElement(Locators.NAME, "Location");
		return locationTxt;
	}

	public UiObject getFileTxt() {
		fileTxt = getElement(Locators.NAME, "File");
		return fileTxt;
	}

	public UiObject getPhotoTxt() {
		photoTxt = getElement(Locators.NAME, "Photo");
		return photoTxt;
	}

	public UiObject getContactTxt() {
		contactTxt = getElement(Locators.NAME, "Contact");
		return contactTxt;
	}

	public NewChatContactSelectionScreen clickOnStartANewChatIcon() {
		clickOnElement(getStartANewChatIcon());
		NewChatContactSelectionScreen newChatContactSelectionScreen = new NewChatContactSelectionScreen();
		return newChatContactSelectionScreen;
	}

	public void clickOnHikeLogoIcon() {
		clickOnElement(getHikeLogoIcon());
	}

	public OverflowMenuHomeScreen clickOnOverflowIcon() {
		clickOnElement(getOverflowIcon());
		OverflowMenuHomeScreen scrn = new OverflowMenuHomeScreen();
		return scrn;
	}

	/**
	 * @return the nowLbl
	 */
	public UiObject getNowLbl() {
		nowLbl = getElement(Locators.NAME, "Now");
		return nowLbl;
	}

	/**
	 * @return the sentYouAStickerTxt
	 */
	public UiObject getSentYouAStickerTxt() {
		sentYouAStickerTxt = getElement(Locators.NAME, "Sent you a sticker");
		return sentYouAStickerTxt;
	}
	/**
	 * @return the sentYouAStickerTxt
	 */
	public UiObject getSentYouAStickerTxt(String sender) {
		sentYouAStickerTxt = getElement(Locators.NAME, sender+" - "+"Sent you a sticker");
		return sentYouAStickerTxt;
	}

public ChatThreadScreen startChatDirectly(String msisdn) {

        UiObject chatInList = getElement(Locators.NAME, msisdn);
        clickElementInList(chatInList);

        return new ChatThreadScreen();
    }
}
