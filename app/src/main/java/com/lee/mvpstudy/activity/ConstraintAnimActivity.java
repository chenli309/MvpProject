package com.lee.mvpstudy.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.view.View;

import com.lee.mvpstudy.R;
import com.lee.mvpstudy.base.RxBaseActivity;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;

public class ConstraintAnimActivity extends RxBaseActivity {

    private ConstraintLayout mConstraintLayout;
    //创建两个ConstraintSet对象(这个对象能记录我们Constraint控件的所有内容)
    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();

    @Override
    protected void getDataFromBundle(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_constraint_anim_layout;
    }

    @Override
    protected void initView() {
        mConstraintLayout = findViewById(R.id.constraint);
    }

    @Override
    protected void initData() {
        //克隆当前的状态
        applyConstraintSet.clone(mConstraintLayout);
        resetConstraintSet.clone(mConstraintLayout);
    }

    public void applyClick(View view) {

        //这个是设置相应的渐变动画的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(mConstraintLayout);
        }

        //设置居中,还有水平的居中方式哦
        applyConstraintSet.setMargin(R.id.button1, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.button1, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.button2, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.button2, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.button3, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.button3, ConstraintSet.END, 0);

        /*
         * 设置相应居中位置的
         * 参数1:相应控件
         * 参数2:父控件
         **/
        applyConstraintSet.centerHorizontally(R.id.button1, R.id.constraint);
        applyConstraintSet.centerHorizontally(R.id.button2, R.id.constraint);
        applyConstraintSet.centerHorizontally(R.id.button3, R.id.constraint);
        applyConstraintSet.applyTo(mConstraintLayout);
    }

    public void resetClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(mConstraintLayout);
        }
        resetConstraintSet.applyTo(mConstraintLayout);
    }

}
