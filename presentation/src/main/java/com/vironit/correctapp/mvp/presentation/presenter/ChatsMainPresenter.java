package com.vironit.correctapp.mvp.presentation.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.ChatInteractor;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IChatsMessageView;

import javax.inject.Inject;

@InjectViewState
public class ChatsMainPresenter extends BasePaginationPresenter<IChatsMessageView> {

    @Inject
    Context mContext;

    @Inject
    ChatInteractor mChatInteractor;

    public ChatsMainPresenter() {
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
    }
}
