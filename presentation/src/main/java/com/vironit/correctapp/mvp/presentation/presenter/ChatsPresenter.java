package com.vironit.correctapp.mvp.presentation.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.ChatInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IChatsView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ChatsPresenter extends BasePaginationPresenter<IChatsView> {

    @Inject
    Context mContext;

    @Inject
    ChatInteractor mChatInteractor;

    public ChatsPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    protected int getItemsCountPerPage() {
        return 5;//15
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refreshData();
    }

    public void addChat(String s) {
        mChatInteractor.addChat(s)
                .doOnSuccess(chats -> getViewState().goUserAddToChatsMainActivity(chats))
                .doOnError(error -> Log.i("LOG_TAG", "Ошибка в создании чата" + error))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    protected void loadData(int totalIt, @Nullable String lastItemId) {
        super.loadData(totalIt, lastItemId);

        addPaginationDisposable(mChatInteractor.showChat(lastItemId, getItemsCountPerPage())
                .observeOn(mUIScheduler)
                .doOnSuccess(dataElement -> {
                    setNextPageAllow(dataElement);
                    getViewState().addDataList(dataElement);
                })
                .doFinally(() -> getViewState().hidePaginationProgress())
                .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                        this));
/*                mChatInteractor.showChat("1234",2)
                .doOnSuccess(s-> Log.i("LOG_TAG","имеется какой-то ч."))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/
    }
}
