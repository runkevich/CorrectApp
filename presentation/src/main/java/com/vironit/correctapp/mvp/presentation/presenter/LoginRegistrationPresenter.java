package com.vironit.correctapp.mvp.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.OauthInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginRegistrationView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class LoginRegistrationPresenter extends BaseAppPresenter<ILoginRegistrationView> {

    @Inject
    OauthInteractor mOauthInteractor;

    public LoginRegistrationPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(ILoginRegistrationView view) {
        super.attachView(view);
    }

    public void registration(@NonNull String personName, @NonNull String personEmail, @NonNull String personPassword) {
        mOauthInteractor.register(personName, personEmail, personPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(v -> getViewState().goToHome())
                .subscribe(co -> AppLog.logPresenter(this, "OOOOOKKKKK4"),
                        co -> AppLog.logPresenter(this, "Not registration"));
    }
}
