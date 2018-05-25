package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.NewsPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;

public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {

    @InjectPresenter
    NewsPresenter mNewsPresenter;

    @Override
    protected NewsPresenter getPresenter() {
        return mNewsPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

}
