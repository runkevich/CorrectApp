package com.vironit.correctapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.vironit.correctapp.BuildConfig;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePresenter;

public abstract class AppLog {

    private static String APP_TAG = "APP_TAG";

    public static void logPresenter(@NonNull BasePresenter presenter) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(presenter));
        }
    }

    public static void logPresenter(@NonNull BasePresenter presenter,
                                    @Nullable String message) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), message);
        }
    }

    public static void logPresenter(@NonNull BasePresenter presenter,
                                    @Nullable String message,
                                    @Nullable Throwable throwable) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), createMessageFromThrowableWithMessage(message, createMessageFromThrowable(throwable)));
        }
    }

    @NonNull
    private static String createMessageFromThrowableWithMessage(@Nullable String message,
                                                                @Nullable String messageFromThrowable) {
        return messageFromThrowable + " " + message;
    }

    public static void logActivity(@NonNull Activity activity) {

        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(activity));
        }
    }

    public static void logObject(@NonNull Object object) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(object));
        }
    }

    public static void logObject(Class clazz, @Nullable String message) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), clazz.getSimpleName() + "." + getMethodName() + " " + createMessage(message));
        }
    }

    public static void logObject(@NonNull Object object, String message, Exception exception) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(object));
        }
    }

    @NonNull
    private static String createMessage(@Nullable String message) {
        return !TextUtils.isEmpty(message) ? message : "TROLOLO";
    }

    private static boolean isLogEnabled() {
        return BuildConfig.DEBUG;
    }

    private static String getMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getMethodName();
        } catch (Exception e) {
            return "unknownMethod";
        }
    }

    @NonNull
    private static String createMessageFromThrowable(@Nullable Throwable throwable) {
        return throwable != null
                ? (createMessageFromThrowableWithMessage(createMessage(throwable.getMessage()), throwable.getClass().getName()))
                : "nullable_throwable";
    }

    private static String getClassName(Object object) {
        return object.getClass().getName();
    }


    private static String getInfo(Object object) {
        return "Class" + getClassName(object) + "Method: " + getMethodName();
    }

    public static void logFragment(@NonNull Fragment fragment) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), "MY_APP_TAG");
        }
    }

    private static String getAppTag() {
        return APP_TAG;
    }
}


