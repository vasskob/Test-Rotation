package com.example.vasskob.testrotation.data.di;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.repository.ProductDataRepository;
import com.example.vasskob.testrotation.data.repository.StoreDataRepository;
import com.example.vasskob.testrotation.domain.repository.ProductRepository;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DataModule {

    @Provides
    @Singleton
    StoreRepository provideStoreRepository(ApiInterface apiInterface) {
        return new StoreDataRepository(apiInterface);
    }

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ApiInterface apiInterface) {
        return new ProductDataRepository(apiInterface);
    }
}
