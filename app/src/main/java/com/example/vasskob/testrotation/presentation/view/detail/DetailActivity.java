package com.example.vasskob.testrotation.presentation.view.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.data.repository.ProductDataRepository;
import com.example.vasskob.testrotation.global.Constants;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.presenter.DetailPresenter;
import com.example.vasskob.testrotation.presentation.view.common.BaseActivity;
import com.example.vasskob.testrotation.presentation.view.detail.adapter.ProductListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class DetailActivity extends BaseActivity implements DetailView {

    private long storeId;

    private ProductListAdapter mAdapter;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Inject
    ProductDataRepository mProductDataRepository;

    @InjectPresenter(type = PresenterType.LOCAL)
    DetailPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    DetailPresenter providePresenter() {
        return new DetailPresenter(mProductDataRepository);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        fetchIntent();
        initAdapter();
        mPresenter.checkConnection(storeId);
     }

    private void fetchIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            storeId = extras.getLong(Constants.STORE_ID);
        }
    }

    private void initAdapter() {
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductListAdapter(this);
        rvProducts.setAdapter(mAdapter);
    }

    @Override
    public void onProductLoadSuccess(List<ProductModel> productList) {
        Timber.d("onProductLoadSuccess: " + productList);
        mAdapter.addItems(productList);
        showToast(R.string.data_load_success);
    }

    @Override
    public void onProductLoadError() {
        showToast(R.string.products_load_err);
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
