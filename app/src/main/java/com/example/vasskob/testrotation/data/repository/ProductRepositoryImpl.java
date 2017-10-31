package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.api.ApiResponse;
import com.example.vasskob.testrotation.domain.model.Product;
import com.example.vasskob.testrotation.data.entity.maper.ProductEntityDataMapper;
import com.example.vasskob.testrotation.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;


public class ProductRepositoryImpl implements ProductRepository {

    private final ApiInterface mApi;
    private final ProductEntityDataMapper mProductEntityDataMapper = new ProductEntityDataMapper();

    @Inject
    public ProductRepositoryImpl(ApiInterface mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<List<Product>> getProducts(long storeId) {
        return mApi
                .loadProductsInStore(storeId)
                .map(ApiResponse::getData)
                .flatMapObservable(Observable::fromIterable)
                .map(mProductEntityDataMapper)
                .toList();
    }
}
