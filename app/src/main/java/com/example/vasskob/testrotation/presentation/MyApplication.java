package com.example.vasskob.testrotation.presentation;

import android.app.Application;

import com.example.vasskob.testrotation.domain.RetrofitSingleton;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitSingleton.init();
    }
}
