package com.vironit.correctapp.mvp.presentation.adapter.chat;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

public class ChatsMainAdapter extends BasePaginationRecyclerViewAdapter<Chats,BaseViewHolder> {
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }
}
