package com.hike.appBenchmark.base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.hike.appBenchmark.common.AndroidClassNames;
import com.hike.appBenchmark.common.HikeConstant;
import com.hike.appBenchmark.common.Locators;

public class UiAutomatorLibrary extends UiAutomatorTestCase {
	public static UiDevice device;

	public UiAutomatorLibrary() {
		if (device == null) {
			device = UiDevice.getInstance();
		}
	}

	public int DEFAULT_TIMEOUT = 60;

	/*****************************************************************************************************************************************************************
	 *
	 * Check for Element in the listView with assertion fails the test case if
	 * object is not found
	 *
	 * @param locator
	 * @param value
	 * @return void
	 * @throws IOException
	 *
	 *
	 ****************************************************************************************************************************************************************/

	public void searchElementInList(Locators locator, String value)
			throws IOException {
		System.out.println("Searching element in list :" + value + " by "
				+ locator);
		UiScrollable scrollable = null;

		try {
			switch (locator) {
			case NAME:
				scrollable = new UiScrollable(
						new UiSelector().className(AndroidClassNames.VIEW));
				scrollable
				.getChildByText(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue("Succesfully found element "
						+ returnScreenshot() + value + returnScreenshot(),
						isElementPresentOnScreen(Locators.NAME, value));
				break;
			case CONTENT_DESCRIPTION:
				scrollable = new UiScrollable(
						new UiSelector()
						.description(AndroidClassNames.LIST_VIEW));
				scrollable
				.getChildByDescription(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue(
						"Succesfully found element " + returnScreenshot()
						+ value + returnScreenshot(),
						isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,
								value));
				break;
			default:
				break;
			}

		} catch (Exception e) {
			Assert.fail("Element not present in the list" + returnScreenshot());
		}

	}

	public void goToDeviceHome() {
		try {
			System.out.println("Go to device home");
			performKeyevent(3);
		} catch(Exception e) {
			System.out.println("Unable to uninstall app");
			e.printStackTrace();
		}
	}

	public void launchHike() {
		System.out.println("Launching Hike");
		launchHikeWithoutWaitingForPopup();
		// Add Checks here for new popups

	}

	public void killHike() {
		System.out.println("Force Kill Hike From Background");
		ExecuteShell executeShellCommand = new ExecuteShell();
		executeShellCommand.ExecuteShellCommand("am", "force-stop" ,"com.bsb.hike");
	}

	public void launchHikeWithoutWaitingForPopup() {
		System.out.println("Launching Hike without waiting for popup");
		String runComponent = HikeConstant.PACKAGE_NAME + '/' + HikeConstant.START_ACTIVITY_NAME;
		ExecuteShell executeShellCommand = new ExecuteShell();
		executeShellCommand.ExecuteShellCommand("am", "start", "-n",
				runComponent);
	}

	public void installApp() {

		try {
			System.out.println("Launching Hike without waiting for popup");
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("pm","install","/data/local/tmp/hike.apk");
		} catch(Exception e) {
			System.out.println("Unable to uninstall app");
			e.printStackTrace();
		}
	}

	public void searchObjectInList(UiObject obj) {
		System.out.println("Searching object in list :" + obj);
		// UiScrollable scrollable=null;

		try {
			// scrollable = new UiScrollable(new
			// UiSelector().className(AndroidClassNames.LIST_VIEW));
			Assert.assertTrue("Succesfully found object " + obj
					+ returnScreenshot(), isElementPresentOnScreen(obj));

		} catch (Exception e) {
			Assert.fail("Object not present in the list" + returnScreenshot());
		}

	}

	/*****************************************************************************************************************************************************************
	 *
	 * Check for Element in the listView
	 *
	 * @param locator
	 * @param value
	 * @return false : if element is not present
	 * @return true : if element is present
	 * @author vivek
	 *
	 ****************************************************************************************************************************************************************/

	public boolean searchElementInListWithouTAsserting(Locators locator,
			String value) {
		System.out.println("Searching element in list without asserting :"
				+ value + " by " + locator);
		UiScrollable scrollable = null;
		boolean isElementPresent = false;
		try {
			switch (locator) {
			case NAME:
				scrollable = new UiScrollable(
						new UiSelector().className("android.widget.ListView"));
				System.out.println(scrollable.toString()
						+ "1 thrown after scrollabler");
				scrollable.flingToBeginning(scrollable.getMaxSearchSwipes());
				scrollable.getChildByText(
						new UiSelector().className("android.widget.TextView"),
						value);
				System.out
				.println("32------------Exception thrown after getting child");
				isElementPresent = true;
				break;
			case CONTENT_DESCRIPTION:
				scrollable = new UiScrollable(
						new UiSelector().className("android.widget.ListView"));
				scrollable.getChildByDescription(
						new UiSelector().className("android.widget.TextView"),
						value);
				isElementPresent = true;
				break;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("LOG : Not Able To Find Element");
			e.getStackTrace();
			isElementPresent = false;
		}
		return isElementPresent;
	}

	/****************************************************************************************************************************************************************
	 *
	 *
	 * @param object
	 *            (UiObject)
	 * @return true : if element is present
	 * @return false : if element is not present
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/

	public boolean isElementPresentOnScreen(UiObject object) {
		System.out
		.println("Checking if element (UiObject) is present on screen");
		boolean isElementPresent = false;
		try {
			for (int i = 0; i < 5; i++) {
				if (object.exists()) {
					isElementPresent = true;
					break;
				} else {
					Thread.sleep(100);

					continue;
				}
			}
			if (!isElementPresent) {
				System.out.println("Element " + object
						+ "is not present in the list " + returnScreenshot());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isElementPresent;
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @return true : if element is present
	 * @return false : if element is not present
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/
	public boolean isElementPresentOnScreen(Locators locator, String value) {
		System.out
		.println("Checking if element is present on screen ,locator: "
				+ locator + " value: " + value);
		boolean isElementPresent = false;
		UiObject object = null;
		switch (locator) {
		case NAME:
			object = new UiObject(new UiSelector().text(value));
			isElementPresent = isElementPresentOnScreen(object);
			break;

		case CONTENT_DESCRIPTION:
			object = new UiObject(new UiSelector().description(value));
			isElementPresent = isElementPresentOnScreen(object);
			break;

		case CLASSNAME:
			object = new UiObject(new UiSelector().className(value));
			isElementPresent = isElementPresentOnScreen(object);
			break;

		case RESOURCE_ID:
			object = new UiObject(new UiSelector().resourceId(value));
			isElementPresent = isElementPresentOnScreen(object);
			break;
		default:
			break;
		}

		return isElementPresent;
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @return void
	 * @author vivek
	 * @throws IOException
	 *
	 ***************************************************************************************************************************************************************/

	public void clickOnElement(Locators locator, String value) {
		try {
			System.out.println("Clicking on element by " + locator + " value: "
					+ value);
			UiObject object = null;
			switch (locator) {

			case NAME:

				object = new UiObject(new UiSelector().text(value));

				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By Name");
					break;
				} else {
					Assert.fail("Not able to click on element " + value
							+ " By Name " + returnScreenshot());
					break;
				}

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));

				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By CONTENT_DESCRIPTION"
							+ returnScreenshot());
					break;
				} else {
					Assert.fail("Not able to click on element " + value
							+ " By CONTENT_DESCRIPTION" + returnScreenshot());
					break;
				}

			case CLASSNAME:
				object = new UiObject(new UiSelector().className(value));

				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By CLASSNAME");
					break;
				} else {
					Assert.fail("Not able to click on element " + value
							+ " By CLASSNAME" + returnScreenshot());
					break;
				}

			case RESOURCE_ID:
				object = new UiObject(new UiSelector().resourceId(value));

				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By RESOURCE_ID");
					break;
				} else {
					Assert.fail("Not able to click on element " + value
							+ " By RESOURCE_ID" + returnScreenshot());
					break;
				}

			default:
				break;

			}
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Exception while clicking on element " + value
					+ returnScreenshot());
		}
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @param index
	 * @author vivek
	 * @throws IOException
	 *
	 ****************************************************************************************************************************************************************/
	public void clickOnElement(Locators locator, String value, int index) {
		System.out.println("Clicking on element by " + locator + " value: "
				+ value + " index:" + index);
		UiObject object = null;
		try {
			object = new UiObject(new UiSelector().className(value)
					.index(index));
			if (isElementPresentOnScreen(object)) {
				object.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Not able to click on element " + value
					+ " By CLASSNAME" + returnScreenshot());
		}
	}

	public void clickOnElement(UiObject object) {
		System.out.println("Clicking element " + object);
		try {
			object.click();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @return true : if element is present
	 * @return false : if element is not present
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/
	public Boolean clickOnElementContinueOnFail(Locators locator, String value) {
		System.out.println("Clicking on element by " + locator + " value: "
				+ value + " and continue on fail");
		UiObject object = null;
		switch (locator) {

		case NAME:
			object = new UiObject(new UiSelector().text(value));
			try {
				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By Name");
					return true;

				} else {
					System.out.println("Not able to click on Element " + value
							+ " By Name");
					return false;
				}
			} catch (Exception e) {
				System.out.println("Not able to click on Element " + value
						+ " By Name");
			}

		case CONTENT_DESCRIPTION:
			object = new UiObject(new UiSelector().description(value));
			try {
				if (isElementPresentOnScreen(object)) {
					object.click();
					System.out.println("Succesfully Clicked on Element "
							+ value + " By CONTENT_DESCRIPTION");
					return true;
				} else {
					System.out.println("Not able to click on element " + value
							+ " By CONTENT_DESCRIPTION");
					return false;
				}
			} catch (Exception e) {
				System.out.println("Not able to click on Element " + value
						+ " By CONTENT_DESCRIPTION");
			}
		default:
			return false;
		}
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @param timeout
	 * @return boolean true :if element appears false :if element does not
	 *         appears
	 * @throws InterruptedException
	 * @author vivek
	 * @throws UiObjectNotFoundException
	 *
	 ****************************************************************************************************************************************************************/
	public boolean waitForElement(Locators locator, String value, int timeout)
			throws InterruptedException, UiObjectNotFoundException {
		boolean isElementVisible = false;
		UiObject object = null;
		try {
			switch (locator) {

			case NAME:

				System.out.println("getting object");
				object = new UiObject(new UiSelector().text(value));
				System.out.println("obj" + object);
				isElementVisible = waitForElement(object, timeout);

				if (!isElementVisible) {
					Assert.fail("Element with " + locator + " value " + value
							+ " does not become visible after " + timeout
							+ " seconds" + returnScreenshot());
				}
				break;

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				isElementVisible = waitForElement(object, timeout);
				if (!isElementVisible) {
					Assert.fail("Element with " + locator + "value " + value
							+ "does not become visible after " + timeout
							+ " seconds" + returnScreenshot());
				}
				break;

			default:
				break;

			}
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		return isElementVisible;
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param object
	 *            (Uiobject)
	 * @param timeout
	 * @return boolean true :if element appears false :if element does not
	 *         appears
	 * @throws InterruptedException
	 * @author vivek
	 * @throws UiObjectNotFoundException
	 *
	 ***************************************************************************************************************************************************************/
	public boolean waitForElement(UiObject object, int timeout)
			throws InterruptedException, UiObjectNotFoundException {
		System.out.println("In between calling:Waiting for element  for "
				+ timeout);
		boolean isElementFound = false;
		for (int counter = 0; counter < timeout; counter++) {
			System.out.println("counter" + counter);
			if (object.exists()) {
				System.out.println("Element found");
				isElementFound = true;
				break;
			} else {
				Thread.sleep(1000);
				continue;
			}
		}
		return isElementFound;
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param object
	 *            (Uiobject)
	 * @param timeout
	 * @return boolean true :if element appears false :if element does not
	 *         appears
	 * @throws InterruptedException
	 * @author vivek
	 * @throws UiObjectNotFoundException
	 *
	 ***************************************************************************************************************************************************************/

	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @return void
	 * @author vivek
	 *
	 ****************************************************************************************************************************************************************/
	public void clickElementInList(Locators locator, String value) {
		System.out.println("Clicking Element in list :" + locator + " value: "
				+ value);
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));

		switch (locator) {

		case NAME:
			try {
				System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%     "
						+ scrollable.getMaxSearchSwipes());
				scrollable.flingToBeginning(scrollable.getMaxSearchSwipes());
				scrollable.getChildByText(
						new UiSelector().className("android.widget.TextView"),
						value).click();
			} catch (Exception e) {
				System.out.println("not able to get child by name" + value);
			}
			break;
		case CLASSNAME:
			// TODO
			break;
		case CONTENT_DESCRIPTION:
			// TODO
			break;
		default:
			break;
		}

	}


	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param value
	 * @return void
	 * @author harsh
	 *
	 ****************************************************************************************************************************************************************/
	public void clickElementInList(UiObject obj) {
		System.out.println("Clicking Element in list :" + obj );
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		try {
			scrollable.flingToBeginning(scrollable.getMaxSearchSwipes());
		} catch (UiObjectNotFoundException e1) {
			System.out.println("not able to get child"+obj);
			e1.printStackTrace();
		}
		try {
			obj.click();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/****************************************************************************************************************************************************************
	 *
	 * @param locator
	 * @param name
	 * @return true: if selected property of element is true false : if selected
	 *         property of element is false
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/
	public boolean isElementSelected(Locators locator, String name) {
		System.out.println("Checking if element is selected " + name);
		UiObject object = null;
		boolean isSelected = false;
		switch (locator) {

		case NAME:
			object = new UiObject(new UiSelector().text(name));
			try {
				isSelected = object.isSelected();
			} catch (Exception e) {
				System.out.println("UiObject not found");
			}
			break;

		default:
			break;
		}
		return isSelected;
	}

	/**************************************************************************************************************************************************************
	 *
	 * @param characterCount
	 * @return void
	 * @author vivek
	 *
	 **************************************************************************************************************************************************************/
	public void clearFocussedElementText(int characterCount) {
		System.out.println("clearing text from text box");
		for (int counter = 0; counter < characterCount; counter++) {
			device.pressKeyCode(67);
		}

	}

	/****************************************************************************************************************************************************************
	 *
	 * @return String : path of file where screenshot get saved
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/

	/****************************************************************************************************************************************************************
	 *
	 * @return String : path of file where screenshot get saved
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/
	public String takeScreenshot() {
		System.out.println("taking screenshot");
		String fileName = ("Screenshot" + RandomStringUtils.randomNumeric(10) + ".png");
		System.out.println("FILE NAME          ******************" + fileName);
		new File("/storage/sdcard0/Pictures").mkdir();
		File storePath = new File("/storage/sdcard0/Pictures/" + fileName);
		device.takeScreenshot(storePath);
		return fileName;
	}

	/****************************************************************************************************************************************************************
	 *
	 * @return String : path of file where screenshot get saved
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/

	public String getIP() throws IOException {

		Properties prop = new Properties();

		String propFileName = "/data/local/tmp/local.properties";
		FileReader reader = new FileReader(propFileName);
		prop.load(reader);

		String ip = prop.getProperty("ip");
		return ip;
	}

	public String returnScreenshot() {

		String fileName = takeScreenshot();
		String filePath = null;
		try {
			filePath = getIP() + "/images/" + fileName;
		} catch (UnknownHostException e) {

			e.printStackTrace();
			System.out.println("not able to fetch ip address of machine");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception while IO");
		}
		return " " + filePath;

	}

	/****************************************************************************************************************************************************************
	 *
	 * @return void
	 * @param Locators
	 * @param value
	 * @param text
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/

	public void enterText(Locators locators, String value, String text) {
		System.out.println("Entering text '" + text + "' in " + value);
		UiObject textField = null;
		try {
			switch (locators) {
			case NAME:
				textField = new UiObject(new UiSelector().text(value));
				if (isElementPresentOnScreen(textField)) {
					textField.setText(text);
					// closekeyboard();
				}
				break;
			case CONTENT_DESCRIPTION:
				textField = new UiObject(new UiSelector().description(value));
				if (isElementPresentOnScreen(textField)) {
					textField.setText(text);
					// closekeyboard();
				}
			case CLASSNAME:
				textField = new UiObject(new UiSelector().className(value));
				if (isElementPresentOnScreen(textField)) {
					textField.setText(text);
					// closekeyboard();
				}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterText(String text) {
		try {
			System.out.println("Entering text " + text);
			if (isElementPresentOnScreen(new UiObject(
					new UiSelector().className("android.widget.EditText")))) {
				UiObject textField = new UiObject(
						new UiSelector().className("android.widget.EditText"));
				textField.click();
				textField.setText(text);
				if (checkDeviceKeyboard())
					device.pressBack();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void enterText(UiObject obj, String text) {
		try {
			System.out.println("Entering text " + text);
			obj.click();
			obj.setText(text);
			if (checkDeviceKeyboard())
				device.pressBack();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/****************************************************************************************************************************************************************
	 *
	 * closes the keyboard
	 *
	 * @author vivek
	 * @return void
	 *
	 ***************************************************************************************************************************************************************/
	public void closekeyboard() {
		// Use this function wisely whenever you think the keyboard needs to be
		// closed or else the app will go back to previous page
		System.out.println("Closing keyboard");
		device.pressBack();
	}

	/***************************************************************************************************************************************************************
	 *
	 * @param uiobject
	 * @throws UiObjectNotFoundException
	 *
	 **************************************************************************************************************************************************************/
	public void tapAndHoldElement(UiObject uiobject)
			throws UiObjectNotFoundException {
		System.out.println("Tap and Hold :" + uiobject.getText());
		Rect rectangle = uiobject.getBounds();
		device.swipe(rectangle.centerX(), rectangle.centerY(),
				rectangle.centerX(), rectangle.centerY(), 100);
	}

	public void tapAndHoldElementInList(UiObject uiobject)
			throws UiObjectNotFoundException {
		System.out.println("Tap and Hold :" + uiobject.getText());
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		scrollable.dragTo(uiobject, 100);
		Rect rectangle = uiobject.getBounds();
		device.swipe(rectangle.centerX(), rectangle.centerY(),
				rectangle.centerX(), rectangle.centerY(), 100);
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param locators
	 * @param value
	 * @throws UiObjectNotFoundException
	 ***************************************************************************************************************************************************************/

	public void tapAndHoldElement(Locators locators, String value) {

		System.out.println("Tap and Hold :" + value + " by " + locators);
		UiObject object = null;
		try {
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				tapAndHoldElement(object);
				break;

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				tapAndHoldElement(object);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed while tapping and holding element " + value
					+ returnScreenshot());

		}
	}

	public void tapAndHoldOnChatElements(Locators locators, List<String> chats) {
		UiObject object = null;
		for (int i = 0; i < chats.size(); i++) {
			try {
				switch (locators) {
				case NAME:
					object = new UiObject(new UiSelector().text(chats.get(i)));
					tapAndHoldElement(object);
					break;

				case CONTENT_DESCRIPTION:
					object = new UiObject(new UiSelector().description(chats
							.get(i)));
					tapAndHoldElement(object);
					break;

				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Failed while tapping and holding element "
						+ chats.get(i) + returnScreenshot());

			}
		}
	}

	public void tapAndHoldElementInList(Locators locators, String value) {

		System.out.println("Tap and Hold :" + value + " by " + locators);
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));
		UiObject object = null;

		try {

			switch (locators) {
			case NAME:

				object = scrollable
				.getChildByText(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);
				tapAndHoldElement(object);
				break;

			case CONTENT_DESCRIPTION:

				object = scrollable
				.getChildByText(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);

				tapAndHoldElement(object);
				break;
			default:
				break;
			}
		}

		catch (Exception e) {
			System.out.println("not able to tap and hold element in List"
					+ value);

		}
	}

	public UiObject getElement(Locators locators, String value, int index) {
		System.out.println("Getting element " + value + " by " + locators
				+ " index: " + index);
		UiObject object = null;
		try {
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				break;

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				object = new UiObject(new UiSelector().className(value).index(
						index));
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return object;
	}

	public UiObject getElement(Locators locators, String value) {
		System.out.println("Getting element " + value + " by " + locators);
		UiObject object = null;
		try {
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				break;

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				object = new UiObject(new UiSelector().className(value));
				break;
			case RESOURCE_ID:
				object = new UiObject(new UiSelector().resourceId(value));
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public UiObject getChild(UiObject parent, Locators locators, String value,
			int index) {
		System.out.println("Getting child of " + parent + " by " + locators
				+ " value: " + value + " index :" + index);
		UiObject child = null;
		try {
			switch (locators) {
			case NAME:
				// object = new UiObject(new UiSelector().text(value));
				break;

			case CONTENT_DESCRIPTION:
				// object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				child = parent.getChild(new UiSelector().className(value)
						.index(index));
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("Child not found");
		}
		return child;

	}

	public UiObject getChild(UiObject parent, Locators locators, String value) {
		System.out.println("Getting child of " + parent + " by " + locators
				+ " value: " + value);
		UiObject child = null;
		try {
			switch (locators) {
			case NAME:

				child = parent.getChild(new UiSelector().text(value));
				break;

			case CONTENT_DESCRIPTION:
				// object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				child = parent.getChild(new UiSelector().className(value));
			default:
				break;
			}
		} catch (Exception e) {

			System.out.println("Child not found");

		}
		return child;

	}

	public void swipe(Point[] cordinates) {
		device.swipe(cordinates, 20);
	}

	/****************************************************************************************************************************************************************
	 *
	 * @param name
	 * @return
	 * @author vivek
	 *
	 ***************************************************************************************************************************************************************/
	@Deprecated
	public boolean isElementPresentOnScreen(String name) {
		boolean isElementPresent = false;
		try {
			for (int i = 0; i < 5; i++) {
				if (new UiObject(new UiSelector().text(name)).exists()) {
					return true;
				} else {
					Thread.sleep(1000);
					continue;
				}
			}
			if (!isElementPresent) {
				System.out.println("Element " + name
						+ " is not present in the list  " + returnScreenshot());
			}

			return isElementPresent;
		} catch (Exception e) {
			return isElementPresent;
		}

	}

	public void setLandscape() throws RemoteException {
		System.out.println("Setting Landscape orientation");
		device.setOrientationRight();

	}

	public void setPotrait() throws RemoteException {
		System.out.println("Setting Potrait orientation");
		device.setOrientationNatural();

	}

	public void searchElementInList(String message, Locators locator,
			String value) throws IOException {
		System.out.println("Searching element in list :" + value + " by "
				+ locator);
		UiScrollable scrollable = null;

		try {
			switch (locator) {
			case NAME:
				scrollable = new UiScrollable(
						new UiSelector().className(AndroidClassNames.LIST_VIEW));
				scrollable
				.getChildByText(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue(message + value + returnScreenshot(),
						isElementPresentOnScreen(Locators.NAME, value));
				break;
			case CONTENT_DESCRIPTION:
				scrollable = new UiScrollable(
						new UiSelector()
						.description(AndroidClassNames.LIST_VIEW));
				scrollable
				.getChildByDescription(new UiSelector()
				.className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue(
						message + value + returnScreenshot(),
						isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,
								value));
				break;
			default:
				break;
			}

		} catch (Exception e) {
			Assert.fail("Element not present in the list" + returnScreenshot());
		}

	}

	public Boolean clickOnElementContinueOnFail(UiObject object) {
		System.out
		.println("Clicking on element by UiObject and continue on fail");

		// object = new UiObject(new UiSelector());
		try {
			if (isElementPresentOnScreen(object)) {
				object.click();
				System.out.println("Succesfully Clicked on Element :" + object);
				return true;

			} else {
				System.out.println("Not able to click on Element " + object);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Not able to click on Element " + object);
		}

		return false;

	}

	public boolean isElementPresentOnScreenContainingText(String value) {
		System.out
		.println("Checking if element is present on screen with value: "
				+ value);
		boolean isElementPresent = false;
		UiObject object = null;
		object = new UiObject(new UiSelector().textContains(value));
		isElementPresent = isElementPresentOnScreen(object);
		return isElementPresent;
	}

	public void clickOnElementContainingText(String value) {
		System.out.println("Clicking on element by  value: " + value);
		UiObject object = null;
		try {
			object = new UiObject(new UiSelector().textContains(value));
			if (isElementPresentOnScreen(object)) {
				object.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Not able to click on element " + value
					+ returnScreenshot());
		}
	}

	public boolean searchElementInListContainingText(String value) {
		System.out.println("Searching element in list :" + value);
		UiScrollable scrollable = null;
		scrollable = new UiScrollable(
				new UiSelector().className(AndroidClassNames.LIST_VIEW));
		// UiObject object = new UiObject(new UiSelector().textContains(value));
		try {
			scrollable.getChild(new UiSelector().textContains(value));
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean checkDeviceKeyboard() {
		ExecuteShell executeShellCommand = new ExecuteShell();
		System.out.println("Detecting device keyboard presence");
		String value = executeShellCommand
				.executeCommand("dumpsys input_method | grep mShowRequested");
		int mInputShown = value.indexOf("mInputShown");
		String keyboard = value.substring(mInputShown + 12).split("\n")[0];
		System.out.println(keyboard + "&&&");
		if (keyboard.equals("true"))
			return true;
		return false;
	}

	public void lockDevice() {
		System.out.println("Locking device");

		performKeyevent(26);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void unLockDevice() {
		System.out.println("Locking device");

		performKeyevent(82);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void performKeyevent(int code) {
		String key = "" + code;
		ExecuteShell executeShellCommand = new ExecuteShell();
		executeShellCommand.ExecuteShellCommand("input", "keyevent", key);
	}

	public UiObject getElementFromParent(UiObject sibling, Locators locator,
			String value, int index) {
		System.out.println("Getting sibling by  value: " + value + " index :"
				+ index);
		UiObject object = null;
		try {
			switch (locator) {
			case NAME:
				object = sibling.getFromParent(new UiSelector().text(value));
				break;
			case CONTENT_DESCRIPTION:
				object = sibling.getFromParent(new UiSelector()
				.description(value));
				break;
			case CLASSNAME:
				object = sibling.getFromParent(new UiSelector()
				.className(value).index(index));
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("Object not found");
		}
		return object;
	}

	public void scrollToEnd(String classname, int maxSwipes) {
		try {
			UiScrollable scroll = new UiScrollable(
					new UiSelector().className(classname));
			scroll.scrollToBeginning(maxSwipes);
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not scroll to end");
		}
	}

	public Boolean checkTextContains(String text_to_be_checked, UiObject object){
		Boolean contains=false;
		try {
			contains=object.getText().contains(text_to_be_checked);
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
			Assert.fail("UiObject does not exist"+returnScreenshot());
		}
		return contains;
	}

	public boolean waitForElementWithoutAssert(Locators locator, String value , int timeout) throws InterruptedException, UiObjectNotFoundException{
		boolean isElementVisible= false;
		UiObject object = null;
		try{
			switch(locator){

			case NAME:

				System.out.println("getting object");
				object = new UiObject(new UiSelector().text(value));
				System.out.println("obj"+object);
				isElementVisible = waitForElement(object, timeout);
				break;

			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				isElementVisible = waitForElement(object, timeout);
				break;

			default:
				break;

			}
		}
		catch(Exception e){
			System.out.println("Exception"); e.printStackTrace();
		}
		return isElementVisible;
	}

	public boolean waitForElementWithoutAssert(UiObject object, int timeout)
			throws InterruptedException, UiObjectNotFoundException {
		System.out.println("In between calling:Waiting for element  for "
				+ timeout);
		boolean isElementFound = false;
		for (int counter = 0; counter < timeout; counter++) {
			System.out.println("counter" + counter);
			if (object.exists()) {
				System.out.println("Element found");
				isElementFound = true;
				break;
			} else {
				Thread.sleep(1000);
				continue;
			}
		}
		return isElementFound;
	}

	public void uninstallApp(String packageName) {

		try {
			System.out.println("Uninstalling app");
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("pm","uninstall", packageName);
		} catch(Exception e) {
			System.out.println("Unable to uninstall app");
			e.printStackTrace();
		}

	}

	public void importContacts() {

		try {
			System.out.println("");
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("am start -t text/x-vcard -d file:///data/local/tmp/Contacts.vcf -a android.intent.action.VIEW com.android.contacts");
		} catch(Exception e) {
			System.out.println("Unable to import contacts");
			e.printStackTrace();
		}
	}

	public void pushStickers() {

		try {
			System.out.println("");
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("cp -R /data/local/tmp/Stickers/* /storage/sdcard0/Android/data/com.bsb.hike/files/stickers/");
		} catch(Exception e) {
			System.out.println("Unable to push stickers");
			e.printStackTrace();
		}
	}

	public void pushFile(String source, String destination, String fileName) {
		System.out.println("Pushing file inside device");

		try {
			//use adb push
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("/Users/kumarpratyush/Documents/Setup/adt-bundle-mac-x86_64-20130911/sdk/platform-tools/adb push", source+fileName, destination);

		} catch(Exception e) {
			System.out.println("Unable to push file inside device");
			e.printStackTrace();
		}
	}

	public void deleteExistingContacts() {

		try {
			//open setting app
			String runComponent = "com.android.settings" + '/' + ".Settings";
			ExecuteShell executeShellCommand = new ExecuteShell();
			executeShellCommand.ExecuteShellCommand("am", "start", "-n",runComponent);

			//scroll till 'Apps' string is found and tap
			searchElementInList(Locators.NAME, "Apps");
			clickOnElement(Locators.NAME, "Apps");

			//tap on 'running' and then on 'all'
			clickOnElement(Locators.NAME, "Running");
			clickOnElement(Locators.NAME, "All");

			//scroll till 'Contacts storage' is found and then tap
			searchElementInList(Locators.NAME, "Contacts storage");
			clickOnElement(Locators.NAME, "Contacts storage");

			//tap on 'Clear Data' and then verify
			clickOnElement(Locators.NAME, "Clear data");
			clickOnElement(Locators.NAME, "OK");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public void goBackUsingDeviceBackKey() {
	        System.out.println("Clicking on device back button");
	        device.pressBack();
	    }
}
