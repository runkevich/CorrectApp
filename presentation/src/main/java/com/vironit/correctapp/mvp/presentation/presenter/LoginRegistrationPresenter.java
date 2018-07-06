package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

@InjectViewState
public class LoginRegistrationPresenter extends BaseAppPresenter<ILoginView> {

    public LoginRegistrationPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(ILoginView view) {
        super.attachView(view);
    }
}
