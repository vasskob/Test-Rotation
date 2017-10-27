package com.example.vasskob.testrotation.presentation.view;

import com.example.vasskob.testrotation.presentation.model.ProductModel;

import java.util.List;

public interface DetailView extends BaseView {
    void showProductList(List<ProductModel> list);
}
