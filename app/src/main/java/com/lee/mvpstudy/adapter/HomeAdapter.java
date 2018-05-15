package com.lee.mvpstudy.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lee.mvpstudy.GlideApp;
import com.lee.mvpstudy.R;
import com.lee.mvpstudy.bean.CategoryResult.ResultsBean;
import com.lee.mvpstudy.util.LeeDateUtil;
import com.lee.mvpstudy.util.ListUtils;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerAdapterManager.LeeRecyclerAdapter<ResultsBean> {
    public HomeAdapter() {
        super(R.layout.item_home_adapter_layout, new ArrayList<ResultsBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultsBean item) {

        if (!ListUtils.isEmpty(item.images)) {
            ImageView imageView = helper.getView(R.id.iv_picture);
            GlideApp.with(mContext).load(item.images.get(0) + "?imageView2/0/w/400").centerCrop().into(imageView);
            helper.setGone(R.id.iv_picture, true);
        } else {
            helper.setGone(R.id.iv_picture, false);
        }

        helper.setText(R.id.tv_title, item.desc == null ? "unknown" : item.desc)
                .setText(R.id.tv_author, item.who == null ? "unknown" : item.who)
                .setText(R.id.tv_time, LeeDateUtil.dateFormat(item.publishedAt))
                .addOnClickListener(R.id.item_root_layout);
    }
}
