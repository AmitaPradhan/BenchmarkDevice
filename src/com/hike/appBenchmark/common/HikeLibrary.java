package com.hike.appBenchmark.common;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.bsb.hike.base.HikeAutomationConfig;
import com.bsb.hike.qa.apisupport.ConsoleDataSupport;
import com.bsb.hike.qa.apisupport.FriendsActionSupport;
import com.bsb.hike.qa.apisupport.GroupChatMessageSupport;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;
import com.bsb.hike.qa.apisupport.MqttSupport;
import com.bsb.hike.qa.apisupport.SendStickerSupport;
import com.bsb.hike.qa.apisupport.StatusUpdateSupport;
import com.bsb.hike.qa.apisupport.UserModificationSupport;
import com.bsb.hike.qa.dbmanager.RedisServiceManagerUtil;
import com.hike.appBenchmark.base.ExecuteShell;
import com.hike.appBenchmark.base.UiAutomatorLibrary;
import com.hike.appBenchmark.objectlocator.popup.ConfirmYourNumberPopup;
import com.hike.appBenchmark.objectlocator.screen.AboutYouLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.ChatThreadScreen;
import com.hike.appBenchmark.objectlocator.screen.HomeScreen;
import com.hike.appBenchmark.objectlocator.screen.LanguageSelectionScreen;
import com.hike.appBenchmark.objectlocator.screen.PhoneNumberLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.PinScreen;
import com.hike.appBenchmark.objectlocator.screen.RegionalKeyboardPopScreen;
import com.hike.appBenchmark.objectlocator.screen.RestoreAccountBackupScreen;
import com.hike.appBenchmark.objectlocator.screen.SignUpEnviromentSelectScreen;
import com.hike.appBenchmark.objectlocator.screen.TellUsMoreLoginScreen;
import com.hike.appBenchmark.objectlocator.screen.WelcomeScreen;
import com.hike.appBenchmark.utility.AdbUtility;

import android.os.RemoteException;

/*****************************************************************************************************************************************************************
 *
 * This class contains all the common functions related to hike app example :
 * create account, delete account
 *
 ****************************************************************************************************************************************************************/

public class HikeLibrary extends UiAutomatorLibrary {
    public ExecuteShell executeShellCommand = new ExecuteShell();
    public FriendsActionSupport friendActionSupport = new FriendsActionSupport();
    public UserModificationSupport userSupport = new UserModificationSupport();
    public Hike2HikeMessageSupport hike2HikeMessage = new Hike2HikeMessageSupport();
    public GroupChatMessageSupport gcSupport = new GroupChatMessageSupport();
    public StatusUpdateSupport SuSupport = new StatusUpdateSupport();
    public SendStickerSupport SsSupport = new SendStickerSupport();
    public MqttSupport mqttSupport = new MqttSupport();
    public ConsoleDataSupport consoleDataSupport = new ConsoleDataSupport();
    public AdbUtility adbUitiliy=new AdbUtility();
    public HikeConstant hikeConstant=new HikeConstant();


    /*****************************************************************************************************************************************************************
     *
     * Functions for getting and setting from docker.properties
     *
     ****************************************************************************************************************************************************************/

    public String getSdk() throws IOException {
        Properties prop = new Properties();
        String propFileName = "/data/local/tmp/docker.properties";
        FileReader reader = new FileReader(propFileName);
        prop.load(reader);
        String msisdn = prop.getProperty("sdk.dir");
        return msisdn;
    }

    /**
     *
     * @param msisdn
     * @throws UiObjectNotFoundException
     * @throws InterruptedException
     * @throws IOException
     */
    public void setMSISDNInFile(String msisdn)
            throws UiObjectNotFoundException, InterruptedException, IOException {
        String prop = "sdk.dir=" + getSdk() + "\nmsisdn=" + msisdn;
        FileWriter output = null;
        try {
            output = new FileWriter("/data/local/tmp/docker.properties");
            output.write(prop);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail("Setting MSISDN failed " + returnScreenshot());

                }

            }
        }
    }
    public String getMSISDNFromFile() {
        Properties prop = new Properties();
        String propFileName = "/data/local/tmp/docker.properties";
        FileReader reader;
        try {
            reader = new FileReader(propFileName);
            prop.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Getting MSISDN failed ");
        }
        String msisdn = prop.getProperty("msisdn");
        HikeConstant.DEFAULT_MSISDN = msisdn;
        return msisdn;
    }

    public String getMSISDNWithoutCountryCode() {
        getMSISDNFromFile();
        return HikeConstant.DEFAULT_MSISDN;
    }

    public String getMSISDNWithCountryCode() {
        getMSISDNFromFile();
        return "+91" + HikeConstant.DEFAULT_MSISDN;
    }


    /**
     * @author pooja This function launches hike from recent app panel
     */
    public void launchHikeFromRecents() {
        try {
            device.pressRecentApps();
            clickOnElement(Locators.CONTENT_DESCRIPTION, "hike");
        } catch (RemoteException e) {
            e.printStackTrace();
            Assert.fail("Could not launch hike from recents"
                    + returnScreenshot());
        }
    }

    /**
     * @author pooja This function launches hike from hike logo in menu
     */
    public void launchHikeFromMenu() {
        try {
            UiObject apps_icon = getElement(Locators.CONTENT_DESCRIPTION,
                    "Apps");
            apps_icon.click();
            UiScrollable appView = new UiScrollable(new UiSelector().className(
                    AndroidClassNames.VIEW).scrollable(true));
            appView.setAsHorizontalList();
            UiObject hike_icon = appView.getChildByText(new UiSelector()
            .className(android.widget.TextView.class.getName()),
            "hike", true);
            hike_icon.clickAndWaitForNewWindow();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not launch hike" + returnScreenshot());
        }
    }



    /*****************************************************************************************************************************************************************
     *
     * Function for asserting with screenshot
     *
     ****************************************************************************************************************************************************************/

    /**
     * @param error_message_to_be_displayed
     * @param condition_to_be_checked
     *            Function for asserting with screenshot
     */
    public void assertTrueWithScreenshot(String error_message_to_be_displayed,
            Boolean condition_to_be_checked) {
        Assert.assertTrue(error_message_to_be_displayed + " "
                + returnScreenshot(), condition_to_be_checked);
    }

    /*****************************************************************************************************************************************************************
     *
     * Functions for controlling device
     *
     ****************************************************************************************************************************************************************/

    /**
     * Function to Press device back key
     */
    public void goBackUsingDeviceBackKey() {
        System.out.println("Clicking on device back button");
        device.pressBack();
    }

    public boolean checkDeviceKeyboard() {
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

    /**
     * Function to set pin
     */
    public void setPin() {
        System.out.println("Setting Pin");
        RedisServiceManagerUtil.getInstance().setKey(
                "pincodes-" + getMSISDNWithCountryCode(), HikeConstant.DEFAULT_PIN);

    }

    /**
     * Function to get pin
     */
    public String getPin() {
        System.out.println("Getting Pin");
        String pin = RedisServiceManagerUtil.getInstance().get(
                "pincodes-" + getMSISDNWithCountryCode());
        System.out.println("Pin is :" + pin);
        return pin;

    }

    /****************************************************************************************************************************************************************
     * *
     *
     * @return void
     * @exception UiObjectNotFoundException
     *
     ***************************************************************************************************************************************************************/
    public HomeScreen goToHome() {
        int counter = 0;
        System.out.println("GOING BACK TO HOME SCREEN");
        HomeScreen homeScreen=new HomeScreen(false);
        UiObject startChat = homeScreen.getStartANewChatIcon();

        while (!startChat.exists()) {

            goBackUsingDeviceBackKey();
            startChat = homeScreen.getStartANewChatIcon();
            counter++;
            if(counter==8)
            {
                exitHike();
                launchHikeWithoutWaitingForPopup();
                Assert.fail("User not able to login properly"+returnScreenshot());
                break;
            }
        }
        return homeScreen;
    }


    public void deleteScreenshotsFromPictures() throws UiObjectNotFoundException, InterruptedException, IOException{
        File dir = new File("storage/sdcard0/Pictures");
        FileUtils.cleanDirectory(dir);


    }

    public ChatThreadScreen startChat(String name) {

        try{

            goToHome();
            System.out.println("Starting Chat with "+name);
            HomeScreen homeScreen=new HomeScreen();
            UiObject startChat = homeScreen.getStartANewChatIcon();
            clickOnElement(startChat);
            clickElementInList(Locators.NAME , name);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail("Starting a chat with "+name+" failed "+returnScreenshot());
        }
        return new ChatThreadScreen();

    }

    public void sendSticker() {
        try{
            System.out.println("sending sticker");

            UiObject sticker =getElement(Locators.CLASSNAME,AndroidClassNames.IMAGE_BUTTON, 0);
            sticker.click();
            UiObject view = getElement(Locators.CLASSNAME,AndroidClassNames.RELATIVE_LAYOUT,0 );
            UiObject obj= getElement(Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
            int x= view.getBounds().width()/8;
            int y= view.getBounds().bottom+(obj.getBounds().bottom-view.getBounds().bottom)/6;
            device.click(x,y);
            Thread.sleep(2000);
            device.pressBack();
        }
        catch(Exception e)
        {
            Assert.fail("Failed while sending sticker "+returnScreenshot());
        }
    }




    /***************************************************************************************************************************************************************
     *
     * @return void
     * @author
     * @throws UiObjectNotFoundException
     * @throws InterruptedException
     *
     **************************************************************************************************************************************************************/

    /***************************************************************************************************************************************************************
     * opens chat with a particular name
     *
     * @param name
     **************************************************************************************************************************************************************/
    public void openChat(String name){
        try{
            System.out.println("Opening chat with "+name);
            goToHome();
            clickElementInList(Locators.NAME , name);
        }
        catch (Exception e){
            e.printStackTrace();
            Assert.fail("Failed while opening chat "+name+" "+returnScreenshot());
        }
    }

    public int getSMSCredit(String msisdn) {
        int smsCredits;
        try{
            System.out.println("Getting SMS Credits");
            Thread.sleep(10000);
            smsCredits = friendActionSupport.getRemainingSMSCredits(msisdn);
            System.out.println(smsCredits);
            return smsCredits;
        }
        catch(Exception e)
        {
            Assert.fail("Failed while getting SMS credit "+msisdn);
            return -1;
        }
    }

    public void setSMSCredit(int smsCountToSet) {
        System.out.println("Setting SMS Credit to "+smsCountToSet);
        String smsCount=""+smsCountToSet;
        friendActionSupport.setSmsCount(getDEFAULT_MSISDN(), smsCount);
    }


    public void createFriendship(String msisdn1 , String msisdn2) {
        System.out.println("Creating friendship of "+ msisdn1+" and "+msisdn2);
        friendActionSupport.create2WayFriendship(msisdn1, msisdn2);
    }

    public void sendFriendRequest(String msisdn1 , String msisdn2) {
        System.out.println("Sending friendship from "+ msisdn1+" to "+msisdn2);
        friendActionSupport.sendFriendRequest(msisdn1, msisdn2);
    }

    public void removeFriendship(String msisdn1 , String msisdn2) {
        System.out.println("Removing friendship of "+ msisdn1+" and "+msisdn2);
        friendActionSupport.removeFriendShip(msisdn1, msisdn2);
    }

    public static String getDEFAULT_MSISDN() {
        setDEFAULT_MSISDN();
        return "+91"+HikeConstant.DEFAULT_MSISDN;
    }
    public static void setDEFAULT_MSISDN(){
        try {
            HikeConstant.DEFAULT_MSISDN=getMsisdn();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static  String getMsisdn()  {

        Properties prop = new Properties();

        String propFileName = "/data/local/tmp/docker.properties";
        FileReader reader;
        try {
            reader = new FileReader(propFileName);
            prop.load(reader);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail("Getting MSISDN failed ");
        }

        String msisdn = prop.getProperty("msisdn");
        return msisdn;
    }



    /***************************************************************************************************************************************************************
     *
     * Completely deletes user from Hike
     *
     * @param msisdn
     *
     **************************************************************************************************************************************************************/
    public void deletingUserFromHike(String msisdn){
        System.out.println("Deleting user from hike through server");
        userSupport.deleteUser(msisdn);
        friendActionSupport.clearCacheAfterDelete(msisdn);
    }


    public void recieveHikeMessageFrom(String sender , String message){
        System.out.println("Receiving hike message");
        hike2HikeMessage.sendHikeMessage(sender, getDEFAULT_MSISDN(),message );
    }

    public void recieveHikeStickerFrom(String sender){
        System.out.println("Receiving hike Sticker");
        SsSupport.sendSticker(sender, getDEFAULT_MSISDN(),"bollywood","001_salu.png");
    }

    public String createGroupAndSendMessage(String owner , List<String> contact){
        System.out.println("Creating group and sending messange through server with group owner"+owner);
        return gcSupport.createGroupAndSendMessage(owner, contact, HikeConstant.DEFAULT_GROUP_NAME);
    }
    public String createGroupAndSendMessage(String owner , List<String> contact,String groupName){
        System.out.println("Creating group and sending messange through server with group owner"+owner);
        return gcSupport.createGroupAndSendMessage(owner,contact,groupName);
    }

    public void addMemberToGroupChat(String groupID, String personAdding , String personToAdd){
        System.out.println("Adding member to group through server: "+personAdding+" adding "+personToAdd);
        try {
            gcSupport.addMemberToGroup(groupID, personAdding, personToAdd);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void removeMemberFromGroup(String groupID, String removingMember , String memberToRemove){
        System.out.println("Removing member from group through server: "+removingMember+" removing "+memberToRemove);
        gcSupport.removeMemberFromGroup(groupID, removingMember, memberToRemove);
    }

    public void changeGroupNameByMember(String groupID, String member , String changesGroupName){
        System.out.println("Changing Group name by member (server)");
        gcSupport.changeGroupNameByMember(groupID, member, changesGroupName);
    }

    public void sendSticker(String member,String gid){
        System.out.println("Sending Stiker from (server)");
        gcSupport.sendHikeSticker(member,gid);
    }

    public void sendNudge(String member,String gid){
        System.out.println("Sending nudge from (server)");
        gcSupport.sendHikeNudge(member,gid);
    }

    public void sendMessage(String member,String gid,String message){
        System.out.println("CSending message from (server)");
        gcSupport.sendHikeMessage(member,gid,message);
    }

    public boolean createHikeUserWithMsisdn(String msisdn){
        System.out.println("Creating hike user having msisdn "+msisdn);
        return userSupport.createHikeUserWithMsisdn(msisdn);
    }

    public void sendHikeMessage(String sender, String reciever, String message){
        System.out.println("Sending hike message from "+sender+" to "+reciever);
        hike2HikeMessage.sendHikeMessage(sender, reciever, message);
    }

    public void sendPinByOtherUser(String groupID , String msisdn , String pinText){
        gcSupport.setPin(groupID, msisdn , pinText);
    }


    public void setStatus( String msisdn , String status){
        System.out.println("Setting status of "+msisdn);
        SuSupport.setStatusUpdate(msisdn,status,0);
    }

    /*****************************************************************************************************************************************************************
     *
     * @return MSISDN of the current User
     * @author
     *
     ****************************************************************************************************************************************************************/
    public static String getUser(){
        return System.getProperty("user.dir");

    }
    public static String getDEFAULTMSISDNCreate(){
        setDEFAULT_MSISDN();
        return HikeConstant.DEFAULT_MSISDN;


    }

    public void turnDeviceWifiOn()
    {
        executeShellCommand.executeCommand("am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
        try{
            clickOnElement(Locators.CLASSNAME,AndroidClassNames.SWITCH);
        }
        catch(Exception e)
        {
            Assert.fail("Failed while turning wifi On "+returnScreenshot());
        }
        device.pressBack();

    }


    public void turnDeviceWifiOff()
    {
        executeShellCommand.executeCommand("am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
        try{
            waitForElement(Locators.CLASSNAME,AndroidClassNames.SWITCH,10);
            clickOnElement(Locators.CLASSNAME,AndroidClassNames.SWITCH);
        }
        catch(Exception e)
        {
            Assert.fail("Failed while turning wifi Off "+returnScreenshot());
        }
        device.pressBack();

    }

    public Boolean checkShortcut(UiObject name)
    {
        try{
            if(isElementPresentOnScreen(name))
            {
                return true;
            }
            else
            {
                UiObject view= getElement(Locators.CLASSNAME, AndroidClassNames.VIEW);
                view.swipeRight(5);
                if(isElementPresentOnScreen(name))
                    return true;
                else
                {
                    device.pressHome();
                    view.swipeLeft(5);
                    if(isElementPresentOnScreen(name))
                        return true;
                    else
                        return false;
                }

            }
        }
        catch (Exception e)
        {
            System.out.println("Not able to click on shortcut due to exception"+returnScreenshot());
            return false;
        }
    }

    public void deleteShortcutFromHomeScreen(String Name)
    {
        try{
            UiObject frame_layout= getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT);
            UiObject uiobject= getElement(Locators.NAME, Name);
            int x=uiobject.getBounds().centerX();
            int y=uiobject.getBounds().centerY();
            //the remove text come at half of the width of the screen at x axis and 1/10.67 times the height of the screen on motorala and nexus devices
            device.drag(x, y,frame_layout.getBounds().width()/2,(int)(frame_layout.getBounds().height()/10.67), 100);
        }
        catch(Exception e)
        {
            Assert.fail("Failed while deleting the shortcut for "+Name + returnScreenshot());
        }
    }


    public void closeKeyboardIfOpen()
    {
        if(checkDeviceKeyboard())
            closekeyboard();
    }
    public String getPinFromConsole(String msisdn) {
        String pin= consoleDataSupport.getPinFromConsole(msisdn);
        if(pin==null)
        {
            pin= consoleDataSupport.getPinFromConsole(msisdn);
        }
        return pin;
    }

    public void createHikeUser()
    {

        exitHike();
        launchHikeWithoutWaitingForPopup();
        WelcomeScreen welcomeScreen=new WelcomeScreen(false);
        try
        {
            setPotrait();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
            System.out.println("******* Failed while changing initial rotation to Portrait Mode ******");
        }
        if(isElementPresentOnScreen(welcomeScreen.getGetStartedBtn()))
        {
            try
            {
                System.out.println("Signing up");
                setDEFAULT_MSISDN();
                setRunningEnviroment();
                // safety check to handle reSignup test cases failures
                closeKeyboardIfOpen();
                PhoneNumberLoginScreen phoneLoginScreen = welcomeScreen.clickOnGetStartedBtn();
                phoneLoginScreen.enterTextPhoneNumber(getDEFAULTMSISDNCreate());
                closeKeyboardIfOpen();
                ConfirmYourNumberPopup confirmNumberPopup = phoneLoginScreen.clickOnNextBtn();
                PinScreen pinScreen = confirmNumberPopup.clickOnConfirmBtn();
                waitForElement(pinScreen.getPinLbl(),100);
                String pin=getPinFromConsole(getDEFAULT_MSISDN());
                pinScreen.enterTextPin(pin);
                closeKeyboardIfOpen();
                AboutYouLoginScreen aboutYouLoginScreen=pinScreen.clickOnNextBtn();
                waitForElement(aboutYouLoginScreen.getAboutYouTitleLbl(),100);
                aboutYouLoginScreen.enterTextName(HikeConstant.DEFAULT_NAME);
                closeKeyboardIfOpen();
                TellUsMoreLoginScreen tellUsMoreLoginScreen=aboutYouLoginScreen.clickonNextBtn();
                tellUsMoreLoginScreen.clickOnIAmABoyLbl();
                tellUsMoreLoginScreen.clickOnNextBtn();
                RestoreAccountBackupScreen restoreAccountBackupScreen=new RestoreAccountBackupScreen(false);
                waitForElementWithoutAssert(restoreAccountBackupScreen.getRestoreAccountLbl(),6);
                //Adding sleep as skip button not get enabled will restore button appeared on backupscreen
                sleep(5000);
                while(isElementPresentOnScreen(restoreAccountBackupScreen.getRestoreAccountLbl()))
                {
                    clickOnElementContinueOnFail(restoreAccountBackupScreen.getSkipLbl());
                }
                exitHike();
                launchHikeWithoutWaitingForPopup();
                if(isElementPresentOnScreen(restoreAccountBackupScreen.getSkipLbl()))
                    restoreAccountBackupScreen.clickOnSkipBtn();
                clickOnElementContinueOnFail(new LanguageSelectionScreen(false).getDoneBtn());
                setSMSCredit(10000);
                exitHike();
                launchHikeWithoutWaitingForPopup();
            }
            catch(Exception e)
            {    e.printStackTrace();
            Assert.fail("Failed while creating user due to an exception "+returnScreenshot());
            }
            Assert.assertTrue("Creation Of Account Failed"+ returnScreenshot(), isElementPresentOnScreen(new HomeScreen().getStartANewChatIcon()));
            try {

                closeRegionalPopupfromChatThread();
            } catch (Exception e) {
                System.out.println("regional popup not closed");
            }
        }
        else
        {
            System.out.println("Account already created"+returnScreenshot());
        }
        try {
            setPotrait();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void closeRegionalPopupfromChatThread() throws UiObjectNotFoundException, InterruptedException, IOException{

        goToHome();
        startChat(HikeConstant.HIKE_CONTACT_NAME);
        clickOnElement(new ChatThreadScreen().getMessageTxt());
        sleep(1000);

        if(isElementPresentOnScreen(new RegionalKeyboardPopScreen(false).getRegionalKeyboardTxt()))
        {
            RegionalKeyboardPopScreen regionalKeyboardPopScreen=new RegionalKeyboardPopScreen(false);
            regionalKeyboardPopScreen.clickOnCrossBtn();
            goToHome();
        }

    }

        public void launchHikeWithoutWaitingForPopup() {
        System.out.println("Launching Hike without waiting for popup");
        try {
            adbUitiliy.launchApp(HikeConstant.PACKAGE_NAME,HikeConstant.START_ACTIVITY_NAME);
        } catch (InterruptedException e) {
            System.out.println("failed while launching hike app");
            e.printStackTrace();
            Assert.fail("Failied while launching hike app"+returnScreenshot());
        }

    }

    public void launchHike() {
        System.out.println("Launching Hike");
        launchHikeWithoutWaitingForPopup();
        // Add Checks here for new popups
    }
    public void exitHike() {
        System.out.println("Exiting hike");
        adbUitiliy.forceStopApp(HikeConstant.PACKAGE_NAME);
    }

    public void SetStatus( String msisdn , String status){
        System.out.println("Setting status of "+msisdn);
        SuSupport.setStatusUpdate(msisdn,status,0);
    }

    public void RemoveFriendship(String msisdn1 , String msisdn2) {
        System.out.println("Removing friendship of "+ msisdn1+" and "+msisdn2);
        friendActionSupport.removeFriendShip(msisdn1, msisdn2);
    }

    /**
     * @author pooja This function swipes hike from recent app panel
     */
    public void forceSwipe() {
        try {
            device.pressRecentApps();
            UiObject hike = getElement(Locators.NAME, "hike");
            hike.swipeLeft(10);
            device.waitForIdle();
            device.pressHome();
            device.pressRecentApps();
            Assert.assertTrue("Could not force swipe"+returnScreenshot(), !hike.exists());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not force swipe" + returnScreenshot());
        }
    }

    public void setRunningEnviroment() throws UiObjectNotFoundException
    {
        WelcomeScreen welcomeScreen=new WelcomeScreen();
        SignUpEnviromentSelectScreen signUpEnviromentSelectScreen=welcomeScreen.tapAndHoldHikeMessengerLogoIcon();
        // Signing up using ip
        HikeAutomationConfig hikeAutomationConfig=new HikeAutomationConfig();
        //Setting mqtt host and port
        signUpEnviromentSelectScreen.enterTextMqttHostTextField(hikeAutomationConfig.getMqtthost());
        signUpEnviromentSelectScreen.enterTextMqttPortTextField(hikeAutomationConfig.getMqttport());
        //Setting Http host and port
        signUpEnviromentSelectScreen.enterTextHttpHostTextField(hikeAutomationConfig.getHttphost());
        signUpEnviromentSelectScreen.enterTextHttpPortTextField(hikeAutomationConfig.getHttpport());
        try
        {
            signUpEnviromentSelectScreen.clickOnUpdateBtn();
        }

        catch(Exception e)
        {
            if(isElementPresentOnScreen(signUpEnviromentSelectScreen.getUpdateBtn()))
                Assert.fail("Host and port fields not entered correctly, failed while clicking on update button"+returnScreenshot());
        }
    }

    public void loginWithRestore(String msisdn) {

        try {
            launchHikeWithoutWaitingForPopup();
            System.out.println(":Creating a new account");
            //          setPin();
            WelcomeScreen welcomeScreenObj = new WelcomeScreen();
            welcomeScreenObj.clickOnHikeMessengerLogoIcon();
            PhoneNumberLoginScreen phoneLoginScreenObj = welcomeScreenObj.clickOnGetStartedBtn();
            phoneLoginScreenObj.enterTextPhoneNumber(msisdn);
            ConfirmYourNumberPopup confirmNumberPopup = phoneLoginScreenObj.clickOnNextBtn();
            PinScreen pinScreenObj = confirmNumberPopup.clickOnConfirmBtn();

            String pin=getPinFromConsole(msisdn);
            pinScreenObj.enterTextPin(pin);
            AboutYouLoginScreen aboutYouScreenObj = pinScreenObj.clickOnNextBtn();
            aboutYouScreenObj.enterTextName("benchmark");
            TellUsMoreLoginScreen tellMoreScreenObj = aboutYouScreenObj.clickonNextBtn();
            tellMoreScreenObj.clickOnIAmABoyLbl();
            RestoreAccountBackupScreen restoreBackupScreenObj = tellMoreScreenObj.clickOnNextBtn();

            boolean restoreButtonPresent = false;
            int counter = 0;
            while(!restoreButtonPresent && counter < 10) {
                restoreButtonPresent = isElementPresentOnScreen(restoreBackupScreenObj.getBackupFoundLbl());
                counter++;
                Thread.sleep(1000);
            }
            Thread.sleep(5000);

            UiObject imgView= getElement(Locators.CLASSNAME,AndroidClassNames.IMAGE_VIEW,3);
            UiObject l_layout= getElement(Locators.CLASSNAME,AndroidClassNames.LINEAR_LAYOUT,0);
            System.out.println(imgView.getBounds().centerX());
            System.out.println(imgView.getBounds().bottom+(l_layout.getBounds().bottom- imgView.getBounds().bottom)/3);
            device.click(imgView.getBounds().centerX(), imgView.getBounds().bottom+(l_layout.getBounds().bottom- imgView.getBounds().bottom)/3);

            boolean homeScreenLoaded = false;
            counter = 0;
            UiObject languageScreen = getElement(Locators.NAME,"Language");
            if(languageScreen != null) {
                Thread.sleep(15000);
                clickOnElementContinueOnFail(Locators.NAME, "Done");
                clickOnElementContinueOnFail(Locators.NAME, "Done");
            }
            HomeScreen homeScreenObj = new HomeScreen();

            while(!homeScreenLoaded && counter < 10) {
                homeScreenLoaded = isElementPresentOnScreen(homeScreenObj.getStartANewChatIcon());
                counter++;
                Thread.sleep(1000);
            }


        }catch(Exception e){
            e.printStackTrace();

            Assert.fail("Login with restore failed "+returnScreenshot());

        }
    }

}
