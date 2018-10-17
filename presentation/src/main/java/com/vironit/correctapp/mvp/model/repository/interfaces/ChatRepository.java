package com.vironit.correctapp.mvp.model.repository.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import java.util.List;

import io.reactivex.Single;

public interface ChatRepository {

    //Single<Chats> addChatToUser(String chatName,String userId);
    Single<List<Chats>> getChatOfUser(String userId, String startAt, int limit);

    Single<Chats> addChatToUser(String chatName, String userId);

    Single<List<Chats>> getUserByChat(String lastItemId, int totalIt);

    String getUser();

    Single<List<User>> getUsersForAddedToChat(String lastItemId, int totalIt);

    Single<List<User>> addUsersToChats();
}
