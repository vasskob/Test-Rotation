package com.example.vasskob.testrotation.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vasskob.testrotation.data.repository.ProductDataRepository;
import com.example.vasskob.testrotation.presentation.mapper.ProductDataMapper;
import com.example.vasskob.testrotation.presentation.view.detail.DetailView;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private final ProductDataRepository mProductDataRepository;
    private CompositeDisposable mCompositeDisposable;
    private boolean dataLoaded;

    public DetailPresenter(ProductDataRepository productDataRepository) {
        mProductDataRepository = productDataRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("onFirstViewAttach: ");
        getViewState().starLoadData();
    }

    public void checkConnection(long storeId) {
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
                .map(products -> new ProductDataMapper().transform(products))
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
