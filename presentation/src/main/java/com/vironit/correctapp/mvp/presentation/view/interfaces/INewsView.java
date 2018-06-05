package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface INewsView extends IBasePaginationView {

    //void showNews();
    //void dataReceived(boolean isAfterRefresh, @Nullable Data data);
}
