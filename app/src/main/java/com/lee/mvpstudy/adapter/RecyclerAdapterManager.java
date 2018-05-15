package com.lee.mvpstudy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.lee.mvpstudy.R;

import java.util.List;

public class RecyclerAdapterManager {

    public static abstract class LeeRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

        public LeeRecyclerAdapter(int layoutResId, @Nullable List<T> data) {
            super(layoutResId, data);
//            RecycleView

//            setLoadMoreView(new CustomLoadMoreView());
        }

        public LeeRecyclerAdapter(@Nullable List<T> data) {
            super(data);

//            setLoadMoreView(new CustomLoadMoreView());
        }

        public LeeRecyclerAdapter(int layoutResId) {
            super(layoutResId);

//            setLoadMoreView(new CustomLoadMoreView());
        }


        /**
         * 设置是加载完成还是加载结束
         *
         * @param size 当前页加载数据条数
         */
        public void setLoadMoreStatus(int size) {
            setLoadMoreStatus(size, true);
        }

        /**
         * 设置是加载完成还是加载结束
         *
         * @param size 当前页加载数据条数
         */
        public void setLoadMoreStatus(int size, boolean isShowEnd) {
            if (isHaveNextPage(size)) {
                loadMoreComplete();
            } else {
//                loadMoreEnd(false);
                loadMoreEnd(!isShowEnd);
            }
        }

        /**
         * 是否还有下一页
         *
         * @param size 当前页加载数据条数
         * @return true 有  false 没有
         */
        private boolean isHaveNextPage(int size) {
            return size >= 15;
        }


        /**
         * 设置是加载完成还是加载结束
         *
         * @param isHaveNextPage 是否还有下一页
         */
        public void setLoadMoreStatus(boolean isHaveNextPage) {
            setLoadMoreStatus(isHaveNextPage, true);
        }

        /**
         * 设置是加载完成还是加载结束
         *
         * @param isHaveNextPage 是否还有下一页
         */
        public void setLoadMoreStatus(boolean isHaveNextPage, boolean isShowEnd) {
            if (isHaveNextPage) {
                loadMoreComplete();
            } else {
//                loadMoreEnd(false);
                loadMoreEnd(!isShowEnd);
            }
        }
    }

    /**
     * RecyclerAdapter 多类型的
     */
    public static abstract class LeeRecyclerMultiItemAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public LeeRecyclerMultiItemAdapter(List<T> data) {
            super(data);
//            setLoadMoreView(new CustomLoadMoreView());
        }

        /**
         * @param data 数据集
         * @param type 0:默认加载完（“我是有底线的”）1：加载完显示（“没有更多了”）
         */
        public LeeRecyclerMultiItemAdapter(List<T> data, int type) {
            super(data);
//            if (type == 0) {
//                setLoadMoreView(new CustomLoadMoreView());
//            } else {
//                setLoadMoreView(new CustomLoadMoreView2());
//            }
        }

        /**
         * 设置是加载完成还是加载结束
         *
         * @param size 当前页加载数据条数
         */
        public void setLoadMoreStatus(int size) {
            setLoadMoreStatus(size, true);
        }

        /**
         * 设置是加载完成还是加载结束
         *
         * @param size 当前页加载数据条数
         */
        public void setLoadMoreStatus(int size, boolean isShowEnd) {
            if (isHaveNextPage(size)) {
                loadMoreComplete();
            } else {
//                loadMoreEnd(false);
                loadMoreEnd(!isShowEnd);
            }
        }

        /**
         * 是否还有下一页
         *
         * @param size 当前页加载数据条数
         * @return true 有  false 没有
         */
        private boolean isHaveNextPage(int size) {
            return size >= 15;
        }
    }

    public static final class CustomLoadMoreView extends LoadMoreView {

        @Override
        public int getLayoutId() {
            return R.layout.view_brvah_load_more;
        }

        @Override
        protected int getLoadingViewId() {
            return R.id.load_more_loading_view;
        }

        @Override
        protected int getLoadFailViewId() {
            return R.id.load_more_load_fail_view;
        }

        @Override
        protected int getLoadEndViewId() {
            return R.id.load_more_load_end_view;
        }
    }

}
