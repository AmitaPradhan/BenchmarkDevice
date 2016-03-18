package com.hike.appBenchmark.common;

import android.os.Build;

public class HikeConstant {
    HikeConstant(){
	//restricts instantiation
    }
    public final static String INSTRUMENTATION_DESCRIPTION = "INSTRUMENTATION_DESCRIPTION";
    public final static String PACKAGE_NAME = "com.bsb.hike";
    public final static String START_ACTIVITY_NAME = ".ui.HomeActivity";
    public final static String APP_PATH = "/Users/Documents/AndroidApk/android-client-unobfuscate.apk";

    // Gives OS level of device
    public final static int OS_VERSION = Build.VERSION.SDK_INT;

    public final static String DEFAULT_PIN = "1111";
    public final static int MAX_TIMEOUT = 120;
    public final static int MIN_TIMEOUT = 60;

    public final static String DEFAULT_HIKE_UNSAVED_SMS_CONTACT_NUMBER = "+914444492853";
    public final static String DEFAULT_HIKE_UNSAVED_NUMBER = "+914444442486";
    public final static String DEFAULT_ADMIN_NAME = "You";
    public final static String DEFAULT_GROUP_LIMIT = "500";
    public final static String DEFAULT_NAME = "Automation Tester";
    public final static String DEFAULT_GROUP_NAME = "HikeTestGroup";
    public final static String DEFAULT_GROUP_NAME1 = "HikeTestGroup1";
    public final static String DEFAULT_GROUP_NAME2 = "HikeTestGroup2";
    public final static String UPDATED_NAME = "HikeTestUser2";
    public final static String STATUS_UPDATE = "Happy";
    public final static String TEST_CHAT_MESSAGE = "Test Message";
    public final static String TEST_GROUP_CHAT_MESSAGE = "Group Chat Message";
    public final static String RANDOM_HIKE_SEARCH_USER = "xyz";
    public final static String TEST_BROADCAST = "FirstTestBroadcast";
    public final static String TEST_GROUP = "FirstTestGroup";
    public final static String TEST_GROUP_1 = "FirstTestG";

    public final static String BACK_UP_NUMBER = "4444448741";

    public final static String HIKE_DND_NAME_1 = "HikeDNDUser";
    public final static String HIKE_DND_NUMBER_1 = "+919818461120";

    // Added new number for invite cases
    public final static String HIKE_INDIAN_NUMBER = "+918802344321";
    public final static String HIKE_INDIAN_NUMBER_NAME = "HikeIndianNumber";

    // Following contacts should be present in the device
    public final static String HIKE_CONTACT_NAME = "FirstTestUser";
    public final static String HIKE_NUMBER_1 = "+914444440001";

    public final static String HIKE_CONTACT_NAME_1 = "SecondTestUser";
    public final static String HIKE_NUMBER_2 = "+914444440002";

    public final static String HIKE_CONTACT_NAME_2 = "ThirdTestUser";
    public final static String HIKE_NUMBER_3 = "+914444440003";

    public final static String HIKE_CONTACT_NAME_3 = "FourthTestUser";
    public final static String HIKE_NUMBER_4 = "+914444440004";

    public final static String HIKE_CONTACT_NAME_4 = "FifthTestUser";
    public final static String HIKE_NUMBER_5 = "+914444440005";

    public final static String INTERNATIONAL_HIKE_USER = "INTERNATIONALHIKEUSER";
    public final static String INTERNATIONAL_HIKE_NUMBER = "+447903524281";

    public final static String HIKE_SMS_CONTACT_NAME_1 = "HikeSMSContact";
    public final static String HIKE_SMS_CONTACT_NUMBER_1 = "+911231231232";

    public final static String HIKE_SMS_CONTACT_NAME_2 = "SecondHikeSMSContact";
    public final static String HIKE_SMS_CONTACT_NUMBER_2 = "+911231234321";

    public final static String HIKE_SMS_CONTACT_NAME_3 = "ThirdHikeSMSContact";
    public final static String HIKE_SMS_CONTACT_NUMBER_3 = "+911231265473";

    public final static String HIKE_SMS_CONTACT_NAME_4 = "FourthHikeSMSContact";
    public final static String HIKE_SMS_CONTACT_NUMBER_4 = "+911231233487";

    public static String DEFAULT_MSISDN;
}
