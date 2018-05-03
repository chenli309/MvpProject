package com.lee.mvpstudy.mvp.home;

import android.os.Bundle;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void getDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            return;
        }
    }

    @Override
    public void onPresenterStart() {

    }

    @Override
    public void onPresenterDestroy() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void requestSms(String phone, String password) {

    }

    @Override
    public void register(String phone, String password, String code) {

    }
}
