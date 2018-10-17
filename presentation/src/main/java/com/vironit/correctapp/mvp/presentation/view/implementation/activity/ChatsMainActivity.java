package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.TempPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseFragmentActivity;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITempView;

public class ChatsMainActivity extends BaseFragmentActivity<TempPresenter> implements ITempView {

    @InjectPresenter
    TempPresenter mTempPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chats_main;
    }

    @Override
    public int getRootViewResId() {
        return  R.id.rv_chats_message;
    }

    @Override
    protected TempPresenter getPresenter() {
        return mTempPresenter;
    }

    @Override
    public void showMessage(@NonNull String message, boolean closable, @Nullable String actionMessage, @Nullable IActionListener iActionListener) {

    }

    /*@InjectPresenter
    ChatsMainPresenter mChatsMainPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chats_main;
    }

    @Override
    public int getRootViewResId() {
        return R.id.rv_chats_message;
    }

    @Override
    protected ChatsMainPresenter getPresenter() {
        return mChatsMainPresenter;
    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {

    }

    @Override
    public void addDataList(List<Message> dataList) {

    }

    @Override
    public void showPaginationProgress() {

    }

    @Override
    public void hidePaginationProgress() {

    }

    @Override
    public void removeData() {

    }*/
}
