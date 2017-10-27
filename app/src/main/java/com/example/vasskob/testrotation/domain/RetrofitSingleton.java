package com.example.vasskob.testrotation.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.vasskob.testrotation.data.api.ApiResponse;
import com.example.vasskob.testrotation.data.api.ApiService;
import com.example.vasskob.testrotation.data.entity.ProductEntity;
import com.example.vasskob.testrotation.data.entity.StoreEntity;
import com.example.vasskob.testrotation.data.entity.maper.ProductEntityDataMapper;
import com.example.vasskob.testrotation.data.entity.maper.ShopEntityDataMapper;
import com.example.vasskob.testrotation.domain.entity.StoreVsProduct;
import com.example.vasskob.testrotation.presentation.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

public class RetrofitSingleton {
    private static final String TAG = RetrofitSingleton.class.getSimpleName();

    private static ReplaySubject<List<StoreVsProduct>> observableModelsList;
    private static ReplaySubject<List<StoreEntity>> observableStoreList;
    private static ReplaySubject<List<ProductEntity>> observableProductsList;
    private static Subscription subscriptionCombine;
    private static Subscription subscriptionProductsById;
    private static Subscription subscriptionStore;
    private static Observable<ApiResponse<List<StoreEntity>>> observableStore;
    private static Observable<List<StoreVsProduct>> combined;
    private static ApiService mService;

    private RetrofitSingleton() {
    }

    public static void init() {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        mService = retrofit.create(ApiService.class);
        observableStore = mService.loadStores();
        Observable<ApiResponse<List<ProductEntity>>> observableProduct = mService.loadAllProducts();

        combined = Observable.zip(observableStore, observableProduct, RetrofitSingleton::getStoreVsProducts);
    }

    @NonNull
    private static List<StoreVsProduct> getStoreVsProducts(ApiResponse<List<StoreEntity>> listApiResponse, ApiResponse<List<ProductEntity>> listApiResponse2) {
        List<StoreVsProduct> storeVsProductList = new ArrayList<>();
        Random r = new Random();
        List<ProductEntity> products = listApiResponse2.getData();
        for (StoreEntity store : listApiResponse.getData()) {
            int index = r.nextInt(products.size());
            storeVsProductList.add(new StoreVsProduct(
                    new ShopEntityDataMapper().transform(store),
                    new ProductEntityDataMapper().transform(products.get(index))));
        }
        return storeVsProductList;
    }

    /////////////////////// -------- COMBINED ------------/////////////////
    public static Observable<List<StoreVsProduct>> getModelsObservable() {
        if (observableModelsList == null) {
            resetModelsObservable();
        }
        return observableModelsList;
    }

    private static void resetModelsObservable() {
        observableModelsList = ReplaySubject.create();

        if (subscriptionCombine != null && !subscriptionCombine.isUnsubscribed()) {
            subscriptionCombine.unsubscribe();
        }

        subscriptionCombine = combined.subscribe(new Subscriber<List<StoreVsProduct>>() {
            @Override
            public void onCompleted() {
                observableModelsList.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                observableModelsList.onError(e);
            }

            @Override
            public void onNext(List<StoreVsProduct> listApiResponse) {
                for (StoreVsProduct store : listApiResponse) {
                    Log.d(TAG, "onNext: " + store.getStore().getName());
                }
                observableModelsList.onNext(listApiResponse);
            }
        });
    }

    /////////////////////// -------- PRODUCT ------------/////////////////
    public static Observable<List<ProductEntity>> getProductObservable(long id) {
        if (observableProductsList == null) {
            resetProductsObservable(id);
        }
        return observableProductsList;
    }

    private static void resetProductsObservable(long id) {
        observableProductsList = ReplaySubject.create();

        if (subscriptionProductsById != null && !subscriptionProductsById.isUnsubscribed()) {
            subscriptionProductsById.unsubscribe();
        }
        Observable<ApiResponse<List<ProductEntity>>> observableProductById = mService.loadProductsInStore(id);

        subscriptionProductsById = observableProductById.subscribe(new Subscriber<ApiResponse<List<ProductEntity>>>() {
            @Override
            public void onCompleted() {
                observableProductsList.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                observableProductsList.onError(e);
            }

            @Override
            public void onNext(ApiResponse<List<ProductEntity>> listApiResponse) {
                observableProductsList.onNext(listApiResponse.getData());
            }
        });
    }

    /////////////////////// -------- STORE ------------/////////////////
    public static Observable<List<StoreEntity>> getStoreObservable() {
        if (observableStoreList == null) {
            resetStoreObservable();
        }
        return observableStoreList;
    }

    private static void resetStoreObservable() {
        observableStoreList = ReplaySubject.create();

        if (subscriptionStore != null && !subscriptionStore.isUnsubscribed()) {
            subscriptionStore.unsubscribe();
        }

        subscriptionStore = observableStore.subscribe(new Subscriber<ApiResponse<List<StoreEntity>>>() {
            @Override
            public void onCompleted() {
                observableStoreList.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                observableStoreList.onError(e);
            }

            @Override
            public void onNext(ApiResponse<List<StoreEntity>> listApiResponse) {
                observableStoreList.onNext(listApiResponse.getData());
            }
        });
    }
}
