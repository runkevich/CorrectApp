package com.vironit.correctapp.mvp.model.manager.implementation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.vironit.correctapp.mvp.model.manager.interfaces.ResourcesManager;

import javax.inject.Inject;

public class ResourcesManagerImpl implements ResourcesManager {

    private final Context mAppContext;

    @Inject
    public ResourcesManagerImpl(Context mAppContext) {
        this.mAppContext = mAppContext;
    }

    @NonNull
    @Override
    public String getString(@StringRes int stringResId) {

        @Nullable String str = null;
        try {
            str = mAppContext.getString(stringResId);
        } catch (Exception e) {
        }
        return str != null ? str : "";
    }


    @NonNull
    @Override
    public String getString(@StringRes int resId, @NonNull Object... formatArgs) {
        @Nullable String str = null;
        try {
            str = mAppContext.getString(resId, formatArgs);
        } catch (Exception e) {
        }
        return str != null ? str : "";
    }
}

//ctr alt l
//coordinator
//dagger
//азвания придумать
//Handler
//Loop

//Stetha
//token
//иммутабельность

//ctr+k для гита ctrl+shift+k

//module - предоставляет
//components - собирает
