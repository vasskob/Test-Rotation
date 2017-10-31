package com.example.vasskob.testrotation.presentation.view.main;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vasskob.testrotation.domain.model.Store;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface MainView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onShopLoadSuccess();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void onShopLoadError();

    void showShopList(List<Store> storeModels);

}
