package com.example.vasskob.testrotation.data.api;

import com.example.vasskob.testrotation.data.entity.ProductEntity;
import com.example.vasskob.testrotation.data.entity.StoreEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("stores")
    Single<ApiResponse<List<StoreEntity>>> loadStores();

    @GET("stores/{id}/products")
    Single<ApiResponse<List<ProductEntity>>> loadProductsInStore(@Path("id") long storeId);
}
