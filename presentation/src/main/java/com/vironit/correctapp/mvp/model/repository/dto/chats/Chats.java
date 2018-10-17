package com.vironit.correctapp.mvp.model.repository.dto.chats;

import android.os.Parcel;
import android.os.Parcelable;

public class Chats implements Parcelable{

    private String chatId;

    private String chatName;

   private long messageTime;

    public Chats() {
    }

    public Chats(String chatId, String chatName, long messageTime) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.messageTime = messageTime;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public long getLastMessageTime() {
        return messageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.messageTime = lastMessageTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chatId);
        dest.writeString(chatName);
        dest.writeLong(messageTime);
    }
}
