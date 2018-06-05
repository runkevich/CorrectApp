package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IHomeView;

@InjectViewState
public class HomePresenter extends BaseAppPresenter<IHomeView> {

    public HomePresenter() {
        App.getsAppComponent().inject(this);
    }

    public void showProfile() {
        getViewState().showProfile();
    }

    public void showNews(){
        getViewState().showNews();
    }

    public void showChat(){
        getViewState().showChat();
     }
}
