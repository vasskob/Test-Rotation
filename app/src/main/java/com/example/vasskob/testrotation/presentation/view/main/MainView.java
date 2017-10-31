package com.example.vasskob.testrotation.presentation.view.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vasskob.testrotation.domain.model.Store;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface MainView extends BaseView, MvpView {

    void showShopList(List<Store> storeModels);

    @StateStrategyType(SkipStrategy.class)
    void onShopLoadSuccess();
    @StateStrategyType(SkipStrategy.class)
    void onShopLoadError();
}
