package com.vironit.correctapp.mvp.presentation.view.implementation.activity.base;

import android.support.v7.widget.RecyclerView;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBasePaginationView;
import com.vironit.correctapp.utils.RecyclerViewUtil;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BasePaginationActivity extends BaseActivity<BasePaginationPresenter> implements IBasePaginationView{

    @BindView(R.id.rv_news)
    RecyclerView recyclerView;

    @Inject
    BasePaginationPresenter basePaginationPresenter;

    @Override
     void initViewBeforeAttached() {
        super.initViewBeforeAttached();
        setLayoutManager();
        initPaginationRecyclerAdapter();
        initScrollListener();
    }

    protected abstract void setLayoutManager();

    protected abstract void initPaginationRecyclerAdapter();

    protected void initScrollListener(){
        RecyclerViewUtil.setSrollListener(recyclerView,basePaginationPresenter);
    }

    @Override
    public void showPaginationProgress() {

    }

    @Override
    public void hidePaginationProgress() {

    }

    @Override
    public void removeData() {

    }
}
