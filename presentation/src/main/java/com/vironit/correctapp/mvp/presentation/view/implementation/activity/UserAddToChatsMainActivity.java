package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.TempPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseFragmentActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.UserAddToChatsMainFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.ITempView;

public class UserAddToChatsMainActivity extends BaseFragmentActivity<TempPresenter> implements ITempView {

    @InjectPresenter
    TempPresenter mTempPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_user_add_to_chat_main;
    }

    @Override
    public int getRootViewResId() {
        return R.id.r_v_user_add_to_chat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getRootViewResId(),UserAddToChatsMainFragment.newInstance())
                .commit();
    }

    @Override
    protected TempPresenter getPresenter() {
        return mTempPresenter;
    }

    @Override
    protected void initViewBeforePresenterAttach() {
        super.initViewBeforePresenterAttach();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(getRootViewResId(),UserAddToChatsMainFragment.newInstance())
                .commit();
    }

    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {

    }
}
