package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IMapView;

@InjectViewState
public class MapPresenter extends BaseAppPresenter<IMapView>{

    public MapPresenter() {
        App.getsAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        showMaps();
    }

    private void showMaps() {
        getViewState().showMaps();
    }
}
