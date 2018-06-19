package com.vironit.correctapp.mvp.presentation.presenter;

import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class NewsPresenter extends BasePaginationPresenter<INewsView> {

    @Inject
    NewsInteractor mNewsInteractor;

    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        super.loadData(totalItemCount, lastItemId);
        addPaginationDisposable(mNewsInteractor.getNews(totalItemCount / getItemsCountPerPage(),getItemsCountPerPage())
                .observeOn(mUIScheduler)
                .doOnSuccess(dataElement -> setNextPageAllow(dataElement.getArticles()))
                .doOnSuccess(dataElement -> getViewState().addDataList(dataElement.getArticles()))
                .doFinally(() -> getViewState().hidePaginationProgress())
                .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                        this));

    }

    @Override
    protected int getItemsCountPerPage() {
        return 7;
    }

    @Override
    public void detachView(INewsView view) {
        super.detachView(view);
        refreshData();
    }
}
