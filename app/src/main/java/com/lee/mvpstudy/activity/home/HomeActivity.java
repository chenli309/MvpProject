package com.lee.mvpstudy.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lee.mvpstudy.BaseActivity;
import com.lee.mvpstudy.R;
import com.lee.mvpstudy.mvp.home.HomeContract;
import com.lee.mvpstudy.mvp.home.HomePresenter;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @Override
    protected void getDataFromBundle(@Nullable Bundle savedInstanceState) {
        mPresenter = new HomePresenter(this);
        mPresenter.getDataFromBundle(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
