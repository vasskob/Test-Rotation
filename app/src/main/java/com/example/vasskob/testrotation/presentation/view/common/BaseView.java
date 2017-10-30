package com.example.vasskob.testrotation.presentation.view.common;

public interface BaseView {

    void startLoadingProgress();
    void stopLoadingProgress();
    void showConnectionFailedToast();
    void showConnectionSuccessToast();
}