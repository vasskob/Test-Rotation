package com.example.vasskob.testrotation.presentation.view.detail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface DetailView extends BaseView, MvpView {

    void showProducts(List<ProductModel> productModels);

    @StateStrategyType(SkipStrategy.class)
    void onProductLoadSuccess();
    @StateStrategyType(SkipStrategy.class)
    void onProductLoadError();
}
