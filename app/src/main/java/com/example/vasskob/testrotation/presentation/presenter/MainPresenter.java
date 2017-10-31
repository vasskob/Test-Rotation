package com.example.vasskob.testrotation.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vasskob.testrotation.data.repository.StoreRepositoryImpl;
import com.example.vasskob.testrotation.presentation.view.main.MainView;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private StoreRepositoryImpl mStoreDataRepository;
    private CompositeDisposable mCompositeDisposable;
    private boolean dataLoaded;

    public MainPresenter(StoreRepositoryImpl storeDataRepository) {
        this.mStoreDataRepository = storeDataRepository;
        mCompositeDisposable = new CompositeDisposable();
        Timber.d("checkConnection: " + getViewState());
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Timber.d("onFirstViewAttach: ");
        getViewState().starLoadData();
    }

    public void checkConnection() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {
                    if (isConnectedToInternet) {
                        if (!dataLoaded) {
                            getViewState().showConnectionSuccessToast();
                            loadShopList();
                        }
                    } else {
                        getViewState().showConnectionFailedToast();
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

    public void setDataLoadedFalse() {
        dataLoaded = false;
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
