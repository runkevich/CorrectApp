package com.vironit.correctapp.utils.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CorrectAppException extends Exception {

    @Nullable
    private final ErrorStatus errorStatus;
    @Nullable
    private final Integer stringResId;

    public CorrectAppException(@NonNull ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
        this.stringResId = null;
    }

    public CorrectAppException(@NonNull Integer stringResId) {
        this.stringResId = stringResId;
        this.errorStatus = null;
    }

    public CorrectAppException(@NonNull Throwable cause,
                               @NonNull ErrorStatus errorStatus) {
        super(cause);
        this.errorStatus = errorStatus;
        this.stringResId = null;
    }

    public CorrectAppException(@NonNull Integer stringResId,
                               @NonNull Throwable cause) {
        super(cause);
        this.stringResId = stringResId;
        this.errorStatus = null;
    }

    public CorrectAppException(@NonNull Throwable cause,
                               @NonNull Integer stringResId,
                               @NonNull String message) {
        super(message, cause);
        this.stringResId = stringResId;
        this.errorStatus = null;
    }

    public CorrectAppException(@NonNull ErrorStatus errorStatus,
                               @NonNull Throwable cause,
                               @NonNull String message) {
        super(message, cause);
        this.errorStatus = errorStatus;
        this.stringResId = null;
    }

    @Nullable
    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }

    @Nullable
    public Integer getStringResId() {
        return stringResId;
    }
}
