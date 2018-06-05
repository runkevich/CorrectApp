package com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBasePaginationView;
import com.vironit.correctapp.utils.AppLog;

import java.util.List;

public abstract class BasePaginationFragment<T extends BasePaginationPresenter> extends BaseFragment<T>
        implements IBasePaginationView{

    private BaseRecyclerViewAdapter mBaseAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRefreshing;


    protected void initPagination(@NonNull RecyclerView recyclerView,
                                  RecyclerView.LayoutManager layoutManager,
                                  BaseRecyclerViewAdapter adapter,
                                  SwipeRefreshLayout swipeRefreshLayout) {
        AppLog.logFragment(this);
        mBaseAdapter = adapter;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    //TODO: grid pagination
                    super.onScrolled(recyclerView, dx, dy);
                    getPresenter().receiveGridLayoutScrollEvent(null);
                }
            });
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPos = linearLayoutManager.findFirstVisibleItemPosition();
                    getPresenter().receiveLinearLayoutScrollEvent(visibleItemCount, totalItemCount, firstVisibleItemPos, null);
                }
            });
        }
        if (swipeRefreshLayout != null) {
            mSwipeRefreshLayout = swipeRefreshLayout;
            swipeRefreshLayout.setOnRefreshListener(() -> {
                mIsRefreshing = true;
                getPresenter().refreshData();
            });
        }
    }

    @Override
    public void dataListReceived(@Nullable List data) {
        if (mIsRefreshing && mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (data != null) {
            if (mIsRefreshing) {
                mBaseAdapter.removeData();
            }
//          TODO: unchecked call
            mBaseAdapter.addData(data);
        }
        mIsRefreshing = false;
    }

    @Override
    public void dataLoadingError(int strId) {
        if (mSwipeRefreshLayout != null && mIsRefreshing) {
            mIsRefreshing = false;
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (strId != AppConstants.NO_STRING_RES) {
            showAutoClosableMessage(getResourseString(strId));
        }
    }
}
