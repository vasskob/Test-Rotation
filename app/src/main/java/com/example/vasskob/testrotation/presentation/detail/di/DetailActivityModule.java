package com.example.vasskob.testrotation.presentation.detail.di;

import com.example.vasskob.testrotation.domain.repository.ProductRepository;
import com.example.vasskob.testrotation.presentation.detail.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailActivityModule {

    @Provides
    @DetailScope
    DetailPresenter provideDetailPresenter(ProductRepository repository) {
        return new DetailPresenter(repository);
    }
}
