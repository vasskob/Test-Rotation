package com.example.vasskob.testrotation.presentation.view.main;

import com.arellomobile.mvp.MvpView;
import com.example.vasskob.testrotation.presentation.model.StoreModel;
import com.example.vasskob.testrotation.presentation.view.common.BaseView;

import java.util.List;

public interface MainView extends BaseView, MvpView {
    void onShopLoadSuccess(List<StoreModel> list);
    void onShopLoadError();
}
