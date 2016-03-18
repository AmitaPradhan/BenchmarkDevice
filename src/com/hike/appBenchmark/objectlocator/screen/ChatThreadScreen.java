package com.hike.appBenchmark.objectlocator.screen;

import org.junit.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.AndroidClassNames;
import com.hike.appBenchmark.common.Locators;

/**
 * @author Harsh
 *
 */
public class ChatThreadScreen extends UiAutomatorLibrary {
	private UiObject attachIcon;
	private UiObject overflowIcon;
	private UiObject callIcon;
	private UiObject messageTxt;
	private UiObject forwardMessageIcon;
	private UiObject shareMessageIcon;
	private UiObject copyMessageIcon;
	private UiObject deleteMessageIcon;
	private UiObject numberOfMessageSelectedLbl;
	private UiObject backKey;
	private UiObject titleLbl;
	private UiObject stickerIcon;
	private UiObject emoticonIcon;
	private UiObject audioIcon;
	private UiObject subheadingLbl;
	private UiObject sendBtn;
	private UiObject progressBar;
	private UiObject crossBtn;
	private UiObject clockUnsentIcon;
	private UiObject sendTick;
	private UiObject sendFreeSms;
	private UiObject blockLbl;
	private UiObject addLbl;
	private UiObject onSmsLbl;
	private UiObject profilePicture;
	private UiObject messageDeliveredToReceiver;
	private UiObject broadcastCreatedLbl;
	private UiObject nudge;
	private UiObject list;
	private UiObject image;
	private UiObject unblockBtn;
	private UiObject sticker;
	private UiObject fileTxtLbl;
	private int childCount;
	private UiObject hikeMessageTxt;
	private UiObject scrollViewWithIndex;
	private UiObject rLayoutWithIndex;
	private UiObject firstStickerFromChatThread;
	private UiObject secondStickerFromChatThread;


	public ChatThreadScreen() {
		waitForScreenToLoad(true);
	}

	public ChatThreadScreen(boolean check) {
		waitForScreenToLoad(check);
	}


	public void waitForScreenToLoad(boolean check) {
		if(check)
		{
			try {
				boolean found = waitForElement(getTitleLbl(), DEFAULT_TIMEOUT);
				found = waitForElement(getEmoticonIcon(), DEFAULT_TIMEOUT);

				if (!found)
					Assert.fail("Chat thread screen not found"+returnScreenshot());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail("Chat thread screen not found"+returnScreenshot());
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
				Assert.fail("Chat thread screen not found"+returnScreenshot());

			}
		}
	}

	/**
	 * @return the clockUnsentIcon
	 */
	public UiObject getMessageDeliveredToReceiver(){
		UiObject list = getElement(Locators.CLASSNAME,AndroidClassNames.LIST_VIEW);
		UiObject llayout = getChild(list, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,2);
		messageDeliveredToReceiver = getChild(llayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,1);
		return messageDeliveredToReceiver;
	}
	public UiObject getClockUnsentIcon() {
		clockUnsentIcon = getElement(Locators.CONTENT_DESCRIPTION, "Message not sent");
		return clockUnsentIcon;
	}
	public UiObject getOnSmsLbl(){
		onSmsLbl=getElement(Locators.CONTENT_DESCRIPTION, "hike status/last seen time");
		return onSmsLbl;
	}
	public UiObject getProfilePicture(){
		//content dec removed..getting through ui again
		UiObject rLayout1 = getRLayoutWithIndex(1);
		UiObject rLayout0 = getChild(rLayout1, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		profilePicture = getChild(rLayout0, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 0);
//		profilePicture = getElement(Locators.CONTENT_DESCRIPTION,"User image (rounded) chat-thread actionbar");
		return profilePicture;
	}
	/**
	 * @return the sendTick
	 */
	public UiObject getSendTick() {
		sendTick = getElement(Locators.CONTENT_DESCRIPTION, "Message not sent");
		return sendTick;
	}

	/**
	 * @return the sendFreeSms
	 */
	public UiObject getSendFreeSms() {
		sendFreeSms = getElement(Locators.NAME, "Send free SMS");
		return sendFreeSms;
	}


	public UiObject getOverflowIcon() {
		overflowIcon = getElement(Locators.CONTENT_DESCRIPTION, "Overflow menu");
		return overflowIcon;
	}

	public UiObject getCallIcon() {
		callIcon = getElement(Locators.CONTENT_DESCRIPTION, "Hike Call");
		return callIcon;
	}


	public UiObject getForwardMessageIcon() {
		forwardMessageIcon = getElement(Locators.CONTENT_DESCRIPTION, "Forward");
		return forwardMessageIcon;
	}

	public UiObject getShareMessageIcon() {
		shareMessageIcon = getElement(Locators.CONTENT_DESCRIPTION, "Share");
		return shareMessageIcon;
	}
	public UiObject getProgressBar(){
		progressBar = getElement(Locators.CLASSNAME, AndroidClassNames.PROGRESS_BAR);
		return progressBar;
	}

	/**
	 * @return the hikeMessageTxt
	 */
	public UiObject getHikeMessageTxt() {
		hikeMessageTxt=getElement(Locators.NAME, "Hike message...");
		return hikeMessageTxt;
	}

	public UiObject getCopyMessageIcon() {
		copyMessageIcon = getElement(Locators.CONTENT_DESCRIPTION, "Copy");
		return copyMessageIcon;
	}

	public UiObject getDeleteMessageIcon() {
		deleteMessageIcon = getElement(Locators.CONTENT_DESCRIPTION,"Delete");
		return deleteMessageIcon;
	}

	public UiObject getNumberOfMessageSelectedLbl(int numberOfSelections) {
		numberOfMessageSelectedLbl = getElement(Locators.NAME, numberOfSelections+" selected");
		return numberOfMessageSelectedLbl;
	}

	public UiObject getBackKey() {
		backKey = getElement(Locators.CONTENT_DESCRIPTION, "Navigate up");
		return backKey;
	}

	public UiObject getMessageTxt() {
		messageTxt = getElement(Locators.CONTENT_DESCRIPTION, "Textbox in chat thread");
		return messageTxt;
	}

	public UiObject getAttachIcon() {
		attachIcon = getElement(Locators.CONTENT_DESCRIPTION, "Attach");
		return attachIcon;
	}
	/**
	 * @return the unblockLbl
	 */
	public UiObject getUnblockBtn() {
		unblockBtn=getElement(Locators.NAME,"Unblock");
		return unblockBtn;
	}

	public UiObject getBlockLbl(){
		blockLbl = getElement(Locators.NAME, "Block");
		return blockLbl;
	}
	public UiObject getAddLbl(){
		addLbl = getElement(Locators.NAME, "Add");
		return addLbl;
	}

	public UiObject getTitleLbl() {
		titleLbl = getElement(Locators.CONTENT_DESCRIPTION,
				"Contact/group name");
		return titleLbl;
	}

	public UiObject getStickerIcon() {
		stickerIcon = getElement(Locators.CONTENT_DESCRIPTION,
				"Choose sticker to send while chatting");
		return stickerIcon;
	}

	/**
	 * @return the sticker
	 */
	public UiObject getStickerFromChatThread(int rLayoutIndex) {
		UiObject list= getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW);
		UiObject rLayout=getChild(list,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,rLayoutIndex);
		sticker= getChild(rLayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		return sticker;
	}


	/**
	 * @return the sticker
	 */
	public UiObject getRecivedStickerFromChatThread(int rLayoutIndex,int linearLayoutIndex) {
		UiObject list= getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW);
		UiObject rLayout=getChild(list,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,rLayoutIndex);
		UiObject lLayout=getChild(rLayout,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,linearLayoutIndex);
		sticker= getChild(lLayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		return sticker;
	}


	public UiObject getEmoticonIcon() {
		emoticonIcon = getElement(Locators.CONTENT_DESCRIPTION,
				"Choose smiley to send while chatting/setting status");
		return emoticonIcon;
	}

	public UiObject getAudioIcon() {
		audioIcon = getElement(Locators.CONTENT_DESCRIPTION,
				"Send recorded audio/text while chatting");
		return audioIcon;
	}

	public UiObject getSubheadingLbl() {
		subheadingLbl = getElement(Locators.CONTENT_DESCRIPTION,
				"hike status/last seen time");
		return subheadingLbl;
	}

	/**
	 * @return the sendBtn
	 * @throws UiObjectNotFoundException
	 */
	public UiObject getSendBtn(){
		sendBtn=getElement(Locators.CONTENT_DESCRIPTION, "Press button to send Message");
		return sendBtn;
	}
	/**
	 * @return the broadcastCreatedLbl
	 */


	public void clickToSendSticker() throws InterruptedException{
		try {
			UiObject relative = getElement(Locators.CLASSNAME,AndroidClassNames.RELATIVE_LAYOUT,0 );
			UiObject view=getChild(relative,Locators.CLASSNAME,AndroidClassNames.RELATIVE_LAYOUT,0);
			UiObject obj= getElement(Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
			int x= view.getBounds().width()/8;
			int y= view.getBounds().bottom+(obj.getBounds().bottom-view.getBounds().bottom)/6;
			device.click(x,y);
			Thread.sleep(2000);
		} catch (UiObjectNotFoundException e) {
			Assert.fail("Failed to click on sticker"+returnScreenshot());
			e.printStackTrace();
		}
	}
	public ChatThreadScreen clickOnSendBtn() throws UiObjectNotFoundException{
		System.out.println("inside click");
		clickOnElement(getSendBtn());
		ChatThreadScreen chatThreadScreen = new ChatThreadScreen(false);
		return chatThreadScreen;
	}

	public void clickOnCallIcon() {
		// TODO nav to call screen
		clickOnElement(getCallIcon());

	}
	public void clickOnCopyMessageIcon(){
		clickOnElement(getCopyMessageIcon());
	}

	public HomeScreen clickOnBackKey() throws InterruptedException,
	UiObjectNotFoundException {
		clickOnElement(getBackKey());
		HomeScreen homeScreen = new HomeScreen();
		return homeScreen;
	}

	public void clickOnTitleLbl() {
		clickOnElement(getTitleLbl());
	}

	public void clickOnStickerIcon() {
		clickOnElement(getStickerIcon());
	}

	public void clickOnEmoticonIcon() {
		clickOnElement(getEmoticonIcon());
	}


	/**
	 *
	 */
	public void clickToSendNudge() {
		try {
			UiObject scrollview= getElement(Locators.CLASSNAME, AndroidClassNames.SCROLL_VIEW, 4);
			int x= scrollview.getBounds().centerX();
			int y= scrollview.getBounds().centerX();
			device.click(x,y);
		} catch (Exception e) {
			Assert.fail("Failed to send nudge"+returnScreenshot());
		}
	}
	public UiObject getCrossBtn(){
		crossBtn=getElement(Locators.RESOURCE_ID,"com.bsb.hike:id/action_mode_close_button");
		return crossBtn;
	}

	public void clickOnCrossBtn()
	{
		//return different screens handled in test case itself which object to return
		clickOnElement(getCrossBtn());
		clickOnElementContinueOnFail(getCrossBtn());
	}

	public UiObject getNudgeFromScreen(){
		nudge= getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/nudge");
		return nudge;
	}
	public UiObject getListView(){
		list= getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW);
		return list;
	}


	public UiObject getImageFromScreen(){
		image= getElement(Locators.RESOURCE_ID, "com.bsb.hike:id/file_thumb");
		return image;
	}
	/**
	 * @return the fileTxtLbl
	 */


	public UiObject getImageView(int index){
		image= getElement(Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,index);
		return image;
	}

	public int getChildCount() throws UiObjectNotFoundException{
		childCount = new ChatThreadScreen().getListView().getChildCount();
		return childCount;
	}


	public void scrollChatThreadToBeginning()
	{
		UiScrollable scrollable=new UiScrollable(new UiSelector().className(AndroidClassNames.LIST_VIEW));
		try {
			scrollable.flingToBeginning(10);
		} catch (UiObjectNotFoundException e2) {
			Assert.fail("Failed while scroll "+returnScreenshot());

		}
	}

	public UiObject getImageFromChatThread(int i)
	{
		UiObject lview= getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW,0);
		UiObject rLayout1=getChild(lview, Locators.CLASSNAME,AndroidClassNames.RELATIVE_LAYOUT,i);
		image=getChild(rLayout1, Locators.CLASSNAME,AndroidClassNames.IMAGE_VIEW,0);
		return image;
	}


	public UiObject getSentFileFromChatThread(int parentRelativeLayoutIndex, int childRelativelayoutIndex)
	{
		UiObject fLayout0=getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT,0);
		UiObject fLayout00=getChild(fLayout0,Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT,0);
		UiObject rLayout1=getChild(fLayout00,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,parentRelativeLayoutIndex);
		UiObject file=getChild(rLayout1,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,childRelativelayoutIndex);
		return file;
	}

	public void selectMultipleSentStickersFromChatThread() throws UiObjectNotFoundException
	{
		try
		{
		UiCollection dummyList = new UiCollection(new UiSelector().className(AndroidClassNames.LIST_VIEW));
		System.out.println(dummyList.getChildCount());

		int count = dummyList.getChildCount();
		for(int i=0;i<count;i++)
		{
			UiObject dummyBenchmark = dummyList.getChildByInstance(new UiSelector().className(AndroidClassNames.IMAGE_VIEW).index(0),i);
			tapAndHoldElement(dummyBenchmark);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("fail while selecting stickers from chat thread screen , sticker not recieved from server after 10 second"+returnScreenshot());
		}
	}

	public void clickOnUnblockBtn()
	{
		clickOnElement(getUnblockBtn());
	}

	/**
	 * @return the scrollView
	 */
	public UiObject getScrollViewWithIndex(int index) {
		scrollViewWithIndex =getElement(Locators.CLASSNAME, AndroidClassNames.SCROLL_VIEW,index);
		return scrollViewWithIndex;
	}
	/**
	 * @return the rLayout
	 */
	public UiObject getRLayoutWithIndex(int rLayoutIndex) {
		UiObject fLayout=getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT,0);
		rLayoutWithIndex=getChild(fLayout,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,rLayoutIndex);
		return rLayoutWithIndex;
	}
	public void clickOnRLayoutWithIndex(int rLayoutIndex){
		clickOnElement(getRLayoutWithIndex(rLayoutIndex));
	}
	/**
	 * @return the firstStickerFromChatThread
	 */
	public UiObject getFirstStickerFromChatThread() {
		UiObject rLayout0=getChild(getListView(),Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,0);
		UiObject rLayout2=getChild(rLayout0,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,2);
		UiObject lLayout0=getChild(rLayout2,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
		firstStickerFromChatThread=getChild(lLayout0,Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		return firstStickerFromChatThread;
	}


	/**
	 * @return the secondStickerFromChatThread
	 */
	public UiObject getSecondStickerFromChatThread() {
		UiObject rLayout0=getChild(getListView(),Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,1);
		UiObject rLayout2=getChild(rLayout0,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,1);
		UiObject lLayout0=getChild(rLayout2,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
		secondStickerFromChatThread=getChild(lLayout0,Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		return secondStickerFromChatThread;
	}

public void scrollInsideThread() {

        try {
            UiScrollable scrollable = new UiScrollable(new UiSelector().className("android.widget.ListView"));
            scrollable.scrollToBeginning(9999);
        } catch(Exception e) {
            System.out.println("Not able to scroll");
            e.printStackTrace();
        }
    }

}
