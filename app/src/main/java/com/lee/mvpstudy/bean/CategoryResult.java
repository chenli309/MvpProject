package com.lee.mvpstudy.bean;

import com.lee.mvpstudy.base.BaseBean;
import com.lee.mvpstudy.base.StatusBean;

import java.util.List;

public class CategoryResult extends StatusBean {
    public List<ResultsBean> results;
    public List<ResultsBean> results1;

    public static class ResultsBean extends BaseBean {

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;
    }
}
