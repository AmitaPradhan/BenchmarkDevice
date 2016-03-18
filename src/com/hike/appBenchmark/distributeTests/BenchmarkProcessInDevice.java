/**
 *
 */
package com.hike.appBenchmark.distributeTests;

import com.hike.appBenchmark.common.HikeLibrary;
import com.hike.appBenchmark.objectlocator.screen.ChatThreadScreen;
import com.hike.appBenchmark.objectlocator.screen.HomeScreen;
import com.hike.appBenchmark.objectlocator.screen.NewChatContactSelectionScreen;
import com.hike.appBenchmark.utils.BenchmarkLibrary;

/**
 * @author kumarpratyush
 *
 */
public class BenchmarkProcessInDevice extends BenchmarkLibrary {

	String benchmarkOnPercentile;

	public void test001() throws InterruptedException {
		try {
			benchmarkOnPercentile = getParams().getString("percentile");
			String msisdnToTest = getParams().getString("msisdn_to_test");
			String msisdn = getParams().getString("msisdn");
			prepareDeviceToRunBenchmarkTest();
			HikeLibrary hl = new HikeLibrary();
			hl.loginWithRestore(msisdn);
			hl.launchHike();

			//perform actions
			HomeScreen homeScreenObj = new HomeScreen();

			for(int i=0; i<5; i++) {
				ChatThreadScreen chatThreadObj = homeScreenObj.startChatDirectly(msisdnToTest);
				homeScreenObj = chatThreadObj.clickOnBackKey();
				//Thread.sleep(500);
			}

			for(int i=0; i<5; i++) {
				ChatThreadScreen chatThreadObj = homeScreenObj.startChatDirectly(msisdnToTest);
				chatThreadObj.scrollInsideThread();
				homeScreenObj = chatThreadObj.clickOnBackKey();
			}

			for(int i=0; i<5; i++) {
				goToDeviceHome();
				launchHike();
			}

			for(int i=0; i<5; i++) {
				killHike();
				launchHike();
				Thread.sleep(2000);
			}

			killHike();

			for(int i = 0; i < 5; i++) {
			    launchHike();
			    @SuppressWarnings("unused")
                NewChatContactSelectionScreen newChatSelectionObj = homeScreenObj.clickOnStartANewChatIcon();
			    goBackUsingDeviceBackKey();
			    goBackUsingDeviceBackKey();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
