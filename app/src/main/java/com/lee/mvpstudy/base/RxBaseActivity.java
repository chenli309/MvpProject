package com.lee.mvpstudy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lee.mvpstudy.R;
import com.lee.mvpstudy.bean.RequestPageBean;
import com.lee.mvpstudy.mvp.BasePresenter;
import com.lee.mvpstudy.mvp.BaseView;
import com.lee.mvpstudy.view.LeeMultipleStatusView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public abstract class RxBaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    protected boolean isActive;

    protected P mPresenter;

    private LeeMultipleStatusView mLayoutStatusView;

    protected RequestPageBean mPageBean = new RequestPageBean();

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
        initListener();

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

    protected void initListener() {
        mLayoutStatusView = findViewById(R.id.multipleStatusView);
        if (mLayoutStatusView != null) {
            mLayoutStatusView.setOnClickListener(mRetryClickListener);
        }
    }

    private View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onRetryClick();
        }
    };

    protected void onRetryClick() {

    }

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
    public void loadMoreFail() {

    }

    @Override
    public void showMsg(String message) {
        ToastUtils.showLong(message);
    }

    @Override
    public void showMsg(int stringId) {
        ToastUtils.showLong(stringId);
    }

    @Override
    public void showPageLoading() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.showStatusLoadingView();
        }
    }

    @Override
    public void showPageEmpty() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.showStatusEmptyView();
        }
    }

    @Override
    public void showPageError() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.showStatusErrorView();
        }
    }

    @Override
    public void showPageContent() {
        if (mLayoutStatusView != null) {
            mLayoutStatusView.showStatusContentView();
        }
    }
    // ************************************ BaseView 实现方法 end *****************************************


    /**
     * 把OkHttp请求生命周期与RxFragment的生命周期绑定起来 解决Http请求泄露
     *
     * @return LifecycleTransformer
     */
    public LifecycleTransformer bindToLife() {
        return this.bindUntilEvent(ActivityEvent.DESTROY); // this.bindToLifecycle()
    }
}
