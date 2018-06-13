package com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBasePaginationView;
import com.vironit.correctapp.utils.RecyclerViewUtil;

import butterknife.BindView;

public abstract class BasePaginationFragment<P extends BasePaginationPresenter>
        extends BaseFragment<P> implements IBasePaginationView{

    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.swipe_refresh_ly)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rv_news)
    protected RecyclerView mRecyclerView;

    private Runnable mEnableRefreshRunnable = () -> {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    };

    private Runnable mDisableRefreshRunnable = () -> {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    protected void initViewBeforePresenterAttach() {
        super.initViewBeforePresenterAttach();
        setLayoutManager();
        setPaginationRecyclerAdapter();
        setScrollListener();
        setRefreshListener();
    }

    protected void setScrollListener() {
        RecyclerViewUtil.setSrollListener(mRecyclerView, getPresenter());
    }

    @Nullable
    protected abstract BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter();

    protected abstract void setPaginationRecyclerAdapter();

    protected abstract void setLayoutManager();

    protected void setRefreshListener() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getPresenter().refreshData();
        });
    }

    @Override
    public void showPaginationProgress() {
        mSwipeRefreshLayout.removeCallbacks(mDisableRefreshRunnable);
        mSwipeRefreshLayout.post(mEnableRefreshRunnable);
    }

    @Override
    public void hidePaginationProgress() {
        mSwipeRefreshLayout.removeCallbacks(mEnableRefreshRunnable);
        mSwipeRefreshLayout.post(mDisableRefreshRunnable);
    }

    @Override
    public void removeData() {
        BasePaginationRecyclerViewAdapter basePaginationRecyclerViewAdapter = getBasePaginationRecyclerViewAdapter();
        if (basePaginationRecyclerViewAdapter != null) {
            basePaginationRecyclerViewAdapter.removeData();
        }
    }

    @Override
    public void onDestroyView() {
        hidePaginationProgress();
        super.onDestroyView();
    }
}
