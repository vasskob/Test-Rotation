package com.example.vasskob.testrotation.presentation.main.di;

import com.example.vasskob.testrotation.domain.repository.StoreRepository;
import com.example.vasskob.testrotation.presentation.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainScope
    MainPresenter provideMainPresenter(StoreRepository repository) {
        return new MainPresenter(repository);
    }
}
