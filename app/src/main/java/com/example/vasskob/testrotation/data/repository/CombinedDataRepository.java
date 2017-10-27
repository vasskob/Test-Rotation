package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.domain.RetrofitSingleton;
import com.example.vasskob.testrotation.domain.entity.StoreVsProduct;
import com.example.vasskob.testrotation.domain.repository.StoreVsProductRepository;

import java.util.List;

import io.reactivex.Observable;

public class CombinedDataRepository implements StoreVsProductRepository {

    @Override
    public Observable<List<StoreVsProduct>> storesVsProducts() {
        return RetrofitSingleton
                .getModelsObservable();
    }
}
