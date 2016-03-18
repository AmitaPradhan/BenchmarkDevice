/Users/kumarpratyush/Documents/Setup/adt-bundle-mac-x86_64-20130911/sdk/tools/android create uitest-project -n src -t 17 -p .
/Users/kumarpratyush/Documents/Setup/apache-ant-1.9.4/bin/ant clean
/Users/kumarpratyush/Documents/Setup/apache-ant-1.9.4/bin/ant build

/Users/kumarpratyush/Documents/Setup/adt-bundle-mac-x86_64-20130911/sdk/platform-tools/adb push ./bin/src.jar /data/local/tmp/

#adb shell uiautomator runtest src.jar -c com.bsb.hike.test.timeline.PostingPhotoFromTimeline
