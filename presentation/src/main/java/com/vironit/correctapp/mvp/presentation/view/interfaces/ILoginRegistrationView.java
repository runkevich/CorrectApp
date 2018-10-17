package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface ILoginRegistrationView extends IBaseView{

    @StateStrategyType(OneExecutionStateStrategy.class)
    void goToHome();
}
