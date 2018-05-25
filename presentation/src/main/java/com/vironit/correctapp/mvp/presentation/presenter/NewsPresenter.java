package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;

@InjectViewState
public class NewsPresenter extends BaseAppPresenter<INewsView> {
    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }
}
