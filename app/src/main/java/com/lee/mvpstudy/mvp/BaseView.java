package com.lee.mvpstudy.mvp;

public interface BaseView {

//    public void setPresenter(T presenter);

    /**
     * 显示加载
     */
    public void showLoading();

    /**
     * 隐藏加载
     */
    public void hideLoading();

    /**
     * 显示信息
     */
    void showMsg(String message);

    /**
     * 显示信息
     */
    void showMsg(int stringId);

    /**
     * 页面加载中
     */
    void showPageLoading();

    /**
     * 空白页面
     */
    void showPageEmpty();

    /**
     * 页面加载失败
     */
    void showPageError();

    /**
     * 展示页面内容
     */
    void showPageContent();
}
