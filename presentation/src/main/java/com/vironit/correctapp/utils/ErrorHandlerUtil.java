package com.vironit.correctapp.utils;

import android.support.annotation.Nullable;

import com.vironit.correctapp.R;
import com.vironit.correctapp.utils.exception.CorrectAppException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;

public abstract class ErrorHandlerUtil {

    public static <T> Single<T> defaultHandle(@Nullable Throwable throwable) {
        if (throwable == null) {
            return Single.error(new CorrectAppException(R.string.unknown_error));
        } else if (throwable instanceof CorrectAppException) {
            return Single.error(throwable);
        } else if (isNoNetworkException(throwable)) {
            return Single.error(new CorrectAppException(R.string.no_network));
        } else {
            return Single.error(new CorrectAppException(R.string.unknown_error));
        }
    }

    private static boolean isNoNetworkException(@Nullable Throwable throwable) {
        return throwable != null
                && (throwable instanceof SocketTimeoutException
                || throwable instanceof ConnectException
                || throwable instanceof UnknownHostException);
    }

}
