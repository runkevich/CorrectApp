package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IProfileView;

@InjectViewState
public class ProfilePresenter extends BaseAppPresenter<IProfileView> {
    public ProfilePresenter() {
        App.getsAppComponent().inject(this);
    }
}
