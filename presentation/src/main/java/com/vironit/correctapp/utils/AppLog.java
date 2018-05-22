package com.vironit.correctapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.vironit.correctapp.BuildConfig;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePresenter;

public abstract class AppLog {

    public static void logPresenter(@NonNull BasePresenter presenter){
        //TODO
    }
    public static void logActivity(@NonNull Activity activity){

    }

    private static boolean isLogEnabled(){

        return BuildConfig.DEBUG; //из своего проекта
    }

}
