package com.example.vasskob.testrotation.global;

import android.app.Activity;
import android.app.Application;

import com.example.vasskob.testrotation.BuildConfig;
import com.example.vasskob.testrotation.data.di.DaggerDataComponent;
import com.example.vasskob.testrotation.data.di.DataComponent;
import com.example.vasskob.testrotation.global.di.AppComponent;
import com.example.vasskob.testrotation.global.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class MyApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initTimber();
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


    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
