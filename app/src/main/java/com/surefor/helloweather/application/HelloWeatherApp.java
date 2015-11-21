package com.surefor.helloweather.application;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by ethan on 15/11/2015.
 */
public class HelloWeatherApp extends Application {
    private static Resources resources = null ;

    public HelloWeatherApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources() ;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static Resources getAppResources() {
        return resources ;
    }
}
