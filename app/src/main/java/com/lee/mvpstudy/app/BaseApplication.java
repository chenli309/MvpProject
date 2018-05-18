package com.lee.mvpstudy.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.Utils;
import com.lee.mvpstudy.webview.X5NetService;

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

        Utils.init(this);
        preInitX5Core();
    }

    public static Context getContext() {
        return mContext;
    }

    private void preInitX5Core() {
        //预加载x5内核
        Intent intent = new Intent(this, X5NetService.class);
        startService(intent);
    }
}
