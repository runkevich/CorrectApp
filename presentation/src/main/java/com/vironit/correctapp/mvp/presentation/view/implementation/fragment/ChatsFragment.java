package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.chat.ChatsAdapter;
import com.vironit.correctapp.mvp.presentation.presenter.ChatsPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.UserAddToChatsMainActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BasePaginationFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IChatsView;
import com.vironit.correctapp.utils.ShowDialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatsFragment extends BasePaginationFragment<ChatsPresenter>
        implements IChatsView {

    @InjectPresenter
    ChatsPresenter mChatsPresenter;

    ChatsAdapter mChatsAdapter;

    EditText inputNameChat;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_ly)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @OnClick(R.id.btn_add_chat)
    public void addChat() {
        inputNameChat = new EditText(this.getContext());
        ShowDialogUtil.showMessageDialogWithEditTextInput(this.getActivity(),
                "Создание чата.",
                "Вы хотите создать новый чат?",
                "Ок?",
                "Отмена.",
                inputNameChat,
                (v2, v1) -> mChatsPresenter.addChat(inputNameChat.getText().toString()),
                (v1, v2) -> Toast.makeText(getContext(), "Отмена в создании чата.", Toast.LENGTH_SHORT).show()
                , true);
    }

    @Override
    public void goUserAddToChatsMainActivity(Chats chats) {
        startActivity(new Intent(this.getContext(), UserAddToChatsMainActivity.class));
    }

    @Override
    protected void initViewBeforePresenterAttach() {
        super.initViewBeforePresenterAttach();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_chat;
    }

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Nullable
    @Override
    protected BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter() {
        return mChatsAdapter;
    }

    @Override
    protected void setPaginationRecyclerAdapter() {
        mChatsAdapter = new ChatsAdapter();
        mRecyclerView.setAdapter(mChatsAdapter);
    }

    @Override
    protected void setLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    protected ChatsPresenter getPresenter() {
        return mChatsPresenter;
    }

    @Override
    public void addDataList(List<Chats> dataList) {
        mChatsAdapter.addData(dataList);
    }
    //addDataList
}
