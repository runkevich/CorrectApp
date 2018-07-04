package com.vironit.correctapp.mvp.model.repository.dto.users;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    @NonNull
    private String userName;

    @NonNull
    private String userId;

    @Nullable
    private String firebaseToken;

    private boolean onlineStatus;

    public User() {
        userId = "";
        userName = "";
        firebaseToken = "";
        onlineStatus = false;
    }

    public User(@NonNull String userName,
                @NonNull String userId,
                @Nullable String firebaseToken,
                boolean onlineStatus) {
        this.userName = userName;
        this.userId = userId;
        this.firebaseToken = firebaseToken;
        this.onlineStatus = onlineStatus;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @Nullable
    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(@Nullable String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", firebaseToken='" + firebaseToken + '\'' +
                ", onlineStatus=" + onlineStatus +
                '}';
    }
}
