package com.example.vasskob.testrotation.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vasskob.testrotation.presentation.Constants;
import com.example.vasskob.testrotation.R;
import com.example.vasskob.testrotation.presentation.model.ProductModel;
import com.example.vasskob.testrotation.presentation.presenter.DetailPresenter;
import com.example.vasskob.testrotation.presentation.view.DetailView;
import com.example.vasskob.testrotation.presentation.view.adapter.ProductListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

    //    long storeId = getIntent().getExtras().getLong(Constants.STORE_ID);
        int storeId = getIntent().getExtras().getInt(Constants.STORE_POSITION);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        DetailPresenter presenter = new DetailPresenter(storeId);
        presenter.attachView(this);
        presenter.loadData();

    }


    @Override
    public void showLoadingSuccessToast() {

    }

    @Override
    public void showLoadingErrorToast() {

    }

    @Override
    public void showConnectionFailedToast() {

    }

    @Override
    public void showConnectionSuccessToast() {

    }

   @Override
    public void showProductList(List<ProductModel> productList) {
        rvProducts.setAdapter(new ProductListAdapter(productList, this));
        pbLoading.setVisibility(View.GONE);
    }
}
