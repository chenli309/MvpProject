package com.lee.mvpstudy.mvp;

public interface BaseView {

//    public void setPresenter(T presenter);

    /**
     * 开始发起网络请求
     */
    void requestStart();

    /**
     * 显示加载 (ProgressDialog)
     */
    void showLoading();

    /**
     * 隐藏加载 (ProgressDialog或者下拉刷新)
     */
    void hideLoading();

    /**
     * RecyclerView加载更多失败
     */
    void loadMoreFail();

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
