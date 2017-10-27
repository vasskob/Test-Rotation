package com.example.vasskob.testrotation.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vasskob.testrotation.data.repository.CombinedDataRepository;
import com.example.vasskob.testrotation.presentation.mapper.ShopVsProductDataMapper;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.model.SpecialStoreModel;
import com.example.vasskob.testrotation.presentation.model.StoreModel;
import com.example.vasskob.testrotation.presentation.model.StoreVsProductModel;
import com.example.vasskob.testrotation.presentation.view.MainView;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter implements IMainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private static final String CITY = " city";
    private static final String FILTER_STRING = "a";
    private static final String MODIFIER = " !";
    private MainView mView;
    private Subscription subscription;

    @Override
    public void loadData() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = new CombinedDataRepository()
                .storesVsProducts()
                .subscribeOn(Schedulers.io())
                .map(storeVsProducts -> new ShopVsProductDataMapper().transform(storeVsProducts))
                .map(this::getSpecialStores)
                .flatMapIterable(specialStores -> specialStores)
                .filter(specialStore -> specialStore.getCity().contains(FILTER_STRING))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber());
    }

    @NonNull
    private List<SpecialStoreModel> getSpecialStores(List<StoreVsProductModel> storeVsProductList) {
        List<SpecialStoreModel> specialStoreList = new ArrayList<>();
        if (storeVsProductList != null && !storeVsProductList.isEmpty()) {
            for (StoreVsProductModel storeVsProduct : storeVsProductList) {
                StoreModel store = storeVsProduct.getStore();
                ProductModel product = storeVsProduct.getProduct();
                specialStoreList.add(new SpecialStoreModel(store.getId(), store.getName() + MODIFIER, store.getCity() + CITY, store.getAddress1(), product.getName()));
            }
        }
        return specialStoreList;
    }

    @Override
    public void attachView(MainView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscribe();
    }

    private void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private class MySubscriber extends Subscriber<List<SpecialStoreModel>> {

        @Override
        public void onCompleted() {
            mView.showLoadingSuccessToast();
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            mView.showLoadingErrorToast();
            Log.e(TAG, "My onError: ", e);
        }

        @Override
        public void onNext(List<SpecialStoreModel> stores) {
            int prevSize = stores.size();
            Log.d(TAG, "onNext: " + prevSize);
            mView.showStoreList(stores);
        }

    }
}
