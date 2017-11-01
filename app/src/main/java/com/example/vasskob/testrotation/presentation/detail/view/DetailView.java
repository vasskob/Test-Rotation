package com.example.vasskob.testrotation.presentation.detail.view;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vasskob.testrotation.domain.model.Product;
import com.example.vasskob.testrotation.presentation.common.view.BaseView;

import java.util.List;

public interface DetailView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onProductLoadSuccess();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void onProductLoadError();

    void showProducts(List<Product> productModels);
}
