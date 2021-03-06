package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.presentation.presenter.ProfilePresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IProfileView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @OnClick(R.id.btn_load_photo_camera)
    void photo1() {
        mProfilePresenter.startCamera(this);
    }

    @OnClick(R.id.btn_load_photo_gallery)
    void photo2() {
        mProfilePresenter.startGallery(this);
    }

    @BindView(R.id.image_profile)
    ImageView mProfilePhoto;

    @BindView(R.id.tv_user_name)
    TextView mUserName;

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

    @Override
    public void setPhoto(File file) {
        Glide.with(this)
                .load(file)
                .into(mProfilePhoto);
    }

    @Override
    public void setUserName(String name) {
        mUserName.setText(name);
    }
}
