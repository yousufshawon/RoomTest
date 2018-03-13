package com.yousuf.shawon.roomtest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by yousuf on 3/12/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(getApplicationContext());
        }

    }
}
