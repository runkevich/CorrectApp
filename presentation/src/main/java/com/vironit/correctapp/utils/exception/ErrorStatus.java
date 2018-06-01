package com.vironit.correctapp.utils.exception;


import android.support.annotation.StringRes;

import static com.vironit.correctapp.constans.AppConstants.NO_STRING_RES;

public enum ErrorStatus {

    A(NO_STRING_RES);

    @StringRes
    private final int stringResId;

    ErrorStatus(@StringRes int stringResId) {
        this.stringResId = stringResId;
    }

    public boolean hasStringMessageInResources() {
        return stringResId != NO_STRING_RES;
    }

    @StringRes
    public int getStringResId() {
        return stringResId;
    }
}
