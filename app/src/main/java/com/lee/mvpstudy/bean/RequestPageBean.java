package com.lee.mvpstudy.bean;

public class RequestPageBean {

    /**
     * 当前第几页 接口参数
     */
    public int no = 1;
    /**
     * 每页多少条 接口参数
     */
    public int size = 15;


    /**
     * 判断当前的请求是不是刷新操作
     *
     * @return true：是首页，false：不是首页
     */
    public boolean isFirst() {
        return no == 1 ? true : false;
    }

    /**
     * 加载第一页
     */
    public void firstPage() {
        no = 1;
    }

    /**
     * 加载下一页
     */
    public int nextPage() {
        return no + 1;
    }

    /**
     * 当前页数+1
     */
    public void addPageNo() {
        no = no + 1;
    }

    /**
     * 是否还有下一页
     */
    public boolean isHaveNextPage(int size) {
        return this.size >= size;
    }
}
