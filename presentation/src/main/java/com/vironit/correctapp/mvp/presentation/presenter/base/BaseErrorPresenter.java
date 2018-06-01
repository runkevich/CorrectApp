package com.vironit.correctapp.mvp.presentation.presenter.base;

import android.support.annotation.Nullable;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IBaseView;
import com.vironit.correctapp.utils.AppLog;
import com.vironit.correctapp.utils.exception.CorrectAppException;

import io.reactivex.functions.Consumer;

public abstract class BaseErrorPresenter<View extends IBaseView> extends BasePresenter<View>
        implements Consumer<Throwable> {

    @Override
    public final void accept(@Nullable Throwable throwable) throws Exception {
        if (throwable != null) {
            throwable.printStackTrace();
        }
        AppLog.logPresenter(this, "ERROR_HUNDLE", throwable);

        defaultErrorHandle(throwable);
    }

    private void defaultErrorHandle(@Nullable Throwable throwable) {
        if (throwable != null && throwable instanceof CorrectAppException) {
            CorrectAppException correctAppException = (CorrectAppException) throwable;
            if (correctAppException.getErrorStatus() != null) {
                //switch ()
            } else if (correctAppException.getStringResId() != null) {
                getViewState().showAutoClosableMessage(getString(correctAppException.getStringResId()));
            }
        }
    }

    protected void handleUnNollError(@Nullable Throwable throwable) {
        getViewState().showAutoClosableMessage(getString(R.string.unknown_error));
    }
}
