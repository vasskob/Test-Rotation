package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.api.ApiResponse;
import com.example.vasskob.testrotation.domain.dto.Store;
import com.example.vasskob.testrotation.domain.dto.maper.ShopEntityDataMapper;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class StoreDataRepository implements StoreRepository {

    private final ApiInterface mApi;

    @Inject
    public StoreDataRepository(ApiInterface mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<List<Store>> getStores() {
        return mApi
                .loadStores()
                .map(ApiResponse::getData)
                .map(storeEntities -> new ShopEntityDataMapper().transform(storeEntities));
    }
}
