package com.example.vasskob.testrotation.presentation.view.detail;

import com.arellomobile.mvp.MvpView;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface DetailView extends BaseView, MvpView {
    void onProductLoadSuccess(List<ProductModel> list);
    void onProductLoadError();
}
