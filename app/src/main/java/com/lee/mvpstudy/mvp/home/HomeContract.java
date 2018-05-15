package com.lee.mvpstudy.mvp.home;

import com.lee.mvpstudy.base.RxBaseActivity;
import com.lee.mvpstudy.bean.CategoryResult;
import com.lee.mvpstudy.mvp.BasePresenter;
import com.lee.mvpstudy.mvp.BaseView;

import java.util.List;

public class HomeContract {

    public interface View extends BaseView {

        void setPageData(List<CategoryResult.ResultsBean> results);
    }

    public interface Presenter extends BasePresenter {

        void requestCategory(RxBaseActivity activity, String category, int number, int page);

    }
}
