package com.vironit.correctapp.mvp.model.repository.implementation;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vironit.correctapp.constans.AppConstants;
import com.vironit.correctapp.constans.FirebaseConstants;
import com.vironit.correctapp.constans.SharedPreferencesConstants;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;
import com.vironit.correctapp.mvp.model.repository.interfaces.ChatRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChatRepositoryImpl implements ChatRepository {

    private Scheduler mScheduler;

    private DatabaseReference mDatabaseReferenceChats = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.CHATS_JSON);
    private DatabaseReference mDatabaseReferenceUsersToChat = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.USERS_TO_CHATS);
    private DatabaseReference mDatabaseReferenceChatToUsers = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.CHATS_TO_USERS);
    private DatabaseReference mDatabaseReferenceUsers = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.USERS_JSON);
    private SharedPreferences mSharedPreferences;

    @Inject
    public ChatRepositoryImpl(@Named(AppConstants.IO_SCHEDULER) Scheduler mScheduler,
                              SharedPreferences mSharedPreferences) {
        this.mScheduler = mScheduler;
        this.mSharedPreferences = mSharedPreferences;
    }

    public Single<List<Chats>> getChatOfUser(String userId, String startAt, int limit) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReferenceUsersToChat
                .child(userId)
                .orderByValue()
                .startAt(startAt)
                .limitToFirst(startAt == null ? limit : limit + 1), DataSnapshot::getChildren)
                .toSingle()
                .map(list -> {
                    List<String> listsChatId = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : list) {
                        listsChatId.add(String.valueOf(dataSnapshot.getKey()));
                    }
                    return listsChatId;
                })
                .flatMap(listsChatId -> getListChats(listsChatId))
                .doOnError(error -> Log.i("LOG_TAG", error.getMessage() + " getChatOfUser"))
                .doOnSuccess(s -> Log.i("LOG_TAG", "Успешно получен чат"))
                .subscribeOn(mScheduler);
    }

    public Single<Chats> getChat(String chatId) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReferenceChats
                .orderByChild(FirebaseConstants.CHAT_ID)
                .equalTo(chatId))
                .toSingle()
                .map(dataSnapshot -> dataSnapshot.getChildren().iterator().next().getValue(Chats.class))
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage() + " getChat - from map"))
                .subscribeOn(mScheduler);
    }

    public Single<List<Chats>> getListChats(List<String> chatIds) {
        return Observable.fromIterable(chatIds)
                .map(this::getChat)
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage()))
                .toList()
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage()))
                .flatMap(singles -> Single.zip(singles, array -> {
                    List<Chats> chats = new ArrayList<>();
                    for (Object object : array) {
                        chats.add((Chats) object);
                    }
                    return chats;
                }))
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage() + " getListChats - from map"));
    }

    public Single<Chats> addChatToUser(String chatName, String userId) {
        return Single.just(mDatabaseReferenceChats)
                .subscribeOn(mScheduler)
                .map(ref -> ref.push().getKey())
                .flatMap(key -> {
                    Chats chats = new Chats(key, chatName, 123);
                    return RxFirebaseDatabase.setValue(mDatabaseReferenceChats.child(key),
                            chats)
                            .toSingle(() -> chats);
                })
                /*.flatMap(chats -> {

                  return RxFirebaseDatabase.setValue(mDatabaseReferenceChatToUsers.child(chats.getChatId()),userId)
                          .toSingle(() -> chats);
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(ref -> Log.i("LOG_TAG", "YEP"))
                .doOnError(ref -> Log.i(
                        "LOG_TAG", "NOOOO"));
    }

    public Single<List<Chats>> getUserByChat(String startAt, int limit) {
        return null;
    }

    @Override
    public String getUser() {
        return mSharedPreferences.getString(SharedPreferencesConstants.USER_ID, "");
    }


    @Override
    public Single<List<User>> getUsersForAddedToChat(String lastItemId, int totalIt) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReferenceUsers
                , DataSnapshot::getChildren)
                .toSingle()
                .map(list -> {
                    List<String> listsUsersId = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : list) {
                        listsUsersId.add(String.valueOf(dataSnapshot.getKey()));
                        //listsUsersId.add(dataSnapshot.getValue(User.class));
                    }
                    Log.i("LOG_TAG", listsUsersId.toString());
                    return listsUsersId;
                })
                .flatMap(listsUsersId -> getListUsers(listsUsersId))
                .doOnError(error -> Log.i("LOG_TAG", error.getMessage() + " getChatOfUser"))
                .doOnSuccess(s -> Log.i("LOG_TAG", "Все прошло прелестно"))
                .subscribeOn(mScheduler);
    }

    public Single<List<User>> getListUsers(List<String> usersIds) {
        return Observable.fromIterable(usersIds)
                .map(this::getUsers)
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage()))
                .toList()
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage()))
                .flatMap(singles -> Single.zip(singles, array -> {
                    List<User> users = new ArrayList<>();
                    for (Object object : array) {
                        users.add((User) object);
                    }
                    return users;
                }))
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage() + " getListChats - from map"));
    }

    public Single<User> getUsers(String usersIds) {
        return RxFirebaseDatabase.observeSingleValueEvent(mDatabaseReferenceUsers
                .orderByChild(FirebaseConstants.USER_ID)
                .equalTo(usersIds))
                .toSingle()
                .map(dataSnapshot -> dataSnapshot.getChildren().iterator().next().getValue(User.class))
                .doOnError(e -> Log.i("LOG_TAG", e.getMessage() + " getChat - from map"))
                .subscribeOn(mScheduler);
    }

    @Override
    public Single<List<User>> addUsersToChats() {
        return null;
               /* Single.just(mDatabaseReferenceUsersToChat)
                .subscribeOn(mScheduler)
                .map(ref -> ref.push().getKey())
                .flatMap(key -> {
                    Chats chats = new Chats(key, "", 123);

                    return RxFirebaseDatabase.setValue(mDatabaseReferenceUsersToChat.child(key),
                            chats)
                            .toSingle(() -> user);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(ref -> Log.i("LOG_TAG", "YEP"))
                .doOnError(ref -> Log.i(
                        "LOG_TAG", "NOOOO"));*/
    }
}

