package com.example.vasskob.testrotation;

import android.app.Activity;
import android.app.Application;

import com.example.vasskob.testrotation.data.di.DaggerDataComponent;
import com.example.vasskob.testrotation.data.di.DataComponent;
import com.example.vasskob.testrotation.global.di.AppComponent;
import com.example.vasskob.testrotation.global.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        DataComponent mDataComponent = DaggerDataComponent.builder()
                .application(this)
                .build();

        AppComponent mAppComponent = DaggerAppComponent.builder()
                .dataComponent(mDataComponent)
                .build();

        mAppComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
