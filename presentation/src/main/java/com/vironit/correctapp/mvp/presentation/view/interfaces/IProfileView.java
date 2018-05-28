package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

@StateStrategyType(SkipStrategy.class)
public interface IProfileView extends IBaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setPhoto(File file);
}
