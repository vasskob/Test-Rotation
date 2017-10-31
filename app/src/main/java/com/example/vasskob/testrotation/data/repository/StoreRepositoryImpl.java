package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.api.ApiResponse;
import com.example.vasskob.testrotation.data.entity.maper.StoreEntityDataMapper;
import com.example.vasskob.testrotation.domain.model.Store;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;


public class StoreRepositoryImpl implements StoreRepository {

    private final ApiInterface mApi;
    private final StoreEntityDataMapper mStoreEntityDataMapper = new StoreEntityDataMapper();

    @Inject
    public StoreRepositoryImpl(ApiInterface mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<List<Store>> getStores() {
        return mApi
                .loadStores()
                .map(ApiResponse::getData)
                .flatMapObservable(Observable::fromIterable)
                .map(mStoreEntityDataMapper)
                .toList();
    }
}
