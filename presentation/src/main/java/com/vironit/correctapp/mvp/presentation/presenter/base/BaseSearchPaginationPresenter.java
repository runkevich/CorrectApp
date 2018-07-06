package com.vironit.correctapp.mvp.presentation.presenter.base;

import com.vironit.correctapp.mvp.presentation.view.interfaces.IBaseSearchPaginationView;

public abstract class BaseSearchPaginationPresenter<View extends IBaseSearchPaginationView>
        extends BasePaginationPresenter<View> {



    @Override
    protected void clearPaginationDisposable() {
        super.clearPaginationDisposable();
    }
}
