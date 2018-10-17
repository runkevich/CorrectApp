package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.LoginRegistrationPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ILoginRegistrationView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginRegistrationActivity extends BaseActivity<LoginRegistrationPresenter> implements ILoginRegistrationView {

    @InjectPresenter
    LoginRegistrationPresenter mLoginRegistrationPresenter;

    @BindView(R.id.et_email_in_registration)
    EditText emailInRegistration;

    @BindView(R.id.ed_name_in_registeration)
    EditText nameInRegistration;

    @BindView(R.id.et_password_in_registration1)
    EditText passwordInRegistration1;

    @BindView(R.id.et_password_in_registration2)
    EditText passwordInRegistration2;

    @OnClick(R.id.b_singup_registratation)
    void singUpRegistration() {
        String nameInRegistration_s = nameInRegistration.getText().toString();
        String emailInRegistration_s = emailInRegistration.getText().toString();
        String passwordInRegistration1_s = passwordInRegistration1.getText().toString();
        String passwordInRegistration2_s = passwordInRegistration2.getText().toString();

        if (!nameInRegistration_s.equals("") && !emailInRegistration_s.equals("") && !passwordInRegistration1_s.equals("")
                && !passwordInRegistration2_s.equals("")){
            if (passwordInRegistration1_s.equals(passwordInRegistration2_s)) {
                mLoginRegistrationPresenter.registration(nameInRegistration_s, emailInRegistration_s, passwordInRegistration1_s);
            } else {
                showAutoClosableMessage("Проверьте введние паролей.");
            }
        } else{
            showAutoClosableMessage("Неправильно введены данные!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {
    }



    @Override
    public void goToHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

}
