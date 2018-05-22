package com.vironit.correctapp.mvp.presentation.view.interfaces;

import android.support.annotation.NonNull;

public interface IProgressView {
    void showProgress(@NonNull String message);
    void showProgress();
    void hideProgress();
}
