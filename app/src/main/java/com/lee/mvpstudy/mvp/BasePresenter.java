package com.lee.mvpstudy.mvp;

import android.os.Bundle;

/**
 *
 */
public interface BasePresenter {

    /**
     * 初始化数据到内存
     */
    void getDataFromBundle(Bundle savedInstanceState);

    /**
     * activity因意外死亡，保存数据，比如:内存不足
     *
     * @param outState Bundle
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * 页面初始化完成后 在这里开始执行数据请求和业务处理逻辑
     */
    void onPresenterStart();

    /**
     * 释放资源
     */
    void onPresenterDestroy();


//    void attachView(V view);
//
//    void detachView();
}
