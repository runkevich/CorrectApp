package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.LoginPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        check();
    }

    private void check() {
        findViewById(R.id.custom_login_button_facebook).
                setOnClickListener(v -> showDialogwithOptions("dialog_with_options",
                        "dialog_with_options",
                        "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this,"positiveText",Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this,"negativeText",Toast.LENGTH_SHORT).show()));

        findViewById(R.id.custom_login_button_twitter).
                setOnClickListener(v -> showDialogwithOptions("dialog_with_options",
                        "dialog_with_options",
                        "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this,"positiveText",Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this,"negativeText",Toast.LENGTH_SHORT).show()));

        findViewById(R.id.custom_login_button_google).
                setOnClickListener(v -> showDialogwithOptions("dialog_with_options",
                        "dialog_with_options",
                        "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this,"positiveText",Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this,"negativeText",Toast.LENGTH_SHORT).show()));
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
}
