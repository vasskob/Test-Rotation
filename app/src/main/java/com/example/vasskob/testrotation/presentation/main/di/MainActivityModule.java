package com.example.vasskob.testrotation.presentation.main.di;

import com.example.vasskob.testrotation.data.repository.StoreRepositoryImpl;
import com.example.vasskob.testrotation.presentation.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainScope
    MainPresenter provideMainPresenter(StoreRepositoryImpl repository) {
        return new MainPresenter(repository);
    }
}
