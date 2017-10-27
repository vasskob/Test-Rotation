package com.example.vasskob.testrotation.presentation.view;


import com.example.vasskob.testrotation.presentation.model.SpecialStoreModel;

import java.util.List;

public interface MainView extends BaseView {
    void showStoreList(List<SpecialStoreModel> list);
}
