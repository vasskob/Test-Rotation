package com.example.vasskob.testrotation.presentation.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.data.repository.StoreDataRepository;

import com.example.vasskob.testrotation.global.Constants;
import com.example.vasskob.testrotation.presentation.model.StoreModel;
import com.example.vasskob.testrotation.presentation.presenter.MainPresenter;
import com.example.vasskob.testrotation.presentation.view.common.BaseActivity;
import com.example.vasskob.testrotation.presentation.view.detail.DetailActivity;
import com.example.vasskob.testrotation.presentation.view.main.adapter.StoreListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

    private StoreListAdapter mAdapter;

    @BindView(R.id.rv_stores)
    RecyclerView rvStores;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Inject
    StoreDataRepository mStoreDataRepository;

    @InjectPresenter(type = PresenterType.LOCAL)
    MainPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    MainPresenter providePresenter() {
        return new MainPresenter(mStoreDataRepository);
    }

    private final StoreListAdapter.onStoreClickListener onClickListener = view -> {
        int position = rvStores.getChildAdapterPosition(view);
        long storeId = mAdapter.getItem(position).getId();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.STORE_ID, storeId);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAdapter();
        mPresenter.checkConnection();
    }

    private void initAdapter() {
        rvStores.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StoreListAdapter(onClickListener);
        rvStores.setAdapter(mAdapter);
    }

    @Override
    public void onShopLoadSuccess(List<StoreModel> storeList) {
        Timber.d("onShopLoadSuccess: " + storeList);
        mAdapter.addItems(storeList);
        showToast(R.string.data_load_success);
    }

    @Override
    public void onShopLoadError() {
        showToast(R.string.shop_load_err);
    }

    @Override
    public void startLoadingProgress() {
        pbLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void stopLoadingProgress() {
        pbLoading.setVisibility(View.GONE);
    }
}
