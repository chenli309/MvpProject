package com.lee.mvpstudy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lee.mvpstudy.mvp.BasePresenter;
import com.lee.mvpstudy.mvp.BaseView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected boolean isActive;

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = savedInstanceState == null ? getIntent().getExtras() : savedInstanceState;
        getDataFromBundle(bundle);

        int layoutId = getContentViewId();
        if (layoutId > 0) {
            setContentView(layoutId);
        }

        initView();
        initData();

        if (mPresenter != null) {
            mPresenter.onPresenterStart();
        }

        // 如果要使用 EventBus 请将此方法返回 true
        if (useEventBus()) {
            // 注册到事件主线
            EventBus.getDefault().register(this);
        }
    }

    protected abstract void getDataFromBundle(@Nullable Bundle savedInstanceState);

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

//    /**
//     * 是否使用EventBus
//     *
//     * @return true使用  false未使用
//     */
//    protected abstract boolean useEventBus();

    /**
     * 是否使用EventBus
     *
     * @return true使用  false未使用
     */
    boolean useEventBus() {
        return false;
    }

    /**
     * 得到当前activity的实例
     *
     * @return 当前activity的实例
     */
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        if (mPresenter != null) {
            mPresenter.onPresenterDestroy();
        }
        mPresenter = null;

        super.onDestroy();
    }

    // ************************************ BaseView 实现方法 start *****************************************
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showMsg(int stringId) {

    }

    @Override
    public void showPageLoading() {

    }

    @Override
    public void showPageEmpty() {

    }

    @Override
    public void showPageError() {

    }

    @Override
    public void showPageContent() {

    }
    // ************************************ BaseView 实现方法 end *****************************************
}
