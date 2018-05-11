//package com.lee.mvpstudy.http;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableTransformer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//
//public class DefaultTransformer<T> implements ObservableTransformer<T, T> {
//    @Override
//    public Observable<T> call(Observable<T> tObservable) {
//        return tObservable
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<T, T>() {// 通用错误处理，判断code
//                    @Override
//                    public T call(T t) {
//                        if (((BaseEntity<T>)t).getStatus_code() != 10000) {
//                            throw new ApiException(((BaseEntity<T>)t).getStatus_code(), ((BaseEntity<T>)t).getError_msg());
//                        }
//                        return t;
//                    }
//                });
//    }
//
//    public static <T> DefaultTransformer<T> create() {
//        return new DefaultTransformer<>();
//    }
//}