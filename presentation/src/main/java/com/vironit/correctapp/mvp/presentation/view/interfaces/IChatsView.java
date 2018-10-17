package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vironit.correctapp.mvp.model.repository.dto.chats.Chats;

//@StateStrategyType(SkipStrategy.class)
public interface IChatsView extends IBasePaginationView, IAddListData<Chats> {

    void addChat();

    @StateStrategyType(SkipStrategy.class)
    void goUserAddToChatsMainActivity(Chats chats);
}
