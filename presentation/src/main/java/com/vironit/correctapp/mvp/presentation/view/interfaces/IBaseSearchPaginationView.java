package com.vironit.correctapp.mvp.presentation.view.interfaces;

import io.reactivex.Observable;

public interface IBaseSearchPaginationView extends IBasePaginationView{

    void changeText();
    Observable<String> request(String query);
    void showResponse(String result);
}
