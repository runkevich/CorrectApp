package com.vironit.correctapp.mvp.presentation.view.interfaces;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface IMessageWithInputView  {
    void showDialogWithOptionsEditText(@NonNull String title,
                               @NonNull String message,
                               @NonNull String positiveOptionMessage,
                               @NonNull String negative,
                               @Nullable View view,
                               @Nullable DialogInterface.OnClickListener positiveListener,
                               @Nullable DialogInterface.OnClickListener negativeListener);

}
