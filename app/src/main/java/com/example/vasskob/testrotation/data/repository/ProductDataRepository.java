package com.example.vasskob.testrotation.data.repository;

import com.example.vasskob.testrotation.data.api.ApiInterface;
import com.example.vasskob.testrotation.data.api.ApiResponse;
import com.example.vasskob.testrotation.domain.dto.Product;
import com.example.vasskob.testrotation.domain.dto.maper.ProductEntityDataMapper;
import com.example.vasskob.testrotation.domain.repository.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class ProductDataRepository implements ProductRepository {

    private final ApiInterface mApi;

    @Inject
    public ProductDataRepository(ApiInterface mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<List<Product>> getProducts(long storeId) {
        return mApi
                .loadProductsInStore(storeId)
                .map(ApiResponse::getData)
                .map(productEntities -> new ProductEntityDataMapper().transform(productEntities));
    }
}
