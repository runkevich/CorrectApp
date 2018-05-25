package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.ProfilePresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IProfileView;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @Override
    protected ProfilePresenter getPresenter() {
        return mProfilePresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_profile;
    }


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
}
