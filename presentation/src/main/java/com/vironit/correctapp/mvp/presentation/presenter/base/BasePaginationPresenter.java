package com.vironit.correctapp.mvp.presentation.presenter.base;

import android.support.annotation.Nullable;

import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBasePaginationView;
import com.vironit.correctapp.utils.AppLog;

import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePaginationPresenter<View extends IBasePaginationView> extends BaseAppPresenter<View> {

    protected boolean mIsNextPageAllow = true;

    @Nullable
    private Disposable mPaginationDisposable = null;

   @Override
   protected void handleUnNollError(@Nullable Throwable throwable) {
       AppLog.logPresenter(this);
       getViewState().hidePaginationProgress();
       super.handleUnNollError(throwable);
   }

    @Override
    protected void clearLiteDisposable() {
        AppLog.logPresenter(this);
        clearPaginationDisposable();
        super.clearLiteDisposable();
    }

    public void loadDataCheck(int totalItemCount,
                              int lastVisibleItemPosition,
                              @Nullable String lastItemId) {
        AppLog.logPresenter(this);
        if (lastVisibleItemPosition + getItemsCountPerPage() / 3 > totalItemCount
                && mIsNextPageAllow
                && !isLoading()) {
            loadData(totalItemCount, lastItemId);
        }
    }

    protected void addPaginationDisposable(@Nullable Disposable disposable) {
        AppLog.logPresenter(this);
        if (disposable != null) {
            clearPaginationDisposable();
            mPaginationDisposable = disposable;
        }
    }

    protected void clearPaginationDisposable() {
        AppLog.logPresenter(this);
        if (isLoading()) {
            mPaginationDisposable.dispose();
        }
    }

    private boolean isLoading() {
        AppLog.logPresenter(this);
        return mPaginationDisposable != null && !mPaginationDisposable.isDisposed();
    }

    public void refreshData() {
        AppLog.logPresenter(this);
        mIsNextPageAllow = true;
        clearPaginationDisposable();
        getViewState().removeData();
        loadData(0, null);
    }

    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        AppLog.logPresenter(this);
        getViewState().showPaginationProgress();
    }

    protected int getItemsCountPerPage() {
        return AppConstants.DEFAULT_ITEMS_COUNT_PER_PAGE;
    }

    protected void setNextPageAllow(@Nullable List list) {
        mIsNextPageAllow = !(list == null || (list.size() < getItemsCountPerPage()));
    }


}
