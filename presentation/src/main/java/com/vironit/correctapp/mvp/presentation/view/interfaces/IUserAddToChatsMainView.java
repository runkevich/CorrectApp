package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import java.util.List;

public interface IUserAddToChatsMainView extends IBasePaginationView, IAddListData<User> {

    void showUsersForAdded(List<User> users);

    void addUsersToChat(List<User> users);

    void goToChatsMain();
}
