package com.vironit.correctapp.mvp.presentation.adapter.chat;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.presentation.adapter.base.BasePaginationRecyclerViewAdapter;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

public class UserAddToChatsMainAdapter extends BasePaginationRecyclerViewAdapter<User,BaseViewHolder> {
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user_add_to_chats_main, parent, false);
        return new UserAddToChatsMainViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(getDataList().get(position));
    }
}
