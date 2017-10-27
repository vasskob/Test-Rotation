package com.example.vasskob.testrotation.presentation.view;

interface BaseView {
    void showLoadingSuccessToast();

    void showLoadingErrorToast();

    void showConnectionFailedToast();

    void showConnectionSuccessToast();
}