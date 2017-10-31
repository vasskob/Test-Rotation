package com.example.vasskob.testrotation.presentation.view.detail;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vasskob.testrotation.domain.model.Product;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface DetailView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onProductLoadSuccess();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void onProductLoadError();

    void showProducts(List<Product> productModels);
}
