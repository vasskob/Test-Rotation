package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.data.entity.maper.ShopEntityDataMapper;
import com.example.vasskob.testrotation.domain.RetrofitSingleton;
import com.example.vasskob.testrotation.domain.entity.Store;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;

import java.util.List;

import io.reactivex.Observable;


public class StoreDataRepository implements StoreRepository {

    @Override
    public Observable<List<Store>> stores() {
        return RetrofitSingleton
                .getStoreObservable()
                .map(storeCollection -> new ShopEntityDataMapper().transform(storeCollection));
    }
}
