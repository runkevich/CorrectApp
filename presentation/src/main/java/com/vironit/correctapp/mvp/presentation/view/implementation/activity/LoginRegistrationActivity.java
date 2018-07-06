package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.LoginRegistrationPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

public class LoginRegistrationActivity extends BaseActivity<LoginRegistrationPresenter> implements ILoginView {

    @InjectPresenter
    LoginRegistrationPresenter mLoginRegistrationPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_activate;
    }

    @Override
    public int getRootViewResId() {
        return R.id.id_v_activity_activate;
    }

    @Override
    protected LoginRegistrationPresenter getPresenter() {
        return mLoginRegistrationPresenter;
    }

    @Override
    public void showFailMessage() {

    }

    @Override
    public void showSuccesMessage() {

    }

    @Override
    public void goToHomeActivity() {

    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {
    }
}
