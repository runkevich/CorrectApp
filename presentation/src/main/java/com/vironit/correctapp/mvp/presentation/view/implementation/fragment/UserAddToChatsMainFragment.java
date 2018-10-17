package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.chat.UserAddToChatsMainAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.UserAddToChatsMainPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.ChatsMainActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BasePaginationFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IUserAddToChatsMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserAddToChatsMainFragment extends BasePaginationFragment<UserAddToChatsMainPresenter>
        implements IUserAddToChatsMainView {

    @InjectPresenter
    UserAddToChatsMainPresenter mUserAddToChatsMainPresenter;

    UserAddToChatsMainAdapter mUserAddToChatsMainAdapter;

    private Chats mChats;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;

    @Nullable
    @BindView(R.id.swipe_refresh_ly)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @OnClick(R.id.btn_add_user_to_chat)
    void addUserToChatMain() {
        mUserAddToChatsMainPresenter.addUserToChatMain();
    }

    public static UserAddToChatsMainFragment newInstance() {
        return new UserAddToChatsMainFragment();
    }

    @Nullable
    @Override
    protected BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter() {
        return mUserAddToChatsMainAdapter;
    }

    @Override
    protected void setPaginationRecyclerAdapter() {
        mUserAddToChatsMainAdapter = new UserAddToChatsMainAdapter();
        mRecyclerView.setAdapter(mUserAddToChatsMainAdapter);
    }

    @Override
    protected void setLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    protected UserAddToChatsMainPresenter getPresenter() {
        return mUserAddToChatsMainPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_user_add_to_chats_main;
    }

    @Override
    public void showUsersForAdded(List<User> users) {

    }

    @Override
    public void addUsersToChat(List<User> users) {

    }

    @Override
    public void goToChatsMain() {
        startActivity(new Intent(this.getContext(), ChatsMainActivity.class));
    }

    @Override
    protected void initFromArguments(@NonNull Bundle arg) {
        super.initFromArguments(arg);
        mChats = arg.getParcelable("KEY_FOR_INTENT_USER_ADD_CHAT");
        mUserAddToChatsMainPresenter.setCurrentChat(mChats);
    }

    @Override
    public void addDataList(List<User> dataList) {
        mUserAddToChatsMainAdapter.addData(dataList);
    }
}
