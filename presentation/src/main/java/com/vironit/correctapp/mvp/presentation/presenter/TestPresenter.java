package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITestView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class TestPresenter extends BaseAppPresenter<ITestView> {

    public TestPresenter() {
        App.getsAppComponent().inject(this);
    }


    @Override
    public void attachView(ITestView view) {
        super.attachView(view);
        getViewState().showProgress();
        Single.just(1)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe((Integer as) ->{
                   getViewState().hideProgress();

                },throwable -> throwable.printStackTrace());
    }
    public void asdas(){
        getViewState().hideProgress();
    }

    public void onlineStatus(){
        addLiteDisposable(Single.timer(10,TimeUnit.MINUTES)
        .subscribe());
    }
}
