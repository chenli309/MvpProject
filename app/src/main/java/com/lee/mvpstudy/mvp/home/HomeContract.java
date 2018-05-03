package com.lee.mvpstudy.mvp.home;

import com.lee.mvpstudy.mvp.BasePresenter;
import com.lee.mvpstudy.mvp.BaseView;

public class HomeContract {

    public interface View extends BaseView {

//        void requestSmsSuccess(String phone, String password);
//
//        void registerSuccess(User user);
    }

    public interface Presenter extends BasePresenter {

        void requestSms(String phone, String password);

        void register(String phone, String password, String code);
    }
}
