
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
public class NewChatContactSelectionScreen extends UiAutomatorLibrary{

	private UiObject newChatTitleLbl;
	private UiObject searchByNameOrNumberTxt;
	private UiObject peopleOnHikeLbl;
	private UiObject smsContactLbl ;
	private UiObject freeSmsLbl;
	private UiObject tapToStartChatLbl ;
	private UiObject otherContactsLbl;
	private UiObject favoriteLbl;
	private UiObject recentlyJoinedHike;
	private UiObject overflowIcon;
	private UiObject noResultsLbl;
	private UiObject noHikeContactsLbl;
	private UiObject refreshLbl ;
	private UiObject invitedLbl;
	private UiObject searchIcon;
	private UiObject backIcon;
	private UiObject backIconOnSearch;
	private UiObject crossIconOnSearchBox;

	/**
	 * Constructor to verify screen
	 */
	public NewChatContactSelectionScreen() {

		try {
			boolean found = waitForElement(getNewChatTitleLbl(),
					DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("New chat conatct selection screen not shown"+returnScreenshot());
			found = waitForElement(getSearchIcon(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("New chat conatct selection screen not shown"+returnScreenshot());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("New chat conatct selection screen not shown"+returnScreenshot());

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("New chat conatct selection screen not shown"+returnScreenshot());
		}
	}

	/**
	 * @return the newChatTitleLbl
	 */
	public UiObject getNewChatTitleLbl() {
		newChatTitleLbl=getElement(Locators.NAME, "New Chat");
		return newChatTitleLbl;
	}
	/**
	 * @return the searchByNameOrNumberTxt
	 */
	public UiObject getSearchByNameOrNumberTxt() {
		searchByNameOrNumberTxt=getElement(Locators.NAME, "Search by name or number");
		return searchByNameOrNumberTxt;
	}
	/**
	 * @return the peopleOnHikeLbl
	 */
	public UiObject getPeopleOnHikeLbl() {
		peopleOnHikeLbl=getElement(Locators.NAME, "PEOPLE ON HIKE");
		return peopleOnHikeLbl;
	}
	/**
	 * @return the smsContactLbl
	 */

	/**
	 * @return the tapToStartChatLbl
	 */
	public UiObject getTapToStartChatLbl() {
		tapToStartChatLbl=getElement(Locators.NAME, "Tap to start chat");
		return tapToStartChatLbl;
	}
	/**
	 * @return the otherContactsLbl
	 */

	/**
	 * @return the favoriteLbl
	 */
	public UiObject getFavoriteLbl() {
		favoriteLbl=getElement(Locators.NAME,"FAVORITES");
		return favoriteLbl;
	}
	/**
	 * @return the recentlyJoinedHike
	 */

	/**
	 * @return the overflowIcon
	 */
	public UiObject getOverflowIcon() {
		overflowIcon=getElement(Locators.CONTENT_DESCRIPTION, "Other");
		return overflowIcon;
	}
	/**
	 * @return the noResultsLbl
	 */

	/**
	 * @return the noHikeContactsLbl
	 */
	public UiObject getNoHikeContactsLbl() {
		noHikeContactsLbl=getElement(Locators.NAME, "No hike contacts");
		return noHikeContactsLbl;
	}
	/**
	 * @return the refreshLbl
	 */
	public UiObject getRefreshLbl() {
		refreshLbl=getElement(Locators.NAME, "Refresh");
		return refreshLbl;
	}
	/**
	 * @return the invitedLbl
	 * @throws UiObjectNotFoundException
	 */

	/**
	 * @return the searchIcon
	 */
	public UiObject getSearchIcon() {
		searchIcon=getElement(Locators.CONTENT_DESCRIPTION, "Search");
		return searchIcon;
	}
	/**
	 * @return the backIcon
	 */
	public UiObject getBackIcon() {
		backIcon=getElement(Locators.CONTENT_DESCRIPTION, "Navigate up");
		return backIcon;
	}
	/**
	 * @return the backIconOnSearch
	 */
	public UiObject getBackIconOnSearch() {
		backIconOnSearch=getElement(Locators.CONTENT_DESCRIPTION, "Collapse");
		return backIconOnSearch;
	}

	public void clickOnSearchIcon()
	{
		clickOnElement(getSearchIcon());
	}

	public UiObject getTapToStartChat(){
		tapToStartChatLbl = getElement(Locators.NAME, "Tap to start chat");
		return tapToStartChatLbl;
	}
	public ChatThreadScreen clickOnTapToStartChat(){
		clickOnElement(getTapToStartChatLbl());
		ChatThreadScreen chatThreadScreen = new ChatThreadScreen();
		return chatThreadScreen;
	}
	public void clickOnOverflowMenu(){
		clickOnElement(getOverflowIcon());
	}
	public void clickOnRefreshLbl(){
		clickOnElement(getRefreshLbl());
	}

	public void clickOnBackIconOnSearch(){
		clickOnElement(getBackIconOnSearch());
	}
	public HomeScreen clickOnBackIcon(){
		clickOnElement(getBackIcon());
		HomeScreen homeScreen = new HomeScreen();
		return homeScreen;
	}

	public void enterTextSearchBar(String msisdn) {
		enterText(msisdn);
		if(checkDeviceKeyboard())
		{
			closekeyboard();
		}
	}

	/**
	 * @return the crossIconOnSearchBox
	 */
	public UiObject getCrossIconOnSearchBox() {
		crossIconOnSearchBox = getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/search_close_btn");
		return crossIconOnSearchBox;
	}

	public void clickOnCrossIconOnSearchBox()
	{
		clickOnElement(getCrossIconOnSearchBox());
	}

}
