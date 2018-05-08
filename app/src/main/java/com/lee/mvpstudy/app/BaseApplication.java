package com.lee.mvpstudy.app;

import android.app.Application;
import android.content.Context;

/**
 * Func:    一句话描述.<br/>
 * Date:    2018/5/7 16:47<br/>
 * Author:  lee<br/>
 * Version: 1.0.0<br/>
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
