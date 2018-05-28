package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.HomePresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.activity.base.BaseFragmentActivity;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ChatFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.NewsFragment;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.ProfileFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IHomeView;

import butterknife.BindView;

public class HomeActivity extends BaseFragmentActivity<HomePresenter> implements IHomeView {

    @InjectPresenter
    HomePresenter mHomePresenter;


    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(),ProfileFragment.newInstance())
                .commit();
        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_profile:
                    mHomePresenter.showProfile();
                    break;
                case R.id.action_chat:
                    mHomePresenter.showChat();
                    break;
                case R.id.action_news:
                    mHomePresenter.showNews();
                    break;
            }
            return true;
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public int getRootViewResId() {
        return R.id.id_v_activity_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        return mHomePresenter;
    }

    @Override
    public void showNews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), NewsFragment.newInstance())
                .commit();
    }

    @Override
    public void showChat() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), ChatFragment.newInstance())
                .commit();
    }

    @Override
    public void showProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), ProfileFragment.newInstance())
                .commit();
    }


    @Override
    public void showMessage(@NonNull String message,
                            boolean closable,
                            @Nullable String actionMessage,
                            @Nullable IActionListener iActionListener) {

    }

    private int getContainerViewId() {
        return R.id.frame_layout;
    }
}
