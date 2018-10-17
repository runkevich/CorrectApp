package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.TestPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITestView;

import io.reactivex.disposables.Disposable;


public class TestActivity extends BaseActivity<TestPresenter> implements ITestView {

    @InjectPresenter
    TestPresenter mTestPresenter;

    public static void start(@Nullable Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, TestActivity.class));
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
    public void showDialogWithOptions(@NonNull String title, @NonNull String message, @NonNull String positiveOptionMessage, @NonNull String negative, @Nullable DialogInterface.OnClickListener positiveListener, @Nullable DialogInterface.OnClickListener negativeListener) {

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
                .setOnClickListener(v -> showDialogMessage("simple_message", true, true));

        findViewById(R.id.btn_show_dialog_with_options)
                .setOnClickListener(v -> showDialogWithOptions("dialog_with_options",
                        "dialog_with_options",
                       "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this, "positiveText", Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this, "negativeText", Toast.LENGTH_SHORT).show()));


        findViewById(R.id.btn_show_message_with_action)
                .setOnClickListener(v -> showMessage("message_with_action", false
                        , "message_with_action", v1 -> Toast.makeText(this, "actionText", Toast.LENGTH_SHORT).show()));

        findViewById(R.id.btn_show_avtoclosable_message)
                .setOnClickListener(v -> showAutoClosableMessage("klhlkhlk"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((Application) getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            @Nullable
            private Disposable mDisposable = null;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
                //mDisposable = mTestPresenter.onlineStatus();
            }

            @Override
            public void onActivityStopped(Activity activity) {
               mDisposable.dispose();
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
