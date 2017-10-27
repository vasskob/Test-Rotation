package com.example.vasskob.testrotation.presentation.presenter;

import android.util.Log;

import com.example.vasskob.testrotation.data.repository.ProductDataRepository;
import com.example.vasskob.testrotation.data.repository.StoreDataRepository;
import com.example.vasskob.testrotation.domain.entity.Product;
import com.example.vasskob.testrotation.presentation.mapper.ProductDataMapper;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.view.DetailView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DetailPresenter implements IDetailPresenter {
    private static final String TAG = DetailPresenter.class.getSimpleName();
    private final int position;
    private DetailView mView;
    private Subscription subscription;

    public DetailPresenter(int position) {
        this.position = position;
    }

    @Override
    public void loadData() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = new StoreDataRepository()
                .stores()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(stores -> stores)
                .flatMap(store -> getProductsForStore(store.getId()))
                .map(products -> new ProductDataMapper().transform(products))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber());

//        subscription = RetrofitSingleton.getStoreObservable()
//                .subscribeOn(Schedulers.io())
//                .flatMapIterable(stores -> stores)
//                .flatMap(store -> getProductsForStore(store.getId()))
//                .toList()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MySubscriber());
    }

    private Observable<List<Product>> getProductsForStore(long id) {
        return new ProductDataRepository().products(id);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void attachView(DetailView view) {
        mView = view;
    }

    private class MySubscriber extends Subscriber<List<List<ProductModel>>> {
        @Override
        public void onCompleted() {
            mView.showLoadingSuccessToast();
            Log.d(TAG, "My onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            mView.showLoadingErrorToast();
            Log.e(TAG, "My onError: ", e);
        }

        @Override
        public void onNext(List<List<ProductModel>> listProductsList) {
            mView.showProductList(listProductsList.get(position));

        }
    }
}
