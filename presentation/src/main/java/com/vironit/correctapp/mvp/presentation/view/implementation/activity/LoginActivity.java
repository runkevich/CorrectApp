package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.LoginPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.custom_login_button_twitter)
    Button bTwitter;

    @BindView(R.id.custom_login_button_facebook)
    Button bFacebook;

    @BindView(R.id.custom_login_button_google)
    Button bGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginPresenter.signOutFromAllAccounts();
        bTwitter.setOnClickListener(v -> mLoginPresenter.clickOnTwitter(this));
        bFacebook.setOnClickListener(v -> mLoginPresenter.clickOnFacebook(this));
        bGoogle.setOnClickListener(v -> mLoginPresenter.clickOnGoogle(this));
    }

    public static void start(@Nullable Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public int getRootViewResId() {
        return R.id.id_v_activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return mLoginPresenter;
    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {

    }

    @Override
    public void showFailMessage() {
        Toast.makeText(this, getString(R.string.fail), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccesMessage() {
        Toast.makeText(this, getString(R.string.ok), Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
