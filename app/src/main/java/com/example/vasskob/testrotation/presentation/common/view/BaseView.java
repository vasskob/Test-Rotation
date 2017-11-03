package com.example.vasskob.testrotation.presentation.common.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface BaseView extends MvpView{

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onConnectionError();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void onConnectionSuccess();

    void startLoadingProgress();
    void stopLoadingProgress();
}