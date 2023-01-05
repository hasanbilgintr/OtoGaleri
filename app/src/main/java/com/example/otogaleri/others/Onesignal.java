package com.example.otogaleri.others;

import android.app.Application;
import android.util.Log;

import com.onesignal.OneSignal;


public class Onesignal extends Application {
    private static final String ONESIGNAL_APP_ID = "151dc9ae-bf29-43b6-892d-386b8102e1e0";
    public static String appuserid;

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        //String [] dizi = {"e9d6142a-ab91-41e5-9b0d-2370125f89f6","f2b0cb06-fce6-48b1-b87c-60b597e69780"};
        appuserid=OneSignal.getDeviceState().getUserId();
        //Log.i("huserid",  ""+OneSignal.getDeviceState().getUserId());
    }
}
