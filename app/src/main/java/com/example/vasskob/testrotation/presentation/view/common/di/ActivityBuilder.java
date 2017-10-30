package com.example.vasskob.testrotation.presentation.view.common.di;

import com.example.vasskob.testrotation.presentation.view.detail.DetailActivity;
import com.example.vasskob.testrotation.presentation.view.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract DetailActivity bindDetailActivity();

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();
}