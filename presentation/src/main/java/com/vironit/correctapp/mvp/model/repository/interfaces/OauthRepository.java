package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import io.reactivex.Single;

public interface OauthRepository {

    Single<User> createNewUser(String name, String email, String password);

    Single<Boolean> authorize(String nameOrEmail, String password);

    //Single<Boolean> getUserByEmailWithPassword(String email);
    //Single<Boolean> getUserByNameWithPassword(String name);

    Single<User> getUserByNameOrEmail(String name, String email);

    Single<User> getUserByEmail(String email);

    Single<User> getUserByName(String name);

    void saveThisUser(User user);

    String getThisUserId();

    String getThisUserName();

    //void saveUserToSharedPref(String nameOrEmail);

    void saveUserToSharedPref(String nameOrEmail);
}
