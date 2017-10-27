package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.domain.RetrofitSingleton;
import com.example.vasskob.testrotation.data.entity.maper.ProductEntityDataMapper;
import com.example.vasskob.testrotation.domain.entity.Product;
import com.example.vasskob.testrotation.domain.repository.ProductRepository;

import java.util.List;

import io.reactivex.Observable;


public class ProductDataRepository implements ProductRepository {

    @Override
    public Observable<List<Product>> products(long shopId) {
        return RetrofitSingleton
                .getProductObservable(shopId)
                .map(productCollection -> new ProductEntityDataMapper().transform(productCollection));
    }
}
