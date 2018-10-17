package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.chat.ChatsMainAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.ChatsMainPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BasePaginationFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITempView;

public class ChatsMessageFragment extends BasePaginationFragment<ChatsMainPresenter>
        implements ITempView {

   // @InjectPresenter
    ChatsMainPresenter mChatsMainPresenter;

    ChatsMainAdapter mChatsMainAdapter;

    public static ChatsMessageFragment newInstance() {
        return new ChatsMessageFragment();
    }

    @Nullable
    @Override
    protected BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter() {
        return mChatsMainAdapter;
    }

    @Override
    protected void setPaginationRecyclerAdapter() {
        mChatsMainAdapter = new ChatsMainAdapter();
        mRecyclerView.setAdapter(mChatsMainAdapter);
    }

    @Override
    protected void setLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    protected ChatsMainPresenter getPresenter() {
        return mChatsMainPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_chats_message;
    }
}
