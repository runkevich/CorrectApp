package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class NewsPresenter extends BaseAppPresenter<INewsView> {

    @Inject
    NewsInteractor mNewsInteractor;

    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(INewsView view) {
        super.attachView(view);
        loadNews();
    }

    private void loadNews() {
        getViewState().showProgress();
        addLiteDisposable(mNewsInteractor.getNews()
                .observeOn(mUIScheduler)
                .doOnSuccess(list -> {
                })
                .doFinally(() -> getViewState().hideProgress())
                .subscribe(list -> AppLog.logPresenter(this,"OOOOOKKKKK"),
                        this));
        //throwable -> AppLog.logPresenter(this))
    }
}
