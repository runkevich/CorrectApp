package com.vironit.correctapp.mvp.presentation.view.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IBaseView  extends MvpView,IMessageView,IProgressView {
    void hideKeyboard();
    void cancelScreen();

    void showDialogMessage(@NonNull String message, boolean closable, boolean cancelable);

    void showMessage(@NonNull String message, boolean closable, @Nullable String actionMessage,
                     @Nullable View.OnClickListener actionListener);
}
