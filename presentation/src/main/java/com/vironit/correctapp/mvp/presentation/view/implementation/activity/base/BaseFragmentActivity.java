package com.vironit.correctapp.mvp.presentation.view.implementation.activity.base;

import android.support.v4.app.Fragment;

import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseFragmentActivity<P extends BaseAppPresenter> extends BaseActivity<P>
        implements HasSupportFragmentInjector{

    @Inject
    DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }
}
