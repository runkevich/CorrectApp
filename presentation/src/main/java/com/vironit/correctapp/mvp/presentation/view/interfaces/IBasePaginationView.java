package com.vironit.correctapp.mvp.presentation.view.interfaces;

public interface IBasePaginationView extends IBaseView {

   // void dataListReceived(@Nullable List data);

   // void dataLoadingError(@StringRes int strId);

    //_____________________________________
    void showPaginationProgress();
    void hidePaginationProgress();
    void removeData();
}
