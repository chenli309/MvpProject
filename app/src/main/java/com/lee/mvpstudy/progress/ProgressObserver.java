package com.lee.mvpstudy.progress;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.lee.mvpstudy.base.BaseBean;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by DeMon on 2017/9/6.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver";
    private ObserverOnNextListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;
    private boolean isShowProgressDialog = false;

    public ProgressObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    public ProgressObserver(Context context, boolean isShowProgressDialog, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        this.isShowProgressDialog = isShowProgressDialog;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null && isShowProgressDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null && isShowProgressDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.d(TAG, "onSubscribe: ");
        showProgressDialog();

        onRequestStart();
    }

    protected void onRequestStart() {
    }

    @Override
    public void onNext(T t) {
        if (listener == null) {
            return;
        }

        if (((BaseBean) t).isSuccess()) {
            try {
                listener.onSuccess(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                listener.onCodeError(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);

        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof SocketTimeoutException
                    || e instanceof UnknownHostException) {
                listener.onFailure(e, true);
            } else {
                listener.onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

//        if (e instanceof ApiException) {
//            //处理服务器返回的错误
//        } else if (e instanceof HttpException) {
//            int errorCode = ((HttpException) e).response().code();
//            switch (errorCode) {
//                case 401:
////                    M response = gson.fromJson("", getClz());
////                    callBack.onFail(response);
//                    break;
//                case 500:
//                    break;
//                default:
//                    break;
//            }
//        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
//            ToastUtils.showShort("网络异常，请检查网络");
//        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
//            ToastUtils.showShort("网络不畅，请稍后再试！");
//        } else if (e instanceof JsonSyntaxException) {
//            ToastUtils.showShort("数据解析异常");
//        } else {
//            ToastUtils.showShort("服务端错误");
//        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.d(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
