package com.asuper.onlyplayer.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

/**
 * Created by Super on 2016/10/28.
 */

public class OnlyPlayerApp extends MultiDexApplication {
    private static final String TAG = "OnlyPlayer";

    private static Context sContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //65536 methods
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();

        sContext = this;

        //catch exception
        Thread.setDefaultUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onTerminate() {
        Log.i(TAG, "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Log.i(TAG, "onLowMemory");
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.i(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }
}
