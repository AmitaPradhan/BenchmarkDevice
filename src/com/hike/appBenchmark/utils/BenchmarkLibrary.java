/**
 *
 */
package com.hike.appBenchmark.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.hike.appBenchmark.base.ExecuteShell;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.common.HikeConstant;
import com.hike.appBenchmark.common.Locators;

/**
 * @author kumarpratyush
 *
 */
public class BenchmarkLibrary extends UiAutomatorLibrary {

    /**
     * Test performs pre-setup. Includes :
     * 1. Push contacts. 2. Uninstall app 3. Push backup files 4. Push stickers pack
     * @throws InterruptedException
     */
    public void prepareDeviceToRunBenchmarkTest() throws InterruptedException {

        deleteExistingContacts();
        Thread.sleep(5000);
        goToDeviceHome();
        importContacts();
        Thread.sleep(36000);
        uninstallApp(HikeConstant.PACKAGE_NAME);

        installApp();
        //TODO push stickers
        pushStickers();
    }


/*    public String readProperty(String key) throws IOException {

        String value = null;
        Properties prop = new Properties();

        String propFileName = "/data/local/tmp/local.properties";
        FileReader reader;
        try {
            reader = new FileReader(propFileName);
            prop.load(reader);
        } catch (Exception e) {
            System.out.println("Unable to read property");
            e.printStackTrace();
        }

        value = prop.getProperty(key);
        return value;
    }
*/
    public void pushStickers(String source, String destination, String fileName) {
        System.out.println("Pushing file inside device");

        try {
            //use adb push
            ExecuteShell executeShellCommand = new ExecuteShell();

            File stickersFolder = new File(source+fileName);
            File[] allStickerPacks = stickersFolder.listFiles();
            for(int i=0; i < allStickerPacks.length; i++) {
                if(allStickerPacks[i].isDirectory()) {
                    String stickerPackName = allStickerPacks[i].getName();
                    executeShellCommand.ExecuteShellCommand("/Users/kumarpratyush/Documents/Setup/adt-bundle-mac-x86_64-20130911/sdk/platform-tools/adb push", source + stickerPackName + "/", destination + stickerPackName + "/");
                }
            }

            //executeShellCommand.ExecuteShellCommand("/Users/kumarpratyush/Documents/Setup/adt-bundle-mac-x86_64-20130911/sdk/platform-tools/adb push", source+fileName, destination);

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

    public void installApp() {

        try {
            System.out.println("Launching Hike without waiting for popup");
            ExecuteShell executeShellCommand = new ExecuteShell();
            executeShellCommand.ExecuteShellCommand("pm","install","/data/local/tmp/android-client.apk");
        } catch(Exception e) {
            System.out.println("Unable to uninstall app");
            e.printStackTrace();
        }
    }

}
