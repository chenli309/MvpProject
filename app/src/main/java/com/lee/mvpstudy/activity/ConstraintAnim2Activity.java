package com.lee.mvpstudy.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.view.View;

import com.lee.mvpstudy.R;
import com.lee.mvpstudy.base.RxBaseActivity;


public class ConstraintAnim2Activity extends RxBaseActivity {

    private ConstraintLayout mConstraintLayout;

    private ConstraintSet start = new ConstraintSet();
    private ConstraintSet end = new ConstraintSet();

    private boolean isSecond = false;

    @Override
    protected void getDataFromBundle(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_constraint_anim2_layout;
    }

    @Override
    protected void initView() {
        mConstraintLayout = findViewById(R.id.main);
    }

    @Override
    protected void initData() {
        start.clone(mConstraintLayout);
        end.clone(this, R.layout.activity_constraint_anim22_layout);
    }

    public void onApplyClick(View view) {
        if (!isSecond) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                TransitionManager.beginDelayedTransition(mConstraintLayout);
            end.applyTo(mConstraintLayout);
            isSecond = true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                TransitionManager.beginDelayedTransition(mConstraintLayout);
            start.applyTo(mConstraintLayout);
            isSecond = false;
        }
    }

}
