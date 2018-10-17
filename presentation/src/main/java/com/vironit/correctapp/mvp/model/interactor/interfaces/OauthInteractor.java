package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import io.reactivex.Single;

public interface OauthInteractor {

    Single<User> register(String name, String email, String password);

    Single<Boolean> authorize(String nameOrEmail, String password);

    //Single<User> authorize(String nameOrEmail, String password);
    //boolean isLogin();
    //boolean hasLocalToken();
    //changeOnlineStatus()
    //change Firebase()
}