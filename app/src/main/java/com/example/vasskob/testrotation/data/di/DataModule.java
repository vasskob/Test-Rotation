package com.example.vasskob.testrotation.data.di;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.repository.ProductRepositoryImpl;
import com.example.vasskob.testrotation.data.repository.StoreRepositoryImpl;
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
        return new StoreRepositoryImpl(apiInterface);
    }

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ApiInterface apiInterface) {
        return new ProductRepositoryImpl(apiInterface);
    }
}
