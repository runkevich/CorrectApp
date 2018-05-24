package com.vironit.correctapp.mvp.presentation.presenter.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpPresenter;
import com.vironit.correctapp.constans.AppConstans;
import com.vironit.correctapp.mvp.model.manager.interfaces.ResourcesManager;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBaseView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<View extends IBaseView> extends MvpPresenter<View> {

    private final CompositeDisposable mLiteCompositeDisposable = new CompositeDisposable();
    private final CompositeDisposable mHardCompositeDisposable = new CompositeDisposable();
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
    protected ResourcesManager mResourcesManager;

    @Override
    public void attachView(View view) {
        AppLog.logPresenter(this);
        super.attachView(view);
    }

    @Override
    protected void onFirstViewAttach() {
        AppLog.logPresenter(this);
        super.onFirstViewAttach();
    }
    @Override
    public void detachView(View view) {
        clearLiteDisposable();
        AppLog.logPresenter(this);
        super.detachView(view);
    }

    @Override
    public void destroyView(View view) {

        AppLog.logPresenter(this);
        super.destroyView(view);
    }

    @Override
    public void onDestroy() {
        clearHardDisposable();
        AppLog.logPresenter(this);
        super.onDestroy();
    }


    protected String getString(@StringRes int stringId) {
        return mResourcesManager.getString(stringId);
    }

    protected final void clearDisposableIfPossible(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
    protected void addLiteDisposable(@Nullable  Disposable disposable){
        if (disposable != null) {
            mLiteCompositeDisposable.add(disposable);
        }
    }

    protected void addHardDisposable(@Nullable Disposable disposable){
        if (disposable != null) {
            mHardCompositeDisposable.add(disposable);
        }
    }

    protected void clearLiteDisposable(){
        mLiteCompositeDisposable.clear();
    }

    protected void clearHardDisposable(){
        mHardCompositeDisposable.clear();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        AppLog.logPresenter(this);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults, @NonNull BaseActivity activity) {
        AppLog.logPresenter(this);

    }







}
