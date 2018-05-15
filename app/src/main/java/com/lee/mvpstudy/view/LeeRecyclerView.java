package com.lee.mvpstudy.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class LeeRecyclerView extends RecyclerView {

    private Context context;

    public LeeRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    public LeeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public LeeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void addItemDecoration(int space) {
        addItemDecoration(space, true);
    }

    public void addItemDecoration(int space, boolean includeEdge) {
        addItemDecoration(new LinearSpacingItemDecoration(SizeUtils.dp2px(space), includeEdge));
    }

    public void addGridItemDecoration(int spanCount, int space, boolean includeEdge) {
        addItemDecoration(new GridSpacingItemDecoration(spanCount, SizeUtils.dp2px(space), includeEdge));
    }

    /**
     * 配置显示图片，需要设置这几个参数，快速滑动时，暂停图片加载
     */
    public void addOnPauseListenerParams() {
        addOnScrollListener(new GlideScrollListener());
    }

    /**
     * 绑定适配器的同时，判断是否绑定上拉加载
     *
     * @param mBaseQuickAdapter        适配器
     * @param mRequestLoadMoreListener null:不绑定上拉加载，否则绑定对应的监听方法
     */
    public void setAdapter(BaseQuickAdapter mBaseQuickAdapter, BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener) {
        mBaseQuickAdapter.setOnLoadMoreListener(mRequestLoadMoreListener, this); // 绑定上拉加载
        setAdapter(mBaseQuickAdapter);
    }

    /*===================InnerClass=================*/
    public static class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int space;
        private boolean includeEdge; // 边缘是否有间距  true有 false无

        public LinearSpacingItemDecoration(int space, boolean includeEdge) {
            this.space = space;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        outRect.left = space;
//        outRect.right = space;
            outRect.bottom = space;

            if (includeEdge) {
                // Add top margin only for the first item to avoid double space between items
                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = space;
                } else {
                    outRect.top = 0;
                }
            } else {
                outRect.top = 0;
            }
        }
    }

    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount; // 3列
        private int spacing; // 间距
        private boolean includeEdge; // 边缘是否有间距  true有 false无

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * 控制滚动时不加载图片
     */
    private class GlideScrollListener extends OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；由于用户的操作，屏幕产生惯性滑动时为2
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                Glide.with(context).pauseRequests();
            } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                Glide.with(context).resumeRequests();
            }
        }
    }
}
