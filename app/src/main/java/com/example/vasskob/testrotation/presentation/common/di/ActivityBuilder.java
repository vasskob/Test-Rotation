package com.example.vasskob.testrotation.presentation.common.di;

import com.example.vasskob.testrotation.presentation.detail.di.DetailActivityModule;
import com.example.vasskob.testrotation.presentation.detail.di.DetailScope;
import com.example.vasskob.testrotation.presentation.detail.view.DetailActivity;
import com.example.vasskob.testrotation.presentation.main.di.MainActivityModule;
import com.example.vasskob.testrotation.presentation.main.di.MainScope;
import com.example.vasskob.testrotation.presentation.main.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @MainScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @DetailScope
    @ContributesAndroidInjector(modules = DetailActivityModule.class)
    abstract DetailActivity bindDetailActivity();
}