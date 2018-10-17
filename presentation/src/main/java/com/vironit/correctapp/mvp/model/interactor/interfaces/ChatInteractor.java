package com.vironit.correctapp.mvp.model.interactor.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import java.util.List;

import io.reactivex.Single;

public interface ChatInteractor {

    Single<Chats> addChat(String nameChat);

    Single<List<Chats>> showChat(String lastItemId, int totalIt);

    Single<List<User>> showAndAddUsers(String lastItemId, int totalIt);

    Single<List<User>> addChatsUsersAfterChoosing(List<String> userId);
}
