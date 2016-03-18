/**
 *
 */
package com.hike.appBenchmark.objectlocator.screen;
import com.android.uiautomator.core.UiObject;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.AndroidClassNames;
import com.hike.appBenchmark.common.Locators;

/**
 * @author harshverma
 *
 */
public class ConversationScreen extends UiAutomatorLibrary{
	private UiObject nowLbl;
	private UiObject hiddenModeLbl;
	private UiObject tapNewMessageIconTop;
	private UiObject singleTickIndicator;
	private UiObject recieveStickerTxt;
	private UiObject recieveMessageTxt;
	private UiObject recieveNudgeTxt;
	private UiObject startANewChatIcon;

	/**
	 * @return the lblMomentsAgoLbl
	 */
	public UiObject getNowLbl() {
		nowLbl=getElement(Locators.NAME,"Now");
		return nowLbl;
	}

	public UiObject getStartANewChatIcon() {
		startANewChatIcon = getElement(Locators.CONTENT_DESCRIPTION,
				"Start a new chat");
		return startANewChatIcon;
	}
	/**
	 * @return the hiddenModeLbl
	 */
	public UiObject getHiddenModeLbl() {
		hiddenModeLbl=getElement(Locators.NAME, "Tap and hold on a chat to mark a chat 'Hidden'.");
		return hiddenModeLbl;
	}

	/**
	 * @return the tapNewMessageIconTop
	 */
	public UiObject getTapNewMessageIconTop() {
		tapNewMessageIconTop=getElement(Locators.NAME,"Tap 'new message' icon on top"+"\n"+"to start a chat");
		return tapNewMessageIconTop;
	}
	public UiObject getSingleTickIndicator(){
		UiObject list = getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW);
		UiObject llayout = getChild(list, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
		UiObject llayout1 = getChild(llayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,1);
		UiObject llayout2 = getChild(llayout1, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,1);
		singleTickIndicator = getChild(llayout2, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		return singleTickIndicator;
	}

	/**
	 * @return the reciveStickerTxt
	 */
	public UiObject getRecieveStickerTxt(String contact) {
		recieveStickerTxt=getElement(Locators.NAME, contact+" - "+"Sent you a sticker");
		return recieveStickerTxt;
	}
	/**
	 * @return the recieveMessageTxt
	 */
	public UiObject getRecieveMessageTxt(String contact , String message) {

		recieveMessageTxt=getElement(Locators.NAME, contact+" - "+message);
		return recieveMessageTxt;
	}
	/**
	 * @return the recieveNudgeTxt
	 */
	public UiObject getRecieveNudgeTxt(String contact) {
		recieveNudgeTxt=getElement(Locators.NAME, contact+" - "+"Nudge!");
		return recieveNudgeTxt;
	}
}
