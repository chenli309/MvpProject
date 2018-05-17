package com.lee.mvpstudy.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.lee.mvpstudy.util.ListUtils;
import com.lee.mvpstudy.view.LeeRecyclerView;
import com.lee.mvpstudy.view.LeeSwipeRefreshLayout;

import java.util.List;

public class HomeActivity extends RxBaseActivity<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private LeeSwipeRefreshLayout swipeRefreshLayout;
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
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        showPageLoading();
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

        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPageBean.firstPage();
        mAdapter.setEnableLoadMore(false);
        mPresenter.requestCategory(this, HttpConfig.CATEGORY_NAME_ANDROID, mPageBean.size, mPageBean.no);
    }

    @Override
    public void setRequestData(List<CategoryResult.ResultsBean> results) {
        mAdapter.setNewData(results);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreStatus(results.size());
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreCategory(this, HttpConfig.CATEGORY_NAME_ANDROID, mPageBean.size, mPageBean.nextPage());
    }

    @Override
    public void setRequestLoadMoreData(List<CategoryResult.ResultsBean> results) {
        if (ListUtils.isEmpty(results)) {
            mAdapter.setLoadMoreStatus(0);
            return;
        }

        mPageBean.addPageNo();
        mAdapter.addData(results);
        mAdapter.setLoadMoreStatus(results.size());
    }

    @Override
    public void loadMoreFail() {
        super.loadMoreFail();
        mAdapter.loadMoreFail();
    }
}
