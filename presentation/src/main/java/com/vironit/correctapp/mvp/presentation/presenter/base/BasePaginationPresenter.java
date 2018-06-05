package com.vironit.correctapp.mvp.presentation.presenter.base;

import android.support.annotation.Nullable;

import com.vironit.correctapp.mvp.presentation.view.interfaces.IBasePaginationView;

public abstract class BasePaginationPresenter<T extends IBasePaginationView> extends BaseAppPresenter<T> {

    public abstract void receiveLinearLayoutScrollEvent(int visibleItemCount,
                                                        int totalItemCount,
                                                        int firstVisibleItemPos,
                                                        @Nullable String id);

    public abstract void receiveGridLayoutScrollEvent(@Nullable String id);

    public abstract void refreshData();

    protected abstract void loadData();
}
