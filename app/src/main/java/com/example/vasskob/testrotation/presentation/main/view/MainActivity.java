package com.example.vasskob.testrotation.presentation.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.domain.model.Store;
import com.example.vasskob.testrotation.presentation.common.view.BaseActivity;
import com.example.vasskob.testrotation.presentation.common.Constants;
import com.example.vasskob.testrotation.presentation.detail.view.DetailActivity;
import com.example.vasskob.testrotation.presentation.main.presenter.MainPresenter;
import com.example.vasskob.testrotation.presentation.main.adapter.StoreListAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

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
    Provider<MainPresenter> mPresenterProvider;

    @InjectPresenter(type = PresenterType.LOCAL)
    MainPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    MainPresenter providePresenter() {
        return mPresenterProvider.get();
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
        Timber.d("onCreate: ");

        initAdapter();
    }

    private void initAdapter() {
        rvStores.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StoreListAdapter(onClickListener);
        rvStores.setAdapter(mAdapter);
    }

    @Override
    public void showShopList(List<Store> storeList) {
        Timber.d("showShopList: ");
        mAdapter.addItems(storeList);
    }

    @Override
    public void onShopLoadSuccess() {
        Timber.d("onShopLoadSuccess: ");
        showToast(R.string.stores_load_success);
    }

    @Override
    public void onShopLoadError() {
        showToast(R.string.shop_load_err);
    }

    @Override
    public void startLoadingProgress() {
        Timber.d("startLoadingProgress: ");
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoadingProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_update:
                mAdapter.clearItems();
                mPresenter.clearConnectionCheck();
                mPresenter.checkConnection();
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
