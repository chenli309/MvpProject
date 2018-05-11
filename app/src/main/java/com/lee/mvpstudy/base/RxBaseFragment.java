package com.lee.mvpstudy.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

public class RxBaseFragment extends RxFragment {

    /**
     * 把OkHttp请求生命周期与RxFragment的生命周期绑定起来 解决Http请求泄露
     *
     * @return LifecycleTransformer
     */
    public LifecycleTransformer bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY); // this.bindToLifecycle()
    }
}
