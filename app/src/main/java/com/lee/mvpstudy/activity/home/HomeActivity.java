package com.lee.mvpstudy.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lee.mvpstudy.R;
import com.lee.mvpstudy.adapter.HomeAdapter;
import com.lee.mvpstudy.base.RxBaseActivity;
import com.lee.mvpstudy.bean.CategoryResult;
import com.lee.mvpstudy.http.HttpConfig;
import com.lee.mvpstudy.mvp.home.HomeContract;
import com.lee.mvpstudy.mvp.home.HomePresenter;
import com.lee.mvpstudy.view.LeeRecyclerView;

import java.util.List;

public class HomeActivity extends RxBaseActivity<HomePresenter> implements HomeContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private LeeRecyclerView recyclerView;
    private HomeAdapter mAdapter;

    @Override
    protected void getDataFromBundle(@Nullable Bundle savedInstanceState) {
        mPresenter = new HomePresenter(this);
        mPresenter.getDataFromBundle(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        mAdapter = new HomeAdapter();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.item_root_layout) {

                }
            }
        });
        recyclerView.setAdapter(mAdapter, this);

        mPresenter.requestCategory(this, HttpConfig.CATEGORY_NAME_ANDROID, 12, 1);
    }

    @Override
    public void setPageData(List<CategoryResult.ResultsBean> results) {
        mAdapter.setNewData(results);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
