package com.lee.mvpstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lee.mvpstudy.R;

public class LeeMultipleStatusView extends MultipleStatusView {
    public LeeMultipleStatusView(Context context) {
        super(context);
    }

    public LeeMultipleStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeeMultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 显示加载中视图
     */
    public void showStatusLoadingView() {
        showLoading();
    }

    /**
     * 显示空视图
     */
    public void showStatusEmptyView() {
        showEmpty();
    }

    /**
     * 显示空视图
     */
    public void showStatusEmptyView(boolean isRetry) {
        showEmpty(isRetry);
    }

    /**
     * 显示空视图
     *
     * @param info 数据为空的原因
     */
    public void showStatusEmptyView(String info) {
        showStatusEmptyView();
        TextView tv_status_empty = findViewById(R.id.tv_status_empty);
        if (tv_status_empty != null) {
            tv_status_empty.setText(info);
        }
    }

    /**
     * 显示错误视图
     */
    public void showStatusErrorView() {
        showError();
    }

    /**
     * 显示错误视图
     *
     * @param info 展示的错误原因说明更改
     */
    public void showStatusErrorView(String info) {
        showStatusErrorView();
        TextView tv_status_error = findViewById(R.id.tv_status_error);
        if (tv_status_error != null) {
            tv_status_error.setText(info);
        }
    }

    /**
     * 显示无网络视图
     */
    public void showStatusNetworkView() {
        showNoNetwork();
    }

    /**
     * 显示内容视图
     */
    public void showStatusContentView() {
        showContent();
    }
}
