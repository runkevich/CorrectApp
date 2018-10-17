package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITempView;

@InjectViewState
public class TempPresenter extends BaseAppPresenter<ITempView> {

    public TempPresenter() {
        App.getsAppComponent().inject(this);
    }
}
