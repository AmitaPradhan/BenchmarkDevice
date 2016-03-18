/**
 *
 */
package com.hike.appBenchmark.objectlocator.screen;

import com.android.uiautomator.core.UiObject;
import com.hike.appBenchmark.common.Locators;

/**
 * @author pooja
 *
 */
public class OneToOneChatThreadScreen extends ChatThreadScreen {
    private UiObject onSmsLbl;// one
    private UiObject sendFreeSms;
    private UiObject sendPaidSms;
    private UiObject isOffline;
    private UiObject next;
    private UiObject send;
    private UiObject willBeSentAs;
    private UiObject sms;
    private UiObject blockLbl;
    private UiObject addLbl;
    private UiObject freeHikeSmsLbl;
    private UiObject regularSmsLbl;

    public UiObject getonSmsLbl() {
	onSmsLbl=getElement(Locators.NAME, "on SMS");
	return onSmsLbl;
}

    public UiObject getsendFreeSms() {
	sendFreeSms=getElement(Locators.NAME,"Send free SMS");
	return sendFreeSms;
}

public UiObject getsendPaidSms() {
	sendPaidSms=getElement(Locators.NAME,"Send paid SMS");
	return sendPaidSms;
}

public UiObject getisOffline() {
	isOffline=getElement(Locators.NAME," is offline");
	return isOffline;
}

public UiObject getNext() {
	next=getElement(Locators.NAME,"NEXT");
	return next;
}

public UiObject getSend() {
	send=getElement(Locators.NAME, "SEND");
	return send;
}

public UiObject getwillBeSentAs() {
	willBeSentAs=getElement(Locators.NAME,"will be sent as ");
	return willBeSentAs;
}

public UiObject getSms() {
	sms=getElement(Locators.NAME," SMS");
	return sms;
}



public UiObject getblockLbl() {
	blockLbl=getElement(Locators.NAME,"Block");
	return blockLbl;
}

public UiObject getaddLbl() {
	addLbl=getElement(Locators.NAME,"Add");
	return addLbl;
}

public UiObject getfreeHikeSmsLbl() {
	freeHikeSmsLbl=getElement(Locators.NAME,"Free hike SMS");
	return freeHikeSmsLbl;
}

public UiObject getregularSmsLbl() {
	regularSmsLbl=getElement(Locators.NAME,"Regular SMS");
	return regularSmsLbl;
}

}
