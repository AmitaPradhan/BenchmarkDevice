package com.hike.appBenchmark.objectlocator.screen;
import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.Locators;

/**
 * @author harsh verma
 *
 */
public class SignUpEnviromentSelectScreen extends UiAutomatorLibrary {

	private UiObject mqttHostLbl;
	private UiObject mqttHostTextField;
	private UiObject mqttPortlbl;
	private UiObject mqttPortTextField;
	private UiObject httpHostlbl;
	private UiObject httpHostTextField;
	private UiObject httpPortlbl;
	private UiObject httpPortTextField;
	private UiObject updateBtn;
	private UiObject backKey;
	private UiObject hikeTitle;


	public SignUpEnviromentSelectScreen() {
		try {
		    Thread.sleep(5000);
			boolean found = true;
			if (!found)
				Assert.fail("SignUpEnviromentSelectScreen not shown : 'hike' title label not present"+returnScreenshot());
			found = waitForElement(getUpdateBtn(), DEFAULT_TIMEOUT);
			if (!found)
				Assert.fail("SignUpEnviromentSelectScreen not shown : 'Update' Button not present"+returnScreenshot());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("SignUpEnviromentSelectScreen not shown "+returnScreenshot());

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("SignUpEnviromentSelectScreen not shown "+returnScreenshot());
		}
	}


	/**
	 * @return the mqttHostLbl
	 */
	public UiObject getMqttHostLbl() {
		mqttHostLbl=getElement(Locators.NAME,"MQTT Host:");
		return mqttHostLbl;
	}


	/**
	 * @return the mqttHostTextField
	 */
	public UiObject getMqttHostTextField() {
		mqttHostTextField=getElement(Locators.NAME,"192.168.xxx.xxx");
		return mqttHostTextField;
	}


	/**
	 * @return the mqttPortlbl
	 */
	public UiObject getMqttPortlbl() {
		mqttPortlbl=getElement(Locators.NAME,"MQTT Port:");
		return mqttPortlbl;
	}


	/**
	 * @return the mqttPortTextField
	 */
	public UiObject getMqttPortTextField() {
		mqttPortTextField=getElement(Locators.NAME,"1883");
		return mqttPortTextField;
	}


	/**
	 * @return the httpHostlbl
	 */
	public UiObject getHttpHostlbl() {
		httpHostlbl=getElement(Locators.NAME,"HTTP Host:");
		return httpHostlbl;
	}


	/**
	 * @return the httpHostTextField
	 */
	public UiObject getHttpHostTextField() {
		httpHostTextField=getElement(Locators.NAME,"192.168.xxx.xxx");
		return httpHostTextField;
	}


	/**
	 * @return the httpPortlbl
	 */
	public UiObject getHttpPortlbl() {
		httpPortlbl=getElement(Locators.NAME,"HTTP Port:");
		return httpPortlbl;
	}


	/**
	 * @return the httpPortTextFiled
	 */
	public UiObject getHttpPortTextField() {
		httpHostTextField=getElement(Locators.NAME,"8080");
		return httpPortTextField;
	}


	/**
	 * @return the updateBtn
	 */
	public UiObject getUpdateBtn() {
		updateBtn=getElement(Locators.NAME,"Update");
		return updateBtn;
	}


	/**
	 * @return the backKey
	 */
	public UiObject getBackKey() {
		backKey=getElement(Locators.CONTENT_DESCRIPTION,"Navigate up");
		return backKey;
	}

	/**
	 * @return the hikeTitle
	 */


	public void enterTextMqttHostTextField(String text)
	{
		enterText(getMqttHostTextField(),text);
	}

	public void enterTextMqttPortTextField(int port)
	{
		String mqttPort=Integer.toString(port);
		enterText(getMqttPortTextField(),mqttPort);
	}

	public void enterTextHttpHostTextField(String text)
	{
		enterText(getHttpHostTextField(),text);
	}

	public void enterTextHttpPortTextField(int port)
	{
		String httpPort=Integer.toString(port);
		enterText(getHttpPortTextField(),httpPort);
	}

	public WelcomeScreen clickOnUpdateBtn()
	{
		clickOnElement(getUpdateBtn());
		return new WelcomeScreen();
	}

	public WelcomeScreen clickOnBackKey()
	{
		clickOnElement(getBackKey());
		return new WelcomeScreen();
	}
}
