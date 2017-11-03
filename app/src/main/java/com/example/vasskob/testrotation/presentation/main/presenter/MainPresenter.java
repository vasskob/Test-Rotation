package com.example.vasskob.testrotation.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vasskob.testrotation.domain.repository.StoreRepository;
import com.example.vasskob.testrotation.presentation.main.view.MainView;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private StoreRepository mStoreDataRepository;
    private CompositeDisposable mCompositeDisposable;
    private Disposable mNetworkDisposable;

    private boolean dataLoaded;

    public MainPresenter(StoreRepository storeDataRepository) {
        this.mStoreDataRepository = storeDataRepository;
        mCompositeDisposable = new CompositeDisposable();
        Timber.d("checkConnection: " + getViewState());
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("onFirstViewAttach: ");
        checkConnection();
    }

    public void checkConnection() {
        Timber.d("onFirstViewAttach:2 ");
        mNetworkDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {
                    if (isConnectedToInternet) {
                        if (!dataLoaded) {
                            Timber.d("onFirstViewAttach:3 ");
                            getViewState().onConnectionSuccess();
                            loadShopList();
                        }
                    } else {
                        getViewState().onConnectionError();
                        getViewState().stopLoadingProgress();
                    }
                });
    }

    private void loadShopList() {
        mStoreDataRepository.getStores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    getViewState().startLoadingProgress();
                })
                .doAfterTerminate(getViewState()::stopLoadingProgress)
                .subscribe(storeModels -> {
                            getViewState().showShopList(storeModels);
                            getViewState().onShopLoadSuccess();
                            dataLoaded = true;
                        },
                        throwable -> getViewState().onShopLoadError());
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void clearConnectionCheck() {
        dataLoaded = false;
        disposeNetwork();
    }

    private void disposeNetwork() {
        if (!mNetworkDisposable.isDisposed()) {
            mNetworkDisposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
        disposeNetwork();
        super.onDestroy();
    }
}
