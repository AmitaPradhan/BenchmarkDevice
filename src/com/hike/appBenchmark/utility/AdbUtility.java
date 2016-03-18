package com.hike.appBenchmark.utility;


import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.hike.appBenchmark.base.ExecuteShell;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.HikeConstant;
import com.hike.appBenchmark.common.Locators;

public class AdbUtility extends UiAutomatorLibrary{

	public ExecuteShell executeShellCommand = new ExecuteShell();

	public void resumeApp(String packageName,String activityName) throws InterruptedException{
		System.out.println("Resume app");
		String runComponent=packageName+'/'+activityName;
		executeShellCommand.ExecuteShellCommand("am","start",runComponent);
		Thread.sleep(4000);
	}

	public void launchApp(String packageName,String activityName) throws InterruptedException{
		System.out.println("Launching app");
		String runComponent=packageName+'/'+activityName;
		executeShellCommand.ExecuteShellCommand("am","start","-n",runComponent);
		Thread.sleep(4000);
	}

	public void forceStopApp(String packageName){

		executeShellCommand.ExecuteShellCommand("am", "force-stop",packageName);
	}

	public void launchSettings() throws InterruptedException{
		System.out.println("Launching Hike without waiting for popup");
		String packageName="com.android.settings";
		String activityName="com.android.settings.Settings";
		launchApp(packageName, activityName);
	}

	public String getLogs() throws Exception{
		System.out.println("Getting Logs");
		return	executeShellCommand.executeCommand("logcat | grep "+ executeShellCommand.getAppPid());
	}
	/*****************************************************************************************************************************************************************
	 *
	 * Functions for launching app
	 * @param packageName TODO
	 *
	 ****************************************************************************************************************************************************************/




	public void clearData(String packageName) {
		System.out.println("Clearing hike data");
		executeShellCommand.ExecuteShellCommand("pm","clear" ,HikeConstant.PACKAGE_NAME );
	}



	/*****************************************************************************************************************************************************************
	 *
	 * Functions for exiting app
	 *
	 ****************************************************************************************************************************************************************/
	/**
	 * @author pooja This function swipes hike from recent app panel
	 */
	public void forceSwipe(String appName) {
		try {
			device.pressRecentApps();
			UiObject applicationName = getElement(Locators.NAME, appName);
			applicationName.swipeLeft(10);
			device.waitForIdle();
			device.pressHome();
			device.pressRecentApps();
			Assert.assertTrue("Could not force swipe"+returnScreenshot(), !applicationName.exists());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Could not force swipe" + returnScreenshot());
		}
	}

	public boolean isDeviceOn()
	{
		System.out.println("********** Checking if device is locked/unlocked **********");
		ExecuteShell executeShellCommand = new ExecuteShell();
		String value =executeShellCommand.executeCommand("dumpsys power");
		if(value!=null)
		{
			try{
				int stateIndex= value.indexOf("Display Power: state");
				String stateValue= value.substring(stateIndex+21).split("\n")[0];
				System.out.println("\n********** Display Power: state= \n"+stateValue+"\n**********");;
				if(stateValue.equals("OFF"))
				{
					System.out.println("********** Device is OFF **********");
					return false;
				}
				else
				{
					System.out.println("********** Device is ON **********");
					return true;
				}
			}
			catch(Exception e)
			{
				Assert.fail("Failed due to an exception: "+e.getMessage());
				return false;
			}
		}
		else
		{
			Assert.fail("Device not connected as executed command returned NULL");
			return false;
		}

	}

	/****************************************************************************************************************************************************************
	 *
	 *
	 * @return boolean
	 * @author chetan
	 * Returns true if device is locked and false if device is unlocked
	 ***************************************************************************************************************************************************************/

	public boolean isDeviceLocked()
	{
		System.out.println("********** Checking if device is locked/unlocked **********");
		ExecuteShell executeShellCommand = new ExecuteShell();
		String value =executeShellCommand.executeCommand("dumpsys power");
		if(value!=null)
		{
			try{
				int timeoutIndex= value.indexOf("mMinimumScreenOffTimeoutConfig");
				String timeoutConfig= value.substring(timeoutIndex+31).split("\n")[0];
				int currentTimeoutIndex= value.indexOf("Screen off timeout: ");
				String currentTimeout= value.substring(currentTimeoutIndex+20,currentTimeoutIndex+25).split("\n")[0];
				System.out.println("********** mMinimumScreenOffTimeoutConfig: \n"+timeoutConfig+"\n********** Screen off timeout: \n"+currentTimeout+"\n**********");

				if(timeoutConfig.trim().equals(currentTimeout.trim()))
				{
					System.out.println("********** Device is LOCKED **********");
					return true;
				}
				else
				{
					System.out.println("********** Device is UNLOCKED **********");
					return false;
				}
			}
			catch(Exception e)
			{
				Assert.fail("Failed due to an exception: "+e.getMessage());
				return false;
			}
		}
		else
		{
			Assert.fail("Device not connected as executed command returned NULL");
			return false;
		}

	}

}
