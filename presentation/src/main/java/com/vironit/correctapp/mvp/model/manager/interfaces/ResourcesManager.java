package com.vironit.correctapp.mvp.model.manager.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface ResourcesManager {

    @NonNull
    String getString(@StringRes int stringResId);

    @NonNull
    String getString(@StringRes int resId, @NonNull Object... formatArgs);
}
