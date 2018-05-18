package com.lee.mvpstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lee.mvpstudy.R;
import com.lee.mvpstudy.base.RxBaseActivity;
import com.lee.mvpstudy.webview.X5WebView;

public class H5Activity extends RxBaseActivity {

    private X5WebView mX5WebView;
    private String webUrl;


    @Override
    protected void getDataFromBundle(@Nullable Bundle savedInstanceState) {
        webUrl = savedInstanceState.getString("key");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_h5_layout;
    }

    @Override
    protected void initView() {
        mX5WebView = findViewById(R.id.x5_web_view);
    }

    @Override
    protected void initData() {
        mX5WebView.loadUrl(webUrl);
    }

//    private void full(boolean enable) {
//        if (enable) {
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            getWindow().setAttributes(lp);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        } else {
//            WindowManager.LayoutParams attr = getWindow().getAttributes();
//            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().setAttributes(attr);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
//    }

}
