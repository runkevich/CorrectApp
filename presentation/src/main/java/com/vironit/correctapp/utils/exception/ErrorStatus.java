package com.vironit.correctapp.utils.exception;


import android.support.annotation.StringRes;

import static com.vironit.correctapp.constans.AppConstants.CHAT_IS_NOT_EXIST_FOR_THIS_USER;
import static com.vironit.correctapp.constans.AppConstants.NO_STRING_RES;
import static com.vironit.correctapp.constans.AppConstants.THIS_IS_NOT_USER_EXIST;
import static com.vironit.correctapp.constans.AppConstants.THIS_USER_EXIST;

public enum ErrorStatus {

    A(NO_STRING_RES,0),
    USER_EXIST(THIS_USER_EXIST,0),
    USER_IS_NOT_EXIST(THIS_IS_NOT_USER_EXIST,0),
    CHAT_IS_NOT_EXIST_FOR_USER(CHAT_IS_NOT_EXIST_FOR_THIS_USER,0);

    @StringRes
    private final int stringResId;
    private final int serverError;

    ErrorStatus(int serverError,@StringRes int stringResId) {
        this.stringResId = stringResId;
        this.serverError = serverError;
    }

    public boolean hasStringMessageInResources() {
        return stringResId != NO_STRING_RES;
    }

    @StringRes
    public int getStringResId() {
        return stringResId;
    }
}
