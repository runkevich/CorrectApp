package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IChatView;

@InjectViewState
public class ChatPresenter extends BaseAppPresenter<IChatView>{

    public ChatPresenter() {
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
