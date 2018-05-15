package com.lee.mvpstudy.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.lee.mvpstudy.R;

public class LeeSwipeRefreshLayout extends SwipeRefreshLayout {
    public LeeSwipeRefreshLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public LeeSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 设置下拉进度的背景颜色，默认就是白色的
        setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        setColorSchemeResources(R.color.colorPrimary);
    }
}
