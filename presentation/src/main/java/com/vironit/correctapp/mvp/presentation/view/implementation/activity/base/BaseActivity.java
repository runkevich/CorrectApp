package com.vironit.correctapp.mvp.presentation.view.implementation.activity.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.vironit.correctapp.R;
import com.vironit.correctapp.constans.AppConstans;
import com.vironit.correctapp.mvp.model.manager.interfaces.ResourcesManager;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBaseView;
import com.vironit.correctapp.utils.AppLog;
import com.vironit.correctapp.utils.KeyboardUtil;
import com.vironit.correctapp.utils.ShowDialogUtil;
import com.vironit.correctapp.utils.ShowSnackBarUtil;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.Scheduler;

public abstract class BaseActivity<P extends BaseAppPresenter> extends MvpAppCompatActivity implements IBaseView {

    @Inject
    @Named(AppConstans.UI_SCHEDULER)
    protected Scheduler mUIScheduler;

    @Inject
    @Named(AppConstans.IO_SCHEDULER)
    protected Scheduler mIOScheduler;

    @Inject
    @Named(AppConstans.COMPUTATION_SCHEDULER)
    protected Scheduler mComputationScheduler;

    @Inject
    protected ResourcesManager mResourceManager;

    @Nullable
    private Snackbar mSnackbar;

    @Nullable
    private AlertDialog mAlertDialog;

    protected final Handler mHandler = new Handler();

    @LayoutRes
    public abstract int getLayoutResId();

    @IdRes
    public abstract int getRootViewResId();

    protected abstract P getPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppLog.logActivity(this);
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                initFromIntentExtras(getIntent().getExtras());
            }

        }

        initBeforeLayoutAttach();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initViewBeforeAttached();
        getMvpDelegate().onAttach();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getMvpDelegate().onAttach();
    }

    @Override
    protected void onStart() {
        AppLog.logActivity(this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        AppLog.logActivity(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        AppLog.logActivity(this);
        hideKeyboard();
        super.onPause();
    }

    @Override
    protected void onStop() {
        AppLog.logActivity(this);
        hideProgress();
        hideDialogMessage();
        hideMessage();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AppLog.logActivity(this);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getPresenter().onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data, this);
    }

    protected void initFromIntentExtras(@NonNull Bundle bundle) {

        //TODO

    }

    void initViewBeforeAttached() {

        //TODO

    }

    void initBeforeLayoutAttach() {

        //TODO

    }

    protected  String getResourseString(@StringRes int stringId) {
        return mResourceManager.getString(stringId);
    }


    @Override
    public void hideKeyboard() {
        KeyboardUtil.hideKeyboard(this);
    }

    @Override
    public void cancelScreen() {
        finish();
    }

    @Override
    public void hideProgress() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }


    @Override
    public void showProgress(@NonNull String message) {
        //String titleText = getResources().getString(R.string.progress_title);
        hideProgress();
        mAlertDialog = ShowDialogUtil.showProgressDialog(this, getString(R.string.app_name), message);
    }

    @Override
    public void showProgress() {
        showProgress(null);
//        hideProgress();
//        mAlertDialog = ShowDialogUtil.showProgressDialog(this, getString(R.string.app_name), null);
    }

    @Override
    public void showAutoClosableMessage(@NonNull String message) {

        showMessage(message, true, null, (View.OnClickListener) null);

    }

    @Override
    public void showDialogMessage(@NonNull String message, boolean closable, boolean cancelable) {
        String title = getResources().getString(R.string.app_name);
        showDialogWithOptions(title, message, null,
                null, null, null);


    }

    @Override
    public void showDialogWithOptions(@Nullable String title,
                                      @NonNull String message,
                                      @Nullable String positiveOptionMessage,
                                      @Nullable String negative,
                                      @Nullable DialogInterface.OnClickListener positiveListener,
                                      @Nullable DialogInterface.OnClickListener negativeListener) {

        hideDialogMessage();
        mAlertDialog = ShowDialogUtil.showMessageDialog(this, title, message, positiveOptionMessage, negative,
                positiveListener, negativeListener, false);

    }


    @Override
    public void hideDialogMessage() {

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }

    }

    @Override
    public void hideMessage() {
        if (mSnackbar != null) {
            mSnackbar.dismiss();
        }

    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable View.OnClickListener actionListener) {
        hideKeyboard();
        hideMessage();

        @Nullable
        View rootView = findViewById(getRootViewResId());

        if (rootView == null) {
            rootView = getWindow().getDecorView();
        }
        /*@BaseTransientBottomBar.Duration */
        int duration = closable ? BaseTransientBottomBar.LENGTH_SHORT : BaseTransientBottomBar.LENGTH_INDEFINITE;
        mSnackbar = ShowSnackBarUtil.showSnackBar(rootView, this, message, actionMessage, actionListener, duration);

    }
}
