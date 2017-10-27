package com.example.vasskob.testrotation.data.api;


import com.example.vasskob.testrotation.data.entity.ProductEntity;
import com.example.vasskob.testrotation.data.entity.StoreEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface ApiService {
    String LCBO_API_KEY = "MDoxZTlhNDkwMi1mM2FjLTExZTYtYWYwNi0wYmFlYTdiMTUyNjY6MjNxY24zdklFV2lhME5NVzZhYnNydWg4MXpsVlBUVk5OM2VY";
    String AUTH_TOKEN = "Authorization: Token " + LCBO_API_KEY;

    @GET("/stores")
    @Headers(AUTH_TOKEN)
    Observable<ApiResponse<List<StoreEntity>>> loadStores();

    @GET("/products")
    @Headers(AUTH_TOKEN)
    Observable<ApiResponse<List<ProductEntity>>> loadAllProducts();

    @GET("/stores/{id}/products")
    @Headers(AUTH_TOKEN)
    Observable<ApiResponse<List<ProductEntity>>> loadProductsInStore(@Path("id") long storeId);
}
