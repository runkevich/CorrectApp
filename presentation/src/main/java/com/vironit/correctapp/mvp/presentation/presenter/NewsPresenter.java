package com.vironit.correctapp.mvp.presentation.presenter;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.Data;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class NewsPresenter extends BasePaginationPresenter<INewsView> {

    private static final int DATA_PAGE_SIZE = 10;
    private static final String COUNTRY_CODE = "ru";

    private int mDataPage;
    private boolean mIsDataLoading;

    @Inject
    NewsInteractor mNewsInteractor;

    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }

    public void loadData(boolean isAfterRefresh) {
        AppLog.logPresenter(this);
        if (!mIsDataLoading) {
            mIsDataLoading = true;
            if (isAfterRefresh) {
                mDataPage = 1;
                loadNews("ru", mDataPage, DATA_PAGE_SIZE);
            } else {
                if (mDataPage == 0) {
                    loadNewsFirstTime("ru", ++mDataPage, DATA_PAGE_SIZE);
                } else {
                    loadNews("ru", ++mDataPage, DATA_PAGE_SIZE);
                }
            }
        }
    }

    private void dataReceived(@Nullable Data data) {
        AppLog.logPresenter(this);
        if (data != null) {
            getViewState().dataListReceived(data.getArticles());
        }
        mIsDataLoading = false;
    }

    private void loadNewsFirstTime(@NonNull String country,
                                   @IntRange(from = 1) int page,
                                   @IntRange(from = 1,
                                           to = 100) int pageSize) {
        AppLog.logPresenter(this);
        getViewState().showProgress();
        addLiteDisposable(mNewsInteractor.getNews(country, page, pageSize)
                .observeOn(mUIScheduler)
                .doOnSuccess(this::dataReceived)
                .doFinally(() -> getViewState().hideProgress())
                .subscribe(list -> AppLog.logPresenter(this),
                        this));
    }

    @Override
    public void attachView(INewsView view) {
        AppLog.logPresenter(this);
        super.attachView(view);
        // loadNews();
        loadData();
    }

    private void loadNews(@NonNull String country,
                          @IntRange(from = 1) int page,
                          @IntRange(from = 1,
                                  to = 100) int pageSize) {
        getViewState().showProgress();
        addLiteDisposable(mNewsInteractor.getNews(country, page, pageSize)
                .observeOn(mUIScheduler)
                .doOnSuccess(list -> {
                })
                .doFinally(() -> getViewState().hideProgress())
                .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                        this));
        //throwable -> AppLog.logPresenter(this))
    }

    @Override
    public void receiveLinearLayoutScrollEvent(int visibleItemCount, int totalItemCount,
                                               int firstVisibleItemPos, @Nullable String id) {
        if (!mIsDataLoading) {
            if (firstVisibleItemPos != 0 && visibleItemCount + firstVisibleItemPos >= totalItemCount - 2) {
                ++mDataPage;
                loadData();
            }
        }
    }

    @Override
    public void receiveGridLayoutScrollEvent(@Nullable String id) {

    }

    @Override
    public void refreshData() {
        if (mIsDataLoading) {
            getViewState().dataListReceived(null);
        } else {
            mDataPage = 1;
            loadData();
        }
    }

    @Override
    protected void loadData() {
        AppLog.logPresenter(this);
        mIsDataLoading = true;
        if (mDataPage == 0) {
            ++mDataPage;
            getViewState().showProgress();
            addLiteDisposable(mNewsInteractor.getNews(COUNTRY_CODE, mDataPage, DATA_PAGE_SIZE)
                    .observeOn(mUIScheduler)
                    .doOnSuccess(this::dataReceived)
                    .doFinally(() -> getViewState().hideProgress())
                    .subscribe(list -> AppLog.logPresenter(this),
                            this));
        } else {
            addLiteDisposable(mNewsInteractor.getNews(COUNTRY_CODE, mDataPage, DATA_PAGE_SIZE)
                    .observeOn(mUIScheduler)
                    .doOnSuccess(this::dataReceived)
                    .subscribe(list -> AppLog.logPresenter(this),
                            this));
        }
    }
}
