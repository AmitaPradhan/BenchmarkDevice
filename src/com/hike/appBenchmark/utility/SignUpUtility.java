package com.hike.appBenchmark.utility;

import org.junit.Assert;

import com.hike.appBenchmark.common.HikeConstant;
import com.hike.appBenchmark.common.HikeLibrary;
import com.hike.appBenchmark.objectlocator.popup.ConfirmYourNumberPopup;
import com.hike.appBenchmark.objectlocator.screen.AboutYouLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.HomeScreen;
import com.hike.appBenchmark.objectlocator.screen.PhoneNumberLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.PinScreen;
import com.hike.appBenchmark.objectlocator.screen.RestoreAccountBackupScreen;
import com.hike.appBenchmark.objectlocator.screen.TellUsMoreLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.WelcomeScreen;

import android.os.RemoteException;

/**
 * @author pooja
 *
 */
public class SignUpUtility extends HikeLibrary {
	public void createUser() {
		launchHikeWithoutWaitingForPopup();
		WelcomeScreen welcome_scrn = new WelcomeScreen(false);
		if (isElementPresentOnScreen(welcome_scrn.getGetStartedBtn())) {
			try {
				System.out.println("Signing up");
				getMSISDNFromFile();
				setPin();
				welcome_scrn.clickOnHikeMessengerLogoIcon();
				PhoneNumberLoginScreen phoneNumberLoginScreen = welcome_scrn
						.clickOnGetStartedBtn();
				phoneNumberLoginScreen
						.enterTextPhoneNumber(getMSISDNWithoutCountryCode());
				ConfirmYourNumberPopup confirmYourNumberPopup = phoneNumberLoginScreen
						.clickOnNextBtn();
				PinScreen pin_scrn = confirmYourNumberPopup.clickOnConfirmBtn();
				pin_scrn.enterTextPin(HikeConstant.DEFAULT_PIN);
				AboutYouLoginScreen aboutYouLoginScreen = pin_scrn
						.clickOnNextBtn();
				aboutYouLoginScreen.enterTextName(HikeConstant.DEFAULT_NAME);
				TellUsMoreLoginScreen tellUsMoreLoginScreen = aboutYouLoginScreen
						.clickonNextBtn();
				int index = (int) (Math.random() * 2);
				if (index == 1)
					tellUsMoreLoginScreen.clickOnIAmABoyLbl();
				else
					tellUsMoreLoginScreen.clickOnIAmAGirlLbl();
				tellUsMoreLoginScreen.clickOnNextBtn();
				Thread.sleep(3000);
				RestoreAccountBackupScreen backup_scrn = new RestoreAccountBackupScreen(
						false);
				clickOnElementContinueOnFail(backup_scrn.getSkipLbl());
				HomeScreen home = new HomeScreen();
				Assert.assertTrue("Creation Of Account Failed"
						+ returnScreenshot(),
						isElementPresentOnScreen(home.getStartANewChatIcon()));
				goToHome();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Failed while creating user due to an exception "
						+ returnScreenshot());
			}

		} else {
			System.out.println("Account already created" + returnScreenshot());
		}
		try {
			setPotrait();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
