package com.vironit.correctapp.mvp.model.repository.dto.users;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {


    private String userName;

    @NonNull
    private String userId;

    @Nullable
    private String firebaseToken;
    @NonNull
    private boolean onlineStatus;

    @NonNull
    private String userEmail;

    private String passwordHash;

    /*private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }*/

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User() {
       /* userId = "id";
        userName = "name";
        firebaseToken = "";
        onlineStatus = false;
        userEmail = "email";*/
    }

    public User( String userName,
                @NonNull String userId,
                @Nullable String firebaseToken,
                boolean onlineStatus,
                @NonNull String userEmail,
                @NonNull String passwordHash) {
        this.userName = userName;
        this.userId = userId;
        this.firebaseToken = firebaseToken;
        this.onlineStatus = onlineStatus;
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
    }


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
                ", userEmail='" + userEmail + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}