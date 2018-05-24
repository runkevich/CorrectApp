package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface ILoginView extends IBaseView {
    void showFailMessage();

    void showSuccesMessage();


    //void checkRegistration();

}
