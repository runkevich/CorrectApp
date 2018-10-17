package com.vironit.correctapp.mvp.model.interactor.implementation;

import android.util.Log;

import com.vironit.correctapp.mvp.model.interactor.interfaces.ChatInteractor;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.model.repository.interfaces.ChatRepository;
import com.vironit.correctapp.mvp.model.repository.interfaces.OauthRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ChatInteractorImpl implements ChatInteractor {

    private final ChatRepository mChatRepository;
    private final OauthRepository mOauthRepository;

    @Inject
    public ChatInteractorImpl(ChatRepository mChatRepository,
                              OauthRepository mOauthRepository) {
        this.mChatRepository = mChatRepository;
        this.mOauthRepository = mOauthRepository;
    }

    @Override
    public Single<Chats> addChat(String chatName) {
        return mChatRepository.addChatToUser(chatName,String.valueOf(mChatRepository.getUser()))
                .doOnError(throwable -> Log.i("LOG_TAG", "Error from ChatInteractorImpl - addChat"));
    }

    @Override
    public Single<List<Chats>> showChat(String lastItemId, int totalIt) {
        return mChatRepository.getChatOfUser(String.valueOf(mChatRepository.getUser()), lastItemId, totalIt)
                .doOnError(throwable -> Log.i("LOG_TAG", "Error from ChatInteractorImpl - don't get chat for user."));
    }

    @Override
    public Single<List<User>> showAndAddUsers(String lastItemId, int totalIt) {
        return mChatRepository.getUsersForAddedToChat(lastItemId, totalIt)
                .doOnError(throwable -> Log.i("LOG_TAG", "Error from ChatInteractorImpl - showAndAddUsers"));
    }

    @Override
    public Single<List<User>> addChatsUsersAfterChoosing(List<String> userId) {
        return mChatRepository.addUsersToChats()
                .doOnError(throwable -> Log.i("LOG_TAG", "Error from ChatInteractorImpl - addChatsUsersAfterChoosing"));
    }
}
