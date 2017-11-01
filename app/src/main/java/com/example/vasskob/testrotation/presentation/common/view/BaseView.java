package com.example.vasskob.testrotation.presentation.common.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface BaseView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void showConnectionFailedToast();
    @StateStrategyType(SkipStrategy.class)
    void showConnectionSuccessToast();

    void startLoadingProgress();
    void stopLoadingProgress();
}