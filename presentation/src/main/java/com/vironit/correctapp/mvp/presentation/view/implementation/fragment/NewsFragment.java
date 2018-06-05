package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.adapter.NewsAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.NewsPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BasePaginationFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;
import com.vironit.correctapp.utils.AppLog;

import butterknife.BindView;

public class NewsFragment extends BasePaginationFragment<NewsPresenter> implements INewsView {

    @InjectPresenter
    NewsPresenter mNewsPresenter;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_ly)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected NewsPresenter getPresenter() {
        return mNewsPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViewBeforePresenterAttach() {
        AppLog.logFragment(this);
        @Nullable Context context = getContext();
        if (context != null) {
            initPagination(mRecyclerView,
                    new LinearLayoutManager(context),
                    new NewsAdapter(context),
                    mSwipeRefreshLayout);
        } else {
            showAutoClosableMessage(getString(R.string.fail));
        }
    }
}
