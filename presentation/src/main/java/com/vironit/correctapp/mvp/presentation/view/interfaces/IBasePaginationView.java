package com.vironit.correctapp.mvp.presentation.view.interfaces;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;


public interface IBasePaginationView extends IBaseView {

    void dataListReceived(@Nullable List data);

    void dataLoadingError(@StringRes int strId);

    //boolean isRefreshing();
}
