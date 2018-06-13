package com.vironit.correctapp.utils;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;

public abstract class RecyclerViewUtil {

    public static void setSrollListener(@Nullable RecyclerView recyclerView,
                                        @Nullable BasePaginationPresenter basePaginationPresenter) {

        if (recyclerView != null && basePaginationPresenter != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        if (adapter instanceof BaseRecyclerViewAdapter) {
                            BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) adapter;
                            int realItemCount = baseRecyclerViewAdapter.getRealItemsCount();
                            int lastVisibleItemPosition = getLastVisibleItemPosition(recyclerView);
                            @Nullable String lastItemId = null;
                            if (baseRecyclerViewAdapter instanceof BasePaginationRecyclerViewAdapter) {
                                lastItemId = ((BasePaginationRecyclerViewAdapter) baseRecyclerViewAdapter).getLastItemId();
                            }

                            basePaginationPresenter.loadDataCheck(realItemCount, lastVisibleItemPosition, lastItemId);
                        }
                    }
                }
            });
        }
    }

    private static int getLastVisibleItemPosition(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return linearLayoutManager.findLastVisibleItemPosition();
    }
}
