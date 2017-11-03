package com.example.vasskob.testrotation.presentation.detail.di;

import com.example.vasskob.testrotation.data.repository.ProductRepositoryImpl;
import com.example.vasskob.testrotation.presentation.detail.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailActivityModule {

    @Provides
    @DetailScope
        // TODO: 03/11/17 why not abstraction ProductRepository?
    DetailPresenter provideDetailPresenter(ProductRepositoryImpl repository) {
        return new DetailPresenter(repository);
    }
}
