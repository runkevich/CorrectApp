package com.vironit.correctapp.mvp.presentation.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

@InjectViewState
public class LoginPresenter extends BaseAppPresenter<ILoginView> {

    public LoginPresenter(){
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(ILoginView view) {
        super.attachView(view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull BaseActivity activity) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, activity);
    }


}
