package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.TestPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITestView;


public class TestActivity extends BaseActivity<TestPresenter> implements ITestView{

    @InjectPresenter
    TestPresenter mTestPresenter;

    public static void start(@Nullable Context context ){
        if (context!=null){
            context.startActivity(new Intent(context,TestActivity.class));
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public int getRootViewResId() {
        return R.id.id_v_activity_test;
    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    @Override
    protected TestPresenter getPresenter() {
        return mTestPresenter;
    }

    private void setListener() {
        findViewById(R.id.btn_show_dialog_simple_message)
                .setOnClickListener(v -> showDialogMessage("simple_message", true,true));

        findViewById(R.id.btn_show_dialog_with_options)
                .setOnClickListener(v -> showDialogWithOptions("dialog_with_options",
                        "dialog_with_options",
                        "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this,"positiveText",Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this,"negativeText",Toast.LENGTH_SHORT).show()));


        findViewById(R.id.btn_show_message_with_action)
                .setOnClickListener(v -> showMessage("message_with_action", false
                        , "message_with_action", v1 -> Toast.makeText(this,"actionText",Toast.LENGTH_SHORT).show()));

        findViewById(R.id.btn_show_avtoclosable_message)
                .setOnClickListener(v -> showAutoClosableMessage("klhlkhlk"));
    }
}
