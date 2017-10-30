package com.example.vasskob.testrotation.global.di;

import android.content.Context;

import com.example.vasskob.testrotation.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(MyApplication application) {
        return application.getApplicationContext();
    }
}
