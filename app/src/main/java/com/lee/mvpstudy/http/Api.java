package com.lee.mvpstudy.http;

public class Api {

    private String baseUrl = "http://gc.ditu.aliyun.com/";//阿里云根据地区名获取经纬度接口

    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
