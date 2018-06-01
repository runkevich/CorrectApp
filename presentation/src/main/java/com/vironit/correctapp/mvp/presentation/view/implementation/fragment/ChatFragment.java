package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.ChatPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IChatView;

public class ChatFragment extends BaseFragment<ChatPresenter> implements IChatView {

    @InjectPresenter
    ChatPresenter mChatPresenter;

    @Override
    protected ChatPresenter getPresenter() {
        return mChatPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_chat;
    }


    public static ChatFragment newInstance(){
        return new ChatFragment();
    }
}
