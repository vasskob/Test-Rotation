package com.example.vasskob.testrotation.data.di;

import com.example.vasskob.testrotation.domain.repository.ProductRepository;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;
import com.example.vasskob.testrotation.global.MyApplication;
import com.example.vasskob.testrotation.global.di.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DataModule.class})

public interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MyApplication application);

        DataComponent build();
    }

    StoreRepository getStoreRepository();

    ProductRepository getProductRepository();
}
