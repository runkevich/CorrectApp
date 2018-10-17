package com.vironit.correctapp.mvp.presentation.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.ChatInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IUserAddToChatsMainView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class UserAddToChatsMainPresenter extends BasePaginationPresenter<IUserAddToChatsMainView> {

    @Inject
    Context mContext;

    @Inject
    ChatInteractor mChatInteractor;

    private Chats mChats;

    public UserAddToChatsMainPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    protected int getItemsCountPerPage() {
        return 10;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refreshData();
    }

    @Override
    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        super.loadData(totalItemCount, lastItemId);
        addPaginationDisposable(mChatInteractor.showAndAddUsers(lastItemId, getItemsCountPerPage())
                .observeOn(mUIScheduler)
                .doOnSuccess(dataElement -> {
                    setNextPageAllow(dataElement);
                    getViewState().addDataList(dataElement);
                })
                .doFinally(() -> getViewState().hidePaginationProgress())
                .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                        this));
    }

    public void setCurrentChat(@Nullable Chats chat) {
        this.mChats = chat;
    }

    public void addUserToChatMain() {
        mChatInteractor.addChatsUsersAfterChoosing(null)//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                .observeOn(mUIScheduler)
                .doOnSuccess(users -> {
                    getViewState().goToChatsMain();
                })
                .subscribe();
    }
}
