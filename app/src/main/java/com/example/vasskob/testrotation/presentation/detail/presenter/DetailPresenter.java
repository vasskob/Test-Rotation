package com.example.vasskob.testrotation.presentation.detail.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vasskob.testrotation.data.repository.ProductRepositoryImpl;
import com.example.vasskob.testrotation.presentation.detail.view.DetailView;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private final ProductRepositoryImpl mProductDataRepository;
    private CompositeDisposable mCompositeDisposable;

    private boolean dataLoaded;
    private long storeId;

    public DetailPresenter(ProductRepositoryImpl productDataRepository) {
        mProductDataRepository = productDataRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("onFirstViewAttach: ");
        checkConnection(storeId);
    }

    private void checkConnection(long storeId) {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::addDisposable)
                .subscribe(isConnectedToInternet -> {
                    if (isConnectedToInternet) {
                        if (!dataLoaded) {
                            getViewState().showConnectionSuccessToast();
                            loadProductInStore(storeId);
                        }
                    } else {
                        getViewState().showConnectionFailedToast();
                        getViewState().stopLoadingProgress();
                    }
                });
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    private void loadProductInStore(long storeId) {
        mProductDataRepository.getProducts(storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    getViewState().startLoadingProgress();
                })
                .doAfterTerminate(getViewState()::stopLoadingProgress)
                .subscribe(productModels -> {
                            getViewState().showProducts(productModels);
                            getViewState().onProductLoadSuccess();
                            dataLoaded = true;
                        },
                        throwable -> getViewState().onProductLoadError());
    }

    @Override
    public void onDestroy() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
        super.onDestroy();
    }
}