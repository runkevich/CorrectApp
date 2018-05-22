package com.vironit.correctapp.mvp.presentation.view.implementation.activity.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    protected ResourcesManager injectResourceManager;

    @Nullable
    private Snackbar mSnackbar;

    @Nullable
    private AlertDialog mAlertDialog;

    @LayoutRes
    public abstract  int getLayoutResId();

    @IdRes
    public abstract int getRootViewResId();

    protected final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppLog.logActivity(this);
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

    }

   protected abstract P getPresenter();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getPresenter().onRequestPermissionsResult(requestCode,permissions,grantResults, this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode,resultCode,data,this);
    }



    @Override
    public void hideKeyboard() {
        KeyboardUtil.hideKeyboard(this);
    }

    @Override
    public void hideProgress() {
        if (mAlertDialog!=null && mAlertDialog.isShowing()){
            mAlertDialog.dismiss();
        }
    }

    @Override
    public void cancelScreen() {

    }

    @Override
    public void showProgress(@NonNull String message) {
        hideProgress();
        mAlertDialog = ShowDialogUtil.showProgressDialog(this,getString(R.string.app_name),message);
    }

    @Override
    public void showProgress() {

        hideProgress();
        mAlertDialog = ShowDialogUtil.showProgressDialog(this,getString(R.string.app_name),null);
    }

    @Override
    public void showAutoClosableMessage(@NonNull String message) {

        showMessage(message,true,null, (View.OnClickListener) null);

    }

    @Override
    public void showDialogMessage(@NonNull String message, boolean closable, boolean cancelable) {

        showDialogwithOptions(getString(R.string.app_name),message,null,
                null,null,null);


    }

    @Override
    public void showDialogwithOptions(@Nullable String title,
                                      @NonNull String message,
                                      @Nullable String positiveOptionMessage,
                                      @Nullable String negative,
                                      @Nullable  DialogInterface.OnClickListener  positiveListener,
                                      @Nullable  DialogInterface.OnClickListener  negativeListener) {

        hideDialogMessage();
        mAlertDialog = ShowDialogUtil.showMessageDialog(this,title,message,positiveOptionMessage,negative,
                positiveListener,negativeListener,false);

    }


    @Override
    public void hideDialogMessage() {

        if (mAlertDialog!=null && mAlertDialog.isShowing()){
            mAlertDialog.dismiss();
        }

    }

    @Override
    public void hideMessage() {
        if (mSnackbar != null){
            mSnackbar.dismiss();
        }

    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable View.OnClickListener actionListener) {
        if (mSnackbar!=null) {
            hideMessage();
        }

        @Nullable
        View rootView = findViewById(getRootViewResId());

        if (rootView == null){
            rootView = getWindow().getDecorView();
        }
        /*@BaseTransientBottomBar.Duration */int duration = closable ? BaseTransientBottomBar.LENGTH_SHORT : BaseTransientBottomBar.LENGTH_INDEFINITE;
        mSnackbar = ShowSnackBarUtil.showSnackBar(rootView,this,message,actionMessage,actionListener,duration);

    }
}
