package com.lee.mvpstudy.base;

import android.content.Context;
import android.text.TextUtils;

import com.lee.mvpstudy.http.ApiException;
import com.lee.mvpstudy.progress.ObserverOnNextListener;
import com.lee.mvpstudy.progress.ProgressObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BaseModel<T> {
    /**
     * 封装线程管理和订阅的过程
     */
    public void Subscribe(Context context, final Observable observable, ObserverOnNextListener<T> listener) {
        final Observer<T> observer = new ProgressObserver<T>(context, listener);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void Subscribe(RxBaseActivity context, final Observable observable, ObserverOnNextListener<T> listener) {
        final Observer<T> observer = new ProgressObserver<T>(context, listener);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(getObservableTransformer(context))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void Subscribe(RxBaseFragment context, final Observable observable, ObserverOnNextListener<T> listener) {
        final Observer<T> observer = new ProgressObserver<T>(context.getContext(), listener);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(getObservableTransformer(context))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private <T> ObservableTransformer<T, T> getObservableTransformer(final RxBaseActivity rxAct) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<T, T>() {
//                            @Override
//                            public T apply(T t) throws Exception {
//                                if (!((BaseBean) t).isSuccess()) {
//                                    throw new ApiException(((BaseBean) t).getCode(), ((BaseBean) t).getMessage());
//                                }
//                                return t;
//                            }
//                        })
                        .compose(rxAct.bindToLife());
            }
        };
    }

    private <T> ObservableTransformer<T, T> getObservableTransformer(final RxBaseFragment rxFragment) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<T, T>() {
//                            @Override
//                            public T apply(T t) throws Exception {
//                                if (!((BaseBean) t).isSuccess()) {
//                                    throw new ApiException(((BaseBean) t).getCode(), ((BaseBean) t).getMessage());
//                                }
//                                return t;
//                            }
//                        })
                        .compose(rxFragment.bindToLife());
            }
        };
    }
}
