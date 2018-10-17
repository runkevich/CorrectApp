package com.vironit.correctapp.mvp.presentation.adapter.chat;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.presentation.adapter.base.BaseViewHolder;

import butterknife.BindView;

public class UserAddToChatsMainViewHolder extends BaseViewHolder<User> {

    @BindView(R.id.tv_user_name_add)
    TextView mUserName;

    @BindView(R.id.cb_add_user)
    CheckBox mCheckBox;

    public UserAddToChatsMainViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(User user) {
        mUserName.setText(user.getUserName());
        mCheckBox.setOnCheckedChangeListener(null);
        //mCheckBox.setSelected(user.);
    }
}
