package com.vironit.correctapp.mvp.presentation.view.interfaces;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface IMessageView {
    void showAutoClosableMessage(@NonNull String message);
    void showDialogMessage(@NonNull String message, boolean closable, boolean cancelable);
    void showDialogWithOptions(@NonNull String title,
                               @NonNull String message,
                               @NonNull String positiveOptionMessage,
                               @NonNull String negative,
                               @Nullable DialogInterface.OnClickListener  positiveListener,
                               @Nullable DialogInterface.OnClickListener  negativeListener);
    void hideDialogMessage();
    void hideMessage();

    void showMessage(@NonNull String message,
                     boolean closable,
                     @Nullable String actionMessage,
                     @Nullable IMessageView.IActionListener iActionListener);

    interface IActionListener{

        void onActionChoosing();

    }
}
