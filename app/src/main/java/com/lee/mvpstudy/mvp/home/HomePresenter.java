package com.lee.mvpstudy.mvp.home;

import android.os.Bundle;

import com.lee.mvpstudy.base.BaseModel;
import com.lee.mvpstudy.base.RxBaseActivity;
import com.lee.mvpstudy.bean.CategoryResult;
import com.lee.mvpstudy.http.Api;
import com.lee.mvpstudy.progress.ObserverOnNextAdapter;
import com.lee.mvpstudy.util.ListUtils;

public class HomePresenter implements HomeContract.Presenter {

    private BaseModel<CategoryResult> model = new BaseModel<>();
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void getDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            return;
        }
    }

    @Override
    public void onPresenterStart() {
        mView.requestStart();
    }

    @Override
    public void onPresenterDestroy() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void requestCategory(RxBaseActivity activity, String category, int number, int page) {
        model.subscribe(activity, Api.getApiService().getCategoryDate(category, number, page), new ObserverOnNextAdapter<CategoryResult>() {

            @Override
            public void onSuccess(CategoryResult categoryResult) {
                if (!ListUtils.isEmpty(categoryResult.results)) {
                    mView.setRequestData(categoryResult.results);
                    mView.showPageContent();
                } else {
                    mView.showPageEmpty();
                }
                mView.hideLoading();
            }

            @Override
            public void onCodeError(CategoryResult categoryResult) {
                super.onCodeError(categoryResult);
                mView.showPageError();
                mView.hideLoading();
            }

            @Override
            public void onFailure(Throwable e, boolean isNetWorkError) {
                super.onFailure(e, isNetWorkError);
                mView.showPageError();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void loadMoreCategory(RxBaseActivity activity, String category, int number, int page) {
        model.subscribe(activity, Api.getApiService().getCategoryDate(category, number, page), new ObserverOnNextAdapter<CategoryResult>() {

            @Override
            public void onSuccess(CategoryResult categoryResult) {
                mView.setRequestLoadMoreData(categoryResult.results);
            }

            @Override
            public void onCodeError(CategoryResult categoryResult) {
                super.onCodeError(categoryResult);
                mView.showMsg(categoryResult.getMessage());
                mView.loadMoreFail();
            }

            @Override
            public void onFailure(Throwable e, boolean isNetWorkError) {
                super.onFailure(e, isNetWorkError);
                mView.showMsg(e.getMessage());
                mView.loadMoreFail();
            }
        });
    }
}
