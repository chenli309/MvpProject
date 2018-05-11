package com.lee.mvpstudy.progress;

public abstract class ObserverOnNextAdapter<T> implements ObserverOnNextListener<T> {


    @Override
    public void onCodeError(T t) {

    }

    @Override
    public void onFailure(Throwable e, boolean isNetWorkError) {

    }
}
