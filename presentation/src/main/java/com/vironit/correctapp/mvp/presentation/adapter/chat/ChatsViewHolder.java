package com.vironit.correctapp.mvp.presentation.adapter.chat;

import android.view.View;
import android.widget.TextView;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

import butterknife.BindView;

public class ChatsViewHolder extends BaseViewHolder<Chats> {

    @BindView(R.id.chat_name)
    TextView mChatName;


    public ChatsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(Chats chats) {
        mChatName.setText(chats.getChatName());
    }
}
