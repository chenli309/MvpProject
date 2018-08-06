# MvpProject
干货API，使用MVP模式，Retrofit + OkHttp + RxJava + Rxlifecycle + MultiStateView

通过Rxlifecycle管理RxJava生命周期，解决网络请求内存泄露的问题

MultiStateView：加载中、空数据、请求失败、网络异常等

提交数据的网络请求，通过RxJava统一封装ProgressObserver，动态设置是否显示"加载中..."对话框
