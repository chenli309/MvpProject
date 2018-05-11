package com.lee.mvpstudy.progress;

/**
 * Created by DeMon on 2017/9/6.
 */

public interface ObserverOnNextListener<T> {

    /**
     * 请求成功了,code也正确
     */
    void onSuccess(T t);

    /**
     * 请求成功了,但是code错误
     */
    void onCodeError(T t);

    /**
     * 请求失败了
     * isNetWorkError 是不是网络异常
     */
    void onFailure(Throwable e, boolean isNetWorkError);
}
